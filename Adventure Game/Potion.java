import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Potion implements Item
{	
	private Player p;
	
	public Potion(Player p)
	{
		this.p = p;
	}
	
	public void use(ArrayList<Bullet> bullets)
	{
		
	}
	
	public int getCharge()
	{
		return 404;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.MAGENTA);
		g.fillRect(p.getX(), p.getY(), 20, 20);
	}
}