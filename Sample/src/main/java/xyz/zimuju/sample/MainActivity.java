package xyz.zimuju.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xyz.zimuju.library.ActionItem;
import xyz.zimuju.library.NathanielDialog;


/**
 * MainActivity
 * 演示
 *
 * @author Nathaniel
 * nathanwriting@126.com
 * @version v1.1.0
 * @date 17-2-20-下午1:43
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView showNormalDialog;
    private TextView showImageDialog;
    private TextView showItemsDialog;
    private TextView showPopupWindowDialog;
    private TextView spotProgressDialog;
    private List<ActionItem> actions;
    private TextView editorDialog;
    private TextView listDialog;
    private NathanielDialog nathanielDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ActionItem actionItem = new ActionItem("item" + (i + 1), R.mipmap.ic_launcher);
            actions.add(actionItem);
        }

        initViews();
        viewOptions();
    }

    private void initViews() {
        showNormalDialog = findViewById(R.id.normal_dialog);
        showImageDialog = findViewById(R.id.image_dialog);
        showItemsDialog = findViewById(R.id.items_dialog);
        showPopupWindowDialog = findViewById(R.id.popup_window_dialog);
        spotProgressDialog = findViewById(R.id.progress_dialog);
        editorDialog = findViewById(R.id.editor_dialog);
        listDialog = findViewById(R.id.list_dialog);
    }

    private void viewOptions() {
        showNormalDialog.setOnClickListener(this);
        showImageDialog.setOnClickListener(this);
        showItemsDialog.setOnClickListener(this);
        showPopupWindowDialog.setOnClickListener(this);
        spotProgressDialog.setOnClickListener(this);
        editorDialog.setOnClickListener(this);
        listDialog.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_dialog:
                nathanielDialog = new NathanielDialog.Builder(this)
                        .setTitle("Title test here")
                        .setMessage("Message test here")
                        .setPositiveButton("Ok", (DialogInterface.OnClickListener) null)
                        .setNegativeButton("Cancel", null)
                        .setNeutralButton("Neutral", null)
                        .setCancelable(true)
                        .create();
                nathanielDialog.show();
                break;

            case R.id.image_dialog:
                nathanielDialog = new NathanielDialog.Builder(this)
                        .setTitle("Beautiful girl")
                        .setImage(R.mipmap.girl)
                        .setPositiveButton("Ok", (DialogInterface.OnClickListener) null)
                        .setNegativeButton("Cancel", null)
                        .setGravity(Gravity.BOTTOM)
                        .setCancelable(true)
                        .create();
                nathanielDialog.show();
                break;

            case R.id.items_dialog:
                nathanielDialog = new NathanielDialog.Builder(this)
                        .setTitle("Please select one of them")
                        .setItems(R.array.dialog_options, new NathanielDialog.Builder.OnItemClickListener() {
                            @Override
                            public void onItemClick(int index) {
                                switch (index) {
                                    default:
                                        Toast.makeText(MainActivity.this, "item" + index + " has been clicked", Toast.LENGTH_SHORT).show();
                                        break;

                                }
                            }
                        })
                        .setCancelable(true)
                        .create();
                nathanielDialog.show();
                break;

            case R.id.popup_window_dialog:
                nathanielDialog = new NathanielDialog.Builder(this)
                        .setTitle("Please select one of them")
                        .setActions(actions, new NathanielDialog.Builder.OnItemClickListener() {
                            @Override
                            public void onItemClick(int index) {
                                switch (index) {
                                    default:
                                        Toast.makeText(MainActivity.this, actions.get(index).getTitle() + " has been clicked", Toast.LENGTH_SHORT).show();
                                        break;

                                }
                            }
                        })
                        .setCancelable(true)
                        .create();
                nathanielDialog.show();
                break;

            case R.id.progress_dialog:
                nathanielDialog = new NathanielDialog.Builder(this)
                        .setProgressive(true)
                        .setMessage("Loading data ......")
                        .setOnKeyListener(new NathanielDialog.Builder.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                                    nathanielDialog.stopProgress();
                                    dialog.dismiss();
                                }
                                return false;
                            }
                        })
                        .create();
                nathanielDialog.show();
                break;

            case R.id.editor_dialog:
                nathanielDialog = new NathanielDialog.Builder(this)
                        .setEditable(true)
                        .setHint("这是提示")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = nathanielDialog.getEditText();
                                Toast.makeText(MainActivity.this, "输入完成->" + text, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create();
                nathanielDialog.show();
                break;

            case R.id.list_dialog:
                nathanielDialog = new NathanielDialog.Builder(this)
                        .setEditable(true)
                        .setHint("这是提示")
                        .setMultiEnable(true)
                        .setActions(actions)
                        .setPositiveButton("确认", new NathanielDialog.Builder.OnMultiChoiceListener() {
                            @Override
                            public void onMultiChoice(List<ActionItem> actionItemList) {
                                Toast.makeText(MainActivity.this, "selected items' size is " + actionItemList.size(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create();
                nathanielDialog.show();
                break;

            default:
                break;
        }
    }
}
