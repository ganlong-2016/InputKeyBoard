package com.hxzj.inputkeyboard.Keyboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.hxzj.inputkeyboard.R;

import java.lang.reflect.Method;


public class CustomKeyBoardManager implements View.OnFocusChangeListener, KeyboardView.OnKeyboardActionListener {

    /**
     * 键盘类型 ：股票搜索数字键盘
     */
    public static final int KEYBOARD_STOCK_123 = 0;
    /**
     * 键盘类型 ：股票搜索字母键盘
     */
    public static final int KEYBOARD_STOCK_ABC = 1;

    /**
     * 键盘类型 ：股票交易价格键盘
     */
    public static final int KEYBOARD_TRADE_PRICE = 2;
    /**
     * 键盘类型 ：股票交易仓位键盘
     */
    public static final int KEYBOARD_TRADE_POSITION = 3;
    /**
     * 键盘类型 ：密码数字键盘
     */
    public static final int KEYBOARD_PASSWORD_123 = 4;
    /**
     * 键盘类型 ：密码字母键盘
     */
    public static final int KEYBOARD_PASSWORD_ABC = 5;

    private Activity mContext;
    private ViewGroup mRootView;
    private FrameLayout mKeyboardViewContainer;
    private FrameLayout.LayoutParams mKeyboardViewLayoutParams;
    private CustomKeyBoardView mKeyboardView;
    private int mKeyboardHeight;
    private EditText mEditText;
    private CustomKeyboard keyboard0;
    private CustomKeyboard keyboard1;
    private CustomKeyboard keyboard2;
    private CustomKeyboard keyboard3;
    private CustomKeyboard keyboard4;
    private CustomKeyboard keyboard5;
    private boolean isKeyBoardShow;
    //自定义按键
    private final int keycode_clear = -1000;
    private final int keycode_delete = -1001;
    private final int keycode_hide = -1002;
    private final int keycode_determine = -1003;
    private final int keycode_system_keyboard = -1004;
    private final int keycode_00 = -1005;
    private final int keycode_useless = -1006;
    private final int keycode_600 = -10000;
    private final int keycode_300 = -10001;
    private final int keycode_000 = -10002;
    private final int keycode_stock_abc = -10003;
    private final int keycode_stock_123 = -10004;
    private final int keycode_point = -10100;
    private final int keycode_next = -10101;
    private final int keycode_stock_num_all = -10200;
    private final int keycode_stock_num_half = -10201;
    private final int keycode_stock_num_1_3 = -10202;
    private final int keycode_stock_num_1_4 = -10203;
    private final int keycode_password_abc = -10300;
    private final int keycode_password_123 = -10301;

    public CustomKeyBoardManager(Activity activity) {
        mContext = activity;
        mRootView = (activity.getWindow().getDecorView().findViewById(android.R.id.content));
        mKeyboardViewContainer = (FrameLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_keyboardview, null);
        mKeyboardView = mKeyboardViewContainer.findViewById(R.id.keyboardview);
        mKeyboardViewLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mKeyboardViewLayoutParams.gravity = Gravity.BOTTOM;
    }

    private CustomKeyboard getKeyboard(int type) {
        CustomKeyboard keyboard;
        switch (type) {
            case KEYBOARD_STOCK_123:
                keyboard = getKeyboard0();
                break;
            case KEYBOARD_STOCK_ABC:
                keyboard = getKeyboard1();
                break;
            case KEYBOARD_TRADE_PRICE:
                keyboard = getKeyboard2();
                break;
            case KEYBOARD_TRADE_POSITION:
                keyboard = getKeyboard3();
                break;
            case KEYBOARD_PASSWORD_123:
                keyboard = getKeyboard4();
                break;
            case KEYBOARD_PASSWORD_ABC:
                keyboard = getKeyboard5();
                break;
            default:
                keyboard = getKeyboard0();
                break;
        }
        return keyboard;
    }

