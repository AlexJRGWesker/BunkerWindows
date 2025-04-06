package logic.tasksToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import logic.devices.Devices;
import utils.PathsConf;

public class TasksToDo_Cam {
	private PathsConf pathsConf;
	
	public TasksToDo_Cam() {
		this.pathsConf = new PathsConf();
	}
	
	public ArrayList<String> tasksToDo_Cam(String hab) throws IOException, InterruptedException{
		ArrayList<String> tasksRestore = new ArrayList<String>();
		
		//CONSENT STORE
		if(hab.equals("Habilitate")) {
			File filei = new File(this.pathsConf.confPrivacityConsentStore + "\\webcam.txt");
			FileReader fr = new FileReader(filei);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine()) != null) {
				if(!line.equals("")) {
					String[] splitLine = line.split("<-->");
					String toAdd = "Cam<-->Privacity<-->ConsentStore<-->SetValues<-->"+splitLine[0]+"<-->"+splitLine[1]+"<--><--><--><-->ConsentStore:"+splitLine[0];
					tasksRestore.add(toAdd);
				}
			}
			br.close();
			fr.close();
		}else {
			File filei = new File(this.pathsConf.confPrivacityConsentStore + "\\webcam.txt");
			FileReader fr = new FileReader(filei);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine()) != null) {
				if(!line.equals("")) {
					String[] splitLine = line.split("<-->");
					if(!splitLine[1].contains("ERROR")) {
						String toAdd = "Cam<-->Privacity<-->ConsentStore<-->SetValues<-->"+splitLine[0]+"<-->Deny<--><--><--><-->ConsentStore:"+splitLine[0];
						tasksRestore.add(toAdd);
					}
				}
			}
			br.close();
			fr.close();
		}
		
		return tasksRestore;
	}
}