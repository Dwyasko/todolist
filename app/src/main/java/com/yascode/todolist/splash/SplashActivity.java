package com.yascode.todolist.splash;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yascode.todolist.R;
import com.yascode.todolist.data.local.AppDatabase;
import com.yascode.todolist.data.local.todolist.TodoList;
import com.yascode.todolist.data.remote.HttpClient;
import com.yascode.todolist.main.MainActivity;
import com.yascode.todolist.utils.ApplicationPreference;

public class SplashActivity extends AppCompatActivity implements SplashContract.view {

    ProgressBar pb;
    TextView txLoader;

    ApplicationPreference applicationPreference;
    AppDatabase appDatabase;
    SplashContract.presenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pb = findViewById(R.id.pb);
        txLoader = findViewById(R.id.tx_loader);

        applicationPreference = new ApplicationPreference();

        if (applicationPreference.isFirstTime(this, ApplicationPreference.IS_FIRST_TIME)) {
            appDatabase = Room.databaseBuilder(this, AppDatabase.class, "tododb").build();

            splashPresenter = new SplashPresenter(this, appDatabase, HttpClient.provideApiService());
            splashPresenter.retreiveTodoList();
        }else{
            gotoIntent();
        }
    }

    @Override
    public void hideLoader() {
        pb.setVisibility(View.GONE);
        txLoader.setText("Data Loaded Succesfully");
        applicationPreference.setNotFirst(this, ApplicationPreference.IS_FIRST_TIME);

        gotoIntent();
    }


    @Override
    public void showLoader() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void gotoIntent() {
        Intent main = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(main);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appDatabase = null;
        splashPresenter = null;
        applicationPreference = null;
    }
}
