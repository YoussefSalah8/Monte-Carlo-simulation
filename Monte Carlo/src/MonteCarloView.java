import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonteCarloView extends JFrame {
    private JTextField totalPointsField;
    private JTextField numThreadsField;
    private JTextArea resultArea;
    private MonteCarloController controller;

    public MonteCarloView() {
        this.controller = controller;
        setTitle("Monte Carlo Simulation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2));

        mainPanel.add(new JLabel("Total Points:"));
        totalPointsField = new JTextField("1000000");
        mainPanel.add(totalPointsField);

        mainPanel.add(new JLabel("Number of Threads:"));
        numThreadsField = new JTextField("4");
        mainPanel.add(numThreadsField);

        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startSimulation();
            }
        });
        mainPanel.add(startButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        mainPanel.add(scrollPane);

        add(mainPanel);
    }

    public void setController(MonteCarloController controller) {
        this.controller = controller;
    }

    public int getTotalPoints() {
        return Integer.parseInt(totalPointsField.getText());
    }


    public int getNumThreads() {
        return Integer.parseInt(numThreadsField.getText());
    }

    public void setResult(String result) {
        resultArea.setText(result);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MonteCarloView().setVisible(true);
//            }
//        });
//    }
}
