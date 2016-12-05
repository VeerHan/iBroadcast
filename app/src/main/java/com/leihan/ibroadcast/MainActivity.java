package com.leihan.ibroadcast;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ProImageView imageView;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ProImageView) findViewById(R.id.imageView);
        testUpload();
    }

    private void testUpload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (progress == 100) {
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                            }
                        });
                        return;
                    }
                    progress++;
                    imageView.setProgress(progress);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
