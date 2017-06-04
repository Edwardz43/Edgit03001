package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class XOGame extends JFrame{
	private JPanel gameZone, menuBar, gameState;
	private JButton[] button = new JButton[9];
	private Buttons[] bt = new Buttons[9];
	private JButton start;
	private JTextPane turns;
	private boolean playerTurn;
	private int moves; 
	
	public XOGame() {
		super("XO Game");
		
		setLayout(new BorderLayout());
		
		//遊戲區 放井字9宮格
		gameZone = new JPanel();
		gameZone.setLayout(new GridLayout(3, 3));
		
		//設置按鈕  
		//包括實體按鍵=>JButton 用來按的     java物件=>Buttons 用來紀錄資訊
		for(int i=0;i<button.length; i++){
			button[i] = new JButton();
			button[i].setBackground(new Color(209, 233, 255));
			gameZone.add(button[i]);
			bt[i] = new Buttons(i); 	
		}
		
		
		//設置JButton監聽器 按下後會紀錄資訊
		for(int i=0;i<button.length; i++){
			int n=i;
			button[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pressed(n);
					bt[n].isPressed = true;
				}
			});
		}
		
		//設置開始功能列  按下後才會開始遊戲/產生新局
		menuBar = new JPanel(new FlowLayout());
		start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		
		menuBar.add(start);gameState = new JPanel();turns = new JTextPane();gameState.add(turns);
		
		add(gameState, BorderLayout.SOUTH);
		add(menuBar, BorderLayout.NORTH);
		add(gameZone, BorderLayout.CENTER);
		
		for(int i=0 ; i< button.length; i++){
			bt[i].isPressed = true;
		}
		
		setSize(450, 500);
		setVisible(true);
		setLocation(getWidth()/2, getHeight()/2);
		setDefaultCloseOperation(3);
	}
	
	//Buttons類別 紀錄按下 以及被誰按兩個屬性
	private class Buttons {
		private int check;
		private boolean isPressed = false;
	
		public Buttons(int number){
			 check = 0;
		}	
	}
	
	//開新局  會將按鍵資訊初始化
	protected void startGame() {
		moves = 0;
		for(int i=0 ; i< button.length; i++){
			bt[i].isPressed = false;
			bt[i].check = 0;
			button[i].setText("");
			button[i].setBackground(new Color(209, 233, 255));
		}
		playerTurn = true;
		JOptionPane.showMessageDialog(null, "Game Start!");
		turns.setText("\"O\"'s turn");
	}
	
	//設置pressed方法  有分先攻或後攻
	protected void pressed(int n) {
		if(!bt[n].isPressed){
			button[n].setFont(new Font(null, Font.CENTER_BASELINE, 50));
			button[n].setBackground(new Color(255, 129, 66));
			bt[n].isPressed = true;
			moves++;
			System.out.println(moves);
			if(playerTurn){
				button[n].setText("O");
				bt[n].check = 1;
				turns.setText("\"X\"'s turn");
				check();
				playerTurn = false;
			}else{
				button[n].setText("X");
				bt[n].check = 2;
				turns.setText("\"O\"'s turn");
				check();
				playerTurn = true;
			}
		}
	}
	
	//檢查是否有人獲勝  一樣分先攻或後攻   結果分成4種  直的 橫的 左斜 右斜   直橫各跑3次迴圈
	protected void check() {
		if(playerTurn){
			for(int i=0; i<3; i++){
				if(bt[i*3].check==1 && bt[i*3+1].check==1 && bt[i*3].check==bt[i*3+2].check){
					isOver(1);
					//System.out.println("1s");
					return;
				}else if(bt[i].check==1 && bt[i+3].check==1 && bt[i].check==bt[i+6].check){
					isOver(1);
					//System.out.println("2s");
					return;
				}	
			}
			if(bt[0].check == 1 && bt[4].check ==1 && bt[0].check==bt[8].check){
				isOver(1);
				//System.out.println("3s");
				return;
			}else if(bt[2].check== 1 && bt[4].check ==1 && bt[2].check==bt[6].check){
				isOver(1);
				//System.out.println("4s");
				return;
			}
		}else{
			for(int i=0; i<3; i++){
				if(bt[i*3].check==2 && bt[i*3+1].check==2 && bt[i*3].check==bt[i*3+2].check){
					isOver(2);
					//System.out.println("1a");
					return;
				}else if(bt[i].check==2 && bt[i+3].check==2 && bt[i].check==bt[i+6].check){
					isOver(2);
					//System.out.println("2a");
					return;
				}	
			}
			if(bt[0].check == 2 && bt[4].check ==2 && bt[0].check==bt[8].check){
				isOver(2);
				//System.out.println("3a");
				return;
		
			}else if(bt[2].check== 2 && bt[4].check ==2 && bt[2].check==bt[6].check){
				isOver(2);
				//System.out.println("4a");
				return;
			}
		}
		if(moves == 9)isOver(3);
	}
	
	//gameover 判斷贏家 關閉按鍵功能
	private void isOver(int n) {
		for(int i=0 ; i< button.length; i++){
			bt[i].isPressed = true;
		}
		switch(n){
			//回傳1 O贏
			case 1:
				turns.setText("\"O\" win");
				JOptionPane.showMessageDialog(null, "Winner is  O !");
				break;
			//回傳2 X贏	
			case 2:	
				turns.setText("\"X\" win");
				JOptionPane.showMessageDialog(null, "Winner is  X !");
				break;
			//回傳3 平手	
			case 3:
				turns.setText("Tied");
				JOptionPane.showMessageDialog(null, "Tied");
				break;
			}	
	}
	public static void main(String[] args) {
		new XOGame();
	}
}