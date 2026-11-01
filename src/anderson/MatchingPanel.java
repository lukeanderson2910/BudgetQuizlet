/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderson;

/**
 *
 * @author Owner
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MatchingPanel extends JPanel {

    private Shape block1, block2, block3, block4, block5, place1, place2, place3, place4, place5;
    private int block1X = 70, block2X = 320, block3X = 570, block4X = 200, block5X = 450, block1Y = 500, block2Y = 500, block3Y = 500, block4Y = 600, block5Y = 600;
    private JLabel block1Label, block2Label, block3Label, block4Label, block5Label, place1Label, place2Label, place3Label, place4Label, place5Label;
    private ArrayList<Shape> places = new ArrayList();
    private ArrayList<Shape> blocks = new ArrayList();
    private final Shape[] heldBlock = new Shape[1];
    private String[][] questionInfo;
    private boolean block1Held, block2Held, block3Held, block4Held, block5Held;
    private ArrayList<String> originalPlaceTexts = new ArrayList(), originalBlockTexts = new ArrayList();
    private ArrayList<String> blockTexts = new ArrayList();
    private ArrayList<String> placeTexts = new ArrayList();
    private boolean submitted = false;
    private Color block1Color = Color.BLACK, block2Color = Color.BLACK, block3Color = Color.BLACK, block4Color = Color.BLACK, block5Color = Color.black;

    public MatchingPanel(String[][] q, String titleQuestionText, int y) {
        questionInfo = q;
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, titleQuestionText, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 13)));
        setBounds(50, y, 850, 700);
        setLayout(null);
        init();

    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public int getBlockPlace(int blockNumber) {
        String correct1 = originalBlockTexts.get(0) + originalPlaceTexts.get(0);
        String correct2 = originalBlockTexts.get(1) + originalPlaceTexts.get(1);
        String correct3 = originalBlockTexts.get(2) + originalPlaceTexts.get(2);
        String correct4 = originalBlockTexts.get(3) + originalPlaceTexts.get(3);
        String correct5 = originalBlockTexts.get(4) + originalPlaceTexts.get(4);
        correct1 = correct1.replaceAll("<[^>]*>", "");
        correct2 = correct2.replaceAll("<[^>]*>", "");
        correct3 = correct3.replaceAll("<[^>]*>", "");
        correct4 = correct4.replaceAll("<[^>]*>", "");
        correct5 = correct5.replaceAll("<[^>]*>", "");
        for (int i = 0; i < 5; i++) {
            String currentBlockText = blockTexts.get(blockNumber) + placeTexts.get(i);
            currentBlockText = currentBlockText.replaceAll("<[^>]*>", "");
            if (currentBlockText.equals(correct1) || currentBlockText.equals(correct2) || currentBlockText.equals(correct3) || currentBlockText.equals(correct4) || currentBlockText.equals(correct5)) {
                return i + 1;
            }
        }

        return -1;
    }

    public boolean[] getBlocksInRightSpot() {
        boolean[] blocksInRightSpot = {getBlockPlace(0) == getPlaceNumber(block1), getBlockPlace(1) == getPlaceNumber(block2),
            getBlockPlace(2) == getPlaceNumber(block3), getBlockPlace(3) == getPlaceNumber(block4), getBlockPlace(4) == getPlaceNumber(block5)};
        
        block1Color = Color.decode(Utilities.getThemedColor(blocksInRightSpot[0]));
        block2Color = Color.decode(Utilities.getThemedColor(blocksInRightSpot[1]));
        block3Color = Color.decode(Utilities.getThemedColor(blocksInRightSpot[2]));
        block4Color = Color.decode(Utilities.getThemedColor(blocksInRightSpot[3]));
        block5Color = Color.decode(Utilities.getThemedColor(blocksInRightSpot[4]));
        repaint();
        return blocksInRightSpot;
    }

    private void init() {
        initializeGraphics();
        this.addMouseListener(new MouseAdapter() {
            Shape tempShape;

            public void mouseReleased(MouseEvent e) {
                if (!submitted) {
                    addToArrays();
                    tempShape = heldBlock[0];
                    block1Held = false;
                    block2Held = false;
                    block3Held = false;
                    block4Held = false;
                    block5Held = false;
                    if (tempShape != null) {
                        isBlockInSpot(block1, 0);
                    }
                    if (tempShape != null) {
                        isBlockInSpot(block2, 1);
                    }
                    if (tempShape != null) {
                        isBlockInSpot(block3, 2);
                    }
                    if (tempShape != null) {
                        isBlockInSpot(block4, 3);
                    }
                    if (tempShape != null) {
                        isBlockInSpot(block5, 4);
                    }

                    heldBlock[0] = null;
                }
            }

            public void addToArrays() {
                places.add(place1);
                places.add(place2);
                places.add(place3);
                places.add(place4);
                places.add(place5);
                blocks.add(block1);
                blocks.add(block2);
                blocks.add(block3);
                blocks.add(block4);
                blocks.add(block5);
            }

            public void isBlockInSpot(Shape block, int blockNumber) {
                int[] pos = new int[2];
                if (block.intersects(place1.getBounds2D()) && placeOpen(0, blockNumber)) {
                    pos[0] = 70;
                    pos[1] = 50;
                    boolean rightSpot = checkIfInRightSpot(blockNumber, 1);
                } else if (block.intersects(place2.getBounds2D()) && placeOpen(1, blockNumber)) {
                    pos[0] = 70;
                    pos[1] = 125;
                    boolean rightSpot = checkIfInRightSpot(blockNumber, 2);
                } else if (block.intersects(place3.getBounds2D()) && placeOpen(2, blockNumber)) {
                    pos[0] = 70;
                    pos[1] = 200;
                    boolean rightSpot = checkIfInRightSpot(blockNumber, 3);
                } else if (block.intersects(place4.getBounds2D()) && placeOpen(3, blockNumber)) {
                    pos[0] = 70;
                    pos[1] = 275;
                    boolean rightSpot = checkIfInRightSpot(blockNumber, 4);
                } else if (block.intersects(place5.getBounds2D()) && placeOpen(4, blockNumber)) {
                    pos[0] = 70;
                    pos[1] = 350;

                } else if (block.intersects(tempShape.getBounds2D()) && !block.equals(tempShape) && getPlaceNumber(block) != -1) {
                    pos[0] = (int) block.getBounds().getX();
                    pos[1] = (int) block.getBounds().getY();
                    if (tempShape.equals(block1)) {
                        placeBlock(0, pos);
                    }
                    if (tempShape.equals(block2)) {
                        placeBlock(1, pos);
                    }
                    if (tempShape.equals(block3)) {
                        placeBlock(2, pos);
                    }
                    if (tempShape.equals(block4)) {
                        placeBlock(3, pos);
                    }
                    if (tempShape.equals(block5)) {
                        placeBlock(4, pos);
                    }
                    tempShape = null;
                    pos = getOriginalPosition(blockNumber);
                } else {
                    pos = getOriginalPosition(blockNumber);
                }
                placeBlock(blockNumber, pos);
            }

            public int[] getOriginalPosition(int blockNumber) {
                int[] pos = new int[2];
                switch (blockNumber) {
                    case 0 -> {
                        pos[0] = 70;
                        pos[1] = 500;
                    }
                    case 1 -> {
                        pos[0] = 320;
                        pos[1] = 500;
                    }
                    case 2 -> {
                        pos[0] = 570;
                        pos[1] = 500;
                    }
                    case 3 -> {
                        pos[0] = 200;
                        pos[1] = 600;
                    }
                    default -> {
                        pos[0] = 450;
                        pos[1] = 600;
                    }
                }
                return pos;
            }

            public boolean placeOpen(int placeNumber, int blockNumber) {
                Shape place = places.get(placeNumber);
                Rectangle2D b1 = block1.getBounds2D();
                Rectangle2D b2 = block2.getBounds2D();
                Rectangle2D b3 = block3.getBounds2D();
                Rectangle2D b4 = block4.getBounds2D();
                Rectangle2D b5 = block5.getBounds2D();
                // return !(place.intersects(b1) || place.intersects(b2) || place.intersects(b3) || place.intersects(b4) || place.intersects(b5));

                switch (blockNumber) {
                    case 0 -> {
                        return !(place.intersects(b2) || place.intersects(b3) || place.intersects(b4) || place.intersects(b5));
                    }

                    case 1 -> {
                        return !(place.intersects(b1) || place.intersects(b3) || place.intersects(b4) || place.intersects(b5));
                    }

                    case 2 -> {
                        return !(place.intersects(b1) || place.intersects(b2) || place.intersects(b4) || place.intersects(b5));
                    }

                    case 3 -> {
                        return !(place.intersects(b1) || place.intersects(b2) || place.intersects(b3) || place.intersects(b5));
                    }

                    default -> {
                        return !(place.intersects(b1) || place.intersects(b2) || place.intersects(b3) || place.intersects(b4));
                    }
                }
            }

            public void placeBlock(int blockNumber, int[] pos) {
                switch (blockNumber) {
                    case 0 -> {
                        block1X = pos[0];
                        block1Y = pos[1];
                    }
                    case 1 -> {

                        block2X = pos[0];
                        block2Y = pos[1];
                    }
                    case 2 -> {
                        block3X = pos[0];
                        block3Y = pos[1];
                    }
                    case 3 -> {
                        block4X = pos[0];
                        block4Y = pos[1];
                    }
                    default -> {
                        block5X = pos[0];
                        block5Y = pos[1];
                    }
                }
                updateLabelPos();
                repaint();

            }

        });
        this.addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                if (!submitted) {
                    if (block1.contains(e.getPoint()) && checkIfAlreadyBeingHeld(block1)) {
                        block1X = e.getX() - 100;
                        block1Y = e.getY() - 25;
                        heldBlock[0] = new Rectangle2D.Double(block1X, block1Y, 200, 50);
                        block1Held = true;
                    } else if (block2.contains(e.getPoint()) && checkIfAlreadyBeingHeld(block2)) {
                        block2X = e.getX() - 100;
                        block2Y = e.getY() - 25;
                        block2Held = true;
                        heldBlock[0] = new Rectangle2D.Double(block2X, block2Y, 200, 50);
                    } else if (block3.contains(e.getPoint()) && checkIfAlreadyBeingHeld(block3)) {
                        block3X = e.getX() - 100;
                        block3Y = e.getY() - 25;
                        block3Held = true;
                        heldBlock[0] = new Rectangle2D.Double(block3X, block3Y, 200, 50);
                    } else if (block4.contains(e.getPoint()) && checkIfAlreadyBeingHeld(block4)) {
                        block4Held = true;
                        heldBlock[0] = new Rectangle2D.Double(block4X, block4Y, 200, 50);
                    } else if (block5.contains(e.getPoint()) && checkIfAlreadyBeingHeld(block5)) {
                        block5X = e.getX() - 100;
                        block5Y = e.getY() - 25;
                        block5Held = true;
                        heldBlock[0] = new Rectangle2D.Double(block5X, block5Y, 200, 50);
                    }

                    if (block1Held) {
                        block1X = e.getX() - 100;
                        block1Y = e.getY() - 25;
                    }
                    if (block2Held) {
                        block2X = e.getX() - 100;
                        block2Y = e.getY() - 25;
                    }
                    if (block3Held) {
                        block3X = e.getX() - 100;
                        block3Y = e.getY() - 25;
                    }
                    if (block4Held) {
                        block4X = e.getX() - 100;
                        block4Y = e.getY() - 25;
                    }
                    if (block5Held) {
                        block5X = e.getX() - 100;
                        block5Y = e.getY() - 25;
                    }

                    updateLabelPos();
                    repaint();
                }
            }

            public boolean checkIfAlreadyBeingHeld(Shape block) {
                // return !(heldBlock[0].equals(block1) || heldBlock[0].equals(block2) || heldBlock[0].equals(block3) || heldBlock[0].equals(block4) || heldBlock[0].equals(block5));
                return block.equals(heldBlock[0]) || heldBlock[0] == null;
            }
        }
        );
        addLabels();
        revalidate();
        updateLabelPos();
        repaint();
    }

    public boolean checkIfInRightSpot(int blockNumber, int placeNumber) {
        return placeNumber == getBlockPlace(blockNumber);
    }

    public int getPlaceNumber(Shape block) {
        if (place1.contains(block.getBounds2D())) {
            return 1;
        } else if (place2.contains(block.getBounds2D())) {
            return 2;
        } else if (place3.contains(block.getBounds2D())) {
            return 3;
        } else if (place4.contains(block.getBounds2D())) {
            return 4;
        } else if (place5.contains(block.getBounds2D())) {
            return 5;
        }

        return -1;
    }

    public String getCurrentBlockAndPlaceTexts(int blockNumber, int placeNumber) {
        String blockPlaceText = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (blockNumber == j && placeNumber == i) {
                    blockPlaceText += blockTexts.get(j) + placeTexts.get(i);
                }
            }
        }
        return blockPlaceText.replaceAll("<[^>]*>", "");
    }

    public void addLabels() {

        for (int i = 0; i < 5; i++) {
            blockTexts.add(getMessage(questionInfo[i][0], "150"));
            placeTexts.add(getMessage(questionInfo[i][1], "400"));
            originalBlockTexts.add(getMessage(questionInfo[i][0], "150"));
            originalPlaceTexts.add(getMessage(questionInfo[i][1], "150"));
        }

        Collections.shuffle(blockTexts);
        Collections.shuffle(placeTexts);

        block1Label = new JLabel(blockTexts.get(0));
        block1Label.setFont(new Font("Comic Sans MS", 5, 10));
        block1Label.setBounds(block1X + 5, block1Y, 200, 100);
        add(block1Label);

        block2Label = new JLabel(blockTexts.get(1));
        block2Label.setFont(new Font("Comic Sans MS", 5, 10));
        block2Label.setBounds(block2X + 5, block2Y, 200, 100);
        add(block2Label);

        block3Label = new JLabel(blockTexts.get(2));
        block3Label.setFont(new Font("Comic Sans MS", 5, 10));
        block3Label.setBounds(block3X + 5, block3Y, 200, 100);
        add(block3Label);

        block4Label = new JLabel(blockTexts.get(3));
        block4Label.setFont(new Font("Comic Sans MS", 5, 10));
        block4Label.setBounds(block4X + 5, block4Y, 200, 100);
        add(block4Label);

        block5Label = new JLabel(blockTexts.get(4));
        block5Label.setFont(new Font("Comic Sans MS", 5, 10));
        block5Label.setBounds(block5X + 5, block5Y, 600, 100);
        add(block5Label);

        place1Label = new JLabel(placeTexts.get(0));
        place1Label.setFont(new Font("Comic Sans MS", 5, 10));
        place1Label.setBounds(280, 50, 600, 50);
        add(place1Label);

        place2Label = new JLabel(placeTexts.get(1));
        place2Label.setFont(new Font("Comic Sans MS", 5, 10));
        place2Label.setBounds(280, 125, 600, 50);
        add(place2Label);

        place3Label = new JLabel(placeTexts.get(2));
        place3Label.setFont(new Font("Comic Sans MS", 5, 10));
        place3Label.setBounds(280, 200, 600, 50);
        add(place3Label);

        place4Label = new JLabel(placeTexts.get(3));
        place4Label.setFont(new Font("Comic Sans MS", 5, 10));
        place4Label.setBounds(280, 275, 600, 50);
        add(place4Label);

        place5Label = new JLabel(placeTexts.get(4));
        place5Label.setFont(new Font("Comic Sans MS", 5, 10));
        place5Label.setBounds(280, 350, 600, 50);
        add(place5Label);
    }

    public void updateLabelPos() {
        block1Label.setBounds(block1X + 5, block1Y-25, 200, 100);
        block2Label.setBounds(block2X + 5, block2Y-25, 200, 100);
        block3Label.setBounds(block3X + 5, block3Y-25, 200, 100);
        block4Label.setBounds(block4X + 5, block4Y-25, 200, 100);
        block5Label.setBounds(block5X + 5, block5Y-25, 200, 100);
    }

    public String getMessage(String info, String thickness) {
        return "<html><div style='width:" + thickness + "px;'>" + info + "</div></html>";
    }

    public void initializeGraphics() {
        block1 = new Rectangle2D.Double(block1X, block1Y, 200, 50);
        block2 = new Rectangle2D.Double(block2X, block2Y, 200, 50);
        block3 = new Rectangle2D.Double(block3X, block3Y, 200, 50);
        block4 = new Rectangle2D.Double(block4X, block4Y, 200, 50);
        block5 = new Rectangle2D.Double(block5X, block5Y, 200, 50);
        place1 = new Rectangle2D.Double(70, 50, 200, 50);
        place2 = new Rectangle2D.Double(70, 125, 200, 50);
        place3 = new Rectangle2D.Double(70, 200, 200, 50);
        place4 = new Rectangle2D.Double(70, 275, 200, 50);
        place5 = new Rectangle2D.Double(70, 350, 200, 50);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        initializeGraphics();
        Graphics2D g2 = (Graphics2D) g;
        String laf = UIManager.getLookAndFeel().toString();
        if (laf.equals("[FlatLaf Light Look and Feel - com.formdev.flatlaf.FlatLightLaf]")) {
            g.setColor(new Color(220, 220, 220));
        } else if (laf.equals("[FlatLaf Dark Look and Feel - com.formdev.flatlaf.FlatDarkLaf]")) {
            g.setColor(new Color(96, 98, 101));
        } else {
            g.setColor(new Color(201, 203, 211));
        }

        g2.fill(place1);
        g2.fill(place2);
        g2.fill(place3);
        g2.fill(place4);
        g2.fill(place5);

        g.setColor(block1Color);
        g2.draw(block1);
        g.setColor(block2Color);
        g2.draw(block2);
        g.setColor(block3Color);
        g2.draw(block3);
        g.setColor(block4Color);
        g2.draw(block4);
        g.setColor(block5Color);
        g2.draw(block5);

    }
}