    private CustomKeyboard getKeyboard0() {
        if (keyboard0 == null) {
            keyboard0 = new CustomKeyboard(mContext, R.xml.keyboard_stock_search_123);
            keyboard0.setCustomKeyStyle(new CustomKeyboard.CustomKeyStyle() {
                @Override
                public Drawable getKeyBackground(Keyboard.Key key, EditText etCur) {
                    if (keycode_determine == key.codes[0]) {
                        return mContext.getResources().getDrawable(R.drawable.bg_custom_key_blue);
                    }

                    return null;
                }

                @Override
                public Float getKeyTextSize(Keyboard.Key key, EditText etCur) {
                    return mContext.getResources().getDimension(R.dimen.keyboard_text_size);

                }

                @Override
                public Integer getKeyTextColor(Keyboard.Key key, EditText etCur) {
                    if (keycode_stock_abc == key.codes[0]) {
                        return mContext.getResources().getColor(R.color.custom_key_blue);
                    }
                    if (keycode_determine == key.codes[0]) {
                        return mContext.getResources().getColor(R.color.custom_key_white);
                    }
                    return null;
                }

                @Override
                public CharSequence getKeyLabel(Keyboard.Key key, EditText etCur) {
                    return null;
                }
            });
        }
        return keyboard0;
    }

    private CustomKeyboard getKeyboard1() {
        if (keyboard1 == null) {
            keyboard1 = new CustomKeyboard(mContext, R.xml.keyboard_stock_search_abc);
            keyboard1.setCustomKeyStyle(new CustomKeyboard.CustomKeyStyle() {
                @Override
                public Drawable getKeyBackground(Keyboard.Key key, EditText etCur) {
                    if (keycode_determine == key.codes[0]) {
                        return mContext.getResources().getDrawable(R.drawable.bg_custom_key_blue);
                    }
                    if (keycode_useless == key.codes[0]) {
                        return mContext.getResources().getDrawable(R.drawable.shape_keyboard_unpressed);
                    }
                    return null;
                }

                @Override
                public Float getKeyTextSize(Keyboard.Key key, EditText etCur) {
                    return mContext.getResources().getDimension(R.dimen.keyboard_text_size);

                }

                @Override
                public Integer getKeyTextColor(Keyboard.Key key, EditText etCur) {
                    if (keycode_stock_123 == key.codes[0]) {
                        return mContext.getResources().getColor(R.color.custom_key_blue);
                    }
                    if (keycode_determine == key.codes[0]) {
                        return mContext.getResources().getColor(R.color.custom_key_white);
                    }
                    return null;
                }

                @Override
                public CharSequence getKeyLabel(Keyboard.Key key, EditText etCur) {
                    return null;
                }
            });
        }
        return keyboard1;
    }

    private CustomKeyboard getKeyboard2() {
        if (keyboard2 == null) {
            keyboard2 = new CustomKeyboard(mContext, R.xml.keyboard_trade_price);
            keyboard2.setCustomKeyStyle(new CustomKeyboard.CustomKeyStyle() {
                @Override
                public Drawable getKeyBackground(Keyboard.Key key, EditText etCur) {
                    if (keycode_next == key.codes[0]) {
                        return mContext.getResources().getDrawable(R.drawable.bg_custom_key_blue);
                    }
                    if (keycode_useless == key.codes[0]) {
                        return mContext.getResources().getDrawable(R.drawable.shape_keyboard_unpressed);
                    }
                    return null;
                }

                @Override
                public Float getKeyTextSize(Keyboard.Key key, EditText etCur) {
                    return mContext.getResources().getDimension(R.dimen.keyboard_text_size);

                }

                @Override
                public Integer getKeyTextColor(Keyboard.Key key, EditText etCur) {
                    if (keycode_next == key.codes[0]) {
                        return mContext.getResources().getColor(R.color.custom_key_white);
                    }
                    return null;
                }

                @Override
                public CharSequence getKeyLabel(Keyboard.Key key, EditText etCur) {
                    return null;
                }
            });
        }
        return keyboard2;
    }

