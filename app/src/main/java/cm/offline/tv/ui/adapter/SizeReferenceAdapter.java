package cm.offline.tv.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cm.offline.tv.R;
import cm.offline.tv.model.SizeReferenceModel;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: SizeChartAdapter
 * Author: wangdakuan
 * Date: 2020-03-13 16:07
 * Description: 尺码指南显示适配器
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class SizeReferenceAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<SizeReferenceModel> mReferenceModels;

    public SizeReferenceAdapter(Context mContext, List<SizeReferenceModel> referenceModels) {
        this.mContext = mContext;
        this.mReferenceModels = referenceModels;
        if (mReferenceModels == null) {
            mReferenceModels = new ArrayList<>();
            mReferenceModels.add(new SizeReferenceModel("S码", "身高150-165cm，体重50kg以下"));
            mReferenceModels.add(new SizeReferenceModel("M码", "身高160-170cm，体重50-65k"));
            mReferenceModels.add(new SizeReferenceModel("L码", "身高170-175cm，体重65-75k"));
            mReferenceModels.add(new SizeReferenceModel("XL码", "身高175-180cm，体重75-87k"));
            mReferenceModels.add(new SizeReferenceModel("XXL码", "身高175-185cm，体重87-97k"));
            mReferenceModels.add(new SizeReferenceModel("XXXL码", "身高175-190cm，体重87-110"));
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_size_reference, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (position < mReferenceModels.size()) {
            SizeReferenceModel model = mReferenceModels.get(position);
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.mTvName.setText(model.name);
            holder.mTvDescribe.setText(model.describe);
        }
    }

    @Override
    public int getItemCount() {
        return null == mReferenceModels ? 0 : mReferenceModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_describe)
        TextView mTvDescribe;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }
    }
}
