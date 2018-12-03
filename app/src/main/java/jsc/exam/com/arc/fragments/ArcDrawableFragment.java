package jsc.exam.com.arc.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import jsc.exam.com.arc.R;
import jsc.kit.arc.ArcConstant;
import jsc.kit.arc.ArcDrawable;
import jsc.kit.arc.ArcUtils;

public class ArcDrawableFragment extends BaseFragment {

    private static final String TAG = "ArcDrawableFragment";
    ImageView ivTarget;
    SeekBar seekBar;
    ArcDrawable arcDrawable = null;
    int[] radioIds = {
            R.id.radio_left_outside,
            R.id.radio_left_inside,
            R.id.radio_top_outside,
            R.id.radio_top_inside,
            R.id.radio_right_outside,
            R.id.radio_right_inside,
            R.id.radio_bottom_outside,
            R.id.radio_bottom_inside,
            R.id.radio_left_right_outside,
            R.id.radio_left_right_inside,
            R.id.radio_top_bottom_outside,
            R.id.radio_top_bottom_inside
    };
    RadioButton[] radios = new RadioButton[radioIds.length];

    private CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!isChecked)
                return;
            updateCheckUI(buttonView.getId());
            switch (buttonView.getId()) {
                case R.id.radio_left_outside:
                    arcDrawable.setArcDirection(ArcConstant.LEFT_OUTSIDE);
                    break;
                case R.id.radio_left_inside:
                    arcDrawable.setArcDirection(ArcConstant.LEFT_INSIDE);
                    break;
                case R.id.radio_top_outside:
                    arcDrawable.setArcDirection(ArcConstant.TOP_OUTSIDE);
                    break;
                case R.id.radio_top_inside:
                    arcDrawable.setArcDirection(ArcConstant.TOP_INSIDE);
                    break;
                case R.id.radio_right_outside:
                    arcDrawable.setArcDirection(ArcConstant.RIGHT_OUTSIDE);
                    break;
                case R.id.radio_right_inside:
                    arcDrawable.setArcDirection(ArcConstant.RIGHT_INSIDE);
                    break;
                case R.id.radio_bottom_outside:
                    arcDrawable.setArcDirection(ArcConstant.BOTTOM_OUTSIDE);
                    break;
                case R.id.radio_bottom_inside:
                    arcDrawable.setArcDirection(ArcConstant.BOTTOM_INSIDE);
                    break;
                case R.id.radio_left_right_outside:
                    arcDrawable.setArcDirection(ArcConstant.LEFT_RIGHT_OUTSIDE);
                    break;
                case R.id.radio_left_right_inside:
                    arcDrawable.setArcDirection(ArcConstant.LEFT_RIGHT_INSIDE);
                    break;
                case R.id.radio_top_bottom_outside:
                    arcDrawable.setArcDirection(ArcConstant.TOP_BOTTOM_OUTSIDE);
                    break;
                case R.id.radio_top_bottom_inside:
                    arcDrawable.setArcDirection(ArcConstant.TOP_BOTTOM_INSIDE);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_arc_drawable, container, false);
        ivTarget = root.findViewById(R.id.iv_target);
        for (int i = 0; i < radioIds.length; i++) {
            radios[i] = root.findViewById(radioIds[i]);
            radios[i].setOnCheckedChangeListener(listener);
        }

        seekBar = root.findViewById(R.id.seek_bar);
        seekBar.setMax(getResources().getDimensionPixelSize(R.dimen.space_64));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                arcDrawable.setArcHeight(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return root;
    }

    private void updateCheckUI(int checkedId){
        for (int i = 0; i < radios.length; i++) {
            if (radios[i].getId() == checkedId)
                continue;
            radios[i].setChecked(false);
        }
    }

    @Override
    void onLoadData(Context context) {
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDimensionPixelSize(R.dimen.space_192);
        Bitmap bitmap = ArcUtils.decodeAssets(context, "img/2.jpg");
        bitmap = ArcUtils.crop(bitmap, width, height);
        arcDrawable = new ArcDrawable(
                bitmap,
                0,
                ArcConstant.BOTTOM_OUTSIDE
        );
        ivTarget.setImageDrawable(arcDrawable);
        seekBar.setProgress(getResources().getDimensionPixelSize(R.dimen.space_24));
    }
}
