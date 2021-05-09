package com.example.bighub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminHomeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Button addproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
// Nav Bar

        Toolbar toolbar=(Toolbar)findViewById(R.id.admintoolbar);
        setSupportActionBar(toolbar);

        nav=(NavigationView)findViewById(R.id.adminnavmenu);
// nav Text View
        NavigationView navigationView = (NavigationView) findViewById(R.id.adminnavmenu);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.txtnavname);
        navUsername.setText(user.getDisplayName());
        TextView navEmail = (TextView) headerView.findViewById(R.id.txtnavemil);
        navEmail.setText(user.getEmail());
        ImageView navImg=(ImageView) headerView.findViewById(R.id.imgnp);
        Glide.with(this).load(user.getPhotoUrl()).into(navImg);

        drawerLayout=(DrawerLayout)findViewById(R.id.admindrawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.adminsignOut :
                        mAuth.getInstance().signOut();
                        startActivity(new Intent(AdminHomeActivity.this,GloginActivity.class));
                        finish();
                        Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.addproduct :
                        mAuth.getInstance().signOut();
                        startActivity(new Intent(AdminHomeActivity.this,AddProductActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.ViewAdmin :
                        startActivity(new Intent(AdminHomeActivity.this,HomeActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

        addproduct=findViewById(R.id.btnAddproduct);
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                startActivity(new Intent(AdminHomeActivity.this,AddProductActivity.class));

            }
        });

    }

}