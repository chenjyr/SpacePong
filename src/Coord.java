
public class Coord {
	// Overview: A new type which stores coordinates for easy retrieval
	// AF(c): 	 a[i], if i == 0 then a[i] = x coordinate of something 
	//				else if i == 1 then a[i] == y coordinate of something
	// RI:		 this != null && (this.size() == 2 || this.size() == 0)
	//			&& a[0] is x coordinate of object && a[1] is y coordinate of object
	
	
	@SuppressWarnings("unused")
	private int size;
	private int[] a = new int[2];

	
	public Coord()
	{
		// Constructor: Creates a new empty Coord 
		
	}
	
	public Coord(int b)
	{
		// Constructor: Create a new Coord with size b
		
		this.size = b;
	}
	
	public int getX()
	{
		// Effects: Gets the x coordinate of this
		
		return this.a[0];
	}
	
	public int getY()
	{
		// Effects: Gets the y coordinate of this
		
		return this.a[1];
	}
	
	public void setX(int x)
	{
		// Effects: Sets the x coordinate of this
		
		this.a[0] = x;
	}
	
	public void setY(int y)
	{
		// Effects: Sets the y coordinate of this
		
		this.a[1] = y;
	}
}
