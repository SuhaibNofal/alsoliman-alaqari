package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.PreviouseVisitAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.VisitPreviouseRequest;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviouseVisitOrder extends AppCompatActivity {
    Helper helper = new Helper();
    private RecyclerView mrecyclyer;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    ArrayList<VisitPreviouseRequest> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_previouse_visit_order);
        FindByID();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        getPreviousVisit(Constant.CustID, Integer.parseInt(Constant.ProjID));

    }

    private void FindByID() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mrecyclyer = findViewById(R.id.rv_Peviouse_visit);
        layoutManager = new LinearLayoutManager(this);
        mrecyclyer.setHasFixedSize(true);
        list = new ArrayList<>();
    }

    private void fillData() {
        adapter = new PreviouseVisitAdapter(list, this);
        mrecyclyer.setLayoutManager(layoutManager);
        mrecyclyer.setAdapter(adapter);
    }

    void getPreviousVisit(String CustID, int ProjID) {
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<VisitPreviouseRequest>> call = api.getPrevVisit(CustID, ProjID);
        call.enqueue(new Callback<List<VisitPreviouseRequest>>() {
            @Override
            public void onResponse(Call<List<VisitPreviouseRequest>> call, Response<List<VisitPreviouseRequest>> response) {
                Toast.makeText(PreviouseVisitOrder.this, response.message(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    return;
                }
                List<VisitPreviouseRequest> list1 = response.body();
                if (list1.size() > 0 && list1.get(0).getReqNo() > 0) {
                    for (int i = 0; i < list1.size(); i++) {
                        list.add(list1.get(i));
                    }
                }
                helper.HideProgressDialog();
                fillData();
            }

            @Override
            public void onFailure(Call<List<VisitPreviouseRequest>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(PreviouseVisitOrder.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
