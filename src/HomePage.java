import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomePage extends JFrame implements ActionListener {
    private static Connection connection;
    private JMenuBar bar;
    private JMenu menu;
    private JMenu it1;
    private JMenu it2;
    private JMenuItem it3;
    private JMenuItem startReading;
    private JMenuItem continueReading;
    private JMenuItem startListening;
    private JMenuItem continueListening;
    private int currentPage = -1;
    private int currentAudio = -1;
    private JButton exit;
    private JPanel footer;

    //---------------------------------------------------------------------------------------------------------
    public void runApplication(Connection conn) throws SQLException {
        connection = conn;
        getCurrentStates();

        //Menu
        bar = new JMenuBar();
        menu = new JMenu("☰");
        Font menuFont = bar.getFont();
        Font newMenuFont = menuFont.deriveFont(30.0f);
        menu.setFont(newMenuFont);

        it1 = new JMenu("Read Qura'an");
        it2 = new JMenu("Listen Recitation");
        it3 = new JMenuItem("Support this App");

        startReading = new JMenuItem("Start Reading");
        continueReading = new JMenuItem("Continue Reading");
        startListening = new JMenuItem("Start Listening");
        continueListening = new JMenuItem("Continue Listening");

        startReading.addActionListener(this);
        continueReading.addActionListener(this);
        startListening.addActionListener(this);
        continueListening.addActionListener(this);
        it3.addActionListener(this);

        it1.add(startReading);
        it1.add(continueReading);
        it2.add(startListening);
        it2.add(continueListening);

        Font menuItemsFont1 = it1.getFont();
        Font newMenuItemsFont1 = menuItemsFont1.deriveFont(18.0f);
        it1.setFont(newMenuItemsFont1);
        it2.setFont(newMenuItemsFont1);
        it3.setFont(newMenuItemsFont1);

        Font menuItemsFont2 = startReading.getFont();
        Font newMenuItemsFont2 = menuItemsFont2.deriveFont(14.0f);
        startReading.setFont(newMenuItemsFont2);
        continueReading.setFont(newMenuItemsFont2);
        startListening.setFont(newMenuItemsFont2);
        continueListening.setFont(newMenuItemsFont2);

        menu.add(it1);
        menu.add(it2);
        menu.add(it3);

        bar.add(menu);
        bar.setSize(500, 500);
        bar.setPreferredSize(new Dimension(100, 40));

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
        exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setFont(newMenuItemsFont1);

        footer = new JPanel(new FlowLayout());
        footer.add(exit);

        //Frame
        setLayout(new BorderLayout());
        setJMenuBar(bar);
        add(header, BorderLayout.NORTH);
        add(backgroundPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        setTitle("E-Qura'an Application");
        setSize(600, 700);
        addWindowListener(new MyWindowListener());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    //---------------------------------------------------------------------------------------------------------
    private void getCurrentStates() throws SQLException {
        String query = "select * from users;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
    }

    //---------------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Support this App":
                Support sp = new Support();
                break;
            case "Start Reading":
                Read rd1 = new Read();
                rd1.startReading();
                break;
            case "Continue Reading":
//                Read rd2 = new Read();
//                rd2.continueReading();
                break;
            case "Start Listening":
                Listen ls1 = new Listen();
                ls1.startListening();
                break;
            case "Continue Listening":
                Listen ls2 = new Listen();
                ls2.continueListening();
                break;
            case "Exit":
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm Close", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
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
                //Save current page and audio
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
