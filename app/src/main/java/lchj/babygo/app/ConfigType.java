package lchj.babygo.app;

/**
 * Created by XiMiTwo on 2017/10/9.
 */

public enum ConfigType {
    /**
     * 唯一的单例。只能初始化一次
     */
    API_HOST,//网络的域名
    APPLICAION_CONTEXT,
    CONFIG_READY,//控制初始化完成没
    HANDLER,
    ICON
}
