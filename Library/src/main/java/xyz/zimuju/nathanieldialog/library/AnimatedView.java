package xyz.zimuju.nathanieldialog.library;

import android.content.Context;
import android.view.View;

/*
 * @description AnimatedView
 * @author Nathaniel
 * @email nathanwriting@126.com
 * @time 2017/4/16 0016 - 14:05
 * @version 1.0.0
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
