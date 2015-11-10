package com.dambrisco.intercept;

import android.util.Log;

import java.lang.reflect.Method;

/**
 * @author Dan Ambrisco
 */
public class LoggableInterceptor implements InterceptorFilter {
    @Override
    public Object perform(MethodInvocation invocation, InterceptorChain chain) throws Throwable {
        Log.e("LOGGING", "SHIT GOT LOGGED");
        return chain.advise(invocation);
    }

    @Override
    public boolean applies(MethodInvocation invocation) {
        try {
            final Method method = invocation.getDelegate().getClass()
                    .getMethod(invocation.getMethod().getName(), invocation.getMethod().getParameterTypes());
            return method != null && method.getAnnotation(Loggable.class) != null;
        } catch (final NoSuchMethodException e) {
            return false;
        }
    }
}
