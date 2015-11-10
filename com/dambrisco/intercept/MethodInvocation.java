package com.dambrisco.intercept;

import java.lang.reflect.Method;

/**
 *
 * @author Przemek Jakubczyk
 * @author Dan Ambrisco
 *
 */
public interface MethodInvocation {

    /**
     * Method used for invocation.
     *
     * @return
     */
    Method getMethod();

    /**
     * Object from which method is called.
     *
     * @return
     */
    Object getDelegate();

    /**
     * Method args.
     *
     * @return
     */
    Object[] getMethodArgs();
}
