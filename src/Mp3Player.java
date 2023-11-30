import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class Mp3Player extends JFrame {
    private Connection connection;
    private AdvancedPlayer player;
    private JButton playButton, stopButton;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    private String filePath, title;
    private long totalLength, startTime;
    private Timer timer;

    //---------------------------------------------------------------------------------------------------------
    public Mp3Player(Connection conn, String t) {
        connection = conn;
        title = t;
        setTitle(title);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(350, 100, 700, 200);
        addWindowListener(new MyWindowListener());

        playButton = new JButton("Play");
        stopButton = new JButton("Stop");
        statusLabel = new JLabel("Status: Stopped");
        progressBar = new JProgressBar(0, 100);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAudio(filePath);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAudio();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(stopButton);

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.add(statusLabel, BorderLayout.NORTH);
        statusPanel.add(progressBar, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void playAudio(String path) {
        filePath = path;
        try {
            stopAudio(); // Stop any currently playing audio

            FileInputStream fileInputStream = new FileInputStream(filePath);
            player = new AdvancedPlayer(fileInputStream);
            totalLength = fileInputStream.available();
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    stopAudio();
                    updateStatus("Status: Stopped");
                    updateProgressBar(0);
                }
            });

            updateStatus("Status: Playing");
            startTime = System.currentTimeMillis();
            startTimer();

            new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                } finally {
                    updateStatus("Status: Stopped");
                    updateProgressBar(0);
                }
            }).start();

        } catch (JavaLayerException | IOException ex) {
            ex.printStackTrace();
            updateStatus("Status: Error");
        }
    }

    private void stopAudio() {
        if (player != null) {
            player.close();
            stopTimer();
        }
    }

    private void updateStatus(String status) {
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText(status);
        });
    }

    private void updateProgressBar(int value) {
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(value);
        });
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                long totalSeconds = elapsedTime / 1000;
                long minutes = totalSeconds / 60;
                long seconds = totalSeconds % 60;

                long remainingTime = (totalLength - elapsedTime) / 1000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                long remainingMinutes = remainingTime / 60;
                long remainingSeconds = remainingTime % 60;

                updateStatus("Status: Playing - Elapsed Time: " + formatTime(minutes) + ":" + formatTime(seconds) +
                        " - Remaining Time: " + formatTime(remainingMinutes) + ":" + formatTime(remainingSeconds));

                int progress = (int) ((double) elapsedTime / totalLength * 100);
                updateProgressBar(progress);
            }
        });
        timer.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    private String formatTime(long timeUnit) {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(timeUnit);
    }

    //---------------------------------------------------------------------------------------------------------
    private void saveState() {
        String query = "update saveddata set currentAudio = ?, title = ? where `row` = 1;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, filePath);
            preparedStatement.setString(2, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //---------------------------------------------------------------------------------------------------------
    class MyWindowListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            stopAudio();
            saveState();
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