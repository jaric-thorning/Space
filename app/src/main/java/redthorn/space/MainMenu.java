package redthorn.space;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainMenu extends Activity implements OnClickListener {

    private static final String TAG = "Space";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        //Register button listeners
        View startGameButton = findViewById(R.id.start_game_button);
        startGameButton.setOnClickListener(this);

        View optionsButton = findViewById(R.id.options_button);
        optionsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.start_game_button:
                Log.d(TAG, "Launching game select class");
                Intent i = new Intent(this, GameSelect.class);
                startActivity(i);
                break;
            case R.id.options_button:
                break;
            default:
                break;
        }
    }
}
