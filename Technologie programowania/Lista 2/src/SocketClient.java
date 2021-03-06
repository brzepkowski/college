import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * Klasa SocketClient umozliwia wysylanie polecen do serwera
 * @author ubuntu
 *
 */

class SocketClient extends JFrame implements ActionListener, Runnable {
  
  public String personalNumber = "";
  public String status = "";
  boolean isConnected = false;
  
  //Auction values
  public int someoneBet = 0;
  public int someoneRaised = 0;
  public int highestBid = 0;
  public int lastOffer = 0;
  public int foldStatus = 0;
  public int allInStatus= 0;
  public int tokens = 0;
  public int numberOfCardsChange = 0;
  
  //Cards remembered to change
  public String cardsToChange = "";
  
  public String c1;
  public String c2;
  public String c3;
  public String c4;
  
  public Hashtable buttonsList = new Hashtable();
  
  //Fields which represent Cards
  
  String cardHeld1 = "";
  String cardHeld2 = "";
  String cardHeld3 = "";
  String cardHeld4 = "";
  
  
  public String[] parts;
  public String part1 = "";
  public String part2 = "";
  public String part3 = "";
  public String part4 = "";
  public String part5 = "";
  public String part6 = "";
	
  JLabel output;
  JLabel informationZero;
  JLabel informationOne;
  JLabel nicknameLabel;
  JTextField nicknameTextField;
  JButton button;
  JButton connect;
  JRadioButton buttonP;
  JRadioButton buttonM;
  JRadioButton buttonR;
  JTextField hostTextField;
  JTextField portTextField;
  JTextField input;
  Socket socket = null;
  PrintWriter out = null;
  BufferedReader in = null;
  //ButtonGroup cardsButtonGroup;
  JLabel numberOfTokensTitle;
  JLabel numberOfTokens;
  JLabel gamerStatusTitle;
  JButton sendCards;
  JLabel gamerStatus;
  JToggleButton card1;
  JToggleButton card2;
  JToggleButton card3;
  JToggleButton card4;
  
  //**********************************************************************************************
  
  JLabel gamer1NumberTitle = new JLabel("Numer gracza:");
  JLabel gamer1Number = new JLabel("...");
  JLabel gamer1LastOfferTitle = new JLabel("Ostatnia stawka:");
  JLabel gamer1LastOffer = new JLabel("...");
  JLabel gamer1TokensTitle = new JLabel("Liczba ??eton??w:");
  JLabel gamer1Tokens = new JLabel("...");
  JLabel gamer1StatTitle = new JLabel("Status gracza:");
  JLabel gamer1Stat = new JLabel("...");
  
  JLabel gamer2NumberTitle = new JLabel("Numer gracza:");
  JLabel gamer2Number = new JLabel("...");
  JLabel gamer2LastOfferTitle = new JLabel("Ostatnia stawka:");
  JLabel gamer2LastOffer = new JLabel("...");
  JLabel gamer2TokensTitle = new JLabel("Liczba ??eton??w:");
  JLabel gamer2Tokens = new JLabel("...");
  JLabel gamer2StatTitle = new JLabel("Status gracza:");
  JLabel gamer2Stat = new JLabel("...");
 
  JLabel gamer3NumberTitle = new JLabel("Numer gracza:");
  JLabel gamer3Number = new JLabel("...");
  JLabel gamer3LastOfferTitle = new JLabel("Ostatnia stawka:");
  JLabel gamer3LastOffer = new JLabel("...");
  JLabel gamer3TokensTitle = new JLabel("Liczba ??eton??w:");
  JLabel gamer3Tokens = new JLabel("...");
  JLabel gamer3StatTitle = new JLabel("Status gracza:");
  JLabel gamer3Stat = new JLabel("...");
 
  JLabel gamer4NumberTitle = new JLabel("Numer gracza:");
  JLabel gamer4Number = new JLabel("...");
  JLabel gamer4LastOfferTitle = new JLabel("Ostatnia stawka:");
  JLabel gamer4LastOffer = new JLabel("...");
  JLabel gamer4TokensTitle = new JLabel("Liczba ??eton??w:");
  JLabel gamer4Tokens = new JLabel("...");
  JLabel gamer4StatTitle = new JLabel("Status gracza:");
  JLabel gamer4Stat = new JLabel("...");
  
  JLabel gamer5NumberTitle = new JLabel("Numer gracza:");
  JLabel gamer5Number = new JLabel("...");
  JLabel gamer5LastOfferTitle = new JLabel("Ostatnia stawka:");
  JLabel gamer5LastOffer = new JLabel("...");
  JLabel gamer5TokensTitle = new JLabel("Liczba ??eton??w:");
  JLabel gamer5Tokens = new JLabel("...");
  JLabel gamer5StatTitle = new JLabel("Status gracza:");
  JLabel gamer5Stat = new JLabel("...");
  
