package com.example.amrit.myapplication2;

public class BMIResult {
    private double height = 0;
    private double weight = 0;
    private String date = "";

    public BMIResult(double height, double weight, String date){
        this.setHeight(height);
        this.setWeight(weight);
        this.setDate(date);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDate() {return date;}

    public void setDate(String date) {
        this.date = date;
    }

    public double getResult(){
        return weight / (height * height);
    }

    public String toString(){
        return date + "\n\t\tBMI: " + String.valueOf(getResult());
    }
}
