package cm.offline.tv.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;
import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: DiyAddImageView
 * Author: wangdakuan
 * Date: 2020-03-09 19:49
 * Description: 添加的素材控件
 * History:
 * version：
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class DiyAddImageView extends FrameLayout {

    @BindView(R.id.iv_material)
    ImageView mIvMaterial;
    @BindView(R.id.btn_diy_delete)
    ImageView mBtnDiyDelete;
    @BindView(R.id.btn_diy_rotate)
    ImageView mBtnDiyRotate;
    @BindView(R.id.btn_diy_zoom)
    ImageView mBtnDiyZoom;
    @BindView(R.id.fl_view)
    FrameLayout mFlView;

    private float centerX, centerX0;
    private float centerY, centerY0;
    private int maxX;
    private int maxY;

    private diyEditCallbackListener mDiyEditCallbackListener;

    public DiyAddImageView(Context context) {
        super(context);
        initView();
    }

    public DiyAddImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DiyAddImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DiyAddImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    public void setMaxXY(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    private void initView() {
        final View view = View.inflate(getContext(), R.layout.item_add_img_view, this);
        ButterKnifeUtil.bind(this, view);
        setBackgroundColor(getResources().getColor(R.color.fastlane_background));
        mIvMaterial.setImageResource(R.mipmap.shaosiming);
        mFlView.setTag(mIvMaterial);
        ViewTreeObserver vto = this.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                          @Override
                                          public void onGlobalLayout() {
                                              //监听一次马上结束
                                              if (Build.VERSION.SDK_INT < 16) {
                                                  mFlView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                              } else {
                                                  mFlView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                              }
                                              int[] location = new int[2];
                                              mFlView.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
                                              centerX0 = location[0] + mFlView.getWidth() / 2;
                                              centerY0 = location[1] + mFlView.getHeight() / 2;
                                              centerX = centerX0;
                                              centerY = centerY0;
                                          }
                                      }

        );

        //平移
        mFlView.setOnTouchListener(new OnTouchListener() {

            private boolean isMove;
            private int downY;
            private int downX;

            private ImageView iv;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                FrameLayout flview = (FrameLayout) view;
                iv = (ImageView) view.getTag();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    downX = (int) event.getRawX();
                    downY = (int) event.getRawY();
                    isMove = false;
                    if (null != mDiyEditCallbackListener) {
                        mDiyEditCallbackListener.onTouchMobileCallback(event);
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    isMove = true;
                    int moveX = (int) event.getRawX();
                    int moveY = (int) event.getRawY();
                    int dx = moveX - downX;
                    int dy = moveY - downY;
                    int i1 = dx + (int) ViewHelper.getTranslationX(flview);
                    int i2 = dy + (int) ViewHelper.getTranslationY(flview);
                    ViewHelper.setTranslationX(flview, i1);
                    ViewHelper.setTranslationY(flview, i2);
                    downX = moveX;
                    downY = moveY;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    centerX = centerX0 + view.getTranslationX();
                    centerY = centerY0 + view.getTranslationY();
                    if (null != mDiyEditCallbackListener) {
                        mDiyEditCallbackListener.onTouchMobileCallback(event);
                    }

                    if (CheckIsOut(iv, flview)) {
                        ViewGroup parent = (ViewGroup) flview.getParent();
                        parent.removeView(flview);
                        Toast.makeText(getContext(), "超出范围已移除", Toast.LENGTH_SHORT).show();
                    }
                }

                return true;
            }
        });

        //删除
        mBtnDiyDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mDiyEditCallbackListener) {
                    mDiyEditCallbackListener.onDeleteCallback(mFlView);

                }
            }
        });

        //旋转
        mBtnDiyRotate.setOnTouchListener(new OnTouchListener() {

            private float mDownY;
            private float mDownX;

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        mDownX = event.getRawX();
                        mDownY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveX = event.getRawX();
                        float moveY = event.getRawY();
                        float rotate = mFlView.getRotation();
                        float angle = getDegress(moveX, moveY) - getDegress(mDownX, mDownY);
                        mFlView.setRotation((rotate + angle) % 360);

                        mDownX = moveX;
                        mDownY = moveY;
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return true;

            }

            private float getDegress(float newX, float newY) {
                float x = newX - centerX;
                float y = newY - centerY;
                return (float) Math.toDegrees(Math.atan2(y, x));
            }
        });

        //缩放
        mBtnDiyZoom.setOnTouchListener(new OnTouchListener() {


            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveX = event.getRawX();
                        float moveY = event.getRawY();

                        float bili = getBili(moveX, moveY);
                        mFlView.setScaleX(bili);
                        mFlView.setScaleY(bili);
                        correct4ButtonSize(1 / bili);
                        break;
                    case MotionEvent.ACTION_UP:
                        centerX = centerX0 + mFlView.getTranslationX();
                        centerY = centerY0 + mFlView.getTranslationY();
                        break;
                }
                return true;

            }

            /**
             *
             * @param bili
             */
            private void correct4ButtonSize(float bili) {

                mBtnDiyDelete.setScaleX(bili);
                mBtnDiyDelete.setScaleY(bili);

                mBtnDiyRotate.setScaleX(bili);
                mBtnDiyRotate.setScaleY(bili);

                mBtnDiyZoom.setScaleX(bili);
                mBtnDiyZoom.setScaleY(bili);
            }

            private float getBili(float moveX, float moveY) {

                int[] location = new int[2];
                mFlView.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
                float radius = mBtnDiyZoom.getWidth();//不精确

                float oldLine = (float) Math.sqrt(mFlView.getWidth() * mFlView.getWidth() + mFlView.getHeight() * mFlView.getHeight());
                float newLine = (float) Math.sqrt((moveX - location[0] + radius) * (moveX - location[0] + radius) + (moveY - location[1] + radius) * (moveY - location[1] + radius));

                float scale = newLine / oldLine;//此处有微量误差
                return scale;
            }
        });

    }

    /**
     * 判断是否超出
     *
     * @param iv
     * @param fl
     * @return
     */
    private boolean CheckIsOut(ImageView iv, FrameLayout fl) {
        int x1 = (int) (fl.getX() + iv.getLeft());
        int x2 = (int) (fl.getX() + fl.getWidth() - iv.getLeft());
        int y1 = (int) (fl.getY() + iv.getTop());
        int y2 = (int) (fl.getY() + fl.getHeight() - iv.getTop());
        if (x1 > maxX || x2 < 0 || y1 > maxY || y2 < 0) {
            return true;
        }
        return false;
    }


    public static Bitmap flip(Bitmap bitmap, boolean isVer) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (isVer)
            matrix.postScale(1, -1);//镜像垂直翻转
        else
            matrix.postScale(-1, 1); //镜像水平翻转
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        bitmap.recycle();
        return newBitmap;
    }

    public interface diyEditCallbackListener {
        void onDeleteCallback(View view);

        void onTouchMobileCallback(MotionEvent event);
    }

    public void setDiyEditCallbackListener(diyEditCallbackListener diyEditCallbackListener) {
        mDiyEditCallbackListener = diyEditCallbackListener;
    }
}
