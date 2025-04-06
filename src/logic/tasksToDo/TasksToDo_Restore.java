package logic.tasksToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import logic.devices.Devices;
import utils.PathsConf;

public class TasksToDo_Restore {
	private PathsConf pathsConf;
	
	public TasksToDo_Restore() {
		this.pathsConf = new PathsConf();
	}
	
	public ArrayList<String> tasksToDo_Restore() throws IOException, InterruptedException{
		ArrayList<String> tasksRestore = new ArrayList<String>();
		
		//CONSENT STORE
		File dirConsentStore = new File(this.pathsConf.confPrivacityConsentStore);
		if(dirConsentStore.exists()) {
			File[] filesConsentStore = dirConsentStore.listFiles();
			for(int i = 0; i < filesConsentStore.length; i++) {
				File filei = filesConsentStore[i];
				FileReader fr = new FileReader(filei);
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				while((line = br.readLine()) != null) {
					if(!line.equals("")) {
						String[] splitLine = line.split("<-->");
						String toAdd = "Restore<-->Privacity<-->ConsentStore<-->SetValues<-->"+splitLine[0]+"<-->"+splitLine[1]+"<--><--><--><-->ConsentStore:"+splitLine[0];
						tasksRestore.add(toAdd);
					}
				}
				br.close();
				fr.close();
			}
		}
		
		//GENERAL PRIVACITY
		/**
		File generalPrivacity = new File(this.pathsConf.confPrivacityGeneral + "\\general.txt");
		FileReader frGP = new FileReader(generalPrivacity);
		BufferedReader brGP = new BufferedReader(frGP);
		String lineGP = "";
		while((lineGP = brGP.readLine()) != null) {
			if(!lineGP.equals("")) {
				String[] splitLine = lineGP.split("<-->");
				if(splitLine.length == 3) {
					String toAdd = "Restore<-->Privacity<-->General<-->SetValues<-->"+splitLine[0]+"<-->"+splitLine[1]+"<-->"+splitLine[2]+"<--><--><-->General:"+splitLine[0];
					tasksRestore.add(toAdd);
				}else {
					if(splitLine.length == 2) {
						String pathij = splitLine[0];
						String[] splitSubscribed = splitLine[1].split("<>");
						for(int i = 0; i < splitSubscribed.length; i++) {
							String[] spliti = splitSubscribed[i].split(":");
							String toAdd = "Restore<-->Privacity<-->General<-->SetValues<-->"+pathij+"<-->"+spliti[0]+"<-->"+spliti[1]+"<--><--><-->General:"+spliti[0];
							tasksRestore.add(toAdd);			
						}
					}
				}
			}
		}
		brGP.close();
		frGP.close();
		*/
		//SEARCH PRIVACITY
		File searchPrivacity = new File(this.pathsConf.confPrivacitySearch + "\\search.txt");
		FileReader frSP = new FileReader(searchPrivacity);
		BufferedReader brSP = new BufferedReader(frSP);
		String lineSP = "";
		while((lineSP = brSP.readLine()) != null) {
			if(!lineSP.equals("")) {
				String[] splitLine = lineSP.split(":");
				if(splitLine.length == 2) {
					String toAdd = "Restore<-->Privacity<-->Search<-->SetValues<-->"+splitLine[0]+"<-->"+splitLine[1]+"<--><--><--><-->Search:"+splitLine[0];
					tasksRestore.add(toAdd);
				}
			}
		}
		brSP.close();
		frSP.close();
		
		//DIAGNOSTICS PRIVACITY
		File diagnosticsPrivacity = new File(this.pathsConf.confPrivacityDiagnostics + "\\diagnostics.txt");
		FileReader frDP = new FileReader(diagnosticsPrivacity);
		BufferedReader brDP = new BufferedReader(frDP);
		String lineDP = "";
		while((lineDP = brDP.readLine()) != null) {
			if(!lineDP.equals("")) {
				String[] splitLine = lineDP.split("<-->");
				if(splitLine.length == 3) {
					String toAdd = "Restore<-->Privacity<-->Diagnostics<-->SetValues<-->"+splitLine[0]+"<-->"+splitLine[1]+"<-->"+splitLine[2]+"<--><--><-->Diagnostics:"+splitLine[0];
					tasksRestore.add(toAdd);
				}else {
					if(splitLine.length == 2) {
						String pathij = splitLine[0];
						String[] splitSubscribed = splitLine[1].split("<>");
						for(int i = 0; i < splitSubscribed.length; i++) {
							String[] spliti = splitSubscribed[i].split(":");
							String toAdd = "Restore<-->Privacity<-->Diagnostics<-->SetValues<-->"+pathij+"<-->"+spliti[0]+"<-->"+spliti[1]+"<--><--><-->Diagnostics:"+spliti[0];
							tasksRestore.add(toAdd);			
						}
					}
				}
			}
		}
		brDP.close();
		frDP.close();
		
		//SERVICES
		File servicesFile = new File(this.pathsConf.confServices + "\\services.txt");
		FileReader frS = new FileReader(servicesFile);
		BufferedReader brS = new BufferedReader(frS);
		String lineS = "";
		while((lineS = brS.readLine()) != null) {
			if(!lineS.equals("")) {
				String[] splitLineS = lineS.split("<-->");
				String toAdd = "Restore<-->Services<-->Services<-->SetValues<-->"+splitLineS[0]+"<-->"+splitLineS[1]+"<--><--><--><-->Services:"+splitLineS[0];
				tasksRestore.add(toAdd);
			}
		}
		brS.close();
		frS.close();
		
		//FIREWALL
		String toAddFirewall = "Restore<-->Firewall<-->Firewall<-->Import<--><--><--><--><--><-->Firewall_Import";
		tasksRestore.add(toAddFirewall);
		
		//DEVICES
		Devices devices = new Devices();
		ArrayList<String> devicesList = devices.filteredDevices();
		for(int i = 0; i < devicesList.size(); i++) {
			String splitDevicei = devicesList.get(i).split("<-->")[2];
			String nameDevicei = devicesList.get(i).split("<-->")[1];
			String toAddDevices = "Restore<-->Devices<-->Devices<-->Habilitate<-->"+splitDevicei+"<--><--><--><--><-->Devices:"+nameDevicei;
			tasksRestore.add(toAddDevices);
		}
		
		return tasksRestore;
	}
}
