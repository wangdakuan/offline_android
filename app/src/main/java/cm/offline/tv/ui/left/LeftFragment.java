package cm.offline.tv.ui.left;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cm.offline.tv.R;
import cm.offline.tv.ui.adapter.SlidingPageAdapter;
import cm.offline.tv.utils.SizeUtils;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: LeftFragment
 * Author: wangdakuan
 * Date: 2020-03-05 18:46
 * Description:
 * History:
 * version：
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class LeftFragment extends Fragment {

    @BindView(R.id.banner)
    Banner mBanner;

    Unbinder unbinder;
    @BindView(R.id.btn_pick_goods)
    ImageView mBtnPickGoods;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Integer> strings = new ArrayList<>();
        strings.add(R.mipmap.test_left_banner);
        mBanner.setAdapter(new SlidingPageAdapter(strings));
        //滑动反向
        mBanner.setOrientation(Banner.HORIZONTAL);
        //指示器位置
        mBanner.setIndicator(new CircleIndicator(getActivity()));
        mBanner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
        mBanner.setIndicatorSelectedColor(getResources().getColor(R.color.indicator_colors));
        mBanner.setIndicatorWidth(SizeUtils.dp2px(25), SizeUtils.dp2px(25));
        //是否允许手动滑动
        mBanner.setUserInputEnabled(true);
        //自动滑动的间隔时间
        mBanner.setDelayTime(3000);
        mBanner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != unbinder) {
            unbinder.unbind();
        }
        if (null != mBanner) {
            mBanner.stop();
        }
    }

    @OnClick(R.id.btn_pick_goods)
    public void onViewClicked() { //取货
    }
}
