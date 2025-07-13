import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuGUI {
    private JFrame frame;
    private JTextField[][] cells = new JTextField[9][9];
    private SudokuSolver solver = new SudokuSolver();

    public SudokuGUI() {
        frame = new JFrame("Sudoku Solver");
        frame.setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 18));
                gridPanel.add(cells[row][col]);
            }
        }

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> {
            int[][] board = new int[9][9];
            try {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        String text = cells[i][j].getText();
                        board[i][j] = text.isEmpty() ? 0 : Integer.parseInt(text);
                    }
                }

                if (solver.solveSudoku(board)) {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            cells[i][j].setText(String.valueOf(board[i][j]));
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "No solution exists!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Only digits 1-9 allowed.");
            }
        });

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(solveButton, BorderLayout.SOUTH);
        frame.setSize(500, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
