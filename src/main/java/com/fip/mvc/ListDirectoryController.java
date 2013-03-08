package com.fip.mvc;

import java.awt.event.ActionEvent;

// ListDirectoryController.java
// Handles user interaction with listeners.
// Calls View and Model as needed.
/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class ListDirectoryController implements ButtonsListener {

	private final ListDirectoryModel dirModel;
	private final ListDirectoryView dirView;

	public ListDirectoryController(ListDirectoryModel model, ListDirectoryView view) {
		this.dirModel = model;
		this.dirView = view;
		view.addButtonsListener(this);
	}
	
	@Override
	public void addPerformed(ActionEvent e) {
		String text = dirView.getInputText();
		dirModel.addDirectory(text);
		dirView.enableRemoveButton();
	}
	
	@Override
	public void deletePerformed(ActionEvent e) {
		String text = dirView.getInputText();
		dirModel.removeDirectory(text);
			int listSize = dirModel.getSize();
			if (listSize == 0) {
				dirView.disableRemoveButton();
			}
			
		}
	}

