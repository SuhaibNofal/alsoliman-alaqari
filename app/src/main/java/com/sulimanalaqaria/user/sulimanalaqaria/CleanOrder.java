package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.CleanServices;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.CleanType;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.UnitUser;
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

public class CleanOrder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {
    Spinner spinnerClean, spinnerCleanServece, spinnerCleanUnit;
    Helper helper = new Helper();
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    TextView txtTitle, txtdate, txtTime;
    EditText txtDetails;
    PopupWindow pw;
    private TextInputLayout txtNoOFEmp, txtNoOFHours;
    private int serveceType;
    HashMap<Integer, String> spinnerMap = new HashMap<Integer, String>();
    HashMap<Integer, String> spinnerMapService = new HashMap<Integer, String>();
    HashMap<Integer, String> spinnerMapUnits = new HashMap<Integer, String>();
    List<String> spinnerArray = new ArrayList<String>();
    List<String> spinnerArrayService = new ArrayList<String>();
    List<String> spinnerArrayUnits = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;
    HashMap<Integer, String> spinnerArrayEmployment = new HashMap<Integer, String>();
    HashMap<Integer, String> spinnerArrayItems = new HashMap<Integer, String>();
    HashMap<Integer, String> spinnerArrayExpenses = new HashMap<Integer, String>();//Revenue
    HashMap<Integer, String> spinnerArrayRevenue = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        //helper.setLocale(getApplicationContext(),ShaerdPrefrenc.getShared(this, "name"));
        setContentView(R.layout.activity_clean_order);
        FindByID();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        getClean();
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

