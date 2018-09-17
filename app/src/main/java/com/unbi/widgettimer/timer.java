package com.unbi.widgettimer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ncorti.slidetoact.SlideToActView;
import com.shawnlin.numberpicker.NumberPicker;
import com.tuyenmonkey.AutoFillEditText;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;

public class timer extends AppCompatActivity {
    private SimpleDateFormat dateFormat;
    public static List<String> wordList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        dateFormat = new SimpleDateFormat("HH:mm:ss", getResources().getConfiguration().locale);
        final AutoFillEditText editText = (AutoFillEditText) findViewById(R.id.textedit);
        SharedPreferences prfs = getSharedPreferences("WORDS", Context.MODE_PRIVATE);
        String words = prfs.getString("words", "Timer,Countdown,");
        String[] wordsme = words.split(",");
        for (String s : wordsme) {
            //Do your stuff here
//            System.out.println(s);
            wordList.add(s);
        }
        editText.addSuggestions(wordList);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        final NumberPicker hour1 = findViewById(R.id.hour1);
        hour1.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        hour1.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        hour1.setTypeface(getString(R.string.roboto_light));
        hour1.setTypeface(R.string.roboto_light, Typeface.NORMAL);
        hour1.setTypeface(R.string.roboto_light);
        String[] data = {"0", "1", "2", "0", "1", "2", "0", "1", "2"};
        hour1.setMinValue(1);
        hour1.setMaxValue(data.length);
        hour1.setDisplayedValues(data);
        hour1.setValue(1);
        // Set fading edge enabled
        hour1.setFadingEdgeEnabled(true);
        // Set scroller enabled
        hour1.setScrollerEnabled(true);
        // Set wrap selector wheel
        hour1.setWrapSelectorWheel(true);
        final NumberPicker hour2 = findViewById(R.id.hour2);
        hour2.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        hour2.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        hour2.setTypeface(getString(R.string.roboto_light));
        setnum(hour2, 9);
        final NumberPicker min1 = findViewById(R.id.minute1);
        min1.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        min1.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        min1.setTypeface(getString(R.string.roboto_light));
        setnum(min1, 5);
        final NumberPicker min2 = findViewById(R.id.minute2);
        min2.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        min2.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        min2.setTypeface(getString(R.string.roboto_light));
        setnum(min2, 9);
        final NumberPicker sec1 = findViewById(R.id.second1);
        sec1.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        sec1.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        sec1.setTypeface(getString(R.string.roboto_light));
        setnum(sec1, 5);
        final NumberPicker sec2 = findViewById(R.id.second2);
        sec2.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        sec2.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        sec2.setTypeface(getString(R.string.roboto_light));
        setnum(sec2, 9);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("WORDS", MODE_PRIVATE);
        setupEventCallbacks(hour1, hour2, min1, min2, sec1, sec2, editText, pref);
        setupUI(findViewById(R.id.linearLayout), editText);
//
//
//        // Set typeface
//        numberPicker.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
//        numberPicker.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
//        numberPicker.setTypeface(getString(R.string.roboto_light));
//        numberPicker.setTypeface(R.string.roboto_light, Typeface.NORMAL);
//        numberPicker.setTypeface(R.string.roboto_light);
//
//        // Set fading edge enabled
//        numberPicker.setFadingEdgeEnabled(true);
//
//        // Set scroller enabled
//        numberPicker.setScrollerEnabled(true);
//
//        // Set wrap selector wheel
//        numberPicker.setWrapSelectorWheel(true);
//
//        // OnClickListener
//        numberPicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("TAG", "Click on current value");
//            }
//        });
//
//        // OnValueChangeListener
//        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                Log.d("TAG", String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
//            }
//        });
        hour1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("hour1", String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
                if (newVal == 3 || newVal == 6 || newVal == 9) {
                    hour2.setMaxValue(3);
                } else {
                    hour2.setMaxValue(9);
                }
                hour1.setWrapSelectorWheel(true);
                hour2.setWrapSelectorWheel(true);
            }
        });
        hour2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("hou2", String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
//                if (hour1.getValue()==3||hour1.getValue()==6||hour1.getValue()==9){
//                    if (oldVal == 3 & newVal == 0) {
//                        int sec1val = hour1.getValue();
//                        sec1val = sec1val + 1;
//                        hour1.setValue(sec1val);
//                    }
//                    if (oldVal == 0 & newVal == 3) {
//                        int sec1val = hour1.getValue();
//                        sec1val = sec1val - 1;
//                        hour1.setValue(sec1val);
//                    }
//                } else {
//                    if (oldVal == 9 & newVal == 0) {
//                        int sec1val = hour1.getValue();
//                        sec1val = sec1val + 1;
//                        hour1.setValue(sec1val);
//                    }
//                    if (oldVal == 0 & newVal == 9) {
//                        int sec1val = hour1.getValue();
//                        sec1val = sec1val - 1;
//                        hour1.setValue(sec1val);
//                    }
//                }
//                if (hour1.getValue()==3||hour1.getValue()==6||hour1.getValue()==9) {
//                    hour2.setMaxValue(3);
//                } else {
//                    hour2.setMaxValue(9);
//                }
//                hour1.setWrapSelectorWheel(true);
//                hour2.setWrapSelectorWheel(true);
            }
        });
        min1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("min1", String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
            }
        });
        min2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("min2", String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
                if (oldVal == 9 & newVal == 0) {
                    int sec1val = min1.getValue();
                    sec1val = sec1val + 1;
                    min1.setValue(sec1val);
                }
                if (oldVal == 0 & newVal == 9) {
                    int sec1val = min1.getValue();
                    sec1val = sec1val - 1;
                    min1.setValue(sec1val);
                }
            }
        });
        sec1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("sec1", String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
            }
        });
        sec2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("sec2", String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
                if (oldVal == 9 & newVal == 0) {
                    int sec1val = sec1.getValue();
                    sec1val = sec1val + 1;
                    sec1.setValue(sec1val);
                }
                if (oldVal == 0 & newVal == 9) {
                    int sec1val = sec1.getValue();
                    sec1val = sec1val - 1;
                    sec1.setValue(sec1val);
                }
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setCursorVisible(true);
            }
        });
        hour1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour1.setValue(1);
                hour2.setValue(0);
                hour2.setMaxValue(9);
            }
        });
        hour2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour1.setValue(1);
                hour2.setValue(0);
                hour2.setMaxValue(9);
            }
        });
        min1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min1.setValue(0);
                min2.setValue(0);
            }
        });
        min2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min1.setValue(0);
                min2.setValue(0);
            }
        });
        sec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sec1.setValue(0);
                sec2.setValue(0);
            }
        });
        sec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sec1.setValue(0);
                sec2.setValue(0);
            }
        });
    }


    private static void setnum(NumberPicker numberPicker, int max) {
        // Set typeface
        numberPicker.setTypeface(R.string.roboto_light, Typeface.NORMAL);
        numberPicker.setTypeface(R.string.roboto_light);
        // Set fading edge enabled
        numberPicker.setFadingEdgeEnabled(true);
        numberPicker.setMaxValue(max);
        // Set scroller enabled
        numberPicker.setScrollerEnabled(true);
        // Set wrap selector wheel
        numberPicker.setWrapSelectorWheel(true);
        // OnValueChangeListener
    }

    private void setupEventCallbacks(final NumberPicker h1, final NumberPicker h2, final NumberPicker m1, final NumberPicker m2, final NumberPicker s1, final NumberPicker s2, final AutoFillEditText edittext, final SharedPreferences spref) {
        final SlideToActView slide = findViewById(R.id.timerbutton);
        slide.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NonNull SlideToActView view) {
                Log.d("PRESS", "\n" + getTime() + " onSlideComplete");
                //TODO DESTRO AND SEND INTENT
                timeobject timeobjects = gettimertime(h1, h2, m1, m2, s1, s2, edittext);
                long timertime = timerdonow(timeobjects, spref, getApplicationContext());
//                if(timertime==0){return;}
                if (!MainActivity.getskipui()) {
                    broadcastIntentskipui(timertime, timeobjects.label);
                } else {
                    broadcastIntent(timertime, timeobjects.label);
                }

                    finish();

            }
        });
        slide.setOnSlideResetListener(new SlideToActView.OnSlideResetListener() {
            @Override
            public void onSlideReset(@NonNull SlideToActView view) {
                Log.d("PRESS", "\n" + getTime() + " onSlideReset");
            }
        });
        slide.setOnSlideToActAnimationEventListener(new SlideToActView.OnSlideToActAnimationEventListener() {
            @Override
            public void onSlideCompleteAnimationStarted(@NonNull SlideToActView view, float threshold) {
                Log.d("PRESS", "\n" + getTime() + " onSlideCompleteAnimationStarted - " + threshold + "");
            }

            @Override
            public void onSlideCompleteAnimationEnded(@NonNull SlideToActView view) {
                Log.d("PRESS", "\n" + getTime() + " onSlideCompleteAnimationEnded");
            }

            @Override
            public void onSlideResetAnimationStarted(@NonNull SlideToActView view) {
                Log.d("PRESS", "\n" + getTime() + " onSlideResetAnimationStarted");
            }

            @Override
            public void onSlideResetAnimationEnded(@NonNull SlideToActView view) {
                Log.d("PRESS", "\n" + getTime() + " onSlideResetAnimationEnded");
            }
        });
    }

    private String getTime() {
        return dateFormat.format(new Date());
    }

    private static timeobject gettimertime(NumberPicker hour1, NumberPicker hour2, NumberPicker min1, NumberPicker min2, NumberPicker sec1, NumberPicker sec2, AutoFillEditText editText) {
        timeobject timeob = new timeobject();
        int h1 = hour1.getValue();
        if (h1 == 1 || h1 == 4 || h1 == 7) {
            h1 = 0;
        }
        if (h1 == 2 || h1 == 5 || h1 == 8) {
            h1 = 1;
        }
        if (h1 == 3 || h1 == 6 || h1 == 9) {
            h1 = 2;
        }
        int h2 = hour2.getValue();
        timeob.hour = String.valueOf(h1) + String.valueOf(h2);
        int m1 = min1.getValue();
        int m2 = min2.getValue();
        timeob.min = String.valueOf(m1) + String.valueOf(m2);
        int s1 = sec1.getValue();
        int s2 = sec2.getValue();
        timeob.sec = String.valueOf(s1) + String.valueOf(s2);
        timeob.label = String.valueOf(editText.getText());
        return timeob;
    }

    private static long timerdonow(timeobject timeobj, SharedPreferences pref, Context context) {
        if (timeobj.label == null || timeobj.label.equals("")) {
            timeobj.label = "Timer";
        }
        Log.d("TSSSSSSSSS", timeobj.label);
        Log.d(timeobj.label, String.valueOf(timeobj.hour) + "  " + String.valueOf(timeobj.min) + "   " + String.valueOf(timeobj.sec));
        wordList.add(timeobj.label);
// add elements to al, including duplicates
        Set<String> hs = new HashSet<>();
        hs.addAll(wordList);
        wordList.clear();
        wordList.addAll(hs);
        String[] stockArr = new String[wordList.size()];
        stockArr = wordList.toArray(stockArr);
        StringBuilder builder = new StringBuilder();
        for (String s : stockArr) {
            builder.append(s);
            builder.append(",");
        }
        String str = builder.toString();
        Log.d("GHJK", str);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("words", str);
        editor.apply();
        long i = 0;
        String string = timeobj.hour + ":" + timeobj.min + ":" + timeobj.sec;
        Log.d("TIMECHIE", string);
        try {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date reference = dateFormat.parse("00:00:00");
            Date date = dateFormat.parse(string);
            long seconds = (date.getTime() - reference.getTime()) / 1000L;
            i = seconds;
        } catch (Exception e) {
            Toast.makeText(context, "Cant'Parse Time", Toast.LENGTH_SHORT);
        }
        return i;
    }

    public void setupUI(View view, final AutoFillEditText editme) {
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hidekeyboard.hideSoftKeyboard(timer.this);
                    editme.setCursorVisible(false);
                    return false;
                }
            });
        } else {
            editme.setCursorVisible(true);
        }
        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, editme);
            }
        }
    }

    public void broadcastIntent(long val, String msg) {
        Log.d("WEARE", String.valueOf(val));
        int ival = (int) val;
        Intent i = new Intent("android.intent.action.SET_TIMER");
        i.putExtra("android.intent.extra.alarm.SKIP_UI", false);
        i.putExtra("android.intent.extra.alarm.LENGTH", ival);
        i.putExtra("android.intent.extra.alarm.MESSAGE", msg);
        startActivity(i);
    }

    public void broadcastIntentskipui(long val, String msg) {
        Log.d("WEARE", String.valueOf(val));
        int ival = (int) val;
        Intent i = new Intent("android.intent.action.SET_TIMER");
        i.putExtra("android.intent.extra.alarm.SKIP_UI", true);
        i.putExtra("android.intent.extra.alarm.LENGTH", ival);
        i.putExtra("android.intent.extra.alarm.MESSAGE", msg);
        startActivity(i);
        Log.d("WESENT","THE FIrST");
    }

    @Override
    protected void onResume() {
        final SlideToActView slidetimer = findViewById(R.id.timerbutton);
        slidetimer.resetSlider();
        super.onResume();
    }
}
