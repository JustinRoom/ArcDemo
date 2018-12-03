package jsc.kit.arc;

import android.graphics.Outline;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * <br>Email:1006368252@qq.com
 * <br>QQ:1006368252
 * <br><a href="https://github.com/JustinRoom/ArcDemo" target="_blank">https://github.com/JustinRoom/ArcDemo</a>
 *
 * @author jiangshicheng
 */
public class CircleOutlineProvider extends ViewOutlineProvider {

    @Override
    public void getOutline(View view, Outline outline) {
        int w = view.getWidth();
        int h = view.getHeight();
        int min = Math.min(w, h);
        int left = (w - min) / 2;
        int top = (h - min) / 2;
        outline.setOval(new Rect(left, top, left + min, top + min));
    }
}
