package gui.mouseListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import gui.components.JProgressBarTasks;
import logic.tasksToDo.TasksToDo_Bunker;
import logic.tasksToDo.TasksToDo_Init;
import logic.tasksToDo.TasksToDo_Restore;
import process.LaunchProcess;
import utils.PathsConf;

public class UpdateButtonListener extends MouseAdapter{
	private Color defaultColor = Color.LIGHT_GRAY;
    private Color hoverColor = Color.GRAY;
    private JPanel jpanel;
    
    public UpdateButtonListener(JPanel jpanel) {
    	this.jpanel = jpanel;
    }
    
	@Override
    public void mouseEntered(MouseEvent e) {
		jpanel.setBackground(hoverColor);
		jpanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
    	jpanel.setBackground(defaultColor);
    	jpanel.setCursor(Cursor.getDefaultCursor());
    }

    public void mouseClicked(MouseEvent e) {
    	// Crear el diálogo de "Espere"
        JDialog loadingDialog = new JDialog();
        loadingDialog.setTitle("Loading");
        ImageIcon icono = new ImageIcon(getClass().getResource("/images/iconoApp.jpg"));
        loadingDialog.setIconImage(icono.getImage());
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
            	ArrayList<String> tasksUpdate = new ArrayList<String>();
                TasksToDo_Restore tasksToDo_Restore = new TasksToDo_Restore();
                ArrayList<String> tasksRestore = tasksToDo_Restore.tasksToDo_Restore();
                TasksToDo_Init tasksToDo_Init = new TasksToDo_Init();
                ArrayList<String> tasksInit = tasksToDo_Init.getTasksToDo_Init();
                for(int i = 0; i < tasksRestore.size(); i++) {
                	tasksUpdate.add(tasksRestore.get(i));
                }
                for(int i = 0; i < tasksInit.size(); i++) {
                	tasksUpdate.add(tasksInit.get(i));
                }
                return tasksUpdate;
            }

            @Override
            protected void done() {
                try {
                    // Obtener las tareas cargadas
                    ArrayList<String> tasks = get();
                    // Ejecutar las tareas con el progress bar
                    PathsConf paths = new PathsConf();
                    String[] deleteDirConfCommand = {
                    	"powershell","/c",
                    	"Remove-Item",paths.confInit,"-Recurse","-Force"
                    };
                    LaunchProcess launch = new LaunchProcess();
                    launch.launchProcess(deleteDirConfCommand);
                    JProgressBarTasks progressBarTasks = new JProgressBarTasks(tasks,"Update");
                    progressBarTasks.executeTasks();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    // Cerrar el diálogo en el hilo EDT
                    loadingDialog.dispose();
                    SwingWorker<ArrayList<String>, Void> worker2 = new SwingWorker<ArrayList<String>, Void>() {
                        @Override
                        protected ArrayList<String> doInBackground() throws Exception {
                            // Simular una carga de tareas
                        	ArrayList<String> tasksUpdate = new ArrayList<String>();
                            TasksToDo_Bunker tasksToDo_Bunker = new TasksToDo_Bunker();
                            ArrayList<String> tasksBunker = tasksToDo_Bunker.tasksToDo_Bunker();
                            for(int i = 0; i < tasksBunker.size(); i++) {
                            	tasksUpdate.add(tasksBunker.get(i));
                            }
                            return tasksUpdate;
                        }

                        @Override
                        protected void done() {
                            try {
                                // Obtener las tareas cargadas
                                ArrayList<String> tasks = get();
                                // Ejecutar las tareas con el progress bar
                                PathsConf paths = new PathsConf();
                                String[] deleteDirConfCommand = {
                                	"powershell","/c",
                                	"Remove-Item",paths.confInit,"-Recurse","-Force"
                                };
                                LaunchProcess launch = new LaunchProcess();
                                launch.launchProcess(deleteDirConfCommand);
                                JProgressBarTasks progressBarTasks = new JProgressBarTasks(tasks,"UpdateFinal");
                                progressBarTasks.executeTasks();
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
                    worker2.execute();
                    
                }
            }
        };

        // Mostrar el diálogo en un hilo separado
        new Thread(() -> {
            loadingDialog.setVisible(true);
        }).start();

        // Iniciar el worker
        worker.execute();
        
    }
}