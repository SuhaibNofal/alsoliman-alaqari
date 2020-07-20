package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.util.Objects;

public class ReservationReplay extends AppCompatActivity {
    Helper helper = new Helper();
    Toolbar toolbar;
    TextView tvTitle,tvBody,tv_reqNo;
    int ReqNo;
    String ReqType;
    String title;
    String Body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_reservation_replay);
        findbyid();
        Bundle extras = getIntent().getExtras();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        title = extras.getString("title").toString();
        ReqType = extras.getString("Type").toString();
        ReqNo = Integer.parseInt(extras.getString("ReqNo"));
        Body=extras.getString("Body").toString();
        tv_reqNo.setText(ReqNo);
    }
    void findbyid(){
        toolbar=findViewById(R.id.toolbar_Res_Replay);
        setSupportActionBar(toolbar);
        tv_reqNo=findViewById(R.id.tv_res_req_no_var);
    }
}
