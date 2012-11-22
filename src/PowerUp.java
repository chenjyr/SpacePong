
public class PowerUp {
	//Overview: Specifies the width and height of the block which contains
	//			a power up, and provides a method to pick up and activate the
	//			power up

	private int randompowerup;
	public static int width = 45;
	public static int height = 12;
	
	
	public PowerUp()
	{
		// Constructor: Creates a new power up object
	}
	
	public boolean PickRandomPowerUp(int random)
	{
		// Effects: Stores a random number inside a power up block
		
		this.randompowerup = random;
		
		return true;
	}
	
	public int ActivatePowerUp()
	{
		// Effects: Activates the power up and returns the number 
		//			that was contained within the power up
		
		if (this.randompowerup == 0)
		{
			return 0;
		}
		else if (this.randompowerup == 1)
		{
			return 1;
		}
		else if (this.randompowerup == 2)
		{
			return 2;
		}
		else 
		{
			return 3;
		}
	}
	
}
