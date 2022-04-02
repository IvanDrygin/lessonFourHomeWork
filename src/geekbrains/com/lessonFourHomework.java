package geekbrains.com;

import java.util.Random;
import java.util.Scanner;

public class lessonFourHomework {

    private static char[][] map;
    public final static int MAP_SIZE = 3;
    public final static int DOTS_COUNT_TO_WIN = 3;
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
            if(checkWin(DOT_X)){
                System.out.println("You won");
                break;
            }
            if (chekDraw()) {
                System.out.println("Draw, looser");
                break;
            }
            computerTurn();
            print();
            if(checkWin(DOT_O)){
                System.out.println("Computer won");
                break;
            }
            if (chekDraw()) {
                System.out.println("Draw, looser");
            }

        }
    }

    private static boolean checkWin(char dot) {
        if (map[0][0] == dot && map[0][1] == dot && map[0][2] == dot) {
            return true;
        }
        if (map[1][0] == dot && map[1][1] == dot && map[1][2] == dot) {
            return true;
        }
        if (map[2][0] == dot && map[2][1] == dot && map[2][2] == dot) {
            return true;
        }
        if (map[0][0] == dot && map[0][1] == dot && map[2][0] == dot) {
            return true;
        }
        if (map[0][1] == dot && map[1][1] == dot && map[2][1] == dot) {
            return true;
        }
        if (map[0][2] == dot && map[1][2] == dot && map[2][2] == dot) {
            return true;
        }
        if (map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) {
            return true;
        }
        if (map[0][2] == dot && map[1][1] == dot && map[2][0] == dot) {
            return true;
        }
return false;
    }

    private static boolean chekDraw(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
       return true;
    }

    private static void computerTurn() {
        System.out.println("Computer turn");
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


    private static boolean cellValidation(int x, int y){
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

    private static void print() {
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

    private static void init() {
        map = new char[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j <map[i].length; j++) {
                map[i][j] =DOT_EMPTY;
            }
            
        }
    }
}
