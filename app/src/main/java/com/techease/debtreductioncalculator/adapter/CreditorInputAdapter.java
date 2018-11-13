package com.techease.debtreductioncalculator.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.debtreductioncalculator.R;
import com.techease.debtreductioncalculator.dataModel.CreditorDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreditorInputAdapter extends RecyclerView.Adapter<CreditorInputAdapter.ViewHolder> {


    private Context context;
    private List<CreditorDataModel> creditorDataModels;
    private LayoutInflater layoutInflater;
    private CreditorDataModel creditorDataModel;
    private boolean bItemNumber = true;

    public CreditorInputAdapter(Context context, List<CreditorDataModel> creditorDataModels, LayoutInflater layoutInflater) {
        this.context = context;
        this.creditorDataModels = creditorDataModels;
        this.layoutInflater = layoutInflater;
    }


    @NonNull
    @Override
    public CreditorInputAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.input_creditor_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditorInputAdapter.ViewHolder viewHolder, int i) {



        creditorDataModel = creditorDataModels.get(i);

        viewHolder.tvCreditor.setText(creditorDataModel.getInput_creditor());
        viewHolder.tvBalance.setText(creditorDataModel.getInput_balance() + ".00");
        viewHolder.tvRate.setText(creditorDataModel.getInput_rate() + ".00%");
        viewHolder.tvPayment.setText(creditorDataModel.getInput_payment() + ".00");
        viewHolder.tvCustom.setText(creditorDataModel.getInput_custom());

        final int finalI = i;
        viewHolder.llCreditorInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, creditorDataModels.get(finalI).getId(), Toast.LENGTH_SHORT).show();
            }
        });

        ///increment the row number by get index position
        i++;
        viewHolder.tvRowNumber.setText(String.valueOf(i));
    }

    @Override
    public int getItemCount() {
        return creditorDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRowNumber, tvCreditor, tvBalance, tvRate, tvPayment, tvCustom;

        private LinearLayout llCreditorInformation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRowNumber = itemView.findViewById(R.id.tv_row_number);
            tvCreditor = itemView.findViewById(R.id.tv_creditor);
            tvBalance = itemView.findViewById(R.id.tv_balance);
            tvRate = itemView.findViewById(R.id.tv_rate);
            tvPayment = itemView.findViewById(R.id.tv_payment);
            tvCustom = itemView.findViewById(R.id.tv_custom);

            llCreditorInformation=itemView.findViewById(R.id.ll_creditor_information_layout);
        }
    }
}
