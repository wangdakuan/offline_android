package cm.offline.tv.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: FragmentUtils
 * Author: wangdakuan
 * Date: 2020-03-07 15:23
 * Description: ButterKnife生命周期
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ButterKnifeUtil {
    private static final String TAG = "ButterKnifeUtil";

    public static void bind(@NonNull Object target, @NonNull View source) {
        LifeCycleHolder.handle(ActivityUtils.getActivity(source.getContext()), ButterKnife.bind(target, source), new LifeCycleHolder.Callback<Unbinder>() {
            @Override
            public void onDestroy(@Nullable Unbinder obj) {
                try {
                    if (obj != null) {
                        obj.unbind();
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    public static void bind(@NonNull View source) {
        LifeCycleHolder.handle(ActivityUtils.getActivity(source.getContext()), ButterKnife.bind(source), new LifeCycleHolder.Callback<Unbinder>() {
            @Override
            public void onDestroy(@Nullable Unbinder obj) {
                try {
                    if (obj != null) {
                        obj.unbind();
                    }
                } catch (Exception e) {
                }
            }
        });
    }
}
