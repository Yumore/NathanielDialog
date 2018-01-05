package xyz.zimuju.library;

import android.view.animation.Interpolator;

/**
 * @author Nathaniel
 * @version 1.0.0
 * @description HesitateInterpolator
 * @email nathanwriting@126.com
 * @time 2017/4/16 0016 - 14:06
 */
public class HesitateInterpolator implements Interpolator {

    private static double POW = 1.0 / 2.0;

    @Override
    public float getInterpolation(float input) {
        return input < 0.5
                ? (float) Math.pow(input * 2, POW) * 0.5f
                : (float) Math.pow((1 - input) * 2, POW) * -0.5f + 1;
    }
}