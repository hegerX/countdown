package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * <h3>CountDown</h3>
 *
 * @author heger
 * @description <p>图标类</p>
 * @date 2023-07-30 23:18
 **/
public interface DownTimeIcons {
    Icon TIME_START = IconLoader.getIcon("/icons/time_start.svg", DownTimeIcons.class);
    Icon TIME_END = IconLoader.getIcon("/icons/time_end.svg", DownTimeIcons.class);
}
