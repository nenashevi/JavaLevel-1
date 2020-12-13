package lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Cross {

    static int SIZE_X = 5;
    static int SIZE_Y = 5;

    static char[][] field = new char[SIZE_Y][SIZE_X];
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static char PLAYER_DOT = 'X';
    static char AI_DOT = 'O';
    static char EMPTY_DOT = '.';

    static void initField() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    static void printField() {
        System.out.println("-------");
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("-------");
    }

    static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x > SIZE_X - 1 || y > SIZE_Y - 1) {
            return false;
        }
        return field[y][x] == EMPTY_DOT;
    }

    static boolean isFieldFull() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    static void playerStep() {
        int x,y;
        do {
            System.out.println("Введите координаты: X Y (1-3)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y,x));
        setSym(y,x, PLAYER_DOT);
    }
    //Алгоритм хода компьютера следующий:
    //Компьютер пытается сначала заблокировать выигрыш игорока, если это не требуется
    //пытается выстроить выигрышную линию. Если нет необходимости делать то или другое,
    //то выполняется случайный ход.
    static void aiStep() {
        int[] aiStepYXtemp = {0, 0, 0};
        int[] aiStepYX = {0, 0, 0};
        boolean isXYForBlockWin = false;
        boolean isXYForWin = false;
        do {
            System.out.println("Ходит SkyNet");


            // Попытка выиграть
            aiStepYXtemp = getCoordForWinInField();
            if (aiStepYXtemp[2] == 1) {
                aiStepYX[0] = aiStepYXtemp[0];
                aiStepYX[1] = aiStepYXtemp[1];
                aiStepYX[2] = aiStepYXtemp[2];
                isXYForWin = true;
                aiStepYXtemp[0] = 0;
                aiStepYXtemp[1] = 0;
                aiStepYXtemp[2] = 0;
            }



            // Блокировка выигрыша игрока
            for (int i = 0; i <= SIZE_Y -4; i++) {
                for (int  j = 0; j<= SIZE_X -4 ;  j++) {
                    aiStepYXtemp = getCoordForBlockWinPlayer(i, j);
                    if (aiStepYXtemp[2] == 1) {
                        aiStepYX[0] = aiStepYXtemp[0];
                        aiStepYX[1] = aiStepYXtemp[1];
                        aiStepYX[2] = aiStepYXtemp[2];
                        isXYForBlockWin = true;
                    }


                }
            }
            if (!isXYForBlockWin && !isXYForWin) {
                aiStepYX[0] = random.nextInt(SIZE_Y);
                aiStepYX[1] = random.nextInt(SIZE_X);
            }


        } while (!isCellValid(aiStepYX[0],aiStepYX[1]));
        setSym(aiStepYX[0],aiStepYX[1], AI_DOT);
    }

    static boolean checkWin(char sym) {

        return checkWinByRow(sym) || checkWinByColumn(sym) || checkDiagonalWin(sym);
    }
    static boolean checkWinByRow(char sym) {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X - (4-1); j++) {
                if (field[i][j] == sym && field[i][j+1] == sym && field[i][j+2] == sym && field[i][j+3] == sym) {
                    return true;
                }

            }

        }
        return false;
    }
    static boolean checkWinByColumn(char sym) {
        for (int j = 0; j < SIZE_X; j++) {
            for (int i = 0; i < SIZE_Y - (4-1); i++) {
                if (field[i][j] == sym && field[i+1][j] == sym && field[i+2][j] == sym && field[i+3][j] == sym) {
                    return true;
                }

            }

        }
        return false;
    }
    static boolean checkDiagonalWin(char sym) {

        for (int i = 0; i <= SIZE_Y -4; i++) {
            for (int  j = 0; j<= SIZE_X -4 ;  j++) {
                if (checkDiagonalOfSubField(sym,i , j))
                    return true;
            }
        }
        return false;





    }
    static boolean checkDiagonalOfSubField (char sym, int y, int x) {
        if (field[y][x] == sym && field[y+1][x+1] == sym && field[y+2][x+2] == sym && field[y+3][x+3] == sym)
            return true;
        if (field[y+3][x] == sym && field[y+2][x+1] == sym && field[y+1][x+2] == sym && field[y][x+3] == sym)
            return true;
        return false;
    }
    static int[] getCoordForBlockWinPlayer (int y, int x) {
        int[] aiStepYX = {0, 0, 0}; // если aiStepYX[2] == 1 то значит поле для бликировки найдено
        for (int i = 0; i < 4; i++) {
            //Проверка на необходимость блокировать строки
            if (field[y + i][x] == EMPTY_DOT && field[y + i][x+1] == PLAYER_DOT && field[y + i][x+2] == PLAYER_DOT && field[y + i][x+3] == PLAYER_DOT) {
                aiStepYX[0] = y + i;
                aiStepYX[1] = x;
                aiStepYX[2] = 0;
            }
            if (field[y + i][x] == PLAYER_DOT && field[y + i][x+1] == EMPTY_DOT && field[y + i][x+2] == PLAYER_DOT && field[y + i][x+3] == PLAYER_DOT) {
                aiStepYX[0] = y + i;
                aiStepYX[1] = x + 1;
                aiStepYX[2] = 1;
            }
            if (field[y + i][x] == PLAYER_DOT && field[y + i][x+1] == PLAYER_DOT && field[y + i][x+2] == EMPTY_DOT && field[y + i][x+3] == PLAYER_DOT) {
                aiStepYX[0] = y + i;
                aiStepYX[1] = x + 2;
                aiStepYX[2] = 1;
            }
            if (field[y + i][x] == PLAYER_DOT && field[y + i][x+1] == PLAYER_DOT && field[y + i][x+2] == PLAYER_DOT && field[y + i][x+3] == EMPTY_DOT) {
                aiStepYX[0] = y + i;
                aiStepYX[1] = x + 3;
                aiStepYX[2] = 1;
            }
        }
        //Проверка на необходимость блокировать столбцы
        for (int i = 0; i < 4; i++) {
            if (field[y][x + i] == EMPTY_DOT && field[y + 1][x + i] == PLAYER_DOT && field[y + 2][x + i] == PLAYER_DOT && field[y + 3][x + i] == PLAYER_DOT) {
                aiStepYX[0] = y;
                aiStepYX[1] = x + i;
                aiStepYX[2] = 1;
            }
            if (field[y][x + i] == PLAYER_DOT && field[y + 1][x + i] == EMPTY_DOT && field[y + 2][x + i] == PLAYER_DOT && field[y + 3][x + i] == PLAYER_DOT) {
                aiStepYX[0] = y + 1;
                aiStepYX[1] = x + i;
                aiStepYX[2] = 1;
            }
            if (field[y][x + i] == PLAYER_DOT && field[y + 1][x + i] == PLAYER_DOT && field[y + 2][x + i] == EMPTY_DOT && field[y + 3][x + i] == PLAYER_DOT) {
                aiStepYX[0] = y + 2;
                aiStepYX[1] = x + i;
                aiStepYX[2] = 1;
            }
            if (field[y][x + i] == PLAYER_DOT && field[y + 1][x + i] == PLAYER_DOT && field[y + 2][x + i] == PLAYER_DOT && field[y + 3][x + i] == EMPTY_DOT) {
                aiStepYX[0] = y + 3 ;
                aiStepYX[1] = x + i;
                aiStepYX[2] = 1;
            }

        }
        //Проверка на необходимосит блокировать диагонали
        // 1-я диагональ
        if (field[y][x] == EMPTY_DOT && field[y + 1][x + 1] == PLAYER_DOT && field[y + 2][x + 2] == PLAYER_DOT && field[y + 3][x + 3] == PLAYER_DOT) {
            aiStepYX[0] = y;
            aiStepYX[1] = x;
            aiStepYX[2] = 1;
        }
        if (field[y][x] == PLAYER_DOT && field[y + 1][x + 1] == EMPTY_DOT && field[y + 2][x + 2] == PLAYER_DOT && field[y + 3][x + 3] == PLAYER_DOT) {
            aiStepYX[0] = y + 1;
            aiStepYX[1] = x + 1;
            aiStepYX[2] = 1;
        }
        if (field[y][x] == PLAYER_DOT && field[y + 1][x + 1] == PLAYER_DOT && field[y + 2][x + 2] == EMPTY_DOT && field[y + 3][x + 3] == PLAYER_DOT) {
            aiStepYX[0] = y + 2;
            aiStepYX[1] = x + 2;
            aiStepYX[2] = 1;
        }
        if (field[y][x] == PLAYER_DOT && field[y + 1][x + 1] == PLAYER_DOT && field[y + 2][x + 2] == PLAYER_DOT && field[y + 3][x + 3] == EMPTY_DOT) {
            aiStepYX[0] = y + 3 ;
            aiStepYX[1] = x + 3;
            aiStepYX[2] = 1;
        }
        // 2-я диагональ
        if (field[y + 3][x] == EMPTY_DOT && field[y + 2][x + 1] == PLAYER_DOT && field[y + 1][x + 2] == PLAYER_DOT && field[y][x + 3] == PLAYER_DOT) {
            aiStepYX[0] = y + 3;
            aiStepYX[1] = x;
            aiStepYX[2] = 1;
        }
        if (field[y + 3][x] == PLAYER_DOT && field[y + 2][x + 1] == EMPTY_DOT && field[y + 1][x + 2] == PLAYER_DOT && field[y][x + 3] == PLAYER_DOT) {
            aiStepYX[0] = y + 2;
            aiStepYX[1] = x + 1;
            aiStepYX[2] = 1;
        }
        if (field[y + 3][x] == PLAYER_DOT && field[y + 2][x + 1] == PLAYER_DOT && field[y + 1][x + 2] == EMPTY_DOT && field[y][x + 3] == PLAYER_DOT) {
            aiStepYX[0] = y + 1;
            aiStepYX[1] = x + 2;
            aiStepYX[2] = 1;
        }
        if (field[y + 3][x] == PLAYER_DOT && field[y + 2][x + 1] == PLAYER_DOT && field[y + 1][x + 2] == PLAYER_DOT && field[y][x + 3] == EMPTY_DOT) {
            aiStepYX[0] = y;
            aiStepYX[1] = x + 3;
            aiStepYX[2] = 1;
        }



        return aiStepYX;


    }
    // Получить координаты хода компьютера для возможности выстроить линию
    // Метод анализирует поле путем анализа всего пооля по квадратам 4х4
    static  int[] getCoordForWinInField () {
        int[] aiStepYX = {0, 0, 0};
        for (int i = 0; i <= SIZE_Y -4; i++) {
            for (int  j = 0; j<= SIZE_X -4 ;  j++) {
                aiStepYX =getCoordForWinInSubField (i, j);
                if (aiStepYX[2] == 1 )
                    return aiStepYX;
            }
        }
        return  aiStepYX;
    }
    // Получить координаты хода компьютера для возможности выстроить линию в квадрате 4х4
    static int[] getCoordForWinInSubField (int y, int x) {
        int[] aiStepYXtemp = {0, 0, 0};
        int[] aiStepYX = {0, 0, 0};
        for (int i = y; i < 4 + y; i++) {
            aiStepYXtemp = getCoordCellForWinInRow (1, i, x);
            if (aiStepYXtemp[2] == 1) {
                aiStepYX[0] = aiStepYXtemp[0];
                aiStepYX[1] = aiStepYXtemp[1];
                aiStepYX[2] = aiStepYXtemp[2];
            }
        }
        for (int i = x; i < 4 + x; i++) {
            aiStepYXtemp = getCoordCellForWinInColumn (1, y, i);
            if (aiStepYXtemp[2] == 1) {
                aiStepYX[0] = aiStepYXtemp[0];
                aiStepYX[1] = aiStepYXtemp[1];
                aiStepYX[2] = aiStepYXtemp[2];
            }
        }
        aiStepYXtemp = getCoordCellForWinInDiagType1 (1, y, x);
        if (aiStepYXtemp[2] == 1) {
            aiStepYX[0] = aiStepYXtemp[0];
            aiStepYX[1] = aiStepYXtemp[1];
            aiStepYX[2] = aiStepYXtemp[2];
        }
        aiStepYXtemp = getCoordCellForWinInDiagType2 (1, y, x);
        if (aiStepYXtemp[2] == 1) {
            aiStepYX[0] = aiStepYXtemp[0];
            aiStepYX[1] = aiStepYXtemp[1];
            aiStepYX[2] = aiStepYXtemp[2];
        }

        for (int i = y; i < 4 + y; i++) {
            aiStepYXtemp = getCoordCellForWinInRow (2, i, x);
            if (aiStepYXtemp[2] == 1) {
                aiStepYX[0] = aiStepYXtemp[0];
                aiStepYX[1] = aiStepYXtemp[1];
                aiStepYX[2] = aiStepYXtemp[2];
            }
        }
        for (int i = x; i < 4 + x; i++) {
            aiStepYXtemp = getCoordCellForWinInColumn (2, y, i);
            if (aiStepYXtemp[2] == 1) {
                aiStepYX[0] = aiStepYXtemp[0];
                aiStepYX[1] = aiStepYXtemp[1];
                aiStepYX[2] = aiStepYXtemp[2];
            }
        }
        aiStepYXtemp = getCoordCellForWinInDiagType1 (2, y, x);
        if (aiStepYXtemp[2] == 1) {
            aiStepYX[0] = aiStepYXtemp[0];
            aiStepYX[1] = aiStepYXtemp[1];
            aiStepYX[2] = aiStepYXtemp[2];
        }
        aiStepYXtemp = getCoordCellForWinInDiagType2 (2, y, x);
        if (aiStepYXtemp[2] == 1) {
            aiStepYX[0] = aiStepYXtemp[0];
            aiStepYX[1] = aiStepYXtemp[1];
            aiStepYX[2] = aiStepYXtemp[2];
        }

        for (int i = y; i < 4 + y; i++) {
            aiStepYXtemp = getCoordCellForWinInRow (3, i, x);
            if (aiStepYXtemp[2] == 1) {
                aiStepYX[0] = aiStepYXtemp[0];
                aiStepYX[1] = aiStepYXtemp[1];
                aiStepYX[2] = aiStepYXtemp[2];
            }
        }
        for (int i = x; i < 4 + x; i++) {
            aiStepYXtemp = getCoordCellForWinInColumn (3, y, i);
            if (aiStepYXtemp[2] == 1) {
                aiStepYX[0] = aiStepYXtemp[0];
                aiStepYX[1] = aiStepYXtemp[1];
                aiStepYX[2] = aiStepYXtemp[2];
            }
        }
        aiStepYXtemp = getCoordCellForWinInDiagType1 (3, y, x);
        if (aiStepYXtemp[2] == 1) {
            aiStepYX[0] = aiStepYXtemp[0];
            aiStepYX[1] = aiStepYXtemp[1];
            aiStepYX[2] = aiStepYXtemp[2];
        }
        aiStepYXtemp = getCoordCellForWinInDiagType2 (3, y, x);
        if (aiStepYXtemp[2] == 1) {
            aiStepYX[0] = aiStepYXtemp[0];
            aiStepYX[1] = aiStepYXtemp[1];
            aiStepYX[2] = aiStepYXtemp[2];
        }

        return aiStepYX;
    }
    //Получить координаты для хода компьютера в возможной выигрышной строке
    static int[] getCoordCellForWinInRow (int sumAI_dot, int y, int x) {
        int[] aiStepYX = {0, 0, 0};
        int numPlayer_dot = 0;
        int numberAI_dot = 0;
        //int numberEmpty_dot = 0;
        for (int i = x; i < 4 + x; i++) {
            if(field[y][i] == PLAYER_DOT)
                numPlayer_dot++;
            if(field[y][i] == AI_DOT) {
                numberAI_dot++;
            }
            if(field[y][i] == EMPTY_DOT) {
                //numberEmpty_dot++;
                aiStepYX[0] = y;
                aiStepYX[1] = i ;
            }
        }
        if (numPlayer_dot == 0 && numberAI_dot == sumAI_dot) {
            aiStepYX[2] = 1;
        }
        return  aiStepYX;

    }
    //Получить координаты для хода компьютера в возможном выигрышном столбце
    static int[] getCoordCellForWinInColumn (int sumAI_dot, int y, int x) {
        int[] aiStepYX = {0, 0, 0};
        int numPlayer_dot = 0;
        int numberAI_dot = 0;
        for (int i = y; i < 4 + y; i++) {
            if(field[i][x] == PLAYER_DOT)
                numPlayer_dot++;
            if(field[i][x] == AI_DOT) {
                numberAI_dot++;
            }
            if(field[i][x] == EMPTY_DOT) {
                aiStepYX[0] = i;
                aiStepYX[1] = x ;
            }
        }
        if (numPlayer_dot == 0 && numberAI_dot == sumAI_dot) {
            aiStepYX[2] = 1;
        }
        return  aiStepYX;

    }
    //Получить координаты для хода компьютера в возможной выигрышной диагонали 1-го типа
    static int[] getCoordCellForWinInDiagType1 (int sumAI_dot, int y, int x) {
        int[] aiStepYX = {0, 0, 0};
        int numPlayer_dot = 0;
        int numberAI_dot = 0;

        for (int i = y, j = x; i < 4 + y; i++, j++) {
            if(field[i][j] == PLAYER_DOT)
                numPlayer_dot++;
            if(field[i][j] == AI_DOT) {
                numberAI_dot++;
            }
            if(field[i][j] == EMPTY_DOT) {
                aiStepYX[0] = i;
                aiStepYX[1] = j ;
            }
        }



        if (numPlayer_dot == 0 && numberAI_dot == sumAI_dot) {
            aiStepYX[2] = 1;
        }
        return  aiStepYX;

    }
    //Получить координаты для хода компьютера в возможной выигрышной диагонали 2-го типа
    static int[] getCoordCellForWinInDiagType2 (int sumAI_dot, int y, int x) {
        int[] aiStepYX = {0, 0, 0};
        int numPlayer_dot = 0;
        int numberAI_dot = 0;

        for (int i = y + 4 - 1, j = x; j < 4 + x; i--, j++) {
            if(field[i][j] == PLAYER_DOT)
                numPlayer_dot++;
            if(field[i][j] == AI_DOT) {
                numberAI_dot++;
            }
            if(field[i][j] == EMPTY_DOT) {
                aiStepYX[0] = i;
                aiStepYX[1] = j ;
            }
        }



        if (numPlayer_dot == 0 && numberAI_dot == sumAI_dot) {
            aiStepYX[2] = 1;
        }
        return  aiStepYX;

    }



    public static void main(String[] args) {
        initField();
        printField();

        while (true) {
            playerStep();
            printField();
            if (checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }
            aiStep();
            printField();
            if (checkWin(AI_DOT)) {
                System.out.println("WIN SkyNet");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }
        }


    }
}
