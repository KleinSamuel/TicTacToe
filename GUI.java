import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GUI extends JFrame implements ActionListener{

	JPanel pBack, pLeft, pRight, pRUp, pRDown;
	JButton[][] buttons;
	JButton replayButton;
	JLabel lLeft, lRight, lDown;
	int dim, win1, win2, currentPlayer;
	
	TicTacToe tic;
	
	public GUI(int dim) throws IOException{
		super();
		
		pBack = new JPanel(new GridLayout(0,2));
		pLeft = new JPanel(new GridLayout(dim, dim));
		pRight = new JPanel(new GridLayout(2,0));
		pRUp = new JPanel(new GridLayout(0,2));
		pRDown = new JPanel(new GridLayout(2,0));
		
		win1 = 0;
		win2 = 0;
		
		Border border = LineBorder.createGrayLineBorder();
		
		lLeft = new JLabel(" X gewonnen: "+win1);
		lLeft.setBorder(border);
		lRight = new JLabel(" O gewonnen: "+win2);
		lRight.setBorder(border);
		lDown = new JLabel();
		lDown.setHorizontalAlignment(JLabel.CENTER);
		lDown.setVerticalAlignment(JLabel.CENTER);
		lDown.setBorder(border);
		
		replayButton = new JButton("Nochmal Spielen");
		replayButton.setEnabled(false);
		replayButton.addActionListener(this);
		
		pRUp.add(lLeft);
		pRUp.add(lRight);
		
		pRDown.add(lDown);
		pRDown.add(replayButton);
		
		pRight.add(pRUp);
		pRight.add(pRDown);
		
		pBack.add(pLeft);
		pBack.add(pRight);
		
		add(pBack);
		
		this.dim = dim;
		
		initFrame();
		
		tic = new TicTacToe(dim);
		currentPlayer = 1 + (int)(Math.random()*2); 
		
		if(currentPlayer == 1){
			lDown.setText("X ist dran!");
		}else{
			lDown.setText("O ist dran!");
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 250);
		setVisible(true);
		
	}
	
	public void initFrame(){
		buttons = new JButton[dim][dim];
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(this);
				buttons[i][j].setBackground(Color.WHITE);
				buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
				pLeft.add(buttons[i][j]);
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		GUI gui = new GUI(3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				if(e.getSource() == buttons[i][j]){
					boolean legitMove = tic.setze(j, i, currentPlayer);
					
					if(legitMove){
						if(tic.istUnentschieden()){
							lDown.setText("Unentschieden!");
							replayButton.setEnabled(true);
						}else if(tic.spielBeendet() != 0){
							lDown.setText("Spieler "+currentPlayer+" hat gewonnen!");
							if(currentPlayer == 1){
								win1++;
								lLeft.setText(" X gewonnen: "+win1);
							}else{
								win2++;
								lRight.setText(" O gewonnen: "+win2);
							}
							replayButton.setEnabled(true);
						}
						buttons[i][j].setEnabled(false);
						if(currentPlayer == 1){
							currentPlayer = 2;
							buttons[i][j].setText("X");
							lDown.setText("O ist dran!");
							
							
						}else{
							currentPlayer = 1;
							buttons[i][j].setText("O");
							lDown.setText("X ist dran!");
						}
					}
				}
				
				
			}
		}
		
		if(e.getSource() == replayButton){
			
			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons.length; j++) {
					buttons[i][j].setText("");
					buttons[i][j].setEnabled(true);
				}
			}
			lDown.setText(currentPlayer+" ist dran");
			tic = null;
			tic = new TicTacToe(dim);
			
			replayButton.setEnabled(false);
		}
		
	}
	
}
