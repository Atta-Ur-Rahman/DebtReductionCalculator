package com.techease.debtreductioncalculator.dataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.techease.debtreductioncalculator.dataModel.CreditorDataModel;
import com.techease.debtreductioncalculator.utills.Utilities;
import com.techease.debtreductioncalculator.view.fragment.CalculatorFragment;
import com.techease.debtreductioncalculator.view.fragment.InputDataFragment;

import java.util.ArrayList;
import java.util.List;

public class Creditor_CURD {
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public Creditor_CURD(Context context) {
        this.context = context;

        CreditorInformationDataBase creditorInformationDataBase = new CreditorInformationDataBase(context);
        sqLiteDatabase = creditorInformationDataBase.getWritableDatabase();
    }


    public void insertUserData(Context context, String strDate,String strCreditorName, String strBalance, String strRate, String strPayment, String strCustom) {

        ContentValues values = new ContentValues();
        values.put("DATE",strDate);
        values.put("CREDITOR_NAME", strCreditorName);
        values.put("BALANCE", strBalance);
        values.put("RATE", strRate);
        values.put("PAYMENT", strPayment);
        values.put("CUSTOM", strCustom);

        sqLiteDatabase.insert("CREDITOR_TABLE", null, values);

        Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show();

        Utilities.withOutBackStackConnectFragment(context, new InputDataFragment());

    }

    @SuppressLint("ResourceAsColor")
    public List<CreditorDataModel> ReadCreditorInformation() {

        List<CreditorDataModel> list = new ArrayList<>();
        Cursor cursor = this.sqLiteDatabase.rawQuery("SELECT * FROM CREDITOR_TABLE ", null);

        if (cursor.moveToFirst()) {

            if (cursor.moveToFirst()) {
                do {

                    CreditorDataModel creditorDataModel = new CreditorDataModel();
                    creditorDataModel.setId(cursor.getString(cursor.getColumnIndex("ID")));
                    creditorDataModel.setInput_creditor(cursor.getString(cursor.getColumnIndex("CREDITOR_NAME")));
                    creditorDataModel.setInput_balance(cursor.getString(cursor.getColumnIndex("BALANCE")));
                    creditorDataModel.setInput_rate(cursor.getString(cursor.getColumnIndex("RATE")));
                    creditorDataModel.setInput_payment(cursor.getString(cursor.getColumnIndex("PAYMENT")));
                    creditorDataModel.setInput_custom(cursor.getString(cursor.getColumnIndex("CUSTOM")));

                    list.add(creditorDataModel);

                } while (cursor.moveToNext());
            }


        }
        return list;
    }

    public String SumBalence() {

        String strSum = null;

        Cursor cursors = sqLiteDatabase.rawQuery(
                "SELECT SUM(BALANCE) FROM CREDITOR_TABLE ", null);
        if (cursors.moveToFirst()) {

            CreditorDataModel creditorDataModel = new CreditorDataModel();
            strSum = creditorDataModel.setStrSum(creditorDataModel.setStrSum(cursors.getString(0)));


        }
        return strSum;
    }

    public String SumPayment() {

        String strSum = null;

        Cursor cursors = sqLiteDatabase.rawQuery(
                "SELECT SUM(PAYMENT) FROM CREDITOR_TABLE ", null);
        if (cursors.moveToFirst()) {

            CreditorDataModel creditorDataModel = new CreditorDataModel();
            strSum = creditorDataModel.setStrSum(creditorDataModel.setStrSum(cursors.getString(0)));


        }
        return strSum;
    }
}
