package com.bnsantos.test.support.library.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.bnsantos.test.support.library.App;
import com.bnsantos.test.support.library.R;

public class SettingsActivity extends AppCompatActivity {
    private EditText apiKey;

    public static void startForResult(Activity activity){
        Intent intent = new Intent(activity, SettingsActivity.class);
        activity.startActivityForResult(intent, MainActivity.SETTINGS_REQ_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        apiKey = (EditText) findViewById(R.id.inputApiKey);
        apiKey.setText(App.API_KEY);

        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.API_KEY = apiKey.getText().toString();
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
