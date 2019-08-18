import java.awt.Color;
import java.awt.Graphics;

public class Gap implements Drawable
{
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Gap(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void drawMe(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}

	public boolean checkFall(Player p)
	{
		return (p.getX() > x && p.getX()+20 < x + width && p.getY() > y && p.getY()+20 < y + height);
	}
}