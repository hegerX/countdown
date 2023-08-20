package com.heger.countdown.ui;

/**
 * <h3>CountDown</h3>
 *
 * @author heger
 * @description <p>时间小工具</p>
 * @date 2023-07-30 09:20
 **/

import com.heger.countdown.utils.Constant;
import com.heger.countdown.utils.PluginConstant;
import com.heger.countdown.utils.TimeState;
import com.heger.countdown.data.TimerStatusBarData;
import com.heger.countdown.service.TimeBarAnAction;
import com.heger.countdown.utils.CountdownNoticeDialog;
import com.heger.countdown.utils.Notice;
import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.StatusBar;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class TimerBarWidget implements CustomStatusBarWidget {

    public final static String ID = PluginConstant.COUNTDOWN_TIMER_BAR_WIDGET;
    private static final Color Level1 = new Color(90, 190, 90);
    private static final Color Level2 = new Color(253, 180, 80);
    private static final Color Level3 = new Color(220, 78, 80);
    public final TimerStatusBarData timerStatusBarData = new TimerStatusBarData();
    private String name = "";

    private final Notice notice = new CountdownNoticeDialog();
    private final JLabel label = new JLabel(time());
    private final Timer timer = new Timer(1000, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int second1 = timerStatusBarData.getSecond();
            if (second1 > 0) {
                reckonByTime();
                label.setText(time());
            }
        }
    });

    private void UpTimeCharacterColor() {
        if (timerStatusBarData.getSecond() < 30 * 60) {
            label.setForeground(Level1);
        } else if (timerStatusBarData.getSecond() < 60 * 60) {
            label.setForeground(Level2);
        } else {
            label.setForeground(Level3);
        }
    }

    private void DownTimeCharacterColor() {
        if (timerStatusBarData.getSecond() < 30 * 60) {
            label.setForeground(Level3);
        } else if (timerStatusBarData.getSecond() < 60 * 60) {
            label.setForeground(Level2);
        } else {
            label.setForeground(Level1);
        }
    }

    private void reckonByTime() {
        int min = 0;
        if (timerStatusBarData.getTimeType().equals(Constant.UP_TIME)) {
            timerStatusBarData.setSecond(timerStatusBarData.getSecond() + 1);
            min = (timerStatusBarData.getTimeLengthSec() - this.timerStatusBarData.getSecond()) / 60;
            UpTimeCharacterColor();
        } else {
            timerStatusBarData.setSecond(timerStatusBarData.getSecond() - 1);
            min = this.timerStatusBarData.getSecond() / 60;
            DownTimeCharacterColor();
        }
        if (Objects.equals(this.timerStatusBarData.getLastRemainingNoteTimeSec(), this.timerStatusBarData.getSecond())) {
            notice.notice("剩余时间：" + min + "分", "最后提醒时间");
        }
        // 重复提醒处理逻辑
        if (this.timerStatusBarData.getRepetitionPeriodTimeSec() > 0
                && (this.timerStatusBarData.getSecond() - this.timerStatusBarData.getTimeLengthSec())
                % this.timerStatusBarData.getRepetitionPeriodTimeSec() == 0L) {
            notice.notice("剩余时间：" + min + "分", "重复提醒");
        }
    }

    private String time() {
        return String.format("[%s]%02d:%02d:%02d", name, timerStatusBarData.getSecond() / 60 / 60,
                timerStatusBarData.getSecond() / 60 % 60, timerStatusBarData.getSecond() % 60);
    }

    @Override
    public JComponent getComponent() {
        return label;
    }

    @NotNull
    @Override
    public String ID() {
        return ID;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {
        label.setVisible(false);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                    TimeBarAnAction.endIcon();
                    Constant.COUNTDOWN_STATUS = TimeState.TIME_STOPPED;
                } else {
                    timer.start();
                    TimeBarAnAction.startIcon();
                    Constant.COUNTDOWN_STATUS = TimeState.TIME_RUNNING;
                }
            }
        });
    }

    @Override
    public void dispose() {
        timer.stop();
    }

    public void startTimer(String name, int timeLength, String timeType) {
        this.timerStatusBarData.setTimeType(timeType);
        if (!this.name.equals(name)) {
            this.name = name;
            this.timerStatusBarData.setSecond(timeLength);
        }
        timer.start();
        if (!label.isVisible()) {
            label.setVisible(true);
        }
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void reset() {
        this.name = "";
        this.timerStatusBarData.setSecond(0);
        this.timerStatusBarData.setTimeLengthSec(Constant.Default_TIME_LENGTH);
        this.timerStatusBarData.setRepetitionPeriodTimeSec(-1);
        this.timerStatusBarData.setLastRemainingNoteTimeSec(-1);
        timer.stop();
        // 解决再次启动时显示上一次的时间
        label.setText(time());
        label.setVisible(false);
    }

    public int getTimeLengthSec() {
        return timerStatusBarData.getTimeLengthSec();
    }

    public int getRepetitionPeriodTimeSec() {
        return timerStatusBarData.getRepetitionPeriodTimeSec();
    }

    public int getLastRemainingNoteTimeSec() {
        return timerStatusBarData.getLastRemainingNoteTimeSec();
    }
}

