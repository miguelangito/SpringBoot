package com.riwi.API.Rest.entities;

public class Operations {
    
    private double num1;
    private double num2;

    public Operations() {
    }

    public Operations(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "operations [num1=" + num1 + ", num2=" + num2 + "]";
    }
    
    

}
