import java.awt.Color;
import java.awt.Graphics;

public class YellowGoal extends Goal
{
	public YellowGoal(int x, int y)
	{
		super(x, y);
	}
	
	public void drawMe(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 20, 20);
	}
	
	public Color getColor()
	{
		return Color.YELLOW;
	}
}