import java.awt.List;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe extends JFrame {
	
	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
	}
	
	public TicTacToe() {
		super("Tic Tac Toe!");
		setSize(480,480);
		setLocation(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		TTTPanel panel = new TTTPanel();
		setContentPane(panel);
		setVisible(true);
	}

}

class TTTPanel extends JPanel implements MouseListener{
	
	protected ArrayList<Integer> red, black;
	protected boolean lan;
	
	public TTTPanel(){
		setBackground(Color.WHITE);
		addMouseListener(this);
		red = new ArrayList<Integer>();
		black = new ArrayList<Integer>();
		lan = false;
	}
	
	public void paintComponent(Graphics g){
		g.drawLine(150, 0, 150, 450);
		g.drawLine(300, 0, 300, 450);
		g.drawLine(0, 150, 450, 150);
		g.drawLine(0, 300, 450, 300);
		
		g.setColor(Color.RED);
		for (int index:red){
			int topLeftX = ((index+2)%3)*150,topLeftY = ((index-1)/3)*150;
			g.fillOval(topLeftX,topLeftY,150,150);
		}
		g.setColor(Color.BLACK);
		for (int index:black){
			int topLeftX = ((index+2)%3)*150,topLeftY = ((index-1)/3)*150;
			g.fillOval(topLeftX,topLeftY,150,150);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		
		boolean reset = false;
		
		int x = e.getX(),y = e.getY();
		int row = y/150,col = x/150+1;
		int index = (3*row)+col;
		
		if (!lan){
			if (red.size()==black.size())
				red.add(index);
			else
				black.add(index);
		}
		
		if (red.contains(1) && red.contains(2) && red.contains(3)){ reset = true;}
		else if (red.contains(4) && red.contains(5) && red.contains(6)){ reset = true;}
		else if (red.contains(7) && red.contains(8) && red.contains(9)){ reset = true;}
		else if (red.contains(1) && red.contains(4) && red.contains(7)){ reset = true;}
		else if (red.contains(2) && red.contains(5) && red.contains(8)){ reset = true;}
		else if (red.contains(3) && red.contains(6) && red.contains(9)){ reset = true;}
		else if (red.contains(1) && red.contains(5) && red.contains(9)){ reset = true;}
		else if (red.contains(3) && red.contains(5) && red.contains(7)){ reset = true;}
		else if (black.contains(1) && black.contains(2) && black.contains(3)){ reset = true;}
		else if (black.contains(4) && black.contains(5) && black.contains(6)){ reset = true;}
		else if (black.contains(7) && black.contains(8) && black.contains(9)){ reset = true;}
		else if (black.contains(1) && black.contains(4) && black.contains(7)){ reset = true;}
		else if (black.contains(2) && black.contains(5) && black.contains(8)){ reset = true;}
		else if (black.contains(3) && black.contains(6) && black.contains(9)){ reset = true;}
		else if (black.contains(1) && black.contains(5) && black.contains(9)){ reset = true;}
		else if (black.contains(3) && black.contains(5) && black.contains(7)){ reset = true;}
		else if (red.size()==5){ reset = true;}
		
		if (reset)
			System.exit(0);
		
		repaint();
		
	}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
	
}
