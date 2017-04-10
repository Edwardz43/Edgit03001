package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.NetworkChannel;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class DigitalSign extends JFrame{
	private JButton clear, undo, redo, color, stroke, BGC, save, open;
	private MySignPanel msp;
	
	public DigitalSign(){
		super("Digital Sign");
		setLayout(new BorderLayout());
		
		clear= new JButton("清空");undo= new JButton("undo");redo= new JButton("redo");stroke= new JButton("筆畫粗細");
		color =  new JButton("設定顏色");BGC= new JButton("背景顏色");
		JPanel top = new JPanel(new FlowLayout());
		top.add(clear);top.add(undo);top.add(redo);top.add(color);top.add(stroke);top.add(BGC);
		add(top, BorderLayout.NORTH);
		msp = new MySignPanel(this);
		add(msp, BorderLayout.CENTER);
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.clear();
			}
		});
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.undo();
			}
		});
		redo.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.redo();
				
			}
		});
		
		//換顏色的按鈕
		color.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Color myColor = JColorChooser.showDialog(DigitalSign.this, "Select a Color :", Color.BLUE);
				msp.changeColor(myColor);
				
			}
		});
		
		//換粗細的按鈕
		stroke.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				//用JOP來輸入粗細  加個try/catch避免輸入錯誤
				try{
					int  stroke = Integer.parseInt(JOptionPane.showInputDialog("輸入筆畫粗細 :"));
					msp.changeStroke(stroke);
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null, "請輸入正確數字!");
				}
			}
		});
		
		//換背景顏色的按鈕
		BGC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Color bgc = JColorChooser.showDialog(DigitalSign.this, "Select a Color :", Color.yellow);
				msp.changeBGC(bgc);
			}
		});
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		new DigitalSign();
	}
}

