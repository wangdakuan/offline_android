package cm.offline.tv.ui.right.operation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cm.offline.tv.R;
import cm.offline.tv.event.MessageEvent;
import cm.offline.tv.ui.right.PayStatusFragment;
import cm.offline.tv.ui.right.RightAdvertisingFragment;
import cm.offline.tv.ui.right.RightCustomMadeFragment;
import cm.offline.tv.ui.right.error.FaultErrorFragment;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.utils.FragmentUtils;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: OperationFragment
 * Author: wangdakuan
 * Date: 2020-03-14 13:04
 * Description: 运营页面
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class OperationFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnifeUtil.bind(this, view);
        FragmentUtils.replaceFragment(getFragmentManager(), new OperationBtnFragment(), R.id.fy_content, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPageEvent(MessageEvent event) {
        if (event.mEventKey == MessageEvent.START_OPERATION_QUERY_REPLENISHMENT_PAGE) {
            FragmentUtils.replaceFragment(getFragmentManager(), new QueryReplenishmentFragment(), R.id.fy_content, false);
        } else if (event.mEventKey == MessageEvent.START_OPERATION_DEVICE_CHECKING_PAGE) {
            FragmentUtils.replaceFragment(getFragmentManager(), new DeviceCheckingFragment(), R.id.fy_content, false);
        } else if (event.mEventKey == MessageEvent.START_OPERATION_BTN_PAGE) {
            FragmentUtils.replaceFragment(getFragmentManager(), new OperationBtnFragment(), R.id.fy_content, false);
        }
    }
}
