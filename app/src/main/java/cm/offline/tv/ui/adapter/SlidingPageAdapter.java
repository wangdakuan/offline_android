package cm.offline.tv.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cm.offline.tv.R;


/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: SlidingPageAdapter
 * Author: wangdakuan
 * Date: 2020-03-07 14:13
 * Description: 广告栏滑动页面
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class SlidingPageAdapter extends BannerAdapter<String, SlidingPageAdapter.SlidingHolder> {

    public SlidingPageAdapter(List<String> mDatas) {
        super(mDatas);
    }

    @Override
    public SlidingHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new SlidingHolder(BannerUtils.getView(parent, R.layout.item_sliding_page));
    }

    @Override
    public void onBindView(SlidingHolder holder, String data, int position, int size) {
    }

    class SlidingHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_page)
        ImageView mImagePage;

        public SlidingHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
