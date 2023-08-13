package com.heger.countdown.service;

import com.heger.countdown.ui.TimerBarWidget;
import com.heger.countdown.ui.SetTime;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;
import icons.DownTimeIcons;

import java.util.Objects;

/**
 * <h3>CountDown</h3>
 *
 * @author heger
 * @description <p></p>
 * @date 2023-07-29 23:10
 **/
public class TimeBarAnAction extends AnAction {

    private SetTime setTime;

    private static Presentation presentaion;

    @Override
    public void actionPerformed(AnActionEvent e) {
        presentaion = e.getPresentation();
        Project project = e.getData(PlatformDataKeys.PROJECT);
        TimerBarWidget timerBarWidget = (TimerBarWidget) WindowManager.getInstance()
                .getStatusBar(Objects.requireNonNull(e.getProject())).getWidget(TimerBarWidget.ID);
        setTime = new SetTime(timerBarWidget);
        // 居中操作
        setTime.setLocationRelativeTo(null);
        // 面板的大小自动适应
        setTime.setResizable(true);
        setTime.pack();
        // 显示弹出框
        setTime.setVisible(true);
    }

    public static void startIcon() {
        presentaion.setIcon(DownTimeIcons.TIME_START);
    }

    public static void endIcon() {
        presentaion.setIcon(DownTimeIcons.TIME_END);
    }
}
