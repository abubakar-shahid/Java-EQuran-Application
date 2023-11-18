import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Application extends JFrame implements ActionListener {
    public void runApplication() {
        //Header
        JLabel heading = new JLabel("\"Welcome to E-Qura'an Application\"");
        Font labelFont1 = heading.getFont();
        Font newLabelFont1 = labelFont1.deriveFont(24.0f);
        heading.setFont(newLabelFont1);

        JPanel header = new JPanel(new FlowLayout());
        header.add(heading);

        //Body
        BackgroundPanel backgroundPanel = new BackgroundPanel("D:\\FAST-NUCES l215845\\5th Semester\\Software Construction & Development\\Project\\Extras\\CoverPicture.png");

        //Frame
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(backgroundPanel, BorderLayout.CENTER);add(new JPanel(), BorderLayout.SOUTH);

        setTitle("E-Qura'an Application");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
