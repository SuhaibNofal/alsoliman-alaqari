package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.util.Objects;

public class Communication extends AppCompatActivity {
LinearLayout liMarbella,liSukina;
    Helper helper = new Helper();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_communication);
        findById();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        if (Constant.ProjID=="1"){
            liMarbella.setVisibility(View.VISIBLE);
            liSukina.setVisibility(View.GONE);
        }else {
            liMarbella.setVisibility(View.GONE);
            liSukina.setVisibility(View.VISIBLE);
        }
    }
   void findById(){
       toolbar = (Toolbar) findViewById(R.id.toolbar_communication);
       setSupportActionBar(toolbar);
        liMarbella=findViewById(R.id.linearLayoutMarbilla);
        liSukina=findViewById(R.id.linearLayoutSukina);
   }


}
