package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import view.app;

public class action_menu implements ActionListener{
    private app App;
	public action_menu(app App) {
		// TODO Auto-generated constructor stub
		this.App = App;
	}


//	@Override
//	public void menuSelected(MenuEvent e) {
//		// TODO Auto-generated method stub
//		
//		System.out.println();		
//	}
//
//	@Override
//	public void menuDeselected(MenuEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void menuCanceled(MenuEvent e) {
//		// TODO Auto-generated method stub
//		
//	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("top");
		App.showTopPlayerMenu();
	}

}
