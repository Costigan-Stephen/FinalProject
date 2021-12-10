package com.example.finalproject.ui.contactedit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactEditViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContactEditViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is messages fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}