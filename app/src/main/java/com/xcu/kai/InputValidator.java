package com.xcu.kai;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;


/**输入验证器类，用于验证输入的有效性
 * 是否都不为空？*/
public class InputValidator {
    public static void setupTextWatcher(TextInputEditText[] inputs, Button button) {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInputs(inputs, button);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        for (TextInputEditText input : inputs) {
            input.addTextChangedListener(watcher);
        }
    }

    /***/
    private static void validateInputs(TextInputEditText[] inputs, Button button) {
        //上面传入了一个数组，和一个按钮；这里循环遍历数组中的每一个元素，如果有一个为空，就将按钮设置为不可用，跳出循环
        boolean isValid = true;
        for (TextInputEditText input : inputs) {
            if (Objects.requireNonNull(input.getText()).toString().trim().isEmpty()) {
                isValid = false;
                break;
            }
        }
        button.setEnabled(isValid);
    }
}