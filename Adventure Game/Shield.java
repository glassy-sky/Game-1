import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Shield implements Item
{
	//Blocks the first hit within 2 seconds
	
	private int charge;
	private Player p;
	
	public Shield(Player p)
	{
		charge = 200;
		this.p = p;
	}
	
	public void use(ArrayList<Bullet> bullets)
	{
		if(charge > 0)
		{
			for(int i = 0; i < bullets.size(); i++)
			{
				if(bullets.get(i).checkCollision(p))
				{
					bullets.remove(i);
					charge = 0;
				}
			}
			charge--;
		}
	}
	
	public int getCharge()
	{
		return charge;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.CYAN);
		g.fillRect(p.getX()-2, p.getY()-2, 24, 24);
	}
}