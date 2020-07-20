package com.sulimanalaqaria.user.sulimanalaqaria;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowPopUP extends AppCompatActivity {
    PopupWindow Popup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_approve);
    }
}
