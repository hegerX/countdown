package com.heger.countdown.data;

import com.heger.countdown.Constant;

public class TimerStatusBarData {
    private int second = 0;
    private int timeLengthSec;
    /**
     * 重复提醒周期
     */
    private int repetitionPeriodTimeSec = -1;
    /**
     * 最后剩余提醒时间
     */
    private int lastRemainingNoteTimeSec = -1;
    private String timeType;

    public TimerStatusBarData() {
        this.timeLengthSec = Constant.Default_TIME_LENGTH;
        this.timeType = Constant.UP_TIME;
    }

    public int getTimeLengthSec() {
        return timeLengthSec;
    }

    public int getSecond() {
        return second;
    }

    public int getRepetitionPeriodTimeSec() {
        return repetitionPeriodTimeSec;
    }

    public int getLastRemainingNoteTimeSec() {
        return lastRemainingNoteTimeSec;
    }

    public void setLastRemainingNoteTimeSec(int lastRemainingNoteTimeSec) {
        this.lastRemainingNoteTimeSec = lastRemainingNoteTimeSec;
    }

    public void setRepetitionPeriodTimeSec(int repetitionPeriodTimeSec) {
        this.repetitionPeriodTimeSec = repetitionPeriodTimeSec;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getTimeType() {
        return this.timeType;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setTimeLengthSec(int timeLengthSec) {
        this.timeLengthSec = timeLengthSec;
    }
}
