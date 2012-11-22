
public class Ball
{
	//Overview: Specifies the radius of a ball and manipulates the coordinates
	//			of the ball
	
	static int x = 0;
	static int y = 1;
	static Coord ballpos = new Coord(2);
	public int x_ball = ballpos.getX();
	public int y_ball = ballpos.getY();
	static int radius = 10;;
	private int temp_x_speed = 0;
	private int temp_y_speed = 0;
	public int x_speed = 1;
	public int y_speed = 1;
	public int speed = 1;

	public Ball()
	{
		// Constructor: Creates a new ball object and specifies initial position
		
		ballpos.setX(100);
		ballpos.setY(100);
	}
	

	public int GetX()
	{
		// Effects: Gets the current x coordinate of this
		
		return x_ball;
	}
	
	public int GetY()
	{
		// Effects: Gets the current y coordinate of this
		
		return y_ball;
	}
	
	public void SetX(int a)
	{
		// Effects: Sets the x coordinate of this
		
		this.x_ball = a;
	}
	
	public void SetY(int a)
	{
		// Effects: Sets the y coordinate of this
		
		this.y_ball = a;
	}
	public int GetXspeed()
	{
		// Effects: Gets the horizontal speed of this
		
		return temp_x_speed;
	}
	
	public int GetYspeed()
	{
		// Effects: Gets the vertical speed of this
		
		return temp_y_speed;
	}
	
	public void Move(int a, int b)
	{
		// Effects: Sets the speed and direction of this
		
		temp_x_speed = a;
		temp_y_speed = b;
		this.x_speed = this.speed * a;
		this.y_speed = this.speed * b;
	}
	
	public void Still()
	{
		// Effects: Brings this to a stand still
		
		Move(0,0);
	}
	
	public void Replace()
	{
		// Effects: Replaces this in the middle of the screen and
		//			moves this in the speed and direction it was previously in

		if (temp_x_speed > 0)
			if (temp_y_speed > 0)
				Move(1,1);
			else
				Move(1,-1);
		else if (temp_x_speed < 0)
			if (temp_y_speed > 0)
				Move(-1,1);
			else
				Move(-1,-1);
		else
			Move(1,1);
					
		this.x_ball = 120;
		this.y_ball = 280;
	}
	
	public void Disappear(int a, int b)
	{
		// Effects: Moves this off the screen into a specific position
		
		this.x_ball = a;
		this.y_ball = b;
	}
	
	
	public void SetSpeed(int a)
	{
		// Effects: Sets the speed of this
		
		this.speed = a;
	}

}
