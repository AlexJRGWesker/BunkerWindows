package gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import logic.LogicMapping;
import process.LaunchProcess;

public class JProgressBarTasks {
    private JProgressBar progressBar;
    private JLabel taskLabel;
    private JDialog dialog;
    private ArrayList<String> tasks;
    private String mode;
    
    public JProgressBarTasks(ArrayList<String> tasks,String mode) {
        // Configuración del diálogo
    	this.mode = mode;
    	this.tasks = tasks;
        dialog = new JDialog();
        dialog.setTitle("Progress");
        ImageIcon icono = new ImageIcon(getClass().getResource("/images/iconoApp.jpg"));
        dialog.setIconImage(icono.getImage());
        dialog.setSize(800, 100);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(null);
        
        // Inicialización del JLabel para mostrar la tarea actual
        taskLabel = new JLabel("Comenzando...", SwingConstants.CENTER);
        dialog.add(taskLabel, BorderLayout.NORTH);

        // Inicialización del JProgressBar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.RED);
        progressBar.setBackground(Color.BLACK);
        dialog.add(progressBar, BorderLayout.CENTER);
        
        dialog.setModal(true); // Hacer el diálogo modal
    }

    public void executeTasks() {
        // Mostrar el diálogo antes de comenzar las tareas en el hilo EDT
        SwingUtilities.invokeLater(() -> dialog.setVisible(true));
        ArrayList<String> tasks = this.tasks;
        // Usar SwingWorker para ejecutar las tareas
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
            	LogicMapping logicMapping = new LogicMapping();
            	for (int i = 0; i < tasks.size(); i++) {
                    // Simular el tiempo que toma completar cada tarea
                    //Thread.sleep(300); // Simula una tarea que toma 300 ms
                    // Publicar el progreso y la tarea actual
                    String titleTask = tasks.get(i).split("<-->")[9];
                    logicMapping.doTask(tasks.get(i));
                    publish(titleTask);
                    int progress = (i + 1) * 100 / tasks.size();
                    setProgress(progress);
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                // Actualizar el JLabel con la tarea actual
                taskLabel.setText(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                progressBar.setString("Todas las tareas completadas!");
                dialog.dispose(); // Cerrar el diálogo al finalizar
                showRestartDialog(); // Mostrar el diálogo de reinicio
            }
        };

        // Establecer el progreso máximo y agregar un listener
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });

        // Iniciar el worker
        worker.execute();
    }

    private void showRestartDialog() {
        // Crear un nuevo JDialog para preguntar sobre el reinicio
    	if(this.mode.equals("Init") || this.mode.equals("Cam") || this.mode.equals("Micro")
    			|| this.mode.equals("Dev") || this.mode.equals("Installer")) {
            JDialog restartDialog = new JDialog();
	        restartDialog.setTitle("Complete");
	        restartDialog.setSize(300, 80);
	        ImageIcon icono = new ImageIcon(getClass().getResource("/images/iconoApp.jpg"));
	        restartDialog.setIconImage(icono.getImage());
	        restartDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	        restartDialog.setLayout(new FlowLayout());
	        restartDialog.setLocationRelativeTo(null);
	        
	        JLabel messageLabel = new JLabel("Action completed succesfully!");
	        restartDialog.add(messageLabel);
	        
	        JButton yesButton = new JButton("OK");
	        yesButton.addActionListener(e -> {
	            // Aquí puedes agregar el código para reiniciar el PC
	            // Por ejemplo, puedes usar un comando del sistema para reiniciar
	            //System.out.println("Reboot the PC, wait...");
	            restartDialog.dispose(); // Cerrar el diálogo
	        });
	        restartDialog.add(yesButton);
	
	        restartDialog.setModal(true); // Hacer el diálogo modal
	        restartDialog.setVisible(true); // Mostrar el diálogo
    	}
    	if(this.mode.equals("Restore") || this.mode.equals("Bunker") || this.mode.equals("UpdateFinal")) {
	        JDialog restartDialog = new JDialog();
	        restartDialog.setTitle("Reboot PC");
	        restartDialog.setSize(300, 80);
	        ImageIcon icono = new ImageIcon(getClass().getResource("/images/iconoApp.jpg"));
	        restartDialog.setIconImage(icono.getImage());
	        restartDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	        restartDialog.setLayout(new FlowLayout());
	        restartDialog.setLocationRelativeTo(null);
	        
	        JLabel messageLabel = new JLabel("You need to reboot the PC");
	        restartDialog.add(messageLabel);
	        
	        JButton yesButton = new JButton("Ok");
	        yesButton.addActionListener(e -> {
	            // Aquí puedes agregar el código para reiniciar el PC
	            // Por ejemplo, puedes usar un comando del sistema para reiniciar
	        	try {
		        	String[] rebootCommand = {
		        		"cmd.exe","/c","shutdown","/r","/t","0"
		        	};
		        	LaunchProcess launch = new LaunchProcess();
		        	launch.launchProcess(rebootCommand);
	        	}catch(Exception exc) {
	        		
	        	}
	            System.out.println("Reboot the PC, wait...");
	            restartDialog.dispose(); // Cerrar el diálogo
	        });
	        restartDialog.add(yesButton);
	
	        restartDialog.setModal(true); // Hacer el diálogo modal
	        restartDialog.setVisible(true); // Mostrar el diálogo
    	}
    }
}