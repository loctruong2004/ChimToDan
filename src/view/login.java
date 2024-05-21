package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.action_login;
import model.DB;

public class login extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int broadWidth = 360;
	public int broadHeight = 640;
	public JTextField textfield;
	private JButton BTNsubmit;
	private DB db;
	private JButton BTNtop;
	private action_login listener;  
	public login(DB db) {
		this.db = db;
		this.showLogin();
	}
	public void showLogin() {
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(broadWidth, broadHeight));
		setBackground(Color.blue);
		setFocusable(true);
		setFocusable(true);

		this.setLayout(new GridLayout(4, 1));
		Font fontHeader = new Font("Arial", Font.BOLD, 22);
		Font fontBTN = new Font("Arial", Font.BOLD, 50);
		Font fontLabel = new Font("Arial", Font.BOLD, 40);
		JPanel header = new JPanel();
		JLabel headerlb = new JLabel("Nhập thông tin người chơi");
		headerlb.setFont(fontHeader);
		header.add(headerlb);

		ImageIcon icon = new ImageIcon(
				"C:/Users/locqw/eclipse-workspace/Java_01/Flappy_Bird_plus_bigger/src/Image/flappybird.png");
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(64, 48, Image.SCALE_SMOOTH); // Thay đổi kích thước hình ảnh
		icon = new ImageIcon(scaledImage);
		JPanel form = new JPanel();
		form.setLayout(new GridLayout(3, 1));
		JPanel wrapimg = new JPanel();
		wrapimg.add(new JLabel(icon));
		JPanel wrapname = new JPanel();
		JLabel name = new JLabel("Tên Người Chơi");
		name.setFont(fontLabel);
		wrapname.add(name);

		textfield = new JTextField();
		JPanel wrapTextfield = new JPanel();
		textfield.setColumns(9);
		textfield.setFont(fontLabel);
		wrapTextfield.add(textfield);
		form.add(wrapimg);
		form.add(wrapname);
		form.add(wrapTextfield);

		JPanel footer = new JPanel();
		BTNsubmit = new JButton("Start");

		BTNsubmit.setFont(fontBTN);
		footer.add(BTNsubmit);

		JPanel top = new JPanel();
		BTNtop = new JButton("Top Người Chơi");
		BTNtop.setFont(fontHeader);
		top.add(BTNtop);

		this.add(header);
		this.add(form);
		this.add(footer);
		this.add(top);

//		this.addKeyListener(ac);
	}

	public void addLoginListener(action_login listener) {
		textfield.addKeyListener(listener);
		textfield.addActionListener(listener);
		BTNsubmit.addActionListener(listener);
		BTNtop.addActionListener(listener);
	}
	public void removeActionListener() {
    	textfield.removeKeyListener(listener);
    	textfield.removeActionListener(listener);
    	BTNsubmit.removeActionListener(listener);
    	BTNtop.removeActionListener(listener);
    	}
	public boolean checked() {
		String text = textfield.getText().trim();
		db.setCurrentName(text);
		if (text.equals(null) || text.equals("") || !db.checkPerson())
			return false;
		return true;
	}
    public String getCurrName() {
    	return  textfield.getText().trim();
    }
	public void error() {
	   Border redBorder = BorderFactory.createLineBorder(Color.RED);
       textfield.setBorder(redBorder);
	}
}
