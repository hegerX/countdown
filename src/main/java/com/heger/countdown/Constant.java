package com.heger.countdown;

/**
 * <h3>CountDown</h3>
 *
 * @author heger
 * @description <p>常量</p>
 * @date 2023-07-30 09:29
 **/
public class Constant {

    public static TimeState COUNTDOWN_STATUS = TimeState.TIME_NOT_STARTED;

    public static final String UP_TIME = "0";
    /**
     * 倒计时
     */
    public static final String DOWN_TIME = "1";
    /**
     * 默认倒计时时间,1个番茄时钟
     */
    public static final int Default_TIME_LENGTH = 25 * 60;
}
