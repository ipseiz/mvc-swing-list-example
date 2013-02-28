package com.fip.mvc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// ListDirectoryController.java
// Handles user interaction with listeners.
// Calls View and Model as needed.
/**
 * Description
 *
 * @author Fabien Ipseiz
 */

public class ListDirectoryController implements ActionListener{

	private ListDirectoryModel dirModel;
	private ListDirectoryView dirView;

	public ListDirectoryController(ListDirectoryModel model, ListDirectoryView view) {
		this.dirModel = model;
		this.dirView = view;
		
		//... Add listeners to the view.
		dirView.addButtonListener(this);
		
	}
	
	   
	public void actionPerformed(ActionEvent e) {
		dirModel.addDirectory("world");
	}


	
}
