package logic.privacity;

import java.io.IOException;
import java.util.ArrayList;

import process.LaunchProcess;
import registry.RegistryOperations;
import utils.RegistryPaths;

public class ConsentStore {
	RegistryPaths registryPaths;
	LaunchProcess launchProcess;
	RegistryOperations registryOperations;
	
	public ConsentStore() {
		this.registryPaths = new RegistryPaths();
		this.launchProcess = new LaunchProcess();
		this.registryOperations = new RegistryOperations();
	}
	
	public ArrayList< ArrayList<String> > obtainPaths() throws IOException, InterruptedException{
		ArrayList< ArrayList<String> > paths = new ArrayList< ArrayList<String> >();
		
		ArrayList<String> pathsHKCUChildItem = this.registryOperations.getChildItem(this.registryPaths.consentStoreHKCUPath);
		ArrayList<String> pathsHKCU = new ArrayList<String>();
		boolean writeHKCU = false;
		for(int i = 0; i < pathsHKCUChildItem.size(); i++) {
			String linei = pathsHKCUChildItem.get(i);
			if(writeHKCU) {
				if(!linei.equals("")) {
					String[] splitLinei = linei.split("\\s+");
					String pathToadd = this.registryPaths.consentStoreHKCUPath + "\\" + splitLinei[0];
					pathsHKCU.add(pathToadd);
				}
			}
			if(linei.contains("-----")) {
				writeHKCU = true;
			}
		}
		ArrayList< ArrayList<String> > pathsHKCUwithChildItems = new ArrayList< ArrayList<String> >();
		for(int i = 0; i < pathsHKCU.size(); i++) {
			String linei = pathsHKCU.get(i);
			ArrayList<String> childItemsPathsHKCU = this.getChildItemForConsentStore(linei);
			ArrayList<String> childItemsi = new ArrayList<String>();
			boolean writeChildItemHKCU = false;
			String childItemActual = "";
			childItemsi.add(linei);
			for(int j = 1; j < childItemsPathsHKCU.size(); j++) {
				String linej = childItemsPathsHKCU.get(j);
				if(!linej.equals("")) {
					String[] splitLinej = linej.split("\\s+");
					if(splitLinej.length>2 && splitLinej[0].equals("Name") && splitLinej[1].equals(":")) {
						childItemActual += splitLinej[2];
					}else {
						if(splitLinej.length == 2) {
							childItemActual += splitLinej[1];
						}else {
							childItemActual += splitLinej[0];
						}
					}
				}else {
					if(!childItemActual.equals("")) {
						String replaceChildItem = childItemActual.replace("HKEY_CURRENT_USER", "HKCU:");
						childItemsi.add(replaceChildItem);
						childItemActual = "";
					}
				}
			}
			pathsHKCUwithChildItems.add(childItemsi);
			//System.out.println(linei);
		}
		
		ArrayList<String> pathsHKLMChildItem = this.registryOperations.getChildItem(this.registryPaths.consentStoreHKLMPath);
		ArrayList<String> pathsHKLM = new ArrayList<String>();
		boolean writeHKLM = false;
		for(int i = 0; i < pathsHKLMChildItem.size(); i++) {
			String linei = pathsHKLMChildItem.get(i);
			if(writeHKLM) {
				if(!linei.equals("")) {
					String[] splitLinei = linei.split("\\s+");
					String pathToadd = this.registryPaths.consentStoreHKLMPath + "\\" + splitLinei[0];
					pathsHKLM.add(pathToadd);
				}
			}
			if(linei.contains("-----")) {
				writeHKLM = true;
			}
		}
		
		for(int i = 0; i < pathsHKLM.size(); i++) {
			String linei = pathsHKLM.get(i);
			String[] splitSepi = linei.split("\\\\");
			String lastSepi = splitSepi[splitSepi.length-1];
			int index = -1;
			boolean find = false;
			for(int j = 0; j < pathsHKCUwithChildItems.size(); j++) {
				String linej = pathsHKCUwithChildItems.get(j).get(0);
				String[] splitSepj = linej.split("\\\\");
				String lastSepj = splitSepj[splitSepj.length-1];
				if(lastSepj.equals(lastSepi)) {
					index = j;
					break;
				}
			}
			if(index != -1) {
				pathsHKCUwithChildItems.get(index).add(linei);
			}else {
				ArrayList<String> ali = new ArrayList<String>();
				ali.add(linei);
				pathsHKCUwithChildItems.add(ali);
			}
			//System.out.println(linei);
		}
		/**
		for(int i = 0; i < pathsHKCUwithChildItems.size(); i++) {
			ArrayList<String> ali = pathsHKCUwithChildItems.get(i);
			for(int j = 0; j < ali.size(); j++) {
				System.out.println(ali.get(j));
			}
			System.out.println("-----------------------------");
		}
		*/
		return pathsHKCUwithChildItems;
	}
	
	public ArrayList<String> getChildItemForConsentStore(String path) throws IOException, InterruptedException{
		String[] command = {
				"powershell","/c",
				"Get-ChildItem",
				"-Path",path,
				"|","Format-List","-Property","Name"
		};
		return this.launchProcess.launchProcess(command);
	}
	
	
	public String getValueFromPath(String path) throws IOException, InterruptedException {
		String value = "";
		ArrayList<String> getOutput = this.registryOperations.getItemProperty(path);
		for(int i = 0; i < getOutput.size(); i++) {
			String linei = getOutput.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("Value")) {
					value = splitLinei[2];
				}
			}
			//System.out.println(linei);
		}
		if(value.equals("")) {
			value = "ERROR:Probably have not for this path.";
		}
		return value;
	}
	
	public boolean setValueToPath(String path, String value) throws IOException, InterruptedException {
		boolean setSuccess = false;
		
		String name = "Value";
		ArrayList<String> setOutput = this.registryOperations.setPropertyOfItem(path, name, value);
		if(setOutput.get(0).contains("OK")) {
			setSuccess = true;
		}
		return setSuccess;
	}
}
