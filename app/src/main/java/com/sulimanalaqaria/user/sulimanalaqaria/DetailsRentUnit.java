package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.DetailsRentUnitAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.DetailsRentUnitModel;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsRentUnit extends AppCompatActivity {
    private RecyclerView recycler_detail_rent;
    private RecyclerView.Adapter adapter;
    Toolbar toolbar;
    Helper helper = new Helper();
    ArrayList<DetailsRentUnitModel> list;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_details_rent_unit);

        findbyid();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        String xx = getIntent().getStringExtra("UnitNo");
        getUnitRentDetails(Integer.parseInt(xx));
    }

    private void getUnitRentDetails(int UnitNo) {
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<DetailsRentUnitModel>> call = api.GetinformationUnitDetails(Constant.CustID, Integer.parseInt(Constant.ProjID), UnitNo);
        call.enqueue(new Callback<List<DetailsRentUnitModel>>() {
            @Override
            public void onResponse(Call<List<DetailsRentUnitModel>> call, Response<List<DetailsRentUnitModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<DetailsRentUnitModel> list1 = response.body();
                if (list1.size() > 0 && !list1.get(0).getUnitNo().equalsIgnoreCase("")) {
                    for (int i = 0; i < list1.size(); i++) {
                        list.add(list1.get(i));
                    }
                    filldata();
                    helper.HideProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<List<DetailsRentUnitModel>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filldata() {
        adapter = new DetailsRentUnitAdapter(list, this);
        recycler_detail_rent.setLayoutManager(mLayoutManager);
        recycler_detail_rent.setAdapter(adapter);
    }

    private void findbyid() {
        toolbar = findViewById(R.id.toolbar_detailRent);
        setSupportActionBar(toolbar);
        list = new ArrayList<>();
        recycler_detail_rent = findViewById(R.id.recycler_detail_rent);
        mLayoutManager = new LinearLayoutManager(this);
    }
}
