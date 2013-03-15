// ImageListCellRenderer.java

package com.fip.mvc;

import java.awt.Component;
import java.net.URL;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

/**
 * Description
 *
 * @author Fabien Ipseiz
 *
 */
public class ImageListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(final JList<?> list,
			Object value, int index, boolean isSelected, boolean cellHasFocus) {
		/* String transformedValue = (String) value;
		try {
			// URL
		URL url = new URL(transformedValue);
		} catch (...) {
			// String
		}*/
		if(value instanceof URL) {
			value = new ImageIcon((URL) value);			
		}
		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}

}
