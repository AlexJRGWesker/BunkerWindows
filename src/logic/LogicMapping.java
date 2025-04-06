package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import logic.devices.Devices;
import logic.firewall.Firewall;
import logic.privacity.ConsentStore;
import logic.privacity.DiagnosticsPrivacity;
import logic.privacity.GeneralPrivacity;
import logic.privacity.SearchPrivacity;
import logic.services.Services;
import utils.PathsConf;

public class LogicMapping {
	private PathsConf pathsConf;
	private ConsentStore consentStore;
	private DiagnosticsPrivacity diagnosticsPrivacity;
	private GeneralPrivacity generalPrivacity;
	private SearchPrivacity searchPrivacity;
	private Services services;
	private Firewall firewall;
	private Devices devices;
	
	public LogicMapping() throws IOException, InterruptedException {
		this.pathsConf = new PathsConf();
		this.consentStore = new ConsentStore();
		this.diagnosticsPrivacity = new DiagnosticsPrivacity();
		this.generalPrivacity = new GeneralPrivacity();
		this.searchPrivacity = new SearchPrivacity();
		this.services = new Services();
		this.firewall = new Firewall();
		this.devices = new Devices();
	}
	
	public String doTask(String task) throws IOException, InterruptedException {
		String taskDebug = "";
		
		String[] splitTask = task.split("<-->");
		String mode = splitTask[0];
		String section = splitTask[1];
		String subsection = splitTask[2];
		String operation = splitTask[3];
		
		if(mode.equals("Init")) {
			File initDir = new File(this.pathsConf.confInit);
			if(!initDir.exists()) {
				initDir.mkdir();
			}
			if(section.equals("Privacity")) {
				File privacityDir = new File(this.pathsConf.confPrivacity);
				if(!privacityDir.exists()) {
					privacityDir.mkdir();
				}
				if(subsection.equals("ConsentStore")) {
					File consentStoreDir = new File(this.pathsConf.confPrivacityConsentStore);
					if(!consentStoreDir.exists()) {
						consentStoreDir.mkdir();
					}
					if(operation.equals("GetValues")) {
						String path = splitTask[4];
						String term = splitTask[5];
						File termFile = new File(this.pathsConf.confPrivacityConsentStore + "\\"+term+".txt");
						if(!termFile.exists()) {
							termFile.createNewFile();
						}
						String valuePath = consentStore.getValueFromPath(path);
						String toAdd = path + "<-->" + valuePath + "\n";
						FileWriter fw = new FileWriter(termFile,true);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(toAdd);
						bw.close();
						fw.close();
					}
				}
				if(subsection.equals("Diagnostics")) {
					File diagonsticsDir = new File(this.pathsConf.confPrivacityDiagnostics);
					if(!diagonsticsDir.exists()) {
						diagonsticsDir.mkdir();
					}
					if(operation.equals("GetValues")) {
						String valuesDiagnostics = diagnosticsPrivacity.getValues();
						String termFile = this.pathsConf.confPrivacityDiagnostics + "\\diagnostics.txt";
						FileWriter fw = new FileWriter(termFile,true);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(valuesDiagnostics);
						bw.close();
						fw.close();
					}
				}
				if(subsection.equals("General")) {
					File generalDir = new File(this.pathsConf.confPrivacityGeneral);
					if(!generalDir.exists()) {
						generalDir.mkdir();
					}
					if(operation.equals("GetValues")) {
						String valuesGeneral = generalPrivacity.getValues();
						String termFile = this.pathsConf.confPrivacityGeneral + "\\general.txt";
						FileWriter fw = new FileWriter(termFile,true);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(valuesGeneral);
						bw.close();
						fw.close();
					}
				}
				if(subsection.equals("Search")) {
					File searchDir = new File(this.pathsConf.confPrivacitySearch);
					if(!searchDir.exists()) {
						searchDir.mkdir();
					}
					if(operation.equals("GetValues")) {
						String valuesSearch = searchPrivacity.getValues();
						String termFile = this.pathsConf.confPrivacitySearch + "\\search.txt";
						FileWriter fw = new FileWriter(termFile,true);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(valuesSearch);
						bw.close();
						fw.close();
					}
				}
			}
			if(section.equals("Services")) {
				File servicesDir = new File(this.pathsConf.confServices);
				if(!servicesDir.exists()) {
					servicesDir.mkdir();
				}
				if(operation.equals("GetValues")) {
					String valueServices = services.obtainStartModeOfService(splitTask[4]);
					String termFile = this.pathsConf.confServices + "\\services.txt";
					String valueToAdd = splitTask[4] + "<-->" + valueServices + "\n";
					FileWriter fw = new FileWriter(termFile,true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(valueToAdd);
					bw.close();
					fw.close();
				}
			}
			if(section.equals("Firewall")) {
				File firewallDir = new File(this.pathsConf.confFirewall);
				if(!firewallDir.exists()) {
					firewallDir.mkdir();
				}
				if(operation.equals("Exports")) {
					String valueServices = services.obtainStartModeOfService(splitTask[4]);
					String termFile = this.pathsConf.confFirewall + "\\rulesFirewall.wfw";
					firewall.exportRules(termFile);
				}
			}
		}
		
		if(mode.equals("Restore")) {
			this.restore(splitTask);
		}
		if(mode.equals("Bunker")) {
			this.bunker(splitTask);
		}
		if(mode.equals("Installer")) {
			this.installer(splitTask);
		}
		if(mode.equals("Micro")) {
			this.micro(splitTask);
		}
		if(mode.equals("Cam")) {
			this.cam(splitTask);
		}
		if(mode.equals("Dev")) {
			this.devices(splitTask);
		}
		return taskDebug;
	}
	
	private void cam(String[] splitTask) throws IOException, InterruptedException {
		String mode = splitTask[0];
		String section = splitTask[1];
		String subsection = splitTask[2];
		String operation = splitTask[3];
		if(section.equals("Privacity")) {
			if(subsection.equals("ConsentStore")) {
				if(operation.equals("SetValues")) {
					String pathij = splitTask[4];
					String valueij = splitTask[5];
					if(!valueij.contains("ERROR")) {
						this.consentStore.setValueToPath(pathij, valueij);
					}
				}
			}
		}
	}
	
	private void micro(String[] splitTask) throws IOException, InterruptedException {
		String mode = splitTask[0];
		String section = splitTask[1];
		String subsection = splitTask[2];
		String operation = splitTask[3];
		if(section.equals("Privacity")) {
			if(subsection.equals("ConsentStore")) {
				if(operation.equals("SetValues")) {
					String pathij = splitTask[4];
					String valueij = splitTask[5];
					if(!valueij.contains("ERROR")) {
						this.consentStore.setValueToPath(pathij, valueij);
					}
				}
			}
		}
	}
	
	private void installer(String[] splitTask) throws IOException, InterruptedException {
		String mode = splitTask[0];
		String section = splitTask[1];
		String subsection = splitTask[2];
		String operation = splitTask[3];
		if(section.equals("Services")) {
			if(operation.equals("SetValues")) {
				String service = splitTask[4];
				String value = splitTask[5];
				boolean habilitatePrimary = this.services.habilitateService(service, value);
				if(!habilitatePrimary) {
					this.services.habilitateServiceAuxiliar(service, value);
				}
			}
		}
	}
	
	private void devices(String[] splitTask) throws IOException, InterruptedException {
		String mode = splitTask[0];
		String section = splitTask[1];
		String subsection = splitTask[2];
		String operation = splitTask[3];
		if(section.equals("Services")) {
			if(operation.equals("SetValues")) {
				String service = splitTask[4];
				String value = splitTask[5];
				boolean habilitatePrimary = this.services.habilitateService(service, value);
				if(!habilitatePrimary) {
					this.services.habilitateServiceAuxiliar(service, value);
				}
			}
		}
	}
	
	private void bunker(String[] splitTask) throws IOException, InterruptedException {
		String mode = splitTask[0];
		String section = splitTask[1];
		String subsection = splitTask[2];
		String operation = splitTask[3];
		if(section.equals("Privacity")) {
			if(subsection.equals("ConsentStore")) {
				if(operation.equals("SetValues")) {
					String pathij = splitTask[4];
					String valueij = splitTask[5];
					this.consentStore.setValueToPath(pathij, valueij);
				}
			}
			if(subsection.equals("General")) {
				if(operation.equals("SetValues")) {
					String pathij = splitTask[4];
					String property = splitTask[5];
					String value = splitTask[6];
					this.generalPrivacity.setValue(pathij, property, value);
				}
			}
			if(subsection.equals("Search")) {
				if(operation.equals("SetValues")) {
					String property = splitTask[4];
					String value = splitTask[5];
					this.searchPrivacity.setValue(property, value);
				}
			}
			if(subsection.equals("Diagnostics")) {
				if(operation.equals("SetValues")) {
					String pathij = splitTask[4];
					String property = splitTask[5];
					String value = splitTask[6];
					this.diagnosticsPrivacity.setValue(pathij, property, value);
				}
			}
		}
		if(section.equals("Services")) {
			if(operation.equals("SetValues")) {
				String service = splitTask[4];
				String value = splitTask[5];
				boolean habilitatePrimary = this.services.habilitateService(service, value);
				if(!habilitatePrimary) {
					this.services.habilitateServiceAuxiliar(service, value);
				}
			}
		}
		if(section.equals("Firewall")) {
			if(operation.equals("ImportFirewallBunker")) {
				this.firewall.deleteRules();
				this.firewall.importRulesBunker();
			}
		}
		if(section.equals("Devices")) {
			if(operation.equals("Deshabilitate")) {
				String device = splitTask[4];
				this.devices.disableDevice(device);
			}
		}
	}
	
	private void restore(String[] splitTask) throws IOException, InterruptedException {
		String mode = splitTask[0];
		String section = splitTask[1];
		String subsection = splitTask[2];
		String operation = splitTask[3];
		if(section.equals("Privacity")) {
			if(subsection.equals("ConsentStore")) {
				if(operation.equals("SetValues")) {
					String pathij = splitTask[4];
					String valueij = splitTask[5];
					this.consentStore.setValueToPath(pathij, valueij);
				}
			}
			if(subsection.equals("General")) {
				if(operation.equals("SetValues")) {
					String pathij = splitTask[4];
					String property = splitTask[5];
					String value = splitTask[6];
					this.generalPrivacity.setValue(pathij, property, value);
				}
			}
			if(subsection.equals("Search")) {
				if(operation.equals("SetValues")) {
					String property = splitTask[4];
					String value = splitTask[5];
					this.searchPrivacity.setValue(property, value);
				}
			}
			if(subsection.equals("Diagnostics")) {
				if(operation.equals("SetValues")) {
					String pathij = splitTask[4];
					String property = splitTask[5];
					String value = splitTask[6];
					this.diagnosticsPrivacity.setValue(pathij, property, value);
				}
			}
		}
		if(section.equals("Services")) {
			if(operation.equals("SetValues")) {
				String service = splitTask[4];
				String value = splitTask[5];
				boolean habilitatePrimary = this.services.habilitateService(service, value);
				if(!habilitatePrimary) {
					this.services.habilitateServiceAuxiliar(service, value);
				}
			}
		}
		if(section.equals("Firewall")) {
			if(operation.equals("Import")) {
				this.firewall.deleteRules();
				this.firewall.importRules();
			}
		}
		if(section.equals("Devices")) {
			if(operation.equals("Habilitate")) {
				String device = splitTask[4];
				this.devices.enableDevice(device);
			}
		}
	}
}
