import java.awt.Color;
import java.awt.Graphics;

public abstract class Goal implements Drawable
{
	protected int x;
	protected int y;

	public Goal(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public abstract void drawMe(Graphics g);
	
	public abstract Color getColor();
	
	public boolean checkFinish(Player p)
	{
		return (p.getX() + 20 > x && p.getX() < x + 20 && p.getY() + 20 > y && p.getY() < y + 20);
	}
}
