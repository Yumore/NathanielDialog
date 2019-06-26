package xyz.zimuju.library;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;

import java.util.List;

/**
 * NathanielDialog
 * 自定义的Dialog
 * 所有的文字相关的都需要在外面传值进来
 * 如果没有设置Title则使用默认的
 * 并且所有的按钮都默认都调用了dialog.dismiss()
 * title的默认值为“操作提示”
 *
 * @author Nathaniel
 * nathanwriting@126.com
 * @version v1.1.2
 * @date 2016年3月30日-下午4:41:18
 */
public class NathanielDialog extends Dialog {
    private static AnimatorPlayer animatorPlayer;
    @SuppressLint("StaticFieldLeak")
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

    private static int dip2px(Context context, int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * stop progress and clear focus
     */
    public void stopProgress() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        if (animatorPlayer != null) {
            animatorPlayer.stop();
            animatorPlayer = null;
        }
    }

    public String getEditText() {
        return builder.getEditText();
    }

    public static class Builder implements View.OnClickListener, OnKeyListener {
        private static final int DELAY = 150;
        private static final int DURATION = 1500;

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

        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private OnClickListener neutralButtonClickListener;
        private OnItemClickListener onItemClickListener;
        private OnDismissListener onDismissListener;
        private OnCancelListener onCancelListener;
        private OnKeyListener onKeyListener;
        private OnMultiChoiceClickListener onMultiChoiceClickListener;
        private OnPositiveClickListener onPositiveClickListener;

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
        private View customView;
        private int maxLines;
        private boolean[] checkedItems;

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

        public Builder setCustomView(View customView) {
            this.customView = customView;
            return this;
        }

        public Builder setCustomView(View customView, boolean needClear) {
            this.needClear = needClear;
            this.customView = customView;
            return this;
        }

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

