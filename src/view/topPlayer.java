package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.acction_top;
import controller.action_login;
import model.DB;
import model.Person;

public class topPlayer extends JPanel {
	private static final long serialVersionUID = 1L;
	public int broadWidth = 360;
	public int broadHeight = 640;
	private JButton back;
	private ArrayList<Person> listPerson = new ArrayList<Person>();
	public topPlayer(DB listPlay) {
		this.listPerson = listPlay.getPerson();
		this.ShowtopPlayer();
	}
	public void  ShowtopPlayer() {
		Font fontHeader = new Font("Arial", Font.BOLD, 22);
		setPreferredSize(new Dimension(broadWidth, broadHeight));
		setBackground(Color.blue);
		setFocusable(true);
		setFocusable(true);
		
		this.setLayout(new BorderLayout());
		JPanel headerTopPlayer = new JPanel();
		headerTopPlayer.setLayout(new BorderLayout());
		back = new JButton("Back");
		back.setFont(fontHeader);
		headerTopPlayer.add(back,BorderLayout.EAST);
//		headerTopPlayer.add()
		
		JPanel table = new JPanel();
		table.setLayout(new GridLayout(10,3));
		JPanel top1 = new JPanel();
		top1.add(new JLabel("Top"));
		top1.setBorder(BorderFactory.createLineBorder(Color.black));
		top1.setBackground(Color.RED); 
		JPanel name1 = new JPanel();
		name1.add(new JLabel("Tên"));
		name1.setBorder(BorderFactory.createLineBorder(Color.black));
		name1.setBackground(Color.RED); 
		JPanel diem1 = new JPanel();
		diem1.add(new JLabel("Số Điểm"));
		diem1.setBorder(BorderFactory.createLineBorder(Color.black));
		diem1.setBackground(Color.RED);
		table.add(top1);
		table.add(name1);
		table.add(diem1);
		for(int i=0;i<listPerson.size();i++) {
			JPanel top = new JPanel();
			top.add(new JLabel(i+""));
			top.setBorder(BorderFactory.createLineBorder(Color.black));
			JPanel name = new JPanel();
			name.add(new JLabel(listPerson.get(i).getTen()));
			name.setBorder(BorderFactory.createLineBorder(Color.black));
			JPanel diem = new JPanel();
			diem.add(new JLabel(listPerson.get(i).getDiem()+""));
			diem.setBorder(BorderFactory.createLineBorder(Color.black));
			table.add(top);
			table.add(name);
			table.add(diem);
		}
		this.add(headerTopPlayer,BorderLayout.NORTH);
		this.add(table,BorderLayout.CENTER);
	}
	public void addTop(acction_top listener) {
		back.addActionListener(listener);
	}
}
