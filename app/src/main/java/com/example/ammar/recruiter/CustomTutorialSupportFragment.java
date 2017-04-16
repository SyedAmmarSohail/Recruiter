package com.example.ammar.recruiter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cleveroad.slidingtutorial.Direction;
import com.cleveroad.slidingtutorial.IndicatorOptions;
import com.cleveroad.slidingtutorial.OnTutorialPageChangeListener;
import com.cleveroad.slidingtutorial.PageOptions;
import com.cleveroad.slidingtutorial.TransformItem;
import com.cleveroad.slidingtutorial.TutorialOptions;
import com.cleveroad.slidingtutorial.TutorialPageOptionsProvider;
import com.cleveroad.slidingtutorial.TutorialSupportFragment;
import com.example.ammar.recruiter.renderer.DrawableRenderer;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import studios.codelight.smartloginlibrary.SmartCustomLoginListener;
import studios.codelight.smartloginlibrary.SmartCustomLogoutListener;
import studios.codelight.smartloginlibrary.SmartLoginBuilder;
import studios.codelight.smartloginlibrary.SmartLoginConfig;
import studios.codelight.smartloginlibrary.manager.UserSessionManager;
import studios.codelight.smartloginlibrary.users.SmartFacebookUser;
import studios.codelight.smartloginlibrary.users.SmartGoogleUser;
import studios.codelight.smartloginlibrary.users.SmartUser;

//import com.cleveroad.slidingtutorial.sample.renderer.DrawableRenderer;

