package jsc.kit.arc;

import android.graphics.Bitmap;

/**
 * <br>Email:1006368252@qq.com
 * <br>QQ:1006368252
 * <br><a href="https://github.com/JustinRoom/ArcDemo" target="_blank">https://github.com/JustinRoom/ArcDemo</a>
 *
 * @author jiangshicheng
 */
public interface IArcSetting {

    float getArcHeight();

    void setArcHeight(int arcHeight);

    int getArcDirection();

    void setArcDirection(int arcDirection);

    void setSrc(Bitmap bitmap);

    Bitmap getSrc();

    void applyToTargetSize(int targetWidth, int targetHeight);

    void reset();
}
