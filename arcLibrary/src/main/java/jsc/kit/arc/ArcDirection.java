package jsc.kit.arc;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef(value = {
        ArcConstant.LEFT_OUTSIDE,
        ArcConstant.LEFT_INSIDE,
        ArcConstant.TOP_OUTSIDE,
        ArcConstant.TOP_INSIDE,
        ArcConstant.RIGHT_OUTSIDE,
        ArcConstant.RIGHT_INSIDE,
        ArcConstant.BOTTOM_OUTSIDE,
        ArcConstant.BOTTOM_INSIDE,
        ArcConstant.LEFT_RIGHT_OUTSIDE,
        ArcConstant.LEFT_RIGHT_INSIDE,
        ArcConstant.TOP_BOTTOM_OUTSIDE,
        ArcConstant.TOP_BOTTOM_INSIDE
})
@Retention(RetentionPolicy.SOURCE)
public @interface ArcDirection {
}