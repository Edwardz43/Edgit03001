package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DigitalSign extends JFrame{
	private JButton clear, undo, redo, color, BGC, save, open;
	// 4/13 新增JRadioButton 選擇筆畫粗細
	private JRadioButton small, mid, big;
	private MySignPanel msp;
	
	public DigitalSign(){
		super("Digital Sign");
		setLayout(new BorderLayout());
		
		clear= new JButton("清空");undo= new JButton("undo");redo= new JButton("redo");small = new JRadioButton("細");
		color =  new JButton("顏色");BGC= new JButton("背景顏色");save= new JButton("儲存");open= new JButton("開啟檔案");
		//JRadioButton
		mid = new JRadioButton("中");big = new JRadioButton("粗");
		
		JPanel bottom = new JPanel(new FlowLayout());
		
		bottom.add(clear);bottom.add(undo);bottom.add(redo);bottom.add(BGC);bottom.add(color);
		//
		bottom.add(small);bottom.add(mid);bottom.add(big);
		
		bottom.add(save);bottom.add(open);
		//把JRadioButton 放在同一個group 這樣才可以一次只按一個鈕
		ButtonGroup bg = new ButtonGroup();
		bg.add(small);bg.add(mid);bg.add(big);
		
		add(bottom, BorderLayout.SOUTH);
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
				msp.changeColor();
			}
		});
		
		//換粗細的按鈕
		small.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.changeStroke(3);
			}
		});
		
		mid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.changeStroke(8);
			}
		});
		
		big.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.changeStroke(12);
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
		
		//儲存簽名
		save.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.saveFile();
			}
		});
		
		//開啟舊檔
		open.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				msp.openFile();
			}
		});
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new DigitalSign();
	}
}

