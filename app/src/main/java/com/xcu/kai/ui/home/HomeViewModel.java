package com.xcu.kai.ui.home;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Handler;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    private int index = 0;
    public MutableLiveData<String> rawText = new MutableLiveData<>();
    public MutableLiveData<String> showedText = new MutableLiveData<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    //注意上面这个Handler别导入错包了应是android.os.Handler;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void saveText(String input){
        rawText.setValue(input);
        transform(input);
    }
    private void transform(String raw){
        index = 0;
        mHandler.removeCallbacksAndMessages(null); // 清除之前的任务
        char[] rawArray = raw.toCharArray();

        // 新增递归方法逐字符显示
        Runnable showNextChar = new Runnable() {
            @Override
            public void run() {
                if (index < rawArray.length) {
                    showedText.setValue(String.valueOf(rawArray[index]));
                    index++;
                    mHandler.postDelayed(this, 100); // 每100ms触发下一次
                } else {
                    showedText.postValue(new String(rawArray)); // 最后显示完整字符串
                }
            }
        };
        mHandler.post(showNextChar); // 添加这行启动任务


    }
    public LiveData<String> showText() {
        return showedText;
    }
    /** 这个“延时”会阻塞线程 */
    private void delay(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            //不执行
        }
    }
}