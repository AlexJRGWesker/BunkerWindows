package gui.titleJPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.components.JPanelWithBackgroundImage;
import gui.components.RigidBlockJPanel;
import utils.GUIText;

public class TitleJPanel{
	private int widht;
	private int height;
	private GUIText guiText;
	
	public TitleJPanel(int width, int height) {
		this.widht = width;
		this.height = height;
		this.guiText = new GUIText();
	}
	
	public JPanel title() {
		JPanel title = new JPanelWithBackgroundImage("/images/titile.jpg");
		title.setPreferredSize(new Dimension(this.widht, this.height));
		title.setBackground(Color.BLACK);
		RigidBlockJPanel rigidBlock = new RigidBlockJPanel(this.widht,150);
		JLabel label = new JLabel(this.guiText.madeByTitle);
		
		title.add(rigidBlock);
		title.add(label);
		return title;
	}
	
}
