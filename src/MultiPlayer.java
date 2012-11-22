import java.awt.*;
import java.applet.*; 

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;


@SuppressWarnings("serial")
public class MultiPlayer extends Applet implements Runnable{

	
	private Image dbImage;
	private Graphics dbg; 
	private Paddle p1 = new Paddle(50, 570);
	private Paddle p2 = new Paddle(50, 0);
	private Ball ball = new Ball();
	private PowerUp powerup = new PowerUp();
	private int paddle_timer = 0;
	private int powerup_random = 0;
	private int powerup_timer = 0;
	private int powerup_x_pos = 0;
	private int powerup_x_pos_random = 200;
	private int powerup_y_pos = 0;
	private int powerup_y_pos_random = 220;
	private boolean p1_powerup = false;
	private int p1_randompowerup = 0;
	private boolean p1_powerup_asserted = false;
	private boolean p2_powerup = false;
	private int p2_randompowerup = 0;
	private boolean p2_powerup_asserted = false;
	private boolean powerup_appear = false;
	private boolean p1_up = false;
	private boolean p1_left = false;
	private boolean p1_right = false;
	private boolean p1_down = false;
	private boolean p2_up = false;
	private boolean p2_left = false;
	private boolean p2_right = false;
	private boolean p2_down = false;
	private int right = 1;
	private int left = -1;
	private int up = -1;
	private int down = 1;
	private boolean start_ball_timer = false;
	private int ball_timer = 0;
	private int p1_lives = 10;
	private int p2_lives = 10;
	private int p1_fuel = 80;
	private int p2_fuel = 80;
	private boolean start = false;
	private boolean p1_inversekeys = false;
	private boolean p2_inversekeys = false;
	private volatile Thread initialize_game;
	private boolean startofgame = false;
	private boolean pausegame = false;
	public boolean instruction = true;
	
	
	// MultiPlayer
	
	
	 public final static int NULL = 0;
	 public final static int DISCONNECTED = 1;
	 public final static int CONNECTED = 2;
	 public final static int CONNECTING = 3;
	 
	 
	 public int state = DISCONNECTED;
	 public boolean server = false;
	 public String IP = "";
	 public int Port = 0;
	
	 private int UDPPort = 750;
	 
	 
	 
	 
	 // Socket Objects
	 ServerSocket srvr = null;
	 Socket serverSck = null;
	 Socket clientSck = null;
	 
	 InputStream in = null;
	 OutputStream out = null;
	 
	 DataInputStream srvIn = null;
	 DataOutputStream srvOut = null;
	 
	
	 DataInputStream cliIn = null;
	 DataOutputStream cliOut = null;
	 
	 //UDP socket 
	 
	 DatagramSocket cliUDP = null;
	 DatagramSocket srvUDP = null; 
	 
	 byte[] rcvUDP = new byte[150];
	 byte[] sendUDP = new byte[150];
 
	 
	 public void connect()
	 {	
	 //Requires: UDPPort,IP Address, Port  
     //Effects: if host, create TCP socket and listen to the assigned PORT
	 // 		else client,create TCP socket and connect to server. 
	 // 		Both client and host creates a UDP datagram using port assigned at UDPPort 
		 
		 
		 
		 
  
		 
		if(server == true)
		{
			try{ //server
				
			 //create socket  
	         srvr = new ServerSocket(Port);
	         
	         // listening 
	         serverSck = srvr.accept();
	         serverSck.setTcpNoDelay(true);
	         serverSck.setReuseAddress(true);
	         
			 while(!serverSck.isConnected()); // wait for connection
			 
			this.state = 2; // change state to connected
	         
	
			// start game 
			start = true;
			startofgame = true;
			
			
	 //        in = serverSck.getInputStream();
	 //        srvIn = new DataInputStream(in);
	         
	  //       out = serverSck.getOutputStream();
	   //      srvOut = new DataOutputStream(out);
	 
			// UDP datagram socket
			srvUDP = new DatagramSocket();
			srvUDP.setSoTimeout(1);
	 
	        
	         
	         
			}
			catch(Exception e){
				e.printStackTrace();
				}
		}
			else{
			try{ // client
				
					
			// create TCP socket	
			clientSck = new Socket(IP, Port);
			clientSck.setTcpNoDelay(true);
			clientSck.setReuseAddress(true);
			
//			in = clientSck.getInputStream();
//		    out = clientSck.getOutputStream();
		    
//		    cliIn = new DataInputStream(in);
//		    cliOut = new DataOutputStream(out);
		    
		    while(!clientSck.isConnected()); // wait for connection
			this.state = 2; // change state to connected
			
			// start game
			start = true;
			startofgame = true;
				
			//create UDP datagram socket
			cliUDP = new DatagramSocket(UDPPort);
			cliUDP.setSoTimeout(1);

			
			
			
			}
			catch(Exception e ){
				System.out.println("Cant' create socket");
				};
				

			
		}
		
	
	 }
	 
