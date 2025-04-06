package gui.components;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelWithBackgroundImage extends JPanel{
	 private Image imagenFondo;

	    public JPanelWithBackgroundImage(String pathImagen) {
	        // Cargar la imagen de fondo
	        imagenFondo = new ImageIcon(getClass().getResource(pathImagen)).getImage();
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        // Dibujar la imagen de fondo
	        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
	    }
}