        public Builder setMultiChoiceItems(int itemsId, boolean[] checkedItems, final OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.items = context.getResources().getStringArray(itemsId);
            this.checkedItems = checkedItems;
            this.onMultiChoiceClickListener = onMultiChoiceClickListener;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, final OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.items = items;
            this.checkedItems = checkedItems;
            this.onMultiChoiceClickListener = onMultiChoiceClickListener;
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

        public Builder setItems(int itemsId) {
            this.items = context.getResources().getTextArray(itemsId);
            return this;
        }

        public Builder setActions(List<ActionItem> actionItemList) {
            this.actionItemList = actionItemList;
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

        public Builder setMaxLines(int maxLines) {
            this.maxLines = maxLines;
            return this;
        }

        public Builder setOnPositiveClickListener(OnPositiveClickListener onPositiveClickListener) {
            this.onPositiveClickListener = onPositiveClickListener;
            return this;
        }

        /**
         * initial all views and set value to widget
         *
         * @return NathanielDialog
         */
        public NathanielDialog create() {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            nathanielDialog = new NathanielDialog(context, R.style.NathanielDialog);
            normalLayout = (RelativeLayout) layoutInflater.inflate(R.layout.layout_dialog_normal, null);
            nathanielDialog.addContentView(normalLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            dialogContainer = normalLayout.findViewById(R.id.dialog_container_ll);
            dialogTitle = normalLayout.findViewById(R.id.dialog_title);
            dialogMessage = normalLayout.findViewById(R.id.dialog_message);
            dialogEditor = normalLayout.findViewById(R.id.dialog_editor);
            dialogImage = normalLayout.findViewById(R.id.dialog_image);
            dialogContent = normalLayout.findViewById(R.id.dialog_content);
            progressLayout = normalLayout.findViewById(R.id.dialog_spots_progress);
            bottomView = normalLayout.findViewById(R.id.dialog_bottom_view);
            topView = normalLayout.findViewById(R.id.dialog_top_view);

            if (title != null) {
                dialogTitle.setText(title);
            } else {
                if (progressive) {
                    dialogTitle.setText(R.string.dialog_default_loading);
                } else {
                    dialogTitle.setText(R.string.dialog_default_not_loading);
                }
            }

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

                if (maxLines > 0) {
                    int defaultLineHeight = dip2px(context, 30);
                    LayoutParams layoutParams = dialogEditor.getLayoutParams();
                    layoutParams.height = maxLines * defaultLineHeight;
                    dialogEditor.setLayoutParams(layoutParams);
                }
                dialogEditor.setVisibility(View.VISIBLE);
            }

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

            if (progressive) {
                if (spotCount <= 0) {
                    spotCount = progressLayout.getSpotsCount();
                }
                progressLayout.setVisibility(View.VISIBLE);

                animatedViews = new AnimatedView[spotCount];
                int spotSize = context.getResources().getDimensionPixelSize(R.dimen.spot_size);
                int progressWidth = context.getResources().getDimensionPixelSize(R.dimen.progress_width);
                for (int i = 0; i < animatedViews.length; i++) {
                    AnimatedView animatedView = new AnimatedView(context);
                    animatedView.setBackgroundResource(R.drawable.shape_dialog_spot);
                    animatedView.setTarget(progressWidth);
                    animatedView.setXFactor(-1f);
                    progressLayout.addView(animatedView, spotSize, spotSize);
                    animatedViews[i] = animatedView;
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
                        itemTextView = itemLayout.findViewById(R.id.dialog_text_bottom_tv);
                    } else {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_center, null);
                        itemTextView = itemLayout.findViewById(R.id.dialog_text_center_tv);
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
                }
            }

            if (strings != null && strings.size() > 0) {
                if (dialogContent != null) {
                    dialogContent.removeAllViews();
                }
                for (int i = 0; i < strings.size(); i++) {
                    if (i == strings.size() - 1) {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_bottom, null);
                        itemTextView = itemLayout.findViewById(R.id.dialog_text_bottom_tv);
                    } else {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_center, null);
                        itemTextView = itemLayout.findViewById(R.id.dialog_text_center_tv);
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
                }
            }

            if (actionItemList != null && actionItemList.size() > 0) {
                if (dialogContent != null) {
                    dialogContent.removeAllViews();
                }
                for (int i = 0; i < actionItemList.size(); i++) {
                    if (i == actionItemList.size() - 1) {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_bottom, null);
                        itemImageView = itemLayout.findViewById(R.id.dialog_image_bottom_iv);
                        itemTextView = itemLayout.findViewById(R.id.dialog_text_bottom_tv);
                    } else {
                        itemLayout = (LinearLayout) layoutInflater.inflate(R.layout.item_dialog_center, null);
                        itemImageView = itemLayout.findViewById(R.id.dialog_image_center_iv);
                        itemTextView = itemLayout.findViewById(R.id.dialog_text_center_tv);
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
                }
            }

            buttonLayout = (LinearLayout) layoutInflater.inflate(R.layout.layout_dialog_button, null);
            if (!TextUtils.isEmpty(positiveButtonText)) {
                positiveButton.setTag(Dialog.BUTTON_POSITIVE);
                positiveButton = buttonLayout.findViewById(R.id.dialog_positive_btn);
                positiveButton.setText(positiveButtonText);
                positiveButton.setVisibility(View.VISIBLE);
                positiveButton.setOnClickListener(this);
            }
            if (!TextUtils.isEmpty(negativeButtonText)) {
                negativeButton = buttonLayout.findViewById(R.id.dialog_negative_btn);
                negativeButton.setTag(Dialog.BUTTON_NEGATIVE);
                negativeButton.setText(negativeButtonText);
                negativeButton.setVisibility(View.VISIBLE);
                negativeButton.setOnClickListener(this);
            }
            if (!TextUtils.isEmpty(neutralButtonText)) {
                neutralButton = buttonLayout.findViewById(R.id.dialog_neutral_btn);
                neutralButton.setTag(Dialog.BUTTON_NEUTRAL);
                neutralButton.setText(neutralButtonText);
                neutralButton.setVisibility(View.VISIBLE);
                neutralButton.setOnClickListener(this);
            }

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
                dialogWindow.setAttributes(windowLayoutParams);
            }

            nathanielDialog.setOnKeyListener(this);

            if (dialogOffset != null) {
                WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
                layoutParams.x = dialogOffset.getOffsetX();
                layoutParams.y = dialogOffset.getOffsetY();
                layoutParams.alpha = dialogOffset.getAlpha();
                dialogWindow.setAttributes(layoutParams);
            }

            if (customView != null) {
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

        @Override
        public void onClick(View view) {
            int tag = (int) view.getTag();
            switch (tag) {
                case Dialog.BUTTON_POSITIVE:
                    if (editable && onPositiveClickListener != null) {
                        onPositiveClickListener.onPositive(nathanielDialog, DialogInterface.BUTTON_POSITIVE, dialogEditor.getText());
                    }
                    if (positiveButtonClickListener != null) {
                        positiveButtonClickListener.onClick(nathanielDialog, DialogInterface.BUTTON_POSITIVE);
                    }

                default:
                    if (positiveButtonClickListener != null) {
                        positiveButtonClickListener.onClick(nathanielDialog, DialogInterface.BUTTON_POSITIVE);
                    }
                    break;
            }
            nathanielDialog.dismiss();
        }

        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            boolean flag = false;
            if (onKeyListener != null) {
                flag = onKeyListener.onKey(dialog, keyCode, event);
            }
            return flag;
        }

        public interface OnItemClickListener {
            /**
             * the item of item clicked
             *
             * @param index
             */
            void onItemClick(int index);
        }


        public interface OnPositiveClickListener {
            void onPositive(DialogInterface dialogInterface, int witch, CharSequence result);
        }

        public interface OnKeyListener {
            /**
             * back key pressed
             *
             * @param dialog
             * @param keyCode
             * @param event
             * @return
             */
            boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event);
        }
    }
}