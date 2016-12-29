package com.linhnguyen.learningenglish;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends Fragment implements OnClickListener {
    TextView tv, tv_socau, tv_time;
    int Touch = 0;
    quanlycauhoi db;
    RadioButton rb_1, rb_2, rb_3, rb_4;
    float tuvitri, denvitri;
    Animation ani_next, ani_under, ani_pre;
    RadioGroup rg;
    ArrayList<RadioButton> List_Radi = new ArrayList<RadioButton>();
    Boolean Finish = false;// Kiem tra hoan thanh
    CountDownTimer cd;
    List<Cauhoi> ds_Cauhoi = new ArrayList<Cauhoi>();
    int socau = 5;
    int index = 0;
    Button bt_finish;
    Cauhoi cauhientai;
    int caudung = 0;
    AlertDialog.Builder Aler;
    int giay = 15;
    int cdt = 17000;
    ImageView iv_start;
    Toast t;
    View layout;
    ImageView iv;
    Typeface Lua;
    Typeface Indam;
    int cdq;
    Animation Rotate_xuoi;
    Animation Rotate_nguoc;
    CountDownTimer cd_quaynguoc;
    CountDownTimer cd_quayxuoi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_read, container, false);

        bt_finish = (Button) view.findViewById(R.id.button1);
        tv = (TextView) view.findViewById(R.id.textView1);
        rb_1 = (RadioButton) view.findViewById(R.id.radio1);
        rb_2 = (RadioButton) view.findViewById(R.id.radio2);
        rb_3 = (RadioButton) view.findViewById(R.id.radio3);
        rb_4 = (RadioButton) view.findViewById(R.id.radioButton4);
        tv_socau = (TextView) view.findViewById(R.id.textView9);

        // Alert
        Aler = new AlertDialog.Builder(getActivity());
        Aler.setCancelable(false);
        tv_time = (TextView) view.findViewById(R.id.textView2);
        iv_start = (ImageView) view.findViewById(R.id.imageView2);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Blazed.ttf");
        tv_time.setTypeface(font);
        LayoutInflater inf = getLayoutInflater(savedInstanceState);
        layout = inf.inflate(R.layout.layout_toast,
                (ViewGroup) view.findViewById(R.id.toast_layout_root));

        Rotate_xuoi = AnimationUtils.loadAnimation(getActivity(),
                R.anim.rotate_xuoi);
        Rotate_nguoc = AnimationUtils.loadAnimation(getActivity(),
                R.anim.rotate_nguoc);

        Lua = Typeface.createFromAsset(getActivity().getAssets(), "Blazed.ttf");
        Indam = Typeface.createFromAsset(getActivity().getAssets(), "amazonen.ttf");
        // font
        tv.setTypeface(Lua);
        tv_socau.setTypeface(Lua);
        bt_finish.setTypeface(Indam);

        rb_1.setTypeface(Indam);
        rb_2.setTypeface(Indam);
        rb_3.setTypeface(Indam);
        rb_4.setTypeface(Indam);
        rb_1.setTextSize(30);
        rb_2.setTextSize(30);
        rb_3.setTextSize(30);
        rb_4.setTextSize(30);
        tv.setTextSize(30);

        iv = (ImageView) layout.findViewById(R.id.image);

        // Man hinh chao
        AnRadio();
        tv.setText("Press The Button To Start Test");
        tv_time.setTextColor(Color.WHITE);

        // CountDown
        CountDownTime();
        // Su kien Click Chung
        List_Radi.add(rb_1);
        List_Radi.add(rb_2);
        List_Radi.add(rb_3);
        List_Radi.add(rb_4);
        rg = (RadioGroup) view.findViewById(R.id.radioGroup1);
        // Animation
        Anim();

        // Copy Database
        Copydatabase();
        db.xoatraloi();

        // Nut Start
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        tuvitri = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        denvitri = motionEvent.getX();

                        if (Touch == 1) {
                            if (tuvitri < denvitri) // pre
                            {

                                if (index > 0) {
                                    index--;
                                    tv_socau.setText(index + 1 + "/5");

                                    hienthi(index);
                                    tv.startAnimation(ani_pre);
                                    rb_1.startAnimation(ani_under);
                                    rb_2.startAnimation(ani_under);
                                    rb_3.startAnimation(ani_under);
                                    rb_4.startAnimation(ani_under);
                                }

                            } else// next
                            {
                                if (tuvitri > denvitri) {

                                    if (index < socau - 1) {
                                        index++;
                                        tv_socau.setText(index + 1 + "/5");

                                        hienthi(index);
                                        tv.startAnimation(ani_next);
                                        rb_1.startAnimation(ani_under);
                                        rb_2.startAnimation(ani_under);
                                        rb_3.startAnimation(ani_under);
                                        rb_4.startAnimation(ani_under);

                                    }
                                }

                            }

                        }
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
        iv_start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                bt_finish.setVisibility(View.VISIBLE);
                Touch = 1;
                // font
                tv.setTypeface(Indam);

                HienRadio();
                Enable();
                Finish = false;
                cd.cancel();
                index = 0;
                giay = 15;
                caudung = 0;

                ds_Cauhoi = db.layNcaungaunhien(5);

                iv_start.setImageResource(R.drawable.restart);

                db.xoatraloi();

                tv_time.setText("Time: " + giay);
                tv_socau.setText("1/5");

                hienthi(index);

                cd.start();

                rg.clearCheck();

            }
        });

        // Button Finish()

        bt_finish.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Aler.setTitle("Finish");
                if (Socauchualam() != 0) {
                    Aler.setMessage("Number of not completed question: "
                            + Socauchualam() + "\n Do you want to submit? ");
                } else {
                    Aler.setMessage("Do you want to submit? ");
                }

                Aler.setPositiveButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                dialog.cancel();

                            }
                        });

                Aler.setNegativeButton("Ok",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                // Kiem tra cau dung - truy xuat sql
                                KetThucTest(dialog);
                            }

                            public void KetThucTest(DialogInterface dialog) {
                                caudung = 0;
                                for (Cauhoi c : ds_Cauhoi) {
                                    if (c.dapan.equalsIgnoreCase(db
                                            .Laycautraloi(c._id))) {
                                        caudung++;
                                    }
                                }

                                // Hien thi thong tin Dialog
                                if (caudung > 3) {
                                    dialog.cancel();

                                    iv.setImageResource(R.drawable.victory);
                                    TextView tv = (TextView) layout
                                            .findViewById(R.id.text);
                                    tv.setText("Congratulations!"
                                            + "\nCorrect: " + caudung + "/5");
                                    tv.setTextSize(30);
                                    tv.setTypeface(Indam);
                                    Toast t = new Toast(getActivity());
                                    t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                    t.setView(layout);
//									t.setDuration(5000);
                                    t.show();

                                } else {

                                    dialog.cancel();

                                    ImageView iv = (ImageView) layout
                                            .findViewById(R.id.image);
                                    iv.setImageResource(R.drawable.victory2);
                                    TextView tv = (TextView) layout
                                            .findViewById(R.id.text);
                                    tv.setText("Do not be discouraged, try next time! "
                                            + "\nCorrect: " + caudung + "/5");
                                    tv.setTypeface(Indam);
                                    tv.setTextSize(30);
                                    t = new Toast(getActivity());
                                    t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                    t.setView(layout);
//									t.setDuration(5000);
                                    t.show();

                                }

                                // Reset lai so cau dung

                                // Khong cho lam lai, chi dc xem lai
                                Disable();
                                bt_finish.setVisibility(View.GONE);
                                Finish = true;
                                // Hien thi lai
                                hienthi(index);
                                cd.cancel();
                                // bt_finish.setClickable(false);
                            }

                        });

                Aler.show();

            }
        });
        for (RadioButton b : List_Radi) {
            b.setOnClickListener(this);
        }
        return view;
    }

    public void Quayvong() {
        cd_quayxuoi = new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                iv.startAnimation(Rotate_xuoi);
                cd_quayxuoi.cancel();
                cd_quaynguoc.start();

            }
        };
        cd_quaynguoc = new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                iv.startAnimation(Rotate_nguoc);
                cd_quaynguoc.cancel();
                cd_quayxuoi.start();

            }
        };

        cd_quayxuoi.start();

    }

    public void AnRadio() {
        bt_finish.setVisibility(View.GONE);
        rb_1.setVisibility(View.GONE);
        rb_2.setVisibility(View.GONE);
        rb_3.setVisibility(View.GONE);
        rb_4.setVisibility(View.GONE);
    }

    public void HienRadio() {
        bt_finish.setVisibility(View.VISIBLE);
        rb_1.setVisibility(View.VISIBLE);
        rb_2.setVisibility(View.VISIBLE);
        rb_3.setVisibility(View.VISIBLE);
        rb_4.setVisibility(View.VISIBLE);
    }

    private void Anim() {
        ani_next = AnimationUtils.loadAnimation(getActivity(), R.anim.translate_next);
        ani_under = AnimationUtils.loadAnimation(getActivity(), R.anim.translate_under);
        ani_pre = AnimationUtils.loadAnimation(getActivity(), R.anim.translate_pre);
    }

    // Time out
    private void CountDownTime() {
        cd = new CountDownTimer(cdt, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                tv_time.setText("Time: " + (giay--));

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                tv_time.setText("Time Out!");
                // Tinh cau dung
                AlertDialog.Builder dialog = new AlertDialog.Builder(
                        getActivity());
                dialog.setCancelable(false);
                dialog.setTitle("Message!");
                dialog.setMessage("Time Out");
                dialog.setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                caudung = 0;
                                for (Cauhoi c : ds_Cauhoi) {
                                    if (c.dapan.equalsIgnoreCase(db
                                            .Laycautraloi(c._id))) {
                                        caudung++;
                                    }
                                }

                                if (caudung > 3) {

                                    iv.setImageResource(R.drawable.victory);
                                    TextView tv = (TextView) layout
                                            .findViewById(R.id.text);
                                    tv.setText("Congratulations!"
                                            + "\nCorrect: " + caudung + "/5");
                                    tv.setTextSize(30);
                                    tv.setTypeface(Indam);
                                    Toast t = new Toast(getActivity());
                                    t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//									t.setDuration(10000);
                                    t.setView(layout);
                                    t.show();

                                } else {

                                    ImageView iv = (ImageView) layout
                                            .findViewById(R.id.image);
                                    iv.setImageResource(R.drawable.victory2);
                                    TextView tv = (TextView) layout
                                            .findViewById(R.id.text);
                                    tv.setText("Do not be discouraged, try next time! "
                                            + "\nCorrect: " + caudung + "/5");
                                    tv.setTextSize(30);
                                    tv.setTypeface(Indam);
                                    t = new Toast(getActivity());
                                    t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//									t.setDuration(10000);
                                    t.setView(layout);
                                    t.show();

                                }
                                Disable();
                                bt_finish.setVisibility(View.GONE);
                                Finish = true;
                                // Hien thi lai
                                hienthi(index);

                            }
                        });
                dialog.show();

            }
        };
    }

