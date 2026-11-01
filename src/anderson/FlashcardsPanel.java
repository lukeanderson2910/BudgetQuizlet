/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package anderson;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author k money
 */
public class FlashcardsPanel extends javax.swing.JPanel {

    private StudySet set;
    private int x = 20;
    private int y = 20;
    private int width = 500;
    private int height = 300;
    private Rectangle2D card = new Rectangle2D.Double(20, 20, 500, 300);
    private Rectangle2D border = new Rectangle2D.Double(15, 15, 510, 310);
    private int currentIndex = 0;
    private JButton back, next;
    private JLabel titleLabel = new JLabel("Term:");
    private JLabel contentLabel = new JLabel("");
    private ArrayList<String> terms, defs;
    private String[] termsArr, defsArr;
    private boolean timerRunning = false;

    /**
     * Creates new form FlashcardsPanel
     */
    public FlashcardsPanel(int x, int y, int width, int height, StudySet set) {
        setBounds(x, y, width, height);
        initComponents();
        terms = set.getTerms();
        defs = set.getDefinitions();
        back = new JButton("Back");
        next = new JButton("Next");
        back.setBounds(20, 360, 75, 25);
        next.setBounds(445, 360, 75, 25);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex == 0) {
                    currentIndex = defsArr.length - 1;
                } else {
                    currentIndex--;
                }
                updateContentLabel(titleLabel.getText().equals("Term:"));
            }
        });
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex >= defsArr.length - 1) {
                    currentIndex = 0;
                } else {
                    currentIndex++;
                }
                updateContentLabel(titleLabel.getText().equals("Term:"));
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (card.contains(e.getPoint())) {
                    flip();
                }
            }
        });
        termsArr = new String[terms.size()];
        defsArr = new String[defs.size()];
        int fontSize = 15;
        contentLabel.setFont(new Font("Comic Sans MS", Font.BOLD, fontSize));
        for (int i = 0; i < defsArr.length; i++) {
            defsArr[i] = "<html><div style='width:350px;'>" + defs.get(i) + "</div></html>";
        }
        for (int i = 0; i < termsArr.length; i++) {
            termsArr[i] = "<html><div style='width:350px;'>" + terms.get(i) + "</div></html>";
        }

        contentLabel.setText(termsArr[0]);
        add(back);
        add(next);

        titleLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        contentLabel.setBounds(40, 100, 450, 100);
        titleLabel.setBounds(25, 15, 100, 50);
        add(contentLabel);
        add(titleLabel);

    }

    public void flip() {
        Timer flipTimer = new Timer();
        TimerTask flipTask = new TimerTask() {
            boolean firstSide = true;

            public void run() {
                if (firstSide) {
                    height -= 2;
                    y++;
                } else {
                    height += 2;
                    y--;
                }
                if (height <= 0) {
                    firstSide = false;
                }
                if (height == 300) {
                    flipTimer.cancel();
                    timerRunning = false;
                    contentLabel.setVisible(true);
                    titleLabel.setVisible(true);
                }
                card = new Rectangle2D.Double(x, y, width, height);
                border = new Rectangle2D.Double(x-5,y-5, width+10,height+10);
                repaint();
            }
        };
        switch (titleLabel.getText()) {
            case "Term:":
                titleLabel.setText("Definition:");
                contentLabel.setText(defsArr[currentIndex]);
                break;
            default:
                titleLabel.setText("Term:");
                contentLabel.setText(termsArr[currentIndex]);
                break;

        }
        contentLabel.setVisible(false);
        titleLabel.setVisible(false);

        if (!timerRunning) {
            flipTimer.schedule(flipTask, 0, 1);
            timerRunning = true;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fill(border);
        g2.setColor(Utilities.getFlashcardColor());
        g2.fill(card);

    }

    public void updateContentLabel(boolean isTerms) {
        if (isTerms) {
            contentLabel.setText(termsArr[currentIndex]);
        } else {
            contentLabel.setText(defsArr[currentIndex]);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
