package xyz.zimuju.nathanieldialog.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/*
 * @description ProgressLayout
 * @author Nathaniel
 * @email nathanwriting@126.com
 * @time 2017/4/16 0016 - 14:06
 * @version 1.0.0
 */
public class ProgressLayout extends FrameLayout {

    private static final int DEFAULT_COUNT = 5;
    private int spotsCount;

    public ProgressLayout(Context context) {
        this(context, null);
    }

    public ProgressLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.SpotsDialog, defStyleAttr, defStyleRes);
        spotsCount = typedArray.getInt(R.styleable.SpotsDialog_DialogSpotCount, DEFAULT_COUNT);
        typedArray.recycle();
    }

    public int getSpotsCount() {
        return spotsCount;
    }
}