	public void disconnect()
	{
		// Effects: if the program is host, close server TCP and UDP sockets
		//			else close client TCP and UDP sockets
		
		
		if(server == true)
		{
			try{
				// close socket
		     							
//				 in.close();
//				 out.close();
//				 srvIn.close();
//		         srvOut.close();
	
				 serverSck.close();
		         srvr.close();
		         
		         srvUDP.close();
		         
		         
			}catch(Exception e){
				System.out.println("Can't Close socket");
				}
		}
		else
		{	
			try{
				
				
				
//				in.close();
//				out.close();
//				cliIn.close();
//				cliOut.close();
				clientSck.close();
				cliUDP.close();
				
				
				
			}
			catch(Exception e){
				System.out.println("Can't Close Socket");
				}
			 
		}
		
		
	}
	 
	 
	
	public MultiPlayer()
	{
	// Default Constructor	
		
	}
	
	 public void stop() {
	    	
	// Effect: Reinitialize all variables
		 
		
	        initialize_game = null;
	        p1.SetHardPosition(100,40);
			p2.SetHardPosition(100,550);
			ball.Replace();
			p2_lives = 10;
			p1_lives = 10;
			p1_fuel = 80;
			p2_fuel = 80;
			p2_left = false;
			p2_right = false;
			p2_down = false;
			p2_up = false;
			powerup_appear = false;
			start = false;
			
			
			
	    }
	
	public void init()
	{
	// Effect: Restart playing field and place ball at the middle of the field	
		
		setBackground (Color.black);
		p1.SetHardPosition(100,40);
		p2.SetHardPosition(100,550);
		if (ball_timer == 200)
			ball.Replace();
		start();
		ball.Still();
		
		
        
		
	}

