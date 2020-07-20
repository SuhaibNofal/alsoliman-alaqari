package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
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

public class approvalLogIn extends AppCompatActivity {
    Helper helper = new Helper();
    Toolbar toolbar;
    TextView tvTitle,tvBody;
    int ReqNo;
    String ReqType;
    String title;
    String Body;
    String ApprovID ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_approval_log_in);
        findByID();
        Bundle extras = getIntent().getExtras();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        title = extras.getString("title").toString();
        ReqType = extras.getString("Type").toString();
        ReqNo = Integer.parseInt(extras.getString("ReqNo"));
        Body=extras.getString("Body").toString();
        title.substring(59);
        ApprovID = title.substring(title.lastIndexOf(" ")+1);
        tvTitle.setText(title);
        tvBody.setText(Body);
    }
    private void findByID() {
        toolbar = findViewById(R.id.toolbar_Approv);
        setSupportActionBar(toolbar);
        tvTitle = findViewById(R.id.tv_approv_title);
        tvBody=findViewById(R.id.tv_Approv_Body);
    }

    public void bu_supmitApprove(View view) {
        if (ReqType.equalsIgnoreCase("7")) {
            helper.ShowProgressDialog(this);
            WebApiServices api = Constant.getApi();
            Call<InsertResult> call = api.Set_AprovMaintLogIn(Integer.parseInt(ApprovID), Integer.parseInt(Constant.ProjID), true, Integer.parseInt(Constant.CustID), Integer.parseInt(Constant.UserType));
            call.enqueue(new Callback<InsertResult>() {
                @Override
                public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                    helper.HideProgressDialog();
                    if (!response.isSuccessful()) {
                        return;
                    }
                    Toast.makeText(approvalLogIn.this, getString(R.string.Approv_Log_in), Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<InsertResult> call, Throwable t) {

                    helper.HideProgressDialog();
                    Toast.makeText(approvalLogIn.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (ReqType.equalsIgnoreCase("8")) {
            helper.ShowProgressDialog(this);
            WebApiServices api = Constant.getApi();
            Call<InsertResult> call = api.Set_AprovCleanLogIn(Integer.parseInt(ApprovID), Integer.parseInt(Constant.ProjID), true, Integer.parseInt(Constant.CustID), Integer.parseInt(Constant.UserType));
            call.enqueue(new Callback<InsertResult>() {
                @Override
                public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                    helper.HideProgressDialog();
                    if (!response.isSuccessful()) {
                        return;
                    }
                    Toast.makeText(approvalLogIn.this, getString(R.string.Rating_its_ok), Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<InsertResult> call, Throwable t) {

                    helper.HideProgressDialog();
                    Toast.makeText(approvalLogIn.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
