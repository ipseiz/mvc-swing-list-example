// GuiFunctionalTest.java

package com.fip.mvc;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Description
 *
 * @author ipseiz
 *
 */
public class GUIFunctionalTest {
	private FrameFixture window;

	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@Before
	public void setUp() {
		final ListDirectoryModel model = new ListDirectoryModel();

		ListDirectoryView view = GuiActionRunner
				.execute(new GuiQuery<ListDirectoryView>() {
					protected ListDirectoryView executeInEDT() {
						return new ListDirectoryView(model);
					}
				});

		ListDirectoryController controller = new ListDirectoryController(model, view);
				

		window = new FrameFixture(view);
		window.show(); // shows the frame to test
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}

	@Test
	public void shouldAddTextInListWhenClickingAddButton() {
		window.textBox("inputText").enterText("Some random text");
		window.button("add").click();
		assertThat(window.list("list").item(0).value(), is(equalTo("Some random text")));
	}
}
