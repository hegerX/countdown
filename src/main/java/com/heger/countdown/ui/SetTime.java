package com.heger.countdown.ui;

import com.heger.countdown.utils.Constant;
import com.heger.countdown.data.CountDownData;
import com.heger.countdown.service.TimeBarAnAction;
import com.heger.countdown.utils.TimeState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SetTime extends JDialog {
    private final CountDownData countDownData = new CountDownData();
    private JPanel contentPane;
    private JButton startButton;
    private JButton closeButton;
    private JSpinner timeLengthMinJTextField;
    private JSpinner repetitionPeriodTimeMinJSpinner;
    private JSpinner lastRemainingNoteTimeMinJSpinner;
    private JButton resetButton;

    public SetTime(TimerBarWidget timerBarWidget) {
        this.countDownData.setTimerBarWidget(timerBarWidget);
        setContentPane(contentPane);
        setModal(true);
        setStartButtonTextAndIsEditedStatusByStatus();
        getRootPane().setDefaultButton(startButton);
        getPreConfiguration(timerBarWidget);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (startButton.getText().equals("暂停") && Constant.COUNTDOWN_STATUS == TimeState.TIME_RUNNING) {
                    countDownData.stopTime();
                    Constant.COUNTDOWN_STATUS = TimeState.TIME_STOPPED;
                    TimeBarAnAction.endIcon();
                    close();
                } else if (startButton.getText().equals("开始") && Constant.COUNTDOWN_STATUS == TimeState.TIME_STOPPED) {
                    timerBarWidget.startTimer();
                    Constant.COUNTDOWN_STATUS = TimeState.TIME_RUNNING;
                    TimeBarAnAction.startIcon();
                    close();
                } else if (startButton.getText().equals("开始") && Constant.COUNTDOWN_STATUS == TimeState.TIME_NOT_STARTED){
                    if (checkLastRemainingNoteTimeMin(getLastRemainingNoteTimeMinJSpinnerValue(), getTimeLengthMinJTextFieldValue())
                            && checkRepetitionPeriodTimeMin(getRepetitionPeriodTimeMinJSpinnerValue(), getTimeLengthMinJTextFieldValue())
                            && checkAndTimeLengthMin(getTimeLengthMinJTextFieldValue())) {
                        setTimeLengthMin();
                        setRepetitionPeriodTimeMin();
                        setLastRemainingNoteTimeMin();
                        countDownData.setCountDownType();
                        countDownData.startCountDown();
                        // 修改TimeBarAnAction图片为启动状态
                        Constant.COUNTDOWN_STATUS = TimeState.TIME_RUNNING;
                        TimeBarAnAction.startIcon();
                        close(); // 所有条件满足后才可以关闭窗口
                    }
                }
                setStartButtonTextAndIsEditedStatusByStatus();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doResetOrCloseListener();
                close();
            }
        });

        // call close() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                close();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doResetOrCloseListener();
                setStartButtonTextAndIsEditedStatusByStatus();
            }
        });
    }

    private Integer getRepetitionPeriodTimeMinJSpinnerValue() {
        return (Integer) repetitionPeriodTimeMinJSpinner.getValue();
    }

    private Integer getTimeLengthMinJTextFieldValue() {
        return (Integer) timeLengthMinJTextField.getValue();
    }

    private Integer getLastRemainingNoteTimeMinJSpinnerValue() {
        return (Integer) lastRemainingNoteTimeMinJSpinner.getValue();
    }

    private void setStartButtonText(String text) {
        startButton.setText(text);
    }

    private void doResetOrCloseListener() {
        countDownData.resetTime();
        TimeBarAnAction.endIcon();
        Constant.COUNTDOWN_STATUS = TimeState.TIME_NOT_STARTED;
        settingDefaultValue();
    }

    private void settingDefaultValue() {
        timeLengthMinJTextField.setValue(25);
        repetitionPeriodTimeMinJSpinner.setValue(0);
        lastRemainingNoteTimeMinJSpinner.setValue(0);
        // 更新UI
        contentPane.repaint();
    }

    private void setStartButtonTextAndIsEditedStatusByStatus() {
        if (Constant.COUNTDOWN_STATUS == TimeState.TIME_RUNNING) {
            setStartButtonText("暂停");
            isEdited(false);
        } else if (Constant.COUNTDOWN_STATUS == TimeState.TIME_NOT_STARTED){
            setStartButtonText("开始");
            isEdited(true);
        } else {
            setStartButtonText("开始");
            isEdited(false);
        }
    }

    private void isEdited(boolean enabled) {
        timeLengthMinJTextField.setEnabled(enabled);
        repetitionPeriodTimeMinJSpinner.setEnabled(enabled);
        lastRemainingNoteTimeMinJSpinner.setEnabled(enabled);
    }

    /**
     * 获取之前配置的信息
     */
    private void getPreConfiguration(TimerBarWidget timerBarWidget) {
        this.setTimeLengthMin((timerBarWidget != null ? timerBarWidget.getTimeLengthSec() : 0) / 60);
        this.setLastRemainingNoteTimeMin((timerBarWidget != null ?
                timerBarWidget.getLastRemainingNoteTimeSec() : 0) / 60);
        this.setRepetitionPeriodTimeMin((timerBarWidget != null ?
                timerBarWidget.getRepetitionPeriodTimeSec() : 0) / 60);
    }

    private void setLastRemainingNoteTimeMin() {
        this.countDownData.setLastRemainingNoteTimeMin(getLastRemainingNoteTimeMinJSpinnerValue());
        this.countDownData.setLastRemainingNoteTime(getLastRemainingNoteTimeMinJSpinnerValue());
    }

    private void setRepetitionPeriodTimeMin() {
        this.countDownData.setRepetitionPeriodTimeMin(getRepetitionPeriodTimeMinJSpinnerValue());
        this.countDownData.setRepetitionPeriodTime(getRepetitionPeriodTimeMinJSpinnerValue());
    }

    private void setTimeLengthMin() {
        this.countDownData.setTimeLengthMin(getTimeLengthMinJTextFieldValue());
        this.countDownData.setTimeLength(getTimeLengthMinJTextFieldValue());
    }

    private boolean checkRepetitionPeriodTimeMin(int repetitionPeriodTimeMin, int timeLengthMin) {
        if (countDownData.checkRepetitionPeriodTimeMin(repetitionPeriodTimeMin, timeLengthMin)) {
            JOptionPane.showMessageDialog(null, "重复提醒周期大于计时时间", "提示", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    private boolean checkLastRemainingNoteTimeMin(int LastRemainingNoteTimeMin, int timeLengthMin) {
        if (countDownData.checkLastRemainingNoteTimeMin(LastRemainingNoteTimeMin, timeLengthMin)) {
            JOptionPane.showMessageDialog(null, "最后剩余提醒时间大于计时时间", "提示", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    private boolean checkAndTimeLengthMin(int timeLengthMin) {
        if (countDownData.checkTimeLengthMin(timeLengthMin)) {
            JOptionPane.showMessageDialog(null, "请输入0~1000的数字", "提示", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            return true;
        }
    }


    private boolean isNum(String num) {
        char[] arr = num.toCharArray();
        for (char c : arr) {
            if (c == '-') {
                JOptionPane.showMessageDialog(null, "请输入正整数", "提示", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (!Character.isDigit(c)) {
                JOptionPane.showMessageDialog(null, "请输入整数", "提示", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void close() {
        // add your code here
        dispose();
    }

    public void setTimeLengthMin(int timeLengthMin) {
        this.timeLengthMinJTextField.setValue(timeLengthMin);
    }

    public void setRepetitionPeriodTimeMin(int repetitionPeriodTimeMin) {
        this.repetitionPeriodTimeMinJSpinner.setValue(repetitionPeriodTimeMin);
    }

    public void setLastRemainingNoteTimeMin(int lastRemainingNoteTimeMin) {
        this.lastRemainingNoteTimeMinJSpinner.setValue(lastRemainingNoteTimeMin);
    }
}
