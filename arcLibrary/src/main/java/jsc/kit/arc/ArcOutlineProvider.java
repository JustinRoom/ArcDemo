package jsc.kit.arc;

import android.graphics.Outline;
import android.graphics.Path;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * <br>Email:1006368252@qq.com
 * <br>QQ:1006368252
 * <br><a href="https://github.com/JustinRoom/ArcDemo" target="_blank">https://github.com/JustinRoom/ArcDemo</a>
 *
 * @author jiangshicheng
 */
public class ArcOutlineProvider extends ViewOutlineProvider {

    private float arcHeight;
    private int arcDirection;

    public ArcOutlineProvider(float arcHeight, @ArcDirection int arcDirection) {
        this.arcHeight = arcHeight;
        this.arcDirection = arcDirection;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        Path path = ArcUtils.createArcPath(null, view.getWidth(), view.getHeight(), arcHeight, arcDirection);
        outline.setConvexPath(path);
    }
}
