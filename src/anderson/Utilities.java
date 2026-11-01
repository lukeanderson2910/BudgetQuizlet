/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderson;

import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author Owner
 */
public class Utilities {

    public static String getThemedColor(boolean correct) {
        String lafName = UIManager.getLookAndFeel().getName();
        if (lafName.equals("FlatLaf Dark")) {
            if (correct) {
                return "#228B22";
            } else {
                return "#8b0000";
            }
        } else {
            if (correct) {
                return "#90EE90";
            } else {
                return "#FF474C";
            }
        }
    }

    public static Color getFlashcardColor() {
        if (UIManager.getLookAndFeel().getName().equals("FlatLaf Dark")) {
            return new Color(96, 98, 101);
        } else {
            return new Color(220, 220, 220);
        }

    }

    public static Color getClickedFlashcardColor() {
        if (UIManager.getLookAndFeel().getName().equals("FlatLaf Dark")) {
            return new Color(136, 138, 141);
        } else {
            return new Color(190, 190, 190);
        }
    }

}
