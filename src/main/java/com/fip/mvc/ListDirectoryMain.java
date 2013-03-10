package com.fip.mvc;
// ListDirectotyMain.java

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class ListDirectoryMain {

	//... Create model, view, and controller.  They are
    //    created once here and passed to the parts that
    //    need them so there is only one copy of each.
	public static void main(String[] args) {
		
		ListDirectoryModel      model      = new ListDirectoryModel();
		ListDirectoryView       view       = new ListDirectoryView(model);
		ListDirectoryController controller = new ListDirectoryController(model, view);
		//... Add listeners to the view.
		//view.addButtonsListener(controller);
		
		view.pack();
		view.setLocation(200, 200);
		view.setVisible(true);
	}

}
