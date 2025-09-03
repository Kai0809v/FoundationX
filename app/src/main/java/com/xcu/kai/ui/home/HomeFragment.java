package com.xcu.kai.ui.home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xcu.kai.databinding.FragmentHomeBinding;
import com.xcu.kai.ui.login.LoginActivity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        TextView textView2 = binding.textHome2;
        textView2.setText("该做些什么呢");
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(toLogin);
            }
        });
        /************先放这里吧
        binding.hBtn1.setOnClickListener(v -> {
            String url = "https://ti.qq.com/qqlevel/task-center?version=1&tab=7&source=1";
            Intent task1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            //task1.setPackage("com.tencent.mtt");  QQ浏览器包名
            task1.setPackage("com.tencent.mobileqq");

            try {
                startActivity(task1);
            } catch (ActivityNotFoundException e) {
                // 如果指定应用未安装，则使用默认方式打开
                Toast.makeText(getActivity(), "失败，使用浏览器打开", Toast.LENGTH_SHORT).show();
                task1.setPackage(null);
                startActivity(task1);
            }

        });
        ****************/


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}