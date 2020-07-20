package com.sulimanalaqaria.user.sulimanalaqaria;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.ServicesAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.OrderSaved;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Rreservation;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.SservisesActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Services extends AppCompatActivity {
    private RecyclerView mrecyclerView;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<SservisesActivity> list;
    Helper helper = new Helper();
    Toolbar toolbar;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        //helper.setLocale(getApplicationContext(),ShaerdPrefrenc.getShared(this, "name"));
        setContentView(R.layout.activity_services);

        creatVariable();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        mrecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        madapter = new ServicesAdapter(list, this);
        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.setAdapter(madapter);

    }

    public void creatVariable() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_Services);
        setSupportActionBar(toolbar);

        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayoutServices);
        mrecyclerView = findViewById(R.id.recyclyer_services);
        list = new ArrayList<SservisesActivity>();
        list.add(new SservisesActivity(R.drawable.maintenance1, getString(R.string.Services_text_maint), 1));
        list.add(new SservisesActivity(R.drawable.cleanblack, getString(R.string.Services_text_clean), 2));
        list.add(new SservisesActivity(R.drawable.visitor, getString(R.string.Services_text_visit), 3));
        list.add(new SservisesActivity(R.drawable.reservation, getString(R.string.Services_text_reser), 4));
    }

    public void go(View view) {

        if ((Integer) view.getTag() == 3) {
            Intent intent = new Intent(this, VisitOrder.class);
            startActivity(intent);
        } else if((Integer) view.getTag() == 1){
            Intent intent = new Intent(this, MaintenanceOrder.class);
            intent.putExtra("Tag", (Integer) view.getTag());
            startActivity(intent);
        }else if((Integer) view.getTag() == 2){
            Intent intent = new Intent(this, CleanOrder.class);
            intent.putExtra("Tag", (Integer) view.getTag());
            startActivity(intent);
        }else if((Integer) view.getTag() == 4){
            Intent intent = new Intent(this, Reservtion.class);
            intent.putExtra("Tag", (Integer) view.getTag());
            startActivity(intent);
        }


    }
}
