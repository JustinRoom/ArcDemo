package jsc.kit.arc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * <br>Email:1006368252@qq.com
 * <br>QQ:1006368252
 * <br><a href="https://github.com/JustinRoom/ArcDemo" target="_blank">https://github.com/JustinRoom/ArcDemo</a>
 *
 * @author jiangshicheng
 */
public class ArcDrawable extends Drawable implements IArcSetting {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean needRecalculatePath = true;
    private Path path = new Path();
    private float arcHeight;
    private int arcDirection;
    private int intrinsicWidth;
    private int intrinsicHeight;
    private Bitmap src = null;

    public ArcDrawable(float arcHeight, @ArcDirection int arcDirection) {
        this(null, arcHeight, arcDirection);
    }

    public ArcDrawable(@NonNull Context context, String assets, float arcHeight, @ArcDirection int arcDirection) {
        this(ArcUtils.decodeAssets(context, assets), arcHeight, arcDirection);
    }

    public ArcDrawable(@NonNull Context context, @DrawableRes int resId, float arcHeight, @ArcDirection int arcDirection) {
        this(ArcUtils.decodeDrawable(context, resId), arcHeight, arcDirection);
    }

    public ArcDrawable(Bitmap src, float arcHeight, @ArcDirection int arcDirection) {
        this.arcHeight = arcHeight;
        this.arcDirection = arcDirection;
        this.src = src;
        setBitmap(src, false);
    }

    private void setBitmap(Bitmap bitmap, boolean invalidate) {
        paint.setShader(bitmap == null ? null : new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        intrinsicWidth = bitmap == null ? ViewGroup.LayoutParams.WRAP_CONTENT : bitmap.getWidth();
        intrinsicHeight = bitmap == null ? ViewGroup.LayoutParams.WRAP_CONTENT : bitmap.getHeight();
        if (invalidate)
            invalidateSelf();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int w = getIntrinsicWidth();
        int h = getIntrinsicHeight();
        if (w <= 0 || h <= 0 || paint.getShader() == null) {
            return;
        }
        if (needRecalculatePath) {
            needRecalculatePath = false;
            ArcUtils.createArcPath(path, w, h, arcHeight, arcDirection);
        }
        canvas.drawPath(path, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
        invalidateSelf();
    }

    @Override
    public int getIntrinsicWidth() {
        return intrinsicWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return intrinsicHeight;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public float getArcHeight() {
        return arcHeight;
    }

    public void setArcHeight(int arcHeight) {
        this.arcHeight = arcHeight;
        needRecalculatePath = true;
        invalidateSelf();
    }

    @Override
    public int getArcDirection() {
        return arcDirection;
    }

    public void setArcDirection(int arcDirection) {
        this.arcDirection = arcDirection;
        needRecalculatePath = true;
        invalidateSelf();
    }

    @Override
    public Bitmap getSrc() {
        return src;
    }

    @Override
    public void setSrc(Bitmap src) {
        this.src = src;
        if (getIntrinsicWidth() > 0 && getIntrinsicHeight() > 0)
            applyToTargetSize(getIntrinsicWidth(), getIntrinsicHeight());
        else
            setBitmap(src, true);
    }

    public void setShader(Shader shader) {
        paint.setShader(shader);
        invalidateSelf();
    }

    public void applyToTargetSize(int targetWidth, int targetHeight) {
        if (src != null && isDifferentSize(targetWidth, targetHeight)) {
            setBitmap(ArcUtils.crop(src, targetWidth, targetHeight), true);
        }
    }

    @Override
    public void reset() {
        setBitmap(src, true);
    }

    private boolean isDifferentSize(int targetWidth, int targetHeight) {
        return (getIntrinsicWidth() != targetWidth) || (getIntrinsicHeight() != targetHeight);
    }
}