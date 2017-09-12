package xyz.zimuju.nathanieldialog.library;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * description 自定义的Dialog
 * 所有的文字相关的都需要在外面传值进来
 * 如果没有设置Title则使用默认的
 * 并且所有的按钮都默认都调用了dialog.dismiss()
 * title的默认值为“操作提示”
 * author Nathaniel-nathanwriting@126.com
 * time 2016年3月30日-下午4:41:18
 * version v1.1.0
 */
public class NathanielDialog extends Dialog {
    private static AnimatorPlayer animatorPlayer;
    private static Builder builder;

    public NathanielDialog(Context context) {
        super(context, R.style.NathanielDialog);
    }

    public NathanielDialog(Context context, int theme) {
        super(context, theme);
    }

    public NathanielDialog(Context context, boolean cancelable, OnCancelListener onCancelListener) {
        super(context, R.style.NathanielDialog);
        setCancelable(cancelable);
        setOnCancelListener(onCancelListener);
    }

    public void stopProgress() {
        // clear focus
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        if (animatorPlayer != null) {
            animatorPlayer.stop();
            animatorPlayer = null;
        }
    }

    public String getEditText() {
        return builder.getEditText();
    }

    public static class Builder {
        private static final int DELAY = 150;
        private static final int DURATION = 1500;

        // field
        private Context context;
        private CharSequence title;
        private CharSequence positiveButtonText;
        private CharSequence neutralButtonText;
        private CharSequence negativeButtonText;
        private boolean cancelable;
        private CharSequence[] items;
        private List<ActionItem> actionItemList;
        private int resId;
        private String imagePath;
        private Bitmap bitmap;
        private AnimatedView[] animatedViews;
        private CharSequence message;
        private boolean needClear;
        private boolean clearAll;
        private int gravity;
        private boolean progressive;
        private int spotCount;
        private List<String> strings;
        private boolean editable;
        private CharSequence hint;

        // listener
        private NathanielDialog.OnClickListener positiveButtonClickListener;
        private NathanielDialog.OnClickListener negativeButtonClickListener;
        private NathanielDialog.OnClickListener neutralButtonClickListener;
        private NathanielDialog.OnClickListener onClickListener;
        private OnItemClickListener onItemClickListener;
        private OnDismissListener onDismissListener;
        private AdapterView.OnItemSelectedListener onItemSelectedListener;
        private OnCancelListener onCancelListener;
        private OnKeyListener onKeyListener;

        // view
        private TextView positiveButton;
        private LinearLayout dialogContainer;
        private TextView neutralButton;
        private TextView negativeButton;
        private TextView dialogMessage;
        private EditText dialogEditor;
        private LinearLayout dialogContent;
        private TextView dialogTitle;
        private TextView itemTextView;
        private LinearLayout itemLayout;
        private LayoutInflater layoutInflater;
        private NathanielDialog nathanielDialog;
        private Window dialogWindow;
        private WindowManager.LayoutParams windowLayoutParams;
        private RelativeLayout normalLayout;
        private LinearLayout buttonLayout;
        private ImageView dialogImage;
        private ImageView itemImageView;
        private DialogOffset dialogOffset;
        private View bottomView;
        private View topView;
        private ProgressLayout progressLayout;
        // custom view
        private View customView;

        public Builder(Context context) {
            builder = this;
            this.context = context;
        }

