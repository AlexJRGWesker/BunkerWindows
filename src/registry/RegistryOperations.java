package registry;

import java.io.IOException;
import java.util.ArrayList;

import process.LaunchProcess;

public class RegistryOperations {
	private LaunchProcess launchProcess;
	
	public RegistryOperations() {
		this.launchProcess = new LaunchProcess();
	}
	
	public ArrayList<String> getItemProperty(String path) throws IOException, InterruptedException{
		String[] command = {
				"powershell","/c",
				"Get-ItemProperty",
				"-Path",
				path
		};
		return this.launchProcess.launchProcess(command);
	}
	
	public ArrayList<String> setPropertyOfItem(String path, String property, String value) throws IOException, InterruptedException{
		String[] command = {
				"powershell","/c",
				"Set-ItemProperty",
				"-Path",path,
				"-Name",property,
				"-Value",value
		};
		return this.launchProcess.launchProcess(command);
	}
	
	public ArrayList<String> getChildItem(String path) throws IOException, InterruptedException{
		String[] command = {
				"powershell","/c",
				"Get-ChildItem",
				"-Path",path
		};
		return this.launchProcess.launchProcess(command);
	}
}
