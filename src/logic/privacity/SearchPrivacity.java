package logic.privacity;

import java.io.IOException;
import java.util.ArrayList;

import registry.RegistryOperations;

public class SearchPrivacity {
	private RegistryOperations registryOperations;
	private String pathSearchPrivacity;
	
	public SearchPrivacity() {
		this.registryOperations = new RegistryOperations();
		this.pathSearchPrivacity = "HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\SearchSettings";
	}
	
	public String getValues() throws IOException, InterruptedException {
		String values = "";
		
		ArrayList<String> valuesOutput = this.registryOperations.getItemProperty(this.pathSearchPrivacity);
		for(int i = 0; i < valuesOutput.size(); i++) {
			String linei = valuesOutput.get(i);
			String[] splitLinei = linei.split("\\s+");
			if(splitLinei.length == 3) {
				if(splitLinei[0].equals("SafeSearchMode")
						|| splitLinei[0].equals("IsMSACloudSearchEnabled")
						|| splitLinei[0].equals("IsAADCloudSearchEnabled")
						|| splitLinei[0].equals("IsDeviceSearchHistoryEnabled")
						|| splitLinei[0].equals("IsGlobalWebSearchProviderToggleEnabled")) {
					values += splitLinei[0] + ":" + splitLinei[2] + "\n";
				}
			}
			//System.out.println(linei);
		}
		
		return values;
	}
	
	public boolean setValue(String property, String value) throws IOException, InterruptedException {
		boolean setSuccess = false;
		
		ArrayList<String> outputSet = this.registryOperations.setPropertyOfItem(this.pathSearchPrivacity, property, value);
		if(outputSet.get(0).contains("OK")) {
			setSuccess = true;
		}
		return setSuccess;
	}
}
