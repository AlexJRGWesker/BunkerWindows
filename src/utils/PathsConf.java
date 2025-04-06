package utils;

public class PathsConf {
	public PathsConf() {}
	
	public String confInit = ".\\initConf";
	
	//Privacity
	public String confPrivacity = confInit + "\\privacity";
	public String confPrivacityConsentStore = confPrivacity + "\\consentStore";
	public String confPrivacityDiagnostics = confPrivacity + "\\diagnostics";
	public String confPrivacityGeneral = confPrivacity + "\\general";
	public String confPrivacitySearch = confPrivacity + "\\search";
	
	//Services
	public String confServices = confInit + "\\services";
	
	//Firewall
	public String confFirewall = confInit + "\\firewall";
	
	//Devices
	public String confDevices = confInit + "\\devices";
}
