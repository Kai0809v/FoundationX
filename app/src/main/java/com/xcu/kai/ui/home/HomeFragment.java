package com.xcu.kai.ui.home;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.xcu.kai.databinding.FragmentHomeBinding;
import com.xcu.kai.ui.login.LoginActivity;

public class HomeFragment extends Fragment {

    //*************成员变量*****************//
    private FragmentHomeBinding binding;
    private boolean isExpanded = false;

    private CardView cardView;
    private ImageView ivExpand;
    //private EditText etInput;
    //private View editLayout1;
    private LinearLayout expandLayout;
    private Button saveButton;
    private TextView tvStart;
    private int initialWidth;
    private int initialHeight;
    private int targetWidth;
    private int targetHeight;

    private BottomSheetBehavior<CardView> bottomSheetBehavior;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;//局部变量，final关键字使其不可改变
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //*************成员变量初始化*****************//
//        expandableButton = binding.expandableButton;
//        mainContent = binding.mainContent;
//        expandedContent = binding.expandedContent;
//        expandIcon = binding.expandIcon;
//
//        // 添加按钮点击监听
//        expandableButton.setOnClickListener(v -> toggleCardExpansion());

        cardView = binding.cardView;
        ivExpand = binding.ivExpand;
        //etInput = binding.etInput;
        //editLayout1 = binding.etInputLayout1;
        expandLayout = binding.expandLayout;

        saveButton = binding.saveButton;//Button saveButton = ……
        tvStart = binding.tvStart;



        ivExpand.setOnClickListener(v -> toggleExpand());


        TextView textView2 = binding.textHome2;
        textView2.setText("该做些什么呢");
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(toLogin);
            }
        });

        cardView.post(() -> {
            // 初始尺寸
            initialWidth = cardView.getWidth();
            initialHeight = cardView.getHeight();

            // 目标宽度（屏幕宽度 - 32dp）
            targetWidth = getResources().getDisplayMetrics().widthPixels -
                    (int)(32 * getResources().getDisplayMetrics().density);

            /** 测量展开后的总高度（卡片高度 + 输入框高度）
             * measure(
             * (宽度) makeMeasureSpec(targetWidth, View.MeasureSpec.EXACTLY),
             * (高度) makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
             */
            cardView.measure(
                    View.MeasureSpec.makeMeasureSpec(targetWidth, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            );
            int measuredHeight = cardView.getMeasuredHeight();
            expandLayout.measure(
                    View.MeasureSpec.makeMeasureSpec(targetWidth, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            );
            //*************目标尺寸初始化*****************//
            targetHeight = measuredHeight + expandLayout.getMeasuredHeight();
        });

        tvStart.setOnClickListener(v -> {
            displayText();
        });
        saveButton.setOnClickListener(v -> {
            EditText etInput = binding.etInput;
            String inputText = etInput.getText().toString();
            homeViewModel.saveText(inputText);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //不执行
            }
        });
        Button cancelButton = binding.cancelButton;
        cancelButton.setOnClickListener(v -> {
            collapseCard();
            isExpanded = false;
        });




        return root;
    }

    private void displayText(){

        TextView showedText = binding.textShow;
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        showedText.setText("");
        homeViewModel.showText().observe(getViewLifecycleOwner(), s -> {
            showedText.append(s); // 追加显示每个字符
            if(s.length() > 1) { // 当收到完整字符串时清空
                showedText.setText(s);
            }
        });
    }

    private void toggleExpand() {
        if (isExpanded) {
            collapseCard();
        } else {
            expandCard();
        }
        isExpanded = !isExpanded;
    }

    private void expandCard() {
        // 目标尺寸：全屏宽度，高度根据内容自适应
        int targetWidth = getResources().getDisplayMetrics().widthPixels - 32;

        // ifInt(start,end)
        ValueAnimator animator = ValueAnimator.ofInt(initialWidth, targetWidth);
        animator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            float fraction = animation.getAnimatedFraction();
            ViewGroup.LayoutParams params = cardView.getLayoutParams();
            params.width = value;
            params.height = (int) (initialHeight + (targetHeight - initialHeight) * fraction);
            cardView.setLayoutParams(params);
        });
        animator.setDuration(300);
        animator.start();

        // 展开时显示输入框并旋转箭头
        expandLayout.setVisibility(View.VISIBLE);
        ivExpand.animate().rotation(180).setDuration(300).start();

        // 增加阴影效果
        cardView.setCardElevation(36);
    }

    private void collapseCard() {

        ValueAnimator animator = ValueAnimator.ofInt(targetWidth, initialWidth);
        animator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            float fraction = animation.getAnimatedFraction();
            ViewGroup.LayoutParams params = cardView.getLayoutParams();
            params.width = value;
            params.height = (int) (targetHeight - (targetHeight - initialHeight) * fraction);
            cardView.setLayoutParams(params);
        });
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
            //作用是监听到动画结束时执行操作
//                expandLayout.setVisibility(View.GONE);
//                ivExpand.animate().rotation(0).setDuration(300).start();
//            }
//        });
        ivExpand.animate().rotation(0).setDuration(300).start();
        animator.setDuration(300);
        animator.start();

        // 恢复阴影效果
        cardView.setCardElevation(6);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        isExpanded = false;
    }
}