//    @Override
//    public void onBackPressed() {
//        // TODO Auto-generated method stub
//        super.onBackPressed();
//        Touch = 0;
//    }

    private void Copydatabase() {
        db = new quanlycauhoi(getActivity());
        try {
            db.createDataBase();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getActivity(), "bi loi rui", Toast.LENGTH_SHORT).show();
        }
    }

// Kiem tra cau dung

    public void hienthi(int vitri) {
        cauhientai = ds_Cauhoi.get(vitri);
        ;
        tv.setText(cauhientai.cauhoi);
        rb_1.setText(cauhientai.cau_a);
        rb_2.setText(cauhientai.cau_b);
        rb_3.setText(cauhientai.cau_c);
        rb_4.setText(cauhientai.cau_d);
        if (Finish) // Hien thi cau dung
        {
            if (cauhientai.dapan.equalsIgnoreCase(db.Laycautraloi(cauhientai))) {
                if (cauhientai.dapan.equalsIgnoreCase("a"))
                    rb_1.setTextColor(Color.argb(200, 26, 92, 225));
                else {
                    if (cauhientai.dapan.equalsIgnoreCase("b"))
                        rb_2.setTextColor(Color.argb(200, 26, 92, 225));
                    else {
                        if (cauhientai.dapan.equalsIgnoreCase("c"))
                            rb_3.setTextColor(Color.argb(200, 26, 92, 225));
                        else
                            rb_4.setTextColor(Color.argb(200, 26, 92, 225));
                    }

                }
            } else {
                if (cauhientai.dapan.equalsIgnoreCase("a"))
                    rb_1.setTextColor(Color.RED);
                else {
                    if (cauhientai.dapan.equalsIgnoreCase("b"))
                        rb_2.setTextColor(Color.RED);
                    else {
                        if (cauhientai.dapan.equalsIgnoreCase("c"))
                            rb_3.setTextColor(Color.RED);
                        else if (cauhientai.dapan.equalsIgnoreCase("d"))
                            rb_4.setTextColor(Color.RED);
                    }
                }
            }
        } else {
            rb_1.setTextColor(Color.argb(200, 37, 128, 54));
            rb_2.setTextColor(Color.argb(200, 37, 128, 54));
            rb_3.setTextColor(Color.argb(200, 37, 128, 54));
            rb_4.setTextColor(Color.argb(200, 37, 128, 54));
        }

        rg.clearCheck();

        if (db.Laycautraloi(cauhientai).equalsIgnoreCase("a")) {
            rb_1.setChecked(true);
        } else {
            if (db.Laycautraloi(cauhientai).equalsIgnoreCase("b")) {
                rb_2.setChecked(true);
            } else {
                if (db.Laycautraloi(cauhientai).equalsIgnoreCase("c")) {
                    rb_3.setChecked(true);
                } else if (db.Laycautraloi(cauhientai).equalsIgnoreCase("d")) {
                    rb_4.setChecked(true);

                }
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.test, menu);
//        return true;
//    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        RadioButton rb = (RadioButton) v;
        String tl = null;
        if (rb_1.isChecked() == true) {
            tl = "a";
        } else if (rb_2.isChecked() == true) {
            tl = "b";
        } else if (rb_3.isChecked() == true) {
            tl = "c";
        } else if (rb_4.isChecked() == true) {
            tl = "d";
        }
        db.settraloi(cauhientai.getId(), tl);
    }

    public void Disable() {
        rb_1.setEnabled(false);
        rb_2.setEnabled(false);
        rb_3.setEnabled(false);
        rb_4.setEnabled(false);
        bt_finish.setClickable(false);
    }

    public void Enable() {
        rb_1.setEnabled(true);
        rb_2.setEnabled(true);
        rb_3.setEnabled(true);
        rb_4.setEnabled(true);
        bt_finish.setClickable(true);
    }

    public int Socauchualam() {
        int Chuatl = 0;
        for (Cauhoi x : ds_Cauhoi) {
            if (db.Laycautraloi(x).equalsIgnoreCase(""))
                Chuatl++;

        }
        return Chuatl;
    }

}


