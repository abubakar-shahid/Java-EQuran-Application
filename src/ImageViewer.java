import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ImageViewer extends JFrame {
    protected JLabel imageLabel;
    protected int currentIndex;
    protected List<String> imagePaths;

    public void imageViewer() {
        setTitle("Image Viewer");
        addWindowListener(new MyWindowListener());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(400,10,600,700);

        imagePaths = new ArrayList<>();
        for (int i = 2; i <= 549; i++) {
            imagePaths.add("D:\\FAST-NUCES l215845\\5th Semester\\Software Construction & Development\\Project\\Extras\\Pages\\" + i + ".jpeg");
        }

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        showImage();

        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        Font buttonFont = prevButton.getFont();
        Font newMenuFont = buttonFont.deriveFont(16.0f);
        prevButton.setFont(newMenuFont);
        nextButton.setFont(newMenuFont);

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousImage();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        showNextImage();
                        break;
                    case KeyEvent.VK_RIGHT:
                        showPreviousImage();
                        break;
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        buttonPanel.add(prevButton);

        add(imageLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setFocusable(true);
        setVisible(true);
    }

    private void showImage() {
        //System.out.println("in showImage() : " + currentIndex + " , " + imagePaths.get(currentIndex));
        ImageIcon imageIcon = new ImageIcon(imagePaths.get(currentIndex));
        Image image = imageIcon.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);
    }

    private void showNextImage() {
        currentIndex = (currentIndex + 1) % imagePaths.size();
        showImage();
    }

    private void showPreviousImage() {
        currentIndex = (currentIndex - 1 + imagePaths.size()) % imagePaths.size();
        showImage();
    }

    //---------------------------------------------------------------------------------------------------------
    class MyWindowListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            dispose();
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