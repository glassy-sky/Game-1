import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class EMP implements Item
{
	//Stops new bullets from coming for 1 second
	
	private int charge;
	private int diameter;
	private Player p;
	
	public EMP(Player p)
	{
		charge = 100;
		diameter = 0;
		this.p = p;
	}
	
	public void use(ArrayList<Bullet> bullets)
	{
		if(charge > 0)
		{
			for(int i = 0; i < bullets.size(); i++)
			{
				if(bullets.get(i).getX() > 801)
				{
					bullets.remove(i);
				}
			}
			charge--;
			diameter += 30;
		}
	}
	
	public int getCharge()
	{
		return charge;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.drawOval(p.getX()+10-(diameter/2), p.getY()+10-(diameter/2), diameter, diameter);
	}
}