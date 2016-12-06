package com.leihan.ibroadcast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_gv).setOnClickListener(this);
        findViewById(R.id.btn_lv).setOnClickListener(this);
        findViewById(R.id.btn_voice).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gv:

                break;
            case R.id.btn_lv:

                break;
            case R.id.btn_voice:

                break;
        }
    }

}
