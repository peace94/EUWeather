package com.example.euweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.euweather.model.WeatherInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<WeatherInfo> values = new ArrayList<>();

    public void clearData() {
        values.clear();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_view, viewGroup, false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.cityName.setText("City: " + values.get(position).getName());
        myViewHolder.main.setText("State: " + values.get(position).getWeather().get(0).getMain());
        myViewHolder.description.setText("Description: " + values.get(position).getWeather().get(0).getDescription());
        myViewHolder.temp.setText("Temperature: " + values.get(position).getMain().getTemp() + " 'C");
        myViewHolder.pressure.setText("Pressure: " + values.get(position).getMain().getPressure() + " hpa");
        myViewHolder.humidity.setText("Humidity: " + values.get(position).getMain().getHumidity() + " %");
        myViewHolder.wind.setText("Wind speed: " + values.get(position).getWind().getSpeed() + " m/s");

        Picasso.get()
                .load("https://cdn3.iconfinder.com/data/icons/bebreezee-weather-symbols/690/icon-weather-sunrainheavy-512.png")
                .fit()
                .into(myViewHolder.ico);

//        switch (values.get(i).getWeather().get(0).getId() / 100) {
//            case 2:
//                myViewHolder.ico.setImageResource(R.drawable.thunder);
//                break;
//            case 3:
//            case 5:
//                myViewHolder.ico.setImageResource(R.drawable.rain);
//                break;
//            case 6:
//                myViewHolder.ico.setImageResource(R.drawable.snow);
//                break;
//            case 7:
//                myViewHolder.ico.setImageResource(R.drawable.sun);
//                break;
//            case 8:
//                if (values.get(i).getWeather().get(0).getId() > 800)
//                    myViewHolder.ico.setImageResource(R.drawable.cloudandsun);
//                else
//                    myViewHolder.ico.setImageResource(R.drawable.sun);
//                break;
//            default:
//                myViewHolder.ico.setImageResource(R.drawable.nature);
//        }


        myViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void setValues(List<WeatherInfo> values) {
        this.values.clear();
        this.values.addAll(values);
        notifyDataSetChanged();
    }

    public void addValue(WeatherInfo value) {
        this.values.add(value);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View layout;
        public TextView cityName;
        public TextView main;
        public TextView description;
        public TextView temp;
        public TextView pressure;
        public TextView humidity;
        public TextView wind;
        public ImageView ico;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ico = itemView.findViewById(R.id.myItemMenuIcon);
            cityName = itemView.findViewById(R.id.myItemMenuTitleCity);
            main = itemView.findViewById(R.id.myItemMenuTitleMain);
            description = itemView.findViewById(R.id.myItemMenuTitleDescription);
            temp = itemView.findViewById(R.id.myItemMenuTitleTemp);
            pressure = itemView.findViewById(R.id.myItemMenuTitlePressure);
            humidity = itemView.findViewById(R.id.myItemMenuTitleHumidity);
            wind = itemView.findViewById(R.id.myItemMenuTitleWind);

        }
    }
}
