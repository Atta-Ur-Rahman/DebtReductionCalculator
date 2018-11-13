package com.techease.debtreductioncalculator.view.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.debtreductioncalculator.R;
import com.techease.debtreductioncalculator.adapter.CreditorInputAdapter;
import com.techease.debtreductioncalculator.dataBase.Creditor_CURD;
import com.techease.debtreductioncalculator.dataModel.CreditorDataModel;
import com.techease.debtreductioncalculator.utills.Utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {

    private View parentView;
    private Creditor_CURD creditor_curd;
    private CreditorInputAdapter creditorInputAdapter;
    private List<CreditorDataModel> creditorDataModels;

    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;

    private int incrementRowNumber = 0;

    @BindView(R.id.et_crediter_name_1)
    EditText etCrediterName;
    @BindView(R.id.et_balance)
    EditText etBalance;
    @BindView(R.id.et_rate)
    EditText etRate;
    @BindView(R.id.et_paymenet)
    EditText etPayment;
    @BindView(R.id.et_custom)
    EditText etCustom;

    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_next)
    TextView tvNext;

    @BindView(R.id.tv_date)
    TextView tvDate;
    private String strCreditor, strBalance, strRate, strPayment, strCustom;


    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_calculator, container, false);

        ButterKnife.bind(this, parentView);
        creditor_curd = new Creditor_CURD(getActivity());
        initCalendar();

        tvSave.setOnClickListener(this);
        tvDate.setOnClickListener(this);
        tvNext.setOnClickListener(this);


        return parentView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:


                strCreditor = etCrediterName.getText().toString();
                strBalance = etBalance.getText().toString();
                strRate = etRate.getText().toString();
                strPayment = etPayment.getText().toString();
                strCustom = etCustom.getText().toString();


                if (strCreditor.equals("")) {
                    etCrediterName.setError("enter creditor");
                } else if (strBalance.equals("")) {
                    etBalance.setError("enter balance");
                } else if (strRate.equals("")) {
                    etRate.setError("enter rate");
                } else if (strPayment.equals("")) {
                    etPayment.setError("enter payment");
                } else if (strCustom.equals("")) {
                    etCustom.setError("enter custom");
                } else {

                    incrementRowNumber = Utilities.getSharedPreferences(getActivity()).getInt("increment_row_number", 0);
                    incrementRowNumber++;
                    int incRow = incrementRowNumber;
                    Utilities.putValueInEditor(getActivity()).putInt("increment_row_number", incRow).commit();

                    creditor_curd.insertUserData(getActivity(),null, strCreditor, strBalance, strRate, strPayment, strCustom);
                }

                break;

            case R.id.tv_date:
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.tv_next:

                Utilities.replaceFragmentWithAnimation(getActivity(), new InputDataFragment(),"");

        }
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
