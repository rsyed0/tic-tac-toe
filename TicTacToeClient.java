/**
 * Reedit Syed Shahriar - 23 April 2017
 * 
 * CONFIDENTIAL
 * 
 * This program tries to connect to a server at the 
 * specified port and IP address. If a connection
 * is made, a game of Tic Tac Toe will start over 
 * the local network, and will end when the game ends.
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.*;
import javax.swing.*;

// Set IP as 192.168.56.1, port to 1337
public class TicTacToeClient extends JFrame{

	private Scanner sc;
	private PrintWriter pw;
	
	public static void main(String[] args) {
		
		new TicTacToeClient();
		
	}
	
	public TicTacToeClient(){
		
		super("Tic Tac Toe! (Client)");
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter IP address: ");
		String name = in.next();
		
		System.out.print("Enter port number: ");
		int port = Integer.parseInt(in.next());
		
		try {
			Socket socket = new Socket(name,port);
			sc = new Scanner(socket.getInputStream());
			pw = new PrintWriter(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("No Line Found.");
		}
		
		setSize(480,480);
		setLocation(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		TTTClientPanel panel = new TTTClientPanel();
		setContentPane(panel);
		setVisible(true);
		
	}
	
	public TicTacToeClient(String name,int port){
		
		super("Tic Tac Toe! (Client)");
		
		try {
			Socket socket = new Socket(name,port);
			sc = new Scanner(socket.getInputStream());
			pw = new PrintWriter(socket.getOutputStream());
		} catch (UnknownHostException e) {
			System.err.println("No host at this IP address.");
			System.exit(1);
		} catch (ConnectException e) {
			System.err.println("No server at this port.");
			System.exit(1);
		} catch (IOException e){
			System.err.println("Unable to manage I/O with server.");
			System.exit(1);
		}
		
		setSize(480,480);
		setLocation(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		TTTClientPanel panel = new TTTClientPanel();
		setContentPane(panel);
		setVisible(true);
		
	}
	
	class TTTClientPanel extends TTTPanel{
		
		protected boolean move;
		
		public TTTClientPanel(){
			
			super();
			move = false;
			lan = true;
			
			MoveThread mt = new MoveThread();
			mt.start();
			
		}
		
		public void mouseClicked(MouseEvent e){
			
			if (move){
				
				move = false;
				
				int x = e.getX(), y = e.getY();
				int index = ((y/150)*3) + ((x/150)+1);
				
				black.add(index);
				pw.println(index);
				pw.flush();
				//System.out.println("Client Sent: "+index);
				
				super.mouseClicked(e);
				
				MoveThread mt = new MoveThread();
				mt.start();

			}
			
		}
		
		class MoveThread extends Thread{
			
			public void run(){
				
				// Scans socket for signal
				int index = Integer.parseInt(sc.nextLine());
				
				//System.out.println("Client Received: "+index);
				red.add(index);
				repaint();
				
				move = true;
				
			}
			
		}
		
	}

}
