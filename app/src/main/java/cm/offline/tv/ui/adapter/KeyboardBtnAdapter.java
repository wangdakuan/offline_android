package cm.offline.tv.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: KeyboardBtnAdapter
 * Author: wangdakuan
 * Date: 2020-03-15 13:15
 * Description: 数字键盘按钮适配器
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class KeyboardBtnAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private Integer keyboard[] = {1, 2, 3, 501, 4, 5, 6, 0, 7, 8, 9, 200};

    private itemClickListener mItemClickListener;

    public KeyboardBtnAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_keyboard_btn, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        int key = keyboard[position];
        if (key == 501) {
            if (holder.mBtnKeyDel.getVisibility() != View.VISIBLE) {
                holder.mBtnKeyDel.setVisibility(View.VISIBLE);
            }
            if (holder.mTvKeyboard.getVisibility() != View.GONE) {
                holder.mTvKeyboard.setVisibility(View.GONE);
            }

        } else if (key == 200) {
            if (holder.mBtnKeyDel.getVisibility() != View.GONE) {
                holder.mBtnKeyDel.setVisibility(View.GONE);
            }
            if (holder.mTvKeyboard.getVisibility() != View.VISIBLE) {
                holder.mTvKeyboard.setVisibility(View.VISIBLE);
            }
            holder.mTvKeyboard.setTextColor(mContext.getResources().getColor(R.color.text_colors_ffffff));
            holder.mTvKeyboard.setText("确认");
            holder.mTvKeyboard.setBackgroundResource(R.drawable.bg_keyboard_blue);
        } else {
            if (holder.mBtnKeyDel.getVisibility() != View.GONE) {
                holder.mBtnKeyDel.setVisibility(View.GONE);
            }
            if (holder.mTvKeyboard.getVisibility() != View.VISIBLE) {
                holder.mTvKeyboard.setVisibility(View.VISIBLE);
            }
            holder.mTvKeyboard.setBackgroundResource(R.drawable.bg_keyboard_white);
            holder.mTvKeyboard.setTextColor(mContext.getResources().getColor(R.color.text_colors_010103));
            holder.mTvKeyboard.setText(key + "");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mItemClickListener) {
                    if (key == 501) {
                        mItemClickListener.onDelNumber();
                    } else if (key == 200) {
                        mItemClickListener.oncConfirm();
                    } else {
                        mItemClickListener.onAddNumber(key);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return keyboard.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_keyboard)
        TextView mTvKeyboard;
        @BindView(R.id.btn_key_del)
        ImageView mBtnKeyDel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }
    }

    public interface itemClickListener {
        void onAddNumber(int number);

        void onDelNumber();

        void oncConfirm();
    }

    public void setItemClickListener(itemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
