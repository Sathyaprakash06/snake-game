package Snake;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    private int row;
    private int col;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

public class Snake {
    private char[][] snakeboard = null;
    private Queue<Node> q = new LinkedList<>();

    Snake(int row, int col) {
        this.snakeboard = new char[row][col];
        this.q = new LinkedList<>();
        // Fill the board with '0'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                snakeboard[i][j] = '0';
            }
        }
        this.q.add(new Node(0, 0));
        this.snakeboard[1][0] = 'x';
        this.snakeboard[2][2] = 'x';
        this.snakeboard[3][4] = 'x';
        this.snakeboard[5][2] = 'x';
    }

    public void snakeMove(int row, int col) {
        if (row < 0 || row >= snakeboard.length || col < 0 || col >= snakeboard[0].length) {
            System.out.println("Game Over !!!");
            return;
        }

        q.add(new Node(row, col));

        if (snakeboard[row][col] != 'x') {
            Node node = q.poll();
            int r = node.getRow();
            int c = node.getCol();
            snakeboard[r][c] = '\0';
        }

        snakeboard[row][col] = '.';
        printSnake();

        System.out.print("Enter the position (U/D/L/R): ");
        Scanner sc = new Scanner(System.in);
        char dir = sc.next().charAt(0);
        switch (dir) {
            case 'U':
                snakeMove(row - 1, col);
                break;
            case 'D':
                snakeMove(row + 1, col);
                break;
            case 'L':
                snakeMove(row, col - 1);
                break;
            case 'R':
                snakeMove(row, col + 1);
                break;
            default:
                System.out.println("Invalid Move");
        }
    }

    public void printSnake() {
        for (char[] chars : snakeboard) {
            for (int i = 0; i < snakeboard[0].length; i++) {
                System.out.print(chars[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Snake game = new Snake(6, 6);
        game.snakeMove(0, 0);
    }
}
