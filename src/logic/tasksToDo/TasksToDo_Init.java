package logic.tasksToDo;

import java.io.IOException;
import java.util.ArrayList;

import logic.privacity.ConsentStore;
import logic.privacity.DiagnosticsPrivacity;
import logic.privacity.GeneralPrivacity;
import logic.privacity.SearchPrivacity;
import logic.services.Services;

public class TasksToDo_Init {
	public TasksToDo_Init() {
		
	}
	
	public ArrayList<String> getTasksToDo_Init() throws IOException, InterruptedException{
		ConsentStore consentStore = new ConsentStore();
		DiagnosticsPrivacity diagnosticsPrivacity = new DiagnosticsPrivacity();
		GeneralPrivacity generalPrivacity = new GeneralPrivacity();
		SearchPrivacity searchPrivacity = new SearchPrivacity();
		
		ArrayList<String> getTasksTD_Init = new ArrayList<String>();
		//CONSENT STORE
		ArrayList< ArrayList<String> > pathsConsentStore = consentStore.obtainPaths();
		for(int i = 0; i < pathsConsentStore.size(); i++) {
			ArrayList<String> pathsConsentStorei = pathsConsentStore.get(i);
			for(int j = 0; j < pathsConsentStorei.size(); j++) {
				String pathij = pathsConsentStorei.get(j);
				if(j == 0 || pathij.contains("HKLM:\\")) {
					String[] rootSplit = pathij.split("\\\\");
					String root = rootSplit[rootSplit.length-1];
					String toAdd = "Init<-->Privacity<-->ConsentStore<-->GetValues<-->"+pathij+"<-->"+root+"<--><--><--><-->ConsentStore:"+pathij;
					getTasksTD_Init.add(toAdd);	
				}else{
					String[] rootSplit = pathij.split("\\\\");
					String root = rootSplit[rootSplit.length-2];
					String toAdd = "Init<-->Privacity<-->ConsentStore<-->GetValues<-->"+pathij+"<-->"+root+"<--><--><--><-->ConsentStore:"+pathij;
					getTasksTD_Init.add(toAdd);
				}
			}
		}
		//DIAGNOSTICS PRIVACITY
		String toAddDiagnostics = "Init<-->Privacity<-->Diagnostics<-->GetValues<--><--><--><--><--><-->DiagnosticsPrivacity";
		getTasksTD_Init.add(toAddDiagnostics);
		
		//GENERAL PRIVACITY
		String toAddGeneral = "Init<-->Privacity<-->General<-->GetValues<--><--><--><--><--><-->GeneralPrivacity";
		getTasksTD_Init.add(toAddGeneral);
		
		//SEARCH PRIVACITY
		String toAddSearch = "Init<-->Privacity<-->Search<-->GetValues<--><--><--><--><--><-->SearchPrivacity";
		getTasksTD_Init.add(toAddSearch);
		
		Services services = new Services();
		ArrayList<String> servicesNames = services.obtainServices();
		for(int i = 0; i < servicesNames.size(); i++) {
			String toAddServices = "Init<-->Services<-->Services<-->GetValues<-->"+servicesNames.get(i)+"<--><--><--><--><-->Services:"+servicesNames.get(i);
			getTasksTD_Init.add(toAddServices);
		}
		
		String toAddFirewall = "Init<-->Firewall<-->Firewall<-->Exports<--><--><--><--><--><-->Firewall_Export";
		getTasksTD_Init.add(toAddFirewall);
		
		
		return getTasksTD_Init;
	}
}
