package cm.offline.tv.ui.right;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.offline.tv.R;
import cm.offline.tv.event.MessageEvent;
import cm.offline.tv.ui.adapter.SlidingPageAdapter;
import cm.offline.tv.ui.view.indicator.MyCircleIndicator;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: RightAdvertisingFragment
 * Author: wangdakuan
 * Date: 2020-03-07 15:13
 * Description: 右边广告显示页面
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class RightAdvertisingFragment extends Fragment {

    @BindView(R.id.banner)
    Banner mBanner;

    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_advertising, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> strings = new ArrayList<>();
        strings.add("1111");
        strings.add("1111");
        mBanner.setAdapter(new SlidingPageAdapter(strings));
        //滑动反向
        mBanner.setOrientation(Banner.VERTICAL);
        //指示器位置
        mBanner.setIndicator(new MyCircleIndicator(getActivity()));
        mBanner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
        mBanner.setIndicatorSelectedColor(getResources().getColor(R.color.indicator_colors));
        //是否允许手动滑动
        mBanner.setUserInputEnabled(true);
        //自动滑动的间隔时间
        mBanner.setDelayTime(3000);
        mBanner.start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                EventBus.getDefault().post(new MessageEvent(MessageEvent.START_CUSTOM_PAGE));
            }

            @Override
            public void onBannerChanged(int position) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(null != unbinder){
            unbinder.unbind();
        }
        if(null != mBanner){
            mBanner.stop();
        }
    }
}
