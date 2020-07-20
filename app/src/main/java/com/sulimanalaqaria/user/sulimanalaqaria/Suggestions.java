package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Suggestions extends AppCompatActivity {
    Helper helper = new Helper();
    Toolbar toolbar;
EditText et_suggestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_suggestions);
        findById();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
    }
    private void findById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_Suggestion);
        setSupportActionBar(toolbar);
        et_suggestion=findViewById(R.id.et_suggestion);
    }
    void set_suggestion(){
        String textSuggestion=et_suggestion.getText().toString();
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<InsertResult> call=api.Set_Suggestion(Constant.UserName,textSuggestion,Integer.parseInt(Constant.UserType),Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                if(!response.isSuccessful()){
                    return;
                }
                helper.HideProgressDialog();
                Toast.makeText(Suggestions.this,"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(Suggestions.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void insertSuggestion(View view) {
        set_suggestion();
    }
}
