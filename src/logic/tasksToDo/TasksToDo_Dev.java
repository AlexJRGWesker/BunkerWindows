package logic.tasksToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import utils.PathsConf;

public class TasksToDo_Dev {
	private PathsConf pathsConf;
	
	public TasksToDo_Dev() {
		this.pathsConf = new PathsConf();
	}
	
	public ArrayList<String> tasksToDo_Dev(String hab) throws IOException, InterruptedException{
		ArrayList<String> tasksRestore = new ArrayList<String>();
		
		//CONSENT STORE
		if(hab.equals("Habilitate")) {
			String toAdd = "Dev<-->Services<-->Services<-->SetValues<-->CDPSvc<-->3<--><--><--><-->CDPSvc";
			String toAdd2 = "Dev<-->Services<-->Services<-->SetValues<-->DeviceInstall<-->3<--><--><--><-->DeviceInstall";
			String toAdd3 = "Dev<-->Services<-->Services<-->SetValues<-->DmEnrollmentSvc<-->3<--><--><--><-->DmEnrollmentSvc";
			String toAdd4 = "Dev<-->Services<-->Services<-->SetValues<-->DeviceAssociationService<-->3<--><--><--><-->DeviceAssociationService";
			String toAdd5 = "Dev<-->Services<-->Services<-->SetValues<-->StorSvc<-->3<--><--><--><-->StorSvc";
			String toAdd6 = "Dev<-->Services<-->Services<-->SetValues<-->PlugPlay<-->3<--><--><--><-->PlugPlay";
			String toAdd7 = "Dev<-->Services<-->Services<-->SetValues<-->Winmgmt<-->3<--><--><--><-->Winmgmt";
			String toAdd8 = "Dev<-->Services<-->Services<-->SetValues<-->upnphost<-->3<--><--><--><-->upnphost";
			String toAdd9 = "Dev<-->Services<-->Services<-->SetValues<-->SSDPSRV<-->3<--><--><--><-->SSDPSRV";
			String toAdd10 = "Dev<-->Services<-->Services<-->SetValues<-->ShellHWDetection<-->3<--><--><--><-->ShellHWDetection";
			String toAdd11 = "Dev<-->Services<-->Services<-->SetValues<-->DsmSvc<-->3<--><--><--><-->DsmSvc";
			tasksRestore.add(toAdd);
			tasksRestore.add(toAdd2);
			tasksRestore.add(toAdd3);
			tasksRestore.add(toAdd4);
			tasksRestore.add(toAdd5);
			tasksRestore.add(toAdd6);
			tasksRestore.add(toAdd7);
			tasksRestore.add(toAdd8);
			tasksRestore.add(toAdd9);
			tasksRestore.add(toAdd10);
			tasksRestore.add(toAdd11);
		}else {
			String toAdd = "Dev<-->Services<-->Services<-->SetValues<-->CDPSvc<-->4<--><--><--><-->CDPSvc";
			String toAdd2 = "Dev<-->Services<-->Services<-->SetValues<-->DeviceInstall<-->4<--><--><--><-->DeviceInstall";
			String toAdd3 = "Dev<-->Services<-->Services<-->SetValues<-->DmEnrollmentSvc<-->4<--><--><--><-->DmEnrollmentSvc";
			String toAdd4 = "Dev<-->Services<-->Services<-->SetValues<-->DeviceAssociationService<-->4<--><--><--><-->DeviceAssociationService";
			String toAdd5 = "Dev<-->Services<-->Services<-->SetValues<-->StorSvc<-->4<--><--><--><-->StorSvc";
			String toAdd6 = "Dev<-->Services<-->Services<-->SetValues<-->PlugPlay<-->4<--><--><--><-->PlugPlay";
			String toAdd7 = "Dev<-->Services<-->Services<-->SetValues<-->Winmgmt<-->4<--><--><--><-->Winmgmt";
			String toAdd8 = "Dev<-->Services<-->Services<-->SetValues<-->upnphost<-->4<--><--><--><-->upnphost";
			String toAdd9 = "Dev<-->Services<-->Services<-->SetValues<-->SSDPSRV<-->4<--><--><--><-->SSDPSRV";
			String toAdd10 = "Dev<-->Services<-->Services<-->SetValues<-->ShellHWDetection<-->4<--><--><--><-->ShellHWDetection";
			String toAdd11 = "Dev<-->Services<-->Services<-->SetValues<-->DsmSvc<-->4<--><--><--><-->DsmSvc";
			tasksRestore.add(toAdd);
			tasksRestore.add(toAdd2);
			tasksRestore.add(toAdd3);
			tasksRestore.add(toAdd4);
			tasksRestore.add(toAdd5);
			tasksRestore.add(toAdd6);
			tasksRestore.add(toAdd7);
			tasksRestore.add(toAdd8);
			tasksRestore.add(toAdd9);
			tasksRestore.add(toAdd10);
			tasksRestore.add(toAdd11);
		}
		
		return tasksRestore;
	}
}