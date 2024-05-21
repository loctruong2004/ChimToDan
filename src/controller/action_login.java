package controller;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import view.app;
import view.login;

public class action_login implements ActionListener, KeyListener {
	private login lg;
	private app App;

	public action_login(login lg, app App) {
		this.lg = lg;
		this.App = App;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (lg.checked()) {
				App.startGame(lg.getCurrName());
			} else {
				lg.error();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sc = e.getActionCommand();
		if ("Start".equals(sc)) {
			if (lg.checked()) {
				App.startGame(lg.getCurrName());
			} else {
				lg.error();
			}
		}
    
		if("Top Người Chơi".equals(sc) ) {
			App.showTopPlayer();
		}
   	}

}
