package com.fip.mvc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


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
	private JButton addButton;
	private JButton removeButton;
	private JTextField inputElement;
	private static String inputText;
	
	
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
		
		// Create input field row:
		JLabel entryLabel = new JLabel("Input text:");
		inputElement = new JTextField("", 30);
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		inputPanel.add(entryLabel);
		inputPanel.add(inputElement);
		JPanel inputRowPanel = new JPanel(new BorderLayout());
		inputRowPanel.setBorder(new TitledBorder("Add a text to the list"));
		inputRowPanel.add(inputPanel);
		contentPane.add(inputRowPanel, BorderLayout.NORTH);

		// Register a KeyListener for the inputElement text field.
		inputElement.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				inputText = textField.getText();
				if (!textField.getText().equals("")) {
					addButton.setEnabled(true);
					removeButton.setEnabled(true);
				} else {
					addButton.setEnabled(false);
				}
			}
			/*public void keyTyped(KeyEvent e) {
				// TODO: Do something for the keyTyped event
			}
			public void keyPressed(KeyEvent e) {
				// TODO: Do something for the keyPressed event
			}*/
		});

		// Create list panel:
		JList<String> viewList = new JList<String>(dirModel);
		getContentPane().add(new JScrollPane(viewList));

		// Create buttons panel:
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addButton=new JButton("Add");
		addButton.setActionCommand("add");
		addButton.setEnabled(false);
		buttonsPanel.add(addButton);
		
		removeButton=new JButton("Delete");
		buttonsPanel.add(removeButton);
		removeButton.setActionCommand("del");
		removeButton.setEnabled(false);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public void buttonsListener(ActionListener l) {
		addButton.addActionListener(l);
		removeButton.addActionListener(l);
	}

	/**
	 * @return the inputText
	 */
	public static String getInputText() {
		return inputText;
	}
}