	public void start ()
	{
	// Effect: Start the game by running the game as a thread		
			Thread thread = new Thread (this);
			thread.start ();	
	}
	
	
	public void run() {
	// Effect:
		

		StringTokenizer token;
		
		
		
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
	
		Thread thisThread = Thread.currentThread();
			initialize_game = thisThread;
	
		while (initialize_game != null)
		{
			
			if (startofgame == true)
			{
				ball.Replace();
				startofgame = false;
			}

			
				// State machine for TCP connection state 
				switch(state)
				{
					case DISCONNECTED:
					
				
					if(serverSck != null || clientSck !=null)
					{
						
							// disconnect server or client 
							disconnect();
							repaint();
							this.stop();
							this.start();
					}
						
						
					break;
					
					case CONNECTING:
						
						// connect to server or host a game
						repaint();
						System.out.println("Connecting");
						connect();
						
						
					break;
					
				
				
				
				
				
					case CONNECTED:
	
							
						
					if (start == true && pausegame == false)
					{
					
						
						
						String sendBuffer;
						String rcvBuffer;
						DatagramPacket sendPacket;	
						DatagramPacket rcvPacket;
						
						try{
						
							if(server == true)
							{
								// Prepare string to be sent 
								sendBuffer = new String(Integer.toString(ball.GetX()) + "," + Integer.toString(ball.GetY())+","+
						                Integer.toString(p1.GetX()) + "," + Integer.toString(p1.GetY()) + "," +
						                Integer.toString(powerup_x_pos) + "," + Integer.toString(powerup_y_pos) +"," + 
						                Integer.toString(p1_fuel) +"," + Integer.toString(p2_fuel) + "," +  
						                Boolean.toString(powerup_appear) + "," +  Integer.toString(paddle_timer) + "," + 
						                Integer.toString(p1_lives) + "," + Integer.toString(p2_lives) + "," +
						                Boolean.toString(start) + "," + Boolean.toString(pausegame) + "," +
						                Boolean.toString(p1_powerup) + "," + Integer.toString(p1_randompowerup) + "," +
						                Boolean.toString(p2_powerup)
						                );
								
								// get player control keys
								if (p1_left == true)
								{
									if (p1.GetX() >= 13 && paddle_timer <= 15)
										p1.SetPosition(left,0);
								}
							      // user presses 'd' key -> go right
								if (p1_right == true)
							    {
									if (p1.GetX() <= 320 && paddle_timer <= 15)
										p1.SetPosition(right,0);
							    }
							      // user presses 'w' key -> go up
							    if (p1_up == true)
							    {
							    	if(p1.GetY() >= 45 && paddle_timer <= 15)
							    		p1.SetPosition(0,up);
							    }
							      // user presses 's' key -> go down
							    if (p1_down == true)
							    {
							    	if (p1.GetY() <= 440 && paddle_timer <= 15)
							    		p1.SetPosition(0,down);
							    }
							    
							  
					
									
								// convert string to bytes
								sendUDP = sendBuffer.getBytes();
	
								// create UDP packet
								sendPacket = new DatagramPacket(sendUDP, sendUDP.length, serverSck.getInetAddress(), UDPPort); 
								
								// send UDP datagram 
								srvUDP.send(sendPacket);
								
								// prepare receive UDP datagram
								rcvPacket = new DatagramPacket(rcvUDP,rcvUDP.length);
								
								// receive UDP datagram if data is available else wait for 1ms and move on 
							    srvUDP.receive(rcvPacket);
							    
							    // assign receive data to string
							    rcvBuffer = new String(rcvPacket.getData());
							    
							    // extract UDP data 
							    if(rcvBuffer != null)
							    {
									token = new StringTokenizer(rcvBuffer,",");
									p2.SetMultiPosition(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
									p2_powerup_asserted = Boolean.parseBoolean(token.nextToken());
	
									rcvBuffer = null;
							    }
				
							    
						
							    
									
							} // end if server 
							else
							{

								    // prepare to receive UDP datagram
								    rcvPacket = new DatagramPacket(rcvUDP,rcvUDP.length);
									
								    // receive UDP datagram if available else wait for 1ms then move on
								    cliUDP.receive(rcvPacket);
								    
								    // convert received data to string
								    rcvBuffer = new String(rcvPacket.getData());
									
								    // extract received UDP data and apply to local variables
								    if(rcvBuffer != null)
								    {
										
									token = new StringTokenizer(rcvBuffer,",");
									ball.SetX(Integer.parseInt(token.nextToken()));
									ball.SetY(Integer.parseInt(token.nextToken()));
									p1.SetMultiPosition(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
									powerup_x_pos = Integer.parseInt(token.nextToken());
									powerup_y_pos = Integer.parseInt(token.nextToken());
									p1_fuel = Integer.parseInt(token.nextToken());
									p2_fuel = Integer.parseInt(token.nextToken());
									powerup_appear = Boolean.parseBoolean(token.nextToken());
									paddle_timer = Integer.parseInt(token.nextToken());
									p1_lives = Integer.parseInt(token.nextToken());
									p2_lives = Integer.parseInt(token.nextToken());
									start = Boolean.parseBoolean(token.nextToken());
									pausegame = Boolean.parseBoolean(token.nextToken());
									p1_powerup = Boolean.parseBoolean(token.nextToken());
									p1_randompowerup = Integer.parseInt(token.nextToken());
									p2_powerup = Boolean.parseBoolean(token.nextToken());
									
									rcvBuffer = null;
									
								    }
									
								    
								    // get client control keys
									   if (p2_left == true)
									    {
									    	if (p2.GetX() >= 13 && paddle_timer <= 15 )
									    		p2.SetPosition(left,0);
									    }
									    // user presses right key
									    if (p2_right == true)
									    {
									    	if (p2.GetX() <= 320 && paddle_timer <= 15)
									    		p2.SetPosition(right,0);
									    }
									    // user presses up key
									    if (p2_up == true)
									    {
									    	if(p2.GetY() >= 160 && paddle_timer <= 15)
									    		p2.SetPosition(0,up);
									    }
									    // user presses down key
									    if (p2_down == true)
									    {
									    	if (p2.GetY() <= 545 && paddle_timer <= 15)
									    		p2.SetPosition(0,down);
									    }
									    
									  

													    

										// send UDP datagram coordinates of client paddle
										sendBuffer = new String(
														Integer.toString(p2.GetX())+","+Integer.toString(p2.GetY()) 
														+ "," + Boolean.toString(p2_powerup_asserted)
														);
									
										// convert string to bytes
										sendUDP = sendBuffer.getBytes();
										
										// create datagram packet and send 
										sendPacket = new DatagramPacket(sendUDP, sendUDP.length, clientSck.getInetAddress(),rcvPacket.getPort() ); 
										cliUDP.send(sendPacket);
									
								
										
								
							} // end else client
							
						}
						catch(Exception e){
							 // System.out.println("Sending error");
							}
					
					
						

						
					       if (server == true)
					       { 
					    	   if (p1_powerup_asserted == true)
							    {
							    	 if (p1_powerup == true)
							    		  p1_randompowerup = powerup.ActivatePowerUp();
							    	  
							    	  p1_powerup = false;
							    	  
							    		if (p1_randompowerup == 0)
										{
							    			p1_lives++;
										}
										
										if (p1_randompowerup == 1)
										{
											p1_fuel += 80;
										}
										
										if (p1_randompowerup == 2)
										{
											p1_inversekeys = true;
										}
										
										if (p1_randompowerup == 3)
										{
											p1.speed = 4;
										}
							    }
					    	   
					    	   if (p2_powerup_asserted == true)
							    {
							    	if (p2_powerup == true)
							    		p2_randompowerup = powerup.ActivatePowerUp();
						    	  
							    	p2_powerup = false;
						    	  
							    	if (p2_randompowerup == 0)
									{
										p2_lives++;
									}
									
									if (p2_randompowerup == 1)
									{
										p2_fuel += 80;
									}
									
									if (p2_randompowerup == 2)
									{
										p2_inversekeys = true;
									}
									
									if (p2_randompowerup == 3)
									{
										p2.speed = 4;
									}
							    }
						    	   // collision paddle1 
							    if (((ball.GetY() >= (p1.GetY() + (Paddle.height + Ball.radius) - 18)) && 
							    		ball.GetY() < (p1.GetY() + (Paddle.height + Ball.radius) - 8)) 
							    		&& (ball.GetX() >= (p1.GetX() - (Paddle.width - 50))
							    				&& (ball.GetX() <= (p1.GetX() + Paddle.width))))
							    {
							    	if (ball.x_speed > 0)	
							    		ball.Move(right,down);
							    	else
									ball.Move(left,down);
								
							    }
							
							    // collision paddle 2
							    if (((ball.GetY() >= (p2.GetY() - (Paddle.height + Ball.radius))) &&
							    		ball.GetY() < (p2.GetY() - (Paddle.height + Ball.radius) + 10)) 
							    		&& (ball.GetX() >= (p2.GetX() - (Paddle.width - 50))
							    				&& (ball.GetX() <= (p2.GetX() + Paddle.width))))
							    {
							    	if (ball.x_speed > 0)	
							    		ball.Move(right,up);
							    	else
							    		ball.Move(left,up);
							    }
						
						
							    // right wall
							    if (ball.GetX() > 390 - Ball.radius)
							    {
							    	if (ball.y_speed > 0)
							    		ball.Move(left,down);
							    	else
							    		ball.Move(left,up);
							    }

							    // left wall
							    if (ball.GetX() < 10)
							    {
							    	if (ball.y_speed > 0)
							    		ball.Move(right,down);
							    	else
							    		ball.Move(right,up);
							    }
					       
							    // goal for p2
							    if (ball.GetY() > 620)
							    {
							    	start_ball_timer = true;	
							    }
							
							    // goal for p1
							    if (ball.GetY() < 0)
							    {
							    	start_ball_timer = true;
							    } 
						
						    
							    // compute goals and reset fuel
							    if (start_ball_timer == true)
							    {
							    	//p2
							    	if (ball.GetY() > 620)
							    	{
							    		if(ball_timer == 0)
							    		{
							    			p2_lives--;
							    			p1_fuel = 80;
							    			p2_fuel = 80;
							    			ball.Replace();
							    			start_ball_timer = false;
							    		}
							    	}
							    	
							    	//p1
							    	else if (ball.GetY() < 0)
							    	{
							    		if(ball_timer == 0)
							    		{
							    			p1_lives--;
							    			p1_fuel = 80;
							    			p2_fuel = 80;
							    			ball.Replace();
							    			start_ball_timer = false;
							    		}
							    	}	    	
							    }
						
							    // moving the ball
							    if (paddle_timer <= 14)
							    {
							    	ball.x_ball += ball.x_speed;
							    	ball.y_ball += ball.y_speed;
							    }
						    
							    // gravity for p1
							    if (p1.GetY() >= 50 && paddle_timer == 4)
							    {
							    	p1.GravityUp();
							    	if (p1_fuel >= 0)
							    		p1_fuel--;
							    }
						
						
							    // fuel for p1
							    if (p1.GetX() <= 50  && p1.GetY() <= 95 
									&& paddle_timer == 4 )
							    {
									if (p1_fuel <= 80)
										p1_fuel++;
							    }
						
						    
							    //fuel p2
							    if (p2.GetX() <= 50  && p2.GetY() >= 505
									&& paddle_timer == 4)
							    {
							    	if (p2_fuel <= 80)
							    		p2_fuel++;
							    }
						
							    if (p1_fuel == 0)
							    {
							    	ball.Disappear(0,-50);
							    }
							
							    if (p2_fuel == 0)
							    {
							    	ball.Disappear(0,700);
							    }
						
							    // reset power up
							    if (powerup_timer == 3000)
							    {
							    	p1.speed = 2;
							    	p2.speed = 2;
							    	p1_inversekeys = false;
							    	p2_inversekeys = false;
							    }
						    

							    // power up
							    if (powerup_appear == true && ((ball.GetY() >= (powerup_y_pos - 10))
							    		&& (ball.GetY() <= (powerup_y_pos + 10))
							    		&& (ball.GetX() >= (powerup_x_pos - 20)
							    				&& (ball.GetX() <= (powerup_x_pos + 45)))))
							    {
							    	if (ball.y_speed > 0)	
							    	{
							    		p1_powerup = powerup.PickRandomPowerUp(powerup_random);
							    		if (ball.x_speed >= 0)
							    			ball.Move(right, up);
							    		else if (ball.x_speed < 0)
							    			ball.Move(left, up);
							    	}
								
							    	else if (ball.y_speed <= 0)
							    	{
							    		p2_powerup = powerup.PickRandomPowerUp(powerup_random);
							    		if (ball.x_speed >= 0)
							    			ball.Move(right, down);
							    		else if (ball.x_speed < 0)
							    			ball.Move(left, down);
							    	}
							    	powerup_appear = false;
							    }
	 
					       } // end if server 

	
					       
					       
					       // game over
							
						    if (p1_lives == 0 || p2_lives == 0)
							{
						    	start = false;
							}

					       
						    //gravity for p2
						    if (p2.GetY() <= 540 && paddle_timer == 4)
						    {
						    	p2.GravityDown();
						    	if (p2_fuel >= 0)
						    		p2_fuel--;
						    }
					       
					       
						    repaint();
						    
						    try 
						    {
						    	Thread.sleep (3);
						    	
						    	if (server == true)
						    	{
						    	paddle_timer++;
						    	if (paddle_timer == 20)
						    		paddle_timer = 0;
						    	powerup_random++;
						    	if (powerup_random == 4)
						    		powerup_random = 0;
						    	powerup_timer++;
						    	if (powerup_timer == 4000)
						    		powerup_timer = 0;
						    
						    	
						    	powerup_x_pos_random++;
						    	if (powerup_x_pos_random == 360)
						    		powerup_x_pos_random = 40;
						    	powerup_y_pos_random++;
						    	if (powerup_y_pos_random == 350)
						    		powerup_y_pos_random = 240;
						    	if (powerup_y_pos_random == 260)
						    		powerup_y_pos_random += 40;
						    	ball_timer++;
						    	if (ball_timer == 300)
						    		ball_timer = 0;
						    	}
						    } 
						    catch (InterruptedException e)
						    {
						    	e.printStackTrace();
						    }

						
					       	
						
						
						
						
						
						
							
							
						}// end if(start == true && pausegame == false)	
							
							
						
						
					break;
		
					default:
					break;
			
					
					
				}; // end switch 
				
				
				
				
			
			 
				
				
				
				
				
			   
		       
			
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		} // while 
		
} // end run 
	

	
	public boolean keyDown (Event e, int key)
	{
	
		// PLAYER 1
	      // user presses 'a' key -> go left
			
	      if (key == 97)
	      {
	    	  if (p1_inversekeys == false)
	    		  p1_left = true;
	    	  else
	    		  p1_right = true;
	    	 
	      }
	      // user presses 'd' key -> go right
	      if (key == 100)
	      {
	    	  if (p1_inversekeys == false)
	    		  p1_right = true;
	    	  else
	    		  p1_left = true;
	      }
	      // user presses 'w' key -> go up
	      if (key == 119)
	      {
	    	  if (p1_inversekeys == false)
	    		  p1_up = true;
	    	  else
	    		  p1_down = true;
	      }
	      // user presses 's' key -> go down
	      if (key == 115)
	      {
	    	  if (p1_inversekeys == false)
	    		  p1_down = true;
	    	  else 
	    		  p1_up = true;
	      }
	    
	      if (key == 32)
	      {
	    	  p1_powerup_asserted = true;
			
	      }
	      
	   // PLAYER 2
	      // user presses left key
	      if (key == Event.LEFT)
	      {
	    	  if (p2_inversekeys == false)
	    		  p2_left = true;
	    	  else
	    		  p2_right = true;
	      }
	      // user presses right key
	      if (key == Event.RIGHT)
	      {
	    	  if (p2_inversekeys == false)
	    		  p2_right = true;
	    	  else
	    		  p2_left = true;
	      }
	      // user presses up key
	      if (key == Event.UP)
	      {
	    	  if (p2_inversekeys == false)
	    		  p2_up = true;
	    	  else
	    		  p2_down = true;
	      }
	      // user presses down key
	      if (key == Event.DOWN)
	      {
	    	  if (p2_inversekeys == false)
	    		  p2_down = true;
	    	  else
	    		  p2_up = true;
	      }
	      
	      if (key == 47)
	      {
	    	  p2_powerup_asserted = true;
	    	 
	      }
	      
	      // Start Game
	      if (key == 10 && start == false)
	      {
	    	  start = true;
	    	  p1_lives = 10;
	    	  p2_lives = 10;
	    	  startofgame = true;
	      }
	      
	      // Pause Game
	      if (key == 27 && start == true && pausegame == false)
	      {
	    	 pausegame = true;
	      }
	      
	      // Resume Game
	      if (key == 96 && start == true && pausegame == true)
	      {
	    	  pausegame = false;
	      }
      return true;
	} 
	
	
	public boolean keyUp(Event evt, int key)
	{
		
		if (evt.id == Event.KEY_ACTION_RELEASE)
	    {
			if (key == 97)
			{
				if (p1_inversekeys == false)
					p1_left = false;
				else
					p1_right = false;
			}
			if (key == 100)
			{
				if (p1_inversekeys == false)
					p1_right = false;
				else
					p1_left = false;
			}
			if (key == 119)
			{
				if (p1_inversekeys == false)
					p1_up = false;
				else
					p1_down = false;
			}
			if (key == 115)
			{
				if (p1_inversekeys == false)
					p1_down = false;
				else
					p1_up = false;
			}
			if (key == Event.LEFT)
			{
				if (p2_inversekeys == false)
					p2_left = false;
				else
					p2_right = false;
			}
			
			if (key == Event.RIGHT)
			{
				if (p2_inversekeys == false)
					p2_right = false;
				else
					p2_left = false;
			}
			if (key == Event.UP)
			{
				if (p2_inversekeys == false)
					p2_up = false;
				else
					p2_down = false;
			}
			if (key == Event.DOWN)
			{
				if (p2_inversekeys == false)
					p2_down = false;
				else
					p2_up = false;
			}
			if (key == 32)
			{
				p1_powerup_asserted = false;
			}
			if (key == 47) 
			{
				p2_powerup_asserted = false;
			}
	    }
	    else
	    {
	    	if (key == 97)
			{
				if (p1_inversekeys == false)
					p1_left = false;
				else
					p1_right = false;
			}
			if (key == 100)
			{
				if (p1_inversekeys == false)
					p1_right = false;
				else
					p1_left = false;
			}
			if (key == 119)
			{
				if (p1_inversekeys == false)
					p1_up = false;
				else
					p1_down = false;
			}
			if (key == 115)
			{
				if (p1_inversekeys == false)
					p1_down = false;
				else
					p1_up = false;
			}
			if (key == Event.LEFT)
			{
				if (p2_inversekeys == false)
					p2_left = false;
				else
					p2_right = false;
			}
			
			if (key == Event.RIGHT)
			{
				if (p2_inversekeys == false)
					p2_right = false;
				else
					p2_left = false;
			}
			if (key == Event.UP)
			{
				if (p2_inversekeys == false)
					p2_up = false;
				else
					p2_down = false;
			}
			if (key == Event.DOWN)
			{
				if (p2_inversekeys == false)
					p2_down = false;
				else
					p2_up = false;
			}
			if (key == 32)
			{
				p1_powerup_asserted = false;
			}
			if (key == 47) 
			{
				p2_powerup_asserted = false;
			}
	    }
	    return true;
	 }

	
	
	// double buffering
	public void update (Graphics g)
	{
	      if (dbImage == null)
	      {
	    	  dbImage = createImage (900, 900);
	    	  dbg = dbImage.getGraphics ();
	      }

	      dbg.setColor (getBackground ());
	      dbg.fillRect (0, 0, 1000, 1000);
	
	      dbg.setColor (getForeground());
	      paint (dbg);

	      g.drawImage (dbImage, 0, 0, this);

	} 

	// paint graphics
	public void paint (Graphics g) 
	{
		
//Effect: Generate graph 
		
		
		Graphics2D starttext = null;
		
		if (start == false)
		{
		/*	
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("Singlep2.bmp"));
			} catch (IOException e) {
				System.out.println("Cannot Find Image");
			}
			g.drawImage(img,0,0,null);
			*/
			
			
			if (state == CONNECTING)
			{
				Graphics2D connect = (Graphics2D) g;
				connect.setColor(Color.GREEN);
				connect.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
				connect.drawString("CONNECTING..", 100, 100);
				
			}
			
			else if (state == DISCONNECTED && instruction == false)
			{
				Graphics2D disconnect = (Graphics2D) g;
				
				disconnect.setColor(Color.RED);
			    disconnect.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
				disconnect.drawString("DISCONNECTED", 100, 100);
			}
			else if(instruction == true)
			{
		
				
			starttext = (Graphics2D) g;
		
			starttext.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
			
			starttext.setColor(Color.ORANGE);
			starttext.drawString("The objective of the game is to use your paddle to ", 15, 20);
			starttext.drawString("hit the ball past the opponent's edge of the field.", 15, 40);
			starttext.drawString("You start with 10 lives.", 15, 70);
			starttext.drawString("Random Power Ups can be obtained at random times", 15, 100);
			starttext.drawString("by bouncing the ball off random blocks that appear.", 15, 120);
			starttext.drawString("Power Ups include: ", 15, 140);
			starttext.drawString("Increase Paddle speed", 90, 160);
			starttext.drawString("Regain Fuel", 90, 180);
			starttext.drawString("Extra Life", 90, 200);
			starttext.drawString("Inverse Keyboard Inputs (Negative Effect)", 90, 220);
			starttext.drawString("Your Paddle decreases in fuel if it is moved towards ", 15, 250);
			starttext.drawString("the middle. You can regain fuel by moving your  ", 15, 270);
			starttext.drawString("paddle towards the -Fueling Station-.", 15, 290);
			starttext.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
			starttext.setColor(Color.RED);
			starttext.drawString("(Top Paddle)", 30, 380);
			starttext.drawString("Server Controls: ", 15, 400);
			starttext.drawString("Go Up:                      W", 10, 430);
			starttext.drawString("Go Down:                 S", 10, 450);
			starttext.drawString("Go Left:                    A", 10, 470);
			starttext.drawString("Go Right:                 D", 10, 490);
			starttext.drawString("Use Power Up:    Space", 10, 510);
			starttext.drawString("(Bottom Paddle)", 235, 380);
			starttext.drawString("Client Controls: ", 220, 400);
			starttext.drawString("Go Up:            Up Arrow", 215, 430);
			starttext.drawString("Go Down:     Down Arrow", 215, 450);
			starttext.drawString("Go Left:         Left Arrow", 215, 470);
			starttext.drawString("Go Right:     Right Arrow", 215, 490);
			starttext.drawString("Use Power Up:     /", 215, 510);
			starttext.drawString("Pause Game:        ESC", 100, 530);
			starttext.drawString("Resume Game:        ~", 100, 550);
			starttext.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
			starttext.setColor(Color.WHITE);
//			starttext.drawString("******** HIT ENTER TO START *******", 8, 580);
			
			
			}
			
			
			if (p1_lives == 0)
			{
				starttext = (Graphics2D) g;
				starttext.setColor(Color.WHITE);
				starttext.drawString("GAME OVER, PLAYER 2 WINS!", 50, 330);
			}
			else if (p2_lives == 0)
			{
				starttext = (Graphics2D) g;
				starttext.setColor(Color.WHITE);
				starttext.drawString("GAME OVER, PLAYER 1 WINS!", 50, 330);
			}
			
			
	
		}

		
		else if (start == true )
		{
			
			
			
		
		Graphics2D fueling_station = (Graphics2D) g;
		fueling_station.setColor(Color.darkGray);
		fueling_station.fillOval(0, 20, 80, 50); 
		fueling_station.fillOval(0, 510, 80, 50);
		
		
		Graphics2D texts = (Graphics2D) g;
		texts.setFont(new Font(Font.SERIF, Font.BOLD, 14));
		texts.setColor(Color.RED);
		texts.drawString("Lives Remaining: ", 10, 18);
		texts.drawString("Lives Remaining: ", 10, 575);
		texts.drawString("POWER UP: ", 190, 18);
		texts.drawString("POWER UP: ", 190, 575);
		texts.drawString("Fuel Bar", 330, 15);
		texts.drawString("Fuel Bar", 330, 575);
		texts.setFont(new Font(Font.SERIF, Font.BOLD, 12));
		texts.setColor(Color.ORANGE);
		texts.drawString("FUELING", 14, 40);
		texts.drawString("STATION", 14, 60);
		texts.drawString("FUELING", 14, 530);
		texts.drawString("STATION", 14, 550);
		
		Graphics2D fuelbar = (Graphics2D) g;
		fuelbar.setColor(Color.gray);
		fuelbar.fillRect(380, 20, 8, 80); 
		fuelbar.fillRect(380, 470, 8, 80); 
		
		Graphics2D basefuelbar = (Graphics2D) g;
		basefuelbar.setColor(Color.BLACK);
		basefuelbar.fillRect(380, 20, 8, 80 - p1_fuel); 
		basefuelbar.fillRect(380, 470, 8, 80 - p2_fuel); 
		
		
		
		String p1g = Integer.toString(p1_lives);
		
		Graphics2D p1_goals = (Graphics2D) g;
		p1_goals.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		p1_goals.setColor(Color.YELLOW);
		p1_goals.drawString(p1g, 140, 18);
		
		String p2g = Integer.toString(p2_lives);
		
		Graphics2D p2_goals = (Graphics2D) g;
		p2_goals.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		p2_goals.setColor(Color.YELLOW);
		p2_goals.drawString(p2g, 140, 575);

		if (p1_powerup == true)
		{
			Graphics2D p1powerup = (Graphics2D) g;
			p1powerup.setFont(new Font(Font.SERIF, Font.BOLD, 14));
			p1powerup.setColor(Color.YELLOW);
			p1powerup.drawString("YES (HIT SPACE TO ACTIVATE)", 155, 36);
		}
		
		if (p2_powerup == true)
		{
			Graphics2D p2powerup = (Graphics2D) g;
			p2powerup.setFont(new Font(Font.SERIF, Font.BOLD, 14));
			p2powerup.setColor(Color.YELLOW);
			p2powerup.drawString("YES (HIT / TO ACTIVATE)", 155, 595);
		}
		
		Graphics2D ball_img = (Graphics2D) g;
		ball_img.setColor(Color.BLUE);
		ball_img.fillOval (ball.GetX() , ball.GetY() , 2 * Ball.radius, 2 * Ball.radius);  
		
		Graphics2D p1_paddle = (Graphics2D) g;
		p1_paddle.setColor(Color.green);
		p1_paddle.fillRect(p1.GetX(), p1.GetY(), Paddle.width, Paddle.height); 
		p1_paddle.setColor(Color.GRAY);
		p1_paddle.fillRect(p1.GetX()+10, p1.GetY()+10, 50, 3);
		
		if (p1_fuel > 20 && p1_fuel <= 40)
		{
			p1_paddle.setColor(Color.ORANGE);
			p1_paddle.fillRect(p1.GetX(), p1.GetY(), Paddle.width, Paddle.height);
		}
		
		if (p1_fuel <= 20)
		{
			p1_paddle.setColor(Color.RED);
			p1_paddle.fillRect(p1.GetX(), p1.GetY(), Paddle.width, Paddle.height);
		}
		
		if (p1.GetX() <= 50  && p1.GetY() <= 95)
		{
			p1_paddle.setColor(Color.BLUE);
			p1_paddle.fillRect(p1.GetX(), p1.GetY(), Paddle.width, Paddle.height);
		}
		
		if (p1_down == true || p1.GetYspeed() > 0)
		{
			p1_paddle.setColor(Color.ORANGE);
			
			if (p1_fuel > 40)
			{
				p1_paddle.fillArc(p1.GetX(), p1.GetY()-20, 10, 40, 0, 80);
				p1_paddle.fillArc(p1.GetX()+27, p1.GetY()-20, 10, 40, 0, 80);
				p1_paddle.fillArc(p1.GetX()+54, p1.GetY()-20, 10, 40, 0, 80);
			}
			else if (p1_fuel > 20 && p1_fuel <=40)
			{
				p1_paddle.fillArc(p1.GetX(), p1.GetY()-20, 10, 40, 0, 30);
				p1_paddle.fillArc(p1.GetX()+27, p1.GetY()-20, 10, 40, 0, 30);
				p1_paddle.fillArc(p1.GetX()+54, p1.GetY()-20, 10, 40, 0, 30);
			}
			else if (p1_fuel <= 20)
			{
				p1_paddle.fillArc(p1.GetX(), p1.GetY()-20, 10, 40, 0, 15);
				p1_paddle.fillArc(p1.GetX()+27, p1.GetY()-20, 10, 40, 0, 15);
				p1_paddle.fillArc(p1.GetX()+54, p1.GetY()-20, 10, 40, 0, 15);
			}
		}
	
		Graphics2D p2_paddle = (Graphics2D) g;
		p2_paddle.setColor(Color.green);
		p2_paddle.fillRect(p2.GetX(), p2.GetY(), Paddle.width, Paddle.height); 
		p2_paddle.setColor(Color.GRAY);
		p2_paddle.fillRect(p2.GetX()+10, p2.GetY()-3, 50, 3);
		
		if (p2_fuel > 20 && p2_fuel <= 40)
		{
			p2_paddle.setColor(Color.ORANGE);
			p2_paddle.fillRect(p2.GetX(), p2.GetY(), Paddle.width, Paddle.height); 
		}
		
		if (p2_fuel <= 20)
		{
			p2_paddle.setColor(Color.RED);
			p2_paddle.fillRect(p2.GetX(), p2.GetY(), Paddle.width, Paddle.height); 
		}
		
		if (p2.GetX() <= 50  && p2.GetY() >= 505)
		{
			p2_paddle.setColor(Color.BLUE);
			p2_paddle.fillRect(p2.GetX(), p2.GetY(), Paddle.width, Paddle.height); 
		}
		
		if (p2_up == true || p2.GetYspeed() < 0)
		{
			p2_paddle.setColor(Color.ORANGE);
			
			if (p2_fuel > 40)
			{
				p2_paddle.fillArc(p2.GetX(), p2.GetY()-10, 10, 40, 0, -80);
				p2_paddle.fillArc(p2.GetX()+27, p2.GetY()-10, 10, 40, 0, -80);
				p2_paddle.fillArc(p2.GetX()+54, p2.GetY()-10, 10, 40, 0, -80);
			}
			else if (p2_fuel > 20 && p2_fuel <=40)
			{
				p2_paddle.fillArc(p2.GetX(), p2.GetY()-10, 10, 40, 0, -30);
				p2_paddle.fillArc(p2.GetX()+27, p2.GetY()-10, 10, 40, 0, -30);
				p2_paddle.fillArc(p2.GetX()+54, p2.GetY()-10, 10, 40, 0, -30);
			}
			else if (p2_fuel <= 20)
			{
				p2_paddle.fillArc(p2.GetX(), p2.GetY()-10, 10, 40, 0, -15);
				p2_paddle.fillArc(p2.GetX()+27, p2.GetY()-10, 10, 40, 0, -15);
				p2_paddle.fillArc(p2.GetX()+54, p2.GetY()-10, 10, 40, 0, -15);
			}
		}
		
		if (powerup_timer == 3000 && powerup_appear == false)
		{
			powerup_x_pos = powerup_x_pos_random;
			powerup_y_pos = powerup_y_pos_random;
			powerup_appear = true;
		
		}
		
		if (powerup_appear == true)
		{
			Graphics2D powerup = (Graphics2D) g;
			powerup.setColor(Color.WHITE);
			powerup.fillRect(powerup_x_pos, powerup_y_pos, PowerUp.width, PowerUp.height); 
		}
			
		}
}
	 

	public static void main(String[] args) 
	{
		
		
	   
		
	}
}
