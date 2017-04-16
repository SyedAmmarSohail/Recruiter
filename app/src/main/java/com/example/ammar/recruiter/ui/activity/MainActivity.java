/*
 * Copyright 2015 Rudson Lima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.ammar.recruiter.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.ammar.recruiter.AboutUs;
import com.example.ammar.recruiter.PremiumFrag;
import com.example.ammar.recruiter.R;
import com.example.ammar.recruiter.Setting;
import com.example.ammar.recruiter.ui.fragment.MainFragment;
import com.example.ammar.recruiter.ui.fragment.ViewPagerFragment;

import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.model.HelpLiveo;
import br.liveo.navigationliveo.NavigationLiveo;

public class MainActivity extends NavigationLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;

    @Override
    public void onInt(Bundle savedInstanceState) {

        // User Information
        this.userName.setText("Syed Ammar Sohail");
        this.userEmail.setText("0347-2003234");
        this.userPhoto.setImageResource(R.drawable.ic_launcher);
        this.userBackground.setImageResource(R.drawable.ic_user_background_first);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.Search), R.drawable.ic_launcher);
    //    mHelpLiveo.addSubHeader(getString(R.string.Profile)); //Item subHeader
        mHelpLiveo.add(getString(R.string.Profile), R.drawable.ic_launcher);
        mHelpLiveo.add(getString(R.string.Activity), R.drawable.ic_launcher);
       /* mHelpLiveo.add(getString(R.string.myFamily), R.drawable.ic_launcher);
        mHelpLiveo.add(getString(R.string.zoneAlert), R.drawable.ic_launcher);*/
        mHelpLiveo.addSeparator(); // Item separator
     //   mHelpLiveo.add(getString(R.string.myAccount), R.drawable.ic_launcher);
        mHelpLiveo.add(getString(R.string.premium), R.drawable.ic_launcher);
        mHelpLiveo.add(getString(R.string.about), R.drawable.ic_launcher);
      //  mHelpLiveo.add(getString(R.string.help), R.drawable.ic_launcher);

        //{optional} - Header Customization - method customHeader
//        View mCustomHeader = getLayoutInflater().inflate(R.layout.custom_header_user, this.getListView(), false);
//        ImageView imageView = (ImageView) mCustomHeader.findViewById(R.id.imageView);

        with(this).startingPosition(1) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())

                //{optional} - List Customization "If you remove these methods and the list will take his white standard color"
                //.selectorCheck(R.drawable.selector_check) //Inform the background of the selected item color
                //.colorItemDefault(R.color.nliveo_blue_colorPrimary) //Inform the standard color name, icon and counter
                //.colorItemSelected(R.color.nliveo_purple_colorPrimary) //State the name of the color, icon and meter when it is selected
                //.backgroundList(R.color.nliveo_black_light) //Inform the list of background color
                //.colorLineSeparator(R.color.nliveo_transparent) //Inform the color of the subheader line

                //{optional} - SubHeader Customization
                .colorItemSelected(R.color.nliveo_blue_colorPrimary)
                .colorNameSubHeader(R.color.nliveo_blue_colorPrimary)
                //.colorLineSeparator(R.color.nliveo_blue_colorPrimary)

                .footerItem(R.string.settings, R.mipmap.ic_settings_black_24dp)
                //.footerSecondItem(R.string.settings, R.mipmap.ic_settings_black_24dp)

                //{optional} - Header Customization
                //.customHeader(mCustomHeader)

                //{optional} - Footer Customization
                //.footerNameColor(R.color.nliveo_blue_colorPrimary)
                //.footerIconColor(R.color.nliveo_blue_colorPrimary)
                //.footerBackground(R.color.nliveo_white)

                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                //.setOnClickFooterSecond(onClickFooter)
                .build();

        int position = this.getCurrentPosition();
        this.setElevationToolBar(position != 2 ? 15 : 0);
    }

    @Override
    public void onItemClick(int position) {
        Fragment mFragment = null;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position){
            case 2:
                mFragment = new ViewPagerFragment();
                break;
            case 3:
                mFragment = new ViewPagerFragment();
                break;
            case 4:
                mFragment = new PremiumFrag();
                break;
            case 5:
                startActivity(new Intent(this, AboutUs.class));
                break;
            default:
                mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());
                break;
        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

        setElevationToolBar(position != 2 ? 15 : 0);
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "onClickPhoto :D", Toast.LENGTH_SHORT).show();
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), Setting.class));
            closeDrawer();
        }
    };
}

