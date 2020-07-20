package com.sulimanalaqaria.user.sulimanalaqaria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.oppwa.mobile.connect.checkout.dialog.CheckoutActivity;
import com.oppwa.mobile.connect.checkout.meta.CheckoutSettings;
import com.oppwa.mobile.connect.exception.PaymentError;
import com.oppwa.mobile.connect.provider.Connect;
import com.oppwa.mobile.connect.provider.Transaction;
import com.oppwa.mobile.connect.provider.TransactionType;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

public class Payment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
EditText txDate;
String checkoutId="263F1AC02814715F5D2C7618A3CF2778.uat01-vm-tx02";
DatePickerDialog.OnDateSetListener mdateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        txDate=findViewById(R.id.et_payment_date);

        Set<String> paymentBrands = new LinkedHashSet<String>();

        paymentBrands.add("VISA");
        paymentBrands.add("MASTER");
        paymentBrands.add("DIRECTDEBIT_SEPA");

        CheckoutSettings checkoutSettings = new CheckoutSettings(checkoutId, paymentBrands, Connect.ProviderMode.TEST);

// Set shopper result URL

        ComponentName receiverComponentName = new ComponentName(getPackageName(), CheckoutBroadcastReceiver.class.getName());
        Intent intent = checkoutSettings.createCheckoutActivityIntent(this);

        startActivityForResult(intent, CheckoutActivity.REQUEST_CODE_CHECKOUT);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case CheckoutActivity.RESULT_OK:
                /* transaction completed */
                Transaction transaction = data.getParcelableExtra(CheckoutActivity.CHECKOUT_RESULT_TRANSACTION);

                /* resource path if needed */
                String resourcePath = data.getStringExtra(CheckoutActivity.CHECKOUT_RESULT_RESOURCE_PATH);

                if (transaction.getTransactionType() == TransactionType.SYNC) {
                    /* check the result of synchronous transaction */
                } else {
                    /* wait for the asynchronous transaction callback in the onNewIntent() */
                }

                break;
            case CheckoutActivity.RESULT_CANCELED:
                /* shopper canceled the checkout process */
                break;
            case CheckoutActivity.RESULT_ERROR:
                /* error occurred */
                PaymentError error = data.getParcelableExtra(CheckoutActivity.CHECKOUT_RESULT_ERROR);
        }
    }

    public void setDate(View view) {
        Calendar c =Calendar.getInstance();
        int year =c.get(Calendar.YEAR);
        int month =c.get(Calendar.MONTH);
        int day =c.get(Calendar.DAY_OF_MONTH);
    DatePickerDialog datePickerDialog=new DatePickerDialog(Payment.this
    ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
    ,mdateSetListener
    ,year,month,day);
    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    datePickerDialog.show();
    mdateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int month, int i2) {
            month=month+1;
            //SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
           // String currentDate = sdf.format(c.getTime());
            //Toast.makeText(Payment.this, currentDate, Toast.LENGTH_SHORT).show();
        }
    };
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
        txDate.setText(c.get(Calendar.DAY_OF_MONTH) + "/" + MonthFormat + "/" + c.get(Calendar.YEAR));
    }

    public void addDate(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.date_picker_dialog_spinner,null,false);
        DatePicker datePicker=v.findViewById(R.id.date_spinnerMode);

        int year =datePicker.getYear();
        int day =datePicker.getDayOfMonth();

    }

}
