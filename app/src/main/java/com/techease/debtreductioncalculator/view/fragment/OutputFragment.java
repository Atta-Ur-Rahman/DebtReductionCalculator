package com.techease.debtreductioncalculator.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.techease.debtreductioncalculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutputFragment extends Fragment implements View.OnClickListener {

    private View parentView;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;
    ;

    public OutputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_output, container, false);
    //hide action bar item
        setHasOptionsMenu(true);

        ButterKnife.bind(this, parentView);


        spinner.setItems("Snowball (Lowest Balance First)", "Avalanche (Highest Interest First)", "Order Entered In Table", "No Snowball", "Custom - Highest First", "Custom - Lowest First");


        return parentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


    ///hide actiion bar item

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_add_creditor);
        item.setVisible(false);
        menu.clear();
    }




}
