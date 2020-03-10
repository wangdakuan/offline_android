package cm.offline.tv.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import cm.offline.tv.R;
import cm.offline.tv.utils.SizeUtils;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: ImagesTemplates
 * Author: wangdakuan
 * Date: 2020-03-06 19:46
 * Description: 自定义diy区域控件
 * History:
 * version：1.0.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ImagesTemplates extends FrameLayout {

    private FrameLayout mFlRoot;
    private ImageView mIvBg;
    private FrameLayout mFlZone;

    private List<DiyAddImageView> mViews = new ArrayList<>();

    public ImagesTemplates(Context context) {
        super(context);
        initView();
    }

    public ImagesTemplates(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ImagesTemplates(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImagesTemplates(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        View.inflate(getContext(), R.layout.view_images_templates, this);
        mFlRoot = (FrameLayout) findViewById(R.id.fl_root);
        mIvBg = (ImageView) findViewById(R.id.iv_bg);
        mFlZone = (FrameLayout) findViewById(R.id.fl_zone);
        initBg(0, 0, SizeUtils.dp2px(848), SizeUtils.dp2px(813));//背景起点 长宽  407
        initEditZone(SizeUtils.dp2px(250), SizeUtils.dp2px(182), SizeUtils.dp2px(350), SizeUtils.dp2px(524));
        addDiyView();
        addDiyView();
    }

    private void addDiyView(){
        DiyAddImageView imageView = new DiyAddImageView(getContext());
        imageView.setMaxXY( SizeUtils.dp2px(848),SizeUtils.dp2px(813));
        imageView.setDiyEditCallbackListener(new DiyAddImageView.diyEditCallbackListener() {
            @Override
            public void onDeleteCallback(View view) {
                mFlZone.removeView(imageView);
                mViews.remove(imageView);
            }
            @Override
            public void onTouchMobileCallback(MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mFlRoot.setClipChildren(false);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mFlRoot.setClipChildren(true);
                    mFlRoot.invalidate();
                }
            }
        });
        mFlZone.addView(imageView);
        mViews.add(imageView);
    }
    /**
     * 背景布局
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void initBg(int x, int y, int width, int height) {
        mIvBg.setX(x);
        mIvBg.setY(y);
        mIvBg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ViewGroup.LayoutParams layoutParams = mIvBg.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        mIvBg.setLayoutParams(layoutParams);
    }

    /**
     * 可编辑区域
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void initEditZone(final int x, final int y, final int width, final int height) {

        ViewTreeObserver vto = mIvBg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //监听一次马上结束

                if (Build.VERSION.SDK_INT < 16) {
                    mIvBg.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    mIvBg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                Bitmap bitmap = Bitmap.createBitmap(mIvBg.getWidth(), mIvBg.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                canvas.drawColor(Color.TRANSPARENT);
                mIvBg.draw(canvas);


                Paint p = new Paint();
                p.setStyle(Paint.Style.STROKE);
                //设置虚线效果
                p.setPathEffect(new DashPathEffect(new float[]{10, 5}, 0));
                p.setStrokeWidth(3);
                p.setColor(Color.parseColor("#000000"));

                Path path = new Path();
                path.moveTo(x, y);
                path.lineTo(x + width, y);
                path.lineTo(x + width, y + height);
                path.lineTo(x, y + height);
                path.close();
                canvas.drawPath(path, p);

                mIvBg.setImageBitmap(bitmap);
                //显示编辑区域范围
                mFlZone.setX(x + mIvBg.getX());
                mFlZone.setY(y + mIvBg.getY());
                FrameLayout.LayoutParams layoutParams = (LayoutParams) mFlZone.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = height;
                mFlZone.setLayoutParams(layoutParams);

            }
        });
    }
}
