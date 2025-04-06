package logic.tasksToDo;

import java.io.IOException;
import java.util.ArrayList;

import utils.PathsConf;

public class TasksToDo_Installer {
	private PathsConf pathsConf;
	
	public TasksToDo_Installer() {
		this.pathsConf = new PathsConf();
	}
	
	public ArrayList<String> tasksToDo_Installer(String hab) throws IOException, InterruptedException{
		ArrayList<String> tasksRestore = new ArrayList<String>();
		
		//CONSENT STORE
		if(hab.equals("Habilitate")) {
			String toAdd = "Installer<-->Services<-->Services<-->SetValues<-->msiserver<-->2<--><--><--><-->CDPSvc";
			String toAdd2 = "Installer<-->Services<-->Services<-->SetValues<-->AxInstSV<-->2<--><--><--><-->AxInstSV";
			String toAdd3 = "Installer<-->Services<-->Services<-->SetValues<-->PushToInstall<-->2<--><--><--><-->PushToInstall";
			String toAdd4 = "Installer<-->Services<-->Services<-->SetValues<-->InstallService<-->2<--><--><--><-->InstallService";
			String toAdd5 = "Installer<-->Services<-->Services<-->SetValues<-->TrustedInstaller<-->2<--><--><--><-->TrustedInstaller";
			String toAdd6 = "Installer<-->Services<-->Services<-->SetValues<-->Winmgmt<-->2<--><--><--><-->Winmgmt";
			tasksRestore.add(toAdd);
			tasksRestore.add(toAdd2);
			tasksRestore.add(toAdd3);
			tasksRestore.add(toAdd4);
			tasksRestore.add(toAdd5);
			tasksRestore.add(toAdd6);
		}else {
			String toAdd = "Installer<-->Services<-->Services<-->SetValues<-->msiserver<-->4<--><--><--><-->CDPSvc";
			String toAdd2 = "Installer<-->Services<-->Services<-->SetValues<-->AxInstSV<-->4<--><--><--><-->AxInstSV";
			String toAdd3 = "Installer<-->Services<-->Services<-->SetValues<-->PushToInstall<-->4<--><--><--><-->PushToInstall";
			String toAdd4 = "Installer<-->Services<-->Services<-->SetValues<-->InstallService<-->4<--><--><--><-->InstallService";
			String toAdd5 = "Installer<-->Services<-->Services<-->SetValues<-->TrustedInstaller<-->4<--><--><--><-->TrustedInstaller";
			String toAdd6 = "Installer<-->Services<-->Services<-->SetValues<-->Winmgmt<-->4<--><--><--><-->Winmgmt";
			tasksRestore.add(toAdd);
			tasksRestore.add(toAdd2);
			tasksRestore.add(toAdd3);
			tasksRestore.add(toAdd4);
			tasksRestore.add(toAdd5);
			tasksRestore.add(toAdd6);
		}
		/**for(int i = 0; i < tasksRestore.size(); i++) {
			System.out.println(tasksRestore.get(i));
		}*/
		
		return tasksRestore;
	}
}