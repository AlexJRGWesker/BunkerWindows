package gui.contentJPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.components.CustomButton;
import gui.components.JPanelWithBackgroundImage;
import gui.components.RigidBlockJPanel;
import gui.mouseListener.DeshabilitateCamListener;
import gui.mouseListener.DeshabilitateDevListener;
import gui.mouseListener.DeshabilitateInstallerListener;
import gui.mouseListener.DeshabilitateMicroListener;
import gui.mouseListener.HabilitateCamListener;
import gui.mouseListener.HabilitateDevListener;
import gui.mouseListener.HabilitateInstallerListener;
import gui.mouseListener.HabilitateMicroListener;
import gui.mouseListener.InitButtonListener;
import gui.mouseListener.RestoreButtonListener;
import gui.mouseListener.UseVPNListener;
import gui.mouseListener.VPNStopButton;
import utils.GUIText;

public class ContentVPNPanel {
	private int widht;
	private int height;
	private GUIText guiText;
	private JFrame gui;
	private Process process;
	
	public ContentVPNPanel(int width, int height,JFrame gui,Process process) {
		this.widht = width;
		this.height = height;
		this.guiText = new GUIText();
		this.gui = gui;
		this.process = process;
	}
	
	public JPanel content() {
		JPanel content = new JPanelWithBackgroundImage("/images/content.jpg");
		content.setPreferredSize(new Dimension(this.widht, this.height));
		//content.setBackground(Color.BLACK);
		content.add(new RigidBlockJPanel(this.widht,10));
		JLabel labelRecomend = new JLabel("The VPN is running");
		labelRecomend.setForeground(Color.WHITE);
		content.add(labelRecomend);
		CustomButton stopVPN = new CustomButton("Stop VPN",100,20);
		VPNStopButton vpnStopButton = new VPNStopButton(stopVPN,this.gui,this.process);
		stopVPN.setMouseListener(vpnStopButton);
		content.add(stopVPN);
		
		return content;
	}
	
}