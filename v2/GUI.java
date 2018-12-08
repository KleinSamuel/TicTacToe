package v2;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GUI extends JFrame implements ActionListener, MouseListener{

	JPanel pBack, pLeft, pRight, pRUp, pRDown;
	JLabel[][] labels;
	JButton replayButton;
	JLabel lLeft, lRight, lDown, lUp, lMid;
	int dim, win1, win2, currentPlayer;
	Border border = LineBorder.createGrayLineBorder();
	Font font = new Font("Serif", Font.PLAIN, 14);
	
	String urlX = "images/atst.gif";
	String url0 = "images/xwing.gif";
	String fight = "images/fight.gif";
	String logo = "images/logo.gif";
	ClassLoader cl = this.getClass().getClassLoader();
	
	TicTacToe tic;
	KI ki;
	Robot r;
	
	public GUI(int dim) throws IOException, AWTException{
		super();
		
		pBack = new JPanel(new GridLayout(0,2));
		pLeft = new JPanel(new GridLayout(dim, dim));
		pRight = new JPanel(new GridLayout(3,0));
		pRUp = new JPanel(new GridLayout(0,3));
		pRDown = new JPanel(new GridLayout(2,0));
		
		win1 = 0;
		win2 = 0;

		lLeft = new JLabel(" Imperium: "+win1);
		lLeft.setBorder(border);
		lLeft.setBackground(Color.BLACK);
		lLeft.setForeground(Color.YELLOW);
		lLeft.setOpaque(true);
		lLeft.setFont(font);
		
		lRight = new JLabel(" Rebellen: "+win2);
		lRight.setBorder(border);
		lRight.setBackground(Color.BLACK);
		lRight.setForeground(Color.YELLOW);
		lRight.setOpaque(true);
		lRight.setFont(font);
		
		lUp = new JLabel();
		lUp.setBackground(Color.BLACK);
		
		lMid = new JLabel();
		
		lDown = new JLabel();
		lDown.setHorizontalAlignment(JLabel.CENTER);
		lDown.setVerticalAlignment(JLabel.CENTER);
		lDown.setBorder(border);
		
		lDown.setBackground(Color.BLACK);
		lDown.setForeground(Color.YELLOW);
		lDown.setOpaque(true);
		lDown.setFont(font);
		
		replayButton = new JButton("Nochmal Spielen");
		replayButton.setEnabled(false);
		replayButton.addActionListener(this);
		replayButton.setBackground(Color.BLACK);
		replayButton.setForeground(Color.YELLOW);
		
		pRUp.add(lLeft);
		pRUp.add(lRight);
		pRUp.add(lUp);
		
		pRDown.add(lDown);
		pRDown.add(replayButton);
		
		pRight.add(pRUp);
		pRight.add(lMid);
		pRight.add(pRDown);
		
		pBack.add(pLeft);
		pBack.add(pRight);
		
		add(pBack);
		
		this.dim = dim;
		
		initFrame();
		
		tic = new TicTacToe(dim);
		ki = new KI();
		r = new Robot();
		
		currentPlayer = 1 + (int)(Math.random()*2); 
		
		if(currentPlayer == 1){
			lDown.setText("Das Imperium ist dran!");
		}else{
			lDown.setText("Die Rebellen sind dran!");
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 375);
		setResizable(false);
		setVisible(true);
		
	}
	
	public void initFrame(){
		
		labels = new JLabel[dim][dim];
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels.length; j++) {
				labels[i][j] = new JLabel();
				labels[i][j].addMouseListener(this);
				labels[i][j].setBackground(Color.BLACK);
				labels[i][j].setOpaque(true);
				labels[i][j].setBorder(border);
				
				pLeft.add(labels[i][j]);
			}
		}
	}
	
