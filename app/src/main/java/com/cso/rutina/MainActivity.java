package com.cso.rutina;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    CosmeFragment cosmeFragment;
    SettingFragment settingFragment;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    NotificationManager nm;
    Notification.Builder builder;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        homeFragment = new HomeFragment();
        cosmeFragment = new CosmeFragment();
        settingFragment = new SettingFragment();
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        createNotificationChannel();

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("고객센터")
                .setPositiveButton("확인", (dialogInterface, i) -> dialogInterface.dismiss())
                .create();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24);

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();

        builder = new Notification.Builder(this, "rutina");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("월간 보고서가 도착했습니다!");
        builder.setContentText("클릭하여 보고서를 확인해주세요!");
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        push.setClass(this, MainActivity.class);

        pendingIntent = PendingIntent.getActivity(this, 0, push, PendingIntent.FLAG_IMMUTABLE);
        nm.notify(1, builder.build());

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.monthly_reporter:
                    String name = getIntent().getStringExtra("name");
                    moveToReporter(name);
                    break;

                case R.id.item_service_center:
                    alertDialog.show();
                    break;
            }
            return false;
        });
        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);
        navigationBarView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();
                    return true;
                case R.id.setting:
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, settingFragment).commit();
                    return true;
                case R.id.info:
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, cosmeFragment).commit();
                    return true;
            }
            return false;
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void moveToReporter(String name){
        startActivity(new Intent(this, Monthly_Activity.class).putExtra("id", name));
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Rutina";
            String description = "Rutina channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("rutina", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}