//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_test);
//
//		bt_finish = (Button) findViewById(R.id.button1);
//		tv = (TextView) findViewById(R.id.textView1);
//		rb_1 = (RadioButton) findViewById(R.id.radio1);
//		rb_2 = (RadioButton) findViewById(R.id.radio2);
//		rb_3 = (RadioButton) findViewById(R.id.radio3);
//		rb_4 = (RadioButton) findViewById(R.id.radioButton4);
//		tv_socau = (TextView) findViewById(R.id.textView9);
//
//		// Alert
//		Aler = new AlertDialog.Builder(TestActivity.this);
//		Aler.setCancelable(false);
//		tv_time = (TextView) findViewById(R.id.textView2);
//		iv_start = (ImageView) findViewById(R.id.imageView2);
//		Typeface font = Typeface.createFromAsset(getAssets(), "Blazed.ttf");
//		tv_time.setTypeface(font);
//		LayoutInflater inf = getLayoutInflater();
//		layout = inf.inflate(R.layout.layout_toast,
//				(ViewGroup) findViewById(R.id.toast_layout_root));
//
//		Rotate_xuoi = AnimationUtils.loadAnimation(TestActivity.this,
//				R.anim.rotate_xuoi);
//		Rotate_nguoc = AnimationUtils.loadAnimation(TestActivity.this,
//				R.anim.rotate_nguoc);
//
//		Lua = Typeface.createFromAsset(getAssets(), "Blazed.ttf");
//		Indam = Typeface.createFromAsset(getAssets(), "amazonen.ttf");
//		// font
//		tv.setTypeface(Lua);
//		tv_socau.setTypeface(Lua);
//		bt_finish.setTypeface(Indam);
//
//		rb_1.setTypeface(Indam);
//		rb_2.setTypeface(Indam);
//		rb_3.setTypeface(Indam);
//		rb_4.setTypeface(Indam);
//		rb_1.setTextSize(30);
//		rb_2.setTextSize(30);
//		rb_3.setTextSize(30);
//		rb_4.setTextSize(30);
//		tv.setTextSize(30);
//
//		iv = (ImageView) layout.findViewById(R.id.image);
//
//		// Man hinh chao
//		AnRadio();
//		tv.setText("Press The Button To Start Test");
//		tv_time.setTextColor(Color.WHITE);
//
//		// CountDown
//		CountDownTime();
//		// Su kien Click Chung
//		List_Radi.add(rb_1);
//		List_Radi.add(rb_2);
//		List_Radi.add(rb_3);
//		List_Radi.add(rb_4);
//		rg = (RadioGroup) findViewById(R.id.radioGroup1);
//		// Animation
//		Anim();
//
//		// Copy Database
//		Copydatabase();
//		db.xoatraloi();
//
//		// Nut Start
//		iv_start.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				bt_finish.setVisibility(View.VISIBLE);
//				Touch = 1;
//				// font
//				tv.setTypeface(Indam);
//
//				HienRadio();
//				Enable();
//				Finish = false;
//				cd.cancel();
//				index = 0;
//				giay = 15;
//				caudung = 0;
//
//				ds_Cauhoi = db.layNcaungaunhien(5);
//
//				iv_start.setImageResource(R.drawable.restart);
//
//				db.xoatraloi();
//
//				tv_time.setText("Time: " + giay);
//				tv_socau.setText("1/5");
//
//				hienthi(index);
//
//				cd.start();
//
//				rg.clearCheck();
//
//			}
//		});
//
//		// Button Finish()
//
//		bt_finish.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//				Aler.setTitle("Finish");
//				if (Socauchualam() != 0) {
//					Aler.setMessage("Number of not completed question: "
//							+ Socauchualam() + "\n Do you want to submit? ");
//				} else {
//					Aler.setMessage("Do you want to submit? ");
//				}
//
//				Aler.setPositiveButton("Cancel",
//						new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//								// TODO Auto-generated method stub
//								dialog.cancel();
//
//							}
//						});
//
//				Aler.setNegativeButton("Ok",
//						new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//								// TODO Auto-generated method stub
//								// Kiem tra cau dung - truy xuat sql
//								KetThucTest(dialog);
//							}
//
//							public void KetThucTest(DialogInterface dialog) {
//								caudung = 0;
//								for (Cauhoi c : ds_Cauhoi) {
//									if (c.dapan.equalsIgnoreCase(db
//											.Laycautraloi(c._id))) {
//										caudung++;
//									}
//								}
//
//								// Hien thi thong tin Dialog
//								if (caudung > 3) {
//									dialog.cancel();
//
//									iv.setImageResource(R.drawable.victory);
//									TextView tv = (TextView) layout
//											.findViewById(R.id.text);
//									tv.setText("Congratulations!"
//											+ "\nCorrect: " + caudung + "/5");
//									tv.setTextSize(30);
//									tv.setTypeface(Indam);
//									Toast t = new Toast(TestActivity.this);
//									t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//									t.setView(layout);
////									t.setDuration(5000);
//									t.show();
//
//								} else {
//
//									dialog.cancel();
//
//									ImageView iv = (ImageView) layout
//											.findViewById(R.id.image);
//									iv.setImageResource(R.drawable.victory2);
//									TextView tv = (TextView) layout
//											.findViewById(R.id.text);
//									tv.setText("Do not be discouraged, try next time! "
//											+ "\nCorrect: " + caudung + "/5");
//									tv.setTypeface(Indam);
//									tv.setTextSize(30);
//									t = new Toast(TestActivity.this);
//									t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//									t.setView(layout);
////									t.setDuration(5000);
//									t.show();
//
//								}
//
//								// Reset lai so cau dung
//
//								// Khong cho lam lai, chi dc xem lai
//								Disable();
//								bt_finish.setVisibility(View.GONE);
//								Finish = true;
//								// Hien thi lai
//								hienthi(index);
//								cd.cancel();
//								// bt_finish.setClickable(false);
//							}
//
//						});
//
//				Aler.show();
//
//			}
//		});
//		for (RadioButton b : List_Radi) {
//			b.setOnClickListener(this);
//		}
//
//	}


