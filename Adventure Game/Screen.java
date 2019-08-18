import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.io.File;
import javax.imageio.ImageIO;

public class Screen extends JPanel
{
	private JButton start;
	private JButton instructions;
	private JButton buyItem1;
	private JButton buyItem2;
	private JButton buyItem3;
	private JButton nextLevel;
	private JButton restart;
	private int level;
	private int money;
	private int gained;
	private int deaths;
	private int shoot;
	private Player player;
	private Goal end;
	private ArrayList<Bullet> bullets;
	private ArrayList<Gap> pitfalls;
	private ArrayList<EMP> EMPs;
	private ArrayList<Shield> shields;
	private ArrayList<Potion> potions;
	private boolean useEMP;
	private boolean useShield;
	private boolean usePotion;
	private boolean showEndScreen;
	private BufferedImage buffer;
	private BufferedImage background;
	Input input;
	
	public void playBGM()
	{
		try
		{
			URL url = this.getClass().getClassLoader().getResource("September_Sky.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception ex)
		{
			ex.printStackTrace(System.out);
		}
	}
	
	public void playDeathSound()
	{
		try
		{
			URL url = this.getClass().getClassLoader().getResource("laser.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		}
		catch(Exception ex)
		{
			ex.printStackTrace(System.out);
		}
	}
	
	public Screen()
	{
		setLayout(null);
		input = new Input(this);
		bullets = new ArrayList<Bullet>();
		pitfalls = new ArrayList<Gap>();
		EMPs = new ArrayList<EMP>();
		shields = new ArrayList<Shield>();
		potions = new ArrayList<Potion>();
		level = 0;
		money = 0;
		gained = 0;
		deaths = 0;
		shoot = 0;
		end = new RedGoal(1000, 1000);
		input = new Input(this);
		player = new Player(3, 3);
		useEMP = false;
		useShield = false;
		usePotion = false;
		showEndScreen = false;
		start = new JButton();  
		start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		start.setBounds(360, 423, 80, 25); 
		start.addActionListener(
			new ActionListener()
			{ 	
				public void actionPerformed(ActionEvent e) 
				{ 
					start.setVisible(false);
					instructions.setVisible(false);
					level++;
					repaint();
				}
			}
		);
		instructions = new JButton();  
		instructions.setOpaque(false);
		instructions.setContentAreaFilled(false);
		instructions.setBorderPainted(false);
		instructions.setBounds(310, 483, 180, 25); 
		instructions.addActionListener(
			new ActionListener()
			{ 	
				public void actionPerformed(ActionEvent e) 
				{ 
					JOptionPane.showMessageDialog(((JButton)e.getSource()).getParent(),
					    "You are a blue citizen of the Cube Nation who has been randomly " +
						"selected to test out your military's new weaponry,\n" +
						"a summons that no one seems to return from. Use the arrow keys " +
						"to move and the keys 1, 2, and 3 to use items,\nwhich you " +
						"can buy with points. Points are earned simply by staying " +
						"alive in a level, and they are saved between\nlevels. " +
						"However, dying once causes you to lose all points you earned " +
						"during that level. To compensate, you have\nunlimited lives.\n" +
						"Your objective is to avoid the hail of bullet fire and reach " +
						"the cube official at the end of each level.\nAnd hopefully make " +
						"it out of this ordeal alive.\nHopefully.\nP.S. Avoid the gaps; " +
						"those kill you too.",
						"Instructions", JOptionPane.PLAIN_MESSAGE);
				}
			}
		);
		buyItem1 = new JButton();
		buyItem1.setOpaque(false);
		buyItem1.setContentAreaFilled(false);
		buyItem1.setForeground(Color.WHITE);
		buyItem1.setText("<html>EMP, 25<br />You have " + EMPs.size() + "</html>");
		buyItem1.setToolTipText("Prevents bullets from firing for 1 second; keybind 1");
		buyItem1.setVisible(false);
		buyItem1.setBounds(115, 285, 120, 50); 
		buyItem1.addActionListener(
			new ActionListener()
			{ 	
				public void actionPerformed(ActionEvent e) 
				{ 
					if(money >= 25)
					{
						EMPs.add(new EMP(player));
						money -= 25;
					}
				}
			}
		);
		buyItem2 = new JButton();
		buyItem2.setOpaque(false);
		buyItem2.setContentAreaFilled(false);
		buyItem2.setForeground(Color.WHITE);
		buyItem2.setText("<html>Shield, 20<br />You have " + shields.size() + "</html>");
		buyItem2.setToolTipText("Blocks next shot within 2 seconds; keybind 2");
		buyItem2.setVisible(false);
		buyItem2.setBounds(340, 285, 120, 50); 
		buyItem2.addActionListener(
			new ActionListener()
			{ 	
				public void actionPerformed(ActionEvent e) 
				{ 
					if(money >= 20)
					{
						shields.add(new Shield(player));
						money -= 20;
					}
				}
			}
		);
		buyItem3 = new JButton();
		buyItem3.setOpaque(false);
		buyItem3.setContentAreaFilled(false);
		buyItem3.setForeground(Color.WHITE);
		buyItem3.setText("<html>Potion, 10<br />You have " + potions.size() + "</html>");
		buyItem3.setToolTipText("Doubles rate of point collection for one level, effect lost upon death; keybind 3");
		buyItem3.setVisible(false);
		buyItem3.setBounds(565, 285, 120, 50); 
		buyItem3.addActionListener(
			new ActionListener()
			{ 	
				public void actionPerformed(ActionEvent e) 
				{ 
					if(money >= 10)
					{
						potions.add(new Potion(player));
						money -= 10;
					}
				}
			}
		);
		nextLevel = new JButton();
		nextLevel.setOpaque(false);
		nextLevel.setContentAreaFilled(false);
		nextLevel.setForeground(Color.WHITE);
		nextLevel.setText("Next Level");
		nextLevel.setVisible(false);
		nextLevel.setBounds(350, 70, 100, 50); 
		nextLevel.addActionListener(
			new ActionListener()
			{ 	
				public void actionPerformed(ActionEvent e) 
				{ 	
					end.setX(1000);
					end.setY(1000);
					level++;
					buyItem1.setVisible(false);
					buyItem2.setVisible(false);
					buyItem3.setVisible(false);
					nextLevel.setVisible(false);
					restart();
					repaint();
				}
			}
		);
		restart = new JButton();  
		restart.setVisible(false);
		restart.setOpaque(false);
		restart.setContentAreaFilled(false);
		restart.setBorderPainted(false);
		restart.setBounds(345, 550, 110, 25); 
		restart.addActionListener(
			new ActionListener()
			{ 	
				public void actionPerformed(ActionEvent e) 
				{ 
					restart.setVisible(false);
					showEndScreen = false;
					level = 0;
					money = 0;
					gained = 0;
					deaths = 0;
					useEMP = false;
					useShield = false;
					usePotion = false;
					EMPs.clear();
					shields.clear();
					potions.clear();
					restart();
					repaint();
				}
			}
		);
		add(start);
		add(instructions);
		add(buyItem1);
		add(buyItem2);
		add(buyItem3);
		add(nextLevel);
		add(restart);
		playBGM();
	}
	
	public void generateBullets()
	{
		int n;
		if(!useEMP)
		{
			for(int i = 0; i < 4; i++)
			{
				n = (int) (Math.random()*712 + 4);
				bullets.add(new Bullet(810, n, true));
			}
			for(int i = 0; i < 4; i++)
			{
				n = (int) (Math.random()*792 + 4);
				bullets.add(new Bullet(n, 730, false));
			}
			for(int i = 1; i < level+1; i++)
			{
				n = (int) (Math.random()*1500 + 10);
				if(n <= 800)
				{
					bullets.add(new Bullet(n, 730));
				}
				else
				{
					bullets.add(new Bullet(810, n-800));
				}
			}
			bullets.add(new Bullet(player.getX(), 730, false));
			bullets.add(new Bullet(810, player.getY(), true));
		}
	}
	
	public Dimension getPreferredSize() 
	{
		return new Dimension(800, 720);
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		if(buffer == null)
		{
		   buffer = (BufferedImage)(createImage(getWidth(),getHeight()));
		}
		
		Graphics gBuffer = buffer.createGraphics();
		
		if(level == 10)
		{
			end = new GreenGoal(end.x, end.y);
		}
		else if(level%2 == 0)
		{
			end = new RedGoal(end.x, end.y);
		}
		else
		{
			end = new YellowGoal(end.x, end.y);
		}
	
		if(showEndScreen)
		{
			gBuffer.setColor(Color.BLACK);
			gBuffer.fillRect(0, 0, 800, 720);
			gBuffer.setColor(Color.WHITE);
			Font scoreFont = new Font("ISOCP", Font.BOLD, 30);
			gBuffer.setFont(scoreFont);
			gBuffer.drawString("You've met with a terrible fate, haven't you?", 80, 250);
			gBuffer.drawString("Deaths: " + deaths, 330, 370);
			gBuffer.drawString("Points: " + money, 330, 440);
			gBuffer.drawString("Restart", 345, 572);
			restart.setVisible(true);
			g.drawImage(buffer, 0, 0, null);
		}
		else if(end.checkFinish(player))
		{
			if(level == 10)
			{
				nextLevel.setVisible(true);
				gBuffer.setColor(Color.BLACK);
				gBuffer.fillRect(0, 0, 800, 720);
				gBuffer.setColor(Color.WHITE);
				gBuffer.drawLine(350, 650, 610, 650);
				gBuffer.drawLine(350, 540, 350, 650);
				Font finalFont = new Font("ISOCP", Font.BOLD, 20);
				gBuffer.setFont(finalFont);
				gBuffer.drawString("How did you make it this far? You should be dead by now...", 80, 160);
				gBuffer.drawString("Is it possible that you are resistant to our bullets? Intriguing.", 80, 230);
				gBuffer.drawString("But I'm afraid we can't let you leave. You see, the public", 80, 300);
				gBuffer.drawString("might not approve of what we put test subjects like you through.", 80, 370);
				gBuffer.drawString("So we need to make sure that you don't tell anyone.", 80, 440);
				gBuffer.drawString("Go on to the final level. And indeed, it will be your last.", 80, 510);
				gBuffer.setColor(end.getColor());
				gBuffer.fillRect(650, 500, 152, 222);
				g.drawImage(buffer, 0, 0, null);
			}
			else
			{
				money += gained;
				gained = 0;
				if(useEMP)
				{
					EMPs.remove(0);
					useEMP = false;
				}
				if(useShield)
				{
					shields.remove(0);
					useShield = false;
				}
				if(usePotion)
				{
					potions.remove(0);
					usePotion = false;
				}
				buyItem1.setVisible(true);
				buyItem2.setVisible(true);
				buyItem3.setVisible(true);
				nextLevel.setVisible(true);
				buyItem1.setText("<html>EMP, 25<br />You have " + EMPs.size() + "</html>");
				buyItem2.setText("<html>Shield, 20<br />You have " + shields.size() + "</html>");
				buyItem3.setText("<html>Potion, 10<br />You have " + potions.size() + "</html>");
				gBuffer.setColor(Color.BLACK);
				gBuffer.fillRect(0, 0, 800, 720);
				gBuffer.setColor(Color.YELLOW);
				gBuffer.drawOval(130, 180, 90, 90);
				gBuffer.drawOval(140, 190, 70, 70);
				gBuffer.drawOval(150, 200, 50, 50);
				gBuffer.drawOval(160, 210, 30, 30);
				gBuffer.drawOval(170, 220, 10, 10);
				gBuffer.setColor(new Color(89, 233, 233));
				gBuffer.fillOval(355, 180, 90, 90);
				gBuffer.setColor(Color.BLUE);
				gBuffer.drawOval(355, 180, 90, 90);
				gBuffer.drawOval(356, 181, 88, 88);
				gBuffer.setColor(new Color(255, 0, 255));
				gBuffer.fillRect(610, 230, 30, 40);
				gBuffer.setColor(new Color(137, 91, 45));
				gBuffer.fillRect(612, 180, 27, 25);
				gBuffer.setColor(Color.WHITE);
				gBuffer.drawRect(610, 190, 30, 80);	
				gBuffer.drawLine(350, 650, 610, 650);
				gBuffer.drawLine(350, 540, 350, 650);
				Font shopFont = new Font("ISOCP", Font.BOLD, 30);
				gBuffer.setFont(shopFont);
				gBuffer.drawString("Points: " + money, 100, 100);
				gBuffer.drawString("Hello! I hope level " + level + " wasn't too hard.", 80, 440);
				gBuffer.drawString("Good luck on level " + (level+1) +". You'll need it.", 80, 510);
				gBuffer.setColor(end.getColor());
				gBuffer.fillRect(650, 500, 152, 222);
				g.drawImage(buffer, 0, 0, null);
			}
		}
		else
		{
			if(background == null)
			{
				try
				{
					background = ImageIO.read(new File("background.jpg"));
					gBuffer.drawImage(background, 0, 0, null);
				}
				catch(Exception ex)
				{
				
				}
			}
			else
			{
				gBuffer.drawImage(background, 0, 0, null);
			}
	
			switch(level)
			{
				case 0:
					start.setVisible(true);
					instructions.setVisible(true);
					Font largeFont = new Font("ISOCP", Font.BOLD, 70);
					Font smallFont = new Font("ISOCP", Font.BOLD, 30);
					gBuffer.setFont(largeFont);
					gBuffer.setColor(Color.BLACK);
					gBuffer.fillRect(0, 0, 800, 720);
					gBuffer.setColor(Color.WHITE);
					gBuffer.drawString("Gauntlet", 255, 305);
					gBuffer.setFont(smallFont);
					gBuffer.drawString("Start", 360, 445);
					gBuffer.drawString("Instructions", 310, 505);
					break;
				case 1:
					end.setX(550);
					end.setY(350);
					break;
				case 2:
					pitfalls.add(new Gap(30, 60, 130, 100));
					pitfalls.add(new Gap(70, 230, 260, 150));
					end.setX(550);
					end.setY(550);
					break;
				case 3:
					pitfalls.add(new Gap(100, 100, 100, 100));
					pitfalls.add(new Gap(100, 400, 100, 100));
					pitfalls.add(new Gap(400, 100, 100, 100));
					pitfalls.add(new Gap(400, 400, 100, 100));
					end.setX(540);
					end.setY(540);
					break;
				case 4: 
					pitfalls.add(new Gap(0, 250, 250, 100));
					pitfalls.add(new Gap(550, 370, 250, 100));
					end.setX(620);
					end.setY(500);
					break;
				case 5:
					for(int i = 30; i < 770; i+=60)
					{
						for(int j = 30; j < 690; j+=60)
						{
							pitfalls.add(new Gap(i, j, 30, 30));
						}
					}
					end.setX(575);
					end.setY(545);
					break;
				case 6:
					for(int i = 30; i < 770; i+=30)
					{
						pitfalls.add(new Gap(i-1, 720-i, 39, 39));
					}
					end.setX(600);
					end.setY(550);
					break;
				case 7:
					pitfalls.add(new Gap(160, 160, 480, 400));
					end.setX(450);
					end.setY(610);
					break;
				case 8:
					pitfalls.add(new Gap(200, 0, 400, 250));
					pitfalls.add(new Gap(200, 470, 400, 253));
					end.setX(620);
					end.setY(600);
					break;
				case 9:
					pitfalls.add(new Gap(200, 0, 100, 500));
					pitfalls.add(new Gap(500, 220, 303, 503));
					end.setX(620);
					end.setY(100);
					break;
				case 10:
					for(int i = 100; i < 800; i+=200)
					{
						for(int j = 0; j < 720; j+=180)
						{
							pitfalls.add(new Gap(i, j, 100, 90));
						}
					}
					for(int k = 0; k < 800; k+=200)
					{
						for(int l = 90; l < 720; l+=180)
						{
							pitfalls.add(new Gap(k, l, 100, 90));
						}
					}
					end.setX(540);
					end.setY(485);
					break;
				case 11:
					end.setX(1000);
					end.setY(1000);
					break;
			}
			for(Gap each: pitfalls)
			{
				each.drawMe(gBuffer);
			}
			if(useEMP)
			{
				EMPs.get(0).use(bullets);
				EMPs.get(0).draw(gBuffer);
				if(EMPs.get(0).getCharge() <= 0)
				{
					useEMP = false;
					EMPs.remove(0);
				}	
			}
			if(useShield)
			{
				shields.get(0).use(bullets);
				shields.get(0).draw(gBuffer);
				if(shields.get(0).getCharge() <= 0)
				{
					useShield = false;
					shields.remove(0);
				}	
			}	
			if(level != 0)
			{
				player.drawMe(gBuffer);
				end.drawMe(gBuffer);
			}
			if(usePotion)
			{
				potions.get(0).draw(gBuffer);
			}
			for(int i = 0; i < bullets.size(); i++)
			{
				bullets.get(i).drawMe(gBuffer);
			}		
			for(Gap each: pitfalls)
			{
				if(each.checkFall(player))
				{
					deaths++;
					restart();
					break;
				}
			}
			for(Bullet each: bullets)
			{
				if(each.checkCollision(player) && !useShield)
				{
					deaths++;
					if(level >= 11)
					{
						showEndScreen = true;
					}
					playDeathSound();
					restart();
					break;
				}
			}
			g.drawImage(buffer, 0, 0, null);
		}
	}
	
	public void animate()
    {
        while(true)
        {
			try{ 
				Thread.sleep(10); 
			}catch(InterruptedException ex){ 
				Thread.currentThread().interrupt(); 
			}
			if(!end.checkFinish(player) && level != 0)
			{
				if(Input.keyboard[37]) 
				{
					player.moveLeft();
				}
				if(Input.keyboard[38])
				{
					player.moveUp();
				}
				if(Input.keyboard[39]) 
				{           
					player.moveRight();
				}
				if(Input.keyboard[40])
				{
					player.moveDown();
				}
				if(Input.keyboard[49]) 
				{
					if(EMPs.size() > 0)
					{
						useEMP = true;
					}
				}
				if(Input.keyboard[50])
				{
					if(shields.size() > 0)
					{
						useShield = true;
					}
				}
				if(Input.keyboard[51]) 
				{           
					if(potions.size() > 0)
					{
						usePotion = true;
					}
				}
				if(Input.keyboard[80])
				{	
					end.setX(player.getX());
					end.setY(player.getY());	
				}
				if(shoot%60 == 0)
				{
					generateBullets();
					gained += 2;
					if(usePotion == true)
					{
						gained += 2;
					}
					shoot = 0;
				}
				shoot++;
				for(Bullet each: bullets)
				{
					each.shoot();
				}
				for(int i = 0; i < bullets.size(); i++)
				{
					if(bullets.get(i).getX() < -6 || bullets.get(i).getY() < -6)
					{
						bullets.remove(i);
					}
				}
			}
			else
			{
				shoot = 0;
			}
			
			repaint();
		}
    }
	
	public void restart()
	{
		pitfalls.clear();
		bullets.clear();
		gained = 0;
		shoot = 0;
		if(useEMP)
		{
			EMPs.remove(0);
			useEMP = false;
		}
		if(useShield)
		{
			shields.remove(0);
			useShield = false;
		}
		if(usePotion)
		{
			potions.remove(0);
			usePotion = false;
		}
		usePotion = false;
		player.setX(3);
		player.setY(3);
	}
}