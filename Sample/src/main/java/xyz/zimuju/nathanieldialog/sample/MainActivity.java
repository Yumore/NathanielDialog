package xyz.zimuju.nathanieldialog.sample;

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

import xyz.zimuju.nathanieldialog.R;
import xyz.zimuju.nathanieldialog.library.ActionItem;
import xyz.zimuju.nathanieldialog.library.NathanielDialog;


/*
 * @description MainActivity 演示
 * @author Nathaniel-nathanwriting@126.com
 * @time 17-2-20-下午1:43
 * @version v1.0.9
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView showNormalDialog;
    private TextView showImageDialog;
    private TextView showItemsDialog;
    private TextView showPopupWindowDialog;
    private TextView spotProgressDialog;
    private List<ActionItem> actions;
    private TextView editorDialog;
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
        showNormalDialog = (TextView) findViewById(R.id.normal_dialog);
        showImageDialog = (TextView) findViewById(R.id.image_dialog);
        showItemsDialog = (TextView) findViewById(R.id.items_dialog);
        showPopupWindowDialog = (TextView) findViewById(R.id.popup_window_dialog);
        spotProgressDialog = (TextView) findViewById(R.id.progress_dialog);
        editorDialog = (TextView) findViewById(R.id.editor_dialog);
    }

    private void viewOptions() {
        showNormalDialog.setOnClickListener(this);
        showImageDialog.setOnClickListener(this);
        showItemsDialog.setOnClickListener(this);
        showPopupWindowDialog.setOnClickListener(this);
        spotProgressDialog.setOnClickListener(this);
        editorDialog.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_dialog:
                nathanielDialog = new NathanielDialog.Builder(this)
                        .setTitle("Title test here")
                        .setMessage("Message test here")
                        .setPositiveButton("Ok", null)
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
                        .setPositiveButton("Ok", null)
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
                                if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0) {
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

                                Toast.makeText(MainActivity.this, "输入完成", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                nathanielDialog.show();
                break;
        }
    }
}
