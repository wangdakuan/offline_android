package cm.offline.tv.ui.right.operation;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import cm.offline.tv.R;
import cm.offline.tv.ui.adapter.DeviceCheckingAdapter;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.utils.SizeUtils;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: DeviceCheckingFragment
 * Author: wangdakuan
 * Date: 2020-03-14 15:21
 * Description: 设备自检
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class DeviceCheckingFragment extends Fragment {

    @BindView(R.id.list_device)
    RecyclerView mListDevice;
    @BindView(R.id.btn_device_checking)
    TextView mBtnDeviceChecking;

    DeviceCheckingAdapter mCheckingAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_checking, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnifeUtil.bind(this, view);

        mCheckingAdapter = new DeviceCheckingAdapter(getActivity());
        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
        manager.setOrientation(RecyclerView.VERTICAL);
        mListDevice.setLayoutManager(manager);
        mListDevice.setAdapter(mCheckingAdapter);
        mListDevice.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                if (childAdapterPosition%3 == 0) {
                    outRect.set(0, SizeUtils.dp2px(38), 0, SizeUtils.dp2px(38));
                } else {
                    outRect.set(SizeUtils.dp2px(30), SizeUtils.dp2px(38), 0, SizeUtils.dp2px(38));
                }
            }
        });

    }

}
