package gui.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;

public class RigidBlockJPanel extends JPanel{
	
	public RigidBlockJPanel(int width, int height) {
		add(Box.createRigidArea(new Dimension(width, height)));
		setOpaque(true);
		setBackground(new Color(255,255,255,0));
	}
}
