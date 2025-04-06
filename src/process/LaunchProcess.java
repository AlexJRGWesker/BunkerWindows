package process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LaunchProcess {
	public LaunchProcess() {}
	
	public ArrayList<String> launchProcess(String[] command) throws IOException, InterruptedException{
		ProcessBuilder pb = new ProcessBuilder(command);
		Process p = pb.start();
		
		ArrayList<String> inputStream = new ArrayList<String>();
		ArrayList<String> errorStream = new ArrayList<String>();
		
		new Thread(new Runnable(){
			@Override 
			public void run() {
				try {
					InputStreamReader isr = new InputStreamReader(p.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					String line = "";
					while((line = br.readLine()) != null) {
						inputStream.add(line);
					}
				}catch(Exception e) {
					
				}
			}
		}).start();
		
		new Thread(new Runnable(){
			@Override 
			public void run() {
				try {
					InputStreamReader isr = new InputStreamReader(p.getErrorStream());
					BufferedReader br = new BufferedReader(isr);
					String line = "";
					while((line = br.readLine()) != null) {
						errorStream.add(line);
					}
				}catch(Exception e) {
					
				}
			}
		}).start();
		
		p.waitFor();
		
		ArrayList<String> output = new ArrayList<String>();
		if(inputStream.size() > 0) {
			output.add("OK");
			output.addAll(inputStream);
		}else if(errorStream.size() > 0) {
			output.add("ERROR");
			output.addAll(errorStream);
		}else {
			output.add("OK-WITHOUT-OUTPUT");
		}
		
		return output;
	}
}
