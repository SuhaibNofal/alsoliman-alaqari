package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.NotificationAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.NotificationClm;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification extends AppCompatActivity {
    Helper helper = new Helper();
    Toolbar toolbar;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter madapter;
    RecyclerView recyclerView;
    ArrayList<NotificationClm> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_notification);
        findById();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");

    }

    private void Filldata() {
        madapter = new NotificationAdapter(this, list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(madapter);

    }

    private void findById() {
        toolbar = findViewById(R.id.toolbar_notification);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rv_notification);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        list = new ArrayList<NotificationClm>();
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView aTitle = view.findViewById(R.id.tv_notify_Title);
                TextView bBody = view.findViewById(R.id.tv_notify_body);
                ImageView cRating = view.findViewById(R.id.img_notify);
                String sTitle = aTitle.getTag().toString();//get Req Type and ReqNum
                String sBody = bBody.getTag().toString();//'get is close or not
                String Body = bBody.getText().toString();
                String sRating = cRating.getTag().toString();//getRating
                String s1 = sTitle.substring(sTitle.indexOf("//") + 2);//ReqNo
                String s2 = sTitle.substring(0, sTitle.indexOf("/"));//ReqType
                if (s2.trim().equals("1")) {
                    Intent intent = new Intent(getApplicationContext(), ApprovalOffersPrice.class);
                    intent.putExtra("ReqNo", s1);
                    intent.putExtra("body", bBody.getText().toString());
                    startActivity(intent);

                } else if ((s2.trim().equals("2") || s2.trim().equals("3") || s2.trim().equals("5")) && sBody.equalsIgnoreCase("true")) {
                    Intent intent = new Intent(getApplicationContext(), RatingRequest.class);
                    intent.putExtra("ReqNo", s1);
                    intent.putExtra("Type", s2);
                    intent.putExtra("title", aTitle.getText().toString());
                    startActivity(intent);

                }else if (s2.trim().equals("7")){
                    Intent intent = new Intent(getApplicationContext(), approvalLogIn.class);
                    intent.putExtra("ReqNo", "0");
                    intent.putExtra("Type", s2);
                    intent.putExtra("Body", Body);
                    intent.putExtra("title", aTitle.getText().toString());
                    startActivity(intent);
                }
                else if (s2.trim().equals("8")){
                    Intent intent = new Intent(getApplicationContext(), approvalLogIn.class);
                    intent.putExtra("ReqNo", s1);
                    intent.putExtra("Type", s2);
                    intent.putExtra("Body", Body);
                    intent.putExtra("title", aTitle.getText().toString());
                    startActivity(intent);
                }
                else if (s2.trim().equals("9")){
                    Intent intent = new Intent(getApplicationContext(), ReservationReplay.class);
                    intent.putExtra("ReqNo", "0");
                    intent.putExtra("Type", s2);
                    intent.putExtra("Body", Body);
                    intent.putExtra("title", aTitle.getText().toString());
                    startActivity(intent);
                }
                /*if (s2.trim().equals("4")){
                    Toast.makeText(getApplicationContext(), s2, Toast.LENGTH_SHORT).show();
                }*/


            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }

    private void getNotify(String CustID, int ProjID, int UserType) {
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<NotificationClm>> call = api.GetNotify(CustID, ProjID, UserType);
        call.enqueue(new Callback<List<NotificationClm>>() {
            @Override
            public void onResponse(Call<List<NotificationClm>> call, Response<List<NotificationClm>> response) {

                if (!response.isSuccessful()) {
                    return;
                }
                helper.HideProgressDialog();
                List<NotificationClm> clist = response.body();
                if (clist.size() >= 1) {
                    if (clist.get(0).getError().equalsIgnoreCase("")||clist.get(0).getError().toString().isEmpty()) {
                        list.clear();
                        for (int i = 0; i < clist.size(); i++) {
                            list.add(clist.get(i));
                        }
                        Filldata();

                    }else{
                        Toast.makeText(Notification.this, "لا يوجد اشعارات", Toast.LENGTH_SHORT).show();
                        list.clear();
                    }


                } else {
                    Toast.makeText(Notification.this, "لا يوجد اشعارات", Toast.LENGTH_SHORT).show();
                    list.clear();
                    Filldata();
                }

            }

            @Override
            public void onFailure(Call<List<NotificationClm>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(Notification.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNotify(Constant.CustID, Integer.parseInt(Constant.ProjID), Integer.parseInt(Constant.UserType));
    }
}
