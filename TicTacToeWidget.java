

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToeWidget extends JPanel implements ActionListener, SpotListener {
	private enum Player { BLACK, WHITE};

	private TicTacToeJSpotBoard _board;
	private JLabel _message; 
	private boolean _game_won;
	private boolean _game_draw;
	private Player _next_to_play; 

	public TicTacToeWidget() {
		_board = new TicTacToeJSpotBoard(3, 3);
		_message = new JLabel();

		setLayout(new BorderLayout());
		add(_board, BorderLayout.CENTER);
		setLayout(new BorderLayout());
		add(_board, BorderLayout.CENTER);

		JPanel reset_message_panel = new JPanel();
		reset_message_panel.setLayout(new BorderLayout());

		JButton reset_button = new JButton("Restart");
		reset_button.addActionListener(this);
		reset_message_panel.add(reset_button, BorderLayout.EAST);
		reset_message_panel.add(_message, BorderLayout.CENTER);

		add(reset_message_panel, BorderLayout.SOUTH);

		_board.addSpotListener(this);

		resetGame();
	}

	private void resetGame() {
		for (Spot s : _board) {
			s.clearSpot();
			s.setSpotColor(Color.GREEN);
		}
		_game_won = false;
		_game_draw = false;
		_next_to_play = Player.WHITE;

		_message.setText("Welcome to Tic Tac Toe. White to play");

	}

	@Override
	public void spotClicked(Spot spot) {
		String player_name = null;
		String next_player_name = null;
		Color player_color = null;
		int count = 0;

		if (_game_won || _game_draw || !spot.isEmpty()) {
			return;
		}
		if (_next_to_play == Player.BLACK) {
			player_color = Color.BLACK;
			player_name = "Black";
			next_player_name = "White";
			_next_to_play = Player.WHITE;
		} else {
			player_color = Color.WHITE;
			player_name = "White";
			next_player_name = "Black";
			_next_to_play = Player.BLACK;
		}
		if(spot.isEmpty()) {
			spot.setSpotColor(player_color);
			spot.toggleSpot();
			if(_board.getSpotAt(0, 0).getSpotColor() == player_color &&
					_board.getSpotAt(0, 1).getSpotColor() == player_color &&
					_board.getSpotAt(0, 2).getSpotColor() == player_color) {
				_game_won = true;
			}
			if(_board.getSpotAt(1, 0).getSpotColor() == player_color &&
					_board.getSpotAt(1, 1).getSpotColor() == player_color &&
					_board.getSpotAt(1, 2).getSpotColor() == player_color) {
				_game_won = true;
			}
			if(_board.getSpotAt(2, 0).getSpotColor() == player_color &&
					_board.getSpotAt(2, 1).getSpotColor() == player_color &&
					_board.getSpotAt(2, 2).getSpotColor() == player_color) {
				_game_won = true;
			}
			if(_board.getSpotAt(0, 0).getSpotColor() == player_color &&
					_board.getSpotAt(1, 1).getSpotColor() == player_color &&
					_board.getSpotAt(2, 2).getSpotColor() == player_color) {
				_game_won = true;
			}
			if(_board.getSpotAt(2, 0).getSpotColor() == player_color &&
					_board.getSpotAt(1, 1).getSpotColor() == player_color &&
					_board.getSpotAt(0, 2).getSpotColor() == player_color) {
				_game_won = true;
			}
			if(_board.getSpotAt(0, 2).getSpotColor() == player_color &&
					_board.getSpotAt(1, 2).getSpotColor() == player_color &&
					_board.getSpotAt(2, 2).getSpotColor() == player_color) {
				_game_won = true;
			}
			if(_board.getSpotAt(0, 1).getSpotColor() == player_color &&
					_board.getSpotAt(1, 1).getSpotColor() == player_color &&
					_board.getSpotAt(2, 1).getSpotColor() == player_color) {
				_game_won = true;
			}
			if(_board.getSpotAt(0, 0).getSpotColor() == player_color &&
					_board.getSpotAt(1, 0).getSpotColor() == player_color &&
					_board.getSpotAt(2, 0).getSpotColor() == player_color) {
				_game_won = true;
			}
			if (_game_won) {
				_message.setText(player_name + " wins!");
			}
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(!_board.getSpotAt(i, j).isEmpty()) {
						count += 1;
					}
				}
			}
			if(count == 9 && !_game_won) {
				_game_draw = true;
			}
			if (_game_draw) {
				_message.setText("Draw game.");
			}
			if(!_game_won && !_game_draw) {
				_message.setText(next_player_name + " to play.");
			}
		}
	}

	@Override
	public void spotEntered(Spot spot) {
		if (_game_won) {
			return;
		}
		if (spot.isEmpty()) {
			spot.highlightSpot();
		}
	}

	@Override
	public void spotExited(Spot spot) {
		spot.unhighlightSpot();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_message.setText("Welcome to the Tic Tac Toe. White to play");
		resetGame();
	}
}