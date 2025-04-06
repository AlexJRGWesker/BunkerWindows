package logic.devices;

import java.io.IOException;
import java.util.ArrayList;

import logic.services.Services;
import process.LaunchProcess;

public class Devices {
	private Services services;
	private String winmgmt;
	private boolean isActivateWinmgmt;
	private LaunchProcess launchProcess;
	
	public Devices() throws IOException, InterruptedException {
		this.services = new Services();
		this.winmgmt = "Winmgmt";
		this.isActivateWinmgmt = this.isActivatedWinmgmt();
		this.launchProcess = new LaunchProcess();
	}
	
	public ArrayList<String> filteredDevices() throws IOException, InterruptedException{
		ArrayList<String> devices = this.getDevices();
		ArrayList<String> filterDevices = new ArrayList<String>();
		String interfaceNetActual = this.interfaceNetActual();
		for(int i = 0; i < devices.size(); i++) {
			String linei = devices.get(i);
			String[] splitLinei = linei.split("<-->");
			if(splitLinei[0].equals("HIDClass")) {
				if(splitLinei[1].contains("radio")
						|| splitLinei[1].contains("Radio")
						|| splitLinei[1].contains("Synt")
						|| splitLinei[1].contains("Sint")) {
					filterDevices.add(linei);
				}
			}else if(splitLinei[0].equals("SoftwareDevice")) {
				if(splitLinei[1].contains("radio")
						|| splitLinei[1].contains("Radio")
						|| splitLinei[1].contains("Synt")
						|| splitLinei[1].contains("Sint")) {
					filterDevices.add(linei);
				}
			}else {
				if(!splitLinei[1].equals(interfaceNetActual)) {
					filterDevices.add(linei);
				}
			}
		}
		return filterDevices;
	}
	
	private ArrayList<String> getDevices() throws IOException, InterruptedException{
		ArrayList<String> devices = new ArrayList<String>();
		if(!this.isActivateWinmgmt) {
			boolean activateWorks = this.activateWinmgmt();
			if(!activateWorks) {
				this.desactivateWinmgmt();
				return null;
			}
		}
		
		String[] command = {
				"powershell","/c",
				"Get-PnPDevice",
				"| Format-List -Property Class,FriendlyName,InstanceId"
		};
		ArrayList<String> outputCommand = this.launchProcess.launchProcess(command);
		
		if(outputCommand.get(0).contains("OK")) {
			boolean write = false;
			String newAdd = "";
			for(int i = 0; i < outputCommand.size(); i++){
				String linei = outputCommand.get(i);
				String auxLinei = linei.replaceAll("\\s+","");
				if(auxLinei.contains("Class:")) {
					write = true;
				}
				if(write) {
					String[] splitLinei = linei.split("\\s+");
					if(splitLinei[0].equals("Class") && splitLinei.length >= 3) {
						if(!splitLinei[2].equals("HIDClass")
								&& !splitLinei[2].equals("Bluetooth")
								&& !splitLinei[2].equals("PrintQueue")
								&& !splitLinei[2].equals("Net")
								&& !splitLinei[2].equals("SoftwareDevice")) {
							write = false;
						}
					}else if(splitLinei[0].equals("Class") && splitLinei.length < 3) {
						write = false;
					}
					if(write) {
						String auxSplit = "";
						if(splitLinei.length > 3) {
							for(int j = 2; j < splitLinei.length; j++) {
								auxSplit += splitLinei[j];
								if(j < splitLinei.length-1) {
									auxSplit += " ";
								}
							}
						}else {
							auxSplit = splitLinei[2];
						}
						newAdd += auxSplit;
					}	
					if(auxLinei.contains("InstanceId:")) {
						write = false;
						devices.add(newAdd);
						newAdd = "";
					}else {
						if(write) {
							newAdd += "<-->";
						}
					}
				}
				
			}
		}else {
			for(int i = 0; i < outputCommand.size(); i++) {
				System.out.println(outputCommand.get(i));
			}
		}
		if(!this.isActivateWinmgmt) {
			boolean desactivateWorks = this.desactivateWinmgmt();
			if(!desactivateWorks) {
				return null;
			}
		}
		
		return devices;
	}
	
	private boolean isActivatedWinmgmt() throws IOException, InterruptedException {
		boolean isActivated = false;
		
		String startModeWinmgmt = this.services.obtainStartModeOfService(this.winmgmt);
		if(!startModeWinmgmt.equals("4")) {
			isActivated = true;
		}
		
		return isActivated;
	}
	
