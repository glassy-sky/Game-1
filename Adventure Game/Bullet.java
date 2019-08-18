import java.awt.Color;
import java.awt.Graphics;

public class Bullet implements Drawable
{
	private int x;
	private int y;
	private int direction;
	
	public Bullet(int x, int y)
	{
		this.x = x;
		this.y = y;
		direction = 0;
	}
	public Bullet(int x, int y, boolean d)
	{
		this.x = x;
		this.y = y;
		direction = d ? 1 : 2;
	}
	
	public void drawMe(Graphics g)
	{
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 6, 6);
	}
	
	public void shoot()
	{
		if(direction == 0)
		{
			x -= 2;
			y -= 2;
		}
		else if(direction == 1)
		{
			x -= 3;
		}
		else
		{
			y -= 3;
		}
	}

	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean checkCollision(Player p)
	{
		return (p.getX()+20 > x && p.getX() < x + 6 && p.getY()+20 > y && p.getY() < y + 6);
	}
}