package com.example.retrofitjsonget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView demoTxt;

    List<RetDemo1> retDemo1List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoTxt = findViewById(R.id.demo_txt);

        loadData();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitAPI = retrofit.create(RetrofitApi.class);

        Call<List<RetDemo1>> demoCall = retrofitAPI.getPost();


        demoCall.enqueue(new Callback<List<RetDemo1>>() {
            @Override
            public void onResponse(Call<List<RetDemo1>> call, Response<List<RetDemo1>> response) {


                List<RetDemo1> z = response.body();


                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                Gson gson = new Gson();




                /*for (int i=0; i<z.size(); i++) {
                    String q = z.get(i).getWord1();
                    String w = z.get(i).getWord2();
                    int o = z.get(i).getNum1();
                    int p = z.get(i).getNum2();

                    retDemo1List.add(new RetDemo1(o, p, q, w));

                    //demoTxt.append(q+"\n"+w+"\n"+o+"\n"+p);
                }*/


                String json = gson.toJson(z);
                myEdit.putString("dataArry", json);
                myEdit.apply();

                loadData();

            }

            @Override
            public void onFailure(Call<List<RetDemo1>> call, Throwable t) {

            }
        });

    }


    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("dataArry", null);
        Type type = new TypeToken<ArrayList<RetDemo1>>(){

         }.getType();
        retDemo1List=gson.fromJson(json, type);

        if (retDemo1List == null){
            retDemo1List = new ArrayList<>();
            demoTxt.setText("");
        }else{
            for (int i=0; i<retDemo1List.size(); i++){
                demoTxt.append("\n"+"\n"+"\n"+retDemo1List.get(i).getNum1()+"\n"+
                        retDemo1List.get(i).getNum2()+"\n"+retDemo1List.get(i).getWord1()
                        +"\n"+retDemo1List.get(i).getWord2()+"\n"+"\n"+"\n");
            }
        }


    }




}