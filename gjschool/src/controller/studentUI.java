package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.studentDaoImpl;
import model.student;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class studentUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField chi;
	private JTextField eng;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentUI frame = new studentUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public studentUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(44, 10, 340, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("學員管理系統");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(118, 10, 98, 26);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(44, 81, 340, 83);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		name = new JTextField();
		name.setBounds(45, 10, 59, 21);
		panel_1.add(name);
		name.setColumns(10);
		
		chi = new JTextField();
		chi.setColumns(10);
		chi.setBounds(157, 10, 59, 21);
		panel_1.add(chi);
		
		eng = new JTextField();
		eng.setColumns(10);
		eng.setBounds(271, 10, 59, 21);
		panel_1.add(eng);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("名:");
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 12));
		lblNewLabel_1.setBounds(22, 13, 46, 15);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("國文:");
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(121, 13, 52, 15);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("英文:");
		lblNewLabel_1_2.setFont(new Font("新細明體", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(236, 13, 46, 15);
		panel_1.add(lblNewLabel_1_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(0, 128, 255));
		panel_1_1.setBounds(44, 190, 340, 140);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 320, 87);
		panel_1_1.add(scrollPane);
		
		JTextArea output = new JTextArea();
		scrollPane.setViewportView(output);
		
		JButton check = new JButton("查詢(String)");
		check.setBounds(33, 10, 113, 23);
		check.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.queryAll2()--->List
				 * 2.String show="";
				 */
				output.setText(new studentDaoImpl().queryAll1());
				
			}
		});
		panel_1_1.add(check);
		
		JButton btnNewButton_1 = new JButton("查詢(List)");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<student> l=new studentDaoImpl().queryAll2();
				String show="";
				int sum=0;
				int i=0;
				for(student o:l)
				{
					i++;
					sum=sum+(o.getChi()+o.getEng());
					
					
					show=show+"id:"+o.getId()+
							"\t名:"+o.getName()+
							"\t國文:"+o.getChi()+
							"\t英文:"+o.getEng()+
							"\t總分:"+(o.getChi()+o.getEng())+"\n";
				}
				
				show=show+"\n總分合計="+sum+"\t平均="+(sum/i);
				
				output.setText(show);
			}
		});
		btnNewButton_1.setBounds(194, 10, 100, 23);
		panel_1_1.add(btnNewButton_1);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(10, 60, 17, 48);
		panel_1_1.add(scrollBar);
		
		
		
		JButton btnNewButton = new JButton("新增");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.擷取 name,chi,eng getText()
				 * 2.name,chi-->轉整數
				 * 3.new student(name,chi,eng);
				 * 4.add(s);
				 */
				
				String Name=name.getText();
				int CHI=Integer.parseInt(chi.getText());
				int ENG=Integer.parseInt(eng.getText());
				
				student s= new student(Name,CHI,ENG);
				
				new studentDaoImpl().add(s);
			}
		});
		btnNewButton.setBounds(24, 50, 87, 23);
		panel_1.add(btnNewButton);
	}
}
