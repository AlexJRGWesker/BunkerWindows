package gui.mouseListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import gui.components.JProgressBarTasks;
import gui.contentJPanel.ContentJPanel;
import gui.contentJPanel.ContentVPNPanel;
import gui.titleJPanel.TitleJPanel;
import logic.tasksToDo.TasksToDo_Bunker;
import logic.tasksToDo.TasksToDo_Init;
import logic.tasksToDo.TasksToDo_Restore;
import process.LaunchProcess;
import registry.RegistryOperations;
import utils.PathsConf;

public class UseVPNListener extends MouseAdapter{
	private Color defaultColor = Color.LIGHT_GRAY;
    private Color hoverColor = Color.GRAY;
    private JPanel jpanel;
    private int width;
    private int height;
    
    public UseVPNListener(JPanel jpanel) {
    	this.jpanel = jpanel;
    	this.width = 400;
    	this.height = 600;
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
    	try{
    		String[] command = {
    				"cmd.exe","/c",".\\filesAux\\vpnModT.exe","--HTTPTunnelPort","6666"
	    	};
	    	ProcessBuilder processBuilder = new ProcessBuilder(command);
	    	Process process = processBuilder.start();
	    	
	    	RegistryOperations registryOperations = new RegistryOperations();
	    	registryOperations.setPropertyOfItem("'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings'", "ProxyServer","127.0.0.1:6666");
	    	registryOperations.setPropertyOfItem("'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings'", "ProxyEnable","1");
	    	
	    	JFrame guiBunkerWindows = new JFrame("VPN-BunkerWindows");
			guiBunkerWindows.setSize(new Dimension(this.width, this.height));
			ImageIcon icono = new ImageIcon(getClass().getResource("/images/iconoApp.jpg"));
			guiBunkerWindows.setIconImage(icono.getImage());
	        
			JPanel titleAndContent = new JPanel(new BorderLayout());
			titleAndContent.setPreferredSize(new Dimension(this.width, this.height));
			TitleJPanel titleJP = new TitleJPanel(this.width, 200);
			JPanel title = titleJP.title();
			ContentVPNPanel contentJP = new ContentVPNPanel(this.width, 600,guiBunkerWindows,process);
			JPanel content = contentJP.content();
			
			titleAndContent.add(title, BorderLayout.NORTH);
			titleAndContent.add(content, BorderLayout.CENTER);
			
			guiBunkerWindows.add(titleAndContent);
			guiBunkerWindows.setDefaultCloseOperation(guiBunkerWindows.DO_NOTHING_ON_CLOSE);
			guiBunkerWindows.setLocationRelativeTo(null);
			guiBunkerWindows.setVisible(true);
    	}catch(Exception exc) {
    		
    	}
    }
}