package cm.offline.tv.ui.right;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cm.offline.tv.R;
import cm.offline.tv.event.MessageEvent;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: PayStatusFragment
 * Author: wangdakuan
 * Date: 2020-03-13 19:09
 * Description: 支付状态页面
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class PayStatusFragment extends Fragment {

    @BindView(R.id.tv_state_view)
    TextView mTvStateView;
    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.tv_continue_buy)
    TextView mTvContinueBuy;
    @BindView(R.id.tv_service_no_code)
    TextView mTvServiceNoCode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_status, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnifeUtil.bind(this, view);
    }

    @OnClick(R.id.tv_continue_buy)
    public void onViewClicked() {
        EventBus.getDefault().post(new MessageEvent(MessageEvent.START_CUSTOM_PAGE));
    }
}
