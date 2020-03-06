package cm.offline.tv.ui.left;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.offline_android.R;

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
    private TextView mTvTest;
    private Button mBtn01;
    private int mInt = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTvTest = (TextView) view.findViewById(R.id.tv_test);
        mBtn01 = (Button) view.findViewById(R.id.btn_01);
        mBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInt++;
                mTvTest.setText(mInt+"");
            }
        });
    }
}
