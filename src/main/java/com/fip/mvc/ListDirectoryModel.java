package com.fip.mvc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

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
public class ListDirectoryModel extends AbstractListModel<Object> {
	
	private static final long serialVersionUID = 1L;
	
	private final ArrayList<Object> listDirectory = new ArrayList<Object>() ;
	private final Properties imageProperties = new Properties();
		
	public ListDirectoryModel() {
		InputStream imageStream = getClass().getResourceAsStream("Images.properties"); 
		try {
			imageProperties.load(imageStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object getElementAt(int index) {
		return listDirectory.get(index);
	}
	
	public int getSize() {
		return listDirectory.size();
	}
	
	public void addDirectory(final String directory) {		
		int index=listDirectory.size();
		String result = imageProperties.getProperty(directory);
		if(result == null) {
			listDirectory.add(directory);
		} else {
			URL url = getClass().getResource(result);
			listDirectory.add(url);
		}		
		fireIntervalAdded(this, index, index);
	}
	
	public void removeDirectory(String directory) {
		int index = listDirectory.lastIndexOf(directory);
		if (index >= 0) {
			listDirectory.remove(directory);
			fireIntervalRemoved(this, index, index);
		}
	}

	public void removeMultipleDirectory(int... indices) {
		if (indices.length > 0) {
			for (int i = indices.length-1; i >= 0; i--) {
				listDirectory.remove(indices[i]);
			}
			fireIntervalRemoved(this, indices[0], indices[indices.length-1]);
		}
	}
}
