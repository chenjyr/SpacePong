import java.awt.*;
import java.applet.*; 


import java.lang.String;

@SuppressWarnings("serial")
public class MainMenu extends Applet {


	public boolean Start_SinglePlayer = false;
	
	public MainMenu()
	{
		
	}
	
	public void init()
	{
		setBackground (Color.BLACK);
		
		start();
	}

	

	// paint graphics
	public void paint (Graphics g) 
	{

/*
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("MainMenu.bmp"));
		} catch (IOException e) {
			System.out.println("Cannot Find Image");
		}
		g.drawImage(img,0,0,null);
	*/	
		
		// Draw Main Menu
		g.setColor(Color.RED);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 120));
		g.drawString("SPACE", 20, 120);
		g.drawString("PONG", 50, 250);
		
		g.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		g.drawString("By Jackie Chen and Victor Tsang", 185, 593);
		
		g.setColor(Color.GREEN);
		g.fill3DRect(170, 450, 150, 20, false);
		g.fill3DRect(80, 360, 200, 40, true);
		g.draw3DRect(170, 450, 150, 20, false);
		g.draw3DRect(81, 361, 200, 40, true);
		g.setColor(Color.BLUE);
		g.fillOval(170, 410, 20, 20);
		
		

		
		
}
	 

	public static void main(String[] args) 
	{
		
		
	   
		
	}
}
