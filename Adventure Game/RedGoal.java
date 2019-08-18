import java.awt.Color;
import java.awt.Graphics;

public class RedGoal extends Goal
{
	public RedGoal(int x, int y)
	{
		super(x, y);
	}
	
	public void drawMe(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(x, y, 20, 20);
	}
	
	public Color getColor()
	{
		return Color.RED;
	}
}