package utils;

public class RegistryPaths {
	public RegistryPaths() {}
	
	public String pathServices = "HKLM:\\SYSTEM\\CurrentControlSet\\Services\\";
	
	public String consentStoreHKCUPath = "HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\CapabilityAccessManager\\ConsentStore";
	public String consentStoreHKLMPath = "HKLM:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\CapabilityAccessManager\\ConsentStore";

	
	public String advertisingInfoPath = "HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\AdvertisingInfo";
	public String languajeRelatedPath = "HKCU:\\Control Panel\\International\\User Profile";
	public String contentDeliveryPath = "HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\ContentDeliveryManager";
	public String accountNotifications = "HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\SystemSettings\\AccountNotifications";
	public String trackProgmPaths = "HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Advanced";
	public String trackDocsPath = "HKLM:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Advanced";
	
	
	public String privacyPolicy = "HKCU:\\Software\\Microsoft\\Personalization\\Settings";
	public String harvestContacts = "HKCU:\\Software\\Microsoft\\InputPersonalization\\TrainedDataStore";
	public String inputPersonalization = "HKCU:\\Software\\Microsoft\\InputPersonalization";
	public String showedAtToastLevel = "HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Diagnostics\\DiagTrack";
	public String tailoredExpHKCU = "HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Privacy";
	public String tailoredExpHKLM = "HKLM:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Privacy";
	public String assistanceSett = "HKLM:\\SOFTWARE\\Microsoft\\Assistance\\Client\\1.0\\Settings";
	
}
