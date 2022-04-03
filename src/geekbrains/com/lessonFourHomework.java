package geekbrains.com;

import java.util.Random;
import java.util.Scanner;

public class lessonFourHomework {

    private static char[][] map;
    public final static int MAP_SIZE = 5;
    public final static int DOTS_COUNT_TO_WIN = 4;
    private final static char DOT_X = 'X';
    private final static char DOT_O = 'O';
    private final static char DOT_EMPTY = '·';
    private static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();

    public lessonFourHomework() {
    }

    public static void main(String[] args) {

        play();
    }

    private static void play() {
        init();
        print();
        while (true) {
            humanTurn();
            print();
            if (checkWin(DOT_X)) {
                System.out.println("You won");
                break;
            }
            if (chekDraw()) {
                System.out.println("Draw, looser");
                break;
            }
            computerTurn();
            print();
            if (checkWin(DOT_O)) {
                System.out.println("Computer won");
                break;
            }
            if (chekDraw()) {
                System.out.println("Draw, looser");
            }

        }
    }

    private static boolean checkWin(char dot) {

        int cols, rows, diagonalFromLeft, diagonalFromRight;
        cols = 0;
        rows = 0;
        diagonalFromLeft = 0;
        diagonalFromRight = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[j][j] == dot) {
                    diagonalFromLeft++;
                }
                if (map[j][(MAP_SIZE - 1) - j] == dot) {
                    diagonalFromRight++;
                }
                if (map[i][j] == dot) {
                    cols++;
                }
                if (map[j][i] == dot) {
                    rows++;
                }
            }
            if (diagonalFromRight == DOTS_COUNT_TO_WIN || diagonalFromLeft == DOTS_COUNT_TO_WIN || cols == DOTS_COUNT_TO_WIN || rows == DOTS_COUNT_TO_WIN) {
                return true;
            } else {
                cols = 0;
                rows = 0;
                diagonalFromLeft = 0;
                diagonalFromRight = 0;
            }
        }
        return false;

        /* boolean cols, rows, diagonalFromLeft, diagonalFromRight; // вариант покороче, но я не понял, как интегрировать счет
        cols = true;
        rows = true;
        diagonalFromLeft = true;
        diagonalFromRight = true;
        for (int col = 0; col < map.length; col++) {
            for (int row = 0; row < map.length; row++) {
                cols &= (map[col][row] == dot);
                rows &= (map[row][col] == dot);
            }
            if (cols || rows)
            return true;
            continue;
        }
        for (int i = 0; i < map.length; i++) {
            diagonalFromLeft &= map [i][i] == dot;
            diagonalFromRight &= map [map.length - i - 1][i] == dot;
        }
        if (diagonalFromLeft || diagonalFromRight ) return true;
        return false; */
    }

    private static boolean chekDraw() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void computerTurn(/*char dot*/) {
       /* System.out.println("Computer turn"); /// вот тут попытался прописать подобие ai, подскажите, в чем ошибка?
        int cols, rows, diagonalFromLeft, diagonalFromRight;
        cols = 0;
        rows = 0;
        diagonalFromLeft = 0;
        diagonalFromRight = 0;

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[j][j] == dot) {
                    diagonalFromLeft++;

                }
                if (map[j][(MAP_SIZE - 1) - j] == dot) {
                    diagonalFromRight++;

                }
                if (map[i][j] == dot) {
                    cols++;

                }
                if (map[j][i] == dot) {
                    rows++;

                    do {
                        map[j * 4][j * 4] = dot;
                    } while (diagonalFromRight == DOTS_COUNT_TO_WIN - 1);

                    do {
                        map[j * 4][((MAP_SIZE - 1) - j) * 4] = dot;
                    } while (diagonalFromLeft == DOTS_COUNT_TO_WIN - 1);

                    do {
                        map[i * 4][j * 4] = dot;
                    } while (cols == DOTS_COUNT_TO_WIN - 1);

                    do {
                        map[j * 4][i * 4] = dot;
                    } while (rows == DOTS_COUNT_TO_WIN - 1);



                }
            }*/
            int x, y;
            do {
                x = random.nextInt(MAP_SIZE);
                y = random.nextInt(MAP_SIZE);
            }
            while (!cellValidation(x + 1, y + 1));
            map[x][y] = DOT_O;
        }
        private static void humanTurn () {
            int x, y;
            do {
                while (true) {
                    System.out.println("please imput dots coordinate in format 'x y'");
                    if (sc.hasNextInt()) {
                        x = sc.nextInt();
                    } else {
                        System.out.println("your input wrong x coordinate format");
                        sc.nextLine();
                        continue;
                    }
                    if (sc.hasNextInt()) {
                        y = sc.nextInt();
                        break;
                    } else {
                        System.out.println("your input wrong y coordinate format");
                        sc.nextLine();
                    }
                }
            } while (!cellValidation(x, y));
            map[x - 1][y - 1] = DOT_X;
        }


        private static boolean cellValidation ( int x, int y){
            if (x < 1 || x > MAP_SIZE || y < 1 || y > MAP_SIZE) {
                System.out.println("Exit mapsizes");
                return false;
            }
            boolean check = map[x - 1][y - 1] == DOT_EMPTY;
            if (check) {
                return check;
            } else {
                System.out.println("Cell is busy");
                return false;
            }
        }

        private static void print () {
            for (int i = 0; i < map.length + 1; i++) {
                if (i == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print((i) + " ");
                }
            }
            System.out.println();
            for (int i = 0; i < map.length; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }

        private static void init () {
            map = new char[MAP_SIZE][MAP_SIZE];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = DOT_EMPTY;
                }

            }
        }

    }

