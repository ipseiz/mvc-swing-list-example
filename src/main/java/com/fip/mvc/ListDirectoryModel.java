package com.fip.mvc;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

// ListDirectoryModel.java
// Model
// This model is completely independent of the user interface.
// It could as easily be used by a command line or web interface.

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
@SuppressWarnings("serial")
public class ListDirectoryModel extends AbstractListModel<String> {
	
	private final ArrayList<String> listDirectory = new ArrayList<String>() ;

	public ListDirectoryModel(){
		listDirectory.clear();
	}
	
	public ListDirectoryModel(String dir){
		listDirectory.add(dir);
	}
		
	public String getElementAt(int index) {
		return listDirectory.get(index);
	}

	public int getSize() {
		return listDirectory.size();
	}
	
	public void addDirectory(String directory) {
		int index=listDirectory.size();
		listDirectory.add(directory);
		fireIntervalAdded(this, index, index);
		}
	
	public void removeDirectory(String directory) {
		int index = listDirectory.lastIndexOf(directory);
		if (index >= 0) {
			listDirectory.remove(directory);
			fireIntervalRemoved(this, index, index);
		}
	}
}
