package com.sulimanalaqaria.user.sulimanalaqaria;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.RecyclerItemClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends LocalizeActivity implements View.OnClickListener {
    Helper helper = new Helper();
    NavigationView navigationView;
    Toolbar toolbar;
    SharedPreferences.Editor editor;
    TextView tvReservation, tvService, tvCleanRequest, tvMaintRequest, tvInformation, tvUserName;
    RelativeLayout rlReservation, rlService, rlCleanRequest, rlMaintRequest, rlInformation,rl_drawer_notify,rl_drawer_Helth,rl_drawer_Suggestion,rl_drawer_Comunication;
    RadioButton radioArabic, radioEnglish;
Button bures;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_main);
        creatVariable();
        if (MyAppPreference.getInstance(this).get_selected_lang().equalsIgnoreCase("en")) {
            radioEnglish.setChecked(true);
        } else if (MyAppPreference.getInstance(this).get_selected_lang().equalsIgnoreCase("ar")) {
            radioArabic.setChecked(true);
        }
        getItemfromShared();
if(Constant.UserType.equalsIgnoreCase("0")){
    rlReservation.setVisibility(View.GONE);
    rlService.setVisibility(View.GONE);
    rlCleanRequest.setVisibility(View.GONE);
    rlMaintRequest.setVisibility(View.GONE);
    //rl_drawer_notify.setVisibility(View.GONE);
    rl_drawer_Helth.setVisibility(View.GONE);
    bures.setVisibility(View.GONE);
}



    }

    private void getItemfromShared() {
        Constant.ProjID = MyAppPreference.getInstance(this).get_user_projid();
        Constant.UserType = MyAppPreference.getInstance(this).get_user_usertype();
        Constant.CustID = MyAppPreference.getInstance(this).get_user_custid();
        Constant.UserName = MyAppPreference.getInstance(this).get_user_username();
        Constant.AccNo = MyAppPreference.getInstance(this).get_user_Acc();
        tvUserName.setText(Constant.UserName);
    }

    public void creatVariable() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        radioArabic = findViewById(R.id.radioButton);
        radioEnglish = findViewById(R.id.radioButton2);
        rlReservation = findViewById(R.id.rl_drawer_reservation);
        rlService = findViewById(R.id.rl_drawer_service);
        rlCleanRequest = findViewById(R.id.rl_drawer_request_clean);
        rlMaintRequest = findViewById(R.id.rl_drawer_request_maint);
        rlInformation = findViewById(R.id.rl_drawer_Information);
        rl_drawer_notify=findViewById(R.id.rl_drawer_notify);
        rl_drawer_Helth =findViewById(R.id.rl_drawer_Helth);
        rl_drawer_Suggestion=findViewById(R.id.rl_drawer_Suggestion);
        rl_drawer_Comunication=findViewById(R.id.rl_drawer_Communication);
        rlReservation.setOnClickListener(this);
        rlService.setOnClickListener(this);
        rlCleanRequest.setOnClickListener(this);
        rlMaintRequest.setOnClickListener(this);
        rlInformation.setOnClickListener(this);
        rl_drawer_notify.setOnClickListener(this);
        rl_drawer_Helth.setOnClickListener(this);
        rl_drawer_Suggestion.setOnClickListener(this);
        rl_drawer_Comunication.setOnClickListener(this);
        tvUserName=findViewById(R.id.tv_user_name);
        bures=findViewById(R.id.bu_Main_Res);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                this.getApplicationContext().getSharedPreferences("Alsoliman", 0).edit().clear().commit();
                LogOut(Integer.parseInt(Constant.CustID),Integer.parseInt(Constant.ProjID));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void LogOut(int CustID, int ProjID) {
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<InsertResult> call=api.LogOut(CustID,ProjID);
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                helper.HideProgressDialog();
                if(!response.isSuccessful())
                    return;

                Intent intent = new Intent(getApplicationContext(), SplachScreen.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void buReservation(View view) {
        Intent goback = new Intent(MainActivity.this, Services.class);
        startActivity(goback);
    }


    void setLocale(Context ctx, String Lang) {

        String lang = Lang;
        String name = ShaerdPrefrenc.getShared(this, "name");
        if (name.equalsIgnoreCase("No name defined")) {
            ShaerdPrefrenc.saveInt(this, "name", lang);
        }
        //"No name defined" is the default value.
        Locale locale = new Locale(lang);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Configuration conf = ctx.getResources().getConfiguration();
            conf.setLocale(locale);
            conf.setLayoutDirection(new Locale(lang));
            // ctx.getResources().updateConfiguration(conf, ctx.getResources().getDisplayMetrics());
            ctx.createConfigurationContext(conf);
            Configuration systemConf = Resources.getSystem().getConfiguration();
            systemConf.setLocale(locale);
            conf.setLayoutDirection(new Locale(lang));
            //Resources.getSystem().updateConfiguration(systemConf, Resources.getSystem().getDisplayMetrics());
            Resources.getSystem().getConfiguration();
            Locale.setDefault(locale);
        } else {

            Configuration conf = ctx.getResources().getConfiguration();
            conf.locale = locale;
            conf.setLayoutDirection(new Locale(lang));
            ctx.getResources().updateConfiguration(conf, ctx.getResources().getDisplayMetrics());

            Configuration systemConf = Resources.getSystem().getConfiguration();
            systemConf.locale = locale;
            conf.setLayoutDirection(new Locale(lang));
            Resources.getSystem().updateConfiguration(systemConf, Resources.getSystem().getDisplayMetrics());
            Locale.setDefault(locale);
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }


    public void setLanguageEnglish(View view) {

        Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
        MyAppPreference.getInstance(MainActivity.this).save_selected_lang("en");
        Constant.lang = "en";
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    public void setLanguageArabic(View view) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
        MyAppPreference.getInstance(MainActivity.this).save_selected_lang("ar");
        Constant.lang = "ar";
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_drawer_reservation:
                Intent reservation = new Intent(MainActivity.this, Reservtion.class);
                startActivity(reservation);
                break;
            case R.id.rl_drawer_service:
                Intent services = new Intent(MainActivity.this, Services.class);
                startActivity(services);
                break;
            case R.id.rl_drawer_request_clean:
                Intent clean = new Intent(MainActivity.this, CleanOrder.class);
                clean.putExtra("Tag", 2);
                startActivity(clean);
                break;
            case R.id.rl_drawer_request_maint:
                Intent maint = new Intent(MainActivity.this, MaintenanceOrder.class);
                maint.putExtra("Tag", 1);
                startActivity(maint);
                break;
            case R.id.rl_drawer_Information:
                Intent information = new Intent(MainActivity.this, Information.class);
                startActivity(information);
                break;
            case R.id.rl_drawer_notify:
                Intent notify = new Intent(MainActivity.this, Notification.class);
                startActivity(notify);
                break;
            case R.id.rl_drawer_Helth:
                Intent Helth = new Intent(MainActivity.this, FamilyinGym.class);
                startActivity(Helth);
                break;
            case R.id.rl_drawer_Suggestion:
                Intent Suggestion = new Intent(MainActivity.this, Suggestions.class);
                startActivity(Suggestion);
                break;
            case R.id.rl_drawer_Communication:
                Intent Comunication = new Intent(MainActivity.this, Communication.class);
                startActivity(Comunication);
                break;
            default:


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }


}
