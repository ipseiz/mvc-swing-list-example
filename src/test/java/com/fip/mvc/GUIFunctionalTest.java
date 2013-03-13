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
import static org.fest.assertions.api.Assertions.failBecauseExceptionWasNotThrown;

/**
 * Description
 *
 * @author Fabien Ipseiz
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

		@SuppressWarnings("unused")
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
		window.button("add").requireDisabled();
		window.button("del").requireDisabled();
		window.textBox("inputText").enterText("Some random text");
		window.button("add").requireEnabled();
		window.button("add").click();
		assertThat(window.list("list").item(0).value(), is(equalTo("Some random text")));
	}
	
	@Test
	public void shouldAddTextInListWhenPressingReturn() {
		window.textBox("inputText").enterText("Some random text\n");
		assertThat(window.list("list").item(0).value(), is(equalTo("Some random text")));
	}
	
	@Test
	public void shouldDeleteTextInListWhenClickingDelButton() {
		shouldAddTextInListWhenClickingAddButton();
		window.list("list").clickItem(0);
		window.button("del").requireEnabled();
		window.button("del").click();
		// throwable specific assertions
		try {
			assertThat(window.list("list").item(0).value(), is(equalTo("Some random text")));
			// if IndexOutOfBoundsException was not thrown, test would fail with message :
			// "Expected IndexOutOfBoundsException to be thrown"
			failBecauseExceptionWasNotThrown(IndexOutOfBoundsException.class);
		} catch (IndexOutOfBoundsException e) {
			//assertThat(e).isInstanceOf(IndexOutOfBoundsException.class);
		}
		window.button("del").requireDisabled();
	}
	@Test
	public void doubleClickTextInListShouldActivateButtons() {
		window.textBox("inputText").enterText("Some random text\n");
		window.textBox("inputText").deleteText();
		window.list("list").item(0).doubleClick();
		window.button("add").requireEnabled();
		window.button("del").requireEnabled();
	}
}
