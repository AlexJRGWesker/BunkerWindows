package logic.privacity;

import java.io.IOException;
import java.util.ArrayList;

import registry.RegistryOperations;
import utils.RegistryPaths;

public class GeneralPrivacity {
	private RegistryOperations registryOperations;
	private RegistryPaths registryPaths;
	
	public GeneralPrivacity() {
		this.registryOperations = new RegistryOperations();
		this.registryPaths = new RegistryPaths();
	}
	
	public String getValues() throws IOException, InterruptedException {
		String values = "";
		
		String advertisingInfoValue = this.getAdvertisingInfo();
		String languageRelatedValue = this.getLanguageRelated();
		String contentDeliveryManager = this.getContentDelivery();
		String accountNotifications = this.getAccountNotifications();
		String startTrackProg = this.getStartTrackPrograms();
		String startTrackDoc = this.getStartTrackDocs();
		
		values += this.registryPaths.advertisingInfoPath + "<-->" + "Enabled<-->" + advertisingInfoValue + "\n";
		values += this.registryPaths.languajeRelatedPath + "<-->" + "HttpAcceptLanguageOptOut<-->" + languageRelatedValue + "\n";
		values += this.registryPaths.contentDeliveryPath + "<-->" + contentDeliveryManager + "\n";
		values += this.registryPaths.accountNotifications + "<-->" + "EnableAccountNotifications<-->" + accountNotifications + "\n";
		values += this.registryPaths.trackProgmPaths + "<-->" + "Start_TrackProgs<-->" + startTrackProg + "\n";
		values += this.registryPaths.trackDocsPath + "<-->" + "Start_TrackDocs<-->" + startTrackDoc + "\n";
		
		return values;
	}
	
	public boolean setValue(String path, String property, String value) throws IOException, InterruptedException {
		boolean setSuccess = false;
		
		ArrayList<String> output = this.registryOperations.setPropertyOfItem(path, property, value);
		if(output.get(0).contains("OK")) {
			setSuccess = true;
		}
		
		return setSuccess;
	}
	
	private String getAdvertisingInfo() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty(this.registryPaths.advertisingInfoPath);
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("Enabled")) {
					value = splitLinei[2];
				}
			}
		}
		return value;
	}
	
	private String getLanguageRelated() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty("'"+this.registryPaths.languajeRelatedPath+"'");
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("HttpAcceptLanguageOptOut")) {
					value = splitLinei[2];
				}
			}
		}
		return value;
	}
	
	private String getAccountNotifications() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty("'"+this.registryPaths.accountNotifications+"'");
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("EnableAccountNotifications")) {
					value = splitLinei[2];
				}
			}
		}
		return value;
	}
	
	private String getStartTrackPrograms() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty("'"+this.registryPaths.trackProgmPaths+"'");
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("Start_TrackProgs")) {
					value = splitLinei[2];
				}
			}
		}
		return value;
	}
	
	private String getStartTrackDocs() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty("'"+this.registryPaths.trackDocsPath+"'");
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("Start_TrackDocs")) {
					value = splitLinei[2];
				}
			}
		}
		return value;
	}
	
	private String getContentDelivery() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty(this.registryPaths.contentDeliveryPath);
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].contains("SubscribedContent")) {
					value += splitLinei[0]+":"+splitLinei[2]+"<>";
				}
			}
		}
		String[] splitvalue = value.split("<>");
		String valueFinal = "";
		for(int i = 0; i < splitvalue.length; i++) {
			valueFinal += splitvalue[i];
			if(i < splitvalue.length-1) {
				valueFinal += "<>";
			}
		}
		return valueFinal;
	}
}
