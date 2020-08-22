/*
* Name: Lorena Buciu
* Date: 2/28/2020
* Version: v1.00
* Purpose: A 2048 game. 
 */
package edu.hdsb.gwss.lorena.ics4u.u2;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

/**
 *
 * @author 4U Lorena
 */
public class TheGame2048 extends javax.swing.JFrame {

    private static int[][] data;
    private JLabel[][] boxes;
    private static int score;
    private static int bestScore;

    /**
     * Creates new form TheGame2048
     */
    public TheGame2048() {
        initComponents();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                formKeyPressed(e);
            }
        });

        //TODO LIST:
        //diff colours for tiles of a certain multiple
        //game over when tile is full
        //score 
        //can move method, dont place random 2/4 unless it can move
        data = new int[4][4];
        boxes = new JLabel[4][4];

        boxes[0][0] = jLabel00;
        boxes[0][1] = jLabel01;
        boxes[0][2] = jLabel02;
        boxes[0][3] = jLabel03;

        boxes[1][0] = jLabel10;
        boxes[1][1] = jLabel11;
        boxes[1][2] = jLabel12;
        boxes[1][3] = jLabel13;

        boxes[2][0] = jLabel20;
        boxes[2][1] = jLabel21;
        boxes[2][2] = jLabel22;
        boxes[2][3] = jLabel23;

        boxes[3][0] = jLabel30;
        boxes[3][1] = jLabel31;
        boxes[3][2] = jLabel32;
        boxes[3][3] = jLabel33;

        randomStart();
        refreshScreen();

    }

    public void refreshScreen() {

        for (int r = 0; r < data.length; r++) {

            for (int c = 0; c < data[r].length; c++) {

                if (data[r][c] != 0) {
                    boxes[r][c].setText("" + (data[r][c]));
                    boxes[r][c].setBackground(Color.pink);
                } else {
                    boxes[r][c].setText("");
                    boxes[r][c].setBackground(new Color(255, 204, 153));
                    jLabelScore.setText("Score\n\n" + score);
                }

            }

        }

    }

    public void randomStart() {

        //random placement of starting tiles
        int randRow2, randCol2, randRow4, randCol4;
        boolean valid = false;

        do {

            randRow4 = (int) (Math.random() * 4);
            randCol4 = (int) (Math.random() * 4);

            randRow2 = (int) (Math.random() * 4);
            randCol2 = (int) (Math.random() * 4);

            // only places them when the indicies for both numbers are not the same location
            if (!(randRow2 == randRow4 && randCol2 == randCol4)) {
                data[randRow4][randCol4] = (int) Math.pow(2, (int) (Math.random() * 2 + 1));
                data[randRow2][randCol2] = (int) Math.pow(2, (int) (Math.random() * 2 + 1));
                valid = true;
            }

        } while (!valid);

    }

    //SPAWN RANDOM 2 AFTER MOVEMENT
    public static void randomTwo() {

        int randRow, randCol;
        boolean valid = false;

        do {

            randRow = (int) (Math.random() * 4);
            randCol = (int) (Math.random() * 4);

            //place random 2 in unoccupied space
            if (data[randRow][randCol] == 0) {
                data[randRow][randCol] = (int) Math.pow(2, (int) (Math.random() * 2 + 1));
                valid = true;

            }

        } while (!valid);

    }

    //MOVEMENT METHODS 
    //LEFT
    public static boolean shiftLeft() {

        boolean moved = false;

        for (int r = 0; r < data.length; r++) {

            for (int pass = 0; pass < data[r].length - 1; pass++) {

                for (int c = 1; c < data[0].length; c++) {

                    if (data[r][c - 1] == 0 && data[r][c] != 0) {
                        data[r][c - 1] = data[r][c];
                        data[r][c] = 0;
                        moved = true;

                    }

                }

            }

        }
        return moved;

    }

    public static void mergeLeft() {

        for (int r = 0; r < data.length; r++) {

            for (int c = 1; c < data[r].length; c++) {

                if (data[r][c - 1] == data[r][c]) {

                    data[r][c - 1] = (data[r][c]) * 2;
                    data[r][c] = 0;

                    score += data[r][c - 1] * 2;

                }

            }

        }

    }

    //RIGHT
    public static boolean shiftRight() {

        boolean moved = false;

        for (int r = 0; r < data.length; r++) {
            for (int pass = 0; pass < data[r].length - 1; pass++) {

                for (int c = 0; c < data[0].length - 1; c++) {

                    if (data[r][c + 1] == 0 && data[r][c] != 0) {

                        data[r][c + 1] = data[r][c];
                        data[r][c] = 0;
                        moved = true;

                    }

                }

            }

        }
        return moved;

    }

    public static void mergeRight() {

        for (int r = 0; r < data.length; r++) {

            for (int c = data[r].length - 2; c >= 0; c--) {

                if (data[r][c] == data[r][c + 1] && data[r][c] != 0) {
                    data[r][c + 1] = (data[r][c]) * 2;
                    data[r][c] = 0;

                    score += data[r][c + 1] * 2;

                }

            }

        }

    }

    //DOWN
    public static boolean shiftDown() {

        boolean moved = false;

        for (int r = 0; r < data.length - 1; r++) {
            for (int pass = 0; pass < data[r].length - 1; pass++) {

                for (int c = 0; c < data[0].length; c++) {

                    if (data[r][c] != 0 && data[r + 1][c] == 0) {
                        data[r + 1][c] = data[r][c];
                        data[r][c] = 0;
                        moved = true;

                    }

                }

            }

        }
        return moved;

    }

    public static void mergeDown() {

        for (int r = 0; r < data.length - 1; r++) {

            for (int c = 0; c < data[r].length; c++) {

                if (data[r + 1][c] == data[r][c] && data[r][c] != 0) {
                    data[r + 1][c] = (data[r][c]) * 2;
                    data[r][c] = 0;

                    score += data[r + 1][c] * 2;

                }

            }

        }

    }

    //UP
    public static boolean shiftUp() {

        boolean moved = false;

        for (int r = data.length - 1; r > 0; r--) {

            for (int pass = 0; pass < data.length - 1; pass++) {

                for (int c = 0; c < data[0].length; c++) {

                    if (data[r][c] != 0 && data[r - 1][c] == 0) {

                        data[r - 1][c] = data[r][c];
                        data[r][c] = 0;
                        moved = true;

                    }

                }

            }

        }

        return moved;
    }

    public static void mergeUp() {

        for (int r = data.length - 1; r > 0; r--) {

            for (int c = 0; c < data[r].length; c++) {

                if (data[r - 1][c] == data[r][c] && data[r][c] != 0) {
                    data[r - 1][c] = (data[r][c]) * 2;
                    data[r][c] = 0;

                    score += data[r - 1][c] * 2;

                }

            }

        }

    }

    //KEYBOARD LISTENER
    private void formKeyPressed(KeyEvent e) {

        if (e.getID() == KeyEvent.KEY_PRESSED) {
        }
        System.out.print("KEY PRESSED: ");

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:

                System.out.println("LEFT");
                shiftLeft();
                mergeLeft();
                shiftLeft();
                randomTwo();
                refreshScreen();
                break;

            case KeyEvent.VK_UP:

                System.out.println("UP");
                shiftUp();
                mergeUp();
                shiftUp();
                randomTwo();
                refreshScreen();
                break;

            case KeyEvent.VK_DOWN:

                System.out.println("DOWN");
                shiftDown();
                mergeDown();
                shiftDown();
                randomTwo();
                refreshScreen();
                break;

            case KeyEvent.VK_RIGHT:

                System.out.println("RIGHT");
                shiftRight();
                mergeRight();
                shiftRight();
                randomTwo();
                refreshScreen();
                break;

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanelTop = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelScore = new javax.swing.JLabel();
        jLabelBest = new javax.swing.JLabel();
        jPanelBottom = new javax.swing.JPanel();
        jLabel00 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel01 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel02 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel03 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("2048");

        jPanelTop.setBackground(new java.awt.Color(255, 204, 204));

        jLabelTitle.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("2048");

        jLabelScore.setBackground(new java.awt.Color(255, 153, 153));
        jLabelScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelScore.setText("Score");
        jLabelScore.setToolTipText("");
        jLabelScore.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelScore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabelScore.setOpaque(true);

        jLabelBest.setBackground(new java.awt.Color(255, 153, 153));
        jLabelBest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBest.setText("Best ");
        jLabelBest.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelBest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabelBest.setOpaque(true);

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabelScore, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelBest, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTopLayout.createSequentialGroup()
                .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelTopLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelBest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelTopLayout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        jPanelBottom.setBackground(new java.awt.Color(255, 153, 153));

        jLabel00.setBackground(new java.awt.Color(255, 204, 153));
        jLabel00.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel00.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel00.setText("2");
        jLabel00.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel00.setOpaque(true);
        jLabel00.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel00.setVerifyInputWhenFocusTarget(false);

        jLabel10.setBackground(new java.awt.Color(255, 204, 153));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("2");
        jLabel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel10.setOpaque(true);
        jLabel10.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel10.setVerifyInputWhenFocusTarget(false);

        jLabel20.setBackground(new java.awt.Color(255, 204, 153));
        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("2");
        jLabel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel20.setOpaque(true);
        jLabel20.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel20.setVerifyInputWhenFocusTarget(false);

        jLabel30.setBackground(new java.awt.Color(255, 204, 153));
        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("2");
        jLabel30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel30.setOpaque(true);
        jLabel30.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel30.setVerifyInputWhenFocusTarget(false);

        jLabel01.setBackground(new java.awt.Color(255, 204, 153));
        jLabel01.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel01.setText("2");
        jLabel01.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel01.setMaximumSize(new java.awt.Dimension(100, 95));
        jLabel01.setName(""); // NOI18N
        jLabel01.setOpaque(true);
        jLabel01.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel01.setVerifyInputWhenFocusTarget(false);

        jLabel31.setBackground(new java.awt.Color(255, 204, 153));
        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("2");
        jLabel31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel31.setOpaque(true);
        jLabel31.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel31.setVerifyInputWhenFocusTarget(false);

        jLabel21.setBackground(new java.awt.Color(255, 204, 153));
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("2");
        jLabel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel21.setOpaque(true);
        jLabel21.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel21.setVerifyInputWhenFocusTarget(false);

        jLabel11.setBackground(new java.awt.Color(255, 204, 153));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("2");
        jLabel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel11.setOpaque(true);
        jLabel11.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel11.setVerifyInputWhenFocusTarget(false);

        jLabel02.setBackground(new java.awt.Color(255, 204, 153));
        jLabel02.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel02.setText("2");
        jLabel02.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel02.setOpaque(true);
        jLabel02.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel02.setVerifyInputWhenFocusTarget(false);

        jLabel12.setBackground(new java.awt.Color(255, 204, 153));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("2");
        jLabel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel12.setOpaque(true);
        jLabel12.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel12.setVerifyInputWhenFocusTarget(false);

        jLabel22.setBackground(new java.awt.Color(255, 204, 153));
        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("2");
        jLabel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel22.setOpaque(true);
        jLabel22.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel22.setVerifyInputWhenFocusTarget(false);

        jLabel32.setBackground(new java.awt.Color(255, 204, 153));
        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("2");
        jLabel32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel32.setOpaque(true);
        jLabel32.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel32.setVerifyInputWhenFocusTarget(false);

        jLabel33.setBackground(new java.awt.Color(255, 204, 153));
        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("2");
        jLabel33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel33.setOpaque(true);
        jLabel33.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel33.setVerifyInputWhenFocusTarget(false);

        jLabel23.setBackground(new java.awt.Color(255, 204, 153));
        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("2");
        jLabel23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel23.setOpaque(true);
        jLabel23.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel23.setVerifyInputWhenFocusTarget(false);

        jLabel13.setBackground(new java.awt.Color(255, 204, 153));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("2");
        jLabel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel13.setOpaque(true);
        jLabel13.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel13.setVerifyInputWhenFocusTarget(false);

        jLabel03.setBackground(new java.awt.Color(255, 204, 153));
        jLabel03.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel03.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel03.setText("2");
        jLabel03.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jLabel03.setOpaque(true);
        jLabel03.setPreferredSize(new java.awt.Dimension(50, 50));
        jLabel03.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanelBottomLayout = new javax.swing.GroupLayout(jPanelBottom);
        jPanelBottom.setLayout(jPanelBottomLayout);
        jPanelBottomLayout.setHorizontalGroup(
            jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBottomLayout.createSequentialGroup()
                        .addComponent(jLabel00, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel01, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel02, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel03, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBottomLayout.createSequentialGroup()
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBottomLayout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBottomLayout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBottomLayout.setVerticalGroup(
            jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel00, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel02, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel03, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel01, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TheGame2048.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TheGame2048.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TheGame2048.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TheGame2048.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TheGame2048().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel00;
    private javax.swing.JLabel jLabel01;
    private javax.swing.JLabel jLabel02;
    private javax.swing.JLabel jLabel03;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabelBest;
    private javax.swing.JLabel jLabelScore;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanelBottom;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
