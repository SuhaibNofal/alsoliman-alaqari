package com.sulimanalaqaria.user.sulimanalaqaria;


import android.os.Bundle;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.appbar.AppBarLayout;
import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.ReservationAdapterRecycler;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.ReservatinAvailable;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.isinBlacklist;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reservtion extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ReservatinAvailable> list;
    Helper helper = new Helper();
    Toolbar toolbar;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        //helper.setLocale(getApplicationContext(),ShaerdPrefrenc.getShared(this, "name"));
        setContentView(R.layout.activity_reservtion);
        creatVariable();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        mrecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        final float y = mrecyclerView.getY();

        //getReservationAvailable(Integer.parseInt(MyAppPreference.getInstance(this).get_user_projid()));
        checkuserBlacklist();
    }

    void FillData() {
        mAdapter = new ReservationAdapterRecycler(list, this);
        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.setAdapter(mAdapter);
        //Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
    }

    public void creatVariable() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        mrecyclerView = findViewById(R.id.recycler_reservation);
        list = new ArrayList<ReservatinAvailable>();
    }

    void getReservationAvailable(int ProjID) {
        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please Wait", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<ReservatinAvailable>> call = api.getReservationAvailable(ProjID);
        call.enqueue(new Callback<List<ReservatinAvailable>>() {
            @Override
            public void onResponse(Call<List<ReservatinAvailable>> call, Response<List<ReservatinAvailable>> response) {
                //Toast.makeText(Reservtion.this, response.message(), Toast.LENGTH_SHORT).show();

                if (!response.isSuccessful()) {
                    return;
                }

                List<ReservatinAvailable> list1 = response.body();
                if (list1.size() > 0 && !list1.get(0).getID().equalsIgnoreCase("0")) {
                    for (int i = 0; i < list1.size(); i++) {
                        list.add(list1.get(i));
                    }

                    FillData();
                }else{Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_SHORT).show();}
                helper.HideProgressDialog();

            }

            @Override
            public void onFailure(Call<List<ReservatinAvailable>> call, Throwable t) {
               helper.HideProgressDialog();
                Toast.makeText(Reservtion.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });

    }
    void checkuserBlacklist() {

        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please Wait", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<isinBlacklist>> call = api.GetinBlacklist(Integer.parseInt(Constant.CustID),Integer.parseInt(Constant.UserType),Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<List<isinBlacklist>>() {
            @Override
            public void onResponse(Call<List<isinBlacklist>> call, Response<List<isinBlacklist>> response) {
                List<isinBlacklist> list1 = response.body();
                Boolean x;
                if (list1.size() > 0 && !list1.get(0).isBlackList()==true) {
                    x=false;
                }else{
                    x=true;
                }
                helper.HideProgressDialog();
                SupmetBlack(x);
            }

            @Override
            public void onFailure(Call<List<isinBlacklist>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(Reservtion.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void SupmetBlack(Boolean x) {
        if (x){
            //Toast.makeText(this, "تم ايقاف خدمة حجز المرافق لسوء استخدامك للمرفق", Toast.LENGTH_SHORT).show();
        }else{
            getReservationAvailable(Integer.parseInt(MyAppPreference.getInstance(this).get_user_projid()));
        }
    }

}
