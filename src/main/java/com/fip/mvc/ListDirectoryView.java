package com.fip.mvc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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
public class ListDirectoryView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton addButton;
	private JButton removeButton;
	private JTextField inputElement;
	
	/**
	 * Create the frame.
	 */
	public ListDirectoryView(ListDirectoryModel dirModel)  {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setName(getTitle());
		setContentPane(contentPane);
		
		// Create input field row:
		JLabel entryLabel = new JLabel("Input text:");
		inputElement = new JTextField(40);
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
				if (!textField.getText().isEmpty()) {
					addButton.setEnabled(true);
					removeButton.setEnabled(true);
				} else {
					addButton.setEnabled(false);
				}
			}
		});

		// Create list panel:
		JList<String> viewList = new JList<String>(dirModel);
		getContentPane().add(new JScrollPane(viewList));

		// Create buttons panel:
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addButton=new JButton("Add");
		addButton.setEnabled(false);
		buttonsPanel.add(addButton);
		
		removeButton=new JButton("Delete");
		removeButton.setEnabled(false);
		buttonsPanel.add(removeButton);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public void addButtonsListener(final ButtonsListener l) {
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				l.addPerformed(e);
				
			}
		});
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				l.deletePerformed(e);
				
			}
		});
	}
	
	/**
	 * @return the inputText
	 */
	public String getInputText() {
		return inputElement.getText();
	}
	
	/**
	 * Activates the button delete 
	 */
	public void enableRemoveButton() {
		removeButton.setEnabled(true);
	}
	
	/**
	 * Deactivates the button delete 
	 */
	public void disableRemoveButton() {
		removeButton.setEnabled(false);
	}
}
