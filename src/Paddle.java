

public class Paddle 
{
	//Overview: Specifies the height and width of a paddle and 
	//			manipulates the coordinates of the paddle, along with several
	//			other methods that can be performed on the paddle
	
	public int x = 0;
	public int y = 1;
	public int speed = 2;
	public Coord paddlepos = new Coord(2);
	private int x_paddle = paddlepos.getX();
	private int y_paddle = paddlepos.getY();
	public static int height = 10;
	public static int width = 70;
	private int y_speed = 0;
	private int x_speed = 0;


	public Paddle (int a, int b)
	{
		// Constructor: Creates a new paddle object and specifies initial position
		
		this.paddlepos.setX(a);
		this.paddlepos.setY(b);
	}

	public int GetX()
	{
		// Effects: Gets the x coordinate of this
		
		return this.x_paddle;
	}
	
	public int GetY()
	{
		// Effects: Gets the y coordinate of this
		
		return this.y_paddle;
	}
	
	public int GetYspeed()
	{
		// Effects: Gets the vertical speed of this
		
		return (this.y_speed*this.speed);
	}
	
	public int GetXspeed()
	{
		// Effects: Gets the horizontal speed of this
		
		return (this.x_speed*this.speed);
	}
	
	public void SetHardPosition(int a, int b)
	{
		// Effects: Sets the position of this in a specific place
		
		this.x_paddle = a;
		this.y_paddle = b;
	}
	
	
	public void SetMultiPosition(int a, int b)
	{
		// Effects: Sets the position of this in a specific place
		//  		(same as above method, just used for differentiation)
		
		this.x_paddle = a;
		this.y_paddle = b;
		
	}
	
	public void SetPosition(int a, int b)
	{
		// Effects: Moves this in a specific direction and speed
		
		this.x_speed = a;
		this.y_speed = b;
		this.x_paddle += (this.speed * this.x_speed);
		this.y_paddle += (this.speed * this.y_speed);
	}

	public void GravityUp()
	{
		// Effects: Moves this in the up direction if this is 
		//			less than a certain y coordinate
		
		if (this.y_paddle <= 500)
			this.y_paddle -= 3;	
	}

	public void GravityDown()
	{
		// Effects: Moves this in the down direction if this is 
		//			greater than a certain y coordinate
		
		if (this.y_paddle >= 100)
			this.y_paddle += 3;
	}
	
	public void SetSpeed(int a)
	{
		// Effects: Sets the moving speed of this
		
		this.speed = a;
	}
	

}