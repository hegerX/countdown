package com.heger.countdown.data;

import com.heger.countdown.Constant;
import com.heger.countdown.ui.TimerBarWidget;

import java.io.Serializable;
import java.util.Objects;

public class CountDownData implements Serializable {
    private int timeLengthMin = -1;
    private int repetitionPeriodTimeMin = 0;
    private int lastRemainingNoteTimeMin = 0;
    /**
     * 默认倒计时
     */
    String countDownType;
    private TimerBarWidget timerBarWidget;

    public CountDownData() {
        this.countDownType = Constant.DOWN_TIME;
    }

    public int getTimeLengthMin() {
        return timeLengthMin;
    }

    /**
     * 重复提醒周期
     */
    public int getRepetitionPeriodTimeMin() {
        return repetitionPeriodTimeMin;
    }

    /**
     * 最后剩余提醒时间
     */
    public int getLastRemainingNoteTimeMin() {
        return lastRemainingNoteTimeMin;
    }

    public void setCountDownType() {
        if (getTimeLengthMin() == 0) {
            countDownType = Constant.UP_TIME;
        } else {
            countDownType = Constant.DOWN_TIME;
        }
    }

    public void setSecond(int TimeLengthMin, String timeType) {
        getTimerBarWidget().timerStatusBarData.setSecond(TimeLengthMin * 60);
        getTimerBarWidget().timerStatusBarData.setTimeType(timeType);
    }

    public void startCountDown() {
        if (Objects.equals(countDownType, Constant.UP_TIME)) {
            setSecond(getTimeLengthMin(), Constant.UP_TIME);
            getTimerBarWidget().startTimer("计时", 0, Constant.UP_TIME);
        } else {
            setSecond(getTimeLengthMin(), Constant.DOWN_TIME);
            getTimerBarWidget().startTimer("倒计时", getTimeLengthMin() * 60, Constant.DOWN_TIME);
        }
    }

    public void stopTime() {
        getTimerBarWidget().stopTimer();
    }

    public void resetTime() {
        getTimerBarWidget().reset();
    }

    public TimerBarWidget getTimerBarWidget() {
        return timerBarWidget;
    }

    public void setTimerBarWidget(TimerBarWidget timerBarWidget) {
        this.timerBarWidget = timerBarWidget;
    }

    public void setTimeLengthMin(int timeLengthMin) {
        this.timeLengthMin = timeLengthMin;
    }

    public boolean checkLastRemainingNoteTimeMin(int lastRemainingNoteTimeMin, int timeLengthMin) {
        return Objects.equals(countDownType, Constant.DOWN_TIME) && lastRemainingNoteTimeMin > timeLengthMin;
    }
    public void setTimeLength(int timeLengthMin) {
        getTimerBarWidget().timerStatusBarData.setTimeLengthSec(timeLengthMin * 60);
    }
    public void setLastRemainingNoteTime(int lastRemainingNoteTimeMin) {
        getTimerBarWidget().timerStatusBarData.setLastRemainingNoteTimeSec(lastRemainingNoteTimeMin * 60);
    }

    public void setRepetitionPeriodTime(int repetitionPeriodTimeMin) {
        getTimerBarWidget().timerStatusBarData.setRepetitionPeriodTimeSec(repetitionPeriodTimeMin * 60);
    }

    public boolean checkRepetitionPeriodTimeMin(int repetitionPeriodTimeMin, int timeLengthMin) {
        return Objects.equals(countDownType, Constant.DOWN_TIME) && repetitionPeriodTimeMin > timeLengthMin;
    }

    public boolean checkTimeLengthMin(int timeLengthMin) {
        return timeLengthMin < 0 || timeLengthMin > 1000;
    }

    public void setRepetitionPeriodTimeMin(int repetitionPeriodTimeMin) {
        this.repetitionPeriodTimeMin = repetitionPeriodTimeMin;
    }

    public void setLastRemainingNoteTimeMin(int lastRemainingNoteTimeMin) {
        this.lastRemainingNoteTimeMin = lastRemainingNoteTimeMin;
    }

}