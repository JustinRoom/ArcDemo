package jsc.kit.arc;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;

import java.io.IOException;
import java.io.InputStream;

/**
 * <br>Email:1006368252@qq.com
 * <br>QQ:1006368252
 * <br><a href="https://github.com/JustinRoom/ArcDemo" target="_blank">https://github.com/JustinRoom/ArcDemo</a>
 *
 * @author jiangshicheng
 */
public final class ArcUtils {

    @Nullable
    public static Bitmap decodeAssets(@NonNull Context context, String assets) {
        Bitmap bitmap = null;
        try {
            InputStream is = context.getAssets().open(assets);
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @NonNull
    public static Bitmap decodeDrawable(@NonNull Context context, @DrawableRes int drawableId) {
        return BitmapFactory.decodeResource(context.getResources(), drawableId);
    }

    public static Bitmap crop(Bitmap src, int targetWidth, int targetHeight) {
        if (src == null)
            return null;
        if (targetWidth >= src.getWidth()
                && targetHeight >= src.getHeight())
            return src;

        int x = (src.getWidth() - targetWidth) / 2;
        int y = (src.getHeight() - targetHeight) / 2;
        x = Math.max(x, 0);
        y = Math.max(y, 0);
        targetWidth = Math.min(targetWidth, src.getWidth() - x);
        targetHeight = Math.min(targetHeight, src.getHeight() - y);
        return Bitmap.createBitmap(src, x, y, targetWidth, targetHeight);
    }

    /**
     * @param path         src path
     * @param width        width
     * @param height       height
     * @param arcHeight    arc height
     * @param arcDirection arc direction
     * @return return src path if src path exists, else create a path
     */
    public static Path createArcPath(Path path, int width, int height, float arcHeight, @ArcDirection int arcDirection) {
        if (path == null)
            path = new Path();
        path.reset();
        switch (arcDirection) {
            case ArcConstant.LEFT_OUTSIDE:
                path.moveTo(arcHeight, 0);
                path.quadTo(-arcHeight, height / 2.0f, arcHeight, height);
                path.lineTo(width, height);
                path.lineTo(width, 0);
                break;
            case ArcConstant.LEFT_INSIDE:
                path.quadTo(arcHeight * 2, height / 2.0f, 0, height);
                path.lineTo(width, height);
                path.lineTo(width, 0);
                break;
            case ArcConstant.TOP_OUTSIDE:
                path.moveTo(0, arcHeight);
                path.quadTo(width / 2.0f, -arcHeight, width, arcHeight);
                path.lineTo(width, height);
                path.lineTo(0, height);
                break;
            case ArcConstant.TOP_INSIDE:
                path.quadTo(width / 2.0f, arcHeight * 2, width, 0);
                path.lineTo(width, height);
                path.lineTo(0, height);
                break;
            case ArcConstant.RIGHT_OUTSIDE:
                path.lineTo(width - arcHeight, 0);
                path.quadTo(width + arcHeight, height / 2.0f, width - arcHeight, height);
                path.lineTo(0, height);
                break;
            case ArcConstant.RIGHT_INSIDE:
                path.lineTo(width, 0);
                path.quadTo(width - arcHeight * 2, height / 2.0f, width, height);
                path.lineTo(0, height);
                break;
            case ArcConstant.BOTTOM_OUTSIDE:
                path.lineTo(0, height - arcHeight);
                path.quadTo(width / 2.0f, height + arcHeight, width, height - arcHeight);
                path.lineTo(width, 0);
                break;
            case ArcConstant.BOTTOM_INSIDE:
                path.lineTo(0, height);
                path.quadTo(width / 2.0f, height - arcHeight * 2, width, height);
                path.lineTo(width, 0);
                break;
            case ArcConstant.LEFT_RIGHT_OUTSIDE:
                path.moveTo(arcHeight, 0);
                path.quadTo(-arcHeight, height / 2.0f, arcHeight, height);
                path.lineTo(width - arcHeight, height);
                path.quadTo(width + arcHeight, height / 2.0f, width - arcHeight, 0);
                break;
            case ArcConstant.LEFT_RIGHT_INSIDE:
                path.quadTo(arcHeight * 2, height / 2.0f, 0, height);
                path.lineTo(width, height);
                path.quadTo(width - arcHeight * 2, height / 2.0f, width, 0);
                break;
            case ArcConstant.TOP_BOTTOM_OUTSIDE:
                path.moveTo(0, arcHeight);
                path.quadTo(width / 2.0f, -arcHeight, width, arcHeight);
                path.lineTo(width, height - arcHeight);
                path.quadTo(width / 2.0f, height + arcHeight, 0, height - arcHeight);
                break;
            case ArcConstant.TOP_BOTTOM_INSIDE:
                path.quadTo(width / 2.0f, arcHeight * 2, width, 0);
                path.lineTo(width, height);
                path.quadTo(width / 2.0f, height - arcHeight * 2, 0, height);
                break;
        }
        path.close();
        return path;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void applyOutlineProvider(@NonNull View view, float arcHeight, @ArcDirection int arcDirection) {
        applyOutlineProvider(view, new ArcOutlineProvider(arcHeight, arcDirection));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void applyOutlineProvider(@NonNull View view, ViewOutlineProvider provider) {
        view.setClipToOutline(true);
        view.setOutlineProvider(provider);
    }
}
