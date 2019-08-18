import java.awt.Color;
import java.awt.Graphics;

public class GreenGoal extends Goal
{
	public GreenGoal(int x, int y)
	{
		super(x, y);
	}
	
	public void drawMe(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 20, 20);
	}
	
	public Color getColor()
	{
		return Color.GREEN;
	}
}