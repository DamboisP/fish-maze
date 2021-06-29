

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainFrame extends JFrame{

	public static void main(String[] args) {

new MainFrame();
		
	
	}
	
	public MainFrame(){
		
		
		JFrame frame = new JFrame();
		frame.setTitle("Fish maze");
		frame.setSize(900, 474);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		//frame.setContentPane(new Camera());
		
		
		frame.add(new Board());
	
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
