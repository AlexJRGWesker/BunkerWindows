package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.contentJPanel.ContentJPanel;
import gui.titleJPanel.TitleJPanel;

public class mainGUI {
	private int width = 600;
	private int height = 800;
	
	public mainGUI() {
		
	}
	
	public JFrame gui() {
		JFrame guiBunkerWindows = new JFrame("BunkerWindows");
		guiBunkerWindows.setSize(new Dimension(this.width, this.height));
		ImageIcon icono = new ImageIcon(getClass().getResource("/images/iconoApp.jpg"));
		guiBunkerWindows.setIconImage(icono.getImage());
        
		JPanel titleAndContent = new JPanel(new BorderLayout());
		titleAndContent.setPreferredSize(new Dimension(this.width, this.height));
		TitleJPanel titleJP = new TitleJPanel(this.width, 200);
		JPanel title = titleJP.title();
		ContentJPanel contentJP = new ContentJPanel(this.width, 600);
		JPanel content = contentJP.content();
		
		titleAndContent.add(title, BorderLayout.NORTH);
		titleAndContent.add(content, BorderLayout.CENTER);
		
		guiBunkerWindows.add(titleAndContent);
		guiBunkerWindows.setDefaultCloseOperation(guiBunkerWindows.EXIT_ON_CLOSE);
		guiBunkerWindows.setLocationRelativeTo(null);
		guiBunkerWindows.setVisible(true);
		return guiBunkerWindows;
	}
}
