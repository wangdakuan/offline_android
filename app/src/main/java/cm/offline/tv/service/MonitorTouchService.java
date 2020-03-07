package cm.offline.tv.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import cm.offline.tv.event.MessageEvent;
import cm.offline.tv.ui.AdvertisingPage;
import cm.offline.tv.utils.ActivityUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: MonitorTouchService
 * Author: wangdakuan
 * Date: 2020-03-07 16:25
 * Description: 监听屏幕是否有操作
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class MonitorTouchService extends AccessibilityService {
    private String TAG = "AliAccessibilityService";

    public static MonitorTouchService mService;
    private Disposable mDisposable;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        mService = this;
        initDisposable();
    }

    private void initDisposable() {
        mDisposable = Flowable.intervalRange(0, 30, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if(!(ActivityUtils.getTopActivity() instanceof AdvertisingPage)){
                            EventBus.getDefault().post(new MessageEvent(MessageEvent.START_ADVERTISING_PAGE));
                        }
                    }
                })
                .subscribe();

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (null != mDisposable) {
            mDisposable.dispose();
            mDisposable = null;
        }
        initDisposable();
    }

    @Override
    public void onInterrupt() {
        mService = null;
        if (null != mDisposable) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mService = null;
        if (null != mDisposable) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    /**
     * 辅助功能是否启动
     */
    public static boolean isStart() {
        return mService != null;
    }
}