    private CustomKeyboard getKeyboard3() {
        if (keyboard3 == null) {
            keyboard3 = new CustomKeyboard(mContext, R.xml.keyboard_trade_position);
            keyboard3.setCustomKeyStyle(new CustomKeyboard.CustomKeyStyle() {
                @Override
                public Drawable getKeyBackground(Keyboard.Key key, EditText etCur) {
                    if (keycode_determine == key.codes[0]) {
                        return mContext.getResources().getDrawable(R.drawable.bg_custom_key_blue);
                    }
                    if (keycode_useless == key.codes[0]) {
                        return mContext.getResources().getDrawable(R.drawable.shape_keyboard_unpressed);
                    }
                    return null;
                }

                @Override
                public Float getKeyTextSize(Keyboard.Key key, EditText etCur) {
                    return mContext.getResources().getDimension(R.dimen.keyboard_text_size);

                }

                @Override
                public Integer getKeyTextColor(Keyboard.Key key, EditText etCur) {
                    if (keycode_determine == key.codes[0]) {
                        return mContext.getResources().getColor(R.color.custom_key_white);
                    }
                    return null;
                }

                @Override
                public CharSequence getKeyLabel(Keyboard.Key key, EditText etCur) {
                    return null;
                }
            });
        }
        return keyboard3;
    }

    private CustomKeyboard getKeyboard4() {
        if (keyboard4 == null) {
            keyboard4 = new CustomKeyboard(mContext, R.xml.keyboard_password_123);
            keyboard4.setCustomKeyStyle(new CustomKeyboard.CustomKeyStyle() {
                @Override
                public Drawable getKeyBackground(Keyboard.Key key, EditText etCur) {

                    return null;
                }

                @Override
                public Float getKeyTextSize(Keyboard.Key key, EditText etCur) {
                    return mContext.getResources().getDimension(R.dimen.keyboard_text_size);
                }

                @Override
                public Integer getKeyTextColor(Keyboard.Key key, EditText etCur) {
                    if (keycode_password_abc == key.codes[0]) {
                        return mContext.getResources().getColor(R.color.custom_key_blue);
                    }
                    return null;
                }

                @Override
                public CharSequence getKeyLabel(Keyboard.Key key, EditText etCur) {
                    return null;
                }
            });
        }
        return keyboard4;
    }

    private CustomKeyboard getKeyboard5() {
        if (keyboard5 == null) {
            keyboard5 = new CustomKeyboard(mContext, R.xml.keyboard_password_abc);
            keyboard5.setCustomKeyStyle(new CustomKeyboard.CustomKeyStyle() {
                @Override
                public Drawable getKeyBackground(Keyboard.Key key, EditText etCur) {
                    if (keycode_determine == key.codes[0]) {
                        return mContext.getResources().getDrawable(R.drawable.bg_custom_key_blue);
                    }
                    if (keycode_useless == key.codes[0]) {
                        return mContext.getResources().getDrawable(R.drawable.shape_keyboard_unpressed);
                    }
                    return null;
                }

                @Override
                public Float getKeyTextSize(Keyboard.Key key, EditText etCur) {
                    return mContext.getResources().getDimension(R.dimen.keyboard_text_size);
                }

                @Override
                public Integer getKeyTextColor(Keyboard.Key key, EditText etCur) {
                    if (keycode_password_123 == key.codes[0]) {
                        return mContext.getResources().getColor(R.color.custom_key_blue);
                    }
                    if (keycode_determine == key.codes[0]) {
                        return mContext.getResources().getColor(R.color.custom_key_white);
                    }
                    return null;
                }

                @Override
                public CharSequence getKeyLabel(Keyboard.Key key, EditText etCur) {
                    return null;
                }
            });
        }
        return keyboard5;
    }