    private void FindByID() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtTitle = findViewById(R.id.txt_title_text);
        txtDetails = findViewById(R.id.et_clean_detail);
        spinnerClean = findViewById(R.id.spinner_clean_type);
        spinnerCleanServece = findViewById(R.id.spinner_clean_service);
        spinnerCleanUnit = findViewById(R.id.spinner_clean_unit);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout2);
        txtNoOFEmp = findViewById(R.id.text_input_NoOFEmp);
        txtNoOFHours = findViewById(R.id.text_input_NoOFHours);
        txtdate = findViewById(R.id.txtDateclean);
        txtTime = findViewById(R.id.txtTimeclean);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getId() == R.id.spinner_clean_type) {
            String name = spinnerClean.getSelectedItem().toString();
            String idspinnerMap = spinnerMap.get(spinnerClean.getSelectedItemPosition());
            spinnerClean.setSelection(position);
            View v = adapterView.getSelectedView();
            ((TextView) v).setGravity(Gravity.CENTER);

            if (spinnerArrayService.size() > 0) {
                spinnerArrayService.set(0, "");
                spinnerMapService.clear();
                spinnerArrayService.clear();
                dataAdapter.notifyDataSetChanged();
            }
            getCleanService(Integer.parseInt(idspinnerMap));
        } else if (adapterView.getId() == R.id.spinner_clean_service) {
            spinnerCleanServece.setSelection(position);
            View v = adapterView.getSelectedView();
            if (v == null) {

            } else {
                ((TextView) v).setGravity(Gravity.CENTER);
            }
        } else if (adapterView.getId() == R.id.spinner_clean_unit) {
            spinnerCleanUnit.setSelection(position);
            View v = adapterView.getSelectedView();
            ((TextView) v).setGravity(Gravity.CENTER);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void buSubmitclean(View view) {
        String idspinnerMap = spinnerMap.get(spinnerClean.getSelectedItemPosition());
        String idspinnerMap2 = spinnerMapService.get(spinnerCleanServece.getSelectedItemPosition());
        String idspinnerMap3 = spinnerMapUnits.get(spinnerCleanUnit.getSelectedItemPosition());
        String idspinnerMapspinnerArrayItems = spinnerArrayItems.get(spinnerCleanServece.getSelectedItemPosition());
        String idspinnerMapspinnerArrayExpenses = spinnerArrayExpenses.get(spinnerCleanServece.getSelectedItemPosition());
        String idspinnerMapspinnerArrayRevenue = spinnerArrayRevenue.get(spinnerCleanServece.getSelectedItemPosition());
        String idspinnerMapspinnerArrayEmployment = spinnerArrayEmployment.get(spinnerCleanServece.getSelectedItemPosition());

        String NoOFEmp = txtNoOFEmp.getEditText().getText().toString().trim();
        String NoOfHoure = txtNoOFHours.getEditText().getText().toString().trim();
        String txtNotes = txtDetails.getText().toString();
        String CreatedBy = Constant.UserName;
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        String checkserviceisnull = "";
        if (spinnerCleanServece.getChildCount() > 0) {
            checkserviceisnull = spinnerCleanServece.getSelectedItem().toString();
        } else {
            checkserviceisnull = "";
        }
        Boolean empno = helper.checkString(NoOFEmp, this);
        Boolean houresno = helper.checkString(NoOfHoure, this);
        String date = helper.addZeroToDayandMonth(mDay) + "-" + helper.addZeroToDayandMonth(mMonth) + "-" + mYear;
        if (!idspinnerMap.equalsIgnoreCase("") || !idspinnerMap2.equalsIgnoreCase("")
                || !idspinnerMap3.equalsIgnoreCase("") || !idspinnerMap3.equalsIgnoreCase(null)
                || !idspinnerMap3.equalsIgnoreCase("null")
                || NoOfHoure != "0" || NoOFEmp.trim() != "" || NoOfHoure.trim() != "") {
            if (checkserviceisnull == "null" || checkserviceisnull == "" || checkserviceisnull.isEmpty()) {
                Toast.makeText(this, getString(R.string.Maintenance_Service_Not_Found), Toast.LENGTH_SHORT).show();
            } else {
                if (!empno && !houresno) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    WindowManager wm =
                            (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                    Display display = wm.getDefaultDisplay();

                    int deviceWidth;
                    int deviceHight;

                    if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
                        Point size = new Point();
                        display.getSize(size);
                        deviceWidth = size.x;
                        deviceHight=size.y;
                    } else {
                        deviceWidth = display.getWidth();
                        deviceHight=display.getHeight();
                    }

                    int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.EXACTLY);
                    int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceHight, View.MeasureSpec.UNSPECIFIED);

                     View view1=inflater.inflate(R.layout.layout_cleanpopup, null, false);
                     TextView cc=view1.findViewById(R.id.tv_cleanamount);
                    pw = new PopupWindow(view1, widthMeasureSpec-100, heightMeasureSpec/2, true);

                    Double some =Double.parseDouble(idspinnerMapspinnerArrayItems)+
                    Double.parseDouble(idspinnerMapspinnerArrayExpenses)+
                    Double.parseDouble(idspinnerMapspinnerArrayRevenue)+
                    Double.parseDouble(idspinnerMapspinnerArrayEmployment);
                   Double EmpandTime=Double.parseDouble(NoOFEmp)+
                           Double.parseDouble(NoOfHoure);

                     cc.setText(String.valueOf (some*EmpandTime));
                    pw.showAtLocation(findViewById(R.id.clean_layout), Gravity.CENTER, 0, 0);
                     } else {
                    Toast.makeText(this, getString(R.string.Clean_toast_emp_and_NoOFHoures), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, getString(R.string.Maintenance_Feiled_not_completed), Toast.LENGTH_SHORT).show();
        }
    }

    private void FillspinnerData() {
        List<String> categories = spinnerArray;
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClean.setAdapter(dataAdapter);
        spinnerClean.setOnItemSelectedListener(this);
    }

    private void FillspinnerDataService() {
        List<String> categories = spinnerArrayService;
        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCleanServece.setAdapter(dataAdapter);
        spinnerCleanServece.setOnItemSelectedListener(this);
        dataAdapter.notifyDataSetChanged();
    }

    private void fillSpinnerUnits() {
        List<String> categories = spinnerArrayUnits;
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCleanUnit.setAdapter(dataAdapter);
        spinnerCleanUnit.setOnItemSelectedListener(this);
    }

    public void previousCleanOrder(View view) {
        Intent intent = new Intent(this, PreviousCleanOrder.class);
        startActivity(intent);
    }

    void getClean() {
        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<CleanType>> call = api.getCleanType(Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<List<CleanType>>() {
            @Override
            public void onResponse(Call<List<CleanType>> call, Response<List<CleanType>> response) {
                //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful())
                    return;
                List<CleanType> list = response.body();
                if (list.size() > 0 && list.get(0).getCleanTypeID() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        spinnerMap.put(i, String.valueOf(list.get(i).getCleanTypeID()));
                        spinnerArray.add(String.valueOf(list.get(i).getCleanTypeAr()));

                    }
                    FillspinnerData();
                    int userType = Integer.parseInt(Constant.UserType);
                    getUnitCustomer(userType, Integer.parseInt(Constant.CustID));
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.Clean_toast_no_clean_availabl), Toast.LENGTH_SHORT).show();
                }
                helper.HideProgressDialog();

            }

            @Override
            public void onFailure(Call<List<CleanType>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                helper.HideProgressDialog();
            }
        });

    }

    void getCleanService(int CleanType) {
        // final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<CleanServices>> call = api.getCleanService(CleanType,Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<List<CleanServices>>() {
            @Override
            public void onResponse(Call<List<CleanServices>> call, Response<List<CleanServices>> response) {
                //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful())
                    return;
                List<CleanServices> list = response.body();
                if (list.size() > 0 && list.get(0).getSID() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        spinnerMapService.put(i, String.valueOf(list.get(i).getSID()));
                        spinnerArrayService.add(String.valueOf(list.get(i).getServiceName()));
                        spinnerArrayEmployment.put(i, String.valueOf(list.get(i).getEmployment()));
                        spinnerArrayRevenue.put(i, String.valueOf(list.get(i).getRevenue()));
                        spinnerArrayExpenses.put(i, String.valueOf(list.get(i).getExpenses()));
                        spinnerArrayItems.put(i, String.valueOf(list.get(i).getItems()));
                    }
                    FillspinnerDataService();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.Maintenance_Service_Not_Found), Toast.LENGTH_SHORT).show();
                    //spinnerCleanServece.removeViewAt(0);
                }
                helper.HideProgressDialog();

            }

            @Override
            public void onFailure(Call<List<CleanServices>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                helper.HideProgressDialog();
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
                helper.HideProgressDialog();
            }

            @Override
            public void onFailure(Call<List<UnitUser>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
                helper.HideProgressDialog();
            }
        });

    }

    private void insertCleanRequest(int ReqNo, String date, String userName, int UnitNo, String Notes, int CleanTypeID,
                                    int CleanService, int NoOfEmp, Double NoOfHours, Double ValueReq, int ProjID) {
        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<InsertResult> call = api.InsertCleaRequest(ReqNo, date, userName, UnitNo, Notes, CleanTypeID, CleanService, NoOfEmp, NoOfHours
                , ValueReq, ProjID);
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                //Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    return;
                }
                InsertResult result = response.body();
                helper.HideProgressDialog();
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
        String varMinute = String.valueOf(minute);
        if (minute < 10) {
            varMinute = "0" + minute;
        }
        txtTime.setText(hourOfDay + ":" + varMinute);
    }

    public void goClean(View view) {
        String idspinnerMap = spinnerMap.get(spinnerClean.getSelectedItemPosition());
        String idspinnerMap2 = spinnerMapService.get(spinnerCleanServece.getSelectedItemPosition());
        String idspinnerMap3 = spinnerMapUnits.get(spinnerCleanUnit.getSelectedItemPosition());
        String NoOFEmp = txtNoOFEmp.getEditText().getText().toString().trim();
        String NoOfHoure = txtNoOFHours.getEditText().getText().toString().trim();
        String txtNotes = txtDetails.getText().toString();
        String CreatedBy = Constant.UserName;
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        String checkserviceisnull = "";
        if (spinnerCleanServece.getChildCount() > 0) {
            checkserviceisnull = spinnerCleanServece.getSelectedItem().toString();
        } else {
            checkserviceisnull = "";
        }
        Boolean empno = helper.checkString(NoOFEmp, this);
        Boolean houresno = helper.checkString(NoOfHoure, this);
        String date = helper.addZeroToDayandMonth(mDay) + "-" + helper.addZeroToDayandMonth(mMonth) + "-" + mYear;
        insertCleanRequest(1, date, Constant.UserName, Integer.parseInt(idspinnerMap3), txtNotes, Integer.parseInt(idspinnerMap),
                Integer.parseInt(idspinnerMap2), Integer.parseInt(NoOFEmp), Double.valueOf(NoOfHoure), 1.0, Integer.parseInt(Constant.ProjID));

    }

    public void hidclean(View view) {
      pw.dismiss();
    }
}