  JLabel gamer6NumberTitle = new JLabel("Numer gracza:");
  JLabel gamer6Number = new JLabel("...");
  JLabel gamer6LastOfferTitle = new JLabel("Ostatnia stawka:");
  JLabel gamer6LastOffer = new JLabel("...");
  JLabel gamer6TokensTitle = new JLabel("Liczba ??eton??w:");
  JLabel gamer6Tokens = new JLabel("...");
  JLabel gamer6StatTitle = new JLabel("Status gracza:");
  JLabel gamer6Stat = new JLabel("...");
  
  //****************************************************************************************************8
  
  JPanel panelG;
  JPanel allGamers;
  JPanel gamer1;
  JPanel gamer2;
  JPanel gamer3;
  JPanel gamer4;
  JPanel gamer5;
  JPanel gamer6;
  
  JPanel auction = new JPanel();
  
  JButton Check;
  JButton Bet;
  JButton Raise;
  JButton Call;
  JButton Fold;
  JButton Allin;
  JLabel Auctions;
  JLabel Bid;
  JLabel BidValue;
  JLabel BidPlayer;
  JLabel BidPlayerValue;
  JLabel Offer;
  JTextField YourOffer;
  
  
  //*********************************************************exitListener*************************************************
  WindowListener exitListener = new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {
    	  if (isConnected == false) {
    		  System.out.println("Wy??aczono przed pod????czeniem do serwera.");
    	  }
    	  else {
    	  int choice = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    		if (choice == JOptionPane.YES_OPTION) {
         	 String str = "Disconnected-" + personalNumber + "-0-0-0-0";
        	   	 out.println(str);
              
           }
           if (choice == JOptionPane.NO_OPTION){
         	  System.out.println("Nie roz????czam si??");
         	  
           }
    	  }
      }
  };
  
  	
  
  
  
  /**
   * Konstruktor SocketClient tworzy GUI klienta
   */

  SocketClient() {
	  
	  setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	  addWindowListener(exitListener);
	  
	setLocation(40, 20);
	setSize(1010, 700);
	setLayout(null);
	setTitle("Gracz");
    setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
    informationZero = new JLabel("Podaj adres hosta:");
    informationZero.setBounds(10, 10, 150, 30);
    hostTextField = new JTextField("localhost");
    hostTextField.setBounds(160, 10, 100, 30);
    informationOne = new JLabel("Podaj numer portu:");
    informationOne.setBounds(275, 10, 150, 30);
    portTextField = new JTextField("4444");
    portTextField.setBounds(430, 10, 100, 30);
    nicknameLabel = new JLabel("Pseudonim: ");
    nicknameLabel.setBounds(550, 10, 100, 30);
    nicknameTextField = new JTextField();
    nicknameTextField.setBounds(650, 10, 100, 30);    
    connect = new JButton("Po????cz");
    connect.setBounds(760, 10, 100, 30);
    connect.addActionListener(this);
   

    add(informationZero);
    add(hostTextField);
    add(informationOne);
    add(portTextField);
    add(nicknameLabel);
    add(nicknameTextField);
    add(connect);
    
    output = new JLabel(".......");
    output.setBounds(870, 10, 100, 30);
    add(output); // <--- Testowy textfield
    
    // Panel Of Gamer
    
    panelG = new JPanel();
    
    panelG.setSize(750, 350);
    panelG.setLocation(0, 360);
    panelG.setLayout(null);
    
    numberOfTokensTitle = new JLabel("Liczba ??eton??w:");
    numberOfTokensTitle.setBounds(0, 0 , 150, 30);
    numberOfTokens = new JLabel("...");
    numberOfTokens.setBounds(160, 0, 80, 30);
    gamerStatusTitle = new JLabel("Status gracza:");
    gamerStatusTitle.setBounds(290, 0, 150, 30);
    gamerStatus = new JLabel("...");
    gamerStatus.setBounds(400, 0, 100, 30);
    sendCards = new JButton("Zamien karty");
    sendCards.addActionListener(this);
    sendCards.setBounds(520, 0, 200, 30);
    card1= new JToggleButton ();
    card1.setBounds(0, 40, 174, 265);
    card2= new JToggleButton ();
    card2.setBounds(185, 40, 174, 265);    
    card3= new JToggleButton ();
    card3.setBounds(370, 40, 174, 265);    
    card4= new JToggleButton ();
    card4.setBounds(555, 40, 174, 265);    
    
    //Adding buttons representing cards to Hashtable - it will enable multiple selection
    
    buttonsList.put(1, card1);
    buttonsList.put(2, card2);
    buttonsList.put(3, card3);
    buttonsList.put(4, card4);
    
    
    panelG.add(gamerStatusTitle);
    panelG.add(gamerStatus);
    panelG.add(numberOfTokensTitle);
    panelG.add(numberOfTokens);
    panelG.add(sendCards);
    panelG.add(card1);
    panelG.add(card2);
    panelG.add(card3);
    panelG.add(card4);
    
    panelG.setVisible(true);
    add(panelG);
    
    // Panel of All Gamers
    
    allGamers = new JPanel();
    add(allGamers);
    allGamers.setLocation(0, 50);
    allGamers.setSize(1000, 300);
    allGamers.setLayout(new GridLayout(3, 2));
    
    // Panel of One Gamer
    
    TitledBorder title;
	title = BorderFactory.createTitledBorder("");
    //**************************1*************
    gamer1 = new JPanel();
	gamer1.setBorder(title);
	
	gamer1.setLayout(new GridLayout(2, 2));

	gamer1.add(gamer1NumberTitle);
	gamer1.add(gamer1Number);
	gamer1.add(gamer1LastOfferTitle);
	gamer1.add(gamer1LastOffer);
	gamer1.add(gamer1TokensTitle);
	gamer1.add(gamer1Tokens);
	gamer1.add(gamer1StatTitle);
	gamer1.add(gamer1Stat);
	
	gamer1.setVisible(true);
    allGamers.add(gamer1);
    gamer1Number.setText("1");
    
  //**************************2*************
    gamer2 = new JPanel();
    gamer2.setBorder(title);
	
	gamer2.setLayout(new GridLayout(2, 2));

	gamer2.add(gamer2NumberTitle);
	gamer2.add(gamer2Number);
	gamer2.add(gamer2LastOfferTitle);
	gamer2.add(gamer2LastOffer);
	gamer2.add(gamer2TokensTitle);
	gamer2.add(gamer2Tokens);
	gamer2.add(gamer2StatTitle);
	gamer2.add(gamer2Stat);
	
	gamer2.setVisible(true);
    allGamers.add(gamer2);
    gamer2Number.setText("2");
    
  //**************************3*************
    gamer3 = new JPanel();
    gamer3.setBorder(title);
	
	gamer3.setLayout(new GridLayout(2, 2));

	gamer3.add(gamer3NumberTitle);
	gamer3.add(gamer3Number);
	gamer3.add(gamer3LastOfferTitle);
	gamer3.add(gamer3LastOffer);
	gamer3.add(gamer3TokensTitle);
	gamer3.add(gamer3Tokens);
	gamer3.add(gamer3StatTitle);
	gamer3.add(gamer3Stat);
	
	gamer3.setVisible(true);
    allGamers.add(gamer3);
    gamer3Number.setText("3");
    
  //**************************4*************
    gamer4 = new JPanel();
    gamer4.setBorder(title);
	
	gamer4.setLayout(new GridLayout(2, 2));

	gamer4.add(gamer4NumberTitle);
	gamer4.add(gamer4Number);
	gamer4.add(gamer4LastOfferTitle);
	gamer4.add(gamer4LastOffer);
	gamer4.add(gamer4TokensTitle);
	gamer4.add(gamer4Tokens);
	gamer4.add(gamer4StatTitle);
	gamer4.add(gamer4Stat);
	
	gamer4.setVisible(true);
    allGamers.add(gamer4);
    gamer4Number.setText("4");
    
  //**************************5*************
    gamer5 = new JPanel();
    gamer5.setBorder(title);
	
	gamer5.setLayout(new GridLayout(2, 2));

	gamer5.add(gamer5NumberTitle);
	gamer5.add(gamer5Number);
	gamer5.add(gamer5LastOfferTitle);
	gamer5.add(gamer5LastOffer);
	gamer5.add(gamer5TokensTitle);
	gamer5.add(gamer5Tokens);
	gamer5.add(gamer5StatTitle);
	gamer5.add(gamer5Stat);
	
	gamer5.setVisible(true);
    allGamers.add(gamer5);
    gamer5Number.setText("5");
  //**************************6*************
    gamer6 = new JPanel();
    gamer6.setBorder(title);
	
	gamer6.setLayout(new GridLayout(2, 2));

	gamer6.add(gamer6NumberTitle);
	gamer6.add(gamer6Number);
	gamer6.add(gamer6LastOfferTitle);
	gamer6.add(gamer6LastOffer);
	gamer6.add(gamer6TokensTitle);
	gamer6.add(gamer6Tokens);
	gamer6.add(gamer6StatTitle);
	gamer6.add(gamer6Stat);
	
	gamer6.setVisible(true);
    allGamers.add(gamer6);
    gamer6Number.setText("6");
    // Auction Panel
    
    JPanel auction = new JPanel();
    
    Auctions = new JLabel("Menu licytacji");
    Bid = new JLabel("Stawka:");
    BidValue = new JLabel("0");
    BidPlayer = new JLabel("Gracz: ");
    BidPlayerValue = new JLabel("-");
    Offer = new JLabel("Twoja oferta:");
    YourOffer = new JTextField();
    Check = new JButton("Check");
    Bet = new JButton("Bet");
    Raise = new JButton("Raise");
    Call = new JButton("Call");
    Fold = new JButton("Fold");
    Allin = new JButton("All in");
    
    auction.setLayout(null);
    //auction.setBackground(Color.RED);
    
    add(auction);
    
    auction.add(Check);
    auction.add(Bet);
    auction.add(Raise);
    auction.add(Call);
    auction.add(Fold);
    auction.add(Allin);
    auction.add(Auctions);
    auction.add(Bid);
    auction.add(BidValue);
    auction.add(BidPlayer);
    auction.add(BidPlayerValue);
    auction.add(Offer);
    auction.add(YourOffer);
   
    Check.addActionListener(this);
    Raise.addActionListener(this);
    Bet.addActionListener(this);
    Call.addActionListener(this);
    Fold.addActionListener(this);
    Allin.addActionListener(this);
   
   
    
    auction.setBounds(800,370, 200,300);
    Auctions.setBounds(15,20, 250, 20);
    Bid.setBounds(15, 50, 100, 20);
    BidValue.setBounds(75, 50, 120, 20);
    BidPlayer.setBounds(120, 50, 120, 20);
    BidPlayerValue.setBounds(180, 50, 50, 20);
    Offer.setBounds(15, 80, 110, 20);
    YourOffer.setBounds(115, 80, 80, 20);
    Check.setBounds(15, 110, 90, 20);
    Bet.setBounds(15, 140, 90, 20);
    Raise.setBounds(15, 170, 90, 20);
    Call.setBounds(15, 200, 90, 20);
    Fold.setBounds(15, 230, 90, 20);
    Allin.setBounds(15, 260, 90, 20); 
    
    // Blocking all buttons. If Button connect is pressed all buttons will be unblocked
    blockAllButtons();

    showGamersCardsBackground();
    
}

