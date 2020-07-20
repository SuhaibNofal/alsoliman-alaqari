package com.sulimanalaqaria.user.sulimanalaqaria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.GymWorkTimeAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.GymTimeModule;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.GymcPercentAndType;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GymPercentandGenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GymPercentandGenderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView tv_percent_Gym,tv_Time_Gym;
    ListView listView;
    int count=2;
    private ArrayList<GymTimeModule> list;
    GymWorkTimeAdapter timeAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Helper helper = new Helper();
    public GymPercentandGenderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GymPercentandGenderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GymPercentandGenderFragment newInstance(String param1, String param2) {
        GymPercentandGenderFragment fragment = new GymPercentandGenderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        list=new ArrayList<GymTimeModule>();
        helper.ShowProgressDialog(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gym_percent_and_gender,container,false);
        tv_percent_Gym=v.findViewById(R.id.tv_percent_Gym);
        tv_Time_Gym=v.findViewById(R.id.tv_Time_Gym);
        listView=v.findViewById(R.id.list_Gym_Work);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.US).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date());
        //Toast.makeText(getActivity(), currentDate, Toast.LENGTH_LONG).show();

        //Toast.makeText(getActivity(), currentTime, Toast.LENGTH_LONG).show();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
        Date date = null;
        int x;
        try {
            date = format.parse(currentDate);
            String dayOfTheWeek = (String) DateFormat.format("EEEE", date);
            x=ConvertDayToInteger(dayOfTheWeek);
            //Toast.makeText(this,String.valueOf(x),Toast.LENGTH_LONG).show();
        } catch (ParseException e) {
            e.printStackTrace();
            x=0;
        }
        getPercentAndGender(currentDate,currentTime,x,Integer.parseInt(Constant.ProjID));
        getWorkTime(Integer.parseInt(Constant.ProjID));
        return v;
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
    private void getPercentAndGender(String currentDate, String currentTime, int x, int parseInt) {
        //helper.ShowProgressDialog(getActivity());
        WebApiServices api = Constant.getApi();
        Call<List<GymcPercentAndType>> call = api.GetOwnerinGym(currentDate, currentTime, x,parseInt);
        call.enqueue(new Callback<List<GymcPercentAndType>>() {
            @Override
            public void onResponse(Call<List<GymcPercentAndType>> call, Response<List<GymcPercentAndType>> response) {

                if (!response.isSuccessful()) {
                    return;
                }
                List<GymcPercentAndType> list1 = response.body();
                if(list1.size()>0){
                    tv_percent_Gym.setText(list1.get(0).getPercent().toString());
                    if (list1.get(0).getGenderInGym()==1){
                        tv_Time_Gym.setText("رجال");
                    }else if (list1.get(0).getGenderInGym()==2){
                        tv_Time_Gym.setText("نساء");
                    }else{
                        tv_percent_Gym.setText("0%");
                        tv_Time_Gym.setText("لا يوجد حجز");
                    }

                }else{
                    tv_percent_Gym.setText("0%");
                    tv_Time_Gym.setText("لا يوجد حجز");
                }
                Constant.count--;
                int x =Constant.count;
                if(Constant.count==0){
                    helper.HideProgressDialog();
                    Constant.count=3;
                }


                //getFamily(Integer.parseInt(Constant.CustID), Integer.parseInt(Constant.UserType), Integer.parseInt(Constant.ProjID));
            }

            @Override
            public void onFailure(Call<List<GymcPercentAndType>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getActivity(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getWorkTime(int ProjID) {
        //helper.ShowProgressDialog(getActivity());
        WebApiServices api = Constant.getApi();
        Call<List<GymTimeModule>> call=api.GetTimeGym(Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<List<GymTimeModule>>() {
            @Override
            public void onResponse(Call<List<GymTimeModule>> call, Response<List<GymTimeModule>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                List<GymTimeModule>list1=response.body();
                if (list1.size() > 0 && list1.get(0).getDay() > 0) {
                    for (int i = 0; i < list1.size(); i++) {
                        list.add(list1.get(i));
                    }
                    FillData();
                }
                Constant.count--;
                int x =Constant.count;
                if(Constant.count==0){
                    helper.HideProgressDialog();
                    Constant.count=3;
                }
            }

            @Override
            public void onFailure(Call<List<GymTimeModule>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getActivity(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void FillData() {

        timeAdapter = new GymWorkTimeAdapter( list,getContext());
        listView.setAdapter(timeAdapter);
    }
}
