package com.zenfer.demo.util;

import android.app.Activity;
import android.content.Context;

import java.util.List;
import java.util.Map;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.external.ExternalAdaptInfo;
import me.jessyan.autosize.external.ExternalAdaptManager;

/**
 * 今日头条屏幕适配方案终极版，一个极低成本的 Android 屏幕适配方案
 * <a href="https://github.com/JessYanCoding/AndroidAutoSize/blob/master/README-zh.md#preview">点击查看在布局中的实时预览方式</a>
 *
 * @author Zenfer
 * @date 2019/6/17 15:40
 */
public class AutoSizeUtil {

    /**
     * 初始化屏幕适配
     */
    public static void initAutoSize(Context context) {
        AutoSize.initCompatMultiProcess(context);

        /**
         * 以下是 AndroidAutoSize 可以自定义的参数, {@link AutoSizeConfig} 的每个方法的注释都写的很详细
         * 使用前请一定记得跳进源码，查看方法的注释, 下面的注释只是简单描述!!!
         */
        AutoSizeConfig.getInstance()
                //是否让框架支持自定义 Fragment 的适配参数, 由于这个需求是比较少见的, 所以须要使用者手动开启
                //如果没有这个需求建议不开启
                .setCustomFragment(true)
                //是否屏蔽系统字体大小对 AndroidAutoSize 的影响, 如果为 true, App 内的字体的大小将不会跟随系统设置中字体大小的改变
                //如果为 false, 则会跟随系统设置中字体大小的改变, 默认为 false
                .setExcludeFontScale(true)
        //是否打印 AutoSize 的内部日志, 默认为 true, 如果您不想 AutoSize 打印日志, 则请设置为 false
        //.setLog(false)

        //是否使用设备的实际尺寸做适配, 默认为 false, 如果设置为 false, 在以屏幕高度为基准进行适配时
        //AutoSize 会将屏幕总高度减去状态栏高度来做适配
        //设置为 true 则使用设备的实际屏幕高度, 不会减去状态栏高度
        // .setUseDeviceSize(true)

        //是否全局按照宽度进行等比例适配, 默认为 true, 如果设置为 false, AutoSize 会全局按照高度进行适配
        //.setBaseOnWidth(false)

        //设置屏幕适配逻辑策略类, 一般不用设置, 使用框架默认的就好
        //.setAutoAdaptStrategy(new AutoAdaptStrategy())
        ;
    }

    /**
     * 给外部的三方库 {@link android.app.Activity} 自定义适配参数, 因为三方库的 {@link android.app.Activity} 并不能通过实现
     * {@link me.jessyan.autosize.internal.CustomAdapt} 接口的方式来提供自定义适配参数 (因为远程依赖改不了源码)
     * 所以使用 {@link me.jessyan.autosize.external.ExternalAdaptManager} 来替代实现接口的方式, 来提供自定义适配参数
     * {@link ExternalAdaptManager} 是一个管理外部三方库的适配信息和状态的管理类, 详细介绍请看 {@link ExternalAdaptManager} 的类注释
     */
    public static <T extends Activity> void customAdaptForExternal(List<Class<T>> cancelActivities, Map<Class<T>, ExternalAdaptInfo> externalActivities) {
        ExternalAdaptManager manager = AutoSizeConfig.getInstance().getExternalAdaptManager();

        //加入的 Activity 将会放弃屏幕适配, 一般用于三方库的 Activity, 详情请看方法注释
        //如果不想放弃三方库页面的适配, 请用 addExternalAdaptInfoOfActivity 方法, 建议对三方库页面进行适配, 让自己的 App 更完美一点
        for (Class<T> clazz : cancelActivities) {
            manager.addCancelAdaptOfActivity(clazz);
        }

        //为指定的 Activity 提供自定义适配参数, AndroidAutoSize 将会按照提供的适配参数进行适配, 详情请看方法注释
        //一般用于三方库的 Activity, 因为三方库的设计图尺寸可能和项目自身的设计图尺寸不一致, 所以要想完美适配三方库的页面
        //就需要提供三方库的设计图尺寸, 以及适配的方向 (以宽为基准还是高为基准?)
        //三方库页面的设计图尺寸可能无法获知, 所以如果想让三方库的适配效果达到最好, 只有靠不断的尝试
        //由于 AndroidAutoSize 可以让布局在所有设备上都等比例缩放, 所以只要您在一个设备上测试出了一个最完美的设计图尺寸
        //那这个三方库页面在其他设备上也会呈现出同样的适配效果, 等比例缩放, 所以也就完成了三方库页面的屏幕适配
        //即使在不改三方库源码的情况下也可以完美适配三方库的页面, 这就是 AndroidAutoSize 的优势
        //但前提是三方库页面的布局使用的是 dp 和 sp, 如果布局全部使用的 px, 那 AndroidAutoSize 也将无能为力
        for (Class<T> clazz : externalActivities.keySet()) {
            manager.addExternalAdaptInfoOfActivity(clazz, externalActivities.get(clazz));
        }
    }
}
