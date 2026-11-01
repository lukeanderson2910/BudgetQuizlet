/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package anderson;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Owner
 */
public class TestDialog extends javax.swing.JDialog {

    /**
     * Creates new form TestDialog
     */
    private StudySet testedSet;
    private String answerWith;
    private ArrayList<String> questionTypes;
    private int questions;
    private int y = 10;
    private int questionNumber = 1;
    private int multipleQuestions = 0;
    private int height = 0;
    private ArrayList<Integer> usedIndex = new ArrayList();
    private ArrayList<Object[]> multipleChoiceQuestions = new ArrayList();
    private ArrayList<Object[]> writtenQuestions = new ArrayList();
    private ArrayList<Object[]> trueFalseQuestions = new ArrayList();
    private ArrayList<MatchingPanel> matchingQuestions = new ArrayList();
    private ArrayList<Object[]> questionsArrList = new ArrayList();
    private ArrayList<String> definitions;
    private ArrayList<String> terms;
    private ArrayList<JPanel> questionPanels = new ArrayList();
    private Random rand = new Random();
    private TestDialog parent = this;

    private JScrollPane scrollPane;

    public TestDialog(BudgetQuizlet parent, boolean modal, StudySet testedSet, String answerWith, ArrayList<String> questionTypes, int questions) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        makeScrollable();
        this.testedSet = testedSet;
        this.answerWith = answerWith;
        this.questionTypes = questionTypes;
        this.questions = questions;
        terms = testedSet.getTerms();
        definitions = testedSet.getDefinitions();
        makeQuestions(questionTypes);

    }

    public void makeQuestions(ArrayList<String> questionTypes) {
        if (questionTypes.size() == 1) {

            if (questionTypes.get(0).equals("M/C")) {

                for (int i = 0; i < questions; i++) {
                    makeMCQuestion();
                }
            }

            if (questionTypes.get(0).equals("WT")) {
                for (int i = 0; i < questions; i++) {
                    makeWrittenQuestion();
                }
            }

            if (questionTypes.get(0).equals("T/F")) {
                for (int i = 0; i < questions; i++) {
                    makeTFQuestion();
                }
            }

            if (questionTypes.get(0).equals("MC")) {
                for (int i = 0; i < questions / 5; i++) {
                    makeMatchingQuestion();
                }

            }
        } else if (questionTypes.size() == 2) {
            if (questionTypes.get(0).equals("M/C") && questionTypes.get(1).equals("T/F")) {
                for (int i = 0; i < questions; i++) {
                    if (rand.nextInt(2) == 0) {
                        makeTFQuestion();
                    } else {
                        makeMCQuestion();
                    }
                }
            }

            if (questionTypes.get(0).equals("M/C") && questionTypes.get(1).equals("WT")) {
                for (int i = 0; i < questions; i++) {
                    if (rand.nextInt(2) == 0) {
                        makeWrittenQuestion();
                    } else {
                        makeMCQuestion();
                    }
                }
            }

            if (questionTypes.get(0).equals("M/C") && questionTypes.get(1).equals("MC")) {
                int index = questions;
                while (index > 0) {
                    if (rand.nextInt(2) == 0 && index >= 5) {
                        makeMatchingQuestion();
                        index -= 5;
                    } else {
                        makeMCQuestion();
                        index--;
                    }
                }
            }

            if (questionTypes.get(0).equals("T/F") && questionTypes.get(1).equals("WT")) {
                for (int i = 0; i < questions; i++) {
                    if (rand.nextInt(2) == 0) {
                        makeTFQuestion();
                    } else {
                        makeWrittenQuestion();
                    }
                }
            }

            if (questionTypes.get(0).equals("T/F") && questionTypes.get(1).equals("MC")) {
                int index = questions;
                while (index > 0) {
                    if (rand.nextInt(2) == 0 && index >= 5) {
                        makeMatchingQuestion();
                        index -= 5;
                    } else {
                        makeTFQuestion();
                        index--;
                    }
                }
            }

            if (questionTypes.get(0).equals("WT")) {
                int index = questions;
                while (index > 0) {
                    if (rand.nextInt(2) == 0 && index >= 5) {
                        makeMatchingQuestion();
                        index -= 5;
                    } else {
                        makeWrittenQuestion();
                        index--;
                    }
                }
            }
        } else if (questionTypes.size() == 3) {
            if (questionTypes.get(0).equals("M/C") && questionTypes.get(1).equals("T/F") && questionTypes.get(2).equals("WT")) {
                for (int i = 0; i < questions; i++) {
                    int chosenQuestion = rand.nextInt(3);
                    if (chosenQuestion == 0) {
                        makeMCQuestion();
                    } else if (chosenQuestion == 1) {
                        makeTFQuestion();
                    } else {
                        makeWrittenQuestion();
                    }
                }
            }

            if (questionTypes.get(0).equals("M/C") && questionTypes.get(1).equals("T/F") && questionTypes.get(2).equals("MC")) {
                int index = questions;
                while (index > 0) {
                    int chosenQuestion = rand.nextInt(3);
                    if (chosenQuestion == 0 && index >= 5) {
                        makeMatchingQuestion();
                        index -= 5;
                    } else if (chosenQuestion == 1) {
                        makeTFQuestion();
                        index--;
                    } else {
                        makeMCQuestion();
                        index--;
                    }
                }
            }

            if (questionTypes.get(0).equals("M/C") && questionTypes.get(1).equals("WT") && questionTypes.get(2).equals("MC")) {
                int index = questions;
                while (index > 0) {
                    int chosenQuestion = rand.nextInt(3);
                    if (chosenQuestion == 0 && index >= 5) {
                        makeMatchingQuestion();
                        index -= 5;
                    } else if (chosenQuestion == 1) {
                        makeWrittenQuestion();
                        index--;
                    } else {
                        makeMCQuestion();
                        index--;
                    }
                }
            }

            if (questionTypes.get(0).equals("T/F")) {
                int index = questions;
                while (index > 0) {
                    int chosenQuestion = rand.nextInt(3);
                    if (chosenQuestion == 0 && index >= 5) {
                        makeMatchingQuestion();
                        index -= 5;
                    } else if (chosenQuestion == 1) {
                        makeWrittenQuestion();
                        index--;
                    } else {
                        makeTFQuestion();
                        index--;
                    }
                }
            }

        } else {
            int index = questions;
            while (index > 0) {
                int chosenQuestion = rand.nextInt(4);
                if (chosenQuestion == 0 && index >= 5) {
                    makeMatchingQuestion();
                    index -= 5;
                } else if (chosenQuestion == 1) {
                    makeWrittenQuestion();
                    index--;
                } else if (chosenQuestion == 2) {
                    makeTFQuestion();
                    index--;
                } else {
                    makeMCQuestion();
                    index--;
                }
            }
        }

        JButton submitButton = new JButton("Submit");
        scrollPanel.setPreferredSize(new Dimension(scrollPanel.getWidth(), height + 150));
        submitButton.setBounds(50, height + 50, 75, 25);
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(parent, "Are you sure you want to submit?", "Submit?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    submit();
                }
            }
        });
        scrollPanel.add(submitButton);
    }

    public void submit() {
        ArrayList<Integer> wrongIndexes = new ArrayList();
        ArrayList<Integer> correctIndexes = new ArrayList();
        ArrayList<Integer[]> correctMatchingIndexes = new ArrayList();
        ArrayList<Integer[]> wrongMatchingIndexes = new ArrayList();
        int correctAnswers = 0;
        for (int i = 0; i < multipleChoiceQuestions.size(); i++) {
            Object[] question = multipleChoiceQuestions.get(i);

            if (correctMCAnswerSelected(question)) {
                correctAnswers++;
                correctIndexes.add(i);
            } else {
                wrongIndexes.add(i);
            }
            for (int k = 0; k < 4; k++) {
                JRadioButton tempButton = (JRadioButton) question[k];
                tempButton.setEnabled(false);
            }
        }

        for (int i = 0; i < trueFalseQuestions.size(); i++) {
            Object[] question = trueFalseQuestions.get(i);
            if (correctTFQuestionSelected(question)) {
                correctAnswers++;
                correctIndexes.add(i);
            } else {
                wrongIndexes.add(i);
            }
            JRadioButton buttonOne = (JRadioButton) question[0];
            JRadioButton buttonTwo = (JRadioButton) question[1];
            buttonOne.setEnabled(false);
            buttonTwo.setEnabled(false);
        }

        for (int i = 0; i < matchingQuestions.size(); i++) {
            boolean[] correctSpots = matchingQuestions.get(i).getBlocksInRightSpot();
            int j = 0;
            for (boolean placedCorrectly : correctSpots) {
                Integer[] matchingQuestion = {i, j};
                if (placedCorrectly) {
                    correctAnswers++;
                    correctMatchingIndexes.add(matchingQuestion);
                } else {
                    wrongMatchingIndexes.add(matchingQuestion);
                }
                j++;
            }
            matchingQuestions.get(i).setSubmitted(true);
        }

        for (int i = 0; i < writtenQuestions.size(); i++) {
            String correctAnswerString = (String) writtenQuestions.get(i)[0];
            JTextArea responseArea = (JTextArea) writtenQuestions.get(i)[1];
            String userAnswer = responseArea.getText();
            boolean correct = userAnswer.equalsIgnoreCase(correctAnswerString);
            if (correct) {
                correctAnswers++;
                correctIndexes.add(i);
            } else {
                wrongIndexes.add(i);
            }
            JPanel questionPanel = (JPanel) writtenQuestions.get(i)[2];
            formatEditorPane(correct, responseArea, questionPanel, correctAnswerString);
        }

        for (JPanel questionPanel : questionPanels) {
            int y = questionPanel.getY();
            questionPanel.setBounds(questionPanel.getX(), y + 150, questionPanel.getWidth(), questionPanel.getHeight());

        }

        correctAnswers = correctIndexes.size() + correctMatchingIndexes.size();

        JLabel resultsLabel = new JLabel("Your Score: " + correctAnswers + "/" + (correctAnswers + wrongMatchingIndexes.size() + wrongIndexes.size()), JLabel.CENTER);
        resultsLabel.setBounds(0, 10, getWidth(), 40);
        resultsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        scrollPanel.add(resultsLabel);
        scrollPanel.revalidate();
        scrollPanel.repaint();

        scrollPane.getVerticalScrollBar().setValue(0);
    }

    public void formatEditorPane(boolean correct, JTextArea area, JPanel questionPanel, String correctAnswer) {
        String codedText;
        String currentText = area.getText();
        String color = Utilities.getThemedColor(correct);
        if (correct) {
            codedText = ("<html><span style='background: " + color + ";'>" + currentText + "</span></html>");
        } else {
            codedText = ("<html><span style='background: " + color + ";'>" + currentText + "</span><br><br>The correct answer was: " + correctAnswer + "</html>");
        }
        int x = area.getX();
        int y = area.getY();
        int width = area.getWidth();
        int height = area.getHeight();
        JEditorPane pane = new JEditorPane("text/html", codedText);
        pane.setBounds(x, y, width, height);
        pane.setEditable(false);
        questionPanel.remove(area);
        questionPanel.add(pane);
        questionPanel.revalidate();
        questionPanel.repaint();
    }

    public int getClosingDivTagIndex(String text) {
        char[] arr = text.toCharArray();
        int closingTagsFound = 0;
        int index = 0;
        for (char character : arr) {
            if (character == '>') {
                closingTagsFound++;
            }
            if (closingTagsFound == 2) {
                return index + 1;
            }
            index++;
        }
        return 0;
    }

    public boolean correctMCAnswerSelected(Object[] question) {
        JRadioButton opt1 = (JRadioButton) question[0];
        JRadioButton opt2 = (JRadioButton) question[1];
        JRadioButton opt3 = (JRadioButton) question[2];
        JRadioButton opt4 = (JRadioButton) question[3];
        String rightAnswer = (String) question[4];

        boolean opt1Correct = opt1.getText().equals(rightAnswer);
        boolean opt2Correct = opt2.getText().equals(rightAnswer);
        boolean opt3Correct = opt3.getText().equals(rightAnswer);
        boolean opt4Correct = opt4.getText().equals(rightAnswer);
        formatButton(opt1, opt1Correct);
        formatButton(opt2, opt2Correct);
        formatButton(opt3, opt3Correct);
        formatButton(opt4, opt4Correct);
        if (opt1.isSelected() && opt1Correct) {
            return true;
        } else if (opt2.isSelected() && opt2Correct) {
            return true;
        } else if (opt3.isSelected() && opt3Correct) {
            return true;
        } else if (opt4.isSelected() && opt4Correct) {
            return true;
        }
        return false;
    }

    public void formatButton(JRadioButton button, boolean correct) {
        String openingHTML, openingSpanTag, closingSpanTag, openingDivTag, closingDivTag, closingHTML, buttonText;
        if (correct) {
            openingSpanTag = "<span style='background: " + Utilities.getThemedColor(true) + ";'>";
        } else {
            openingSpanTag = "<span style='background: " + Utilities.getThemedColor(false) + ";'>";
        }
        if (button.getText().charAt(0) == '<') {
            openingHTML = button.getText().substring(0, 6);
            closingSpanTag = "</span>";
            openingDivTag = button.getText().substring(6, getClosingDivTagIndex(button.getText()));
            closingDivTag = "</div>";
            closingHTML = button.getText().substring(button.getText().length() - 7);
            buttonText = button.getText().replaceAll("<[^>]*>", "");
            button.setText(openingHTML + openingDivTag + openingSpanTag + buttonText + closingSpanTag + closingDivTag + closingHTML);
        } else {
            openingHTML = "<html>";
            closingSpanTag = "</span>";
            closingHTML = "</html>";
            buttonText = button.getText();
            button.setText(openingHTML + openingSpanTag + buttonText + closingSpanTag + closingHTML);
        }

    }

    public boolean correctTFQuestionSelected(Object[] question) {
        JRadioButton trueButton = (JRadioButton) question[0];
        JRadioButton falseButton = (JRadioButton) question[1];

        boolean correctAnswer = (boolean) question[2];
        if (correctAnswer) {
            formatButton(trueButton, true);
            formatButton(falseButton, false);
        } else {
            formatButton(trueButton, false);
            formatButton(falseButton, true);
        }

        if (trueButton.isSelected() && correctAnswer) {
            return true;
        } else if (falseButton.isSelected() && !correctAnswer) {
            return true;
        }

        return false;
    }

    public final void makeScrollable() {
        scrollPanel.setLayout(null);
        scrollPanel.setPreferredSize(new Dimension(1000, 397));
        scrollPane = new JScrollPane(scrollPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public final void makeWrittenQuestion() {
        JPanel questionPanel = new JPanel();

        int questionIndex = getQuestionIndex();

        String[] info = getQuestionInfo(questionIndex);

        String questionText = info[0];
        String rightAnswer = info[1];

        addAttributes(questionPanel, "<html>Question " + questionNumber + ": " + questionText + "</html>", false);

        JTextArea answerField = new JTextArea();
        JLabel infoLabel = new JLabel("Enter your response here:");
        answerField.setBounds(25, 70, 750, 90);
        infoLabel.setBounds(25, 20, 150, 60);
        questionPanel.add(answerField);
        questionPanel.add(infoLabel);

        scrollPanel.add(questionPanel);
        scrollPanel.revalidate();
        scrollPanel.repaint();

        questionNumber++;
        multipleQuestions++;

        Object[] writtenInfo = {rightAnswer, answerField, questionPanel};
        writtenQuestions.add(writtenInfo);
        questionsArrList.add(writtenInfo);
        questionPanels.add(questionPanel);
        increasePanelSize(questionPanel);
    }

    public final void makeMCQuestion() {
        String questionText;
        String rightAnswer;
        int questionIndex = getQuestionIndex();

        String[] info = getQuestionInfo(questionIndex);
        questionText = info[0];
        rightAnswer = info[1];

        String titleQuestionText = "<html>Question " + questionNumber + ": " + questionText + "</html>";

        JPanel questionPanel = new JPanel();

        addAttributes(questionPanel, titleQuestionText, false);

        Object[] options = makeOptions(questionIndex, rightAnswer);

        int maxWidth = 400;
        int x1 = 25;
        int x2 = x1 + maxWidth + 20;
        int y1 = 50;
        int y2 = y1 + 60;

        JRadioButton opt1 = (JRadioButton) options[0];
        JRadioButton opt2 = (JRadioButton) options[1];
        JRadioButton opt3 = (JRadioButton) options[2];
        JRadioButton opt4 = (JRadioButton) options[3];

        opt1.setBounds(x1, y1 - 30, maxWidth, 100);
        opt2.setBounds(x2, y1 - 30, maxWidth, 100);
        opt3.setBounds(x1, y2 - 10, maxWidth, 100);
        opt4.setBounds(x2, y2 - 10, maxWidth, 100);

        questionPanel.add(opt1);
        questionPanel.add(opt2);
        questionPanel.add(opt3);
        questionPanel.add(opt4);

        scrollPanel.add(questionPanel);

        increasePanelSize(questionPanel);

        scrollPanel.revalidate();
        scrollPanel.repaint();

        Object[] buttonsAndAnswer = {opt1, opt2, opt3, opt4, options[4], "M/C"};
        multipleChoiceQuestions.add(buttonsAndAnswer);
        questionsArrList.add(buttonsAndAnswer);
        questionPanels.add(questionPanel);
        multipleQuestions++;
        questionNumber++;

    }

    public final void makeTFQuestion() {
        JPanel questionPanel = new JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(5, 126, 497, 126);

            }

        };

        int questionIndex = getQuestionIndex();

        addAttributes(questionPanel, "<html>Question " + questionNumber + "</html>", true);

        JLabel separatorVertical = new JLabel("<html>|<br>|<br>|<br>|<br>|<br>|<br>|</html>");

        separatorVertical.setBounds(questionPanel.getWidth() / 2, -10, 10, questionPanel.getHeight() - 30);

        questionPanel.add(separatorVertical);

        JLabel option1TitleLabel = new JLabel("");
        JLabel option2TitleLabel = new JLabel("");
        JLabel option1Label = new JLabel("");
        JLabel option2Label = new JLabel("");

        Object[] termAndDef = getTrueFalseQuestionInfo(questionIndex);
        Object[] buttons = makeTrueFalseOptions(questionIndex, (String) termAndDef[0], (String) termAndDef[1], questionPanel);

        termAndDef[0] = "<html><div style='width:190px';>" + termAndDef[0] + "</div></html>";

        termAndDef[1] = "<html><div style='width:190px';>" + termAndDef[1] + "</div></html>";

        if (answerWith.equals("term")) {
            option2TitleLabel.setText("Term:");
            option1TitleLabel.setText("Definition:");

            option2Label.setText((String) termAndDef[0]);
            option1Label.setText((String) termAndDef[1]);

        } else {
            option1TitleLabel.setText("Term:");
            option2TitleLabel.setText("Definition:");

            option1Label.setText((String) termAndDef[0]);
            option2Label.setText((String) termAndDef[1]);
        }

        option1TitleLabel.setBounds(10, 30, 50, 10);
        option2TitleLabel.setBounds(questionPanel.getWidth() / 2 + 10, 30, 100, 10);

        option1Label.setBounds(10, 30, 300, 100);
        option2Label.setBounds(questionPanel.getWidth() / 2 + 10, 30, 300, 100);

        questionPanel.add(option1TitleLabel);
        questionPanel.add(option2TitleLabel);

        questionPanel.add(option1Label);
        questionPanel.add(option2Label);

        questionPanel.add((JRadioButton) buttons[0]);
        questionPanel.add((JRadioButton) buttons[1]);
        questionPanel.repaint();
        questionPanel.revalidate();
        scrollPanel.add(questionPanel);

        questionNumber++;
        multipleQuestions++;
        Object[] info = {buttons[0], buttons[1], termAndDef[2], "T/F"};

        trueFalseQuestions.add(info);
        questionPanels.add(questionPanel);
        questionsArrList.add(info);

        increasePanelSize(questionPanel);
    }

    public final void makeMatchingQuestion() {

        int[] indexes = new int[5];
        String[][] questionInfo = new String[5][2];

        for (int i = 0; i < 5; i++) {
            indexes[i] = getQuestionIndex();
            String questionText = getQuestionInfo(indexes[i])[0];
            String rightAnswer = getQuestionInfo(indexes[i])[1];
            questionInfo[i][0] = questionText;
            questionInfo[i][1] = rightAnswer;
        }

        MatchingPanel questionPanel = new MatchingPanel(questionInfo, "Questions " + questionNumber + "-" + (questionNumber + 4), y);
        y = questionPanel.getY() + questionPanel.getHeight() + 100;
        scrollPanel.add(questionPanel);
        questionNumber += 5;
        multipleQuestions += 5;
        increasePanelSize(questionPanel);
        matchingQuestions.add(questionPanel);
        Object[] info = {questionPanel, "MC"};
        questionsArrList.add(info);
        questionPanels.add(questionPanel);
    }

    public String[] getWrongAnswers(int rightIndex) {
        Random rand = new Random();
        String[] wrongAnswers = new String[3];
        ArrayList<Integer> usedIndexes = new ArrayList();

        while (usedIndexes.size() < 3) {
            int wIndex = rightIndex;
            while (wIndex == rightIndex) {
                wIndex = rand.nextInt(testedSet.getDefinitions().size());
            }

            boolean usable = true;
            for (int ind : usedIndexes) {
                if (wIndex == ind) {
                    usable = false;
                }
            }
            if (usable) {
                usedIndexes.add(wIndex);
            }
        }
        for (int i = 0; i < 3; i++) {
            if (answerWith.equals("term")) {
                wrongAnswers[i] = testedSet.getTerms().get(usedIndexes.get(i));
            } else {
                wrongAnswers[i] = testedSet.getDefinitions().get(usedIndexes.get(i));
            }

        }
        return wrongAnswers;
    }

    public int getQuestionIndex() {
        int questionIndex;
        while (true) {
            questionIndex = rand.nextInt(definitions.size());
            boolean usableIndex = true;

            for (int ind : usedIndex) {
                if (ind == questionIndex) {
                    usableIndex = false;
                }
            }
            if (usableIndex) {
                usedIndex.add(questionIndex);
                break;
            }
        }
        return questionIndex;
    }

    public void addAttributes(JPanel questionPanel, String titleQuestionText, boolean trueFalseQuestion) {
        questionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, titleQuestionText, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 13)));
        questionPanel.setLayout(null);
        if (trueFalseQuestion) {
            questionPanel.setBounds(50, y, 502, 200);
        } else {
            questionPanel.setBounds(50, y, 800, 200);
        }
        y = questionPanel.getY() + questionPanel.getHeight() + 100;
    }

    public Object[] makeOptions(int questionIndex, String rightAnswer) {
        ButtonGroup group = new ButtonGroup();

        JRadioButton opt1 = new JRadioButton("");
        JRadioButton opt2 = new JRadioButton("");
        JRadioButton opt3 = new JRadioButton("");
        JRadioButton opt4 = new JRadioButton("");
        group.add(opt1);
        group.add(opt2);
        group.add(opt3);
        group.add(opt4);

        String[] wrongAnswers = getWrongAnswers(questionIndex);
        int rightIndex = rand.nextInt(4) + 1;

        int maxWidth = 250;
        String wrappedRight = "<html><div style='width:" + maxWidth + "px;'>" + rightAnswer + "</div></html>";
        String wrappedWrong1 = "<html><div style='width:" + maxWidth + "px;'>" + wrongAnswers[0] + "</div></html>";
        String wrappedWrong2 = "<html><div style='width:" + maxWidth + "px;'>" + wrongAnswers[1] + "</div></html>";
        String wrappedWrong3 = "<html><div style='width:" + maxWidth + "px;'>" + wrongAnswers[2] + "</div></html>";

        switch (rightIndex) {
            case 1 -> {
                opt1.setText(wrappedRight);
                opt2.setText(wrappedWrong1);
                opt3.setText(wrappedWrong2);
                opt4.setText(wrappedWrong3);
            }
            case 2 -> {
                opt2.setText(wrappedRight);
                opt1.setText(wrappedWrong1);
                opt3.setText(wrappedWrong2);
                opt4.setText(wrappedWrong3);
            }
            case 3 -> {
                opt3.setText(wrappedRight);
                opt1.setText(wrappedWrong1);
                opt2.setText(wrappedWrong2);
                opt4.setText(wrappedWrong3);
            }
            case 4 -> {
                opt4.setText(wrappedRight);
                opt1.setText(wrappedWrong1);
                opt2.setText(wrappedWrong2);
                opt3.setText(wrappedWrong3);
            }
        }

        Object[] opts = {opt1, opt2, opt3, opt4, wrappedRight};
        return opts;
    }

    public Object[] makeTrueFalseOptions(int questionIndex, String term, String definition, JPanel questionPanel) {
        ButtonGroup group = new ButtonGroup();

        JRadioButton trueButton = new JRadioButton("True");
        JRadioButton falseButton = new JRadioButton("False");

        trueButton.setBounds(60, questionPanel.getHeight() / 2 + 50, 50, 20);
        falseButton.setBounds(questionPanel.getWidth() / 2 + 60, questionPanel.getHeight() / 2 + 50, 100, 20);

        group.add(trueButton);
        group.add(falseButton);

        Object[] buttons = {trueButton, falseButton};

        return buttons;
    }

    public String[] getQuestionInfo(int questionIndex) {
        String questionText;
        String rightAnswer;
        if (answerWith.equals("term")) {
            questionText = definitions.get(questionIndex);
            rightAnswer = terms.get(questionIndex);
        } else {
            questionText = terms.get(questionIndex);
            rightAnswer = definitions.get(questionIndex);
        }
        String[] info = {questionText, rightAnswer};
        return info;
    }

    public Object[] getTrueFalseQuestionInfo(int questionIndex) {

        String term, definition;
        boolean correctAnswer;
        term = terms.get(questionIndex);
        definition = getTrueFalseAnswer(questionIndex);
        correctAnswer = definition.equals(definitions.get(questionIndex));

        Object[] info = {term, definition, correctAnswer};

        return info;
    }

    public String getTrueFalseAnswer(int questionIndex) {
        String wrongAnswer = null;
        if (rand.nextInt(2) == 0) {
            while (wrongAnswer == null) {
                int index = rand.nextInt(terms.size());
                if (index != questionIndex) {
                    wrongAnswer = definitions.get(index);
                }
            }
        } else {
            wrongAnswer = definitions.get(questionIndex);
        }

        return wrongAnswer;
    }

    public void increasePanelSize(JPanel questionPanel) {
        scrollPanel.setPreferredSize(new Dimension(scrollPanel.getWidth(), questionPanel.getY() + questionPanel.getHeight()));
        height = questionPanel.getY() + questionPanel.getHeight();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout scrollPanelLayout = new javax.swing.GroupLayout(scrollPanel);
        scrollPanel.setLayout(scrollPanelLayout);
        scrollPanelLayout.setHorizontalGroup(
            scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1118, Short.MAX_VALUE)
        );
        scrollPanelLayout.setVerticalGroup(
            scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 914, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel scrollPanel;
    // End of variables declaration//GEN-END:variables
}
