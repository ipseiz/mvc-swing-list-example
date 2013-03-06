package com.fip.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

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
		dirView.buttonsListener(this);
	}
	
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		String text = ListDirectoryView.getInputText();
		switch (cmd.toLowerCase()) {
		case "add":
			dirModel.addDirectory(text);
			dirView.enableRemoveButton();
			break;
		case "del":
			dirModel.removeDirectory(text);
			int listSize = dirModel.getSize();
			if (listSize == 0) {
				dirView.disableRemoveButton();
			}
			
		}
	}
}
