package com.telstra.assessment.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.telstra.assessment.R;
import com.telstra.assessment.presenter.DataCallback;
import com.telstra.assessment.view.fragment.DataListFragment;

public class MainActivity extends AppCompatActivity implements DataCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        if (null == savedInstanceState) {
            loadFragment(new DataListFragment());
        }
    }

    public void loadFragment(Fragment fragment) {
        //attach fragment to the activity
        getSupportFragmentManager().beginTransaction().replace(R.id.container
                , fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    /*
     Binding data for title
     */
    @Override
    public void setTitle(@NonNull String title) {
        if (null != getSupportActionBar())
            getSupportActionBar().setTitle(title);
    }
}
