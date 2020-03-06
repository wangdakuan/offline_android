package cm.offline.tv.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.List;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: ActivityUtils
 * Author: wangdakuan
 * Date: 2020-03-06 15:41
 * Description: Activity相关帮助类
 * History:
 * version：
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public final class ActivityUtils {

    private ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取栈顶Activity
     *
     * @return 栈顶Activity
     */
    public static AppCompatActivity getTopActivity() {
        return (AppCompatActivity) AppUtils.sTopActivity;
    }


    /**
     * 启动Activity
     *
     * @param targetActivity 需要启动的页面
     */
    public static void navigateTo(@NonNull Class<? extends Activity> targetActivity) {
        navigateTo(targetActivity, new Intent());
    }

    /**
     * 启动Activity
     *
     * @param targetActivity 需要启动的页面
     * @param extras         页面传值对象
     */
    public static void navigateTo(@NonNull Class<? extends Activity> targetActivity, @NonNull Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        navigateTo(targetActivity, intent);
    }

    /**
     * 启动Activity
     *
     * @param targetActivity 需要启动的页面
     * @param extras         页面传值对象
     * @param requestCode    请求回调码
     */
    public static void navigateTo(@NonNull Class<? extends Activity> targetActivity, @NonNull Bundle extras, int requestCode) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        navigateTo(targetActivity, intent, requestCode);
    }

    /**
     * 启动Activity
     *
     * @param targetActivity 启动的页面
     * @param requestCode    请求回调码
     */
    public static void navigateTo(@NonNull Class<? extends Activity> targetActivity, int requestCode) {
        navigateTo(targetActivity, new Intent(), requestCode);
    }

    /**
     * 启动Activity
     *
     * @param targetActivity 需要启动的页面
     * @param intent         Intent 对象
     */
    public static void navigateTo(@NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent) {
        Activity currentActivity = getTopActivity();
        navigateTo(currentActivity, targetActivity, intent, -1);
    }

    /**
     * 启动Activity
     *
     * @param targetActivity 需要启动的页面
     * @param intent         Intent 对象
     * @param requestCode    请求回调码
     */
    public static void navigateTo(@NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent, int requestCode) {
        Activity currentActivity = getTopActivity();
        navigateTo(currentActivity, targetActivity, intent, requestCode);
    }

    /**
     * 启动Activity
     *
     * @param context        上下文
     * @param targetActivity 启动的页面
     * @param intent         跳转Intent对象
     * @param requestCode    请求回调码
     */
    public static void navigateTo(@NonNull Activity context, @NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent, int requestCode) {
        intent.setClass(context, targetActivity);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 路由启动Activity
     * @param url activity 路由地址
     */
    public static void navigateUrlTo(@NonNull String url) {
        navigateUrlTo(url, null);
    }

    /**
     * 路由启动Activity
     *
     * @param url    activity 路由地址
     * @param extras 页面传值对象
     */
    public static void navigateUrlTo(@NonNull String url, @NonNull Bundle extras) {
        navigateUrlTo(url, extras, -1);
    }

    /**
     * 路由启动Activity
     *
     * @param url         activity 路由地址
     * @param extras      页面传值对象
     * @param requestCode 请求回调码
     */
    public static void navigateUrlTo(@NonNull String url, @NonNull Bundle extras, int requestCode) {
//        Activity currentActivity = getTopActivity();
//        Postcard postcard = ARouter.getInstance().build(url);
//        if (null != extras) {
//            postcard.with(extras).navigation(currentActivity, requestCode);
//        } else {
//            postcard.navigation(currentActivity, requestCode);
//        }
    }


    /**
     * 判断是否存在Activity
     *
     * @param cls activity.class
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isActivityExists(@NonNull final Class<?> cls) {
        Context context = AppUtils.getApp();
        Intent intent = new Intent();
        intent.setClassName(context.getPackageName(), cls.getName());
        return !(AppUtils.getApp().getPackageManager().resolveActivity(intent, 0) == null ||
                intent.resolveActivity(AppUtils.getApp().getPackageManager()) == null ||
                AppUtils.getApp().getPackageManager().queryIntentActivities(intent, 0).size() == 0);
    }


    /**
     * 销毁指定页面
     *
     * @param classes 销毁指定页面
     */
    public static void removerActivity(Class... classes) {
        Iterator<Activity> sListIterator = AppUtils.sActivityList.iterator();
        while (sListIterator.hasNext()) {
            Activity act = sListIterator.next();
            boolean isRemove = false;
            Class[] aClass = classes;
            for (int j = 0, sizeClass = aClass.length; j < sizeClass; j++) {
                if (act.getClass() == aClass[j]) {
                    isRemove = true;
                    break;
                }
            }
            if (isRemove) {
                act.finish();
                sListIterator.remove();
            }
        }
    }

    /**
     * 保留传入的activity 移除其它所有的
     *
     * @param classes
     */
    public static void keepForRemoveActivity(Class... classes) {
        Iterator<Activity> sListIterator = AppUtils.sActivityList.iterator();
        while (sListIterator.hasNext()) {
            Activity act = sListIterator.next();
            boolean isRemove = false;
            Class[] aClass = classes;
            for (int j = 0, sizeClass = aClass.length; j < sizeClass; j++) {
                if (act.getClass() != aClass[j]) {
                    isRemove = true;
                    break;
                }
            }
            if (isRemove) {
                act.finish();
                sListIterator.remove();
            }
        }
    }

    /**
     * 结束所有activity
     */
    public static void finishAllActivities() {
        List<Activity> activityList = AppUtils.sActivityList;
        for (int i = activityList.size() - 1; i >= 0; --i) {
            activityList.get(i).finish();
            activityList.remove(i);
        }
    }
}
