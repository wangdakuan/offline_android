package cm.offline.tv.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: AppUtils
 * Author: wangdakuan
 * Date: 2020-03-06 15:42
 * Description: 初始化
 * History:
 * version：
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class AppUtils {
    public interface ActivitListening{
        void onStart();
        void onStop();
        void onPaused();
        void onResumed();
        void onDestroy();
    }
    private static ActivitListening mActivitListening;
    /**
     * app Application
     */
    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;
    /**
     * activity 管理
     */
    static List<Activity> sActivityList = new LinkedList<>();
    /**
     * 当前页面显示的activity（ 栈顶的activity）
     */
    @SuppressLint("StaticFieldLeak")
    static Activity sTopActivity;

    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            sTopActivity = activity;
            sActivityList.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            sTopActivity = activity;
        }

        @Override
        public void onActivityResumed(Activity activity) {
            sTopActivity = activity;
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            sActivityList.remove(activity);
        }
    };

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param app 应用
     */
    public static void init(@NonNull final Application app) {
        AppUtils.sApplication = app;
        app.registerActivityLifecycleCallbacks(mCallbacks);
    }

    /**
     * 获取Application
     * 在不跟activity页面相关的context可以直接使用，方便于在app使用context
     *
     * @return Application
     */
    public static Application getApp() {
        if (sApplication != null) return sApplication;
        throw new NullPointerException("u should init first");
    }

    /**
     * 获取Application
     * 在不跟activity页面相关的context可以直接使用，方便于在app使用context
     *
     * @return Application
     */
    public static void setActivitListening(ActivitListening activitListening) {
        mActivitListening = activitListening;
    }
}
