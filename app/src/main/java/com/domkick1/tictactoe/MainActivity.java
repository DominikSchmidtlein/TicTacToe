package com.domkick1.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {
	
	public static final String NUMBER_OF_PLAYERS = "com.domkick1.tictactoe.NumberOfPlayers";
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    
    public void startGame(View view){
    	Intent intent = new Intent(this,GameActivity.class);
    	int number;
    	if(view.getId() == R.id.one_player_game){
    		number = 1;
    	}
    	else{
    		number = 2;
    	}
    	intent.putExtra(NUMBER_OF_PLAYERS, number);
    	startActivity(intent);
    }
}
