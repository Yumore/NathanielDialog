package xyz.zimuju.nathanieldialog.library;

/*
 * @description DialogOffset 横向/纵向/透明度变化
 * @author Nathaniel-nathanwriting@126.com
 * @time 17-2-20-下午1:42
 * @version v1.0.0
 */
public class DialogOffset {
    private int offsetX;
    private int offsetY;
    private int alpha;

    public DialogOffset() {
    }

    public DialogOffset(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public DialogOffset(int offsetX, int offsetY, int alpha) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.alpha = alpha;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
}