public void drawImageLabel(){
		
		Icon saveIcon;
		
		saveIcon  = new ImageIcon(cl.getResource(fight));			
		
		((ImageIcon) saveIcon).setImage(((ImageIcon) saveIcon).getImage().getScaledInstance((int)lUp.getWidth(), (int)lUp.getHeight(), Image.SCALE_DEFAULT));

		lUp.setIcon(saveIcon);

		saveIcon  = new ImageIcon(cl.getResource(logo));			
		
		((ImageIcon) saveIcon).setImage(((ImageIcon) saveIcon).getImage().getScaledInstance((int)lMid.getWidth(), (int)lMid.getHeight(), Image.SCALE_DEFAULT));

		lMid.setIcon(saveIcon);
}
	
	public void drawImageLabel(int x, int y, int player){
		
		Icon saveIcon;
		
		if(currentPlayer == 1){
			saveIcon  = new ImageIcon(cl.getResource(urlX));			
		}else{
			saveIcon  = new ImageIcon(cl.getResource(url0));
		}
		
		((ImageIcon) saveIcon).setImage(((ImageIcon) saveIcon).getImage().getScaledInstance((int)labels[x][y].getWidth(), (int)labels[x][y].getHeight(), Image.SCALE_DEFAULT));

		labels[x][y].setIcon(saveIcon);
	}
	
	public static void main(String[] args) throws IOException, AWTException {
		GUI gui = new GUI(3);
		gui.drawImageLabel();
		
		if(gui.currentPlayer == 1){
			gui.kiClick();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == replayButton){

			for (int k = 0; k < labels.length; k++) {
				for (int k2 = 0; k2 < labels.length; k2++) {
					labels[k][k2].setIcon(null);
					labels[k][k2].addMouseListener(this);
				}
			}
			
			currentPlayer = 1 + (int)(Math.random()*2);
			
			if(currentPlayer == 1){
				lDown.setText("Das Imperium ist dran");
			}else{
				lDown.setText("Die Rebellen sind dran");				
			}
			tic = null;
			tic = new TicTacToe(dim);
			
			replayButton.setEnabled(false);
			
			if(currentPlayer == 1){
				kiClick();
			}
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels.length; j++) {
				
				if(e.getSource() == labels[i][j]){
					
					boolean legitMove = tic.setze(j, i, currentPlayer);
					
					if(legitMove){
						
						if(currentPlayer == 1){
							drawImageLabel(i, j, 2);
						}else{
							drawImageLabel(i, j, 1);
						}
						
						if(tic.istUnentschieden()){
							lDown.setText("Unentschieden!");
							replayButton.setEnabled(true);

						}else if(tic.spielBeendet() != 0){
							
							for (int k = 0; k < labels.length; k++) {
								for (int k2 = 0; k2 < labels.length; k2++) {
									labels[k][k2].removeMouseListener(this);
								}
							}
							
							if(currentPlayer == 1){
								win1++;
								lLeft.setText(" Imperium: "+win1);
								lDown.setText("Das Imperium hat gewonnen!");
							}else{
								win2++;
								lRight.setText(" Rebellen: "+win2);
								lDown.setText("Die Rebellen haben gewonnen!");
							}
							replayButton.setEnabled(true);
							
						}else{
							
							labels[i][j].removeMouseListener(this);
							
							if(currentPlayer == 1){
								currentPlayer = 2;
								lDown.setText("Die Rebellen sind dran!");
								
								
							}else{
								currentPlayer = 1;
								lDown.setText("Das Imperium ist dran!");
							}
						}
					}
				}
			}
		}
		
		if(currentPlayer == 1){
			kiClick();
		}
	}
	
	public void kiClick(){
		int[] tmp = ki.makeMove(tic.getBoard());
		int i = tmp[0];
		int j = tmp[1];
		
		Point x = labels[i][j].getLocationOnScreen();
		
		int posX = (int)x.getX()+5;
		int posY = (int)x.getY()+5;
		
		
		r.mouseMove(posX, posY);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
