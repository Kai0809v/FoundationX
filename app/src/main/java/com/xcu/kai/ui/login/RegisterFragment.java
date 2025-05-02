package com.xcu.kai.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.xcu.kai.InputValidator;
import com.xcu.kai.R;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private TextInputEditText reAccountInput;
    private TextInputEditText rePasswordInput;
    private TextInputEditText reConfirmInput;
    private Button signUpButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 初始化输入控件，为了方便，在类下面已经声明了类型，这里直接赋值
        reAccountInput = view.findViewById(R.id.re_account);
        rePasswordInput = view.findViewById(R.id.re_password);
        reConfirmInput = view.findViewById(R.id.re_confirm_password);
        signUpButton = view.findViewById(R.id.btn_Register);
        //signUpButton.setEnabled(true);

        //初始化注册按钮
        Button signUpButton = view.findViewById(R.id.btn_Register);


        InputValidator.setupTextWatcher(
                new TextInputEditText[]{reAccountInput, rePasswordInput,reConfirmInput},
                signUpButton
        );


        TextInputLayout accountLayout = view.findViewById(R.id.RQ_account); // 获取TextInputLayout
        TextInputLayout passwordLayout = view.findViewById(R.id.RQ_password); // 获取TextInputLayout
        TextInputLayout confirmLayout = view.findViewById(R.id.RQ_confirm); // 获取TextInputLayout
        signUpButton.setOnClickListener(v -> {
            if(rePasswordInput.getText().toString().equals(reConfirmInput.getText().toString())){
                passwordLayout.setErrorEnabled(false);
                confirmLayout.setErrorEnabled(false);

                    //reAccountInput.setError("账号不能为空");
                    saveInLocal();
                    //Navigation.findNavController(v).navigate(R.id.action_register_to_login);//注册成功后跳转到登录页面


            }else{
                //passwordInput.setError("两次密码不一致");
                passwordLayout.setError(getText(R.string.tips_confirm));
                confirmLayout.setError(getString(R.string.tips_confirm));
            }
        });
        // 可以通过返回按钮或系统返回键返回登录页面
        // 系统返回键已自动处理，如需自定义返回按钮：
        /*
        view.findViewById(R.id.backButton).setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
        */

    }




    private void saveInLocal() {
        // 保存用户信息到本地
        // 这里可以使用 SharedPreferences 或其他存储方式
        //Toast.makeText(getContext(), "欸嘿，不给你注册（还没做出来）", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "欸嘿，不给你注册\n（还没做出来）", Toast.LENGTH_LONG).show();

    }
}