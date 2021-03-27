package ghosh687.souvik.flickrgallery.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import ghosh687.souvik.flickrgallery.R;
import ghosh687.souvik.flickrgallery.adapter.MyAdapter;
import ghosh687.souvik.flickrgallery.api.FlickrApi;
import ghosh687.souvik.flickrgallery.modelclass.FlickrGallery;
import ghosh687.souvik.flickrgallery.modelclass.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AlertDialog alertDialog;

    public static ArrayList<String> imageurls = new ArrayList<>();
    RecyclerView images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Photos");

        images=findViewById(R.id.imageslist);
        images.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        images.setLayoutManager(llm);

        getdata();
        createdrawerLayout();
        showalertdialog();
    }

    private void showalertdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View dialogview = layoutInflater.inflate(R.layout.loading_networkerror,null);
        builder.setView(dialogview);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogview.findViewById(R.id.longertime).setVisibility(View.VISIBLE);
            }
        }, 7000);
    }

    private void getdata() {
        Call<FlickrGallery> galleryCall = FlickrApi.getPostService().getImages();
        galleryCall.enqueue(new Callback<FlickrGallery>() {
            @Override
            public void onResponse(Call<FlickrGallery> call, Response<FlickrGallery> response) {
                if(response.body().getStat().equals("ok")){
                    for (int k = 0; k < 30; k++) {
                        imageurls.add(response.body().getPhotos().getPhoto().get(k).getUrlS());
                    }
                    MyAdapter myadapter = new MyAdapter(imageurls,MainActivity.this);
                    images.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<FlickrGallery> call, Throwable t) {

            }
        });
    }

    private void createdrawerLayout() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigationviewside);
        View view = navigationView.inflateHeaderView(R.layout.sidenavigationicon);
        Animation animation;
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.sample_animation);

        view.findViewById(R.id.textView21).startAnimation(animation);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                UserItemSelected(item);
                return false;
            }
            private void UserItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.exit:
                        finish();
                        break;
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}