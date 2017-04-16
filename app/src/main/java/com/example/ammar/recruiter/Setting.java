package com.example.ammar.recruiter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Setting extends AppCompatActivity implements OnClickListener,
		OnItemClickListener, OnCheckedChangeListener,
		RadioGroup.OnCheckedChangeListener {

	public static View view;
	TextView batteryLevel, batteryAlert, panicAlert, movTime, speed,
			intervalTime_manually, intervalTime_default, movement_manuallyTv,
			movement_defaultTv;

	AlertDialog levelDialog;
	final String[] batteryLevelPerc = { " 10% ", " 20% ", " 30% ", " 40% " };
	final String[] BatteryALertTime = { " 5min ", " 10min ", " 15min ",
			" 20min " };
	final String[] noMovTime = { " 30min ", " 60min ", " 2hr ", " 3hr ", "1day" };
	final String[] panicAlertSilent = { " 30min ", " 60min ", " 2hr ", " 3hr ",
			"1day" };
	final String[] speedKMHr = { " 60 ", " 70 ", " 80 ", " 90 ", "100" };
	final String[] interval = { " 15sec ", " 30sec ", " 1min ", " 2min " };
	ListView settingCustomListview;
	AlertDialog.Builder builder;
	LayoutInflater inflate;
	View viewforcustomdialog;
	TextView mainHeaderForCustomDialog;
	boolean batteryLevelBool = false, batteryAlertBool = false,
			panicAlertBool = false, movTimeBool = false, speedBool = false,
			intervalTimeBool = false;
	ToggleButton deviceonoff, lowStatusBattery, trackerGPSOnoff,
			trackerDataConn, noMovAlert, panicAlertSwitch, overSpeedAlert,
			twoWayCommun, zoneAlert, updateLocation;
	CheckBox setDefaultLowBatery;
	RadioGroup movement, location;
	RadioButton movement_manually, movement_default, location_manually,
			location_default;
	RelativeLayout back;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.setting);
	//	back = (RelativeLayout) findViewById(R.id.editprofile_back);
		/*back.setOnClickListener(this);*/
		batteryLevel = (TextView) findViewById(R.id.batteryLevel);
		batteryAlert = (TextView) findViewById(R.id.batteryALert);
		panicAlert = (TextView) findViewById(R.id.panicAlert);
		movTime = (TextView) findViewById(R.id.movTime);
		intervalTime_default = (TextView) findViewById(R.id.setting_set_default_update_time);
		speed = (TextView) findViewById(R.id.speed);
		setDefaultLowBatery = (CheckBox) findViewById(R.id.setting_set_default_low_battery_status);

		movement_defaultTv = (TextView) findViewById(R.id.setting_set_default_no_mov_alert);

		deviceonoff = (ToggleButton) findViewById(R.id.setting_device_on_off);
		deviceonoff.setOnCheckedChangeListener(this);
		lowStatusBattery = (ToggleButton) findViewById(R.id.setting_low_battery_status);
		lowStatusBattery.setOnCheckedChangeListener(this);
		trackerGPSOnoff = (ToggleButton) findViewById(R.id.setting_tracker_GPS_on_off);
		trackerGPSOnoff.setOnCheckedChangeListener(this);
		trackerDataConn = (ToggleButton) findViewById(R.id.setting_tracker_data_connectivity_alert);
		trackerDataConn.setOnCheckedChangeListener(this);
		noMovAlert = (ToggleButton) findViewById(R.id.setting_no_movemnt_alert);
		noMovAlert.setOnCheckedChangeListener(this);
		panicAlertSwitch = (ToggleButton) findViewById(R.id.setting_panic_alert);
		panicAlertSwitch.setOnCheckedChangeListener(this);
		overSpeedAlert = (ToggleButton) findViewById(R.id.setting_over_speed);
		overSpeedAlert.setOnCheckedChangeListener(this);
		twoWayCommun = (ToggleButton) findViewById(R.id.setting_two_way_communication);
		twoWayCommun.setOnCheckedChangeListener(this);
		zoneAlert = (ToggleButton) findViewById(R.id.setting_zone_alert);
		zoneAlert.setOnCheckedChangeListener(this);
		updateLocation = (ToggleButton) findViewById(R.id.setting_update_location);
		updateLocation.setOnCheckedChangeListener(this);

		movement = (RadioGroup) findViewById(R.id.movement_radio_group);
		movement.setOnCheckedChangeListener(this);
		location = (RadioGroup) findViewById(R.id.locationradiogroup);
		location.setOnCheckedChangeListener(this);
		movement_default = (RadioButton) findViewById(R.id.movement_radio_group_default);
		movement_manually = (RadioButton) findViewById(R.id.movement_radio_group_manually);
		location_default = (RadioButton) findViewById(R.id.locationradiogroup_default);
		location_manually = (RadioButton) findViewById(R.id.locationradiogroup_manually);
		intervalTime_manually = (TextView) findViewById(R.id.movTime);
		movement_manuallyTv = (TextView) findViewById(R.id.setting_manually_update_time);

		batteryLevel.setOnClickListener(this);
		batteryAlert.setOnClickListener(this);
		panicAlert.setOnClickListener(this);
		movTime.setOnClickListener(this);
		intervalTime_default.setOnClickListener(this);
		speed.setOnClickListener(this);

		ActionBar actionBar = getSupportActionBar();

		if (actionBar != null){
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeButtonEnabled(true);
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.batteryLevel:

			inflate = getLayoutInflater();
			viewforcustomdialog = inflate.inflate(
					R.layout.setting_custom_dialog_list, null, false);

			settingCustomListview = (ListView) viewforcustomdialog
					.findViewById(R.id.setting_custom_listview);
			settingCustomListview.setAdapter(new SettingAdapter(
					getApplicationContext(), R.layout.setting_custom_dialog,
					batteryLevelPerc));
			settingCustomListview.setOnItemClickListener(this);
			mainHeaderForCustomDialog = (TextView) viewforcustomdialog
					.findViewById(R.id.setting_main_Header);
			mainHeaderForCustomDialog.setText("Battery Level");
			builder = new AlertDialog.Builder(Setting.this);
			builder.setView(viewforcustomdialog);
			levelDialog = builder.create();
			levelDialog.show();
			batteryLevelBool = true;
			batteryAlertBool = false;
			panicAlertBool = false;
			movTimeBool = false;
			speedBool = false;
			intervalTimeBool = false;
			break;

		case R.id.batteryALert:
			inflate = getLayoutInflater();
			viewforcustomdialog = inflate.inflate(
					R.layout.setting_custom_dialog_list, null, false);

			settingCustomListview = (ListView) viewforcustomdialog
					.findViewById(R.id.setting_custom_listview);
			settingCustomListview.setAdapter(new SettingAdapter(
					getApplicationContext(), R.layout.setting_custom_dialog,
					BatteryALertTime));
			settingCustomListview.setOnItemClickListener(this);
			mainHeaderForCustomDialog = (TextView) viewforcustomdialog
					.findViewById(R.id.setting_main_Header);
			mainHeaderForCustomDialog.setText("Battery Alert");

			builder = new AlertDialog.Builder(Setting.this);
			builder.setView(viewforcustomdialog);
			levelDialog = builder.create();
			levelDialog.show();
			batteryAlertBool = true;
			panicAlertBool = false;
			movTimeBool = false;
			speedBool = false;
			intervalTimeBool = false;
			batteryLevelBool = false;
			break;

		case R.id.panicAlert:
			inflate = getLayoutInflater();
			viewforcustomdialog = inflate.inflate(
					R.layout.setting_custom_dialog_list, null, false);

			settingCustomListview = (ListView) viewforcustomdialog
					.findViewById(R.id.setting_custom_listview);
			settingCustomListview.setAdapter(new SettingAdapter(
					getApplicationContext(), R.layout.setting_custom_dialog,
					panicAlertSilent));
			settingCustomListview.setOnItemClickListener(this);
			mainHeaderForCustomDialog = (TextView) viewforcustomdialog
					.findViewById(R.id.setting_main_Header);
			mainHeaderForCustomDialog.setText("Panic ALert");

			builder = new AlertDialog.Builder(Setting.this);
			builder.setView(viewforcustomdialog);
			levelDialog = builder.create();
			levelDialog.show();
			panicAlertBool = true;
			batteryAlertBool = false;
			movTimeBool = false;
			speedBool = false;
			intervalTimeBool = false;
			batteryLevelBool = false;

			break;
		case R.id.movTime:
			inflate = getLayoutInflater();
			viewforcustomdialog = inflate.inflate(
					R.layout.setting_custom_dialog_list, null, false);

			settingCustomListview = (ListView) viewforcustomdialog
					.findViewById(R.id.setting_custom_listview);
			settingCustomListview.setAdapter(new SettingAdapter(
					getApplicationContext(), R.layout.setting_custom_dialog,
					noMovTime));
			settingCustomListview.setOnItemClickListener(this);
			mainHeaderForCustomDialog = (TextView) viewforcustomdialog
					.findViewById(R.id.setting_main_Header);
			mainHeaderForCustomDialog.setText("No Movement Alert");

			builder = new AlertDialog.Builder(Setting.this);
			builder.setView(viewforcustomdialog);
			levelDialog = builder.create();
			levelDialog.show();
			movTimeBool = true;
			batteryAlertBool = false;
			panicAlertBool = false;
			speedBool = false;
			batteryLevelBool = false;
			intervalTimeBool = false;

			break;

		case R.id.speed:
			inflate = getLayoutInflater();
			viewforcustomdialog = inflate.inflate(
					R.layout.setting_custom_dialog_list, null, false);

			settingCustomListview = (ListView) viewforcustomdialog
					.findViewById(R.id.setting_custom_listview);
			settingCustomListview.setAdapter(new SettingAdapter(
					getApplicationContext(), R.layout.setting_custom_dialog,
					speedKMHr));
			settingCustomListview.setOnItemClickListener(this);
			mainHeaderForCustomDialog = (TextView) viewforcustomdialog
					.findViewById(R.id.setting_main_Header);
			mainHeaderForCustomDialog.setText("Over-Speed ALert");

			builder = new AlertDialog.Builder(Setting.this);
			builder.setView(viewforcustomdialog);
			levelDialog = builder.create();
			levelDialog.show();
			speedBool = true;
			batteryAlertBool = false;
			panicAlertBool = false;
			movTimeBool = false;
			batteryLevelBool = false;
			intervalTimeBool = false;

			break;

		// case R.id.interval:
		// inflate = getActivity().getLayoutInflater();
		// viewforcustomdialog = inflate.inflate(
		// R.layout.setting_custom_dialog_list, null, false);
		//
		// settingCustomListview = (ListView) viewforcustomdialog
		// .findViewById(R.id.setting_custom_listview);
		// settingCustomListview.setAdapter(new SettingAdapter(getActivity()
		// .getApplicationContext(), R.layout.setting_custom_dialog,
		// interval));
		// settingCustomListview.setOnItemClickListener(this);
		// mainHeaderForCustomDialog = (TextView) viewforcustomdialog
		// .findViewById(R.id.setting_main_Header);
		// mainHeaderForCustomDialog.setText("Update Intervals");
		//
		// builder = new AlertDialog.Builder(getActivity());
		// builder.setView(viewforcustomdialog);
		// levelDialog = builder.create();
		// levelDialog.show();
		// intervalTimeBool = true;
		// batteryAlertBool = false;
		// panicAlertBool = false;
		// movTimeBool = false;
		// batteryLevelBool = false;
		//
		// break;
		/*case R.id.editprofile_back:
			// startActivity(new Intent(this, MainActivity.class));
			finish();
			overridePendingTransition(R.anim.left_to_right,
					R.anim.right_to_left);

			break;*/
		}
	}

	class SettingAdapter extends ArrayAdapter<String> {

		Context context;
		String[] array;

		public SettingAdapter(Context context, int resource, String[] objects) {
			super(context, R.layout.setting_custom_dialog, objects);
			// TODO Auto-generated constructor stub
			this.context = context;
			array = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.setting_custom_dialog,
						parent, false);
				holder.text = (TextView) convertView
						.findViewById(R.id.setting_custom_text);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.text.setText("" + array[position]);

			return convertView;
		}

		class ViewHolder {
			TextView text;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if (batteryLevelBool) {
			batteryLevel.setText("" + batteryLevelPerc[position]);
			levelDialog.dismiss();
		}
		if (batteryAlertBool) {
			batteryAlert.setText("" + BatteryALertTime[position]);
			levelDialog.dismiss();
		}
		if (panicAlertBool) {
			panicAlert.setText("" + panicAlertSilent[position]);
			levelDialog.dismiss();
		}
		if (movTimeBool) {
			movTime.setText("" + noMovTime[position]);
			levelDialog.dismiss();
			movement_defaultTv.setEnabled(false);

		}

		if (speedBool) {
			speed.setText("" + speedKMHr[position]);
			levelDialog.dismiss();

		}
		if (intervalTimeBool) {
			movement_manuallyTv.setText("" + interval[position]);
			levelDialog.dismiss();
		}

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.setting_device_on_off:

			break;
		case R.id.setting_low_battery_status:
			if (lowStatusBattery.isChecked()) {
				batteryLevel.setEnabled(true);
				batteryAlert.setEnabled(true);
				setDefaultLowBatery.setEnabled(true);
			} else {
				batteryLevel.setEnabled(false);
				batteryAlert.setEnabled(false);
				setDefaultLowBatery.setEnabled(false);
			}
			break;
		case R.id.setting_tracker_GPS_on_off:
			break;
		case R.id.setting_tracker_data_connectivity_alert:
			break;
		case R.id.setting_no_movemnt_alert:
			if (noMovAlert.isChecked()) {
				movTime.setEnabled(true);
				movement_defaultTv.setEnabled(true);
				movement_manuallyTv.setEnabled(true);
				movement_default.setEnabled(true);
				movement_manually.setEnabled(true);

			} else {
				movTime.setEnabled(false);
				movement_defaultTv.setEnabled(false);
				movement_manuallyTv.setEnabled(false);
				movement_default.setEnabled(false);
				movement_manually.setEnabled(false);

			}
			break;
		case R.id.setting_over_speed:
			if (overSpeedAlert.isChecked()) {
				speed.setEnabled(true);

			} else {
				speed.setEnabled(false);
			}
			break;
		case R.id.setting_panic_alert:
			if (panicAlertSwitch.isChecked()) {
				panicAlert.setEnabled(true);

			} else {
				panicAlert.setEnabled(false);
			}
			break;
		case R.id.setting_two_way_communication:
			break;
		case R.id.setting_zone_alert:
			break;
		case R.id.setting_update_location:
			// if (updateLocation.isChecked()) {
			// intervalTime.setEnabled(true);
			// //setDefaultUpdateLoca.setEnabled(true);
			//
			// } else {
			// intervalTime.setEnabled(false);
			// //setDefaultUpdateLoca.setEnabled(false);
			// }
			break;

		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (group.getId()) {
		case R.id.movement_radio_group:
			if (checkedId == R.id.movement_radio_group_default) {
				movement_defaultTv.setEnabled(true);
				intervalTime_manually.setEnabled(false);

			} else if (checkedId == R.id.movement_radio_group_manually) {
				inflate = getLayoutInflater();
				viewforcustomdialog = inflate.inflate(
						R.layout.setting_custom_dialog_list, null, false);

				settingCustomListview = (ListView) viewforcustomdialog
						.findViewById(R.id.setting_custom_listview);
				settingCustomListview.setAdapter(new SettingAdapter(
						getApplicationContext(),
						R.layout.setting_custom_dialog, noMovTime));
				settingCustomListview.setOnItemClickListener(this);
				mainHeaderForCustomDialog = (TextView) viewforcustomdialog
						.findViewById(R.id.setting_main_Header);
				mainHeaderForCustomDialog.setText("No Movement Alert");

				builder = new AlertDialog.Builder(Setting.this);
				builder.setView(viewforcustomdialog);
				levelDialog = builder.create();
				levelDialog.show();

				movement_defaultTv.setEnabled(false);
				intervalTime_manually.setEnabled(true);

				movTimeBool = true;
				batteryAlertBool = false;
				panicAlertBool = false;
				speedBool = false;
				batteryLevelBool = false;
				intervalTimeBool = false;
			}
			break;

		case R.id.locationradiogroup:
			if (checkedId == R.id.locationradiogroup_default) {

				intervalTime_default.setText("60sec");
				intervalTime_default.setEnabled(true);

				movement_manuallyTv.setEnabled(false);

			} else if (checkedId == R.id.locationradiogroup_manually) {
				inflate = getLayoutInflater();
				viewforcustomdialog = inflate.inflate(
						R.layout.setting_custom_dialog_list, null, false);

				settingCustomListview = (ListView) viewforcustomdialog
						.findViewById(R.id.setting_custom_listview);
				settingCustomListview.setAdapter(new SettingAdapter(
						getApplicationContext(),
						R.layout.setting_custom_dialog, interval));
				settingCustomListview.setOnItemClickListener(this);
				mainHeaderForCustomDialog = (TextView) viewforcustomdialog
						.findViewById(R.id.setting_main_Header);
				mainHeaderForCustomDialog.setText("Update Intervals");

				builder = new AlertDialog.Builder(Setting.this);
				builder.setView(viewforcustomdialog);
				levelDialog = builder.create();
				levelDialog.show();

				movement_manuallyTv.setEnabled(true);
				intervalTime_default.setEnabled(false);

				intervalTimeBool = true;
				batteryAlertBool = false;
				panicAlertBool = false;
				movTimeBool = false;
				batteryLevelBool = false;

			}
			break;
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
