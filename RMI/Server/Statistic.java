import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Реализация GUI через JFrame.
*/

public class Statistic extends JFrame implements Runnable {
    private double stat = 0d;
    private long num = 0l;
    private JLabel Label;
    private JButton getRequests;
    private JButton toZero;
    private JButton addRequests;
    private JButton genPerformance;
    private JButton addPerformance;
    private JButton getPerformance;
    static Long startTime = System.currentTimeMillis();

    Statistic() {
        super("Statistic");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Label = new JLabel("Waiting for command.");
        getRequests = new JButton("Number of GetReq");
        toZero = new JButton("Set statistics to zero");
        addRequests = new JButton("Number of AddReq");
        genPerformance = new JButton("General Performance");
        getPerformance = new JButton("GetReq Performance");
        addPerformance = new JButton("AddReq Performance");

        JPanel buttonsPanel = new JPanel(new FlowLayout());

        add(Label, BorderLayout.NORTH);

        buttonsPanel.add(getRequests);
        buttonsPanel.add(addRequests);
        buttonsPanel.add(genPerformance);
        buttonsPanel.add(getPerformance);
        buttonsPanel.add(addPerformance);
        buttonsPanel.add(toZero);

        add(buttonsPanel, BorderLayout.CENTER);

        initListeners();
    }

    private void initListeners() {
        getRequests.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num = DateReq.getReq();
                updateReqCounter();
            }
        });
        toZero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DateReq.Clean();
            }
        });
        addRequests.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num = DateReq.addReq();
                updateReqCounter();
            }
        });
        genPerformance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Long tempVal = DateReq.addReq() + DateReq.getReq();
                Long tempTime = System.currentTimeMillis() - startTime;
                stat = (double) tempVal / tempTime * 1000L;
                updatePerCounter();
            }
        });
        getPerformance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long tempVal = DateReq.getReq();
                Long tempTime = System.currentTimeMillis() - startTime;
                stat = (double) tempVal / tempTime * 1000L;
                updatePerCounter();
            }
        });
        addPerformance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long tempVal = DateReq.addReq();
                Long tempTime = System.currentTimeMillis() - startTime;
                stat = (double) tempVal / tempTime * 1000L;
                updatePerCounter();
            }
        });
    }

    private void updatePerCounter() {
        Label.setText(String.format("%8.2f", stat).replace(',', '.') + " per second.");
    }

    private void updateReqCounter() {
        Label.setText("Count: " + num);
    }

    public void run() {
        Statistic app = new Statistic();
        app.setVisible(true);
        app.pack();
    }

}
