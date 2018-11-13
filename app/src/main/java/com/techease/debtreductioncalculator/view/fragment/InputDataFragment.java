package com.techease.debtreductioncalculator.view.fragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
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
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputDataFragment extends Fragment {

    private View parentView;
    private Creditor_CURD creditor_curd;
    private CreditorInputAdapter creditorInputAdapter;
    private List<CreditorDataModel> creditorDataModels;
    @BindView(R.id.rv_creditor_information)
    RecyclerView rvCreditorInformation;


    @BindView(R.id.et_monthly_payment)
    EditText etMonthlyPayment;
    @BindView(R.id.tv_total_balance)
    TextView tvTotalBalance;
    @BindView(R.id.tv_total_payment)
    TextView tvTatalPayment;
    @BindView(R.id.tv_initial_snowball)
    TextView tvInitialSnowball;

    @BindView(R.id.tv_out_fragment)
    TextView tvGoToOutput;

    private String m_Text = "";



    public InputDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.fragment_input_data, container, false);



        ButterKnife.bind(this, parentView);
        creditor_curd = new Creditor_CURD(getActivity());

        editTextDailog();

        rvCreditorInformation.hasFixedSize();
        rvCreditorInformation.setLayoutManager(new LinearLayoutManager(getActivity()));

        creditorDataModels = creditor_curd.ReadCreditorInformation();
        creditorInputAdapter = new CreditorInputAdapter(getActivity(), creditorDataModels, getLayoutInflater());
        rvCreditorInformation.setAdapter(creditorInputAdapter);


        tvTotalBalance.setText(creditor_curd.SumBalence()+".00");
        tvTatalPayment.setText(creditor_curd.SumPayment()+".00");

        etMonthlyPayment.addTextChangedListener(genraltextWatcher);

        etMonthlyPayment.setText(Utilities.getSharedPreferences(getActivity()).getString("monthly_payment", ""));



        tvGoToOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.replaceFragmentWithAnimation(getActivity(),new OutputFragment(),"");
            }
        });
        return parentView;
    }

    private TextWatcher genraltextWatcher = new TextWatcher() {
        private View view;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            String strMonthlyPayment = etMonthlyPayment.getText().toString();


            Utilities.putValueInEditor(getActivity()).putString("monthly_payment", strMonthlyPayment).commit();

            if (strMonthlyPayment.equals("")) {
                etMonthlyPayment.setError("monthly payment");
            } else {
                String strPayment = creditor_curd.SumPayment();

                double monthlypayment, payment, initailsnowball;

                monthlypayment = Double.parseDouble(strMonthlyPayment);
                payment = Double.parseDouble(strPayment);

                if (payment > monthlypayment) {
                    etMonthlyPayment.setError("monthly must be greater than payment");
                } else {
                    initailsnowball = monthlypayment - payment;
                    tvInitialSnowball.setText(String.valueOf(initailsnowball));
                }


            }

        }

    };


    private void editTextDailog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Title");

// Set up the input
        final EditText input = new EditText(getActivity());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                Toast.makeText(getActivity(), m_Text, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