public class CustomTutorialSupportFragment extends TutorialSupportFragment
        implements OnTutorialPageChangeListener, SmartCustomLogoutListener, SmartCustomLoginListener {

    private static final String TAG = "CustomTutorialSFragment";
    private static final int TOTAL_PAGES = 3;
    private static final int ACTUAL_PAGES_COUNT = 3;
    SmartUser currentUser;
    GoogleApiClient mGoogleApiClient;

    private final View.OnClickListener mOnSkipClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tvSkip:
                //    startActivity(new Intent(getContext().getApplicationContext(), LoginActivity.class));

                    //////////Ammar////////

                    currentUser = UserSessionManager.getCurrentUser(getContext());
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

                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .requestProfile()
                            .build();

                    if (mGoogleApiClient != null) {


                    }
                    else{
                        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                                .enableAutoManage(getActivity(), null)
                                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                                .build();

                    }
                    if (currentUser != null) {
                      /*  UserSessionManager.logout(getActivity(), currentUser, CustomTutorialSupportFragment.this, mGoogleApiClient);
                        currentUser = UserSessionManager.getCurrentUser(getContext());*/
                        //     startActivity(new Intent(getApplication().getApplicationContext(), DialogActivity.class));

                        /*FragmentManager fm = getFragmentManager();
                        DialogFragment_logout dialogFragment = new DialogFragment_logout ();
                        dialogFragment.
                        dialogFragment.show(fm, "");*/

                        /*Ammar Comment*/
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage(R.string.user_exists);
                        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                UserSessionManager.logout(getActivity(), currentUser, CustomTutorialSupportFragment.this, mGoogleApiClient);
                                currentUser = UserSessionManager.getCurrentUser(getContext());
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


                        Intent intent = loginBuilder.with(getContext().getApplicationContext())
                                .setAppLogo(getlogo())
                                .isFacebookLoginEnabled(true)
                                .withFacebookAppId(getString(R.string.facebook_app_id)).withFacebookPermissions(permissions)
                                .isGoogleLoginEnabled(true)
                                .isCustomLoginEnabled(true, SmartLoginConfig.LoginType.withEmail)
                                .setSmartCustomLoginHelper(CustomTutorialSupportFragment.this)
                                .build();

                        startActivityForResult(intent, SmartLoginConfig.LOGIN_REQUEST);
                        //startActivity(intent);
                    }

                    //////////Ammar///////
                    break;


                }





        }
    };

    private int getlogo() {

            return R.mipmap.ic_launcher;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String fail = "Login Failed";
        if(resultCode == SmartLoginConfig.FACEBOOK_LOGIN_REQUEST){
            SmartFacebookUser user;
            try {
                user = data.getParcelableExtra(SmartLoginConfig.USER);
                String userDetails = user.getProfileName() + " " + user.getEmail() + " " + user.getBirthday();

            }catch (Exception e){

            }
        }
        else if(resultCode == SmartLoginConfig.GOOGLE_LOGIN_REQUEST){
            SmartGoogleUser user = data.getParcelableExtra(SmartLoginConfig.USER);
            String userDetails = user.getEmail() + " " + user.getDisplayName();

        }
        else if(resultCode == SmartLoginConfig.CUSTOM_LOGIN_REQUEST){
            SmartUser user = data.getParcelableExtra(SmartLoginConfig.USER);
            String userDetails = user.getUsername() + " (Custom User)";

        }
        else if(resultCode == SmartLoginConfig.CUSTOM_SIGNUP_REQUEST){
            SmartUser user = data.getParcelableExtra(SmartLoginConfig.USER);
            String userDetails = user.getUsername() + " (Custom User)";

        }
        else if(resultCode == getActivity().RESULT_CANCELED){

        }

    }

    @Override
    public boolean customUserSignout(SmartUser smartUser) {
        //Implement your logic
        UserSessionManager.logout(getActivity(), smartUser, this, mGoogleApiClient);
        return true;
    }


    @Override
    public boolean customSignin(SmartUser user) {
        //This "user" will have only username and password set.
      //  Toast.makeText(LoginActivity.this, user.getUsername() + " " + user.getEmail(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), com.example.ammar.recruiter.ui.activity.MainActivity.class));
        return true;
    }

    @Override
    public boolean customSignup(SmartUser newUser) {
        //Implement your our custom sign up logic and return true if success

        Toast.makeText(getContext(), newUser.getUsername() + " :  " + newUser.getEmail() + " : " + newUser.getPassword() +  " : "  + newUser.getChoose(), Toast.LENGTH_SHORT  ).show();
        return true;
    }

    @Override
    public boolean customSignuptext() {

        return true;
    }




    private final TutorialPageOptionsProvider mTutorialPageOptionsProvider = new TutorialPageOptionsProvider() {
        @NonNull
        @Override
        public PageOptions provide(int position) {
            @LayoutRes int pageLayoutResId;
            TransformItem[] tutorialItems;
          //  position %= ACTUAL_PAGES_COUNT;

            switch (position) {
                case 0: {
                //    Toast.makeText(getContext(), "case definition 0", Toast.LENGTH_SHORT).show();
                    pageLayoutResId = R.layout.fragment_page_first;
                    tutorialItems = new TransformItem[]{
                            TransformItem.create(R.id.ivFirstImage, Direction.LEFT_TO_RIGHT, 0.2f),
                            TransformItem.create(R.id.ivSecondImage, Direction.RIGHT_TO_LEFT, 0.06f),
                            TransformItem.create(R.id.ivThirdImage, Direction.LEFT_TO_RIGHT, 0.08f),
                            TransformItem.create(R.id.ivFourthImage, Direction.RIGHT_TO_LEFT, 0.1f),
                            TransformItem.create(R.id.ivFifthImage, Direction.RIGHT_TO_LEFT, 0.03f),
                            TransformItem.create(R.id.ivSixthImage, Direction.RIGHT_TO_LEFT, 0.09f),
                            TransformItem.create(R.id.ivSeventhImage, Direction.RIGHT_TO_LEFT, 0.14f),
                            TransformItem.create(R.id.ivEighthImage, Direction.RIGHT_TO_LEFT, 0.07f)
                    };
                    break;
                }
                case 1: {
                  //  Toast.makeText(getContext(), "case defination 1", Toast.LENGTH_SHORT).show();
                    pageLayoutResId = R.layout.fragment_page_second;
                    tutorialItems = new TransformItem[]{
                            TransformItem.create(R.id.ivFirstImage, Direction.RIGHT_TO_LEFT, 0.2f),
                            TransformItem.create(R.id.ivSecondImage, Direction.LEFT_TO_RIGHT, 0.06f),
                            TransformItem.create(R.id.ivThirdImage, Direction.RIGHT_TO_LEFT, 0.08f),
                            TransformItem.create(R.id.ivFourthImage, Direction.LEFT_TO_RIGHT, 0.1f),
                            TransformItem.create(R.id.ivFifthImage, Direction.LEFT_TO_RIGHT, 0.03f),
                            TransformItem.create(R.id.ivSixthImage, Direction.LEFT_TO_RIGHT, 0.09f),
                            TransformItem.create(R.id.ivSeventhImage, Direction.LEFT_TO_RIGHT, 0.14f),
                            TransformItem.create(R.id.ivEighthImage, Direction.LEFT_TO_RIGHT, 0.07f)
                    };
                    break;
                }
                case 2: {
                  //  Toast.makeText(getContext(), "case defination 2", Toast.LENGTH_SHORT).show();
                    pageLayoutResId = R.layout.fragment_page_third;
                    tutorialItems = new TransformItem[]{
                            TransformItem.create(R.id.ivFirstImage, Direction.RIGHT_TO_LEFT, 0.2f),
                            TransformItem.create(R.id.ivSecondImage, Direction.LEFT_TO_RIGHT, 0.06f),
                            TransformItem.create(R.id.ivThirdImage, Direction.RIGHT_TO_LEFT, 0.08f),
                            TransformItem.create(R.id.ivFourthImage, Direction.LEFT_TO_RIGHT, 0.1f),
                            TransformItem.create(R.id.ivFifthImage, Direction.LEFT_TO_RIGHT, 0.03f),
                            TransformItem.create(R.id.ivSixthImage, Direction.LEFT_TO_RIGHT, 0.09f),
                            TransformItem.create(R.id.ivSeventhImage, Direction.LEFT_TO_RIGHT, 0.14f)
                    };
                    break;
                }


                default: {
                    throw new IllegalArgumentException("Unknown position: " + position);
                }
            }

            return PageOptions.create(pageLayoutResId, position, tutorialItems);
        }
    };

  /*  private final TutorialPageProvider<Fragment> mTutorialPageProvider = new TutorialPageProvider<Fragment>() {
        @NonNull
        @Override
        public Fragment providePage(int position) {
         //   position %= ACTUAL_PAGES_COUNT;
            Toast.makeText(getContext(), "case declaration", Toast.LENGTH_SHORT).show();
            switch (position) {
                case 0:
                    Toast.makeText(getContext(), "case declaration", Toast.LENGTH_SHORT).show();
                    return new FirstCustomPageSupportFragment();
                case 1:
                    return new SecondCustomPageSupportFragment();
                case 2:
                    return new ThirdCustomPageSupportFragment();
                case 3:
                    return new ThirdCustomPageSupportFragment();
                default: {
                    throw new IllegalArgumentException("Unknown position: " + position);
                }
            }
        }
    };*/

    private int[] pagesColors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (pagesColors == null) {
            pagesColors = new int[]{
                    ContextCompat.getColor(getContext(), android.R.color.holo_orange_dark),
                    ContextCompat.getColor(getContext(), android.R.color.holo_green_dark),
                    ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark),
                    ContextCompat.getColor(getContext(), android.R.color.holo_red_dark),
                    ContextCompat.getColor(getContext(), android.R.color.holo_purple),
                    ContextCompat.getColor(getContext(), android.R.color.darker_gray)
            };
        }
        addOnTutorialPageChangeListener(this);
    }

    @Override
    protected TutorialOptions provideTutorialOptions() {
        return newTutorialOptionsBuilder(getContext())
                .setUseInfiniteScroll(true)
                .setPagesColors(pagesColors)
                .setPagesCount(TOTAL_PAGES)
                .setTutorialPageProvider(mTutorialPageOptionsProvider)
                .setIndicatorOptions(IndicatorOptions.newBuilder(getContext())
                        .setElementSizeRes(R.dimen.indicator_size)
                        .setElementSpacingRes(R.dimen.indicator_spacing)
                        .setElementColorRes(android.R.color.darker_gray)
                        .setSelectedElementColor(Color.LTGRAY)
                        .setRenderer(DrawableRenderer.create(getContext()))
                        .build())
                .onSkipClickListener(mOnSkipClickListener)

                //.setTutorialPageProvider(mTutorialPageProvider)
                .build();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.custom_tutorial_layout;
    }

    @Override
    public void onPageChanged(int position) {
        Log.i(TAG, "onPageChanged: position = " + position);

        if (position == TutorialSupportFragment.EMPTY_FRAGMENT_POSITION) {
            Log.i(TAG, "onPageChanged: Empty fragment is visible");
        }
    }
}
