package utils;

import java.io.File;

public class ConfExist {
	private PathsConf pathsConf;
	
	public ConfExist() {
		this.pathsConf = new PathsConf();
	}
	
	public boolean existConf() {
		boolean existsConf = true;
		
		File init = new File(this.pathsConf.confInit);
		if(init.exists()) {
			File privacity = new File(this.pathsConf.confPrivacity);
			File firewall = new File(this.pathsConf.confFirewall);
			//File devices = new File(this.pathsConf.confDevices);
			File services = new File(this.pathsConf.confServices);
			if(!privacity.exists() || !firewall.exists() || !services.exists()) {
				existsConf = false;
			}else {
				File consentStore = new File(this.pathsConf.confPrivacityConsentStore);
				File diagnostics = new File(this.pathsConf.confPrivacityDiagnostics);
				File general = new File(this.pathsConf.confPrivacityGeneral);
				File search = new File(this.pathsConf.confPrivacitySearch);
				if(!consentStore.exists() || !diagnostics.exists() || !general.exists() || !search.exists()) {
					existsConf = false;
				}
			}
		}else {
			existsConf = false;
		}
		
		return existsConf;
	}
}