        public Builder setTitle(int title) {
            this.title = context.getResources().getString(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = context.getResources().getString(message);
            return this;
        }

        public Builder setEditable(boolean editable) {
            this.editable = editable;
            return this;
        }

        public Builder setHint(int resId) {
            this.resId = resId;
            return this;
        }

        public Builder setHint(CharSequence hint) {
            this.hint = hint;
            return this;
        }

        public Builder setImage(int resId) {
            this.resId = resId;
            return this;
        }

        public Builder setImage(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public Builder setImageBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        // keep title show
        public Builder setCustomView(View customView) {
            this.customView = customView;
            return this;
        }

        public Builder setCustomView(View customView, boolean needClear) {
            this.needClear = needClear;
            this.customView = customView;
            return this;
        }

        // clear all view
        public Builder setCustomView(View customView, boolean needClear, boolean clearAll) {
            this.needClear = needClear;
            this.clearAll = clearAll;
            this.customView = customView;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = context.getResources().getString(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = context.getResources().getString(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNeutralButton(int neutralButtonText, OnClickListener listener) {
            this.neutralButtonText = context.getResources().getString(neutralButtonText);
            this.neutralButtonClickListener = listener;
            return this;
        }

        public Builder setNeutralButton(String neutralButtonText, OnClickListener listener) {
            this.neutralButtonText = neutralButtonText;
            this.neutralButtonClickListener = listener;
            return this;
        }

        public Builder setItems(CharSequence[] items, OnItemClickListener onItemClickListener) {
            this.items = items;
            this.onItemClickListener = onItemClickListener;
            return this;
        }

        public Builder setItems(List<String> strings, OnItemClickListener onItemClickListener) {
            this.strings = strings;
            this.onItemClickListener = onItemClickListener;
            return this;
        }

        public Builder setItems(int itemsId, OnItemClickListener onItemClickListener) {
            this.items = context.getResources().getTextArray(itemsId);
            this.onItemClickListener = onItemClickListener;
            return this;
        }

        public Builder setActions(List<ActionItem> actionItemList, OnItemClickListener onItemClickListener) {
            this.actionItemList = actionItemList;
            this.onItemClickListener = onItemClickListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.onCancelListener = onCancelListener;
            return this;
        }


        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setAttributes(WindowManager.LayoutParams windowLayoutParams) {
            this.windowLayoutParams = windowLayoutParams;
            return this;
        }

        public Builder setProgressive(boolean progressive) {
            this.progressive = progressive;
            return this;
        }

        public Builder setLayoutParamsOffset(DialogOffset dialogOffset) {
            this.dialogOffset = dialogOffset;
            return this;
        }

        public Builder setProgressSpotCount(int spotCount) {
            this.spotCount = spotCount;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            this.onKeyListener = onKeyListener;
            return this;
        }

        public String getEditText() {
            return dialogEditor.getText().toString();
        }

        public NathanielDialog create() {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            nathanielDialog = new NathanielDialog(context, R.style.NathanielDialog);

            // normal dialog
            normalLayout = (RelativeLayout) layoutInflater.inflate(R.layout.layout_dialog_normal, null);

            nathanielDialog.addContentView(normalLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            dialogContainer = (LinearLayout) normalLayout.findViewById(R.id.dialog_container_ll);

            dialogTitle = (TextView) normalLayout.findViewById(R.id.dialog_title);

            dialogMessage = (TextView) normalLayout.findViewById(R.id.dialog_message);

            dialogEditor = (EditText) normalLayout.findViewById(R.id.dialog_editor);

            dialogImage = (ImageView) normalLayout.findViewById(R.id.dialog_image);

            dialogContent = (LinearLayout) normalLayout.findViewById(R.id.dialog_content);

            progressLayout = (ProgressLayout) normalLayout.findViewById(R.id.dialog_spots_progress);

            bottomView = normalLayout.findViewById(R.id.dialog_bottom_view);

            topView = normalLayout.findViewById(R.id.dialog_top_view);

            // title
            if (title != null) {
                dialogTitle.setText(title);
            } else {
                if (progressive) {
                    dialogTitle.setText(R.string.dialog_default_loading);
                } else {
                    dialogTitle.setText(R.string.dialog_default_not_loading);
                }
            }

            // message
            if (message != null) {
                dialogMessage.setText(message);
                dialogMessage.setVisibility(View.VISIBLE);
            }

            if (editable) {
                if (resId != 0) {
                    dialogEditor.setHint(context.getResources().getString(resId));
                }

                if (TextUtils.isEmpty(hint)) {
                    dialogEditor.setHint(hint);
                }
                dialogEditor.setCursorVisible(true);
                dialogEditor.setVisibility(View.VISIBLE);
            }

            // image
            if (resId > 0) {
                dialogImage.setImageResource(resId);
                dialogImage.setVisibility(View.VISIBLE);
            }

            if (imagePath != null) {
                dialogImage.setImageURI(Uri.parse(imagePath));
                dialogImage.setVisibility(View.VISIBLE);
            }

            if (bitmap != null) {
                dialogImage.setImageBitmap(bitmap);
                dialogImage.setVisibility(View.VISIBLE);
            }

            // progress
            if (progressive) {
                progressLayout.setVisibility(View.VISIBLE);

                // spot count
                if (spotCount <= 0) {
                    spotCount = progressLayout.getSpotsCount();
                }

                // initialized progress layout
                animatedViews = new AnimatedView[spotCount];
                int spotSize = context.getResources().getDimensionPixelSize(R.dimen.spot_size);
                int progressWidth = context.getResources().getDimensionPixelSize(R.dimen.progress_width);
                for (int i = 0; i < animatedViews.length; i++) {
                    AnimatedView v = new AnimatedView(context);
                    v.setBackgroundResource(R.drawable.shape_dialog_spot);
                    v.setTarget(progressWidth);
                    v.setXFactor(-1f);
                    progressLayout.addView(v, spotSize, spotSize);
                    animatedViews[i] = v;
                }

                Animator[] animators = new Animator[spotCount];
                for (int i = 0; i < animatedViews.length; i++) {
                    Animator move = ObjectAnimator.ofFloat(animatedViews[i], "xFactor", 0, 1);
                    move.setDuration(DURATION);
                    move.setInterpolator(new HesitateInterpolator());
                    move.setStartDelay(DELAY * i);
                    animators[i] = move;
                }

                if (animatorPlayer != null) {
                    animatorPlayer.stop();
                }
                animatorPlayer = new AnimatorPlayer(animators);
                animatorPlayer.play();
            }


            if (items != null && items.length > 0) {
                if (dialogContent != null) {
                    dialogContent.removeAllViews();
                }
                for (int i = 0; i < items.length; i++) {
                    if (i == items.length - 1) {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_bottom, null);
                        itemLayout.setLayoutParams(dialogTitle.getLayoutParams());
                        itemTextView = (TextView) itemLayout.findViewById(R.id.dialog_text_bottom_tv);
                    } else {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_center, null);
                        itemTextView = (TextView) itemLayout.findViewById(R.id.dialog_text_center_tv);
                    }
                    itemTextView.setText(items[i]);
                    itemTextView.setTag(i);
                    dialogContent.addView(itemLayout);
                    dialogContent.setBackgroundResource(R.drawable.shape_dialog_normal);
                    if (onItemClickListener != null) {
                        final TextView finalTextView = itemTextView;
                        itemLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Integer index = (Integer) finalTextView.getTag();
                                onItemClickListener.onItemClick(index);
                                nathanielDialog.dismiss();
                            }
                        });
                    }
                    nathanielDialog.dismiss();
                }
            }

            if (strings != null && strings.size() > 0) {
                if (dialogContent != null) {
                    dialogContent.removeAllViews();
                }
                for (int i = 0; i < strings.size(); i++) {
                    if (i == strings.size() - 1) {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_bottom, null);
                        itemTextView = (TextView) itemLayout.findViewById(R.id.dialog_text_bottom_tv);
                    } else {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_center, null);
                        itemTextView = (TextView) itemLayout.findViewById(R.id.dialog_text_center_tv);
                    }
                    itemTextView.setVisibility(View.VISIBLE);
                    itemTextView.setText(strings.get(i));
                    itemTextView.setTag(i);
                    dialogContent.addView(itemLayout);
                    dialogContent.setBackgroundResource(R.drawable.shape_dialog_normal);
                    if (onItemClickListener != null) {
                        final TextView finalTextView = itemTextView;
                        itemLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Integer index = (Integer) finalTextView.getTag();
                                onItemClickListener.onItemClick(index);
                                nathanielDialog.dismiss();
                            }
                        });
                    }
                    nathanielDialog.dismiss();
                }
            }

            if (actionItemList != null && actionItemList.size() > 0) {
                if (dialogContent != null) {
                    dialogContent.removeAllViews();
                }
                for (int i = 0; i < actionItemList.size(); i++) {
                    if (i == actionItemList.size() - 1) {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_bottom, null);
                        itemImageView = (ImageView) itemLayout.findViewById(R.id.dialog_image_bottom_iv);
                        itemTextView = (TextView) itemLayout.findViewById(R.id.dialog_text_bottom_tv);
                    } else {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_center, null);
                        itemImageView = (ImageView) itemLayout.findViewById(R.id.dialog_image_center_iv);
                        itemTextView = (TextView) itemLayout.findViewById(R.id.dialog_text_center_tv);
                    }

                    itemImageView.setVisibility(View.VISIBLE);
                    itemTextView.setVisibility(View.VISIBLE);
                    if (actionItemList.get(i).getBitmap() != null) {
                        itemImageView.setImageBitmap(actionItemList.get(i).getBitmap());
                    }

                    if (actionItemList.get(i).getResId() > 0) {
                        itemImageView.setImageResource(actionItemList.get(i).getResId());
                    }
                    itemTextView.setText(actionItemList.get(i).getTitle());
                    itemTextView.setTag(i);
                    dialogContent.addView(itemLayout);
                    dialogContent.setBackgroundResource(R.drawable.shape_dialog_normal);
                    if (onItemClickListener != null) {
                        final TextView finalTextView = itemTextView;
                        itemLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Integer index = (Integer) finalTextView.getTag();
                                onItemClickListener.onItemClick(index);
                                nathanielDialog.dismiss();
                            }
                        });
                    }
                    nathanielDialog.dismiss();
                }
            }

            int type = 0;
            if (!TextUtils.isEmpty(positiveButtonText)) {
                type = 1;
                if (!TextUtils.isEmpty(negativeButtonText)) {
                    type = 2;
                    if (!TextUtils.isEmpty(neutralButtonText)) {
                        type = 3;
                    }
                }
            }

            switch (type) {
                case 1:
                    buttonLayout = (LinearLayout) layoutInflater.inflate(R.layout.layout_dialog_one_button, null);
                    positiveButton = (TextView) buttonLayout.findViewById(R.id.dialog_positive_btn);
                    positiveButton.setText(positiveButtonText);
                    break;

                case 2:
                    buttonLayout = (LinearLayout) layoutInflater.inflate(R.layout.layout_dialog_two_button, null);
                    positiveButton = (TextView) buttonLayout.findViewById(R.id.dialog_positive_btn);
                    negativeButton = (TextView) buttonLayout.findViewById(R.id.dialog_negative_btn);
                    positiveButton.setText(positiveButtonText);
                    negativeButton.setText(negativeButtonText);
                    break;

                case 3:
                    buttonLayout = (LinearLayout) layoutInflater.inflate(R.layout.layout_dialog_three_button, null);
                    positiveButton = (TextView) buttonLayout.findViewById(R.id.dialog_positive_btn);
                    negativeButton = (TextView) buttonLayout.findViewById(R.id.dialog_negative_btn);
                    neutralButton = (TextView) buttonLayout.findViewById(R.id.dialog_neutral_btn);
                    positiveButton.setText(positiveButtonText);
                    negativeButton.setText(negativeButtonText);
                    neutralButton.setText(neutralButtonText);
                    break;
            }

            if (positiveButton != null) {
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (positiveButtonClickListener != null) {
                            positiveButtonClickListener.onClick(nathanielDialog, DialogInterface.BUTTON_POSITIVE);
                        }
                        nathanielDialog.dismiss();
                    }
                });
            }

            if (negativeButton != null) {
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (negativeButtonClickListener != null) {
                            negativeButtonClickListener.onClick(nathanielDialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                        nathanielDialog.dismiss();
                    }
                });
            }

            if (neutralButton != null) {
                neutralButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (negativeButtonClickListener != null) {
                            neutralButtonClickListener.onClick(nathanielDialog, DialogInterface.BUTTON_NEUTRAL);
                        }
                        nathanielDialog.dismiss();
                    }
                });
            }

            dialogContent.setBackgroundResource(type > 0 ? R.drawable.shape_dialog_center : R.drawable.shape_dialog_bottom);

            if (buttonLayout != null) {
                buttonLayout.setLayoutParams(dialogTitle.getLayoutParams());
                dialogContainer.addView(buttonLayout);
            }

            dialogWindow = nathanielDialog.getWindow();

            nathanielDialog.setCancelable(cancelable);

            nathanielDialog.setCanceledOnTouchOutside(cancelable);

            nathanielDialog.setOnCancelListener(onCancelListener);

            nathanielDialog.setOnDismissListener(onDismissListener);

            if (gravity != 0) {
                if (gravity == Gravity.TOP) {
                    topView.setVisibility(View.VISIBLE);
                } else {
                    topView.setVisibility(View.GONE);
                }

                if (gravity == Gravity.BOTTOM) {
                    bottomView.setVisibility(View.VISIBLE);
                } else {
                    bottomView.setVisibility(View.GONE);
                }
                dialogWindow.setGravity(gravity);
            }

            if (windowLayoutParams != null) {
                WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
                layoutParams.width = windowLayoutParams.width;
                layoutParams.height = windowLayoutParams.height;
                dialogWindow.setAttributes(layoutParams);
            }

            nathanielDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    boolean flag = false;
                    if (onKeyListener != null) {
                        flag = onKeyListener.onKey(dialog, keyCode, event);
                    }
                    return flag;
                }
            });

            if (dialogOffset != null) {
                WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
                layoutParams.x = dialogOffset.getOffsetX();
                layoutParams.y = dialogOffset.getOffsetY();
                layoutParams.alpha = dialogOffset.getAlpha();
                dialogWindow.setAttributes(layoutParams);
            }

            // custom view
            if (customView != null) {
                // need clear title or not
                if (needClear) {
                    if (clearAll) {
                        normalLayout.removeAllViews();
                        normalLayout.addView(customView);
                    } else {
                        dialogContent.removeAllViews();
                        dialogContent.addView(customView);
                    }
                } else {
                    dialogContent.removeAllViews();
                    dialogContent.addView(customView);
                }
            }

            nathanielDialog.setContentView(normalLayout);

            return nathanielDialog;
        }

        public interface OnItemClickListener {
            void onItemClick(int index);
        }

        public interface OnKeyListener {
            boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event);
        }
    }
}
