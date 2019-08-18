import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class UselessPotion implements Item
{	
	public void use(ArrayList<Bullet> bullets)
	{
		//As the name would suggest, this item does absolutely nothing.
	}
	
	public int getCharge()
	{
		//Of course, in the game, it is simply labeled as a "Potion."
		return 404;
	}
	
	public void draw(Graphics g)
	{
		//One of the many pitfalls this game has in store. No pun intended.
	}
}