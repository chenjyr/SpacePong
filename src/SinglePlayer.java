import java.awt.*;
import java.applet.*; 




@SuppressWarnings("serial")
public class SinglePlayer extends Applet implements Runnable{


	private Image dbImage;
	private Graphics dbg; 
	private Paddle bot = new Paddle(50, 570);
	private Paddle player = new Paddle(50, 0);
	private Ball ball = new Ball();
	private PowerUp powerup = new PowerUp();
	private int paddle_timer = 0;
	private int powerup_random = 0;
	private int powerup_timer = 0;
	private int powerup_x_pos = 0;
	private int powerup_x_pos_random = 200;
	private int powerup_y_pos = 0;
	private int powerup_y_pos_random = 220;
	private boolean bot_powerup = false;
	private int bot_randompowerup = 0;
	private boolean player_powerup = false;
	private int player_randompowerup = 0;
	private boolean powerup_appear = false;
	private boolean player_up = false;
	private boolean player_left = false;
	private boolean player_right = false;
	private boolean player_down = false;
	private int right = 1;
	private int left = -1;
	private int up = -1;
	private int down = 1;
	private boolean start_ball_timer = false;
	private int ball_timer = 0;
	private int bot_lives = 10;
	private int player_lives = 10;
	private int bot_fuel = 80;
	private int player_fuel = 80;
	private boolean start = false;
	@SuppressWarnings("unused")
	private boolean bot_inversekeys = false;
	private boolean player_inversekeys = false;
	private volatile Thread initialize_game;
	private boolean Hard_mode = false;
	private boolean Normal_mode = false;
	private boolean Easy_mode = false;
	private boolean startofgame = false;
	private boolean pausegame = false;

	
	public SinglePlayer()
	{
		
	}
	
	 public void stop() {
	    	
	        initialize_game = null;
	        bot.SetHardPosition(100,40);
			player.SetHardPosition(100,550);
			ball.Replace();
			player_lives = 10;
			bot_lives = 10;
			bot_fuel = 80;
			player_fuel = 80;
			player_left = false;
			player_right = false;
			player_down = false;
			player_up = false;
			powerup_appear = false;
			Hard_mode = false;
			Normal_mode = false;
			Easy_mode = false;
			start = false;
	    }
	
	public void init()
	{
		setBackground (Color.black);
		bot.SetHardPosition(100,40);
		player.SetHardPosition(100,550);
		if (ball_timer == 200)
			ball.Replace();
		start();
	}

