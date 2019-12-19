package com.hellofresh.core.listeners;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.ArrayList;
import java.util.List;

@Aspect
public class StepLogger {

    private static final ThreadLocal<List<String>> METHODS_CALLS = new ThreadLocal<>();

    @Before("execution(@com.hellofresh.core.listeners.Step * com.hellofresh.pageobjects..*.*(..))")
    public void beforeInvocation(final JoinPoint joinPoint) throws Throwable {
        String message = "Step: " + joinPoint.getSignature().getName() + " on "+ joinPoint.getSignature().getDeclaringType().getSimpleName();
        getMethodsCallsList().add(message);
    }

    public static List<String> getMethodsCallsList() {
        if (METHODS_CALLS.get() == null) {
            METHODS_CALLS.set(new ArrayList<String>());
        }

        return METHODS_CALLS.get();
    }

    public static void clearMethodsCallsList() {
        if (METHODS_CALLS.get() != null) {
            METHODS_CALLS.remove();
        }
    }
}
