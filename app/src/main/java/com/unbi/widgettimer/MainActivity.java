package com.unbi.widgettimer;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private static boolean skipui=false,skipuialram=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(this,timer.class);
//        startActivity(intent);
//        Intent intent = new Intent(this, Alarm.class);
//        startActivity(intent);
        final Switch switchme=(Switch)findViewById(R.id.switch1);
        final Switch alarmswitch=(Switch)findViewById(R.id.switchalarm);
        ReadSharePreference();

        if(skipui){
            switchme.setChecked(true);
        }else{
            switchme.setChecked(false);

        }
        if(skipuialram){
            alarmswitch.setChecked(true);
        }else{
            alarmswitch.setChecked(false);

        }
        switchme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bool=switchme.isChecked();
                if (bool) {
//                    switchme.setChecked(false);
                    skipui=true;
                } else {
//                    switchme.setChecked(true);
                    skipui=false;
                }

                SharedPreferences spref = getSharedPreferences("switch", MODE_PRIVATE);
                SharedPreferences.Editor editor = spref.edit();
                editor.putBoolean("skipui", skipui); // value to store
                editor.apply();
            }
        });

        alarmswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bool=alarmswitch.isChecked();
                if (bool) {
//                    switchme.setChecked(false);
                    skipuialram=true;
                } else {
//                    switchme.setChecked(true);
                    skipuialram=false;
                }
                SharedPreferences spref = getSharedPreferences("switch", MODE_PRIVATE);
                SharedPreferences.Editor editor = spref.edit();
                editor.putBoolean("skipuialram", skipuialram); // value to store
                editor.apply();
            }
        });




    }

    public static boolean getskipui(SharedPreferences spref){
//        Log.d("skipui",String.valueOf(skipui));


        skipui = spref.getBoolean("skipui", false);
        skipuialram = spref.getBoolean("skipuialram", false);

        return skipui;
    }
    public static boolean getskipuialarm(SharedPreferences spref){
//        Log.d("skipuialram",String.valueOf(skipuialram));
        skipui = spref.getBoolean("skipui", false);
        skipuialram = spref.getBoolean("skipuialram", false);
        return skipuialram;
    }
    public void ReadSharePreference() {
        SharedPreferences spref = getSharedPreferences("switch", MODE_PRIVATE);
        skipui = spref.getBoolean("skipui", false);
        skipuialram = spref.getBoolean("skipuialram", false);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