	private boolean activateWinmgmt() throws IOException, InterruptedException {
		boolean activateWinmgmt = false;
		String[] commandActivated = {
				"powershell","/c",
				"Set-Service","-Name",this.winmgmt,"-StartupType","Automatic"
		};
		ArrayList<String> outputCommandActivated = this.launchProcess.launchProcess(commandActivated);
		if(outputCommandActivated.get(0).contains("OK")) {
			activateWinmgmt = true;
		}
		String[] commandStart = {
				"powershell","/c",
				"net","start",this.winmgmt
		};
		ArrayList<String> outputCommandStart = this.launchProcess.launchProcess(commandStart);
		boolean startWinmgmt = false;
		if(outputCommandStart.get(0).contains("OK")) {
			startWinmgmt = true;
		}
		return (activateWinmgmt && startWinmgmt);
	}
	
	private boolean desactivateWinmgmt() throws IOException, InterruptedException {
		String[] commandStop = {
				"powershell","/c",
				"net","stop",this.winmgmt
		};
		ArrayList<String> outputCommandStop = this.launchProcess.launchProcess(commandStop);
		boolean stopWinmgmt = false;
		if(outputCommandStop.get(0).contains("OK")) {
			stopWinmgmt = true;
		}
		boolean desactivateWinmgmt = false;
		String[] commandDesactivated = {
				"powershell","/c",
				"Set-Service","-Name",this.winmgmt,"-StartupType","Disabled"
		};
		ArrayList<String> outputCommandDesactivated = this.launchProcess.launchProcess(commandDesactivated);
		if(outputCommandDesactivated.get(0).contains("OK")) {
			desactivateWinmgmt = true;
		}
		return (stopWinmgmt && desactivateWinmgmt);
	}
	
	public String interfaceNetActual() throws IOException, InterruptedException {
		if(!this.isActivateWinmgmt) {
			boolean activateWorks = this.activateWinmgmt();
			if(!activateWorks) {
				this.desactivateWinmgmt();
				return null;
			}
		}
		
		String[] command = {
				"powershell","/c",
				"Get-NetAdapter",
				"|","Format-Table","-Property","InterfaceDescription"
		};
		ArrayList<String> outputCommand = this.launchProcess.launchProcess(command);
		if(outputCommand.get(0).contains("OK")) {
			String interfaceActual = "";
			boolean write = false;
			for(int i = 0; i < outputCommand.size(); i++) {
				String linei = outputCommand.get(i);
				if(write) {
					if(!linei.equals("")) {
						interfaceActual += linei;
					}
				}
				if(linei.contains("--------")) {
					write = true;
				}
			}
			if(!this.isActivateWinmgmt) {
				boolean desactivateWorks = this.desactivateWinmgmt();
				if(!desactivateWorks) {
					return null;
				}
			}
			return interfaceActual;
		}else {
			if(!this.isActivateWinmgmt) {
				boolean desactivateWorks = this.desactivateWinmgmt();
				if(!desactivateWorks) {
					return null;
				}
			}
			return null;
		}
	}
	
	public boolean enableDevice(String instanceId) throws IOException, InterruptedException {
		if(!this.isActivateWinmgmt) {
			boolean activateWorks = this.activateWinmgmt();
			if(!activateWorks) {
				this.desactivateWinmgmt();
				return false;
			}
		}
		boolean enableSuccess = false;
		String[] command = {
				"powershell","/c",
				"Enable-PnPDevice",
				"-InstanceId",
				"'"+instanceId+"'",
				"-Confirm:$false"
		};
		ArrayList<String> enableOutput = this.launchProcess.launchProcess(command);
		if(enableOutput.get(0).contains("OK")) {
			enableSuccess = true;
		}
		if(!this.isActivateWinmgmt) {
			boolean desactivateWorks = this.desactivateWinmgmt();
			if(!desactivateWorks) {
				return false;
			}
		}
		return enableSuccess;
	}
	
	public boolean disableDevice(String instanceId) throws IOException, InterruptedException {
		if(!this.isActivateWinmgmt) {
			boolean activateWorks = this.activateWinmgmt();
			if(!activateWorks) {
				this.desactivateWinmgmt();
				return false;
			}
		}
		boolean enableSuccess = false;
		String[] command = {
				"powershell","/c",
				"Disable-PnPDevice",
				"-InstanceId",
				"'"+instanceId+"'",
				"-Confirm:$false"
		};
		ArrayList<String> enableOutput = this.launchProcess.launchProcess(command);
		if(enableOutput.get(0).contains("OK")) {
			enableSuccess = true;
		}
		if(!this.isActivateWinmgmt) {
			boolean desactivateWorks = this.desactivateWinmgmt();
			if(!desactivateWorks) {
				return false;
			}
		}
		return enableSuccess;
	}
}
