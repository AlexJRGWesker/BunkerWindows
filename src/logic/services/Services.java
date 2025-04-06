package logic.services;

import java.io.IOException;
import java.util.ArrayList;

import process.LaunchProcess;
import registry.RegistryOperations;
import utils.RegistryPaths;

public class Services {
	private RegistryPaths registryPaths;
	private RegistryOperations registryOperations;
	
	public Services() {
		this.registryPaths = new RegistryPaths();
		this.registryOperations = new RegistryOperations();
	}
	
	
	public ArrayList<String> obtainServices() throws IOException, InterruptedException{
		String[] command = {
				"powershell","/c",
				"Get-Service",
				"|","Select-Object -Property Name"
		};
		LaunchProcess launchProcess = new LaunchProcess();
		ArrayList<String> services = launchProcess.launchProcess(command);
		ArrayList<String> output = new ArrayList<String>();
		if(services.get(0).contains("OK")) {
			boolean write = false;
			for(int i = 0; i < services.size(); i++) {
				String linei = services.get(i);
				if(write) {
					if(!linei.equals("")) {
						output.add(this.parserService(linei));
					}
				}
				if(linei.contains("----")) {
					write = true;
				}
			}
		}else {
			output.add("ERROR");
			output.addAll(services);
		}
		ServicesWhiteList servicesWhiteList = new ServicesWhiteList();
		ArrayList<String> whiteList = servicesWhiteList.whiteListServices();
		ArrayList<String> outputFinal = new ArrayList<String>();
		for(int i = 0; i < output.size(); i++) {
			boolean find = false;
			for(int j = 0; j < whiteList.size(); j++) {
				if(output.get(i).equals(whiteList.get(j))) {
					find = true;
					break;
				}
			}
			if(!find) {
				outputFinal.add(output.get(i));
			}
		}
		return outputFinal;
	}
	
	public String obtainStartModeOfService(String service) throws IOException, InterruptedException {
		ArrayList<String> obtainServiceProperty = this.registryOperations.getItemProperty(this.registryPaths.pathServices+service);
		if(obtainServiceProperty.get(0).contains("OK")) {
			String startMode = "";
			for(int i = 0; i < obtainServiceProperty.size(); i++) {
				String linei = obtainServiceProperty.get(i);
				String[] splitLinei = linei.split("\s++");
				if(splitLinei.length == 3 && splitLinei[0].equals("Start") && splitLinei[1].equals(":")) {
					startMode = splitLinei[2];
				}
			}
			return startMode;
		}else {
			return "ERROR";
		}
	}
	
	public boolean habilitateService(String service, String value) throws IOException, InterruptedException {
		boolean habilitateWorks = false;
		
		String path = this.registryPaths.pathServices + service;
		String property = "Start";
		ArrayList<String> habilitate = this.registryOperations.setPropertyOfItem(path, property, value);
		
		if(habilitate.get(0).contains("OK")) {
			habilitateWorks = true;
		}
		
		return habilitateWorks;
	}
	
	public boolean habilitateServiceAuxiliar(String service,String value) throws IOException, InterruptedException {
		String startUpType = "";
		boolean hab = false;
		if(value.equals("4")) {
			startUpType = "Disabled";
		}else if(value.equals("3")){
			startUpType = "Manual";
		}else {
			startUpType = "Automatic";
		}
		String[] command = {
			"powershell.exe","/c",
			"Set-Service","-Name",service,"-StartupType",value
		};
		LaunchProcess launch = new LaunchProcess();
		ArrayList<String> launchCommandOutput = launch.launchProcess(command);
		if(launchCommandOutput.get(0).contains("OK")) {
			hab = true;
		}
		return hab;
	}
	
	
	public boolean deshabilitateService(String service) throws IOException, InterruptedException {
		boolean deshabilitateWorks = false;
		
		String path = this.registryPaths.pathServices + service;
		String property = "Start";
		String value = "4";
		ArrayList<String> deshabilitate = this.registryOperations.setPropertyOfItem(path, property, value);
		
		if(deshabilitate.get(0).contains("OK")) {
			deshabilitateWorks = true;
		}
		
		return deshabilitateWorks;
	}
	
	private String parserService(String service) {
		String parser = "";
		String[] splitService = service.split("\\s+");
		if(splitService.length >= 2) {
			for(int i = 0; i < splitService.length; i++) {
				parser += splitService[i];
				if(i < splitService.length-1) {
					parser += " ";
				}
			}
		}else {
			parser = splitService[0];
		}
		String[] parserSplit = parser.split("_");
		String serviceFinal = "";
		if(parserSplit.length == 2) {
			serviceFinal = parserSplit[0];
		}else {
			serviceFinal = parser;
		}
		return serviceFinal;
	}
}
