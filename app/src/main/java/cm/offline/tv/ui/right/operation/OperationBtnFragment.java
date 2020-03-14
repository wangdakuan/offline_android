package cm.offline.tv.ui.right.operation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
 * FileName: OperationBtnFragment
 * Author: wangdakuan
 * Date: 2020-03-14 13:10
 * Description: 运营操作按钮页面
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class OperationBtnFragment extends Fragment {

    @BindView(R.id.btn_query_replenishment)
    ImageView mBtnQueryReplenishment;
    @BindView(R.id.btn_device_checking)
    ImageView mBtnDeviceChecking;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation_btn, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnifeUtil.bind(this, view);
    }

    @OnClick({R.id.btn_query_replenishment, R.id.btn_device_checking})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_query_replenishment: //查询补货
                EventBus.getDefault().post(new MessageEvent(MessageEvent.START_OPERATION_QUERY_REPLENISHMENT_PAGE));
                break;
            case R.id.btn_device_checking: //设备自检
                EventBus.getDefault().post(new MessageEvent(MessageEvent.START_OPERATION_DEVICE_CHECKING_PAGE));
                break;
        }
    }
}
