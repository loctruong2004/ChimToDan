package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.app;
import view.topPlayer;

public class acction_top implements ActionListener{
    private app App;
	
	public acction_top(app App) {
       this.App = App; 	
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sc = e.getActionCommand();
		System.out.println(sc);
		if("Back".equals(sc)) {
			this.App.showLogin();
		}
	}
      
}
