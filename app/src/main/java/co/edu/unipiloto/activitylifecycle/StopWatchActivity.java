package co.edu.unipiloto.activitylifecycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class StopWatchActivity extends AppCompatActivity {

    private int seconds =0;
    private boolean running;

    private int c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt("seconds",seconds);
        saveInstanceState.putBoolean("running",running);
    }
    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    public void onClickLap(View view){
        c++;
        TextView timeView = (TextView) findViewById(R.id.time_view);
        TextView lapView;
        if (c==1) {
            lapView = (TextView) findViewById(R.id.lap1);
            lapView.setText(timeView.getText());
        }
        else if (c==2) {
            lapView = (TextView) findViewById(R.id.lap2);
            lapView.setText(timeView.getText());
        }
        else if (c==3) {
            lapView = (TextView) findViewById(R.id.lap3);
            lapView.setText(timeView.getText());
        }
        else if (c==4) {
            lapView = (TextView) findViewById(R.id.lap4);
            lapView.setText(timeView.getText());
        }
        else if (c==5) {
            lapView = (TextView) findViewById(R.id.lap5);
            lapView.setText(timeView.getText());
            c=0;
        }



    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds&60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours,minutes,secs);
                timeView.setText(time);
                if (running) seconds++;
                handler.postDelayed(this,1000);
            }

        });

    }
}