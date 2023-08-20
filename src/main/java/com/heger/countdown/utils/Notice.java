package com.heger.countdown.utils;

public interface Notice {
    default void notice(String time) {

    }

    void notice(String time, String message);
}
