package cm.offline.tv.ui.right.operation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cm.offline.tv.R;
import cm.offline.tv.event.MessageEvent;
import cm.offline.tv.ui.adapter.QueryReplenishmentAdapter;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: QueryReplenishmentFragment
 * Author: wangdakuan
 * Date: 2020-03-14 13:48
 * Description: 运营查询补货页面
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class QueryReplenishmentFragment extends Fragment {

    @BindView(R.id.list_viewpager)
    RecyclerView mListViewpager;
    @BindView(R.id.btn_next)
    TextView mBtnNext;

    private QueryReplenishmentAdapter mQueryReplenishmentAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query_replenishment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnifeUtil.bind(this, view);
        mQueryReplenishmentAdapter = new QueryReplenishmentAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mListViewpager.setLayoutManager(linearLayoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mListViewpager);
        mListViewpager.setAdapter(mQueryReplenishmentAdapter);
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        if(mBtnNext.getText().toString().contains("下一步")){
            mBtnNext.setText("完成");
            mListViewpager.smoothScrollToPosition(1);
        } else {
            EventBus.getDefault().post(new MessageEvent(MessageEvent.START_OPERATION_BTN_PAGE));
        }

    }
}
