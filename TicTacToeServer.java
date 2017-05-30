/**
 * Reedit Syed Shahriar - 23 April 2017
 * 
 * CONFIDENTIAL
 * 
 * This program creates a server at the specified port that
 * can be used to play a LAN game of Tic Tac Toe, as long as
 * an appropriate client is provided (TicTacToeClient). It
 * blocks while waiting for a connection to the port by a
 * socket-based client, and then opens the window. When the
 * game ends, the window closes.
 */

import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

// server -> red, client -> black
public class TicTacToeServer extends JFrame{
	
	private Scanner sc;
	private PrintWriter pw;
	
	public static void main(String[] args) {
		
		new TicTacToeServer();

	}
	
	public TicTacToeServer(){
		
		super("Tic Tac Toe! (Server)");
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter port number: ");
		int port = Integer.parseInt(in.next());
		
		System.out.println("Waiting for connection...");
		
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
			//new TicTacToeClient("192.168.56.1",port);
			Socket socket = ss.accept();
			sc = new Scanner(socket.getInputStream());
			pw = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Connection achieved!");
		
		setSize(480,480);
		setLocation(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		TTTServerPanel panel = new TTTServerPanel();
		setContentPane(panel);
		setVisible(true);
		
	}
	
	class TTTServerPanel extends TTTPanel{
		
		protected boolean move;
		
		public TTTServerPanel(){
			
			super();
			move = true;
			lan = true;
			
		}
		
		public void mouseClicked(MouseEvent e){
			
			if (move){
				
				move = false;
				
				int x = e.getX(), y = e.getY();
				int index = ((y/150)*3) + ((x/150)+1);
				
				red.add(index);
				pw.println(index);
				pw.flush();
				//System.out.println("Server Sent: "+index);
				
				super.mouseClicked(e);
				
				repaint();
				
				MoveThread mt = new MoveThread();
				mt.start();

			}
			
		}
		
		class MoveThread extends Thread{
			
			public void run(){
				
				// Scans socket for signal
				int index = Integer.parseInt(sc.nextLine());
				//System.out.println("Server Received: "+index);
				black.add(index);
				repaint();
				
				move = true;
				
			}
			
		}
		
	}

}
