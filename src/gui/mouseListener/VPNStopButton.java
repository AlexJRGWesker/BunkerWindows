package gui.mouseListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.contentJPanel.ContentVPNPanel;
import gui.titleJPanel.TitleJPanel;
import registry.RegistryOperations;

public class VPNStopButton extends MouseAdapter{
	private Color defaultColor = Color.LIGHT_GRAY;
    private Color hoverColor = Color.GRAY;
    private JPanel jpanel;
    private int width;
    private int height;
    private JFrame gui;
    private Process process;
    
    public VPNStopButton(JPanel jpanel,JFrame gui,Process process) {
    	this.jpanel = jpanel;
    	this.width = 400;
    	this.height = 600;
    	this.gui = gui;
    	this.process = process;
    }
    
	@Override
    public void mouseEntered(MouseEvent e) {
		jpanel.setBackground(hoverColor);
		jpanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
    	jpanel.setBackground(defaultColor);
    	jpanel.setCursor(Cursor.getDefaultCursor());
    }

    public void mouseClicked(MouseEvent e) {
    	RegistryOperations registryOperations = new RegistryOperations();
    	try {
			registryOperations.setPropertyOfItem("'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings'", "ProxyEnable","0");
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	this.process.destroyForcibly();
    	this.gui.dispose();
    }
}