import java.awt.Graphics;
import java.util.ArrayList;

public interface Item
{
	public void use(ArrayList<Bullet> bullets);
	
	public int getCharge();
	
	public void draw(Graphics g);
}