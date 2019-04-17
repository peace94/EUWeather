package com.example.euweather;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.euweather.model.WeatherInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int CODE = 0;

    private RecyclerView my_recycler;
    private FloatingActionButton edit_button;
    private List<CityEnum> cities = new ArrayList<>();
    MyAPI myApi;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        my_recycler = findViewById(R.id.myRecycler);
        my_recycler.setLayoutManager(layoutManager);
        my_recycler.setAdapter(adapter);

        cities.add(CityEnum.MOSCOW);
        cities.add(CityEnum.KALUGA);
        cities.add(CityEnum.KALININGRAD);
        cities.add(CityEnum.MURMANSK);
        cities.add(CityEnum.NOVOSIBIRSK);
        cities.add(CityEnum.OMSK);

        edit_button = findViewById(R.id.EditButton);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChooseCity.class);
                intent.putExtra(CityEnum.MOSCOW.getName(), cities.contains(CityEnum.MOSCOW));
                intent.putExtra(CityEnum.KALUGA.getName(), cities.contains(CityEnum.KALUGA));
                intent.putExtra(CityEnum.KALININGRAD.getName(), cities.contains(CityEnum.KALININGRAD));
                intent.putExtra(CityEnum.OMSK.getName(), cities.contains(CityEnum.OMSK));
                intent.putExtra(CityEnum.NOVOSIBIRSK.getName(), cities.contains(CityEnum.NOVOSIBIRSK));
                intent.putExtra(CityEnum.MURMANSK.getName(), cities.contains(CityEnum.MURMANSK));
                startActivityForResult(intent, CODE);
            }
        });


        MyApplication myApplication = MyApplication.getMyApplicationInstance();
        myApi = myApplication.getMyApi();

        getInfoByCities();




    }

    private void getInfoByCities() {
        adapter.values.clear();
        for(CityEnum city: cities){
            myApi.getWeather(city.getId(), MyApplication.APP_ID).enqueue(new Callback<WeatherInfo>() {
                @Override
                public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                    adapter.addValue(response.body());
                }

                @Override
                public void onFailure(Call<WeatherInfo> call, Throwable t) {
                    System.out.println("EXCEPTION: " + t);
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            cities.clear();
            if (data.getBooleanExtra(CityEnum.MOSCOW.getName(), false))
                cities.add(CityEnum.MOSCOW);
            if (data.getBooleanExtra(CityEnum.KALUGA.getName(), false))
                cities.add(CityEnum.KALUGA);
            if (data.getBooleanExtra(CityEnum.KALININGRAD.getName(), false))
                cities.add(CityEnum.KALININGRAD);
            if (data.getBooleanExtra(CityEnum.OMSK.getName(), false))
                cities.add(CityEnum.OMSK);
            if (data.getBooleanExtra(CityEnum.NOVOSIBIRSK.getName(), false))
                cities.add(CityEnum.NOVOSIBIRSK);
            if (data.getBooleanExtra(CityEnum.MURMANSK.getName(), false))
                cities.add(CityEnum.MURMANSK);
            getInfoByCities();
        }
    }
}
