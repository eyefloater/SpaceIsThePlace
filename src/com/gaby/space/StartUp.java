package com.gaby.space;

import javax.swing.SwingUtilities;

public class StartUp {

	public static void main(String[] args) {

		//runs program
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new GUI().setVisible(true);
	            }
	        });
    }

}