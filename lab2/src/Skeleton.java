import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import javax.swing.*;

// 1. Рух по колу проти годинникової стрілки
// 9. Зміна прозорості
// JOIN_MITER

public class Skeleton extends JPanel implements ActionListener {
    Timer timer;

    private static int maxHeight;
    private static int maxWidth;

    private double scale = 1;
    private double delta = 0.01;

    public Skeleton() {
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)scale));
        paintTV(g2d);
    }
    public static void main(String [] args) {
        JFrame frame = new JFrame("lab2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Skeleton());
        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }
    public void paintTV(Graphics2D g2d) {
        int width = maxWidth;
        int height = maxHeight;
        g2d.setPaint(new Color(127, 255, 0));
        g2d.fillRect(0, 0, width, height);
        g2d.setPaint(new Color(255, 165, 0));
        g2d.fillRect(50, 50, 300, 200);
        g2d.setPaint(new Color(128, 128, 128)); //display color
        g2d.fillRoundRect(65, 65, 170, 170, 30, 30);
        g2d.setPaint(Color.BLACK); //button color
        g2d.fillOval(293, 225, 7, 7);
        g2d.fillOval(293, 215, 7, 7);
        g2d.fillOval(293, 205, 7, 7);
        Path2D lines = new Path2D.Double();
        lines.moveTo(170, 20);
        lines.lineTo(200, 50);
        lines.lineTo(230, 20);
        g2d.draw(lines);
    }

    public void actionPerformed(ActionEvent e) {
        if(scale < 0.01) delta = -delta;

        if(scale > 0.99) delta = -delta;
        scale += delta;
        repaint();
    }

}