    public void attachTo(final EditText editText, int type, KeyListener keyListener) {
        hideSystemSoftKeyboard(editText);
        mRootView.removeView(mKeyboardViewContainer);

        CustomKeyboard keyboard = getKeyboard(type);
        if (keyListener != null) {
            keyboard.setKeyListener(keyListener);
        }
        editText.setTag(R.id.edit_text_bind_keyboard, keyboard);

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText.setFocusable(true);
                editText.requestFocus();
                editText.setFocusableInTouchMode(true);
                if (event.getAction() == MotionEvent.ACTION_UP) {//ACTION_UP事件才触发
                    mRootView.removeView(mKeyboardViewContainer);
                    hideSystemSoftKeyboard(editText);
                    showSoftKeyboard(editText);
                    v.performClick();
                }
                return true;
            }
        });
        editText.setOnFocusChangeListener(this);
    }

    /**
     * 计算屏幕向上移动距离
     *
     * @param view 响应输入焦点的控件
     * @return 移动偏移量
     */
    private int getMoveHeight(View view) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int height = 0;
        DisplayMetrics dm = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(dm);
            height = dm.heightPixels;       // 屏幕高度（像素）
        }
        int[] vLocation = new int[2];
        view.getLocationOnScreen(vLocation); //计算输入框在屏幕中的位置
        int keyboardTop = vLocation[1] + view.getHeight() + view.getPaddingBottom() + view.getPaddingTop();
        if (keyboardTop - mKeyboardHeight < 0) { //如果输入框到屏幕顶部已经不能放下键盘的高度, 则不需要移动了.
            return 0;
        }
