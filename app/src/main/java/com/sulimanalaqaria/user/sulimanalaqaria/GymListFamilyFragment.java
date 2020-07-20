package com.sulimanalaqaria.user.sulimanalaqaria;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.sulimanalaqaria.user.sulimanalaqaria.Adapter.FamilyGymAdapter;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Helper;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.PFamilyHelthClubCls;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GymListFamilyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GymListFamilyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView listGym;
    FamilyGymAdapter adapterGem;
    private ArrayList<PFamilyHelthClubCls> list;
    Helper helper = new Helper();
    ArrayList gemselect = new ArrayList();
    int countMember=0;
    HashMap<Integer, Boolean> map = new HashMap<>();
    Button gym_submit;
    ArrayList <Integer>arrayListCheckTrue=new ArrayList<>();;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GymListFamilyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GymListFamilyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GymListFamilyFragment newInstance(String param1, String param2) {
        GymListFamilyFragment fragment = new GymListFamilyFragment();
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
        list = new ArrayList<PFamilyHelthClubCls>();


        getFamily(Integer.parseInt(Constant.CustID), Integer.parseInt(Constant.UserType), Integer.parseInt(Constant.ProjID));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gym_list_family,container,false);
        listGym = (ListView)v.findViewById(R.id.list_Gym);
        gym_submit =v.findViewById(R.id.button4);
        gym_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAllFamily();
                //helper.ShowProgressDialog(getActivity());
               /* for (int i = 0; i < map.size(); i++) {

                    if(map.get(list.get(i).getRowID())==true){

                        updateMemberFamily(list.get(i).getRowID());

                    }

                }*/

            }
        });
        listGym.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        map.replace(x, true);
                    }else{
                        map.put(x, true);
                    }


                }
            }
        });
//other tasks you need to do

        return v;



        //getPercentAndGender(currentDate,currentTime,x,Integer.parseInt(Constant.ProjID));
    }


    private void FillData() {

        adapterGem = new FamilyGymAdapter(getContext(), list);
        listGym.setAdapter(adapterGem);
    }

    private void getFamily(int custID, int userType, int projID) {
        //helper.ShowProgressDialog(getActivity());
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
                    //helper.HideProgressDialog();
                }
                Constant.count--;
                int x =Constant.count;
                if(Constant.count==0){
                    helper.HideProgressDialog();
                    Constant.count=3;
                }
            }

            @Override
            public void onFailure(Call<List<PFamilyHelthClubCls>> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getContext(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
    void updateAllFamily(){
        helper.ShowProgressDialog(getActivity());
        WebApiServices api =Constant.getApi();
        Call<InsertResult>call=api.Set_familyupdatefalse(Integer.parseInt(Constant.CustID),Integer.parseInt(Constant.UserType),Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                //helper.HideProgressDialog();
                if (!response.isSuccessful()) {
                    return;
                }
                updateMemperFunction();
                /*InsertResult result = response.body();
                if (result.getResult().equalsIgnoreCase("True")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.Maintenance_request_is_done), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.Maintenance_somthing_rong_not_requested), Toast.LENGTH_SHORT).show();
                }*/
                //Toast.makeText(getActivity(), getString(R.string.Maintenance_somthing_rong_not_requested), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getActivity(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
    void updateMemperFunction(){
         //countMember=map.size();

        arrayListCheckTrue.clear();
        for (int i = 0; i < map.size(); i++) {

            if(map.get(list.get(i).getRowID())==true){
                arrayListCheckTrue.add(list.get(i).getRowID());

               // updateMemberFamily(list.get(i).getRowID());

            }

        }
        countMember=arrayListCheckTrue.size();
        if (arrayListCheckTrue.size()>0){

            for (int i = 0; i < arrayListCheckTrue.size(); i++) {
                updateMemberFamily(arrayListCheckTrue.get(i));
            }
        }else{
            helper.HideProgressDialog();
        }
        //helper.HideProgressDialog();
    }
    void updateMemberFamily(int RowID){
        //helper.ShowProgressDialog(getActivity());
        WebApiServices api =Constant.getApi();
        Call<InsertResult>call=api.Set_familyAccept(Integer.parseInt(Constant.CustID),RowID,Integer.parseInt(Constant.UserType),Integer.parseInt(Constant.ProjID));
        call.enqueue(new Callback<InsertResult>() {
            @Override
            public void onResponse(Call<InsertResult> call, Response<InsertResult> response) {
                //helper.HideProgressDialog();
                if (!response.isSuccessful()) {
                    return;
                }
                countMember --;
                if (countMember==0){
                    helper.HideProgressDialog();
                    countMember=arrayListCheckTrue.size();
                }
            }

            @Override
            public void onFailure(Call<InsertResult> call, Throwable t) {
                helper.HideProgressDialog();
                Toast.makeText(getActivity(), getString(R.string.Check_Internet_Connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
