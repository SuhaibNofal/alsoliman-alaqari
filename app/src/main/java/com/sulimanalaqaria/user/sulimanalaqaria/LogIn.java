package com.sulimanalaqaria.user.sulimanalaqaria;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.LogInInformation;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogIn extends LocalizeActivity {
    Helper helper = new Helper();
    private TextInputLayout txtUserName, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_log_in);
        txtUserName = findViewById(R.id.text_input_username);
        txtPassword = findViewById(R.id.text_input_pass);
        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        /*Settings.Secure.getString(getApplicationContext().getContentResolver(),
                'Settings.Secure.ANDROID_ID);*/
        System.out.print(android_id);
    }

    private boolean validateUserName() {
        String userNameInput = txtUserName.getEditText().getText().toString().trim();

        if (userNameInput.isEmpty()) {
            txtUserName.setError(getString(R.string.error_Message_UserName));
            return false;
        } else if (userNameInput.length() > 10) {
            txtUserName.setError(getString(R.string.error_Message_UserName_toLong));
            return false;
        } else {
            txtUserName.setError(null);
            txtUserName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = txtPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            txtPassword.setError(getString(R.string.error_Message_Passworld));
            return false;
        } else {
            txtPassword.setError(null);
            txtPassword.setErrorTextColor(ColorStateList.valueOf(Color.BLUE));
            txtPassword.setErrorEnabled(false);

            return true;
        }
    }

    public void confirmData(View view) {
        if (!validateUserName() | !validatePassword()) {
            return;
        } else {
            getCustomer(txtUserName.getEditText().getText().toString().trim(), txtPassword.getEditText().getText().toString().trim());
        }

    }

    private void getCustomer(String username, String pass) {
        //While the app fetched data we are displaying a progress dialog
        helper.ShowProgressDialog(this);
        // final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Creating an object of our api interface
        WebApiServices api = adapter.create(WebApiServices.class);
        Call<List<LogInInformation>> call = api.getLogInInformation(username, pass);
        call.enqueue(new Callback<List<LogInInformation>>() {
            @Override
            public void onResponse(Call<List<LogInInformation>> call, Response<List<LogInInformation>> response) {
                //Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    return;
                }
                List<LogInInformation> list = response.body();
                //loading.dismiss();
                helper.HideProgressDialog();
                if (list.get(0).getErorr() == "" || list.get(0).getErorr().isEmpty()) {
                    Boolean active = list.get(0).getUserActive();
                    Constant.ProjID = list.get(0).getProjID().toString();
                    Constant.UserType = list.get(0).getUserType().toString();
                    Constant.CustID = list.get(0).getCustID().toString();
                    Constant.UserName = list.get(0).getUserName().toString();
                    Constant.AccNo = list.get(0).getAccNo().toString();


                    if (active) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), getString(R.string.Log_in_ًWelcom_To_Alsoliman), Toast.LENGTH_SHORT).show();
                        finish();
                        MyAppPreference.getInstance(LogIn.this).save_user_details(Constant.ProjID, Constant.UserType, Constant.CustID, Constant.UserName, Constant.AccNo);
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.Log_in_User_is_Not_Active), Toast.LENGTH_SHORT).show();
                    }
                    FirebaseInstanceId.getInstance().getInstanceId()
                            .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                @Override
                                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                    if (!task.isSuccessful()) {
                                        Log.w("InstanceID", "getInstanceId failed", task.getException());
                                        return;
                                    }
                                    // Get new Instance ID token
                                    String token = task.getResult().getToken();
                                    // Log and toast
                                    // String msg = getString(R.string.msg_token_fmt, token);
                                    //Log.d("TAG", msg);
                                    //Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                                    sendTokentoServer(Constant.CustID, token, Constant.ProjID, Constant.UserType);
                                }
                            });
                    if (Integer.parseInt(Constant.ProjID) == 1) {
                        FirebaseMessaging.getInstance().subscribeToTopic("marbella")
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        String msg = "msg_subscribed";
                                        if (!task.isSuccessful()) {
                                            msg = "msg_subscribe_failed";
                                        }
                                        Log.d("", msg);
                                        //Toast.makeText(LogIn.this, msg, Toast.LENGTH_SHORT).show();
                                    }
                                });

                    } else if(Integer.parseInt(Constant.ProjID) == 2){
                        FirebaseMessaging.getInstance().subscribeToTopic("Sakenah")
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        String msg = "msg_subscribed";
                                        if (!task.isSuccessful()) {
                                            msg = "msg_subscribe_failed";
                                        }
                                        Log.d("", msg);
                                        //Toast.makeText(LogIn.this, msg, Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }


                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.Log_in_Faild_User), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<LogInInformation>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                String s = t.getMessage();
                String r = "xs";

            }
        });


    }

    void sendTokentoServer(String CustID, String DeviceToken, String ProjID, String UserType) {
        WebApiServices api = Constant.getApi();
        Call<InsertResult> call = api.SendToken(Integer.parseInt(CustID), DeviceToken, Integer.parseInt(ProjID), Integer.parseInt(UserType));
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                if (!response.isSuccessful())
                    return;
                //Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {

            }
        });


    }
}
