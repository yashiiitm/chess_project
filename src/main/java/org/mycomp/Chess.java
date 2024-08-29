package org.mycomp;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chess {
    static char[] array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    public static void display(String currentBox, String currentPiece) {
        String reset = "\u001B[0m";
        String greenText = "\u001B[32m";
        String whiteBackground = "\u001B[47m";
        int currentRow = 0;
        int currentColumn = 0;
        if (!currentBox.isEmpty()) {
            currentRow = Integer.parseInt(String.valueOf(currentBox.charAt(1)));
            currentColumn = Arrays.binarySearch(array, currentBox.charAt(0)) + 1;
        }
        String str;
        List<String> possibleSpots = Chess.getPossibleSpots(currentPiece, currentRow, currentColumn);
        for (int row = 0; row < 8; row++) {
            System.out.println("");
            System.out.println(StringUtils.repeat("----", 8));

            for (int column = 0; column < 8; column++) {
                str = String.valueOf(array[column]) + (8 - row);
                if (currentBox.equals(str)) {  // change text color to highligh current piece
                    System.out.print("| " + greenText + array[column] + (8 - row) + reset);
                } else if (possibleSpots.contains(str)) { // Change background of possible spots where piece can move
                    System.out.print("| " + whiteBackground + array[column] + (8 - row) + reset);
                } else {
                    System.out.print("| " + array[column] + (8 - row) + reset);
                }
            }
            System.out.print("|");
        }
        System.out.println("");
        System.out.println(StringUtils.repeat("----", 8));
        System.out.println("Possible moves-" + possibleSpots);
    }

    public static List<String> getPossibleSpots(String type, int currentRow, int currentColumn) {
        List<String> result = new ArrayList<>();
        return switch (type) {
            case "PAWN" -> getPawnPossibleSpots(currentRow, currentColumn);
            case "KING" -> getKingPossibleSpots(currentRow, currentColumn);
            case "QUEEN" -> getQueenPossibleSpots(currentRow, currentColumn);
            default -> result;
        };
    }

    public static List<String> getPawnPossibleSpots(int row, int column) {
        List<String> result = new ArrayList<>();
        for (int irow = 0; irow < 8; irow++) {
            if (irow == row) {
                for (int icolumn = 0; icolumn < 8; icolumn++) {
                    if (icolumn == column - 1) {
                        result.add(String.valueOf(array[icolumn]) + (row + 1));
                        break;
                    }
                }
                break;
            }
        }
        return result;
    }

    public static List<String> getKingPossibleSpots(int row, int column) {
        List<String> result = new ArrayList<>();
        for (int irow = 1; irow < 9; irow++) {
            if (irow == row || irow == row + 1 || irow == row - 1) {
                for (int icolumn = 0; icolumn < 8; icolumn++) {
                    if ((icolumn == column - 1 || icolumn == column - 2 || icolumn == column) && !(row == irow && column == icolumn + 1)) {
                        result.add(String.valueOf(array[icolumn]) + (irow));
                    }
                }
            }
        }
        return result;
    }

    public static List<String> getQueenPossibleSpots(int row, int column) {
        List<String> result = new ArrayList<>();
        int queenRowDistance;
        for (int irow = 0; irow < 8; irow++) {
            queenRowDistance = row - irow; // Difference between current row and Queen's row
            for (int icolumn = 0; icolumn < 8; icolumn++) {
                if (queenRowDistance == 1 && column != icolumn + 1) {  //
                    result.add(String.valueOf(array[icolumn]) + (irow + 1));
                } else if (queenRowDistance != 1 && (icolumn == column - 1 || icolumn == column + queenRowDistance - 2 || icolumn == column - queenRowDistance)) {
                    result.add(String.valueOf(array[icolumn]) + (irow + 1));
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        Chess.display("", ""); // Empty chess
        // Pawn
        Chess.display("D1", "PAWN");
        // King
        Chess.display("B5", "KING");
        // Queen
        Chess.display("H4", "QUEEN");
    }
}