// ListDirectoryModelTest.java

package com.fip.mvc;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Description
 *
 * @author ipseiz
 *
 */

public class ListDirectoryModelTest {

	private ListDirectoryModel model ;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		model = new ListDirectoryModel("one");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.fip.mvc.ListDirectoryModel#getElementAt(int)}.
	 */
	@Test
	public void testGetElementAt() {
		assertThat(model.getElementAt(0), is(equalTo("one")));
	}

	/**
	 * Test method for {@link com.fip.mvc.ListDirectoryModel#getSize()}.
	 */
	@Test
	public void testGetSize() {
		//assertEquals(1, model.getSize());  <= junit
		assertThat(model.getSize(), is(equalTo(1)));   // <= hamcrest
	}

	/**
	 * Test method for {@link com.fip.mvc.ListDirectoryModel#addDirectory(java.lang.String)}.
	 */
	@Test
	public void testAddDirectory() {
		model.addDirectory("two");
		assertThat(model.getSize(), is(equalTo(2)));
		assertThat(model.getElementAt(1), is(equalTo("two")));
	}

	/**
	 * Test method for {@link com.fip.mvc.ListDirectoryModel#removeDirectory(java.lang.String)}.
	 */
	@Test
	public void testRemoveDirectory() {
		model.removeDirectory("two");
		assertThat(model.getSize(), is(equalTo(1)));
	}

}
