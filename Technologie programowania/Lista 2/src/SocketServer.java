import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import java.util.*; /* import bibliotek narzedziowych */
import java.net.*; /*import bibliotek odpowiedzialnych za komunikacje sieciowa */


public class SocketServer extends Thread{
	
	public String message;
	
  // ServerSocket for accepting new connections (may be important to add private)
  ServerSocket server = null;
  
  //ArrayList of all connected Clients - enables to send messages to all of them
  public Hashtable allStreams = new Hashtable(); //<==== Can be changed to HashMap
  
  public static int bots=0;
  
  static int numberOfPlayers = 0;
  static int port;
  private static int limitOfPlayers = 2;
  public static int tokens = 0;
  public static int smallBlindBet;
  public static int bigBlindBet;
  public static Bot[] bot;
  
  boolean allConnected = false;
  
  public Table table;


  
  // Constructor of SocketServer creates port on which it will be listening
  //to new Clients
  SocketServer(int port, int limitOfPlayers) {   
    
	  listenSocket(port, limitOfPlayers);
  }
  
  
  // Method listen() enables to listen forever for new clients
  public void listenSocket(int port, int limitOfPlayers) {
	  
	  try {
		server = new ServerSocket(port);

		
	  
	  System.out.println("Uruchomiono serwer na porcie: " + port);
	  
	  while(allConnected == false) {
		  
			  Socket client = server.accept();
			  
			  numberOfPlayers++;
			 
			  System.out.println("Nawiazano polaczenie z:" + client);
      
      		  PrintWriter out = new PrintWriter(client.getOutputStream(), true);
      
      		  allStreams.put(numberOfPlayers, out);
      		 
      		  // Creates a Thread to deal with new connection
      		  //(A thread is generally defined as separate line of control within one  process)
      		  //(Many task done at the same time (multitasking).
      		  new ServerThread (this, client);
      		  
      		  out.println(numberOfPlayers);
      		  
      		 
      		if (numberOfPlayers == limitOfPlayers) {
				  allConnected = true;
				  System.out.println("wszyscy gracze pod????czeni");
			  }
      		  
      } 
	  //Creation of Object table
	  if (numberOfPlayers == limitOfPlayers) {
		  table = new Table(this);
		  broadcast("LimitOfPlayers-" + limitOfPlayers + "-0-0-0-0");
		  broadcast("Tokens-" + tokens + "-0-0-0-0"); // Additional zeros not to make error with sending
	  }
	  
	  }
	  catch (IOException e) {
			e.printStackTrace();
		}
  } 
  
  
 	// Gets register/specification of all connected Clients
  	Enumeration getAllStreams() {
  
	  	return allStreams.elements();
  	}

  	public void broadcast(String message) {
  		
  		//We have to synchronize, because Clients may disconnect
  		synchronized (allStreams) {
  			
  			for (Enumeration enumeration = getAllStreams(); enumeration.hasMoreElements(); ) {
  				
  				PrintWriter out = (PrintWriter)enumeration.nextElement();
  				
  				out.println(message);
  				
  			}
  		}
  	}
  	
  	public static int getLimitOfPlayers() {
  		return limitOfPlayers;
  	}


  	public static void setLimitOfPlayers(int limitOfPlayers) {
  		SocketServer.limitOfPlayers = limitOfPlayers;
  	}

  public static void main(String[] args) {
	  try {
		  // args[0] - port || args[1] - limitOfPlayers || args[2] - tokens for all players 
		  // args[3] - Bet of Small Blind || args[4] - Bet of Big Blind || args[5] - number of bots
		  port = Integer.parseInt(args[0]);
		  setLimitOfPlayers(Integer.parseInt(args[1]));
		  tokens = Integer.parseInt(args[2]);
		  smallBlindBet = Integer.parseInt(args[3]);
		  bigBlindBet = Integer.parseInt(args[4]);
		  bots = Integer.parseInt(args[5]);
		  
		  
	  }
	  catch (NumberFormatException ex) {
		  System.out.println("Nieprawidlowa dana");
	  }
	  if (bots > 0) {
	  bot = new Bot[bots];
	  int numberOfBots = 0;
      if(numberOfBots < bots){
			  for(int i=0; i<bots; i++){
				  bot[i]=new Bot(port);
				  System.out.println("Bot "+i+" created");
				  numberOfBots++;
			  }
		  }
	  }
	  new SocketServer(port, getLimitOfPlayers());
	
	  
	  
  }
}