public void onCheck()
{
   String str = "Auction-" + personalNumber + "-check-0-0-0";
   highestBid = lastOffer;
   out.println(str);
}

public void onBet()
{
	String str = "Auction-" + personalNumber + "-bet-"+YourOffer.getText()+"-0-0";
	try {
		lastOffer = Integer.parseInt(YourOffer.getText());
	}
	catch (NumberFormatException e) {
		System.out.println("Nie podano warto??ci dla Raise");
	}
	highestBid = lastOffer;
	someoneBet = 1;
	out.println("LastOffer-" + personalNumber + "-" + lastOffer + "-0-0-0");
	out.println("HighestBid-" + personalNumber + "-" + lastOffer + "-0-0-0");
	   out.println(str);
}

public void onRaise()
{
	String str = "Auction-" + personalNumber + "-raise-"+YourOffer.getText()+"-0-0";
	try {
	lastOffer = Integer.parseInt(YourOffer.getText());
	}
	catch (NumberFormatException e) {
		System.out.println("Nie podano warto??ci dla Raise");
	}
	
	highestBid = lastOffer;
	someoneRaised = 1;
	out.println("LastOffer-" + personalNumber + "-" + lastOffer + "-0-0-0");
	out.println("HighestBid-" + personalNumber + "-" + lastOffer + "-0-0-0");
	   out.println(str);
}


