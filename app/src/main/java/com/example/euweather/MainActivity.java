package com.example.euweather;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.euweather.model.ManyCitiesResponse;
import com.example.euweather.model.WeatherInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MyAdapter.AdapterInteractionListener{

    private static final int CODE = 0;

    private RecyclerView my_recycler;
    private List<WeatherInfo> weatherInfoList = new ArrayList<>();
    private FloatingActionButton edit_button;
    private List<CityEnum> cityEnums = new ArrayList<>();
    private MyAPI myApi;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        my_recycler = findViewById(R.id.myRecycler);
        my_recycler.setLayoutManager(layoutManager);
        my_recycler.setAdapter(adapter);

        cityEnums.add(CityEnum.MOSCOW);
        cityEnums.add(CityEnum.KALUGA);
        cityEnums.add(CityEnum.KALININGRAD);
        cityEnums.add(CityEnum.MURMANSK);
        cityEnums.add(CityEnum.NOVOSIBIRSK);
        cityEnums.add(CityEnum.OMSK);

        edit_button = findViewById(R.id.EditButton);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChooseCity.class);
                intent.putExtra(CityEnum.MOSCOW.getName(), cityEnums.contains(CityEnum.MOSCOW));
                intent.putExtra(CityEnum.KALUGA.getName(), cityEnums.contains(CityEnum.KALUGA));
                intent.putExtra(CityEnum.KALININGRAD.getName(), cityEnums.contains(CityEnum.KALININGRAD));
                intent.putExtra(CityEnum.OMSK.getName(), cityEnums.contains(CityEnum.OMSK));
                intent.putExtra(CityEnum.NOVOSIBIRSK.getName(), cityEnums.contains(CityEnum.NOVOSIBIRSK));
                intent.putExtra(CityEnum.MURMANSK.getName(), cityEnums.contains(CityEnum.MURMANSK));
                startActivityForResult(intent, CODE);
            }
        });

        myApi = MyApplication.getMyApplicationInstance().getMyApi();

        getInfoByCities();
    }

    private void getInfoByCities() {

        adapter.clearData();

        StringBuilder ids = new StringBuilder();
        for (CityEnum city : cityEnums) {
            ids.append(city.getId()).append(",");
        }
        if (ids.length() > 0) {
            ids.deleteCharAt(ids.length() - 1);
        }

        myApi.getManyCitiesWeather(ids.toString(), MyApplication.APP_ID, "metric")
                .enqueue(new Callback<ManyCitiesResponse>() {
                    @Override
                    public void onResponse(Call<ManyCitiesResponse> call, Response<ManyCitiesResponse> response) {
                        weatherInfoList = response.body().getWeatherInfoList();
                        adapter.setValues(weatherInfoList);
                    }

                    @Override
                    public void onFailure(Call<ManyCitiesResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

//        for(CityEnum city: cityEnums){
//            myApi.getWeather(city.getId(), MyApplication.APP_ID, "metric").enqueue(new Callback<WeatherInfo>() {
//                @Override
//                public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
//                    adapter.addValue(response.body());
//                }
//
//                @Override
//                public void onFailure(Call<WeatherInfo> call, Throwable t) {
//                    System.out.println("EXCEPTION: " + t);
//                }
//            });
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            cityEnums.clear();
            if (data.getBooleanExtra(CityEnum.MOSCOW.getName(), false))
                cityEnums.add(CityEnum.MOSCOW);
            if (data.getBooleanExtra(CityEnum.KALUGA.getName(), false))
                cityEnums.add(CityEnum.KALUGA);
            if (data.getBooleanExtra(CityEnum.KALININGRAD.getName(), false))
                cityEnums.add(CityEnum.KALININGRAD);
            if (data.getBooleanExtra(CityEnum.OMSK.getName(), false))
                cityEnums.add(CityEnum.OMSK);
            if (data.getBooleanExtra(CityEnum.NOVOSIBIRSK.getName(), false))
                cityEnums.add(CityEnum.NOVOSIBIRSK);
            if (data.getBooleanExtra(CityEnum.MURMANSK.getName(), false))
                cityEnums.add(CityEnum.MURMANSK);
            getInfoByCities();
        }
    }

//    void showMyDialog(int position) {
//        EditText input = new EditText(this);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//        );
//        input.setLayoutParams(layoutParams);
//
//        new AlertDialog.Builder(this)
//                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "ADDED", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "DELETED", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "CANCELLED", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setView(input)
//                .setTitle("What do you want?")
//                .create()
//                .show();
//
//
//    }

    @Override
    public void onWeatherItemClicked(int position) {
        Toast.makeText(this, "clicked on+" + position , Toast.LENGTH_SHORT).show();
//        showMyDialog(position);
        EditText input = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        input.setLayoutParams(layoutParams);
        new AlertDialog.Builder(this)
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "ADDED", Toast.LENGTH_SHORT).show();
                        String randomCityId =new String().valueOf(new Random().nextInt(99999));
                        myApi.getWeather(input.getText().toString(),MyApplication.APP_ID,"metric").enqueue(new Callback<WeatherInfo>() {
                            @Override
                            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                                if(response.isSuccessful()) {
                                    weatherInfoList.add(position, response.body());
                                    adapter.setValues(weatherInfoList);
                                } else {
                                    Toast.makeText(MainActivity.this, "errorIfSucc", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(MainActivity.this, "errorCityId", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "DELETED", Toast.LENGTH_SHORT).show();
                        weatherInfoList.remove(position);
                        adapter.setValues(weatherInfoList);
                    }
                })
                .setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "CANCELLED", Toast.LENGTH_SHORT).show();
                    }
                })
                .setView(input)
                .setTitle("What do you want?")
                .create()
                .show();

    }
}
