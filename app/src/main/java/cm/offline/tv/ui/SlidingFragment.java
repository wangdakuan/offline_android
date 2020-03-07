package cm.offline.tv.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cm.offline.tv.R;


/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: SlidingFragment
 * Author: wangdakuan
 * Date: 2020-03-07 14:17
 * Description: 滑动的Fragment页面
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class SlidingFragment extends Fragment {

    private static final String POSITION = "position";

    public static SlidingFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        SlidingFragment fragment = new SlidingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sliding, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
