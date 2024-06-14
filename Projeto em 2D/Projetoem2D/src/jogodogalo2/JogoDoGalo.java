package jogodogalo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class JogoDoGalo extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnButton1;
	private JButton btnButton2;
	private JButton btnButton3;
	private JButton btnButton4;
	private JButton btnButton5;
	private JButton btnButton6;
	private JButton btnButton7;
	private JButton btnButton8;
	private JButton btnButton9;
	private String startGame = "X";
	private JLabel lblScorePlayer1;
	private JLabel lblScorePlayer2;
	private int player1 = 0;
	private int player2 = 0;
	public static JogoDoGalo janela = new JogoDoGalo();
	

	public static void main(String[] args) {
		
		janela.setSize(700, 800);
		//janela.setVisible(true);

	}
	
	/////Métodos/////
	
	private void playerTurn() {
		if(startGame.equalsIgnoreCase("X")) {
			startGame = "O";
		}else {
			startGame = "X";
		}
	}
	
	private void gameWinner() {
		
		String btn1 = btnButton1.getText();
		String btn2 = btnButton2.getText();
		String btn3 = btnButton3.getText();
		String btn4 = btnButton4.getText();
		String btn5 = btnButton5.getText();
		String btn6 = btnButton6.getText();
		String btn7 = btnButton7.getText();
		String btn8 = btnButton8.getText();
		String btn9 = btnButton9.getText();
		
		///Player 1 winner 
		
		if (btn1 == "X" && btn2 == "X" && btn3 == "X") {
			JOptionPane.showMessageDialog(null, "Player 1 Wins!");
			player1++;
			lblScorePlayer1.setText(String.valueOf(player1));
			scoreLimit();
		}
		
		if (btn1 == "X" && btn4 == "X" && btn7 == "X") {
			JOptionPane.showMessageDialog(null, "Player 1 Wins!");
			player1++;
			lblScorePlayer1.setText(String.valueOf(player1));
			scoreLimit();
		}
		
		if (btn1 == "X" && btn5 == "X" && btn9 == "X") {
			JOptionPane.showMessageDialog(null, "Player 1 Wins!");
			player1++;
			lblScorePlayer1.setText(String.valueOf(player1));
			scoreLimit();
		}
		if (btn2 == "X" && btn5 == "X" && btn8 == "X") {
			JOptionPane.showMessageDialog(null, "Player 1 Wins!");
			player1++;
			lblScorePlayer1.setText(String.valueOf(player1));
			scoreLimit();
		}
		if (btn3 == "X" && btn6 == "X" && btn9 == "X") {
			JOptionPane.showMessageDialog(null, "Player 1 Wins!");
			player1++;
			lblScorePlayer1.setText(String.valueOf(player1));
			scoreLimit();
		}
		if (btn4 == "X" && btn5 == "X" && btn6 == "X") {
			JOptionPane.showMessageDialog(null, "Player 1 Wins!");
			player1++;
			lblScorePlayer1.setText(String.valueOf(player1));
			scoreLimit();
		}
		if (btn7 == "X" && btn8 == "X" && btn9 == "X") {
			JOptionPane.showMessageDialog(null, "Player 1 Wins!");
			player1++;
			lblScorePlayer1.setText(String.valueOf(player1));
			scoreLimit();
		}
		if (btn7 == "X" && btn5 == "X" && btn3 == "X") {
			JOptionPane.showMessageDialog(null, "Player 1 Wins!");
			player1++;
			lblScorePlayer1.setText(String.valueOf(player1));
			scoreLimit();
		}
		
		//Player 2 Wins
		
		if (btn1 == "O" && btn2 == "O" && btn3 == "O") {
			JOptionPane.showMessageDialog(null, "Player 2 Wins!");
			player2++;
			lblScorePlayer2.setText(String.valueOf(player2));
			scoreLimit();
		}
		
		if (btn1 == "O" && btn4 == "O" && btn7 == "O") {
			JOptionPane.showMessageDialog(null, "Player 2 Wins!");
			player2++;
			lblScorePlayer2.setText(String.valueOf(player2));
			scoreLimit();
		}
		
		if (btn1 == "O" && btn5 == "O" && btn9 == "O") {
			JOptionPane.showMessageDialog(null, "Player 2 Wins!");
			player2++;
			lblScorePlayer2.setText(String.valueOf(player2));
			scoreLimit();
		}
		if (btn2 == "O" && btn5 == "O" && btn8 == "O") {
			JOptionPane.showMessageDialog(null, "Player 2 Wins!");
			player2++;
			lblScorePlayer2.setText(String.valueOf(player2));
			scoreLimit();
		}
		if (btn3 == "O" && btn6 == "O" && btn9 == "O") {
			JOptionPane.showMessageDialog(null, "Player 2 Wins!");
			player2++;
			lblScorePlayer2.setText(String.valueOf(player2));
			scoreLimit();
		}
		if (btn4 == "O" && btn5 == "O" && btn6 == "O") {
			JOptionPane.showMessageDialog(null, "Player 2 Wins!");
			player2++;
			lblScorePlayer2.setText(String.valueOf(player2));
			scoreLimit();
		}
		if (btn7 == "O" && btn8 == "O" && btn9 == "O") {
			JOptionPane.showMessageDialog(null, "Player 2 Wins!");
			player2++;
			lblScorePlayer2.setText(String.valueOf(player2));
			scoreLimit();
		}
		if (btn7 == "O" && btn5 == "O" && btn3 == "O") {
			JOptionPane.showMessageDialog(null, "Player 2 Wins!");
			player2++;
			lblScorePlayer2.setText(String.valueOf(player2));
			scoreLimit();
		}
		
	}
	
	private void scoreLimit() {
		if(player1 == 10) {
			JOptionPane.showMessageDialog(null, "Player 1 reached the score limit");
			JOptionPane.showMessageDialog(null, "Player 1 Champion");	
			disableButtons();
		}
		if(player2 == 10) {
			JOptionPane.showMessageDialog(null, "Player 2 reached the score limit");
			JOptionPane.showMessageDialog(null, "Player 2 Champion");	
			disableButtons();
		}
	}
	
	private void clearControls() {
		
		btnButton1.setText(null);
		btnButton2.setText(null);
		btnButton3.setText(null);
		btnButton3.setText(null);
		btnButton4.setText(null);
		btnButton5.setText(null);
		btnButton6.setText(null);
		btnButton7.setText(null);
		btnButton8.setText(null);
		btnButton9.setText(null);

	}
	
	private void disableButtons() {
		
		btnButton1.setEnabled(false);
		btnButton2.setEnabled(false);
		btnButton3.setEnabled(false);
		btnButton4.setEnabled(false);
		btnButton5.setEnabled(false);
		btnButton6.setEnabled(false);
		btnButton7.setEnabled(false);
		btnButton8.setEnabled(false);
		btnButton9.setEnabled(false);
	}
	
	private void enableButtons() {
		
		btnButton1.setEnabled(true);
		btnButton2.setEnabled(true);
		btnButton3.setEnabled(true);
		btnButton4.setEnabled(true);
		btnButton5.setEnabled(true);
		btnButton6.setEnabled(true);
		btnButton7.setEnabled(true);
		btnButton8.setEnabled(true);
		btnButton9.setEnabled(true);
	}

	private void restartGame() {
		playerTurn();
		clearControls();
		enableButtons();
	}

	public JogoDoGalo() {
		
		// Add menus
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
				
		// File menu
		JMenu menu = new JMenu("Menu");
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		
		setTitle("Jogo Do Galo");
		setOpacity(1.0f);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblJogoDoGalo = new JLabel("Jogo Do Galo");
		lblJogoDoGalo.setFont(new Font("Segoe Script", Font.BOLD, 21));
		lblJogoDoGalo.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogoDoGalo.setBounds(0, 0, 684, 110);
		contentPane.add(lblJogoDoGalo);
		
		JLabel lblPlayer_1 = new JLabel("Player 1:");
		lblPlayer_1.setFont(new Font("Segoe UI Black", Font.ITALIC, 15));
		lblPlayer_1.setBounds(123, 158, 75, 26);
		contentPane.add(lblPlayer_1);
		
		JLabel lblPlayer_2 = new JLabel("Player 2:");
		lblPlayer_2.setFont(new Font("Segoe UI Black", Font.ITALIC, 15));
		lblPlayer_2.setBounds(465, 158, 75, 26);
		contentPane.add(lblPlayer_2);
		
		JLabel lblLabel_X = new JLabel("X");
		lblLabel_X.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel_X.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLabel_X.setBounds(185, 160, 65, 29);
		contentPane.add(lblLabel_X);
		
		JLabel lblLabel_O = new JLabel("O");
		lblLabel_O.setBounds(550, 160, 58, 29);
		contentPane.add(lblLabel_O);
		
		JLabel lblScorePlayer_1 = new JLabel("Score:");
		lblScorePlayer_1.setBounds(142, 214, 46, 14);
		contentPane.add(lblScorePlayer_1);
		
		JLabel lblScorePlayer_2 = new JLabel("Score:");
		lblScorePlayer_2.setBounds(485, 214, 46, 14);
		contentPane.add(lblScorePlayer_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 251, 684, 2);
		contentPane.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(119, 185, 1, 276);
		contentPane.add(separator_2);
		
		JButton btnButtonPlayAgain = new JButton("Play Again");
		btnButtonPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerTurn();
				clearControls();

				
			}
		});
		btnButtonPlayAgain.setBackground(Color.WHITE);
		btnButtonPlayAgain.setForeground(new Color(0, 0, 255));
		btnButtonPlayAgain.setBounds(296, 163, 100, 23);
		contentPane.add(btnButtonPlayAgain);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartGame();
				player1 = 0;
				player2 = 0;
				lblScorePlayer1.setText(null);
				lblScorePlayer2.setText(null);
				enableButtons();
			}
		});
		btnRestart.setForeground(Color.BLUE);
		btnRestart.setBackground(Color.WHITE);
		btnRestart.setBounds(296, 210, 100, 23);
		contentPane.add(btnRestart);
		
		lblScorePlayer1 = new JLabel("");
		lblScorePlayer1.setBounds(185, 214, 46, 14);
		contentPane.add(lblScorePlayer1);
		
		lblScorePlayer2 = new JLabel("");
		lblScorePlayer2.setBounds(529, 214, 46, 14);
		contentPane.add(lblScorePlayer2);
		
		btnButton1 = new JButton("");
		btnButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnButton1.setText(startGame);
				
				if(startGame.equalsIgnoreCase("X")) {
					btnButton1.setForeground(Color.RED);
				}else {
					btnButton1.setForeground(Color.BLUE); 
				}
				playerTurn();
				gameWinner();
			}
		});
		btnButton1.setBounds(210, 383, 89, 75);
		contentPane.add(btnButton1);
		
		btnButton2 = new JButton("");
		btnButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnButton2.setText(startGame);
				
				if(startGame.equalsIgnoreCase("X")) {
					btnButton2.setForeground(Color.RED);
				}else {
					btnButton2.setForeground(Color.BLUE); 
				}
				playerTurn();
				gameWinner();
			}
		});
		btnButton2.setBounds(299, 383, 89, 75);
		contentPane.add(btnButton2);
		
		btnButton3 = new JButton("");
		btnButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnButton3.setText(startGame);
				
				if(startGame.equalsIgnoreCase("X")) {
					btnButton3.setForeground(Color.RED);
				}else {
					btnButton3.setForeground(Color.BLUE); 
				}
				playerTurn();
				gameWinner();
			}
		});
		btnButton3.setBounds(388, 383, 89, 75);
		contentPane.add(btnButton3);
		
		btnButton4 = new JButton("");
		btnButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnButton4.setText(startGame);
				
				if(startGame.equalsIgnoreCase("X")) {
					btnButton4.setForeground(Color.RED);
				}else {
					btnButton4.setForeground(Color.BLUE); 
				}
				playerTurn();
				gameWinner();
			}
		});
		btnButton4.setBounds(210, 457, 89, 75);
		contentPane.add(btnButton4);
		
		btnButton5 = new JButton("");
		btnButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnButton5.setText(startGame);
				
				if(startGame.equalsIgnoreCase("X")) {
					btnButton5.setForeground(Color.RED);
				}else {
					btnButton5.setForeground(Color.BLUE); 
				}
				playerTurn();
				gameWinner();
			}
		});
		btnButton5.setBounds(299, 457, 89, 75);
		contentPane.add(btnButton5);
		
		btnButton6 = new JButton("");
		btnButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnButton6.setText(startGame);
				
				if(startGame.equalsIgnoreCase("X")) {
					btnButton6.setForeground(Color.RED);
				}else {
					btnButton6.setForeground(Color.BLUE); 
				}
				playerTurn();
				gameWinner();
			}
		});
		btnButton6.setBounds(388, 457, 89, 75);
		contentPane.add(btnButton6);
		
		btnButton7 = new JButton("");
		btnButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnButton7.setText(startGame);
				
				if(startGame.equalsIgnoreCase("X")) {
					btnButton7.setForeground(Color.RED);
				}else {
					btnButton7.setForeground(Color.BLUE); 
				}
				playerTurn();
				gameWinner();
			}
		});
		btnButton7.setBounds(210, 530, 89, 75);
		contentPane.add(btnButton7);
		
		btnButton8 = new JButton("");
		btnButton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnButton8.setText(startGame);
				
				if(startGame.equalsIgnoreCase("X")) {
					btnButton8.setForeground(Color.RED);
				}else {
					btnButton8.setForeground(Color.BLUE); 
				}
				playerTurn();
				gameWinner();
			}
		});
		btnButton8.setBounds(299, 530, 89, 75);
		contentPane.add(btnButton8);
		
		btnButton9 = new JButton("");
		btnButton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnButton9.setText(startGame);
				
				if(startGame.equalsIgnoreCase("X")) {
					btnButton9.setForeground(Color.RED);
				}else {
					btnButton9.setForeground(Color.BLUE); 
				}
				playerTurn();
				gameWinner();
			}
		});
		btnButton9.setBounds(388, 530, 89, 75);
		contentPane.add(btnButton9);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String option = e.getActionCommand();
		if(option.equals("Exit")) {
			System.exit(0);
		}
	}
}
