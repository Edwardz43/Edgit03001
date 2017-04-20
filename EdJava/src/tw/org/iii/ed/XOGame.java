package tw.org.iii.ed;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class XOGame extends JFrame{
	private JButton[] button = new JButton[9];
	
	public XOGame() {
	super("XO Game");
	setLayout(new GridLayout(3, 3));
	
	
	
	for(int i=0;i<button.length; i++){
		button[i] = new JButton(""+(i+1));
		add(button[i]);
	}
	
	for(int i=0;i<button.length; i++){
		int n=i;
		button[i].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pressed(n);
			}
		});
	}
	
	
	
	setSize(300, 300);
	setVisible(true);
	setDefaultCloseOperation(3);
	}

	protected void pressed(int n) {
		button[n].setFont(new Font(null, Font.CENTER_BASELINE, 30));
		button[n].setText("X");
	}
	
//	@Override
//	public void paint(Graphics g) {
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.drawOval(30, 30, 50, 50);
//	}

}
