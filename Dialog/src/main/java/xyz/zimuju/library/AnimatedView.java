package xyz.zimuju.library;

import android.content.Context;
import android.view.View;

/**
 * @author Nathaniel
 * @version 1.0.0
 * @description AnimatedView
 * @email nathanwriting@126.com
 * @time 2017/4/16 0016 - 14:05
 */
public class AnimatedView extends View {

    private int target;

    public AnimatedView(Context context) {
        super(context);
    }

    public float getXFactor() {
        return getX() / target;
    }

    public void setXFactor(float xFactor) {
        setX(target * xFactor);
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
