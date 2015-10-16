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

    private Button[][] buttons;
    private TicTacToe ttt;
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
		clearBoard();
        ttt = new TicTacToe(numberOfPlayers);
		
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
		if (id == R.id.action_settings)
			return true;
		return super.onOptionsItemSelected(item);
	}
	
	public void reset(View v){
    	gameStatus = true;
		clearBoard();
        ttt = new TicTacToe(numberOfPlayers);
        TextView instructions = (TextView) findViewById(R.id.instruction_message);
        instructions.setText(R.string.instruction_message_x);
    }

	private void clearBoard() {
		for(Button[] bs: buttons)
			for(Button button: bs)
				button.setText(TicTacToe.BLANK + "");
	}
    
    public void click(View v){
    	if(!gameStatus)
    		return;
		Move move;
		switch (v.getId()) {
			case R.id.button1: move = new Move(0,0);
				break;
			case R.id.button2: move = new Move(0,1);
				break;
			case R.id.button3: move = new Move(0,2);
				break;
			case R.id.button4: move = new Move(1,0);
				break;
			case R.id.button5: move = new Move(1,1);
				break;
			case R.id.button6: move = new Move(1,2);
				break;
			case R.id.button7: move = new Move(2,0);
				break;
			case R.id.button8: move = new Move(2,1);
				break;
			default: move = new Move(2,2);
				break;
		}
        ttt.play(move);
        updateBoard(ttt);
		TextView instructions = (TextView) findViewById(R.id.instruction_message);
		if(ttt.getStatus() == TicTacToe.TIE){
			instructions.setText(R.string.tie);
			gameStatus = false;
			return;
		}
		else if(ttt.getStatus() == TicTacToe.X_WON){
			instructions.setText(R.string.x_won);
			gameStatus = false;
			return;
		}
		else if(ttt.getStatus() == TicTacToe.O_WON){
			instructions.setText(R.string.o_won);
			gameStatus = false;
			return;
		}
		else if(ttt.getStatus() == TicTacToe.ILLEGAL){
			return;
		}

		if(ttt.getTurn() == TicTacToe.X)
			instructions.setText(R.string.instruction_message_x);
		else
			instructions.setText(R.string.instruction_message_o);
    }

    private void updateBoard(TicTacToe ticTacToe){
        char[][] grid = ticTacToe.getGrid();
        for(int i = 0; i < TicTacToe.SIZE; i++)
            for(int j = 0; j < TicTacToe.SIZE; j++)
                buttons[i][j].setText(grid[i][j] + "");
    }
}
