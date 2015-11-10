package com.dambrisco.intercept;

import dagger.Module;
import dagger.Provides;

/**
  * @author Dan Ambrisco
  */
@Module
public abstract class AbstractInterceptorModule {
    @Provides
    public <I, C extends I> I bindWithFilters(Class<I> iface, Class<C> clazz,
            Class<? extends InterceptorFilter>... filterClasses) {
        return new InterceptorProvider<>(iface, clazz, filterClasses).get();
    }
}
