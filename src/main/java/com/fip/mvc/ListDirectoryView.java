package com.fip.mvc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// ListDirectoryView.java
// Presentation only.  No user actions.

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class ListDirectoryView extends JFrame {

	private static final long serialVersionUID = 1L;

	private final JButton addButton;
	private final JButton removeButton;
	private final JTextField inputElement;
	private final JList<String> viewList ;
	
	/**
	 * Create the frame.
	 */
	public ListDirectoryView(ListDirectoryModel dirModel)  {
		
		// Get translation object and set default locale:
		TextTranslation t = TextTranslation.getInstance();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setName(getTitle());
		setContentPane(contentPane);
		
		// Create input field row:
		JLabel entryLabel = new JLabel(t.get("label.input"));
		inputElement = new JTextField(40);
		inputElement.setName("inputText");
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		inputPanel.add(entryLabel);
		inputPanel.add(inputElement);
		JPanel inputRowPanel = new JPanel(new BorderLayout());
		inputRowPanel.setBorder(new TitledBorder(t.get("label.title")));
		inputRowPanel.add(inputPanel);
		contentPane.add(inputRowPanel, BorderLayout.NORTH);

		// Register a KeyListener for the inputElement text field.
		inputElement.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
				      addButton.doClick();
				}
			}
		});

		// Register a DocumentListener for the inputElement text field.
		inputElement.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (inputElement.getText().isEmpty()) {
					addButton.setEnabled(false);
				} 
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				addButton.setEnabled(true);
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}
		});
		
		// Create list panel:
		viewList = new JList<String>(dirModel);
		viewList.setName("list");
		getContentPane().add(new JScrollPane(viewList));

		// Select text if row is double-clicked:
		viewList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2 && viewList.getSelectedIndex() != -1) {
					inputElement.setText(viewList.getSelectedValue());
				}
			}
		});

		// Register a ListSelectionListener for the list panel.
		viewList.addListSelectionListener(new ListSelectionListener() {
						
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (viewList.getSelectedIndices().length == 0 ){
					removeButton.setEnabled(false);
				} else {
					removeButton.setEnabled(true);
				}
				
			}
		});
		
		// Create buttons panel:
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addButton=new JButton(t.get("button.add"));
		addButton.setEnabled(false);
		addButton.setName("add");
		buttonsPanel.add(addButton);
		
		removeButton=new JButton(t.get("button.remove"));
		removeButton.setEnabled(false);
		removeButton.setName("del");
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
	 * @return the indexes of the selected rows of the list
	 */
	public int[] getSelectedText() {
		return viewList.getSelectedIndices();
	}
	
	/**
	 * @return the inputText
	 */
	public String getInputText() {
		return inputElement.getText();
	}
}
