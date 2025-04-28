import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveListerGUI  extends JFrame {

     JButton startButton, quitButton;
     JTextArea fileArea;
     JScrollPane scroller;
     JLabel titleLabel;

    public RecursiveListerGUI() {
        // Set up the JFrame
        setTitle("Recursive File Lister");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        titleLabel = new JLabel("File and Directory Lister", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fileArea = new JTextArea();
        fileArea.setEditable(false);
        scroller = new JScrollPane(fileArea);
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");

        // Add components to the JFrame
        add(titleLabel, BorderLayout.NORTH);
        add(scroller, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(quitButton);
        add(panel, BorderLayout.SOUTH);


        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = fileChooser.getSelectedFile();
                    fileArea.setText("");  // Clear the JTextArea before listing files
                    listFiles(selectedDirectory);  // Start the recursive listing
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Close the application
            }
        });
    }

    // Recursive method
    private void listFiles(File directory) {
        // Get all files and directories in the current directory
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    fileArea.append("Directory: " + file.getAbsolutePath() + "\n");
                    listFiles(file);
                } else {
                    fileArea.append("File: " + file.getAbsolutePath() + "\n");
                }
            }
        }
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecursiveListerGUI gui = new RecursiveListerGUI();
            gui.setVisible(true);
        });
    }
}

