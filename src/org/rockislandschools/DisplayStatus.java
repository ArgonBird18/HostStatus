package org.rockislandschools;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayStatus {

	public static boolean isHidden = false;
	public static boolean hasSystemTray = false;
	public static SystemTray tray;

	public static JFrame buildFrame(final String ipAdd, String status){

		if(isHidden){
			Toolkit.getDefaultToolkit().beep();
			tray.getTrayIcons()[0].displayMessage("HostStatus - "+ipAdd, "Status change: The server is "+status, TrayIcon.MessageType.INFO);
			return Main.statusFrame;
		}else{
			final JFrame frame = new JFrame("Host Status");
			frame.addWindowListener(
					new WindowAdapter() {
						public void windowClosing( WindowEvent e )
						{
							DisplayStatus.setVisible(false, ipAdd, frame);
						}
					});
			
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

			JPanel panel = new JPanel();

			JLabel ipLabel = new JLabel(ipAdd);
			ipLabel.setOpaque(true);
			ipLabel.setBackground(Color.white);
			ipLabel.setPreferredSize(new Dimension(20, 25));

			JLabel stateLabel = new JLabel(status);
			stateLabel.setOpaque(true);
			if (status.equals("Up")){
				stateLabel.setBackground(Color.GREEN);
			}
			if (status.equals("Down")){
				stateLabel.setBackground(Color.red);
			} 
			if (status.equals("Loading...")){
				stateLabel.setBackground(Color.yellow);
			} 
			stateLabel.setPreferredSize(new Dimension(20, 25));

			if(hasSystemTray){
				frame.addWindowListener(
						new WindowAdapter() {
							public void windowClosing( WindowEvent e )
							{
								if(tray.getTrayIcons().length != 0){
									DisplayStatus.setVisible(false, ipAdd, frame);
								}else{
									System.exit(0);
								}
							}
						});
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}else{
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			panel.setPreferredSize(new Dimension(400,400));
			frame.getContentPane().add(panel);
			frame.getContentPane().add(ipLabel, BorderLayout.CENTER);
			frame.getContentPane().add(stateLabel, BorderLayout.AFTER_LAST_LINE);
			frame.pack();
			frame.setIconImage(createImage("icon.png", "tray icon"));
			frame.setVisible(true);
			return frame;
		}

	}

	public static void setVisible(boolean b, String ip, JFrame frame){
		if(b){
			isHidden = false;
			frame.dispose();
			frame = DisplayStatus.buildFrame(ip, Main.currentState);
			frame.setVisible(true);
		}else{
			frame.setVisible(false);
			isHidden = true;
			tray.getTrayIcons()[0].displayMessage("HostStatus - "+ip, "The frame has been hidden. Click here to show it.", TrayIcon.MessageType.INFO);
		}
	}

	public static void setupSystemTray(){
		if (!SystemTray.isSupported()) {
			System.err.println("SystemTray is not supported");
			return;
		}
		final PopupMenu popup = new PopupMenu();
		final TrayIcon trayIcon =
				new TrayIcon(createImage("icon.png", "tray icon").getScaledInstance(16, 16, 0));
		tray = SystemTray.getSystemTray();
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}

		});
		MenuItem showItem = new MenuItem("Show");
		showItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				isHidden = false;
				Main.statusFrame.dispose();
				Main.statusFrame = DisplayStatus.buildFrame(Main.address, Main.currentState);
				Main.statusFrame.setVisible(true);

			}

		});
		popup.add(showItem);
		popup.add(exitItem);
		trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.err.println("TrayIcon could not be added.");
			return;
		}
		hasSystemTray = true;
	}

	protected static Image createImage(String path, String description) {
		URL imageURL = ClassLoader.getSystemClassLoader().getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}

}

