package jsc.kit.arc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * <br>Email:1006368252@qq.com
 * <br>QQ:1006368252
 * <br><a href="https://github.com/JustinRoom/ArcDemo" target="_blank">https://github.com/JustinRoom/ArcDemo</a>
 *
 * @author jiangshicheng
 */
public class ArcView extends View implements IArcSetting {

    private float arcHeight;
    private int arcDirection;
    private ArcDrawable arcDrawable = null;

    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ArcView, defStyleAttr, 0);
        arcHeight = a.getDimension(R.styleable.ArcView_arcHeight, 0);
        arcDirection = a.getInt(R.styleable.ArcView_arcDirection, ArcConstant.BOTTOM_OUTSIDE);
        int drawableId = a.getResourceId(R.styleable.ArcView_arcDrawable, -1);
        a.recycle();
        if (drawableId != -1)
            arcDrawable = new ArcDrawable(context, drawableId, arcHeight, arcDirection);
        else
            arcDrawable = new ArcDrawable(arcHeight, arcDirection);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ViewGroup.LayoutParams params = getLayoutParams();
        if (params.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            if (arcDrawable.getIntrinsicWidth() > 0) {
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(arcDrawable.getIntrinsicWidth(), MeasureSpec.EXACTLY);
            }
        }
        if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            if (arcDrawable.getIntrinsicHeight() > 0) {
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(arcDrawable.getIntrinsicHeight(), MeasureSpec.EXACTLY);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        arcDrawable.applyToTargetSize(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getSrc() == null)
            return;

        int dx = (getWidth() - arcDrawable.getIntrinsicWidth()) / 2;
        int dy = (getHeight() - arcDrawable.getIntrinsicHeight()) / 2;
        canvas.save();
        canvas.translate(dx, dy);
        arcDrawable.draw(canvas);
        canvas.restore();
    }

    public float getArcHeight() {
        return arcHeight;
    }

    public int getArcDirection() {
        return arcDirection;
    }

    @Override
    public void setArcHeight(int arcHeight) {
        this.arcHeight = arcHeight;
        if (arcDrawable != null)
            arcDrawable.setArcHeight(arcHeight);
        invalidate();
    }

    @Override
    public void setArcDirection(int arcDirection) {
        this.arcDirection = arcDirection;
        if (arcDrawable != null)
            arcDrawable.setArcDirection(arcDirection);
        invalidate();
    }

    @Override
    public Bitmap getSrc() {
        return arcDrawable.getSrc();
    }

    @Override
    public void setSrc(Bitmap src) {
        arcDrawable.setSrc(src);
        requestLayout();
    }

    @Override
    public void applyToTargetSize(int targetWidth, int targetHeight) {
        //do nothing
    }

    @Override
    public void reset() {
        //do nothing
    }
}
