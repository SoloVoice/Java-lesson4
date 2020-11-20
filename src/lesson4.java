import java.util.Scanner;

public class lesson4 {
    public static Scanner scann = new Scanner(System.in);
    public static char[][] gameField;
    public static int SIZE = 3;
    public static final char CELL_X = 'X';
    public static final char CELL_O = 'O';
    public static final char CELL_EMPTY = '*';

    public static void main(String[] args) {
        //Инициализирует поле
        createGameField();
        //Выводим поле
        showGameField();
        while (true) {
            //Ход человека
            //Проерка выбранной ячейки
            //Проверка ничьи
            //Проверка победы
            humanTurn();
            if (isGameDraw()) {
                System.out.println("НичьяИгра\nзакончена!!!");
                break;
            }
            if (win(CELL_X)) {
                System.out.println("Вы выйграли!");
                break;
            }

            //Ход AI
            //Проерка выбранной ячейки
            //Проверка ничьи
            //Проверка победы
            aiTurn();
            if (win(CELL_O)) {
                System.out.println("Победил компьютер");
                break;
            }
        }
    }

    public static void createGameField() {
        gameField = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gameField[i][j] = CELL_EMPTY;
            }
        }
    }

    public static void showGameField() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() {
        System.out.println("Ваш ход!");
        int x, y;
        do {
            System.out.print("Введите координату 'X': ");
            y = scann.nextInt() - 1;
            System.out.print("Введите координату 'Y': ");
            x = scann.nextInt() - 1;
        } while (!cellIsEmpty(x, y));
        gameField[x][y] = CELL_X;
        System.out.println("Вы выбрали ячейку с номером " + (y + 1) + "-" + (x + 1));
        showGameField();
    }

    public static void aiTurn() {
        System.out.println("Ход компьютера");
        int x, y;
        do {
            y = (int) ((Math.random()) * SIZE);
            x = (int) ((Math.random()) * SIZE);
        } while (!cellIsEmpty(x, y));
        gameField[x][y] = CELL_O;
        System.out.println("Компьютер выбрал ячейку с номером " + (y + 1) + "-" + (x + 1));
        showGameField();
    }

    public static boolean cellIsEmpty(int x, int y) {
        if (gameField[x][y] == CELL_EMPTY) {
            return true;
        }
        System.out.println("Эта ячейка занята. Выберите другую!");
        return false;
    }

    public static boolean isGameDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (gameField[i][j] == CELL_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean win(char player_mark) {
        int win;
        for (int i = 0; i < SIZE; i++) {
            win = 0;
            for (int j = 0; j < SIZE; j++) {
                if (gameField[i][j] == player_mark) {
                    win += 1;
                }
            }
            if (win == SIZE) {
                return true;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            win = 0;
            for (int j = 0; j < SIZE; j++) {
                if (gameField[j][i] == player_mark) {
                    win += 1;
                }
            }
            if (win == SIZE) {
                return true;
            }
        }
        win = 0;
        for (int i = 0; i < SIZE; i++) {
            if (gameField[i][i] == player_mark) {
                win += 1;
            }
        }
        if (win == SIZE) {
            return true;
        }
        win = 0;
        int revert = SIZE-1;
        for (int i = 0; i < SIZE; i++) {
            if (gameField[i][revert] == player_mark) {
                revert--;
                win += 1;
            }
        }
        if (win == SIZE) {
            return true;
        }
        return false;
    }
}
