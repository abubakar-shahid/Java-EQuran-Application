import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class HomePage extends JFrame implements ActionListener {
    //---------------------------------------------------------------------------------------------------------
    public void runApplication() {
        //Header
        JLabel heading = new JLabel("\"Welcome to E-Qura'an Application\"");
        Font labelFont1 = heading.getFont();
        Font newLabelFont1 = labelFont1.deriveFont(24.0f);
        heading.setFont(newLabelFont1);
        Font fontStyle = new Font(heading.getFont().getFamily(), Font.ITALIC | Font.BOLD, heading.getFont().getSize());
        heading.setFont(fontStyle);

        JPanel header = new JPanel(new FlowLayout());
        header.add(heading);

        //Body
        BackgroundPanel backgroundPanel = new BackgroundPanel("D:\\FAST-NUCES l215845\\5th Semester\\Software Construction & Development\\Project\\Extras\\CoverPicture.png");

        //Footer
        JButton support = new JButton("Support this App");
        support.addActionListener(this);
        JButton read = new JButton("Read Qura'an");
        read.addActionListener(this);
        JButton listen = new JButton("Listen Audio");
        listen.addActionListener(this);
        JButton exit = new JButton("Exit App");
        exit.addActionListener(this);

        Font buttonFont = read.getFont();
        Font newButtonFont = buttonFont.deriveFont(16.0f);
        support.setFont(newButtonFont);
        read.setFont(newButtonFont);
        listen.setFont(newButtonFont);
        exit.setFont(newButtonFont);

        JPanel footer = new JPanel(new FlowLayout());
        footer.add(support);
        footer.add(read);
        footer.add(listen);
        footer.add(exit);

        //Frame
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(backgroundPanel, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.SOUTH);
        add(footer, BorderLayout.SOUTH);

        setTitle("E-Qura'an Application");
        setSize(700, 700);
        addWindowListener(new MyWindowListener());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    //---------------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Support this App":
                Support sp = new Support();
                break;
            case "Read Qura'an":
                Read rd = new Read();
                break;
            case "Listen Audio":
                Listen ls = new Listen();
                break;
            case "Exit App":
                System.exit(0);
        }
    }

    //---------------------------------------------------------------------------------------------------------
    class MyWindowListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm Close", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }
    //---------------------------------------------------------------------------------------------------------
}
