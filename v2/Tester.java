package v2;

import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Tester extends JFrame implements MouseListener, ComponentListener{

	JPanel jp;
	JLabel jl;
	Icon saveIcon;
	String url2 = "button-red.png";
	
	public Tester(){
		
		setVisible(true);
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addComponentListener(this);
		
		jp = new JPanel();
		jl = new JLabel();
		jl.addMouseListener(this);
		
		jp.add(jl);
		
		add(jp);
		
	}
	
	public void later(int w, int h, String url){
		ClassLoader cl = this.getClass().getClassLoader();
		
		saveIcon  = new ImageIcon(cl.getResource(url));
		
		((ImageIcon) saveIcon).setImage(((ImageIcon) saveIcon).getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));

		jl.setIcon(saveIcon);
	}
	
	
	
	public static void main(String[] args) {
		
		Tester t = new Tester();
		
//		t.later();
		
//		t.jl.setText("jadhfpihfa");
		
		System.out.println(t.getSize());
		
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == jl){
			
			if(url2.equals("button-blue.png")){
				url2 = "button-red.png";
				later((int)jl.getPreferredSize().getWidth(), (int)jl.getPreferredSize().getHeight(), url2);
			}else{
				url2 = "button-blue.png";	
				later((int)jl.getPreferredSize().getWidth(), (int)jl.getPreferredSize().getHeight(), url2);
			}
		}
		
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

	@Override
	public void componentResized(ComponentEvent e) {
		
		later(getContentPane().getWidth(), getContentPane().getHeight(), url2);
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
