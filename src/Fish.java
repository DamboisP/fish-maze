import java.awt.Image;

import javax.swing.ImageIcon;


public class Fish {

	private int tileX, 	tileY;
	
	private Image fishRight;
	private Image fishLeft;
	private Image fishUp;
	private Image fishDown;
	
	
	public Fish(){
		
		ImageIcon img = new ImageIcon("graphics/fishRight.png");
		fishRight = img.getImage();
		
		ImageIcon img2 = new ImageIcon("graphics/fishLeft.png");
		fishLeft = img2.getImage();
		
		ImageIcon img3 = new ImageIcon("graphics/fishUp.png");
		fishUp = img3.getImage();
		
		ImageIcon img4 = new ImageIcon("graphics/fishDown.png");
		fishDown = img4.getImage();
		
		
		

		
		tileX = 0;
		tileY = 12;
	}
	
	
	public Image getFishDown(){
		
		return fishDown;	
	}
	
	public Image getFishUp(){
		
		return fishUp;	
	}
	
	public Image getFishLeft(){
		
		return fishLeft;	
	}
	
	public Image getFishRight(){
		
		return fishRight;	
	}
	

	
	public int getTileX(){
		return tileX;
	}
	
	public int getTileY(){
		return tileY;
	}
	
	
	public void move(int dx, int dy){

		
		tileX += dx;
		tileY += dy;
		
		
	}
}
