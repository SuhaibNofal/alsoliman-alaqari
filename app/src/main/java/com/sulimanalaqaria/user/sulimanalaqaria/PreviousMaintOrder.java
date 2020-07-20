package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.PreviousMaintAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.MaintPreviousRequest;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.OrderSaved;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.PPreviousMaintOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviousMaintOrder extends AppCompatActivity {
    ArrayList<MaintPreviousRequest> list;
    RecyclerView rv_maint_prevous_order;
    Helper helper = new Helper();
    AppBarLayout appBarLayout;
    TextView txtTitle;
    private PreviousMaintAdapter madapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_prevuse_maint_order);
        findByID();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        mLayoutManager = new LinearLayoutManager(this);
        getPreviousRequest(Constant.UserName, Integer.parseInt(Constant.ProjID));
    }


    private void findByID() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv_maint_prevous_order = findViewById(R.id.rv_maint_prevous_order1);
        appBarLayout = findViewById(R.id.appBarLayout2);
        txtTitle = findViewById(R.id.txt_title_text);
        list = new ArrayList<MaintPreviousRequest>();
    }

    void filldata() {

        madapter = new PreviousMaintAdapter(list, this);
        rv_maint_prevous_order.setLayoutManager(mLayoutManager);
        rv_maint_prevous_order.setAdapter(madapter);
        //helper.HideProgressDialog();
    }

    void getPreviousRequest(String CreatedBy, int ProjID) {
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<MaintPreviousRequest>> call = api.getLastMaintenanceRequest(CreatedBy, ProjID);
        call.enqueue(new Callback<List<MaintPreviousRequest>>() {
            @Override
            public void onResponse(Call<List<MaintPreviousRequest>> call, Response<List<MaintPreviousRequest>> response) {
helper.HideProgressDialog();
                //Toast.makeText(PreviousMaintOrder.this, response.message(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    return;
                }
                List<MaintPreviousRequest> list1 = response.body();
                if (list1.size() > 0 && list1.get(0).getReqNo() > 0) {
                    for (int i = 0; i < list1.size(); i++) {
                        list.add(list1.get(i));
                    }
                    filldata();
                }
            }

            @Override
            public void onFailure(Call<List<MaintPreviousRequest>> call, Throwable t) {
                Toast.makeText(PreviousMaintOrder.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                helper.HideProgressDialog();
            }
        });

    }
}
