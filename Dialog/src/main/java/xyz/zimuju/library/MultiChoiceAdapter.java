package xyz.zimuju.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author Nathaniel
 * @date 2018/4/26-21:54
 */
public class MultiChoiceAdapter extends BaseAdapter {
    private List<ActionItem> actionItemList;

    public List<ActionItem> getActionItemList() {
        return actionItemList;
    }

    public void setActionItemList(List<ActionItem> actionItemList) {
        this.actionItemList = actionItemList;
    }

    @Override
    public int getCount() {
        return actionItemList == null ? 0 : actionItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return actionItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_list, parent, false);
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private CheckBox checkBox;
    }
}
