/*
 * Name: Lorena Buciu
 * Date: 2/28/2020
 * Version: v1.00
 * Purpose: A 2048 game. 
 */
package edu.hdsb.gwss.lorena.ics4u.u2;

/**
 *
 * @author 4U Lorena
 */
public class GameLogic2048 {

    public static int[][] data = {
        {2, 2, 2, 0},
        {0, 2, 2, 2},
        {2, 0, 2, 0},
        {0, 2, 0, 2},};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //method shiftleft, mergeleft, shiftLeft  
        //start at col 1, check if its 0, if 0 then switch, col++, n-1 passes
        // then merge, start at 0, if the right is the same then double the value and turn the other one to 0
        // there are holes so shift left again, then refresh screen, place random 2 or 4 only if empty  
        //logic:
        //press left 
        //fake 2220
        //     0222
        //     2020
        //     0202
        display();

        shiftLeft();
        System.out.println("");
        mergeLeft();
        System.out.println("");
        display();
        shiftLeft();
        System.out.println("");
        display();

    }

    public static void display() {

        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[r].length; c++) {

                System.out.print(data[r][c] + " ");

            }
            System.out.println("");
        }

    }

    //LEFT
    public static void shiftLeft() {

        for (int r = 0; r < data.length; r++) {

            for (int pass = 0; pass < data[r].length - 1; pass++) {

                for (int c = 1; c < data[0].length; c++) {

                    if (data[r][c - 1] == 0 && data[r][c] != 0) {
                        data[r][c - 1] = data[r][c];
                        data[r][c] = 0;

                    }

                }

            }

        }

    }

    public static void mergeLeft() {

        for (int r = 0; r < data.length; r++) {

            for (int c = 1; c < data[r].length; c++) {

                if (data[r][c - 1] == data[r][c]) {

                    data[r][c - 1] = (data[r][c]) * 2;
                    data[r][c] = 0;

                }

            }

        }

    }

    //RIGHT
    public static void shiftRight() {

        for (int r = 0; r < data.length; r++) {
            for (int pass = 0; pass < data[r].length - 1; pass++) {

                for (int c = data[0].length - 2; c >= 0; c--) {

                    if (data[r][c + 1] == 0 && data[r][c] != 0) {

                        data[r][c] = data[r][c + 1];
                        data[r][c + 1] = 0;

                    }

                }

            }

        }

    }

    public static void mergeRight() {

        for (int r = 0; r < data.length; r++) {

            for (int c = data[r].length - 2; c >= 0; c--) {

                if (data[r][c] == data[r][c + 1] && data[r][c] != 0) {
                    data[r][c + 1] = (data[r][c]) * 2;
                    data[r][c] = 0;

                }

            }

        }

    }

    //DOWN
    public static void shiftDown() {

        for (int r = 0; r < data.length - 1; r++) {
            for (int pass = 0; pass < data[r].length - 1; pass++) {

                for (int c = 0; c < data[0].length; c++) {

                    if (data[r][c] != 0 && data[r + 1][c] == 0) {
                        data[r + 1][c] = data[r][c];
                        data[r][c] = 0;

                    }

                }

            }

        }

    }

    public static void mergeDown() {

        for (int r = 0; r < data.length - 1; r++) {

            for (int c = 0; c < data[r].length; c++) {

                if (data[r + 1][c] == data[r][c] && data[r][c] != 0) {
                    data[r + 1][c] = (data[r][c]) * 2;
                    data[r][c] = 0;

                }

            }

        }

    }

    //UP
    public static void shiftUp() {

        for (int r = data.length - 1; r >= 0; r--) {

            for (int pass = 0; pass < data.length - 1; pass++) {

                for (int c = 0; c < data[0].length; c++) {

                    if (data[r][c] != 0 && data[r - 1][c] == 0) {

                        data[r - 1][c] = data[r][c];
                        data[r][c] = 0;

                    }

                }

            }

        }

    }

    public static void mergeUp() {

        for (int r = data.length-1; r > 0; r--) {

            for (int c = 0; c < data[r].length; c++) {

                if (data[r - 1][c] == data[r][c] && data[r][c] != 0) {
                    data[r - 1][c] = (data[r][c]) * 2;
                    data[r][c] = 0;

                }

            }

        }

    }

}
