package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.DetailsReservationAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.IMyViewHolderClicks;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.ReservationAvailableTime;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.DdetailReservation;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.RecyclerItemClickListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsReservation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    Helper helper = new Helper();
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    TextView txtTitle, tvDate, tvAvailabletime, tvBookingCost;
    RecyclerView rvAvailableTime;
    private RecyclerView.LayoutManager mLayoutManager;
    private DetailsReservationAdapter adapter;
    private ArrayList<ReservationAvailableTime> list;
    int VarlastPosition = -1;
    int BookingType;
    String BookAmount;
    String BookDayDate;
    String BookingName;
    String FHours = "";
    String THours = "";
PopupWindow pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_details_reservation);


        FindByID();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        BookingType = getIntent().getIntExtra("BookingType", 0);
        BookAmount = getIntent().getStringExtra("BookAmount");
        BookingName = getIntent().getStringExtra("BookingName");
        tvAvailabletime.setText(getString(R.string.detailReservation_tv_avail_time) + " " + (BookingName));
        tvBookingCost.setText(BookAmount);
        //Toast.makeText(this, String.valueOf(BookingType), Toast.LENGTH_SHORT).show();
        getAvailabletime(BookingType, Integer.parseInt(Constant.ProjID));
        BookDayDate = helper.getDateToday();
        FHours = "";
    }

    private void FindByID() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtTitle = findViewById(R.id.txt_title_text);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout2);
        tvAvailabletime = (TextView) findViewById(R.id.tv_Available_time);
        tvBookingCost = (TextView) findViewById(R.id.tv_Booking_cost);
        tvDate = (TextView) findViewById(R.id.txtDate);
        tvDate.setOnClickListener(this);
        rvAvailableTime = (RecyclerView) findViewById(R.id.rv_Available_Time);
        mLayoutManager = new LinearLayoutManager(this);
        list = new ArrayList<ReservationAvailableTime>();
        rvAvailableTime.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rvAvailableTime, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (VarlastPosition != position) {
                    //view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    RecyclerView.ViewHolder a = rvAvailableTime.findViewHolderForAdapterPosition(position);
                    if (VarlastPosition >= 0) {
                        RecyclerView.ViewHolder b = rvAvailableTime.findViewHolderForAdapterPosition(VarlastPosition);
                        b.itemView.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    }

                    a.itemView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                    VarlastPosition = position;
                } else {

                }
                TextView a = view.findViewById(R.id.tv_day);
                TextView b = view.findViewById(R.id.tv_fromHour);
                TextView c = view.findViewById(R.id.tv_ToHour);
                Log.wtf("Yes", a.getText().toString());
                Log.e("b", b.getText().toString());
                Log.e("c", c.getText().toString());
                FHours = b.getText().toString();
                THours = c.getText().toString();
                // Toast.makeText(DetailsReservation.this, a.getText().toString() + "  قم بإدخال التاريخ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));


    }

    void FillTime() {
        adapter = new DetailsReservationAdapter(list, this);
        rvAvailableTime.setLayoutManager(mLayoutManager);
        rvAvailableTime.setAdapter(adapter);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
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
        tvDate.setText(c.get(Calendar.DAY_OF_MONTH) + "-" + MonthFormat + "-" + c.get(Calendar.YEAR));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txtDate) {
            DialogFragment dialogFragment = new DatePickerFragment();
            dialogFragment.show(getSupportFragmentManager(), "date picker");
        } else {
        }
    }

    public void buRequest(View view) {
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

        PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.popup_approve, null, false), widthMeasureSpec-100, heightMeasureSpec/2, true);
        pw.showAtLocation(findViewById(R.id.detaillayout), Gravity.CENTER, 0, 0);

        /*LayoutInflater inflater = (LayoutInflater) DetailsReservation.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int x =LinearLayout.LayoutParams.MATCH_PARENT;
        //Inflate the view from a predefined XML layout
        View layout = inflater.inflate(R.layout.popup_approve, null);

       /* if (tvDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(DetailsReservation.this, getString(R.string.detailReservation_toast_enter_date), Toast.LENGTH_SHORT).show();
            return;
        }
        if (helper.checkStringEmpty(FHours.toString(), this)) {
            Toast.makeText(this, getString(R.string.detailReservation_toast_selecttime), Toast.LENGTH_SHORT).show();
            return;
        }
        //Toast.makeText(DetailsReservation.this, tvDate.getText().toString() + FHours, Toast.LENGTH_SHORT).show();
        insertReservation(1,helper.getDateToday(),Integer.parseInt(Constant.UserType),Constant.CustID,BookingType,Double.parseDouble(BookAmount)
        ,0.0,tvDate.getText().toString(),FHours,THours,Integer.parseInt(Constant.ProjID));
        //finish();*/
    }

    void getAvailabletime(int BookingType, int ProjID) {
        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please Wait", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<ReservationAvailableTime>> call = api.getReservationAvailableTime(BookingType, ProjID);
        call.enqueue(new Callback<List<ReservationAvailableTime>>() {
            @Override
            public void onResponse(Call<List<ReservationAvailableTime>> call, Response<List<ReservationAvailableTime>> response) {
               helper.HideProgressDialog();
                //Toast.makeText(DetailsReservation.this, response.message(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    return;
                }
                List<ReservationAvailableTime> list1 = response.body();
                if (list1.size() > 0 && list1.get(0).getID() > 0) {
                    for (int i = 0; i < list1.size(); i++) {
                        list.add(list1.get(i));
                    }
                    FillTime();
                }
            }

            @Override
            public void onFailure(Call<List<ReservationAvailableTime>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(DetailsReservation.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void insertReservation(int ID, String DateToday, int UserType, String UserName, int ServiceType,
                           Double BookAmount, Double PayBookAmount, String ReqDate, String ReqTime, String ReqTimeTo, int ProjID) {
        //final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please Wait", false, false);
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<InsertResult> call = api.insertReservation(ID, DateToday, UserType, UserName, ServiceType,
                BookAmount, PayBookAmount, ReqDate, ReqTime, ReqTimeTo, ProjID);
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                helper.HideProgressDialog();
               // Toast.makeText(DetailsReservation.this, response.message(), Toast.LENGTH_SHORT).show();
                if (!response.isSuccessful()) {
                    return;
                }
                InsertResult result = response.body();
                if (result.getResult().equalsIgnoreCase("True")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.detailReservation_toast_Req_done), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.detailReservation_toast_check_date), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
               helper.HideProgressDialog();
                Toast.makeText(DetailsReservation.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SubmitReservation(View view) {
        insertReservation(1,helper.getDateToday(),Integer.parseInt(Constant.UserType),Constant.CustID,BookingType,Double.parseDouble(BookAmount)
                ,0.0,tvDate.getText().toString(),FHours,THours,Integer.parseInt(Constant.ProjID));
    }
}
