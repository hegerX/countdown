package com.heger.countdown.ui;

import com.heger.countdown.utils.PluginConstant;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidgetFactory;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * <h3>CountDown</h3>
 *
 * @author heger
 * @description <p>显示时间提供者</p>
 * @date 2023-07-30 09:15
 **/
public class TimerStatusBarWidgetProvider implements StatusBarWidgetFactory {
    @Override
    public @NonNls @NotNull String getId() {
        return PluginConstant.COUNTDOWN_EDITOR_TIMER_STATUS_BAR_ID;
    }

    @Override
    public @Nls @NotNull String getDisplayName() {
        return PluginConstant.COUNTDOWN_EDITOR_TIMER_STATUS_BAR_ID;
    }

    @Override
    public boolean isAvailable(@NotNull Project project) {
        return true;
    }

    @Override
    public @NotNull StatusBarWidget createWidget(@NotNull Project project) {
        return new TimerBarWidget();
    }

    @Override
    public void disposeWidget(@NotNull StatusBarWidget widget) {
    }

    @Override
    public boolean canBeEnabledOn(@NotNull StatusBar statusBar) {
        return true;
    }
}
