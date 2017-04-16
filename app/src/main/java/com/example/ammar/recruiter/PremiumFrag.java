package com.example.ammar.recruiter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ammar.recruiter.ui.activity.ExpandAnim;

public class PremiumFrag extends Fragment {

	public static View view;
	ListView paymentType;
	String[] payType = { "EasyPesa", "TimePay", "MobiPesa" };
	TextView payTimeTv;
	LinearLayout listFirstItem, listSecondItem, listThirdItem, listFourItem,
			premiumyearly, premiumbasic, premiumadvance, premiumtracker,
			termsone, termtwo;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
				parent.removeView(view);
		}
		view = inflater.inflate(R.layout.premium, container, false);

		listFirstItem = (LinearLayout) view.findViewById(R.id.first_layout);
		premiumyearly = (LinearLayout) view.findViewById(R.id.premium_yearly);
		listFirstItem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ExpandAnim ani = new ExpandAnim(premiumyearly, 100);
				premiumyearly.startAnimation(ani);
				premiumyearly.setVisibility(View.VISIBLE);
				final RadioButton one;
				final RadioButton two;

				final TextView totalCost;
				Button purchase;
				one = (RadioButton) premiumyearly
						.findViewById(R.id.premium_yearly_oneyear_radio);
				two = (RadioButton) premiumyearly
						.findViewById(R.id.premium_yearly_twoyear_radio);
