// JFSTextT.java

package com.fip.mvc;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *  Translates the text of ListDirectoryView frame. 
 *
 * @author Fabien Ipseiz
 */
public enum TextTranslation {
	
	INSTANCE;

	private Locale locale = null;
	private ResourceBundle bundle = null;

	/**
	 * Sets some default values for the object.
	 */
	private TextTranslation() {
		locale = Locale.getDefault();
		//locale = Locale.ENGLISH;
		bundle = ResourceBundle.getBundle("com.fip.mvc.Translation",locale);
	}

	/**
	 * Returns the reference of the only Text object.
	 * 
	 * @return The only Text instance.
	 */
	public final static TextTranslation getInstance() {
		return INSTANCE;
	}

	/**
	 * Returns the translated string for a certain key.
	 * 
	 * @param key
	 *            The key.
	 * @return Translated string.
	 */
	public final String get(String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}
}
