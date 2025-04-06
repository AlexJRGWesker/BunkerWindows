package logic.firewall;

import java.io.IOException;
import java.util.ArrayList;

import process.LaunchProcess;
import utils.FirewallPaths;

public class Firewall {
	private LaunchProcess launchProcess;
	private FirewallPaths firewallPaths;
	
	public Firewall() {
		this.launchProcess = new LaunchProcess();
		this.firewallPaths = new FirewallPaths();
	}
	
	public boolean exportRules(String path) throws IOException, InterruptedException {
		boolean exportSuccess = false;
		
		String[] command = {
				"cmd.exe","/c",
				"netsh",
				"advfirewall",
				"export",
				path
		};
		ArrayList<String> export = this.launchProcess.launchProcess(command);
		if(export.get(0).contains("OK")) {
			exportSuccess = true;
		}
		
		return exportSuccess;
	}
	
	public boolean importRules() throws IOException, InterruptedException {
		boolean importSuccess = false;
		
		String[] command = {
				"cmd.exe","/c",
				"netsh",
				"advfirewall",
				"import",
				this.firewallPaths.rulesFile
		};
		ArrayList<String> importR = this.launchProcess.launchProcess(command);
		if(importR.get(0).contains("OK")) {
			importSuccess = true;
		}
		
		return importSuccess;
	}
	
	public boolean importRulesBunker() throws IOException, InterruptedException {
		boolean importSuccess = false;
		
		String[] command = {
				"cmd.exe","/c",
				"netsh",
				"advfirewall",
				"import",
				".\\filesAux\\rulesFirewall.wfw"
		};
		ArrayList<String> importR = this.launchProcess.launchProcess(command);
		if(importR.get(0).contains("OK")) {
			importSuccess = true;
		}
		
		return importSuccess;
	}
	
	public boolean deleteRules() throws IOException, InterruptedException {
		boolean deleteSuccess = false;
		
		String[] command = {
				"cmd.exe","/c",
				"netsh","advfirewall","firewall","delete","rule","name=all"
		};
		ArrayList<String> delete = this.launchProcess.launchProcess(command);
		if(delete.get(0).contains("OK")) {
			deleteSuccess = true;
		}
		
		return deleteSuccess;
	}
}
