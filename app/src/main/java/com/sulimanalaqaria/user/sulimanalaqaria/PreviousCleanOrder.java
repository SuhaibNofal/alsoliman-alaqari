
package com.sulimanalaqaria.user.sulimanalaqaria;


import android.os.Bundle;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.PrevuseCleanAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.CleanPreviousRequest;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.OrderSaved;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.PPreviousCleanOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviousCleanOrder extends AppCompatActivity {
    private RecyclerView mrecyclerView;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Helper helper = new Helper();
    Toolbar toolbar;
    ArrayList<CleanPreviousRequest> list;
    String intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_prevuse_clean_order);
        FindByID();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        getCleanRequestPrevious(Constant.UserName, Integer.parseInt(Constant.ProjID));
    }

    private void FindByID() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_prevues);
        setSupportActionBar(toolbar);
        mrecyclerView = findViewById(R.id.prevues_recycler);
        mrecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        list=new ArrayList<CleanPreviousRequest>();

    }

    private void filldata() {
        madapter = new PrevuseCleanAdapter(list, this);
        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.setAdapter(madapter);

    }

    void getCleanRequestPrevious(String CrertedBy, int ProjID) {
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<CleanPreviousRequest>> call = api.getLastCleanRequest(CrertedBy, ProjID);
        call.enqueue(new Callback<List<CleanPreviousRequest>>() {
            @Override
            public void onResponse(Call<List<CleanPreviousRequest>> call, Response<List<CleanPreviousRequest>> response) {
                helper.HideProgressDialog();
                if (!response.isSuccessful()) {
                    return;
                }
                List<CleanPreviousRequest> list1 = response.body();
                if (list1.size() > 0 && list1.get(0).getReqNo() > 0) {
                    for (int i = 0; i < list1.size(); i++) {
                        list.add(list1.get(i));
                    }
                    filldata();
                }
            }

            @Override
            public void onFailure(Call<List<CleanPreviousRequest>> call, Throwable t) {
                Toast.makeText(PreviousCleanOrder.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                helper.HideProgressDialog();
            }
        });

    }

}
