package com.dambrisco.intercept;

import dagger.ObjectGraph;

import javax.inject.Provider;
import java.lang.reflect.Proxy;

/**
 * @author Dan Ambrisco
 */
public class InterceptorProvider<I, C extends I> implements Provider<I> {
    private final Class<C> mClazz;
    private final Class<I> mIface;
    private final Class<? extends InterceptorFilter>[] mFilterClasses;

    public InterceptorProvider(Class<I> iface, Class<C> clazz, Class<? extends InterceptorFilter>... filterClasses) {
        mIface = iface;
        mClazz = clazz;
        mFilterClasses = filterClasses;
    }

    @SuppressWarnings("unchecked")
    @Override
    public I get() {
        final InterceptorFilter[] filters = new InterceptorFilter[mFilterClasses.length];
        ObjectGraph graph = ObjectGraph.create();
        for (int i = 0; i < mFilterClasses.length; ++i) {
            filters[i] = graph.get(mFilterClasses[i]);
        }
        final ClassLoader classLoader = mIface.getClassLoader();
        final Class< ? >[] interfaces = new Class< ? >[] { mIface };
        final Object instance = graph.get(mClazz);
        final InterceptorProxy proxyClass = new InterceptorProxy(instance, filters);
        return (I) Proxy.newProxyInstance(classLoader, interfaces, proxyClass);
    }
}
