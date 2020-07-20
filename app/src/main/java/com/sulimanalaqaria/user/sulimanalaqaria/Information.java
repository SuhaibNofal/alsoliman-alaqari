package com.sulimanalaqaria.user.sulimanalaqaria;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.InformationAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.UserInformationData;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.VisitPreviouseRequest;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.customerInformationdetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Information extends AppCompatActivity {
    Helper helper = new Helper();
    Toolbar toolbar;
    TextView tvOprname, tvArea, tvRentAmount, tvRentEndDate, tvRentNextPay, tvAcc;
    LinearLayout liinformationtitle, liinformationfeild;
    ArrayList<customerInformationdetails> list;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_information);
        findById();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        getInformation();
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView UnitNo = view.findViewById(R.id.tv_var_Unit);
                //TextView bBody = view.findViewById(R.id.tv_notify_body);
                Intent intent=new Intent(Information.this,DetailsRentUnit.class);
                intent.putExtra("UnitNo",UnitNo.getTag().toString());
                startActivity(intent);
            }
        });*/
    }

    private void findById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_infor);
        setSupportActionBar(toolbar);
        listView=findViewById(R.id.list_information);
        list=new ArrayList<>();
        /*tvOprname = findViewById(R.id.tv_info_unit);
        tvArea = findViewById(R.id.tv_info_area);
        tvAcc = findViewById(R.id.tv_info_acc);
        tvRentAmount = findViewById(R.id.tv_info_rent_amount);
        tvRentEndDate = findViewById(R.id.tv_info_end_rent_date);
        tvRentNextPay = findViewById(R.id.tv_info_next_pay);
        liinformationtitle = findViewById(R.id.li_information_rent_title);
        liinformationfeild = findViewById(R.id.li_information_rent_feild);*/
    }


   /* void getInformation() {
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<UserInformationData>> call;
        if (Constant.UserType.equalsIgnoreCase("0"))
            call = api.getInformationCust(Constant.CustID, Integer.parseInt(Constant.ProjID), Constant.AccNo);
        else
            call = api.getInformationTenant(Constant.CustID, Integer.parseInt(Constant.ProjID), Constant.AccNo);

        call.enqueue(new Callback<List<UserInformationData>>() {
            @Override
            public void onResponse(Call<List<UserInformationData>> call, Response<List<UserInformationData>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<UserInformationData> list = response.body();
                if (list.size() > 0 && list.get(0).getProjID() != 0) {

                    tvOprname.setText(list.get(0).getOprNo());
                    tvArea.setText(String.valueOf(list.get(0).getNetArea()));
                    tvAcc.setText(list.get(0).getBalance());
                    if (Constant.UserType.equalsIgnoreCase("0")) {
                        liinformationfeild.setVisibility(View.INVISIBLE);
                        liinformationtitle.setVisibility(View.INVISIBLE);
                    } else {
                        liinformationfeild.setVisibility(View.VISIBLE);
                        liinformationtitle.setVisibility(View.VISIBLE);
                        tvRentAmount.setText(String.valueOf(list.get(0).getRentAmt()));
                        tvRentEndDate.setText(list.get(0).getRentEndDate());
                        tvRentNextPay.setText(list.get(0).getNextPayDate());
                    }
                }
                helper.HideProgressDialog();
            }

            @Override
            public void onFailure(Call<List<UserInformationData>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(Information.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
    }*/
   void getInformation() {
       helper.ShowProgressDialog(this);
       WebApiServices api = Constant.getApi();
       Call<List<customerInformationdetails>> call;
       if (Constant.UserType.equalsIgnoreCase("0"))
           call = api.GetinformationCustUnit(Constant.CustID, Integer.parseInt(Constant.ProjID), Constant.AccNo);
       else
           call = api.GetinformationTenantUnit(Constant.CustID, Integer.parseInt(Constant.ProjID), Constant.AccNo);

     call.enqueue(new Callback<List<customerInformationdetails>>() {
         @Override
         public void onResponse(Call<List<customerInformationdetails>> call, Response<List<customerInformationdetails>> response) {
             if(!response.isSuccessful()){
                 return;
             }
             List<customerInformationdetails>list1=response.body();
             if (list1.size()>0&&!list1.get(0).getOprNo().equalsIgnoreCase("")){
                 for (int i = 0; i < list1.size(); i++) {
                     list.add(list1.get(i));
                 }
                 filldata();
                 helper.HideProgressDialog();
             }
             helper.HideProgressDialog();
         }

         @Override
         public void onFailure(Call<List<customerInformationdetails>> call, Throwable t) {
helper.HideProgressDialog();
             Toast.makeText(Information.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

         }
     });
   }

    private void filldata() {

        InformationAdapter adapter=new InformationAdapter(list,this, Integer.parseInt(Constant.UserType));
        listView.setAdapter(adapter);
    }
}
