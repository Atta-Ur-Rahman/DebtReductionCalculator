package com.techease.debtreductioncalculator.utills;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techease.debtreductioncalculator.R;
import com.techease.debtreductioncalculator.view.activity.MainActivity;

public class Utilities {


    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static Button button;
    public static ImageView imageView;
    public static TextView textView;
    public static EditText editText;
    public static LinearLayout linearLayout;



    public static SharedPreferences.Editor putValueInEditor(Context context) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        return editor;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        //sharedPreferences = context.getSharedPreferences(Configuration.MY_PREF, 0);
        return context.getSharedPreferences(Configurations.MY_PREF, 0);
    }

    private static class Configurations {
        public static final String MY_PREF = null;
    }


    public static Fragment connectFragment(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("true").commit();

        return fragment;
    }

    public static Fragment withOutBackStackConnectFragment(Context context, Fragment fragment) {

        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fragment_container, fragment).commit();

        return fragment;
    }

    public static Button buttonDeclaration(int buttonID, View view) {
        button = (Button) view.findViewById(buttonID);
        return button;
    }

    public static ImageView imageViewDeclaration(int imageViewID, View view) {
        imageView = (ImageView) view.findViewById(imageViewID);
        return imageView;
    }

    public static TextView textViewDeclaration(int textViewID, View view) {
        textView = (TextView) view.findViewById(textViewID);

        return textView;
    }

    public static EditText editTextDeclaration(int edtihTextID, View view) {

        editText = (EditText) view.findViewById(edtihTextID);

        return editText;
    }


    public static void replaceFragmentWithAnimation(Context context,Fragment fragment, String tag){
        FragmentTransaction transaction =   ((MainActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public static void replaceWithoubackStackFragmentWithAnimation(Context context,Fragment fragment){
        FragmentTransaction transaction =   ((MainActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }


}
