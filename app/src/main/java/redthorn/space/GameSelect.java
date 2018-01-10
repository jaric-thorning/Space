package redthorn.space;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import redthorn.space.R;

/**
 * Created by jaricthorning on 2/1/18.
 */
public class GameSelect extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_select);

        //Register buttons
        View newGameButton = findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.new_game_button:
                Intent i = new Intent(this, GameScreen.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
