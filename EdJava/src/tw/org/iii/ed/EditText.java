package tw.org.iii.ed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EditText extends JFrame{
	private JButton open, save, exit;
	private JTextArea textarea;	
	public EditText(){
		super("文字編輯器");
		setLayout(new BorderLayout());		
		JPanel jp = new JPanel();
		
		open = new JButton("開啟檔案");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		
		save = new JButton("儲存檔案");	
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		
		exit = new JButton("關閉程式");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		textarea = new JTextArea();			
		jp.add(open); jp.add(save);jp.add(exit);
		add(textarea, new BorderLayout().CENTER);
		add(jp, new BorderLayout().NORTH);
		textarea.setBackground(Color.LIGHT_GRAY);
		textarea.setLineWrap(true);
		textarea.setFont(new Font("標楷體", Font.ROMAN_BASELINE, 20));
		setVisible(true);
		setSize(600, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	void openFile(){
		JFileChooser fc = new JFileChooser();
		int option = fc.showDialog(null,null);
		if(option == JFileChooser.APPROVE_OPTION){
			try {
				textarea.setText("");
				BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()));
				String temp;
				String lineSeparator = System.getProperty("line.separator");
				while((temp = br.readLine())!=null){
					textarea.append(temp);
					textarea.append(lineSeparator);
				}
				br.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e);
			}	
		}
	}
	void saveFile(){
		JFileChooser fc = new JFileChooser();
		int option = fc.showSaveDialog(null);
		if(option == JFileChooser.APPROVE_OPTION){
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fc.getSelectedFile()));
				bw.write(textarea.getText());
				bw.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	public static void main(String[] args){
			EditText t1 = new EditText();
	}
}

