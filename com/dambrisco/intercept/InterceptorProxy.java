package com.dambrisco.intercept;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Main Proxy class. Here all invoking logic is made.
 *
 * @author Przemek Jakubczyk
 * @author Dan Ambrisco
 *
 */
public class InterceptorProxy implements InvocationHandler {
    Object obj;
    InterceptorChain chain;

    public InterceptorProxy(final Object o, final InterceptorFilter... filter) {
        obj = o;
        chain = new InterceptorChain(filter[0], ProxyUtils.copyOfRange(filter, 1, filter.length));
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return chain.advise(new MethodInvocationImpl(method, obj, args));
    }
}
