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
        ViewHolder viewHolder;
        ActionItem actionItem = actionItemList.get(position);
        if (null == convertView) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.dialog_multichoise_image_iv);
            viewHolder.textView = convertView.findViewById(R.id.dialog_multichoise_text_tv);
            viewHolder.checkBox = convertView.findViewById(R.id.dialog_multichoise_check_cb);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.imageView.setImageResource(R.drawable.shape_dialog_spot);
            viewHolder.textView.setText(actionItem.getTitle());
            viewHolder.checkBox.setChecked(actionItem.isSelectable());
        }
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
        CheckBox checkBox;
    }
}
