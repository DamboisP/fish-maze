import java.awt.Image;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Map{

	private Scanner m;
	private String Map[] = new String[14];

private Image floor, coral, end, start;

public Map(){

	ImageIcon img = new ImageIcon("graphics/rockFloor.png");
	floor = img.getImage();
	img = new ImageIcon("graphics/coralBlock.png");
	coral = img.getImage();
	img = new ImageIcon("graphics/end.png");
	end = img.getImage();
	img = new ImageIcon("graphics/start.png");
	start = img.getImage();

	openFile();
	readFile();
	closeFile();

}

public Image getFloor(){

	return floor;

}

public Image getFinishLine(){

	return end;

}

public Image getStart(){

	return start;

}

public Image getCoral(){

	return coral;

}


public String getMap(int x, int y){

String index = Map[y].substring(x,x + 1);
return index;

}

public void openFile(){


try{
m = new Scanner(new File("levels/lvl1.txt"));
}catch(Exception e){
System.out.println("Error loading map");
}


}

public void readFile(){
while(m.hasNext()){

for(int i = 0; i < 14; i++){
Map[i] = m.next();

}

}

}

public void closeFile(){

	m.close();

}



}