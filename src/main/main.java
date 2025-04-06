package main;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import gui.mainGUI;
import gui.components.JProgressBarTasks;
import logic.devices.Devices;
import logic.firewall.Firewall;
import logic.privacity.ConsentStore;
import logic.privacity.DiagnosticsPrivacity;
import logic.privacity.GeneralPrivacity;
import logic.privacity.SearchPrivacity;
import logic.services.Services;
import logic.tasksToDo.TasksToDo_Init;
import process.LaunchProcess;
import utils.ConfExist;

public class main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		ConfExist confExist = new ConfExist();
		boolean existConf = confExist.existConf();
		//System.out.println("Existe la configuracion: " + existConf);
		if(!existConf) {
			JDialog loadingDialog = new JDialog();
	        loadingDialog.setTitle("Loading");
	        loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	        loadingDialog.add(new JLabel("              Wait..."), BorderLayout.CENTER);
	        loadingDialog.setSize(100, 70);
	        loadingDialog.setLocationRelativeTo(null); // Centrar en la pantalla
	        loadingDialog.setModal(true); // Hacer el diálogo modal

	        // Usar SwingWorker para cargar las tareas en segundo plano
	        SwingWorker<ArrayList<String>, Void> worker = new SwingWorker<ArrayList<String>, Void>() {
	            @Override
	            protected ArrayList<String> doInBackground() throws Exception {
	                // Simular una carga de tareas
	                TasksToDo_Init tasksToDo_Init = new TasksToDo_Init();
	                return tasksToDo_Init.getTasksToDo_Init();
	            }

	            @Override
	            protected void done() {
	                try {
	                    // Obtener las tareas cargadas
	                    ArrayList<String> tasks = get();
	                    // Ejecutar las tareas con el progress bar
	                    JProgressBarTasks progressBarTasks = new JProgressBarTasks(tasks,"Init");
	                    progressBarTasks.executeTasks();
	                    mainGUI gui = new mainGUI();
	        			JFrame guiJF = gui.gui();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                } finally {
	                    // Cerrar el diálogo en el hilo EDT
	                    loadingDialog.dispose();
	                }
	            }
	        };

	        // Mostrar el diálogo en un hilo separado
	        new Thread(() -> {
	            loadingDialog.setVisible(true);
	        }).start();

	        // Iniciar el worker
	        worker.execute();
	        
		}else {
			mainGUI gui = new mainGUI();
			JFrame guiJF = gui.gui();
		}
		
		/**
		Services services = new Services();
		boolean servicesAL = services.habilitateService("TrustedInstaller", "3");
		System.out.println(servicesAL);		
		if(!servicesAL) {
			services.habilitateServiceAuxiliar("TrustedInstaller", "3");
		}
		*/
	}

}
