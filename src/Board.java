import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.bytedeco.javacpp.opencv_core.IplImage;




/*#############################################
##################### LE JEU #################
##############################################*/
@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener{
	


	
	Camera webcam = new Camera();
	private Timer timer;
	private Map m;

	private int direction;

	private Fish f;
	private Image arrowUp,arrowLeft,arrowDown,arrowRight;
	
	private int fishDirection = 0;
	public Board(){


		addKeyListener(new Al());
		setFocusable(true);

		ImageIcon img = new ImageIcon("graphics/arrowUp.png");
		arrowUp = img.getImage();
		img = new ImageIcon("graphics/arrowLeft.png");
		arrowLeft = img.getImage();
		img = new ImageIcon("graphics/arrowDown.png");
		arrowDown = img.getImage();
		img = new ImageIcon("graphics/arrowRight.png");
		arrowRight = img.getImage();
		
		m = new Map();
		f = new Fish();

		timer = new Timer(25,this);
		timer.start();



		
	}
	
	
	
	
public void actionPerformed(ActionEvent e){
		
	if(m.getMap(f.getTileX(), f.getTileY()).equals("e")){

		System.out.println("Finish");
		timer.stop();
	
	}
	repaint();
}
	
	public void paintComponent(Graphics g){
		
		Color aColor = new Color(163,174,255);

	g.setColor(aColor);
	g.fillRect(0, 0, 900, 474);

	
		
	 try {
		 int fishX = webcam.getPosX();
		 int fishY = webcam.getPosY();
			if(fishX > 0 && fishX < 320){
				if(fishY > 0 && fishY < 240){
					
			if(f.getTileX()<= 13 && f.getTileX()>=0){
				if(f.getTileY()<=13 && f.getTileY()>=1){
			fishDirection = 3;
			if (!m.getMap(f.getTileX(), f.getTileY()-1).equals("c")){
			f.move(0, -1);
			System.out.println("UP");
			
		}
				}}
				}
		}
			
			if(fishX > 320 && fishX < 640){
				if(fishY > 0 && fishY < 240){
					
					if(f.getTileX()<= 12 && f.getTileX()>=0){
						if(f.getTileY()<=13 && f.getTileY()>=0){
					fishDirection = 0;
					if (!m.getMap(f.getTileX()+1, f.getTileY()).equals("c")){
					f.move(1, 0);
					System.out.println("RIGHT");
		}
				}}
				}
		}
			
			//
			if(fishX > 0 && fishX < 320){
				if(fishY > 240 && fishY < 480){
					
					if(f.getTileX()<=13 && f.getTileX()>=1){
						if(f.getTileY()<=13 && f.getTileY()>=0){
					fishDirection = 1;
					if (!m.getMap(f.getTileX()-1, f.getTileY()).equals("c")){
					f.move(-1, 0);
					System.out.println("LEFT");
		}
				}}
				}
		}
			
			//
			
			if(fishX > 320 && fishX < 640){
				if(fishY > 240 && fishY < 480){
					
					if(f.getTileX()<= 13 && f.getTileX()>=0){
						if(f.getTileY()<=12 && f.getTileY()>=0){
					fishDirection = 2;
					if (!m.getMap(f.getTileX(), f.getTileY()+1).equals("c")){
					f.move(0, 1);
					System.out.println("DOWN");
		}
				}}
				}
		}
			
			//
			
			
			
			
		 g.drawImage(webcam.getValue(), 468, 158,404,227, null);
	      Image logo = ImageIO.read(new File("graphics/logo.png"));
	      g.drawImage(logo, 468, 49, this);
	      Image webSite = ImageIO.read(new File("graphics/webSite.png"));
	      //g.drawImage(webSite, 468, 395, this);
	      
	      g.drawImage(arrowUp, 553, 199, this);
	      g.drawImage(arrowLeft, 553, 312, this);
	      g.drawImage(arrowDown, 759, 312, this);
	      g.drawImage(arrowRight, 759, 199, this);




	    } catch (IOException e) {
	      e.printStackTrace();
	    } 
	 
	  g.setColor(Color.white);
	    g.drawLine(670, 158, 670, 385);
	    g.drawLine(468, 271, 872 , 271);
		
     
	}
	
public void paint(Graphics g){
	
               
	          
	super.paint(g);
	

	
	for(int y = 0;y < 14; y++){
		for(int x= 0; x < 14; x++){
		if(m.getMap(x,y).equals("f")){
		g.drawImage(m.getFloor(), x*32, y*32, null);
			}
		if(m.getMap(x,y).equals("c")){
		g.drawImage(m.getCoral(), x*32, y*32, null);
			}
		if(m.getMap(x,y).equals("s")){
		g.drawImage(m.getStart(), x*32, y*32, null);
			}
		if(m.getMap(x,y).equals("e")){
		g.drawImage(m.getFinishLine(), x*32, y*32, null);
			}
		}
	}
	
	if(fishDirection == 0){
		g.drawImage(f.getFishRight(), f.getTileX()*32, f.getTileY()*32, null);
	}
	if(fishDirection == 1){
		g.drawImage(f.getFishLeft(), f.getTileX()*32, f.getTileY()*32, null);
	}
	if(fishDirection == 2){
		g.drawImage(f.getFishDown(), f.getTileX()*32, f.getTileY()*32, null);
	}
	if(fishDirection == 3){
		g.drawImage(f.getFishUp(), f.getTileX()*32, f.getTileY()*32, null);
	}
	
}





private class Al extends KeyAdapter{
	public void keyPressed(KeyEvent e){
		int keycode = e.getKeyCode();
		
		
		if(keycode == KeyEvent.VK_Z){
			
			
					
			if(f.getTileX()<= 13 && f.getTileX()>=0){
				if(f.getTileY()<=13 && f.getTileY()>=1){
			fishDirection = 3;
			if (!m.getMap(f.getTileX(), f.getTileY()-1).equals("c")){
			f.move(0, -1);
			System.out.println("UP");
		}
				
				}
		}
		}
		if(keycode == KeyEvent.VK_S){
			if(f.getTileX()<= 13 && f.getTileX()>=0){
				if(f.getTileY()<=12 && f.getTileY()>=0){
			fishDirection = 2;
			if (!m.getMap(f.getTileX(), f.getTileY()+1).equals("c")){
			f.move(0, 1);
			System.out.println("DOWN");
			}}
		}
		}
		if(keycode == KeyEvent.VK_Q){
			if(f.getTileX()<=13 && f.getTileX()>=1){
				if(f.getTileY()<=13 && f.getTileY()>=0){
			fishDirection = 1;
			if (!m.getMap(f.getTileX()-1, f.getTileY()).equals("c")){
			f.move(-1, 0);
			System.out.println("LEFT");
			}}
		}
		}
		if(keycode == KeyEvent.VK_D){
			if(f.getTileX()<= 12 && f.getTileX()>=0){
				if(f.getTileY()<=13 && f.getTileY()>=0){
			fishDirection = 0;
			if (!m.getMap(f.getTileX()+1, f.getTileY()).equals("c")){
			f.move(1, 0);
			System.out.println("RIGHT");
			}
		}}}
		
		}
	
	
	public void keyReleased(KeyEvent e){
		
	}
	
	public void keyTyped(KeyEvent e){
		
	}


}

}
