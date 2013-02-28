package com.fip.mvc;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


// ListDirectoryView.java
// Presentation only.  No user actions.

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
@SuppressWarnings("serial")
public class ListDirectoryView extends JFrame {

	private JPanel contentPane;
	private JButton button;
		
	/**
	 * Create the frame.
	 */
	public ListDirectoryView(ListDirectoryModel dirModel)  {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JList<String> view = new JList<String>(dirModel);
		getContentPane().add(new JScrollPane(view));
		
		button=new JButton("add \"world\"");
		getContentPane().add(button,BorderLayout.SOUTH);
	}
	
	public void addButtonListener(ActionListener l) {
		button.addActionListener(l);
	}
}