//
				totalCost = (TextView) premiumyearly
						.findViewById(R.id.premium_yearly_totacost);
				purchase = (Button) premiumyearly
						.findViewById(R.id.premium_yearly_purchase);
				if (one.isChecked()) {
					two.setChecked(false);

				}
				if (two.isChecked()) {
					one.setChecked(false);

				}
				one.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (one.isChecked()) {
							two.setChecked(false);
							totalCost.setText("Rs 105");

						}
					}
				});
				two.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (two.isChecked()) {
							one.setChecked(false);
							totalCost.setText("Rs 208");

						}
					}
				});
				purchase.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialogwork();
					}
				});
			}
		});

		listSecondItem = (LinearLayout) view.findViewById(R.id.second_layout);
		premiumtracker = (LinearLayout) view.findViewById(R.id.premium_tracker);
		listSecondItem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ExpandAnim ani = new ExpandAnim(premiumtracker,100);
				premiumtracker.startAnimation(ani);
				premiumtracker.setVisibility(View.VISIBLE);
				final RadioButton one;
				final RadioButton two;
				final RadioButton three;

				final TextView totalCost;
				Button purchase;
				one = (RadioButton) premiumtracker
						.findViewById(R.id.premium_tracker_one);
				two = (RadioButton) premiumtracker
						.findViewById(R.id.premium_tracker_two);
				three = (RadioButton) premiumtracker
						.findViewById(R.id.premium_tracker_three);

				totalCost = (TextView) premiumtracker
						.findViewById(R.id.premium_tracker_totalCost);
				purchase = (Button) premiumtracker
						.findViewById(R.id.premium_tracker_purchaseBtn);

				if (one.isChecked()) {
					two.setChecked(false);
					three.setChecked(false);

				}
				if (two.isChecked()) {
					one.setChecked(false);
					three.setChecked(false);

				}
				if (three.isChecked()) {
					two.setChecked(false);
					one.setChecked(false);

				}
				one.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (one.isChecked()) {
							two.setChecked(false);
							three.setChecked(false);
							totalCost.setText("Rs 105");

						}
					}
				});
				two.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (two.isChecked()) {
							one.setChecked(false);
							three.setChecked(false);
							totalCost.setText("Rs 208");

						}
					}
				});
				three.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (three.isChecked()) {
							two.setChecked(false);
							one.setChecked(false);
							totalCost.setText("Rs 350");

						}
					}
				});
				purchase.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialogwork();
					}
				});

			}
		});

		listThirdItem = (LinearLayout) view.findViewById(R.id.third_layout);
		premiumbasic = (LinearLayout) view.findViewById(R.id.premium_basic);
		termsone = (LinearLayout) view.findViewById(R.id.termsone);
		termtwo = (LinearLayout) view.findViewById(R.id.termstwo);

		listThirdItem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ExpandAnim ani = new ExpandAnim(premiumbasic, 100);
				premiumbasic.startAnimation(ani);
				// termtwo.setVisibility(View.GONE);
				termsone.setVisibility(View.VISIBLE);
				premiumbasic.setVisibility(View.VISIBLE);

				final RadioButton basic;
				final RadioButton advance;
				final TextView totalCost;
				Button purchase;
				basic = (RadioButton) premiumbasic
						.findViewById(R.id.premium_basic_basicRadio);
				advance = (RadioButton) premiumbasic
						.findViewById(R.id.premium_basic_advanRad);
				totalCost = (TextView) premiumbasic
						.findViewById(R.id.premium_basic_totalCost);
				purchase = (Button) premiumbasic
						.findViewById(R.id.premium_basic_Purchasebtn);

				if (basic.isChecked() && !advance.isChecked()) {
					advance.setChecked(false);
					termsone.setVisibility(View.VISIBLE);
					PremiumFrag.this.termtwo.setVisibility(View.GONE);

				}
				if (!basic.isChecked() && advance.isChecked()) {
					basic.setChecked(false);
					termtwo.setVisibility(View.VISIBLE);
					PremiumFrag.this.termsone.setVisibility(View.GONE);

				}

				basic.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (basic.isChecked()) {
							advance.setChecked(false);
							termsone.setVisibility(View.VISIBLE);
							PremiumFrag.this.termtwo.setVisibility(View.GONE);
							totalCost.setText("Rs 105");

						}
					}

				});
				advance.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (advance.isChecked()) {
							basic.setChecked(false);

							termsone.setVisibility(View.GONE);
							PremiumFrag.this.termtwo
									.setVisibility(View.VISIBLE);
							totalCost.setText("Rs 208");

						}
					}

				});
				purchase.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialogwork();
					}
				});

			}
		});
		// listFourItem = (LinearLayout) view.findViewById(R.id.four_layout);
		// premiumadvance = (LinearLayout)
		// view.findViewById(R.id.premium_advance);
		// listFourItem.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// ExpandAnim ani = new ExpandAnim(premiumadvance, 100);
		// premiumadvance.startAnimation(ani);
		// termsone.setVisibility(View.VISIBLE);
		// termtwo.setVisibility(View.VISIBLE);
		// premiumadvance.setVisibility(View.VISIBLE);
		//
		// }
		// });

		/*
		 * here google integration for payment via credit card
		 */

		return view;

	}

	public void paymentType() {
		final Dialog dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

		wmlp.x = (int) payTimeTv.getX(); // x position
		wmlp.y = (int) payTimeTv.getX() - 100; // y position

		LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
		View view=inflater.inflate(R.layout.prompts, null);
		paymentType = (ListView) view.findViewById(R.id.list_for_geofence);
		TextView tv=(TextView) view.findViewById(R.id.promt_textView1);
		tv.setText("Select Payment Type");
		
		dialog.setContentView(view);
		dialog.getWindow().setBackgroundDrawableResource(
				android.R.color.holo_orange_light);

		dialog.show();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity()
				.getApplicationContext(), R.layout.simple_text, R.id.simple_tv,
				payType);
		paymentType.setAdapter(adapter);
		paymentType.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String itemValue = (String) paymentType
						.getItemAtPosition(position);

				payTimeTv.setText(itemValue);

				dialog.cancel();

			}

		});
	}

	public void dialogwork() {
		final Dialog dialogOne = new Dialog(getActivity());
		dialogOne.requestWindowFeature(Window.FEATURE_NO_TITLE);

		dialogOne.getWindow().setBackgroundDrawableResource(
				android.R.color.holo_blue_bright);

		LayoutInflater in = (LayoutInflater) getActivity()
				.getApplicationContext().getSystemService(
						getActivity().LAYOUT_INFLATER_SERVICE);

		View vi = in.inflate(R.layout.payment_dialog, null);
		Button localBtn = (Button) vi
				.findViewById(R.id.payment_dialog_LocalBtn);
		localBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialogOne.dismiss();
				final Dialog dialogtwo = new Dialog(getActivity());
				dialogtwo.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialogtwo.getWindow().setBackgroundDrawableResource(
						android.R.color.holo_blue_bright);
				LayoutInflater in = (LayoutInflater) getActivity()
						.getApplicationContext().getSystemService(
								getActivity().LAYOUT_INFLATER_SERVICE);
				View vi = in.inflate(R.layout.payment_dialog_two, null);
				Button submit = (Button) vi
						.findViewById(R.id.payment_dial_two_submit);
				payTimeTv = (TextView) vi
						.findViewById(R.id.payment_dial_two_pay_typ);
				payTimeTv.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						paymentType();

					}
				});
				submit.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialogtwo.dismiss();
						final Dialog dialogtwo = new Dialog(getActivity());
						dialogtwo.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialogtwo.getWindow().setBackgroundDrawableResource(
								android.R.color.holo_blue_bright);
						LayoutInflater in = (LayoutInflater) getActivity()
								.getApplicationContext().getSystemService(
										getActivity().LAYOUT_INFLATER_SERVICE);
						View vi = in
								.inflate(R.layout.payment_dialog_four, null);
						Button submi = (Button) vi
								.findViewById(R.id.payment_dial_btn);
						submi.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialogtwo.dismiss();
								final Dialog dialog = new Dialog(getActivity());
								dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
								dialog.getWindow().setBackgroundDrawableResource(
										android.R.color.holo_blue_bright);
								LayoutInflater in = (LayoutInflater) getActivity()
										.getApplicationContext()
										.getSystemService(
												getActivity().LAYOUT_INFLATER_SERVICE);
								View vi = in.inflate(
										R.layout.payment_dialog_three, null);
								Button btn = (Button) vi
										.findViewById(R.id.payment_dial_three_btn);
								btn.setOnClickListener(new View.OnClickListener() {

									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										dialog.dismiss();

									}
								});
								dialog.setContentView(vi);

								dialog.getWindow()
										.setBackgroundDrawableResource(
												android.R.color.primary_text_dark);
								dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

								dialog.show();
							}
						});
						dialogtwo.setContentView(vi);

						dialogtwo.getWindow().setBackgroundDrawableResource(
								android.R.color.holo_green_light);
						dialogtwo.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

						dialogtwo.show();

					}
				});
				dialogtwo.setContentView(vi);

				dialogtwo.getWindow().setBackgroundDrawableResource(
						android.R.color.transparent);
				dialogtwo.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

				dialogtwo.show();

			}
		});
		Button creditBtn = (Button) vi
				.findViewById(R.id.payment_dialog_InternationalBtn);
		creditBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		dialogOne.setContentView(vi);
		dialogOne.show();
	}
}
