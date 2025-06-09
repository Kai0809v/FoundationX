package com.xcu.kai.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xcu.kai.data.UserListActivity;
import com.xcu.kai.databinding.FragmentAccountBinding;
import com.xcu.kai.ui.login.LoginActivity;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;//根据对应xml文件自动生成的，fragment_account-->FragmentAccount+Binding

    public boolean loginState = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(toLogin);

                loginState = !loginState;
                binding.stateLogoutContainer.setVisibility(View.GONE);
                binding.stateLoginContainer.setVisibility(View.VISIBLE);
            }
        });
        /*
        if(!loginState){
            binding.stateLoginContainer.setVisibility(View.GONE);
            binding.stateLogoutContainer.setVisibility(View.VISIBLE);

        }if(loginState){
            binding.stateLoginContainer.setVisibility(View.VISIBLE);
            binding.stateLogoutContainer.setVisibility(View.GONE);

        }
        */
        binding.toLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginState =!loginState;
                binding.stateLoginContainer.setVisibility(View.GONE);
                binding.stateLogoutContainer.setVisibility(View.VISIBLE);

            }
        });
        binding.accountImage.setOnClickListener(v -> {
            Intent users = new Intent(getActivity(), UserListActivity.class);
            startActivity(users);

        });

        final TextView textView = binding.textAccount;
        accountViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}