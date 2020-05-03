package sg.edu.np.WhackAMole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - Feel free to modify the function to suit your program.
    */
    private static final String TAG = "Whack-A-Mole"; //final means locked at run time
    private List<Button> holeButtonList = new ArrayList<>();
    private Integer randomLocation;
    private TextView result;
    private Button button1;
    private Button button2;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.Button1);
        button2 = (Button) findViewById(R.id.Button2);
        button3 = (Button) findViewById(R.id.Button3);
        result = (TextView) findViewById(R.id.result);
        holeButtonList.add(button1);
        holeButtonList.add(button2);
        holeButtonList.add(button3);


        Log.v(TAG, "Finished Pre-Initialisation!");
    }

    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonPress = (Button) v;
                switch (holeButtonList.indexOf(buttonPress)){
                    case 0:
                        Log.v(TAG, "Left Button Clicked!");
                        break;
                    case 1:
                        Log.v(TAG, "Middle Button Clicked!");
                        break;
                    case 2:
                        Log.v(TAG, "Right Button Clicked!");
                        break;
                    default:
                        Log.v(TAG, "No case");
                        break;
                }
                Integer score = Integer.parseInt(result.getText().toString());
                switch (buttonPress.getText().toString()) {
                    case "0":
                        Log.v(TAG,"Missed, score deducted!");
                        score -= 1;
                        result.setText(score.toString());
                        break;
                    case "*":
                        Log.v(TAG,"Hit, score added!");
                        score += 1;
                        result.setText(score.toString());
                        break;
                    default:
                        Log.v(TAG,"Unknown button pressed, no case for it's text set.");
                }
                resetMole();
                setNewMole();
            }
        };
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        Log.v(TAG, "Starting GUI!");
    }


    public void setNewMole()
    {
        Random ran = new Random();
        randomLocation = ran.nextInt(3);
        holeButtonList.get(randomLocation).setText("*");
    }
    public void resetMole()
    {
        holeButtonList.get(randomLocation).setText("0");
    }
}
