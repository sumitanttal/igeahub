package com.sumit.igeahub.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.FrameLayout;

import com.sumit.igeahub.R;
import com.sumit.igeahub.fragments.Home_Fragment;

public class HomeActivity extends ParentActivity {
    FrameLayout content_frame;
  public DrawerLayout drawer_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        content_frame=findViewById(R.id.content_frame);
        drawer_layout=findViewById(R.id.drawer_layout);

        Fragment fragment = new Home_Fragment();
        init_Frame(fragment);
    }

    private void init_Frame(Fragment fragment) {
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

    }


    @Override
    public void onTransfer(int code) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_items, menu);
        return true;
    }
}
