package cm.offline.tv.ui.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import butterknife.BindView;
import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: SizeReplenishmentView
 * Author: wangdakuan
 * Date: 2020-03-14 14:20
 * Description: 尺码补货View
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class SizeReplenishmentView extends LinearLayout {
    public SizeReplenishmentView(Context context) {
        super(context);
    }

    public SizeReplenishmentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SizeReplenishmentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SizeReplenishmentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setSizeReplenishmentData() {

        for (int i = 0; i < 3; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_size_replenishment_view, null, false);
            ViewHolder viewHolder = new ViewHolder(view);
            addView(view);
        }
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_size)
        TextView mTvSize;
        @BindView(R.id.btn_reduction)
        ImageView mBtnReduction;
        @BindView(R.id.tv_number)
        TextView mTvNumber;
        @BindView(R.id.btn_add)
        ImageView mBtnAdd;

        ViewHolder(View view) {
            ButterKnifeUtil.bind(this, view);
        }
    }
}
