package com.techease.debtreductioncalculator.dataModel;

public class CreditorDataModel {
    String input_creditor, input_balance, input_rate, input_payment, input_custom, strSum,strPaymentSum,Id;



    public String getInput_creditor() {
        return input_creditor;
    }

    public void setInput_creditor(String input_creditor) {
        this.input_creditor = input_creditor;
    }

    public String getInput_balance() {
        return input_balance;
    }

    public void setInput_balance(String input_balance) {
        this.input_balance = input_balance;
    }

    public String getInput_rate() {
        return input_rate;
    }

    public void setInput_rate(String input_rate) {
        this.input_rate = input_rate;
    }

    public String getInput_payment() {
        return input_payment;
    }

    public void setInput_payment(String input_payment) {
        this.input_payment = input_payment;
    }

    public String getInput_custom() {
        return input_custom;
    }

    public void setInput_custom(String input_custom) {
        this.input_custom = input_custom;
    }

    public String getStrSum() {
        return strSum;
    }

    public String setStrSum(String strSum) {
        this.strSum = strSum;
        return strSum;
    }

    public String getStrPaymentSum() {
        return strPaymentSum;
    }

    public void setStrPaymentSum(String strPaymentSum) {
        this.strPaymentSum = strPaymentSum;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
