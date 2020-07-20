package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.NotificationManager;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingRequest extends AppCompatActivity {
    Helper helper = new Helper();
    Toolbar toolbar;
    RatingBar ratingBar;
    TextView tvNameService;
    EditText etNote;
    int ReqNo;
    String ReqType;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_rating_request);
        findByID();
        Bundle extras = getIntent().getExtras();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        title = extras.getString("title").toString();
        ReqType = extras.getString("Type").toString();
        ReqNo = Integer.parseInt(extras.getString("ReqNo"));
        tvNameService.setText(title);
        switch (Integer.parseInt(ReqType)){
            case 2:
                tvNameService.setText(getString(R.string.Rating_text_detail_Maint)+" :"+" "+ReqNo);
                break;
            case 3:
                tvNameService.setText(getString(R.string.Rating_text_detail_Clean)+" :"+" "+ReqNo);
                break;
            case 5:
                tvNameService.setText(getString(R.string.Rating_text_detail_Res)+" :"+" "+ReqNo);
                break;

        }

    }

    private void findByID() {
        toolbar = findViewById(R.id.toolbar_rating);
        setSupportActionBar(toolbar);
        ratingBar = findViewById(R.id.ratingBar_rating);
        tvNameService = findViewById(R.id.tv_rating);
        etNote = findViewById(R.id.et_rating);
    }

    public void bu_rating(View view) {
        //int numStars = ratingBar.getNumStars();
        helper.ShowProgressDialog(this);
        float numStars = ratingBar.getRating();
        String note = etNote.getText().toString();
        WebApiServices api = Constant.getApi();
        Call<InsertResult> call = api.Set_RatReq(ReqNo, Integer.parseInt(Constant.ProjID), note, numStars, Integer.parseInt(Constant.CustID), Integer.parseInt(Constant.UserType), Integer.parseInt(ReqType));
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                helper.HideProgressDialog();
                if (!response.isSuccessful()) {
                    return;
                }
                Toast.makeText(RatingRequest.this, getString(R.string.Rating_its_ok), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {

                helper.HideProgressDialog();
                Toast.makeText(RatingRequest.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
