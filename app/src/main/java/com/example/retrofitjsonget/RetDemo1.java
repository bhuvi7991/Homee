package com.example.retrofitjsonget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetDemo1 {

    @SerializedName("userId")
    @Expose
    public int num1;

    @SerializedName("id")
    @Expose
    public int num2;


    @SerializedName("title")
    @Expose
    public  String word1;

    @SerializedName("body")
    @Expose
    public  String word2;


    public RetDemo1(int num1, int num2, String word1, String word2) {
        this.num1 = num1;
        this.num2 = num2;
        this.word1 = word1;
        this.word2 = word2;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public String getWord1() {
        return word1;
    }

    public String getWord2() {
        return word2;
    }
}
