package com.sulimanalaqaria.user.sulimanalaqaria;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;

import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.appbar.AppBarLayout;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.MaintServices;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.MaintType;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.UnitUser;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaintenanceOrder extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {
    Spinner spinnerMaint, spinnerServise, spinnerUnits;
    Helper helper = new Helper();
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    TextView txtTitle,txtdate,txtTime;
    EditText txtDetails;

    private int serveceType;
    HashMap<Integer, String> spinnerMap = new HashMap<Integer, String>();
    HashMap<Integer, String> spinnerMapService = new HashMap<Integer, String>();
    HashMap<Integer, String> spinnerMapUnits = new HashMap<Integer, String>();
    ArrayAdapter<String>  adapterService;
    List<String> spinnerArray = new ArrayList<String>();
    List<String> spinnerArrayService = new ArrayList<String>();
    List<String> spinnerArrayUnits = new ArrayList<String>();
    //List<String> spinnerArrayService = new ArrayList<String>();
    private String imageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        //helper.setLocale(getApplicationContext(),ShaerdPrefrenc.getShared(this, "name"));
        setContentView(R.layout.activity_maintenance_order);
        FindByID();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        serveceType = getIntent().getIntExtra("Tag", 0);
        try{
            imageName=getIntent().getStringExtra("imageName");
        }catch(Exception e){
            imageName="";
        }


        getMaint();
        int userType = Integer.parseInt(Constant.UserType);
        getUnitCustomer(userType, Integer.parseInt(Constant.CustID));
        ///getMaintService(9);
        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "date picker");
            }
        });
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new TimePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    private void FillspinnerData(int ServecType) {

            List<String> categories = spinnerArray;
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerMaint.setAdapter(dataAdapter);
            spinnerMaint.setOnItemSelectedListener(this);



    }

    private void FillspinnerDataService() {

        List<String> categories = spinnerArrayService;
        // Creating adapter for spinner
        adapterService = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        adapterService.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServise.setAdapter(adapterService);
        spinnerServise.setOnItemSelectedListener(this);
        adapterService.notifyDataSetChanged();
    }

    private void fillSpinnerUnits() {
        List<String> categories = spinnerArrayUnits;
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnits.setAdapter(dataAdapter);
        spinnerUnits.setOnItemSelectedListener(this);
    }

    private void FindByID() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtTitle = findViewById(R.id.txt_title_text);
        txtDetails = findViewById(R.id.et_maint_detail);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout2);
        spinnerMaint = findViewById(R.id.spinner);
        spinnerServise = findViewById(R.id.spinnerMaintService);
        spinnerUnits = findViewById(R.id.spinnerUnits);
        txtdate=findViewById(R.id.txtDateMaint);
        txtTime=findViewById(R.id.txtTimeMaint);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.spinner) {
            String name = spinnerMaint.getSelectedItem().toString();
            String idspinnerMap = spinnerMap.get(spinnerMaint.getSelectedItemPosition());
            spinnerMaint.setSelection(position);
            View v=parent.getSelectedView();
            ((TextView)v).setGravity(Gravity.CENTER);
            if (spinnerArrayService.size() > 0) {
                spinnerArrayService.set(0, "");
                spinnerMapService.clear();
                spinnerArrayService.clear();
                adapterService.notifyDataSetChanged();
            }
            getMaintService(Integer.parseInt(idspinnerMap));
        } else if (parent.getId() == R.id.spinnerMaintService) {
            String idspinnerMap = spinnerMapService.get(spinnerServise.getSelectedItemPosition());
            String d2d = spinnerArrayService.get(0).toString();
            View v=parent.getSelectedView();
            if(v==null){

            }else{((TextView)v).setGravity(Gravity.CENTER);}

        } else if (parent.getId() == R.id.spinnerUnits) {
            String idspinnerMap = spinnerMapService.get(spinnerServise.getSelectedItemPosition());
            View v=parent.getSelectedView();
            ((TextView)v).setGravity(Gravity.CENTER);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void byOrdermaint(View view) {
        Intent intent = new Intent(this, PreviousMaintOrder.class);
        startActivity(intent);
    }

    public void bu_maint_submit(View view) {
        String idspinnerMap = spinnerMap.get(spinnerMaint.getSelectedItemPosition());
        String idspinnerMap2 = spinnerMapService.get(spinnerServise.getSelectedItemPosition());
        String idspinnerMap3 = spinnerMapUnits.get(spinnerUnits.getSelectedItemPosition());
        String txtNotes = txtDetails.getText().toString();
        String CreatedBy = Constant.UserName;
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        String month = "0";
        String day = "0";
        String checkserviceisnull ="";
        if(spinnerServise.getChildCount()>0){
             checkserviceisnull = spinnerServise.getSelectedItem().toString();
        }else
        {
            checkserviceisnull="";
        }
        if (mMonth < 10) {
            month = "0" + mMonth;
        } else {
            month = String.valueOf(mMonth);
        }
        if (mDay < 10) {
            day = "0" + mDay;
        } else {
            day = String.valueOf(mDay);
        }
        String date = day + "-" + month + "-" + mYear;
        if (!idspinnerMap.equalsIgnoreCase("") || !idspinnerMap2.equalsIgnoreCase("")
                || !idspinnerMap3.equalsIgnoreCase("")) {
            if(checkserviceisnull=="null"||checkserviceisnull==""||checkserviceisnull.isEmpty()){
                Toast.makeText(this, getString(R.string.Maintenance_Service_Not_Found), Toast.LENGTH_SHORT).show();
            }else{
                insertRequest(1, date, Constant.UserName, Integer.parseInt(idspinnerMap3), txtNotes, Integer.parseInt(idspinnerMap),
                        Integer.parseInt(idspinnerMap2), Integer.parseInt(Constant.ProjID),imageName);
            }
        } else {
            Toast.makeText(this, getString(R.string.Maintenance_Feiled_not_completed), Toast.LENGTH_SHORT).show();
        }

    }

    private void getMaint() {

        /*final ProgressDialog loading = new ProgressDialog(this);
        loading.setProgressStyle(R.style.ProgressBar);
        loading.show();*/
        helper.ShowProgressDialog(this);
        //Creating an object of our api interface
        WebApiServices api = Constant.getApi();
        Call<List<MaintType>> call = api.getMaintType(Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<List<MaintType>>() {
            @Override
            public void onResponse(Call<List<MaintType>> call, Response<List<MaintType>> response) {
                //Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    return;
                }
                List<MaintType> list = response.body();
                if (list.size() > 0 &&list.get(0).getMainTypeID()>0) {
                for (int i = 0; i < list.size(); i++) {
                    spinnerMap.put(i, String.valueOf(list.get(i).getMainTypeID()));
                    if(Locale.getDefault().toString().contains("en")){
                        spinnerArray.add(String.valueOf(list.get(i).getMaintTypeEn()));
                    }else{
                        spinnerArray.add(String.valueOf(list.get(i).getMaintTypeAr()));
                    }

                }}
                helper.HideProgressDialog();
                FillspinnerData(serveceType);

            }

            @Override
            public void onFailure(Call<List<MaintType>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                helper.HideProgressDialog();
            }
        });
    }

    private void getMaintService(int ID) {
        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<MaintServices>> call = api.getMaintService(ID,Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<List<MaintServices>>() {
            @Override
            public void onResponse(Call<List<MaintServices>> call, Response<List<MaintServices>> response) {
                //Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    return;
                }
                List<MaintServices> list = response.body();
                if (list.size()>0&& list.get(0).getSID()>0){
                for (int i = 0; i < list.size(); i++) {
                    spinnerMapService.put(i, String.valueOf(list.get(i).getSID()));
                    spinnerArrayService.add(String.valueOf(list.get(i).getServiceName()));
                }
                    helper.HideProgressDialog();
                FillspinnerDataService();
                   // helper.HideProgressDialog();
            }else{
                    helper.HideProgressDialog();
                    Toast.makeText(getApplicationContext(), getString(R.string.Maintenance_Service_Not_Found), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<MaintServices>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUnitCustomer(int CustType, int CustID) {
        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        //helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<UnitUser>> call;
        if (CustType == 0) {
            call = api.getUnitOwner(CustID,Integer.parseInt(Constant.ProjID));
        } else {
            call = api.getUnitTenant(CustID,Integer.parseInt(Constant.ProjID));
        }
        call.enqueue(new Callback<List<UnitUser>>() {
            @Override
            public void onResponse(Call<List<UnitUser>> call, Response<List<UnitUser>> response) {

                //Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    return;
                }
                List<UnitUser> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    spinnerMapUnits.put(i, String.valueOf(list.get(i).getUnitNo()));
                    spinnerArrayUnits.add(String.valueOf(list.get(i).getOprNo()));
                }
                //helper.HideProgressDialog();
                fillSpinnerUnits();
            }

            @Override
            public void onFailure(Call<List<UnitUser>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
               helper.HideProgressDialog();
            }
        });

    }

    private void insertRequest(int ReqNo, String date, String userName, int UnitNo, String Notes, int MaintTypeID, int MaintService, int ProjID,String imageName) {
        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<InsertResult> call = api.InsertMaintRequest(ReqNo, date, userName, UnitNo, Notes,
                MaintTypeID, MaintService, ProjID,imageName);
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                //Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();
                helper.HideProgressDialog();
                if (!response.isSuccessful()) {
                    return;
                }
                InsertResult result = response.body();
                if (result.getResult().equalsIgnoreCase("True")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.Maintenance_request_is_done), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.Maintenance_somthing_rong_not_requested), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        int varMonth = month + 1;
        String MonthFormat;
        if (varMonth == 1 || varMonth == 2 || varMonth == 3 || varMonth == 4 || varMonth == 5 || varMonth == 6 || varMonth == 7 || varMonth == 8
                || varMonth == 9) {
            MonthFormat = "0" + varMonth;
        } else {
            MonthFormat = String.valueOf(varMonth);
        }
        String dateFormate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        txtdate.setText(c.get(Calendar.DAY_OF_MONTH) + "-" + MonthFormat + "-" + c.get(Calendar.YEAR));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String varMinute=String.valueOf(minute);
        if(minute<10){
            varMinute="0"+minute;
        }
        txtTime.setText(hourOfDay + ":" + varMinute);
    }

    public void goSelectImage(View view) {
        Intent intent=new Intent(this,ImageMaintSelect.class);

        startActivity(intent);
    }
}
