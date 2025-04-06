package gui.components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class CustomButton extends JPanel {
    private Color defaultColor;
    private Color hoverColor;
    
    public CustomButton(String text, int width, int height) {
        defaultColor = Color.LIGHT_GRAY;
        hoverColor = Color.GRAY;

        setBackground(defaultColor);
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        // Cambiar color y cursor al pasar el mouse
        //setEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20)); // Bordes redondeados
    }
    
    public void setMouseListener(MouseAdapter adapter) {
        addMouseListener(adapter);	
    }
}