	public void start ()
	{
			Thread thread = new Thread (this);
			thread.start ();	
	}
	
	
	public void run() {
		
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
	
		Thread thisThread = Thread.currentThread();
			initialize_game = thisThread;
	
		while (initialize_game != null)
		{	
			if (startofgame == true)
			{
				ball.Replace();
				startofgame = false;
			}
			
			if (start == true && pausegame == false)
			{
			
				AI();

				if (player_left == true)
				{
					if (player.GetX() >= 13 && paddle_timer <= 15 )
						player.SetPosition(left,0);
				}
		     
				// user presses right key
				if (player_right == true)
				{
					if (player.GetX() <= 320 && paddle_timer <= 15)
						player.SetPosition(right,0);
				}
		    
				// user presses up key
				if (player_up == true)
				{
					if(player.GetY() >= 160 && paddle_timer <= 15)
						player.SetPosition(0,up);
				}
				
				// user presses down key
				if (player_down == true)
				{
					if (player.GetY() <= 545 && paddle_timer <= 15)
						player.SetPosition(0,down);
				}
		       
				if (((ball.GetY() >= (bot.GetY() + (Paddle.height + Ball.radius) - 18)) && 
						ball.GetY() < (bot.GetY() + (Paddle.height + Ball.radius) - 8)) 
						&& (ball.GetX() >= (bot.GetX() - (Paddle.width - 50))
								&& (ball.GetX() <= (bot.GetX() + Paddle.width))))
				{
					if (ball.x_speed > 0)	
						ball.Move(right,down);
					else
						ball.Move(left,down);
				}
					
				if (((ball.GetY() >= (player.GetY() - (Paddle.height + Ball.radius))) &&
						ball.GetY() < (player.GetY() - (Paddle.height + Ball.radius) + 10)) 
						&& (ball.GetX() >= (player.GetX() - (Paddle.width - 50))
								&& (ball.GetX() <= (player.GetX() + Paddle.width))))
				{
					if (ball.x_speed > 0)	
						ball.Move(right,up);
					else
						ball.Move(left,up);
				}
			
				if (powerup_appear == true && ((ball.GetY() >= (powerup_y_pos - 10))
						&& (ball.GetY() <= (powerup_y_pos + 10))
						&& (ball.GetX() >= (powerup_x_pos - 20)
								&& (ball.GetX() <= (powerup_x_pos + 45)))))
				{
					if (ball.y_speed > 0)	
					{
						bot_powerup = powerup.PickRandomPowerUp(powerup_random);
						if (ball.x_speed >= 0)
						ball.Move(right, up);
						else if (ball.x_speed < 0)
							ball.Move(left, up);
					}
				
					else if (ball.y_speed <= 0)
					{
						player_powerup = powerup.PickRandomPowerUp(powerup_random);
						if (ball.x_speed >= 0)
							ball.Move(right, down);
						else if (ball.x_speed < 0)
							ball.Move(left, down);
					}
					powerup_appear = false;
				}
			
				if (ball.GetX() > 390 - Ball.radius)
				{
					if (ball.y_speed > 0)
						ball.Move(left,down);
					else
						ball.Move(left,up);
				}

				if (ball.GetX() < 10)
				{
					if (ball.y_speed > 0)
						ball.Move(right,down);
					else
						ball.Move(right,up);
				} 
		
				if (ball.GetY() > 620)
				{
					start_ball_timer = true;	
				}
			
				if (ball.GetY() < 0)
				{
					start_ball_timer = true;
				} 
			
				if (start_ball_timer == true)
				{
					if (ball.GetY() > 620)
					{
						if(ball_timer == 0)
						{
							player_lives--;
							bot_fuel = 80;
							player_fuel = 80;
							ball.Replace();
							start_ball_timer = false;
						}
					}
					
					else if (ball.GetY() < 0)
					{
						if(ball_timer == 0)
						{
							bot_lives--;
							bot_fuel = 80;
							player_fuel = 80;
							ball.Replace();
							start_ball_timer = false;
						}
					}			
				}
				
				if (paddle_timer <= 14)
				{
					ball.x_ball += ball.x_speed;
					ball.y_ball += ball.y_speed;
				}
		
				if (bot.GetY() >= 50 && paddle_timer == 4)
				{
					bot.GravityUp();
					if (bot_fuel >= 0)
						bot_fuel--;
				}
			
				if (player.GetY() <= 540 && paddle_timer == 4)
				{
					player.GravityDown();
					if (player_fuel >= 0)
						player_fuel--;
				}
			
				if (bot.GetX() <= 50  && bot.GetY() <= 95 
					&& paddle_timer == 4 )
				{
					if (bot_fuel <= 80)
					bot_fuel++;
				}

				if (player.GetX() <= 50  && player.GetY() >= 505
					&& paddle_timer == 4)
				{
					if (player_fuel <= 80)
					player_fuel++;
				}
			
				if (bot_fuel == 0)
					ball.Disappear(0,-50);
				
			
				if (player_fuel == 0)
					ball.Disappear(0,700);
			
				if (powerup_timer == 3000)
				{
					bot.speed = 2;
					player.speed = 2;
					bot_inversekeys = false;
					player_inversekeys = false;
				}
		
				if (bot_lives == 0 || player_lives == 0)
					start = false;
			
				repaint();
			
				try 
				{
					Thread.sleep (4);
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
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
		
	}
	
	public void AI()
	{
		if (bot_powerup == true)
		{
			bot_randompowerup = powerup.ActivatePowerUp();
			bot_powerup = false;
			
			if (bot_randompowerup == 0)
			{
				bot_lives++;
			}
			
			if (bot_randompowerup == 1)
			{
				bot_fuel += 80;
			}
			
			if (bot_randompowerup == 2)
			{
				bot_inversekeys = true;
			}
			
			if (bot_randompowerup == 3)
			{
				bot.speed = 4;
			}
			
		}
		
		if (Hard_mode == true)
		{
			if (ball.GetX() < bot.GetX())
			{
				if (bot.GetX() >= 13 && ball_timer <= 260 && paddle_timer <= 10)
					bot.SetPosition(left,0);
			}
			
			if (ball.GetX() >= bot.GetX())
			{
				if (bot.GetX() <= 320 && ball_timer <= 260 && paddle_timer <= 10)
					bot.SetPosition(right,0);
			}
		
			if (bot_fuel <= 40)
			{
				if(bot.GetY() >= 45 && ball_timer <= 260 && paddle_timer <= 5)
					bot.SetPosition(0,up);
				if (ball.GetY() >= 200)
				{
					if (bot.GetX() >= 13)
						bot.SetPosition(left,0);
				}
			}
		 
			if ((ball.GetY() <= 280 && ball.GetX() >= 100 && ball.GetX() <= 200 )|| bot_fuel <= 40)
			{
				if(bot.GetY() >= 45 && ball_timer <= 260 && paddle_timer <= 5)
					bot.SetPosition(0,up);
			}
	  
			if (ball.GetY() >= 380 )
			{
				if (bot.GetY() <= 340 &&  ball_timer <= 260 && paddle_timer <= 5)
					bot.SetPosition(0,down);
			}

		}
		
		else if (Normal_mode == true)
		{
			if (ball.GetX() < bot.GetX())
			{
				if (bot.GetX() >= 13 && ball_timer <= 250 && paddle_timer <= 10)
					bot.SetPosition(left,0);
			}
			
			if (ball.GetX() >= bot.GetX())
			{
				if (bot.GetX() <= 320 && ball_timer <= 250 && paddle_timer <= 10)
					bot.SetPosition(right,0);
			}
		
			if (bot_fuel <= 40)
			{
				if(bot.GetY() >= 45 && ball_timer <= 250 && paddle_timer <= 5)
					bot.SetPosition(0,up);
				if (ball.GetY() >= 200)
				{
					if (bot.GetX() >= 13)
						bot.SetPosition(left,0);
				}
			}
		 
			if ((ball.GetY() <= 280 && ball.GetX() >= 100 && ball.GetX() <= 200 )|| bot_fuel <= 40)
			{
				if(bot.GetY() >= 45 && ball_timer <= 250 && paddle_timer <= 5)
					bot.SetPosition(0,up);
			}
	  
			if (ball.GetY() >= 380 )
			{
				if (bot.GetY() <= 340 &&  ball_timer <= 250 && paddle_timer <= 5)
					bot.SetPosition(0,down);
			}
		}
		
		else if (Easy_mode == true)
		{
			if (ball.GetX() < bot.GetX())
			{
				if (bot.GetX() >= 13 && ball_timer <= 240 && paddle_timer <= 9)
					bot.SetPosition(left,0);
			}
			
			if (ball.GetX() >= bot.GetX())
			{
				if (bot.GetX() <= 320 && ball_timer <= 240 && paddle_timer <= 9)
					bot.SetPosition(right,0);
			}
		
			if (bot_fuel <= 40)
			{
				if(bot.GetY() >= 45 && ball_timer <= 240 && paddle_timer <= 4)
					bot.SetPosition(0,-1);
				if (ball.GetY() >= 200)
				{
					if (bot.GetX() >= 13)
						bot.SetPosition(left,0);
				}
			}
		 
			if ((ball.GetY() <= 280 && ball.GetX() >= 100 && ball.GetX() <= 200 )|| bot_fuel <= 40)
			{
				if(bot.GetY() >= 45 && ball_timer <= 240 && paddle_timer <= 4)
					bot.SetPosition(0,up);
			}
	  
			if (ball.GetY() >= 380 )
			{
				if (bot.GetY() <= 340 &&  ball_timer <= 240 && paddle_timer <= 4)
					bot.SetPosition(0,down);
			}
		}
		
	}
	
	public boolean keyDown (Event e, int key)
	{
	
      
   // PLAYER 2
      // user presses left key
      if (key == Event.LEFT)
      {
    	  if (player_inversekeys == false)
    		  player_left = true;
    	  else
    		  player_right = true;
      }
      // user presses right key
      if (key == Event.RIGHT)
      {
    	  if (player_inversekeys == false)
    		  player_right = true;
    	  else
    		  player_left = true;
      }
      // user presses up key
      if (key == Event.UP)
      {
    	  if (player_inversekeys == false)
    		  player_up = true;
    	  else
    		  player_down = true;
      }
      // user presses down key
      if (key == Event.DOWN)
      {
    	  if (player_inversekeys == false)
    		  player_down = true;
    	  else
    		  player_up = true;
      }
      
      if (key == 47)
      {
    	  if (player_powerup == true)
    		  player_randompowerup = powerup.ActivatePowerUp();
    	  
    	  player_powerup = false;
    	  
    	  if (player_randompowerup == 0)
			{
				player_lives++;
			}
			
			if (player_randompowerup == 1)
			{
				player_fuel += 80;
			}
			
			if (player_randompowerup == 2)
			{
				player_inversekeys = true;
			}
			
			if (player_randompowerup == 3)
			{
				player.speed = 4;
			}
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
      
      // Start Easy Mode
      if (key == 101 && start == false)
      {
    	  Easy_mode = true;
    	  Normal_mode = false;
    	  Hard_mode = false;
    	  start = true;
    	  bot_lives = 10;
    	  player_lives = 10;
    	  startofgame = true;
      }
      
      // Start Normal Mode
      if (key == 110 && start == false) 
      {
    	  Easy_mode = false;
    	  Normal_mode = true;
    	  Hard_mode = false;
    	  start = true;
    	  bot_lives = 10;
    	  player_lives = 10;
    	  startofgame = true;
      }
      
      // Start Hard Mode
      if (key == 104 && start == false)
      {
    	  Easy_mode = false;
    	  Normal_mode = false;
    	  Hard_mode = true;
    	  start = true;
    	  bot_lives = 10;
    	  player_lives = 10;
    	  startofgame = true;
      }
      

      
      return true;
	} 
	
	
	public boolean keyUp(Event evt, int key)
	{
		
		if (evt.id == Event.KEY_ACTION_RELEASE)
	    {
			if (key == Event.LEFT)
			{
				if (player_inversekeys == false)
					player_left = false;
				else
					player_right = false;
			}
			
			if (key == Event.RIGHT)
			{
				if (player_inversekeys == false)
					player_right = false;
				else
					player_left = false;
			}
			if (key == Event.UP)
			{
				if (player_inversekeys == false)
					player_up = false;
				else
					player_down = false;
			}
			if (key == Event.DOWN)
			{
				if (player_inversekeys == false)
					player_down = false;
				else
					player_up = false;
			}
	    }
	    else
	    {
	    	if (key == Event.LEFT)
			{
				if (player_inversekeys == false)
					player_left = false;
				else
					player_right = false;
			}
			
			if (key == Event.RIGHT)
			{
				if (player_inversekeys == false)
					player_right = false;
				else
					player_left = false;
			}
			if (key == Event.UP)
			{
				if (player_inversekeys == false)
					player_up = false;
				else
					player_down = false;
			}
			if (key == Event.DOWN)
			{
				if (player_inversekeys == false)
					player_down = false;
				else
					player_up = false;
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
		
		
		if (start == false)
		{
		/*	
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("SinglePlayer.bmp"));
			} catch (IOException e) {
				System.out.println("Cannot Find Image");
			}
			g.drawImage(img,0,0,null);
			*/
			Graphics2D starttext = (Graphics2D) g;
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
			starttext.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
			starttext.setColor(Color.RED);
			starttext.drawString("Player (bottom paddle) Controls: ", 10, 390);
			starttext.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
			starttext.drawString("Go Up:          Up Arrow", 20, 420);
			starttext.drawString("Go Down:     Down Arrow", 20, 440);
			starttext.drawString("Go Left:        Left Arrow", 20, 460);
			starttext.drawString("Go Right:     Right Arrow", 20, 480);
			starttext.drawString("Use Power Up:     /", 20, 500);
			starttext.drawString("Pause Game:     ESC", 20, 520);
			starttext.drawString("Resume Game:    ~", 20, 540);
			starttext.setColor(Color.YELLOW);
			starttext.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
			starttext.drawString("Press:", 240, 420);
			starttext.drawString("e to play", 255, 440);
			starttext.drawString("easy mode", 250, 455);
			starttext.drawString("n to play", 255, 475);
			starttext.drawString("normal mode", 250, 490);
			starttext.drawString("h to play", 255, 510);
			starttext.drawString("hard mode", 250, 525);
			starttext.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
			starttext.setColor(Color.WHITE);
			starttext.drawString("******* HIT e,n, or h TO START *******", 8, 580);
			
			
			
			if (bot_lives == 0)
			{
				starttext.setColor(Color.WHITE);
				starttext.drawString("GAME OVER, YOU WIN!", 50, 330);
			}
			else if (player_lives == 0)
			{
				starttext.setColor(Color.WHITE);
				starttext.drawString("GAME OVER, YOU LOSE!", 50, 330);
			}
	
		}
		
		else if (start == true)
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
		basefuelbar.fillRect(380, 20, 8, 80 - bot_fuel); 
		basefuelbar.fillRect(380, 470, 8, 80 - player_fuel); 
		
		
		
		String botg = Integer.toString(bot_lives);
		
		Graphics2D bot_goals = (Graphics2D) g;
		bot_goals.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		bot_goals.setColor(Color.YELLOW);
		bot_goals.drawString(botg, 140, 18);
		
		String playerg = Integer.toString(player_lives);
		
		Graphics2D player_goals = (Graphics2D) g;
		player_goals.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		player_goals.setColor(Color.YELLOW);
		player_goals.drawString(playerg, 140, 575);

		if (bot_powerup == true)
		{
			Graphics2D botpowerup = (Graphics2D) g;
			botpowerup.setFont(new Font(Font.SERIF, Font.BOLD, 14));
			botpowerup.setColor(Color.YELLOW);
			botpowerup.drawString("YES (HIT SPACE TO ACTIVATE)", 155, 36);
		}
		
		if (player_powerup == true)
		{
			Graphics2D playerpowerup = (Graphics2D) g;
			playerpowerup.setFont(new Font(Font.SERIF, Font.BOLD, 14));
			playerpowerup.setColor(Color.YELLOW);
			playerpowerup.drawString("YES (HIT / TO ACTIVATE)", 155, 595);
		}
		
		Graphics2D ball_img = (Graphics2D) g;
		ball_img.setColor(Color.BLUE);
		ball_img.fillOval (ball.GetX() , ball.GetY() , 2 * Ball.radius, 2 * Ball.radius);  
		
		Graphics2D bot_paddle = (Graphics2D) g;
		bot_paddle.setColor(Color.green);
		bot_paddle.fillRect(bot.GetX(), bot.GetY(), Paddle.width, Paddle.height);
		bot_paddle.setColor(Color.GRAY);
		bot_paddle.fillRect(bot.GetX()+10, bot.GetY()+10, 50, 3);
	
		if (bot_fuel > 20 && bot_fuel <= 40)
		{
			bot_paddle.setColor(Color.ORANGE);
			bot_paddle.fillRect(bot.GetX(), bot.GetY(), Paddle.width, Paddle.height);
		}
		
		if (bot_fuel <= 20)
		{
			bot_paddle.setColor(Color.RED);
			bot_paddle.fillRect(bot.GetX(), bot.GetY(), Paddle.width, Paddle.height);
		}
		
		if (bot.GetX() <= 50  && bot.GetY() <= 95)
		{
			bot_paddle.setColor(Color.BLUE);
			bot_paddle.fillRect(bot.GetX(), bot.GetY(), Paddle.width, Paddle.height);
		}
		
		if (bot.GetYspeed() > 0)
		{
			bot_paddle.setColor(Color.ORANGE);
			
			if (bot_fuel > 40)
			{
				bot_paddle.fillArc(bot.GetX(), bot.GetY()-20, 10, 40, 0, 80);
				bot_paddle.fillArc(bot.GetX()+27, bot.GetY()-20, 10, 40, 0, 80);
				bot_paddle.fillArc(bot.GetX()+54, bot.GetY()-20, 10, 40, 0, 80);
			}
			else if (bot_fuel > 20 && bot_fuel <= 40)
			{
				bot_paddle.fillArc(bot.GetX(), bot.GetY()-20, 10, 40, 0, 30);
				bot_paddle.fillArc(bot.GetX()+27, bot.GetY()-20, 10, 40, 0, 30);
				bot_paddle.fillArc(bot.GetX()+54, bot.GetY()-20, 10, 40, 0, 30);
			}
			else if (bot_fuel <= 20)
			{
				bot_paddle.fillArc(bot.GetX(), bot.GetY()-20, 10, 40, 0, 15);
				bot_paddle.fillArc(bot.GetX()+27, bot.GetY()-20, 10, 40, 0, 15);
				bot_paddle.fillArc(bot.GetX()+54, bot.GetY()-20, 10, 40, 0, 15);
				
			}
		}
		
		Graphics2D player_paddle = (Graphics2D) g;
		player_paddle.setColor(Color.green);
		player_paddle.fillRect(player.GetX(), player.GetY(), Paddle.width, Paddle.height); 
		player_paddle.setColor(Color.GRAY);
		player_paddle.fillRect(player.GetX()+10, player.GetY()-3, 50, 3);
		
		if (player_fuel > 20 && player_fuel <= 40)
		{
			player_paddle.setColor(Color.ORANGE);
			player_paddle.fillRect(player.GetX(), player.GetY(), Paddle.width, Paddle.height); 
		}
		
		if (player_fuel <= 20)
		{
			player_paddle.setColor(Color.RED);
			player_paddle.fillRect(player.GetX(), player.GetY(), Paddle.width, Paddle.height); 
		}
		
		if (player.GetX() <= 50  && player.GetY() >= 505)
		{
			player_paddle.setColor(Color.BLUE);
			player_paddle.fillRect(player.GetX(), player.GetY(), Paddle.width, Paddle.height); 
		}
		
		if (player_up == true)
		{
			player_paddle.setColor(Color.ORANGE);
			
			if (player_fuel > 40)
			{
				player_paddle.fillArc(player.GetX(), player.GetY()-10, 10, 40, 0, -80);
				player_paddle.fillArc(player.GetX()+27, player.GetY()-10, 10, 40, 0, -80);
				player_paddle.fillArc(player.GetX()+54, player.GetY()-10, 10, 40, 0, -80);
			}
			else if (player_fuel > 20 && player_fuel <= 40)
			{
				player_paddle.fillArc(player.GetX(), player.GetY()-10, 10, 40, 0, -30);
				player_paddle.fillArc(player.GetX()+27, player.GetY()-10, 10, 40, 0, -30);
				player_paddle.fillArc(player.GetX()+54, player.GetY()-10, 10, 40, 0, -30);
				
			}
			else if (player_fuel <= 20)
			{
				player_paddle.fillArc(player.GetX(), player.GetY()-10, 10, 40, 0, -15);
				player_paddle.fillArc(player.GetX()+27, player.GetY()-10, 10, 40, 0, -15);
				player_paddle.fillArc(player.GetX()+54, player.GetY()-10, 10, 40, 0, -15);
				
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
