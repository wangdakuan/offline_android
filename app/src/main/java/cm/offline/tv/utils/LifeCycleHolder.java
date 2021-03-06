package cm.offline.tv.utils;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;



/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: FragmentUtils
 * Author: wangdakuan
 * Date: 2020-03-07 15:23
 * Description: 生命周期监听
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class LifeCycleHolder<T> implements LifecycleObserver {

    protected T obj;
    private Callback<T> callbak;

    public static <T> LifeCycleHolder<T> empty() {
        return new LifeCycleHolder<>(null, null);
    }

    public static <T> LifeCycleHolder handle(Object who, T obj, Callback<T> callback) {
        if (who instanceof LifecycleOwner) {
            LifeCycleHolder<T> result = new LifeCycleHolder<T>(obj, callback);
            ((LifecycleOwner) who).getLifecycle().addObserver(result);
            return result;
        }
        return empty();
    }

    private LifeCycleHolder(T obj, Callback<T> callback) {
        this.obj = obj;
        this.callbak = callback;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        if (callbak != null) {
            callbak.onCreate(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        if (callbak != null) {
            callbak.onStart(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if (callbak != null) {
            callbak.onResume(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        if (callbak != null) {
            callbak.onPause(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        if (callbak != null) {
            callbak.onStop(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        if (callbak != null) {
            callbak.onDestroy(obj);
        }
        obj = null;
        callbak = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny() {
        if (callbak != null) {
            callbak.onAny(obj);
        }
    }


    public static abstract class Callback<T> {
        public void onCreate(@Nullable T obj) {
        }

        public void onStart(@Nullable T obj) {
        }

        public void onResume(@Nullable T obj) {
        }

        public void onPause(@Nullable T obj) {
        }

        public void onStop(@Nullable T obj) {
        }

        public void onDestroy(@Nullable T obj) {
        }

        public void onAny(@Nullable T obj) {
        }
    }
}
