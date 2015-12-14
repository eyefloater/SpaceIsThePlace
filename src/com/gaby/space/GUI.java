package com.gaby.space;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {

	// simple GUI, not resizable, with a frame, 4 text areas for display, and a text field to serve as command line
	private JFrame frame;
	private static JTextArea results;
	private static JTextArea options;
	private static JTextArea commands;
	private static JTextArea inventory;
	private static JTextField commandLine;
	private static String commandLineText;

	public GUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Space Is The Place");

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);

		// results = central description panel
		results = new JTextArea(20, 10);
		results.setEditable(false);
		results.setBackground(Color.black);

		// set font
		Font font = new Font("Arial", Font.BOLD, 12);
		results.setFont(font);
		results.setForeground(Color.WHITE);
		results.setCaretColor(Color.DARK_GRAY);

		JScrollPane scrollPaneResults = new JScrollPane(results);
		scrollPaneResults.setBounds(216, 8, 500, 250);
		// results.setLineWrap(true);
		results.setLineWrap(true);
		results.setWrapStyleWord(true);
		results.setText("");
		panel.add(scrollPaneResults);

		// dialog is displayed in options panel
		options = new JTextArea(20, 10);
		options.setEditable(false);
		options.setBackground(Color.BLACK);
		JScrollPane scrollPanelOptions = new JScrollPane(options);
		scrollPanelOptions.setBounds(216, 265, 500, 150);
		options.setCaretColor(Color.DARK_GRAY);

		// set font
		Font font2 = new Font("Arial", Font.BOLD, 12);
		options.setFont(font2);

		options.setForeground(Color.WHITE);
		options.setLineWrap(true);
		options.setWrapStyleWord(true);
		options.setText("");
		panel.add(scrollPanelOptions);

		// panel just lists the actions player can perform (although there a
		// couple that appear later in the game: move box, take off suit.
		commands = new JTextArea(20, 10);
		commands.setEditable(false);
		commands.setBackground(Color.BLACK);
		commands.setBounds(8, 8, 200, 444);

		// set font
		Font font3 = new Font("Arial", Font.BOLD, 12);
		commands.setFont(font3);
		commands.setForeground(Color.LIGHT_GRAY);
		commands.setText("");
		panel.add(commands);

		// lists items in inventory. items appended to list as they are taken,
		// removed when dropped
		inventory = new JTextArea(20, 10);
		inventory.setEditable(false);
		inventory.setBackground(Color.BLACK);
		inventory.setBounds(724, 8, 200, 444);
		Font font4 = new Font("Arial", Font.BOLD, 12);
		inventory.setFont(font4);
		inventory.setForeground(Color.LIGHT_GRAY);
		inventory.setText("");
		panel.add(inventory);

		// where player enters commands
		commandLine = new JTextField(20);
		commandLine.setBackground(Color.white);
		commandLine.setBounds(216, 428, 500, 24);
		commandLine.setText("Enter a command.");
		panel.add(commandLine);

		commandLine.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent event) {
				int key = event.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					String text = commandLine.getText();
					// remove end of line
					text = text.replaceAll("(\\r|\\n)", "");
					/*
					 * if (onlyNumbers) { int number = 0; try { number =
					 * Integer.parseInt(text); if (number == 1) {
					 * showResults(result1); }else if (number == 2){
					 * showResults(result2); } else {
					 * options.append("Enter a valid option.\n"); } } catch
					 * (Exception e) {
					 * 
					 * number = 0; options.append("Enter a valid number.\n"); }
					 * } else {
					 */
					Commands.proccessCommand(text);
					clearCommandLineText();
					commandLine.setCaretPosition(0);
					commandLine.requestFocus();
				}

			}

			private void clearCommandLineText() {
				commandLine.setText("");
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		// keeps game going until window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(950, 500);
		frame.setVisible(true);
	}

	// prints text to the results pane
	public static void showResults(String text) {
		results.setText(text);
	}

	public static void clearCommands() {
		commands.setText("");

	}

	public static void showCommands(String text) {
		commands.append(text);

	}

	public static void showInventory(String text) {
		inventory.setText("");
		inventory.append(text + "\n");

	}

	public static void clearaInventory() {
		inventory.setText("");

	}

	public static void clear() {
		results.setText("");
	}

	public static void clearCommandLineText() {
		commandLineText = "";
		commandLine.setText("");
	}

	public static void clearOptions() {
		options.setText("");
	}

	// prints text to options (dialog) pane
	public static void addToOptions(String text) {
		options.append(text);
	}

}
