package com.xcu.kai.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.xcu.kai.InputValidator;
import com.xcu.kai.R;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText loAccountInput = view.findViewById(R.id.lo_account);
        TextInputEditText loPasswordInput = view.findViewById(R.id.lo_password);

        Button signUpButton = view.findViewById(R.id.btn_Register);
        Button loginButton = view.findViewById(R.id.btn_Login);
        signUpButton.setOnClickListener(v -> {
            //页面跳转page_login.xml文件中的fragment标签的id
            Navigation.findNavController(v).navigate(R.id.action_login_to_register);
        });

        InputValidator.setupTextWatcher(
                new TextInputEditText[]{loAccountInput, loPasswordInput},
                loginButton
                //上面两行分别是一个输入框数组，和一个按钮；作为参数传入
        );


    }
}
