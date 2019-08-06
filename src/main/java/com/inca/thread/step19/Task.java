package com.inca.thread.step19;

@FunctionalInterface
public interface Task<T> {
    T call();
}
