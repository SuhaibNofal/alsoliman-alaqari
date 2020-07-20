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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.UnitUser;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.VisitResone;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitOrder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    TextView txtDate, txtTime;
    AppCompatEditText VartxtName, VartxtVisetorName;
    Spinner spinnerUnits, spinner_visit_Resone;
    Helper helper = new Helper();
    Toolbar toolbar;
    List<String> listResonVisit;
    List<String> listUnitVisit;
    HashMap<Integer, String> listResonVisitNo = new HashMap<Integer, String>();
    HashMap<Integer, String> spinnerMapUnits = new HashMap<Integer, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_visit_order);
        FindById();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        txtDate.setOnClickListener(new View.OnClickListener() {
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

    public void FindById() {
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        toolbar = findViewById(R.id.toolbar_visit);
        setSupportActionBar(toolbar);
        spinnerUnits = findViewById(R.id.spinner_Unit_visit);
        spinner_visit_Resone = findViewById(R.id.spinner_Resone_visit);
        VartxtName = (AppCompatEditText) findViewById(R.id.txtName);
        VartxtVisetorName = (AppCompatEditText) findViewById(R.id.txtVisetorName);
        listResonVisit = new ArrayList<>();
        listUnitVisit = new ArrayList<>();
        resoneViset(Integer.parseInt(Constant.ProjID));
        getUnitCustomer(Integer.parseInt(Constant.UserType), Integer.parseInt(Constant.CustID));
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
        txtDate.setText(c.get(Calendar.DAY_OF_MONTH) + "-" + MonthFormat + "-" + c.get(Calendar.YEAR));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String varMinute=String.valueOf(minute);
        if(minute<10){
            varMinute="0"+minute;
        }
        txtTime.setText(hourOfDay + ":" + varMinute);
    }

    void resoneViset(int ProjID) {
        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<VisitResone>> call = api.getVisitReasons(ProjID);
        call.enqueue(new Callback<List<VisitResone>>() {
            @Override
            public void onResponse(Call<List<VisitResone>> call, Response<List<VisitResone>> response) {

                //Toast.makeText(VisitOrder.this, response.message(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    return;
                }
                List<VisitResone> list = response.body();
                if (list.size() > 0 && list.get(0).getReasNo() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (String.valueOf(list.get(i).getReasArbName()) != "null") {
                            listResonVisit.add(String.valueOf(list.get(i).getReasArbName()));
                        } else {
                            listResonVisit.add(String.valueOf(list.get(i).getReasEngName()));
                        }

                        listResonVisitNo.put(i, String.valueOf(list.get(i).getReasNo()));
                    }

                    fillResoneVisitAndUnits(1);
                }
                //helper.HideProgressDialog();
            }

            @Override
            public void onFailure(Call<List<VisitResone>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(VisitOrder.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
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
                if (!response.isSuccessful()) {
                    return;
                }
                List<UnitUser> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    spinnerMapUnits.put(i, String.valueOf(list.get(i).getUnitNo()));
                    listUnitVisit.add(String.valueOf(list.get(i).getOprNo()));
                }
                helper.HideProgressDialog();
                fillResoneVisitAndUnits(2);
            }

            @Override
            public void onFailure(Call<List<UnitUser>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fillResoneVisitAndUnits(int id) {
        if (id == 1) {
            List<String> categories = listResonVisit;
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_visit_Resone.setAdapter(dataAdapter);
            spinner_visit_Resone.setOnItemSelectedListener(this);
        } else {
            List<String> categories = listUnitVisit;
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerUnits.setAdapter(dataAdapter);
            spinnerUnits.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.spinner_Resone_visit) {
            View v=adapterView.getSelectedView();
            ((TextView)v).setGravity(Gravity.CENTER);
        }else{
            View v=adapterView.getSelectedView();
            ((TextView)v).setGravity(Gravity.CENTER);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void SubmitVisit(View view) {
        String name = VartxtName.getText().toString();
        String visitorName = VartxtVisetorName.getText().toString();
        String idspinnerMap;
        String idSpinnerMapVisit;
        try {
            idspinnerMap = spinnerMapUnits.get(spinnerUnits.getSelectedItemPosition());
            idSpinnerMapVisit = listResonVisitNo.get(spinner_visit_Resone.getSelectedItemPosition());
            if (idspinnerMap.isEmpty() || idspinnerMap == null) {
                idspinnerMap = "0";
                idSpinnerMapVisit = "0";
            }
            if (idspinnerMap.equalsIgnoreCase(null)) {
                idspinnerMap = "0";
                idSpinnerMapVisit = "0";
            }
        } catch (Exception e) {
            idspinnerMap = "0";
            idSpinnerMapVisit = "0";
        }

        if (txtDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(VisitOrder.this, getString(R.string.VisitOrder_text_entery_date_visit), Toast.LENGTH_SHORT).show();
            return;
        }
        if (txtTime.getText().toString().trim().isEmpty()) {
            Toast.makeText(VisitOrder.this, getString(R.string.VisitOrder_text_entery_visit_order_time), Toast.LENGTH_SHORT).show();
            return;
        }
        if (helper.checkString(idspinnerMap, this)) {
            Toast.makeText(VisitOrder.this, getString(R.string.VisitOrder_text_entery_visit_order_unit_number), Toast.LENGTH_SHORT).show();
            return;
        }
        if (helper.checkString(idSpinnerMapVisit, this)) {
            Toast.makeText(VisitOrder.this, getString(R.string.VisitOrder_text_entery_visit_order_Reason_visit), Toast.LENGTH_SHORT).show();
            return;
        }
        if (helper.checkStringEmpty(name, this)) {
            Toast.makeText(VisitOrder.this, getString(R.string.VisitOrder_text_entery_visit_order_name), Toast.LENGTH_SHORT).show();
            return;
        }
        if (helper.checkStringEmpty(visitorName, this)) {
            Toast.makeText(VisitOrder.this, getString(R.string.VisitOrder_text_entery_visit_order_visit_name), Toast.LENGTH_SHORT).show();
            return;
        }
        insertRequestVisit(1, txtDate.getText().toString(), Integer.parseInt(idspinnerMap), Constant.CustID
                , visitorName, txtTime.getText().toString(), Integer.parseInt(idSpinnerMapVisit), Integer.parseInt(Constant.ProjID));
    }

    void insertRequestVisit(int ReqNo, String ReqDate, int UnitNo, String CustID, String VisitName, String VisitTime,
                            int VisitReas, int ProjID) {
        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please Wait", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<InsertResult> call = api.insertVisitRequest(ReqNo, ReqDate, UnitNo, CustID, VisitName, VisitTime, VisitReas, ProjID);
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                helper.HideProgressDialog();
               // Toast.makeText(VisitOrder.this, response.message(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(VisitOrder.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getVisitPrevious(View view) {
        Intent intent = new Intent(this, PreviouseVisitOrder.class);
        startActivity(intent);
    }
}
