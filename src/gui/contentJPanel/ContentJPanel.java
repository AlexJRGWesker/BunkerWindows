package gui.contentJPanel;

import java.awt.Color;
import java.awt.Dimension;

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
import gui.mouseListener.UpdateButtonListener;
import gui.mouseListener.UseVPNListener;
import utils.GUIText;

public class ContentJPanel{
	private int widht;
	private int height;
	private GUIText guiText;
	
	public ContentJPanel(int width, int height) {
		this.widht = width;
		this.height = height;
		this.guiText = new GUIText();
	}
	
	public JPanel content() {
		JPanel content = new JPanelWithBackgroundImage("/images/content.jpg");
		content.setPreferredSize(new Dimension(this.widht, this.height));
		//content.setBackground(Color.BLACK);
		content.add(new RigidBlockJPanel(this.widht,10));
		JLabel labelRecomend = new JLabel(this.guiText.recomendation);
		labelRecomend.setForeground(Color.WHITE);
		content.add(labelRecomend);
		content.add(new RigidBlockJPanel(this.widht,5));
		CustomButton customButton = new CustomButton(this.guiText.buttonInit,100,20);
		InitButtonListener initAdapter = new InitButtonListener(customButton);
		customButton.setMouseListener(initAdapter);
		content.add(customButton);
		CustomButton restoreButton = new CustomButton(this.guiText.buttonRestore,100,20);
		RestoreButtonListener restoreAdapter = new RestoreButtonListener(restoreButton);
		restoreButton.setMouseListener(restoreAdapter);
		content.add(restoreButton);
		/**
		CustomButton updateButton = new CustomButton(this.guiText.buttonUpdate,100,20);
		UpdateButtonListener updateAdapter = new UpdateButtonListener(updateButton);
		updateButton.setMouseListener(updateAdapter);
		content.add(updateButton);
		*/
		
		content.add(new RigidBlockJPanel(this.widht,10));
		JLabel labelInfoNextButtons = new JLabel("The next buttons is for habilitate / deshabilitate some characteristics on case the bunker will be active.");
		labelInfoNextButtons.setForeground(Color.WHITE);
		content.add(labelInfoNextButtons);
		
		
		content.add(new RigidBlockJPanel(this.widht,10));
		JLabel activMicroJL = new JLabel("Habilitate / Deshabilitate microphone: ");
		activMicroJL.setForeground(Color.WHITE);
		CustomButton habMicroButton = new CustomButton("Habilitate Microphone",150,20);
		CustomButton deshabMicroButton = new CustomButton("Deshabilitate Microphone",150,20);
		HabilitateMicroListener microAdapter = new HabilitateMicroListener(habMicroButton);
		DeshabilitateMicroListener microAdapterD = new DeshabilitateMicroListener(deshabMicroButton);
		habMicroButton.setMouseListener(microAdapter);
		deshabMicroButton.setMouseListener(microAdapterD);
		content.add(activMicroJL);
		content.add(habMicroButton);
		content.add(deshabMicroButton);
		
		content.add(new RigidBlockJPanel(this.widht,10));
		JLabel activCam = new JLabel("Habilitate / Deshabilitate camera:        ");
		activCam.setForeground(Color.WHITE);
		CustomButton habCamButton = new CustomButton("Habilitate Camera",150,20);
		CustomButton deshabCamButton = new CustomButton("Deshabilitate Camera",150,20);
		HabilitateCamListener camAdapter = new HabilitateCamListener(habCamButton);
		DeshabilitateCamListener camAdapterD = new DeshabilitateCamListener(deshabCamButton);
		habCamButton.setMouseListener(camAdapter);
		deshabCamButton.setMouseListener(camAdapterD);
		content.add(activCam);
		content.add(habCamButton);
		content.add(deshabCamButton);
		
		content.add(new RigidBlockJPanel(this.widht,10));
		JLabel activDevJL = new JLabel("Habilitate / Deshabilitate devs:            ");
		activDevJL.setForeground(Color.WHITE);
		CustomButton habDevButton = new CustomButton("Habilitate Devices",150,20);
		CustomButton deshabDevButton = new CustomButton("Deshabilitate Devices",150,20);
		HabilitateDevListener devAdapter = new HabilitateDevListener(habDevButton);
		DeshabilitateDevListener devAdapterD = new DeshabilitateDevListener(deshabDevButton);
		habDevButton.setMouseListener(devAdapter);
		deshabDevButton.setMouseListener(devAdapterD);
		content.add(activDevJL);
		content.add(habDevButton);
		content.add(deshabDevButton);
		
		content.add(new RigidBlockJPanel(this.widht,10));
		JLabel activInstallerJL = new JLabel("Habilitate / Deshabilitate installer:        ");
		activInstallerJL.setForeground(Color.WHITE);
		CustomButton habInstallerButton = new CustomButton("Habilitate Installer",150,20);
		CustomButton deshabInstallerButton = new CustomButton("Deshabilitate Installer",150,20);
		HabilitateInstallerListener installerAdapter = new HabilitateInstallerListener(habInstallerButton);
		DeshabilitateInstallerListener installerAdapterD = new DeshabilitateInstallerListener(deshabInstallerButton);
		habInstallerButton.setMouseListener(installerAdapter);
		deshabInstallerButton.setMouseListener(installerAdapterD);
		content.add(activInstallerJL);
		content.add(habInstallerButton);
		content.add(deshabInstallerButton);
		
		content.add(new RigidBlockJPanel(this.widht,20));
		JLabel labelVPN = new JLabel("The next buttons is for use a VPN for use Internet.");
		labelVPN.setForeground(Color.WHITE);
		content.add(labelVPN);
		content.add(new RigidBlockJPanel(this.widht,5));
		CustomButton useVPN = new CustomButton("Use VPN for Internet",150,20);
		UseVPNListener useVPNListener = new UseVPNListener(useVPN);
		useVPN.setMouseListener(useVPNListener);
		content.add(useVPN);
		
		content.add(new RigidBlockJPanel(this.widht,80));
		JLabel labelFinal = new JLabel(this.guiText.madeByteContent);
		labelFinal.setForeground(Color.WHITE);
		content.add(labelFinal);
		
		return content;
	}
	
}
