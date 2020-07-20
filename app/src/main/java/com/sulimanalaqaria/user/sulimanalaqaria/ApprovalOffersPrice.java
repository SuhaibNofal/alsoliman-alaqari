package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovalOffersPrice extends AppCompatActivity {
    Helper helper = new Helper();
TextView tvReqNo,value_notify;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_approval_offers_price);
        Bundle extras = getIntent().getExtras();
        FindById();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        tvReqNo.setText(extras.getString("ReqNo"));
        value_notify.setText(extras.getString("body"));
    }

    private void FindById() {
        toolbar=findViewById(R.id.toolbar_Approval_offers);
        setSupportActionBar(toolbar);
        tvReqNo=findViewById(R.id.tv_reqNo_Notifi);
        value_notify=findViewById(R.id.tv_offer_value_notify);
    }

    public void SubmitOffers(View view) {
        Intent intent=new Intent(this,PaymentInfo.class);
        intent.putExtra("ReqNo",tvReqNo.getText().toString());
        startActivity(intent);
       /* helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<InsertResult>call=api.Set_AprovOffers(Integer.parseInt(tvReqNo.getText().toString()),Integer.parseInt(Constant.ProjID),true,Integer.parseInt(Constant.CustID),Integer.parseInt(Constant.UserType));
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                if(!response.isSuccessful()){
                    return;
                }
                helper.HideProgressDialog();
                Toast.makeText(ApprovalOffersPrice.this, getString(R.string.Approval_its_ok), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                Toast.makeText(ApprovalOffersPrice.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
helper.HideProgressDialog();
            }
        });*/
    }
}
