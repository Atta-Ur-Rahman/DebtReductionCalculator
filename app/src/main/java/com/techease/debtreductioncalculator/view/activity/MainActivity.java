package com.techease.debtreductioncalculator.view.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.techease.debtreductioncalculator.R;
import com.techease.debtreductioncalculator.dataBase.Creditor_CURD;
import com.techease.debtreductioncalculator.utills.Utilities;
import com.techease.debtreductioncalculator.view.fragment.CalculatorFragment;
import com.techease.debtreductioncalculator.view.fragment.InputDataFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private Dialog creditorAddDailog;
    private TextView tvDate;
    private EditText etCreditorName, etBalance, etRate, etPayment, etCustom;
    private Button btnSave;
    private String strDate, strCreditor, strBalance, strRate, strPayment, strCustom;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;

    private Creditor_CURD creditor_curd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creditor_curd = new Creditor_CURD(this);


        Utilities.replaceWithoubackStackFragmentWithAnimation(this, new InputDataFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.creditor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add_creditor:
                creditorAddDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void creditorAddDialog() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final View dialogView = getLayoutInflater().inflate(R.layout.creditor_add_layout, null);
        dialogView.setBackgroundResource(android.R.color.transparent);
        dialogBuilder.setView(dialogView);
        creditorAddDailog = dialogBuilder.create();

        ImageView ivCreditorCross = dialogView.findViewById(R.id.iv_creditor_cross);
        tvDate = dialogView.findViewById(R.id.tv_date);
        etCreditorName = dialogView.findViewById(R.id.et_crediter_name);
        etBalance = dialogView.findViewById(R.id.et_balance);
        etRate = dialogView.findViewById(R.id.et_rate);
        etPayment = dialogView.findViewById(R.id.et_paymenet);
        etCustom = dialogView.findViewById(R.id.et_custom);
        btnSave = dialogView.findViewById(R.id.btn_save);
        ivCreditorCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creditorAddDailog.dismiss();
            }
        });

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCalendar();

                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strDate = tvDate.getText().toString();
                strCreditor = etCreditorName.getText().toString();
                strBalance = etBalance.getText().toString();
                strRate = etRate.getText().toString();
                strPayment = etPayment.getText().toString();
                strCustom = etCustom.getText().toString();


                if (strDate.equals("")) {
                    tvDate.setError("emter date");
                } else if (strCreditor.equals("")) {
                    etCreditorName.setError("enter creditor");
                } else if (strBalance.equals("")) {
                    etBalance.setError("enter balance");
                } else if (strRate.equals("")) {
                    etRate.setError("enter rate");
                } else if (strPayment.equals("")) {
                    etPayment.setError("enter payment");
                } else if (strCustom.equals("")) {
                    etCustom.setError("enter custom");
                } else {


                    creditor_curd.insertUserData(MainActivity.this, strDate, strCreditor, strBalance, strRate, strPayment, strCustom);
                    creditorAddDailog.dismiss();
                }
            }
        });


        creditorAddDailog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //style id
        creditorAddDailog.show();
        creditorAddDailog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {


                return false;
            }
        });
    }


    private void initCalendar() {

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }

        };
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tvDate.setText(sdf.format(myCalendar.getTime()));
    }
}
