package com.hxzj.inputkeyboard.Keyboard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.widget.EditText;


public class CustomKeyboard extends Keyboard{

    private EditText etCurrent;
    private CustomKeyStyle customKeyStyle;
    private CustomKeyBoardManager.KeyListener keyListener;

    public CustomKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }



    public void setCurEditText(EditText etCurrent) {
        this.etCurrent = etCurrent;
    }

    public EditText getCurEditText() {
        return etCurrent;
    }


    public CustomKeyStyle getCustomKeyStyle() {
        return customKeyStyle;
    }

    public void setCustomKeyStyle(CustomKeyStyle customKeyStyle) {
        this.customKeyStyle = customKeyStyle;
    }

    public EditText getEtCurrent() {
        return etCurrent;
    }

    public void setEtCurrent(EditText etCurrent) {
        this.etCurrent = etCurrent;
    }

    public CustomKeyBoardManager.KeyListener getKeyListener() {
        return keyListener;
    }

    public void setKeyListener(CustomKeyBoardManager.KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public interface CustomKeyStyle {
        Drawable getKeyBackground(Key key, EditText etCur);

        Float getKeyTextSize(Key key, EditText etCur);

        Integer getKeyTextColor(Key key, EditText etCur);

        CharSequence getKeyLabel(Key key, EditText etCur);
    }


}