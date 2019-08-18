import java.awt.Graphics;
import java.awt.Color;

public class Player implements Drawable
{
	int x;
	int y;
	
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void drawMe(Graphics g)
	{ 
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 20, 20);
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void moveUp()
	{
		if(y > 0)
			y-=3;
	}
	
	public void moveDown()
	{
		if(y < 700)
			y+=3;
	}
	
	public void moveLeft()
	{
		if(x > 0)
			x-=3;
	}
	
	public void moveRight()
	{
		if(x < 780)
			x+=3;
	}
}