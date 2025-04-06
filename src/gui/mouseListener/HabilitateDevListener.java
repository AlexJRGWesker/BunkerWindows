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
import logic.tasksToDo.TasksToDo_Dev;

public class HabilitateDevListener extends MouseAdapter{
	private Color defaultColor = Color.LIGHT_GRAY;
    private Color hoverColor = Color.GRAY;
    private JPanel jpanel;
    
    public HabilitateDevListener(JPanel jpanel) {
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

    @Override
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
            	TasksToDo_Dev tasksToDo_Dev = new TasksToDo_Dev();
                return tasksToDo_Dev.tasksToDo_Dev("Habilitate");
            }

            @Override
            protected void done() {
                try {
                    // Obtener las tareas cargadas
                    ArrayList<String> tasks = get();
                    // Ejecutar las tareas con el progress bar
                    JProgressBarTasks progressBarTasks = new JProgressBarTasks(tasks,"Dev");
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
        worker.execute();
    }
}