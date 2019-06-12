package com.zenfer.demo.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by zhangwulin on 2017/12/5.
 * Email:zhangwulin@feitaikeji.com
 */

public class ActivityUtil {

    private static Stack<Activity> activityStack = new Stack<>();

    /**
     * 添加Activity到堆栈
     */
    public static void addActivity(Activity activity) {
        activityStack.push(activity);
    }

    public static void removeActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public static Activity currentActivity() {
        if (activityStack.isEmpty()) {
            return null;
        }
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public static void finishCurrentActivity() {
        if (activityStack.isEmpty()) {
            return;
        }
        Activity activity = activityStack.pop();
        activity.finish();
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        activityStack.remove(activity);
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 删除当前Activity以外的所有界面
     */
    public static void removeExceptByCurrentActivity(Class className) {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            if (activity.getClass().equals(className)) {
                continue;
            }
            finishActivity(activity);
        }
    }

    /**
     * 检测activity是否存在
     *
     * @param cls 要判断的activity
     * @return true has exist false no exist
     */
    public static boolean doCheckActivityIsExist(Class<?> cls) {
        if (cls == null || activityStack == null) {
            return false;
        }
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

}
