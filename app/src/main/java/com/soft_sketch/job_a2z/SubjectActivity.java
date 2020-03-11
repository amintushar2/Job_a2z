package com.soft_sketch.job_a2z;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SubjectActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String SUBJECT_CODE = "SubjefctCode";
    public static final String SHARED_PREF_NAME = "SubjectCodeTransfered";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static final String USER_NAME ="tushar";
    public static final String PASSWORD = "tushar";

    private AlertDialog.Builder mBuilder;
    private View mView;
    private AlertDialog dialog;
    private EditText userName;
    private EditText mPassword;
    private Button logBtn;
    private Button cancleBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mBuilder = new AlertDialog.Builder(this);
        mView = getLayoutInflater().inflate(R.layout.admin_login_dialogue, null);
        userName = (EditText) mView.findViewById(R.id.AD_userName_id);
        mPassword = (EditText) mView.findViewById(R.id.AD_password_id);
        logBtn =  mView.findViewById(R.id.AD_log_Btn_id);
        cancleBtn = mView.findViewById(R.id.AD_cancle_Btn_id);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Varified()){
                    Intent intent = new Intent(SubjectActivity.this,DataEntryActivity.class);
                    startActivity(intent);

                }
                else {
                    userName.setError("Invalid User Name"); mPassword.setError("Invalid Password");
                }
            }
        });

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.subject, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logo_id) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(SubjectActivity.this, "SignOut SuccessFully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SubjectActivity.this,
                                SelectSignInMethod.class);
                        startActivity(intent);
                        }
                    });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.admin) {
            showAlerDialogue();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showAlerDialogue() {
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    private boolean Varified() {
        Boolean status = false;
        int atempt = 0;
        if (userName.getText().toString().equals(USER_NAME) && mPassword.getText().toString().equals(PASSWORD)) {
            status = true;
        } else {
            userName.setText(""); mPassword.setText("");
            atempt++;
            if (atempt==3){
                Toast.makeText(this, "You become risky for our system!!", Toast.LENGTH_SHORT).show();
            }
        }
        return status;
    }


    public void onGknowClicked(View view) {
        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putInt(SUBJECT_CODE,104);
        editor.apply();
        Intent intent = new Intent(this,ExamListActivity.class);
        startActivity(intent);
    }

    public void onEnglishClicked(View view) {
        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putInt(SUBJECT_CODE,103);
        editor.apply();
        Intent intent = new Intent(this,ExamListActivity.class);
        startActivity(intent);
    }

    public void onMathClicked(View view) {
        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putInt(SUBJECT_CODE,102);
        editor.apply();
        Intent intent = new Intent(this,ExamListActivity.class);
        startActivity(intent);
    }

    public void onBanlaClicked(View view) {
        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putInt(SUBJECT_CODE,101);
        editor.apply();
        Intent intent = new Intent(this,ExamListActivity.class);
        startActivity(intent);
    }

}
