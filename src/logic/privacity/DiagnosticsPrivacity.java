package logic.privacity;

import java.io.IOException;
import java.util.ArrayList;

import registry.RegistryOperations;
import utils.RegistryPaths;

public class DiagnosticsPrivacity {
	private RegistryPaths registryPaths;
	private RegistryOperations registryOperations;
	
	public DiagnosticsPrivacity() {
		this.registryOperations = new RegistryOperations();
		this.registryPaths = new RegistryPaths();
	}
	
	public String getValues() throws IOException, InterruptedException {
		String values = "";
		
		String privatyPolicy= this.getPrivacyPolicy();
		String harvestContacts = this.getHarvestContacts();
		String inputPersonalization = this.getInputPersonalization();
		String tailoredExpHKCU = this.getTailoredExpHKCU();
		String tailoredExpHKLM = this.getTailoredExpHKLM();
		String showedAtToastLevel = this.getShowedAtToastLevel();
		String assistance = this.getAssistance();
		
		values += this.registryPaths.privacyPolicy + "<-->" + "AcceptedPrivacyPolicy<-->" + privatyPolicy + "\n";
		values += this.registryPaths.harvestContacts + "<-->" + "HarvestContacts<-->" + harvestContacts + "\n";
		values += this.registryPaths.inputPersonalization + "<-->" + inputPersonalization + "\n";
		values += this.registryPaths.tailoredExpHKCU + "<-->" + "TailoredExperiencesWithDiagnosticDataEnabled<-->" + tailoredExpHKCU + "\n";
		values += this.registryPaths.tailoredExpHKLM + "<-->" + "TailoredExperiencesWithDiagnosticDataEnabled<-->" + tailoredExpHKLM + "\n";
		values += this.registryPaths.showedAtToastLevel + "<-->" + "ShowedToastAtLevel<-->" + showedAtToastLevel + "\n";
		values += this.registryPaths.assistanceSett + "<-->" + assistance + "\n";
		
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
	
	private String getPrivacyPolicy() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty(this.registryPaths.privacyPolicy);
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("AcceptedPrivacyPolicy")) {
					value = splitLinei[2];
				}
			}
		}
		return value;
	}
	
	private String getHarvestContacts() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty(this.registryPaths.harvestContacts);
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("HarvestContacts")) {
					value = splitLinei[2];
				}
			}
		}
		return value;
	}
	
	private String getTailoredExpHKCU() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty(this.registryPaths.tailoredExpHKCU);
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("TailoredExperiencesWithDiagnosticDataEnabled")) {
					value = splitLinei[2];
				}
			}
		}
		return value;
	}
	
	private String getShowedAtToastLevel() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty(this.registryPaths.showedAtToastLevel);
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("ShowedToastAtLevel")) {
					value = splitLinei[2];
				}
			}
		}
		return value;
	}
	
	private String getTailoredExpHKLM() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty(this.registryPaths.tailoredExpHKLM);
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("TailoredExperiencesWithDiagnosticDataEnabled")) {
					value = splitLinei[2];
				}
			}
		}
		return value;
	}
	
	private String getInputPersonalization() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty(this.registryPaths.inputPersonalization);
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("RestrictImplicitInkCollection")) {
					value += splitLinei[0]+":"+splitLinei[2]+"<>";
				}
				if(splitLinei[0].equals("RestrictImplicitTextCollection")) {
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
	
	private String getAssistance() throws IOException, InterruptedException {
		String value = "";
		
		ArrayList<String> getValue = this.registryOperations.getItemProperty(this.registryPaths.assistanceSett);
		for(int i = 0; i < getValue.size(); i++) {
			String linei = getValue.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("GlobalImplicitFeedback")) {
					value += splitLinei[0]+":"+splitLinei[2]+"<>";
				}
				if(splitLinei[0].equals("GlobalOnlineAssist")) {
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
