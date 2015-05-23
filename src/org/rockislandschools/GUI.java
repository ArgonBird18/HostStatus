/**
 * 
 */
package org.rockislandschools;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * @author ArgonBird18
 *
 */
public class GUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch(Exception ex){
			ex.printStackTrace();
		}
		Main.main(new String[]{JOptionPane.showInputDialog(null, "Enter the IP adress of the server:", 
				"Enter IP", JOptionPane.QUESTION_MESSAGE)});

	}

}
