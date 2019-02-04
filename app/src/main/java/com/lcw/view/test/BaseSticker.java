package com.lcw.view.test;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * 贴纸的基类（存放贴纸的基本的属性）
 * Create by: chenWei.li
 * Date: 2019/2/4
 * Time: 12:55 AM
 * Email: lichenwei.me@foxmail.com
 */
public abstract class BaseSticker implements ISupportOperation {

    protected Bitmap bitmap;//贴纸图片
    protected Matrix matrix;//维护图像变化的矩阵
    private int mode;

    protected float[] mScrPoints;//矩阵变换前的点坐标
    protected float[] mDstPoints;//矩阵变换后的点坐标
    protected RectF mBitmapBound;//图片范围的点坐标
    protected PointF mMidPointF = new PointF();


    public static final int MODE_SINGLE = 1;//标志是否可移动
    public static final int MODE_MULTIPLE = 2;//标志是否可缩放，旋转

    public BaseSticker(Bitmap bitmap) {
        this.bitmap = bitmap;
        matrix = new Matrix();

        mScrPoints = new float[]{
                0, 0,//左上
                bitmap.getWidth(), 0,//右上
                bitmap.getWidth(), bitmap.getHeight(),//右下
                0, bitmap.getHeight(),//左下
                bitmap.getWidth() / 2, bitmap.getHeight() / 2//中间点
        };
        mDstPoints = mScrPoints.clone();
        mBitmapBound = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());

    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public RectF getBitmapBound() {
        return mBitmapBound;
    }

    /**
     * 获取图像中心点
     *
     * @return
     */
    public PointF getMidPoint() {
        mMidPointF.set(mDstPoints[8], mDstPoints[9]);
        return mMidPointF;
    }

}