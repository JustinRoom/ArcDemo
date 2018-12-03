package jsc.kit.arc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
    private String[] arcShaderSplits = null;
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
        String arcShader = a.getString(R.styleable.ArcView_arcShader);
        int drawableId = a.getResourceId(R.styleable.ArcView_arcDrawable, -1);
        a.recycle();
        createArcDrawable(context, arcShader, drawableId);
    }

    private void createArcDrawable(Context context, String arcShader, int drawableId) {
        if (TextUtils.isEmpty(arcShader)) {
            arcShader = "drawable";
        }
        arcShaderSplits = arcShader.split(",");
        switch (arcShaderSplits[0].trim()) {
            case "drawable":
                if (drawableId != -1)
                    arcDrawable = new ArcDrawable(context, drawableId, arcHeight, arcDirection);
                break;
            case "assets":
                if (arcShaderSplits.length > 1) {
                    arcDrawable = new ArcDrawable(context, arcShaderSplits[1].trim(), arcHeight, arcDirection);
                }
                break;
            case "linear":
            case "sweep":
            case "radial":
                if (arcShaderSplits.length < 3)
                    throw new IllegalArgumentException("three parameters at least, like this:linear/sweep/radial, #FF3A80, #FF3745");
                break;
        }
        if (arcDrawable == null)
            arcDrawable = new ArcDrawable(arcHeight, arcDirection);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String shaderType = arcShaderSplits[0].trim();
        if (TextUtils.equals(shaderType, "drawable")
                || TextUtils.equals(shaderType, "assets")) {
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
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        String shaderType = arcShaderSplits[0].trim();
        if (TextUtils.equals(shaderType, "drawable")
                || TextUtils.equals(shaderType, "assets")) {
            arcDrawable.applyToTargetSize(getMeasuredWidth(), getMeasuredHeight());
        } else {
            int startColor = Color.parseColor(arcShaderSplits[1].trim());
            int endColor = Color.parseColor(arcShaderSplits[2].trim());
            Shader shader = null;
            switch (shaderType) {
                case "linear":
                    shader = new LinearGradient(w / 2.0f, 0, h / 2.0f, h, startColor, endColor, Shader.TileMode.MIRROR);
                    break;
                case "sweep":
                    shader = new SweepGradient(w / 2.0f, h / 2.0f, startColor, endColor);
                    break;
                case "radial":
                    shader = new RadialGradient(w / 2.0f, h / 2.0f, Math.max(w, h) / 2.0f, startColor, endColor, Shader.TileMode.MIRROR);
                    break;
            }
            arcDrawable.setShader(shader, w, h);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (arcDrawable.getShader() == null)
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
