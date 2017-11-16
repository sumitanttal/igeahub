package com.sumit.igeahub.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.sumit.igeahub.R;
import com.sumit.igeahub.fragments.About_Fragment;
import com.sumit.igeahub.fragments.FriendList_Fragment;
import com.sumit.igeahub.fragments.Home_Fragment;
import com.sumit.igeahub.fragments.Luca_dezzani_Fragment;
import com.sumit.igeahub.fragments.News_frag;
import com.sumit.igeahub.fragments.Perspectives_Fragment;
import com.sumit.igeahub.fragments.Profile_Fragment;
import com.sumit.igeahub.fragments.Recent_Fragment;
import com.sumit.igeahub.interfaces.OnFragmentTransfer;

public class HomeActivity extends ParentActivity implements OnFragmentTransfer,NavigationView.OnNavigationItemSelectedListener{
    FrameLayout content_frame;
  public DrawerLayout drawer_layout;
    private final int LOGIN_ACT_REQ_CODE=400;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        content_frame=findViewById(R.id.content_frame);
        drawer_layout=findViewById(R.id.drawer_layout);

        Fragment fragment = new Home_Fragment();
        init_Frame(fragment);
        setNavigationViewListner();
    }

    public  void init_Frame(Fragment fragment) {
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

    }


    @Override
    public void onTransfer(int code) {
        switch (code){
            case LOGIN_ACT_REQ_CODE:
                finish();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.slider_menu, menu);
        return true;
    }
    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment =null;


        switch (item.getItemId()) {

            case R.id.home:

                fragment=new Home_Fragment();
                break;
            case R.id.news:

                fragment=new News_frag();
                break;
            case R.id.perspectives:

                fragment=new Perspectives_Fragment();
                break;
            case R.id.about:
                fragment=new About_Fragment();

                break;
            case R.id.profile:
                fragment=new Profile_Fragment();
                break;
            case R.id.friends:

                fragment=new FriendList_Fragment();
                break;

            case R.id.groups:


                break;
            case R.id.luca_dezzani:
                fragment=new Luca_dezzani_Fragment();

                break;
            case R.id.logout:
                Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                HomeActivity.super.setOnActivityTrasfer(intent,LOGIN_ACT_REQ_CODE);


                break;
        }
        try {
            init_Frame(fragment);
        }catch (Exception e){

        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onTransfer(Fragment fragment) {
       init_Frame(fragment);
    }
}
