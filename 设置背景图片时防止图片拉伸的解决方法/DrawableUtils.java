package szrjk.com.schoolhealth.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 设置Layout的background时让图片自适应屏幕的大小，包含屏幕旋转时的调整。
 * Created by wkz on 2016/5/18.
 */
public class DrawableUtils {

    private static final String TAG = "DrawableUtils";

    public static Drawable createImage(Context context, String pathName) {
        // 取得当前屏幕的长宽
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        // 图片缩小放大
        Bitmap bitmap = decodeSampledBitmapFromFile(context, pathName);
        if (readPictureDegree(pathName) != 0) {
            bitmap = rotaingImageBitmap(readPictureDegree(pathName), bitmap);
        }
        return getClipDrawable(bitmap, screenWidth, screenHeight);
    }

    /**
     * 从文件中加载图片并压缩成指定大小
     * 先通过BitmapFactory.decodeStream方法，创建出一个bitmap，
     * 再调用上述方法将其设为ImageView的 source。decodeStream最大的秘密在
     * 于其直接调用JNI>>nativeDecodeAsset()来完成decode，无需再使用java层的createBitmap，
     * 从而节省了java层的空间
     *
     * @param pathName
     * @return
     * @throws FileNotFoundException
     */
    public static Bitmap decodeSampledBitmapFromFile(Context context, String pathName) {
        // 取得当前屏幕的长宽
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        // 加载位图
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(
                    new File(pathName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, screenWidth,
                screenHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        // 为位图设置100K的缓存
        options.inTempStorage = new byte[100 * 1024];
        // 设置位图颜色显示优化方式
        // ALPHA_8：每个像素占用1byte内存（8位）
        // ARGB_4444:每个像素占用2byte内存（16位）
        // ARGB_8888:每个像素占用4byte内存（32位）
        // RGB_565:每个像素占用2byte内存（16位）
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        // 设置图片可以被回收，创建Bitmap用于存储Pixel的内存空间在系统内存不足时可以被回收
        options.inPurgeable = true;
        // 设置解码位图的尺寸信息
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(bis, null, options);
    }

    /**
     * 根据传入的宽和高，计算出合适的inSampleSize值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final float heightRatio = (float) height / (float) reqHeight;
            final float widthRatio = (float) width / (float) reqWidth;
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = (int) Math.ceil(heightRatio < widthRatio ? heightRatio : widthRatio);
        }
        return inSampleSize;
    }

    /**
     * 读取图片旋转角度
     * 三星手机会旋转图片
     *
     * @param imagePath
     * @return
     */
    public static int readPictureDegree(String imagePath) {
        int imageDegree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(imagePath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    imageDegree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    imageDegree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    imageDegree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageDegree;
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param mBitmap
     * @return
     */
    public static Bitmap rotaingImageBitmap(int angle, Bitmap mBitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap b = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
            mBitmap = null;
        }
        return b;
    }

    /**
     * 返回按比例缩减后的Bitmap
     *
     * @param bmp
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getZoomBitmap(Bitmap bmp, int width, int height) {
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        //等比例自动缩放图片适应控件
        float f1 = (float) bmpWidth / width;
        float f2 = (float) bmpHeight / height;
        float scale = 1f;
        if (f1 > 1 || f2 > 1) {
            //放大
            scale = f1 < f2 ? f2 : f1;
        } else if (f1 < 1 || f2 < 1) {
            //缩小
            scale = f1 < f2 ? f1 : f2;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
    }

    /**
     * 返回按比例缩减后的Drawable
     *
     * @param bmp
     * @param width
     * @param height
     * @return
     */
    public static Drawable getZoomDrawable(Bitmap bmp, int width, int height) {
        try {
            int bmpWidth = bmp.getWidth();
            int bmpHeight = bmp.getHeight();
            //按比例自动缩放图片适应控件
            float f1 = (float) width / bmpWidth;
            float f2 = (float) height / bmpHeight;
            float scale = 1f;
            if (f1 > 1 || f2 > 1) {
                //放大
                scale = f1 < f2 ? f2 : f1;
            } else if (f1 < 1 || f2 < 1) {
                //缩小
                scale = f1 < f2 ? f1 : f2;
            }
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
            bmp.recycle();
            // 绘制背景图片
            Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas mCanvas = new Canvas(mBitmap);
            Paint bitmapPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
            // 设定背景颜色
            mCanvas.drawColor(0xff000000);
            mCanvas.drawBitmap(resizedBitmap, width / 2 - bmpWidth * scale / 2, height / 2 - bmpHeight * scale / 2, bitmapPaint);
            mCanvas.save();
            BitmapDrawable drawable = new BitmapDrawable(mBitmap);
            resizedBitmap.recycle();
            return drawable;
        } catch (Exception e) {
            Log.i(TAG, e.toString());
            return null;
        }
    }

    /**
     * 返回按屏幕宽高剪裁后的Drawable,scaleType为centerCrop
     *
     * @param bmp
     * @param width
     * @param height
     * @return
     */
    public static Drawable getClipDrawable(Bitmap bmp, int width, int height) {
        try {
            int bmpWidth = bmp.getWidth();
            int bmpHeight = bmp.getHeight();
            //scaleType为centerCrop
            float scale;
            float dx = 0, dy = 0;
            if (bmpWidth * height > width * bmpHeight) {
                scale = (float) height / (float) bmpHeight;
                dx = (width - bmpWidth * scale) * 0.5f;
            } else {
                scale = (float) width / (float) bmpWidth;
                dy = (height - bmpHeight * scale) * 0.5f;
            }
            Matrix matrix = new Matrix();
            matrix.setScale(scale, scale);
            matrix.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));

            Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
            bmp.recycle();
            // 绘制背景图片
            Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas mCanvas = new Canvas(mBitmap);
            Paint bitmapPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
            // 设定背景颜色
            mCanvas.drawColor(0xff000000);
            mCanvas.drawBitmap(resizedBitmap, width / 2 - bmpWidth * scale / 2, height / 2 - bmpHeight * scale / 2, bitmapPaint);
            mCanvas.save();
            BitmapDrawable drawable = new BitmapDrawable(mBitmap);
            resizedBitmap.recycle();
            return drawable;
        } catch (Exception e) {
            Log.i(TAG, e.toString());
            return null;
        }
    }
}
