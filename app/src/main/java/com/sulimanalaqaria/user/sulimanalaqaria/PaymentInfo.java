package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.oppwa.mobile.connect.checkout.dialog.CheckoutActivity;
import com.oppwa.mobile.connect.checkout.meta.CheckoutSettings;
import com.oppwa.mobile.connect.provider.Connect;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentInfo extends AppCompatActivity {
Helper helper =new Helper();
    int ReqNo;
    Toolbar toolbar;
    RadioGroup radioGroup;
    RadioButton radioPayReception,radioPayVisa;
    int payWay =0;
    String checkoutId="263F1AC02814715F5D2C7618A3CF2778.uat01-vm-tx02";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_payment_info);
        findByID();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        String txtReqNo=getIntent().getStringExtra("ReqNo");
        ReqNo= Integer.parseInt(txtReqNo);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(i)
                {
                    case R.id.radioPaywhenRecieving:
                        payWay=1;
                        break;
                    case R.id.radioPayVisa:
                        payWay=2;
                        break;
                }
            }
        });
    }

    void findByID() {
        toolbar = findViewById(R.id.toolbar_Payment);
        setSupportActionBar(toolbar);
        radioGroup = findViewById(R.id.radioGroupPayment);
        radioPayReception = findViewById(R.id.radioPaywhenRecieving);
        radioPayVisa = findViewById(R.id.radioPayVisa);

    }

    public void PaymentSubmitOffers(View view) {
        if(payWay==1){
            helper.ShowProgressDialog(this);
            WebApiServices api = Constant.getApi();
            Call<InsertResult> call=api.Set_AprovOffers(ReqNo,Integer.parseInt(Constant.ProjID),true,Integer.parseInt(Constant.CustID),Integer.parseInt(Constant.UserType));
            call.enqueue(new Callback<InsertResult>() {
                @Override
                public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                    if(!response.isSuccessful()){
                        return;
                    }
                    helper.HideProgressDialog();
                    Toast.makeText(PaymentInfo.this, getString(R.string.Approval_its_ok), Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<InsertResult> call, Throwable t) {
                    Toast.makeText(PaymentInfo.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                    helper.HideProgressDialog();
                }
            });
        }else if(payWay==2) {

            Intent intent =new Intent(this,checkout_ui.class);
            startActivity(intent);
        }

    }

}