public void onCall()
{
	String str = "Auction-" + personalNumber + "-call-0-0-0";
	lastOffer = highestBid;
	highestBid = lastOffer;
	out.println("LastOffer-" + personalNumber + "-" + lastOffer + "-0-0-0");
	out.println("HighestBid-" + personalNumber + "-" + lastOffer + "-0-0-0");
	   out.println(str);
}

public void onFold()
{
	String str = "Auction-" + personalNumber + "-fold-0-0-0";
	foldStatus = 1;
	out.println("AnswerFoldStatus-" + personalNumber + "-" + foldStatus + "-0-0-0");
	   out.println(str);
}

public void onAllin() //<---- Do zrobienia
{
	String str = "Auction-" + personalNumber + "-allin-0-0-0-0";
	allInStatus = 1;
	lastOffer = tokens;
	highestBid = tokens;
	someoneRaised = 1;
	out.println("LastOffer-" + personalNumber + "-" + lastOffer + "-0-0-0");
	out.println("HighestBid-" + personalNumber + "-" + lastOffer + "-0-0-0");
	out.println("AnswerAllInStatus-" + personalNumber + "-" + allInStatus + "-0-0-0");
	   out.println(str);
}

/**
 * Potrzebna tablica z kartami
 */
public void onSendCards() {
    String str = "ChangeOfCards-" + personalNumber + "-";
   
    if (card1.isSelected() == false && card2.isSelected() == false && card3.isSelected() == false && card4.isSelected() == false) {
            str += "0-0-0-0";
    }
    else if (card1.isSelected() == false && card2.isSelected() == false && card3.isSelected() == false && card4.isSelected()) {
            str += "0-0-0-"+c4;
    }
    else if (card1.isSelected() == false && card2.isSelected() == false && card3.isSelected() && card4.isSelected() == false) {
            str += "0-0-"+c3+"-0";
    }
    else if (card1.isSelected() == false && card2.isSelected() == false && card3.isSelected() && card4.isSelected()) {
            str += "0-0-"+c3+"-"+c4;
    }
    else if (card1.isSelected() == false && card2.isSelected() && card3.isSelected() == false && card4.isSelected() == false) {
            str += "0-"+c2+"-0-0";
    }
    else if (card1.isSelected() == false && card2.isSelected() && card3.isSelected() == false && card4.isSelected()) {
            str += "0-"+c2+"-0-"+c4;
    }
    else if (card1.isSelected() == false && card2.isSelected() && card3.isSelected() && card4.isSelected() == false) {
            str += "0-"+c2+"-"+c3+"-0";
    }
    else if (card1.isSelected() == false && card2.isSelected() && card3.isSelected() && card4.isSelected()) {
            str += "0-"+c2+"-"+c3+"-"+c4;
    }
    else if (card1.isSelected() && card2.isSelected() == false && card3.isSelected() == false && card4.isSelected() == false) {
            str += c1+"-0-0-0";
    }
    else if (card1.isSelected() && card2.isSelected() == false && card3.isSelected() == false && card4.isSelected()) {
            str += c1+"-0-0-"+c4;
    }
    else if (card1.isSelected() && card2.isSelected() == false && card3.isSelected() && card4.isSelected() == false) {
            str += c1+"-0-"+c3+"-0";
    }
    else if (card1.isSelected() && card2.isSelected() == false && card3.isSelected() && card4.isSelected()) {
            str += c1+"-0-"+c3+"-"+c4;
    }
    else if (card1.isSelected() && card2.isSelected() && card3.isSelected() == false && card4.isSelected() == false) {
            str += c1+"-"+c2+"-0-0";
    }
    else if (card1.isSelected() && card2.isSelected() && card3.isSelected() == false && card4.isSelected()) {
            str += c1+"-"+c2+"-0-"+c4;
    }
    else if (card1.isSelected() && card2.isSelected() && card3.isSelected() && card4.isSelected() == false) {
            str += c1+"-"+c2+"-"+c3+"-0";
    }
    else if (card1.isSelected() && card2.isSelected() && card3.isSelected() && card4.isSelected()) {
            str += c1+"-"+c2+"-"+c3+"-"+c4;
    }
    cardsToChange = str;
    out.println(str);
    
    //Deselect all selected buttons
    card1.setSelected(false);
    card2.setSelected(false);
    card3.setSelected(false);
    card4.setSelected(false);
   
    //output.setText(str);
}

	public void blockAllButtons() {
		Check.setEnabled(false);
	    Bet.setEnabled(false);
	    Raise.setEnabled(false);
	    Call.setEnabled(false);
	    Fold.setEnabled(false);
	    Allin.setEnabled(false);
	    
	    card1.setEnabled(false);
	    card2.setEnabled(false);
	    card3.setEnabled(false);
	    card4.setEnabled(false);
	    sendCards.setEnabled(false);
	}
	
	public void blockCardButtons() {
		card1.setEnabled(false);
	    card2.setEnabled(false);
	    card3.setEnabled(false);
	    card4.setEnabled(false);
	    sendCards.setEnabled(false);
	}
	
	public void unlockCardButtons() {
		card1.setEnabled(true);
	    card2.setEnabled(true);
	    card3.setEnabled(true);
	    card4.setEnabled(true);
	    sendCards.setEnabled(true);
	}
	
	public void blockAuctionButtons() {
		Check.setEnabled(false);
	    Bet.setEnabled(false);
	    Raise.setEnabled(false);
	    Call.setEnabled(false);
	    Fold.setEnabled(false);
	    Allin.setEnabled(false);
	}
	
	public void unlockAuctionButtons() {
		someoneBet = Integer.parseInt(part3);
		someoneRaised = Integer.parseInt(part4);
		highestBid = Integer.parseInt(part5);
		
		if (lastOffer == highestBid && someoneBet == 0) {
			Check.setEnabled(true);
		}
		else {
			Check.setEnabled(false);
		}
		if (someoneBet == 1 && tokens > highestBid) {
			Bet.setEnabled(false);
		}
		else {
			Bet.setEnabled(true);
		}
		if (someoneBet == 0 || tokens < highestBid) {
			Raise.setEnabled(false);
		}
		else {
			Raise.setEnabled(true);
		}
		if (lastOffer < highestBid && tokens >= highestBid) {
			Call.setEnabled(true);
		}
		else {
			Call.setEnabled(false);
		}
	    Fold.setEnabled(true);
	    Allin.setEnabled(true);
	    
	    System.out.println("SbBet: " + someoneBet + " sbRaised: " + someoneRaised + " highestBid: "  + highestBid +
	 		   " lastOffer: " + lastOffer + " tokens: " + tokens);
	}
    
	public void unlockAllButtons() {
		Check.setEnabled(true);
	    Bet.setEnabled(true);
	    Raise.setEnabled(true);
	    Call.setEnabled(true);
	    Fold.setEnabled(true);
	    Allin.setEnabled(true);
	    
	    card1.setEnabled(true);
	    card2.setEnabled(true);
	    card3.setEnabled(true);
	    card4.setEnabled(true);
	    sendCards.setEnabled(true);

	}
  

  public void actionPerformed(ActionEvent event) {
	if(event.getSource() == sendCards) {
		onSendCards();
    }
    else if(event.getSource() == connect) {
    	listenSocket();
    }
    else if (event.getSource() == Check) {
    	onCheck();
    }
    else if (event.getSource() == Bet) {
    	onBet();
    }
    else if (event.getSource() == Raise) {
    	onRaise();
    }
    else if (event.getSource() == Call) {
    	onCall();
    }
    else if (event.getSource() == Fold) {
    	onFold();
    }
    else if (event.getSource() == Allin) {
    	onAllin();
    }
  }

  public void listenSocket(){
    try {
    	if (isConnected == true) {}
    	else {
      String hostAddress = hostTextField.getText();
      int portAddress = Integer.parseInt(portTextField.getText());
      socket = new Socket(hostAddress, portAddress);
      System.out.println ("Po????czono z socketem: " + socket);
      out = new PrintWriter(socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      
      //Prosba o przydzielenie numeru gracza
      
      personalNumber = in.readLine();
      changeGamerColor();
      output.setText(personalNumber);
      
      new Thread (this).start();
      
      isConnected = true;
    	}
    }
    catch (UnknownHostException e) {
       System.out.println("Unknown host"); System.exit(1);
     }
     catch  (IOException e) {
       System.out.println("No I/O");
     }
    
  }
  public void run() {
	  while (true) {
		  
		  try {
			String message = in.readLine();
			GUIinterpreter(message);
			
			 System.out.println("Message odebrana przez klienta: //" + message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  
	  }
  }
  
  public void GUIinterpreter(String message) {
	  try{
	  parts = message.split("-");
	  part1 = parts[0];
	  part2 = parts[1];
	  part3 = parts[2];
	  part4 = parts[3];
	  part5 = parts[4];
	  part6 = parts[5];
	  }
	  catch (NullPointerException e) {
		  //System.out.println("Serwer zosta?? wy????czony, prosz?? si?? roz????czy??");
		  JOptionPane.showMessageDialog(null, "Wy????czono serwer, nast??pi wy????czenie programu");
    	  System.exit(0);
	  }
	  
	  if (part1.equals("Status")) {
		  if (part2.equals(personalNumber)) {
			  status = "dealer";
			  gamerStatus.setText("Dealer");
		  }
		  else if (part3.equals(personalNumber)) {
			  status = "smallBlind";
			  gamerStatus.setText("Small Blind");
		  }
		  else if (part4.equals(personalNumber)) {
			  status = "bigBlind";
			  gamerStatus.setText("Big Blind");
		  }
		  
		  printGamersStatus();
		  output.setText(message);
	  }
	  else if (part1.equals("Tokens")) {
		  printGamersTokens();
		  output.setText(message);		  
	  }
	  else if (part1.equals("LastOffer")) { 
		  printLastOffer(part3);
	  }
	  else if (part1.equals("SetLastOffer")) {
		  if (part2.equals(personalNumber)) {
			  lastOffer = Integer.parseInt(part3);
		  }
	  }
	  else if (part1.equals("SetNotFoldStatus")) {
		  if (part2.equals(personalNumber)) {
			  foldStatus = 0;
		  }
	  }
	  /*else if (part1.equals("CheckFoldStatus")) {
		  if (part2.equals(personalNumber)) {
			  out.println("AnswerFoldStatus-" + personalNumber + "-" + foldStatus + "-0-0-0");
		  }
	  }*/
	  else if (part1.equals("SetNotAllInStatus")) {
		  if (part2.equals(personalNumber)) {
			  allInStatus = 0;
		  }
	  }
	  /*else if (part1.equals("CheckAllInStatus")) {
		  if (part2.equals(personalNumber)) {
			  out.println("AnswerAllInStatus-" + personalNumber + "-" + allInStatus + "-0-0-0");
		  }
	  }*/
	  else if (part1.equals("HighestBid")) { //<--------------------------------------
			  highestBid = Integer.parseInt(part3);
			  BidValue.setText(part3);
			  output.setText(message);	
		  
	  }
	  else if (part1.equals("ShowBackground")) {
		  showGamersCardsBackground();
	  }
	  
	  else if (part1.equals("BasicCards")) {
		  setCardImage();
	  }
	  else if (part1.equals("UnlockButtons")) {
		  unlockAllButtons();
	  }
	  else if (part1.equals("UnlockAuctionButtons")) {
		  if (part2.equals(personalNumber)) {
			  System.out.println("Klient: UAB: " + message);
			  unlockAuctionButtons();
		  }
		  else {
			  someoneBet = Integer.parseInt(part3);
			  someoneRaised = Integer.parseInt(part4);
			  highestBid = Integer.parseInt(part5);
		  }
	  }
	  else if (part1.equals("BlockAuctionButtons")) {
		  if (part2.equals(personalNumber)) {
			  blockAuctionButtons();
		  }
	  }
	  else if (part1.equals("UnlockCardButtons")) {
		  if (part2.equals(personalNumber)) {
			  unlockCardButtons();
		  }
	  }
	  else if (part1.equals("BlockCardButtons")) {
		  if (part2.equals(personalNumber)) {
			  blockCardButtons();
		  }
	  }
	  else if (part1.equals("ChangeOfCards")) {
		  if (part2.equals(personalNumber)) {
			  System.out.println("Do zamiany: " + part3 + "-" + part4 + "-" + part5 + "-" + part6);
			  changeOfCards(part3, part4, part5, part6);
		  }
	  }
	  else if (part1.equals("LimitOfPlayers")) {
		  showDiscardedPlayers(part2);
	  }
	  else if (part1.equals("GetCardValues")) {
		  if (part2.equals(personalNumber)) {
			  String str = "CardValues-" + personalNumber + "-" + c1 + "-" + c2 + "-" + c3 + "-" + c4;
			  System.out.println("KLIENT: Karty GRACZA " + personalNumber + " :" + c1 + "-" + c2 + "-" + c3 + "-" + c4);
			  out.println(str);
		  }
	  }
	  else if (part1.equals("Disconnected")) {
		  JOptionPane.showMessageDialog(null, "Jeden z graczy si?? roz????czy??, nast??pi wy????czenie programu");
    	  System.exit(0);
	  }
	/*  else if(part1.equals("Lock")){
		  if(part2.equals(personalNumber)){
				if(part3.equals("0"))
					Check.setEnabled(false);
					else
					Check.setEnabled(true);
					if(part4.equals("0"))
					Call.setEnabled(false);
					else
					Call.setEnabled(true);
					if(part5.equals("0"))
					Raise.setEnabled(false);
					else
					Raise.setEnabled(true);
					if(part6.equals("0"))
					Bet.setEnabled(false);
					else
					Bet.setEnabled(true);
					Fold.setEnabled(true);
					Allin.setEnabled(true);
					}
	  }
	  */
	  else {
	  
	  output.setText(message);
	  }
  }
  
  public void changeGamerColor() {
	  if (personalNumber.equals("1")) {
		  gamer1.setBackground(Color.ORANGE); 
	  }
	  if (personalNumber.equals("2")) {
		  gamer2.setBackground(Color.ORANGE); 
	  }
	  if (personalNumber.equals("3")) {
		  gamer3.setBackground(Color.ORANGE); 
	  }
	  if (personalNumber.equals("4")) {
		  gamer4.setBackground(Color.ORANGE); 
	  }
	  if (personalNumber.equals("5")) {
		  gamer5.setBackground(Color.ORANGE); 
	  }
	  if (personalNumber.equals("6")) {
		  gamer6.setBackground(Color.ORANGE); 
	  }
  }
  
  public void printLastOffer(String gamerOffer) {
	 if (part2.equals("1")) {
		 gamer1LastOffer.setText(gamerOffer);
	 }
	 if (part2.equals("2")) {
		 gamer2LastOffer.setText(gamerOffer);
	 }
	 if (part2.equals("3")) {
		 gamer3LastOffer.setText(gamerOffer);
	 }
	 if (part2.equals("4")) {
		 gamer4LastOffer.setText(gamerOffer);
	 }
	 if (part2.equals("5")) {
		 gamer5LastOffer.setText(gamerOffer);
	 }
	 if (part2.equals("6")) {
		 gamer6LastOffer.setText(gamerOffer);
	 }
  }
  
  public void printGamersStatus() {
	  if (part2.equals("1")) {
		  gamer1Stat.setText("Dealer");
	  }
	  else if (part2.equals("2")) {
		  gamer2Stat.setText("Dealer");
	  }
	  else if (part2.equals("3")) {
		  gamer3Stat.setText("Dealer");
	  }
	  else if (part2.equals("4")) {
		  gamer4Stat.setText("Dealer");
	  }
	  else if (part2.equals("5")) {
		  gamer5Stat.setText("Dealer");
	  }
	  else if (part2.equals("6")) {
		  gamer6Stat.setText("Dealer");
	  }
	  
	  if (part3.equals("1")) {
		  gamer1Stat.setText("Small Blind");
	  }
	  else if (part3.equals("2")) {
		  gamer2Stat.setText("Small Blind");
	  }
	  else if (part3.equals("3")) {
		  gamer3Stat.setText("Small Blind");
	  }
	  else if (part3.equals("4")) {
		  gamer4Stat.setText("Small Blind");
	  }
	  else if (part3.equals("5")) {
		  gamer5Stat.setText("Small Blind");
	  }
	  else if (part3.equals("6")) {
		  gamer6Stat.setText("Small Blind");
	  }
	  
	  if (part4.equals("1")) {
		  gamer1Stat.setText("Big Blind");
	  }
	  else if (part4.equals("2")) {
		  gamer2Stat.setText("Big Blind");
	  }
	  else if (part4.equals("3")) {
		  gamer3Stat.setText("Big Blind");
	  }
	  else if (part4.equals("4")) {
		  gamer4Stat.setText("Big Blind");
	  }
	  else if (part4.equals("5")) {
		  gamer5Stat.setText("Big Blind");
	  }
	  else if (part4.equals("6")) {
		  gamer6Stat.setText("Big Blind");
	  }
  }
  
  public void printGamersTokens() {
	  if (part2.equals(personalNumber)) {
		  numberOfTokens.setText(part3);
		  tokens = Integer.parseInt(part3);
	  }
	  if (part2.equals("1")) {
		  gamer1Tokens.setText(part3);
	  }
	  if (part2.equals("2")) {
		  gamer2Tokens.setText(part3);
	  }
	  if (part2.equals("3")) {
		  gamer3Tokens.setText(part3);
	  }
	  if (part2.equals("4")) {
		  gamer4Tokens.setText(part3);
	  }
	  if (part2.equals("5")) {
		  gamer5Tokens.setText(part3);
	  }
	  if (part2.equals("6")) {
		  gamer6Tokens.setText(part3);
	  }
  }
  
  public void showGamersCardsBackground () {
	  ImageIcon icon = new ImageIcon("resources/Cards/Outside.png");
	  card1.setIcon(icon);
	  card2.setIcon(icon);
	  card3.setIcon(icon);
	  card4.setIcon(icon);
  }
  
  public void showDiscardedPlayers (String limitOfPlayers) {
	  if (limitOfPlayers.equals("2")) {
		  gamer3.setBackground(Color.RED);
		  gamer4.setBackground(Color.RED);
		  gamer5.setBackground(Color.RED);
		  gamer6.setBackground(Color.RED);
		  System.out.println("zaznaczy??o reszt?? niekatywnych graczy");
	  }
	  else if (limitOfPlayers.equals("3")) {
		  gamer4.setBackground(Color.RED);
		  gamer5.setBackground(Color.RED);
		  gamer6.setBackground(Color.RED);
	  }
	  else if (limitOfPlayers.equals("4")) {
		  gamer5.setBackground(Color.RED);
		  gamer6.setBackground(Color.RED);
	  }
	  else if (limitOfPlayers.equals("5")) {
		  gamer6.setBackground(Color.RED);
	  }
	  else if (limitOfPlayers.equals("6")) {
	  }
  }
  
  public void setCardImage () {
	  
	  if (personalNumber.equals(part2)) {
  
	  for (int i = 0; i < 4; i++) {
		  if (i == 0) {
			  ImageIcon icon1 = new ImageIcon(createImageSource(part3));
			  card1.setIcon(icon1);	
			  c1 = part3;
		  }
		  
		  if (i == 1) {
			  ImageIcon icon2 = new ImageIcon(createImageSource(part4));
			  card2.setIcon(icon2);	
			  c2 = part4;
		  }
		  
		  if (i == 2) {
			  ImageIcon icon3 = new ImageIcon(createImageSource(part5));
			  card3.setIcon(icon3);	
			  c3 = part5;
		  }
		  
		  if (i == 3) {
			  ImageIcon icon4 = new ImageIcon(createImageSource(part6));
			  card4.setIcon(icon4);	
			  c4 = part6;
		  }
		  
	  }
	  }
  
  }
  
  public String createImageSource(String part) {
	  char c;
	  String str = "resources/Cards/";
	//Which folder
	  c = part.charAt(1);
	  if (c == 'c') { 
		  str = str + "/c";
	  }
	  if (c == 'd') { 
		  str = str + "/d";
	  }
	  if (c == 'h') { 
		  str = str + "/h";
	  }
	  if (c == 's') { 
		  str = str + "/s";
	  }
	  //Which file
	  c = part.charAt(0);
	  if (c == '2') {
		  str = str + "/2.png";
	  }
	  if (c == '3') {
		  str = str + "/3.png";
	  }
	  if (c == '4') {
		  str = str + "/4.png";
	  }
	  if (c == '5') {
		  str = str + "/5.png";
	  }
	  if (c == '6') {
		  str = str + "/6.png";
	  }
	  if (c == '7') {
		  str = str + "/7.png";
	  }
	  if (c == '8') {
		  str = str + "/8.png";
	  }
	  if (c == '9') {
		  str = str + "/9.png";
	  }
	  if (c == '1') {
		  str = str + "/10.png";
	  }
	  if (c == 'j') {
		  str = str + "/J.png";
	  }
	  if (c == 'q') {
		  str = str + "/Q.png";
	  }
	  if (c == 'k') {
		  str = str + "/K.png";
	  }
	  if (c == 'a') {
		  str = str + "/A.png";
	  }
	  return str;
  }
  //************************************************************************************************************************
  public void changeOfCards(String part3, String part4, String part5, String part6) {
	  
	  System.out.println("Change of cards: " + cardsToChange);
	  System.out.println("Party: " + part3 + "-" + part4 + "-" + part5 + "-" + part6);
	  String[] cardParts = cardsToChange.split("-");
	  
	  //Zliczanie, ile kart do wymiany w sumie
	  if (cardParts[2].equals("0") == false) {
		  ImageIcon icon = new ImageIcon(createImageSource(part3));
		  card1.setIcon(icon);
		  c1 = part3;
	  }
	  if (cardParts[3].equals("0") == false) {
		  ImageIcon icon = new ImageIcon(createImageSource(part4));
		  card2.setIcon(icon);
		  c2 = part4;
	  }
	  if (cardParts[4].equals("0") == false) {
		  ImageIcon icon = new ImageIcon(createImageSource(part5));
		  card3.setIcon(icon);
		  c3 = part5;
	  }
	  if (cardParts[5].equals("0") == false) {
		  ImageIcon icon = new ImageIcon(createImageSource(part6));
		  card4.setIcon(icon);
		  c4 = part6;
	  }
	  
	  
	  
  }

  public static void main(String[] args){
    SocketClient frame = new SocketClient();
    frame.addWindowListener( new WindowAdapter() {
                               public void windowClosing(WindowEvent e) {
                                 System.exit(0);
                               }
                             } );
    //frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);
    //frame.run();
    
    
  }
}