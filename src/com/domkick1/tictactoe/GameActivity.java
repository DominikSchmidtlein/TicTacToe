package com.domkick1.tictactoe;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity {
	
	private static final int ILLEGAL = 0;
    private static final int ONGOING = 1;
    private static final int TIE = 2;
    private static final int X_WON = 3;
    private static final int O_WON = 4;
    Button[][] buttons;
    TicTacToe ttt;
    boolean gameStatus;
    int numberOfPlayers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		Intent intent = getIntent();
		numberOfPlayers = intent.getIntExtra(MainActivity.NUMBER_OF_PLAYERS,1);
		
		gameStatus = true;
        buttons = new Button[3][3];
        
        buttons[0][0] = (Button) findViewById(R.id.button1);
        buttons[0][1] = (Button) findViewById(R.id.button2);
        buttons[0][2] = (Button) findViewById(R.id.button3);
        buttons[1][0] = (Button) findViewById(R.id.button4);
        buttons[1][1] = (Button) findViewById(R.id.button5);
        buttons[1][2] = (Button) findViewById(R.id.button6);
        buttons[2][0] = (Button) findViewById(R.id.button7);
        buttons[2][1] = (Button) findViewById(R.id.button8);
        buttons[2][2] = (Button) findViewById(R.id.button9);
        ttt = new TicTacToe(buttons);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
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
	
	public void reset(View v){
    	gameStatus = true;
    	buttons = new Button[3][3];
        
        buttons[0][0] = (Button) findViewById(R.id.button1);
        buttons[0][1] = (Button) findViewById(R.id.button2);
        buttons[0][2] = (Button) findViewById(R.id.button3);
        buttons[1][0] = (Button) findViewById(R.id.button4);
        buttons[1][1] = (Button) findViewById(R.id.button5);
        buttons[1][2] = (Button) findViewById(R.id.button6);
        buttons[2][0] = (Button) findViewById(R.id.button7);
        buttons[2][1] = (Button) findViewById(R.id.button8);
        buttons[2][2] = (Button) findViewById(R.id.button9);
        ttt = new TicTacToe(buttons);
        TextView instructions = (TextView) findViewById(R.id.instruction_message);
        instructions.setText(R.string.instruction_message_x);
    }
    
    public void click(View v){
    	if(!gameStatus){
    		return;
    	}
    	int cap;
    	if(numberOfPlayers == 1){
    		cap = 2;
    	}
    	else{
    		cap = 1;
    	}
    	for(int i = 0;i < cap;i++){
    		int result;
    		if(i == 0){
    			Move move;
    			if(v.getId() == R.id.button1){
    				move = new Move(0,0);
    			}
    			else if(v.getId() == R.id.button2){
    				move = new Move(0,1);
    			}
    			else if(v.getId() == R.id.button3){
    				move = new Move(0,2);
    			}
    			else if(v.getId() == R.id.button4){
    				move = new Move(1,0);
    			}
    			else if(v.getId() == R.id.button5){
    				move = new Move(1,1);
    			}
    			else if(v.getId() == R.id.button6){
    				move = new Move(1,2);
    			}
    			else if(v.getId() == R.id.button7){
    				move = new Move(2,0);
    			}
    			else if(v.getId() == R.id.button8){
    				move = new Move(2,1);
    			}
    			else{
    				move = new Move(2,2);
    			}
    			result = ttt.play(move);
    		}
    		else{
    			result = ttt.machinePlay();
    		}

    		TextView instructions = (TextView) findViewById(R.id.instruction_message);
    		if(result == TIE){
    			instructions.setText(R.string.tie);
    			gameStatus = false;
    			return;
    		}
    		else if(result == X_WON){
    			instructions.setText(R.string.x_won);
    			gameStatus = false;
    			return;
    		}
    		else if(result == O_WON){
    			instructions.setText(R.string.o_won);
    			gameStatus = false;
    			return;
    		}
    		else if(result == ILLEGAL){
    			return;
    		}


    		if(ttt.getTurn() == "x"){
    			instructions.setText(R.string.instruction_message_x);    	
    		}
    		else{
    			instructions.setText(R.string.instruction_message_o); 
    		}

    		if(result == TIE){
    			instructions.setText(R.string.tie);
    		}
    		else if(result == X_WON){
    			instructions.setText(R.string.x_won);
    		}
    		else if(result == O_WON){
    			instructions.setText(R.string.o_won);
    		}
    	}
    }
}
