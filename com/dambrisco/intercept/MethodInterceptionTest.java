package com.dambrisco.intercept;

import java.lang.reflect.Method;

/**
 * @author Dan Ambrisco
 */
public class TestMI {
    final Object object = new Object();
    Method method;
    private MethodInvocationImpl invocationImpl;

    Object method() {
        return object;
    }

    public void test() throws Throwable {
        method = TestMI.class.getDeclaredMethod("method");
        invocationImpl = new MethodInvocationImpl(method, this, null);

        InterceptorProxy proxy = new InterceptorProxy(this, new InterceptorFilter() {
            @Override
            public Object perform(MethodInvocation invocation, InterceptorChain chain) throws Throwable {
                return new Object();
            }

            @Override
            public boolean applies(MethodInvocation invocation) {
                return true;
            }
        });

        Object o = proxy.invoke(null, method, null);
    }
}
