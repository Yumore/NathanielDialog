package xyz.zimuju.nathanieldialog.library;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/*
 * @description ActionItem : PopupWindow 的内部子类项（绘制标题和图标）
 *
 * 在这里我们要考虑以下情况：
 * 1.只有图片资源(通过 context 获取id)
 * 2.直接添加文字
 * 3.通过id添加文字(通过 context 获取id)
 * 4.网络图片
 * 5.图片资源 文字
 *
 * @author Nathaniel-nathanwriting@126.com
 * @time 2016/8/17-10:06
 * @version v1.0.0
 */
public class ActionItem {
    private int resId;
    private Bitmap bitmap;
    private CharSequence title;
    private boolean selectable;

    public ActionItem() {
    }

    public ActionItem(int resId) {
        this.resId = resId;
    }

    public ActionItem(CharSequence title, Bitmap bitmap) {
        this.title = title;
        this.bitmap = bitmap;
    }

    public ActionItem(CharSequence title, String imagePath) {
        this.title = title;
        this.bitmap = BitmapFactory.decodeFile(imagePath);
    }

    public ActionItem(CharSequence title, int resId) {
        this.title = title;
        this.resId = resId;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;
    }
}
