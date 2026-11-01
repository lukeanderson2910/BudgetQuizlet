/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderson;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Owner
 */
public class MatchingTimedPanel extends JPanel {

    private MatchingShape[] blocks;
    private boolean started = false;
    private double time = 0;
    private JLabel timeLabel = new JLabel("Time: " + time);
    private Timer secondTimer;

    private StudySet set;
    private String[] texts;
    private ArrayList<Integer> usedIndexes = new ArrayList();
    private ArrayList<String[]> actualSet = new ArrayList();
    private JDialog parent;

    public MatchingTimedPanel(JDialog parent, StudySet set) {
        this.parent = parent;
        setLayout(null);
        ArrayList<String> terms = new ArrayList();
        ArrayList<String> defs = new ArrayList();
        Object[] termsInSet = set.getTerms().toArray();
        Object[] defsInSet = set.getDefinitions().toArray();

        for (int i = 0; i < set.getTerms().size(); i++) {
            String[] termAndDef = {"<html><div style='width: 120px;'>" + termsInSet[i] + "</div></html>", "<html><div style='width: 120px;'>" + defsInSet[i] + "</div></html>"};
            actualSet.add(termAndDef);
        }
        Collections.shuffle(actualSet);

        this.set = new StudySet(terms, defs, "");
        if (set.getTerms().size() >= 12) {
            blocks = new MatchingShape[12];
        } else {
            blocks = new MatchingShape[set.getTerms().size() * 2];
        }

        texts = new String[blocks.length];
        for (int i = 0; i < texts.length / 2; i++) {
            texts[i] = actualSet.get(i)[0];
            texts[i + (texts.length / 2)] = actualSet.get(i)[1];
        }
        JButton startButton = new JButton("Click me to start!");
        startButton.setBounds(1300/2-75, 300, 150, 25);
        timeLabel.setBounds(1300/2-150, 0, 1000, 100);
        timeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        MatchingTimedPanel panelParent = this;
        
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initComponents();
                startButton.setEnabled(false);
                startButton.setVisible(false);
                started = true;
                panelParent.add(timeLabel);
                secondTimer = new Timer (100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        time += 0.1;
                        String label = String.format("%.1f\n", time);
                        
                        timeLabel.setText("Time: " + label);
                        if(allShapesBroken()) {
                            secondTimer.stop();
                            end();
                        }
                        
                    }
                    
                });
                secondTimer.start();
            }
        });
        add(startButton);

    }
    
    public void end() {
        parent.dispose();
    }

    public Object[] clickedShape(MouseEvent e) {
        boolean clicked = false;
        Object[] info = new Object[2];
        for (MatchingShape shape : blocks) {
            if (shape.getShape().contains(e.getPoint())) {
                clicked = true;
                info[1] = shape;
            }
        }
        info[0] = clicked;
        return info;
    }
    
    public boolean allShapesBroken() {
        for(MatchingShape shape : blocks) {
            if(!shape.isBroken()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String[]> getSet() {
        return actualSet;
    }

    public void initComponents() {
        int blockWidth = 200;
        int blockHeight = 100;
        int blockX = 145;
        int blockY = 150;
        int blockYOffset = 150;
        int blockXOffset = 250;
        int k = 0;
        int j = 0;
        for (int i = 0; i < blocks.length; i++) {
            Shape shape;
            shape = new Rectangle2D.Double(blockX + (j * blockXOffset), blockY + (blockYOffset * k), blockWidth, blockHeight);
            double iDouble = i;
            int halfSize = blocks.length / 2;
            blocks[i] = new MatchingShape(this, shape, Utilities.getFlashcardColor(), getInfo());
            j++;
            if ((i + 1) % 4 == 0 && i != 0) {
                k++;
                j = 0;
            }

        }
        addMouseListener(new MouseAdapter() {
            int clicked = 0;
            MatchingShape prevShape;

            public void mousePressed(MouseEvent e) {
                Object[] info = clickedShape(e);
                MatchingShape currentShape = (MatchingShape) info[1];
                if ((boolean) info[0]) {
                    if (clicked == 0) {
                        prevShape = currentShape;
                        currentShape.setColor(Utilities.getClickedFlashcardColor());
                        clicked = 1;
                    } else {
                        if (prevShape.goesTogether(currentShape.getText())) {
                            prevShape.setBroken(true);
                            currentShape.setBroken(true);
                        }
                        prevShape.setColor(Utilities.getFlashcardColor());
                        clicked = 0;

                    }
                }
                repaint();
            }
        });

        repaint();

    }

    public String getInfo() {
        Random rand = new Random();
        int index = rand.nextInt(texts.length);
        while (isUsed(index)) {
            index = rand.nextInt(texts.length);
        }
        usedIndexes.add(index);
        return texts[index];
    }

    public boolean isUsed(int index) {
        for (Integer ind : usedIndexes) {
            if (index == ind) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (started) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int i = 0;
            for (MatchingShape block : blocks) {
                g2.setColor(block.getColor());
                if (!block.isBroken()) {
                    g2.fill(block.getShape());
                }

            }
        }

    }

    public double getRecord() {
        double timeCon = Double.parseDouble(String.format("%.1f\n", time));
        return timeCon;
    }
    
}



class MatchingShape {

    Shape shape;
    Color color;
    String text;
    boolean broken = false;
    JLabel label;
    ArrayList<String[]> actualSet;
    int indexInActualSet;
    boolean isDef;

    public MatchingShape(MatchingTimedPanel parent, Shape shape, Color color, String text) {
        this.shape = shape;
        this.color = color;
        this.text = text;
        actualSet = parent.getSet();
        label = new JLabel(getText());
        label.setBounds(getX() + 10, getY()-150, getWidth(), getHeight()+300);
        parent.add(label);
        for (int i = 0; i < actualSet.size(); i++) {
            if (actualSet.get(i)[0].equals(text)) {
                indexInActualSet = i;
                isDef = false;
            } else if (actualSet.get(i)[1].equals(text)) {
                indexInActualSet = i;
                isDef = true;
            }
        }
    }

    public boolean goesTogether(String text) {
        if (isDef) {
            return actualSet.get(indexInActualSet)[0].equals(text);
        } else {
            return actualSet.get(indexInActualSet)[1].equals(text);
        }
    }

    public Shape getShape() {
        return shape;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
        if (broken) {
            label.setVisible(false);
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return (int) Math.floor(shape.getBounds().getX());
    }

    public int getY() {
        return (int) Math.floor(shape.getBounds().getY());
    }

    public int getWidth() {
        return (int) shape.getBounds().getWidth();
    }

    public int getHeight() {
        return (int) shape.getBounds().getHeight();
    }
}
