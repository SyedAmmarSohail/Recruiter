package com.example.ammar.recruiter;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import studios.codelight.smartloginlibrary.SmartCustomLoginListener;
import studios.codelight.smartloginlibrary.SmartCustomLogoutListener;
import studios.codelight.smartloginlibrary.SmartLoginActivity;
import studios.codelight.smartloginlibrary.SmartLoginBuilder;
import studios.codelight.smartloginlibrary.SmartLoginConfig;
import studios.codelight.smartloginlibrary.manager.UserSessionManager;
import studios.codelight.smartloginlibrary.users.SmartFacebookUser;
import studios.codelight.smartloginlibrary.users.SmartGoogleUser;
import studios.codelight.smartloginlibrary.users.SmartUser;


public class LoginActivity extends AppCompatActivity implements SmartCustomLogoutListener, SmartCustomLoginListener {
    //SmartFacebookResult smartFacebookResult;
    TextView loginResult;
    CheckBox customLogin, facebookLogin, googleLogin, appLogoCheckBox;
    SmartUser currentUser;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginResult = (TextView) findViewById(R.id.login_result);
        customLogin = (CheckBox) findViewById(R.id.customCheckbox);
        facebookLogin = (CheckBox) findViewById(R.id.facebookCheckbox);
        googleLogin = (CheckBox) findViewById(R.id.googleCheckbox);
        appLogoCheckBox = (CheckBox) findViewById(R.id.appLogoCheckbox);

        //////////Ammar////
       /* SmartLoginActivity loginActivity = new SmartLoginActivity();
        loginActivity.findViewById(R.id.custom_signup_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new LoginActivity().getApplicationContext(), new DialogActivity().getClass());
                Toast.makeText(new LoginActivity().getApplicationContext(), "as", Toast.LENGTH_SHORT).show();
            }
        });*/
        ////////Ammar////////

        //get the current user details
        currentUser = UserSessionManager.getCurrentUser(this);
        String display = "no user";
        if(currentUser != null) {
            if (currentUser instanceof SmartFacebookUser) {
                SmartFacebookUser facebookUser = (SmartFacebookUser) currentUser;
                display = facebookUser.getProfileName() + " (FacebookUser)is logged in";
            } else if (currentUser instanceof SmartGoogleUser) {
                display = ((SmartGoogleUser) currentUser).getDisplayName() + " (GoogleUser) is logged in";
            } else {
                display = currentUser.getUsername() + " (Custom User) is logged in";
            }
        }
        loginResult.setText(display);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, null)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        if (loginButton != null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (currentUser != null) {

                   //     startActivity(new Intent(getApplication().getApplicationContext(), DialogActivity.class));

                        /*Ammar Comment*/
                        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage(R.string.user_exists);
                        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                UserSessionManager.logout(LoginActivity.this, currentUser, LoginActivity.this, mGoogleApiClient);
                                currentUser = UserSessionManager.getCurrentUser(LoginActivity.this);
                            }
                        });
                        builder.setCancelable(true);

                        builder.create().show();
                    } else {

                        SmartLoginBuilder loginBuilder = new SmartLoginBuilder();

                        //Set facebook permissions
                        ArrayList<String> permissions = new ArrayList<>();
                        permissions.add("public_profile");
                        permissions.add("email");
                        permissions.add("user_birthday");
                        permissions.add("user_friends");


                        Intent intent = loginBuilder.with(getApplicationContext())
                                .setAppLogo(getlogo())
                                .isFacebookLoginEnabled(facebookLogin.isChecked())
                                .withFacebookAppId(getString(R.string.facebook_app_id)).withFacebookPermissions(permissions)
                                .isGoogleLoginEnabled(googleLogin.isChecked())
                                .isCustomLoginEnabled(customLogin.isChecked(), SmartLoginConfig.LoginType.withEmail)
                                .setSmartCustomLoginHelper(LoginActivity.this)
                                .build();

                        startActivityForResult(intent, SmartLoginConfig.LOGIN_REQUEST);
                        //startActivity(intent);
                    }
                }
            });
        }
    }

    private int getlogo() {
        if(appLogoCheckBox.isChecked()){
            return R.mipmap.ic_launcher;
        }
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String fail = "Login Failed";
        if(resultCode == SmartLoginConfig.FACEBOOK_LOGIN_REQUEST){
            SmartFacebookUser user;
            try {
                user = data.getParcelableExtra(SmartLoginConfig.USER);
                String userDetails = user.getProfileName() + " " + user.getEmail() + " " + user.getBirthday();
                loginResult.setText(userDetails);
            }catch (Exception e){
                loginResult.setText(fail);
            }
        }
        else if(resultCode == SmartLoginConfig.GOOGLE_LOGIN_REQUEST){
            SmartGoogleUser user = data.getParcelableExtra(SmartLoginConfig.USER);
            String userDetails = user.getEmail() + " " + user.getDisplayName();
            loginResult.setText(userDetails);
        }
        else if(resultCode == SmartLoginConfig.CUSTOM_LOGIN_REQUEST){
            SmartUser user = data.getParcelableExtra(SmartLoginConfig.USER);
            String userDetails = user.getUsername() + " (Custom User)";
            loginResult.setText(userDetails);
        }
        else if(resultCode == SmartLoginConfig.CUSTOM_SIGNUP_REQUEST){
            SmartUser user = data.getParcelableExtra(SmartLoginConfig.USER);
            String userDetails = user.getUsername() + " (Custom User)";
            loginResult.setText(userDetails);
        }
        else if(resultCode == RESULT_CANCELED){
            loginResult.setText(fail);
        }

    }

    @Override
    public boolean customUserSignout(SmartUser smartUser) {
        //Implement your logic
        UserSessionManager.logout(this, smartUser, this, mGoogleApiClient);
        return true;
    }


    @Override
    public boolean customSignin(SmartUser user) {
        //This "user" will have only username and password set.
        Toast.makeText(LoginActivity.this, user.getUsername() + " " + user.getEmail(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, com.example.ammar.recruiter.ui.activity.MainActivity.class));
        return true;
    }

    @Override
    public boolean customSignup(SmartUser newUser) {
        //Implement your our custom sign up logic and return true if success

        Toast.makeText(LoginActivity.this, newUser.getUsername() + " :  " + newUser.getEmail() + " : " + newUser.getPassword() +  " : "  + newUser.getChoose(), Toast.LENGTH_SHORT  ).show();
        return true;
    }

    @Override
    public boolean customSignuptext() {

        return true;
    }
    public void showDialog(){
        SmartLoginActivity loginActivity = new SmartLoginActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.getApplication().getApplicationContext());
        builder.setTitle("Select As a");

        //list of items
        String[] items = getResources().getStringArray(R.array.Choose_Options);
        builder.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // item selected logic
                    }
                });

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }


}