//        if (null != mShowUnderView) { //如果有基线View. 则计算基线View到屏幕的距离
//            int[] underVLocation = new int[2];
//            mShowUnderView.getLocationOnScreen(underVLocation);
//            keyboardTop = underVLocation[1] + mShowUnderView.getHeight() + mShowUnderView.getPaddingBottom() + mShowUnderView.getPaddingTop();
//        }
        //输入框或基线View的到屏幕的距离 + 键盘高度 如果 超出了屏幕的承载范围, 就需要移动.
        int moveHeight = keyboardTop + mKeyboardHeight - height;
        return moveHeight > 0 ? moveHeight : 0;
    }

    private CustomKeyboard getKeyboard(View view) {
        Object tag = view.getTag(R.id.edit_text_bind_keyboard);
        if (null != tag && tag instanceof CustomKeyboard) {
            return (CustomKeyboard) tag;
        }
        return null;
    }

    private void showSoftKeyboard(EditText view) {
        mEditText = view;
        isKeyBoardShow = true;
        CustomKeyboard keyboard = getKeyboard(view); //获取输入框所绑定的键盘BaseKeyboard
        if (null == keyboard) {
//            Log.e(TAG, "The EditText no bind CustomBaseKeyboard!");
            return;
        }
//        mKeyboardView.setEditText(view);
//        mKeyboardView.setListener(this);
        keyboard.setCurEditText(view);
        mKeyboardView.setOnKeyboardActionListener(this);
        refreshKeyboard(keyboard); //设置键盘keyboard到KeyboardView中.

        //将键盘布局加入到根布局中.
        mRootView.addView(mKeyboardViewContainer, mKeyboardViewLayoutParams);
        //设置加载动画.
//        mKeyboardViewContainer.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.down_to_up));

        int moveHeight = getMoveHeight(view);
        if (moveHeight > 0) {
            mRootView.getChildAt(0).scrollBy(0, moveHeight); //移动屏幕
        } else {
            moveHeight = 0;
        }
        view.setTag(R.id.keyboard_view_move_height, moveHeight);
    }

    private void refreshKeyboard(CustomKeyboard keyboard) {
        mKeyboardView.setKeyboard(keyboard);
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);
//        mKeyboardView.setOnKeyboardActionListener(keyboard);
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mKeyboardView.measure(width, height);
        mKeyboardHeight = mKeyboardView.getMeasuredHeight();
    }

    public void hideSoftKeyboard() {
        isKeyBoardShow = false;
        int moveHeight = 0;
        Object tag = mEditText.getTag(R.id.keyboard_view_move_height);
        if (null != tag) moveHeight = (int) tag;
        if (moveHeight > 0) { //复原屏幕
            mRootView.getChildAt(0).scrollBy(0, -1 * moveHeight);
            mEditText.setTag(R.id.keyboard_view_move_height, 0);
        }
        mRootView.removeView(mKeyboardViewContainer); //将键盘从根布局中移除.
    }

    private void hideSystemSoftKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
        try {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(editText, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isKeyBoardShow() {
        return isKeyBoardShow;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v instanceof EditText) {
            EditText attachEditText = (EditText) v;
            if (!hasFocus) {
                hideSystemSoftKeyboard(attachEditText);
                hideSoftKeyboard();
            } else {
                showSoftKeyboard(attachEditText);
            }
        }
    }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        onDealKey(primaryCode);
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    private void onDealKey(int primaryCode) {
        Editable editable = mEditText.getText();
        int start = mEditText.getSelectionStart();
        CustomKeyboard keyboard = (CustomKeyboard) mEditText.getTag(R.id.edit_text_bind_keyboard);
        KeyListener listener = null;
        if (keyboard != null) {
            listener = keyboard.getKeyListener();
        }
        String INITKEY = "-1";
        String keyvalue = INITKEY;
        switch (primaryCode) {
            case keycode_00:
                keyvalue = "00";
                break;
            case keycode_stock_num_all:// 全部
                keyvalue = "1000";
                break;
            case keycode_stock_num_half://
                keyvalue = "500";
                break;
            case keycode_stock_num_1_3:
                keyvalue = "333";
                break;
            case keycode_stock_num_1_4:
                keyvalue = "250";
                break;
            case keycode_600:// 600
                keyvalue = "600";
                break;
            case keycode_300:// 300
                keyvalue = "300";
                break;
            case keycode_000:// 000
                keyvalue = "000";
                break;
            case keycode_point:
                keyvalue = ".";
                break;
            case keycode_stock_abc:// ABC

                attachTo(mEditText, 1, listener);
                showSoftKeyboard(mEditText);
                break;
            case keycode_hide:// 隐藏
                hideSoftKeyboard();
                hideSystemSoftKeyboard(mEditText);
                break;
            case keycode_stock_123:// 123
                attachTo(mEditText, 0, listener);
                showSoftKeyboard(mEditText);
                break;
            case keycode_system_keyboard:
                onShowSystemKeyBoard();
                break;
            case keycode_delete:
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
                break;
            case keycode_clear:
                if (editable != null && editable.length() > 0) {
                    editable.clear();
                }
                break;
            case keycode_determine:
                hideSoftKeyboard();
                if (listener != null) {
                    listener.determine(mEditText);
                }
                break;
            case keycode_next:
                if (listener != null) {
                    listener.next(mEditText);
                }
                break;
            case keycode_password_123:
                attachTo(mEditText, 4, listener);
                showSoftKeyboard(mEditText);
                break;
            case keycode_password_abc:
                attachTo(mEditText, 5, listener);
                showSoftKeyboard(mEditText);
                break;
            case keycode_useless:
                break;
            default:
                keyvalue = Character.toString((char) primaryCode);
                break;
        }
        if (!INITKEY.equals(keyvalue) && editable != null) {
            editable.insert(start, keyvalue);
        }
    }

    private void onShowSystemKeyBoard() {
        hideSoftKeyboard();
        if (mEditText != null && mContext != null) {
            mEditText.requestFocus();
            InputMethodManager manager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager != null) {
                manager.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
                mEditText.setSelection(mEditText.getText().length());
            }
        }
    }

    public static abstract class KeyListener {
        public void determine(EditText editText) {
        }

        public void next(EditText editText) {
        }
    }
}
