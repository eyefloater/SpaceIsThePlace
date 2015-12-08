package com.gaby.space;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import java.awt.Insets;

public class GUI extends JFrame {
	private JTextField commandLine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JTextPane Descriptor = new JTextPane();
		Descriptor.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_Descriptor = new GridBagConstraints();
		gbc_Descriptor.gridheight = 9;
		gbc_Descriptor.gridwidth = 8;
		gbc_Descriptor.insets = new Insets(0, 0, 5, 5);
		gbc_Descriptor.fill = GridBagConstraints.BOTH;
		gbc_Descriptor.gridx = 0;
		gbc_Descriptor.gridy = 0;
		getContentPane().add(Descriptor, gbc_Descriptor);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 4;
		gbc_tabbedPane.gridheight = 9;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 8;
		gbc_tabbedPane.gridy = 0;
		getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		commandLine = new JTextField();
		GridBagConstraints gbc_commandLine = new GridBagConstraints();
		gbc_commandLine.insets = new Insets(0, 0, 0, 5);
		gbc_commandLine.gridwidth = 12;
		gbc_commandLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_commandLine.gridx = 0;
		gbc_commandLine.gridy = 9;
		getContentPane().add(commandLine, gbc_commandLine);
		commandLine.setColumns(10);
	}

}
