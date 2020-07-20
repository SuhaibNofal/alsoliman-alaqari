package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.FamilyGymAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.PFamilyHelthClubCls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FamilyinGym extends AppCompatActivity {
     Helper helper = new Helper();
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    private ArrayList<PFamilyHelthClubCls> list;
    ListView listGym;
    FamilyGymAdapter adapterGem;
    ArrayList gemselect = new ArrayList();
    HashMap<Integer, Boolean> map = new HashMap<>();
    int count = 0;
    TextView tv_percent_Gym,tv_Time_Gym;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.gym,
            R.drawable.clean,
            R.drawable.maintenance,
            R.drawable.reservations
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.FullScreen(this);
        setContentView(R.layout.activity_family_gym);
        findByID();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        addTabs(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //setupTabIcons();

        /*listGym.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int x = (int) view.getTag();
                //Toast.makeText(getApplicationContext(),String.valueOf(x),Toast.LENGTH_LONG).show();
                CheckBox c = view.findViewById(R.id.checkgym);
                //Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_LONG).show();
                if (c.isChecked()) {
                    c.setChecked(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        map.replace(x, false);
                    }else{
                        map.put(x, false);
                    }


                } else {
                    c.setChecked(true);
                    map.put(x, true);

                }
            }
        });*/


        //Date currentTime = Calendar.getInstance().getTime();

        //getPercentAndGender(currentDate,currentTime,x,Integer.parseInt(Constant.ProjID));
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(null);
        tabLayout.getTabAt(1).setIcon(null);
        /*tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);*/
    }
    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new GymPercentandGenderFragment(), "فترة الدوام");
        adapter.addFrag(new GymListFamilyFragment(), "الافراد المشتركين");

        viewPager.setAdapter(adapter);
    }

    private void findByID() {
       toolbar = findViewById(R.id.toolbar_gym);
        setSupportActionBar(toolbar);
         /*list = new ArrayList<PFamilyHelthClubCls>();
        listGym = findViewById(R.id.list_Gym);
        tv_percent_Gym=findViewById(R.id.tv_percent_Gym);
        tv_Time_Gym=findViewById(R.id.tv_Time_Gym);*/

    }

    private void FillData() {

        adapterGem = new FamilyGymAdapter(this, list);
        listGym.setAdapter(adapterGem);
    }

    private void getFamily(int custID, int userType, int projID) {
        helper.ShowProgressDialog(this);
        WebApiServices api = Constant.getApi();
        Call<List<PFamilyHelthClubCls>> call = api.GetFamelyCust(custID, userType, projID);
        call.enqueue(new Callback<List<PFamilyHelthClubCls>>() {
            @Override
            public void onResponse(Call<List<PFamilyHelthClubCls>> call, Response<List<PFamilyHelthClubCls>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<PFamilyHelthClubCls> list1 = response.body();
                if (list1.size() > 0 && list1.get(0).getOwnrNo() > 0) {
                    for (int i = 0; i < list1.size(); i++) {
                        list.add(list1.get(i));
                        map.put(list1.get(i).getRowID(),list1.get(i).isApporvlogin());

                    }
                    FillData();
                    helper.HideProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<List<PFamilyHelthClubCls>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(FamilyinGym.this, getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void gym_submit(View view) {
        //Toast.makeText(this, String.valueOf( map.size()),Toast.LENGTH_LONG).show();
        updateAllFamily();
        helper.ShowProgressDialog(this);
        for (int i = 0; i < map.size(); i++) {

            if(map.get(list.get(i).getRowID())==true){

                updateMemberFamily(list.get(i).getRowID());

            }

        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                helper.HideProgressDialog();
            }
        }, 3000);
        //helper.HideProgressDialog();
    }
    void updateAllFamily(){
        //helper.ShowProgressDialog(this);
        WebApiServices api =Constant.getApi();
        Call<InsertResult>call=api.Set_familyupdatefalse(Integer.parseInt(Constant.CustID),Integer.parseInt(Constant.UserType),Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                //helper.HideProgressDialog();
                if (!response.isSuccessful()) {
                    return;
                }
                /*InsertResult result = response.body();
                if (result.getResult().equalsIgnoreCase("True")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.Maintenance_request_is_done), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.Maintenance_somthing_rong_not_requested), Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
    void updateMemberFamily(int RowID){
//helper.ShowProgressDialog(this);
        WebApiServices api =Constant.getApi();
        Call<InsertResult>call=api.Set_familyAccept(Integer.parseInt(Constant.CustID),RowID,Integer.parseInt(Constant.UserType),Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                //helper.HideProgressDialog();
                if (!response.isSuccessful()) {
                    return;
                }
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getApplicationContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public int ConvertDayToInteger(String day){
        int dayNumber=0;
        switch (day)
        {
            case "Sunday":
                dayNumber = 1;
                break;
            case "Monday":
                dayNumber = 2;
                break;
            case "Tuesday":
                dayNumber = 3;
                break;
            case "Wednesday":
                dayNumber = 4;
                break;
            case "Thursday":
                dayNumber = 5;
                break;
            case "Friday":
                dayNumber = 6;
                break;
            case "Saturday":
            dayNumber = 7;
                break;

        }
        return dayNumber;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
