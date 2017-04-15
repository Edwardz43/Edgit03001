package tw.org.iii.ed;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Racing extends JFrame{
	private JButton go;
	int n = 8;
	private JLabel[] lanes = new JLabel[n];
	private Car[] cars = new Car[n];
	private int rank;
	private boolean isRunnig;
	
	public Racing(){
		super("Racing Game");
		setLayout(new GridLayout(lanes.length+1, 1));
		
		go = new JButton("Go!");
		add(go);
		for(int i=0; i< lanes.length; i++){
			JLabel lane = new JLabel((i+1)+ ".");
			lanes[i] = lane;
			add(lane);
		}
		
		go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				go();
			}
		});
				
		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(3);
	}
	
	protected void stopGame() {
		for(Car car: cars){
			car.interrupt();
			isRunnig = false;
		}
	}

	private void go(){
		if(!isRunnig){
			isRunnig = true;
			rank = 1;
			for(int i=0; i<lanes.length; i++){
				lanes[i].setText((i+1)+ ".");
			}
			
			for(int i=0; i<cars.length; i++){
				cars[i] = new Car(i);
			}
			
			for(int i=0; i<cars.length; i++){
				cars[i].start();
			}
		}
	}
	
	private class Car extends Thread{
		private int lane;
		Car(int lane){
			this.lane=lane;
		}
		@Override
		public void run() {
			for(int i=0; i<100; i++){
				lanes[lane].setText(lanes[lane].getText()+">");
				try {
					Thread.sleep(10 + (int)(Math.random()*100));
				} catch (InterruptedException e) {
					return;
				}
			}
			lanes[lane].setText(lanes[lane].getText() +"==> WINNER");
			stopGame();
		}
	}
	
	public static void main(String[] args) {
		new Racing();
	}
}
