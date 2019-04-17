package com.example.euweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class ChooseCity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        Intent intent = getIntent();

        CheckBox moscow = findViewById(R.id.idMoscow);
        moscow.setChecked(intent.getBooleanExtra(CityEnum.MOSCOW.getName(), true));
        CheckBox kaluga = findViewById(R.id.idKaluga);
        kaluga.setChecked(intent.getBooleanExtra(CityEnum.KALUGA.getName(), true));
        CheckBox kaliningrad = findViewById(R.id.idKaliningrad);
        kaliningrad.setChecked(intent.getBooleanExtra(CityEnum.KALININGRAD.getName(), true));
        CheckBox omsk = findViewById(R.id.idOmsk);
        omsk.setChecked(intent.getBooleanExtra(CityEnum.OMSK.getName(), true));
        CheckBox novosibursk = findViewById(R.id.idNovosibirsk);
        novosibursk.setChecked(intent.getBooleanExtra(CityEnum.NOVOSIBIRSK.getName(), true));
        CheckBox murmansk = findViewById(R.id.idMurmansk);
        murmansk.setChecked(intent.getBooleanExtra(CityEnum.MURMANSK.getName(), true));

    }

    public void sendAnswer(View view) {
        Intent intentToMainActivity = new Intent();
        CheckBox moscow = findViewById(R.id.idMoscow);
        CheckBox kaluga = findViewById(R.id.idKaluga);
        CheckBox kaliningrad = findViewById(R.id.idKaliningrad);
        CheckBox omsk = findViewById(R.id.idOmsk);
        CheckBox novosibursk = findViewById(R.id.idNovosibirsk);
        CheckBox murmansk = findViewById(R.id.idMurmansk);
        intentToMainActivity.putExtra(CityEnum.MOSCOW.getName(), moscow.isChecked());
        intentToMainActivity.putExtra(CityEnum.KALUGA.getName(), kaluga.isChecked());
        intentToMainActivity.putExtra(CityEnum.KALININGRAD.getName(), kaliningrad.isChecked());
        intentToMainActivity.putExtra(CityEnum.OMSK.getName(), omsk.isChecked());
        intentToMainActivity.putExtra(CityEnum.NOVOSIBIRSK.getName(), novosibursk.isChecked());
        intentToMainActivity.putExtra(CityEnum.MURMANSK.getName(), murmansk.isChecked());
        setResult(RESULT_OK, intentToMainActivity);
        this.finish();
    }
}
