package com.xcu.kai.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is account fragment此为账户页面\nfrom AccountViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}