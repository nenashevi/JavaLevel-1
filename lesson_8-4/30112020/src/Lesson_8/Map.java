package Lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Map extends JPanel {
    static int SIZE_X = 5;
    static int SIZE_Y = 5;
    static String gameStatus = "";
    static int currentPlayer = 1;

    static char[][] field; //= new char[SIZE_Y][SIZE_X];
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static char PLAYER_DOT = 'X';
    static char AI_DOT = 'O';
    static char EMPTY_DOT = '.';
    static char PLAYER_1 = 'X';
    static char PLAYER_2 = 'O';


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

    static void playerStep(int y, int x, char DOT) {
        //int x,y;
//        do {
//            System.out.println("Введите координаты: X Y (1-3)");
//            x = scanner.nextInt() - 1;
//            y = scanner.nextInt() - 1;
//        } while (!isCellValid(y,x));
        if (!isCellValid(y,x)) {
            System.out.println("Значения ячеек выходят за допустимые границы");
            return;
        }
        setSym(y,x, DOT);
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
            //System.out.println("Ходит SkyNet");


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
            if (field[i][j] == PLAYER_DOT)
                numPlayer_dot++;
            if (field[i][j] == AI_DOT) {
                numberAI_dot++;
            }
            if (field[i][j] == EMPTY_DOT) {
                aiStepYX[0] = i;
                aiStepYX[1] = j;
            }
        }


        if (numPlayer_dot == 0 && numberAI_dot == sumAI_dot) {
            aiStepYX[2] = 1;
        }
        return aiStepYX;
    }






    //********************************************************************************************************
    //********************************************************************************************************
    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    //int[][] field;
    int fieldSizeX;
    int fieldSizeY;
    int winLen;
    int gameMode; // 0 - H vs Ai, 1 - H vs H
    private final GameWindow gameWindow;

    int cellHeight;
    int cellWidth;

    boolean isInitialized = false;

    Map(GameWindow gameWindow) {
        this.gameWindow =gameWindow;
        gameWindow.setTextToInfoGame("Go start the game");
        setBackground(Color.ORANGE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //super.mouseReleased(e);
                update(e);
            }
        });
    }

    void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        //System.out.println("x " + cellX + " y " + cellY);
        if(gameMode == 0) {
            gameHumanVsAi(cellX, cellY);
            repaint();
            return;
        }
        if(gameMode == 1) {
            gameHumanVsHuman(cellX, cellY);
            repaint();
            return;
        }






    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    void startNewGame(int mode, int SIZE_X, int SIZE_Y, int winLen) {
        //System.out.println(mode + " " + fieldSizeX + " " + fieldSizeY + " " + winLen);
        this.gameMode = mode;
        this.SIZE_X = SIZE_X;
        this.SIZE_Y = SIZE_Y;
        this.winLen = winLen;
        field = new char[SIZE_Y][SIZE_X];
        isInitialized = true;
        gameStatus ="";
        initField();
        if (mode == MODE_H_V_A) {
            gameWindow.setTextToInfoGame("Player is turning");
        }
        if (mode == MODE_H_V_H) {
            gameWindow.setTextToInfoGame("Player #1 is turning");
        }
        //printField();

        repaint();
    }

    void render(Graphics g) {
        if (!isInitialized) return;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellWidth = panelWidth / SIZE_X;
        cellHeight = panelHeight / SIZE_Y;

        // рисуем горизонтальные полоски
        for (int i = 0; i < SIZE_Y; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        // рисуем вертикальные полоски
        for (int i = 0; i < SIZE_X; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
        //paintSym(g, 2, 2);
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if(field[i][j] == PLAYER_DOT) {
                    g.drawLine(j * cellWidth, i * cellHeight, (j+1) * cellWidth, (i + 1) * cellHeight);
                    g.drawLine(j * cellWidth, (i * cellHeight) + cellHeight, (j+1) * cellWidth, i * cellHeight);
                }
                if(field[i][j] == AI_DOT) {
                    //g.drawLine(j * cellWidth, i * cellHeight, (j+1) * cellWidth, (i + 1) * cellHeight);
                    g.fillOval(j * cellWidth, i * cellHeight, cellWidth, cellHeight);

                }

            }

        }
        //add(new JLabel("Player #1 is turning"));
    }
    void gameHumanVsAi(int cellX, int cellY) {
        if(gameStatus == "playerWin" || gameStatus == "draw" || gameStatus == "aiWin") {
            //System.out.println("Game over");
            gameWindow.setTextToInfoGame("Game over");
            return;
        }
        playerStep(cellY, cellX, PLAYER_DOT);
        //printField();

        if (checkWin(PLAYER_DOT)) {
            //System.out.println("Player WIN!");
            gameWindow.setTextToInfoGame("Player WIN !");
            gameStatus = "playerWin";
            //break;
            return;
        }
        if (isFieldFull()) {
            //System.out.println("DRAW");
            gameWindow.setTextToInfoGame("DRAW !");
            gameStatus = "draw";
            //break;
            return;
        }
        aiStep();
        //printField();
        if (checkWin(AI_DOT)) {
            //System.out.println("WIN SkyNet");
            gameWindow.setTextToInfoGame("SkyNet WIN !");
            gameStatus = "aiWin";
            //break;
            return;
        }
        if (isFieldFull()) {
            //System.out.println("DRAW");
            gameWindow.setTextToInfoGame("DRAW !");
            //break;
            return;
        }
        gameWindow.setTextToInfoGame("Player is turning");
    }
    void gameHumanVsHuman(int cellX, int cellY) {
        if(gameStatus == "player #1" || gameStatus == "draw" || gameStatus == "player #2") {
            //System.out.println("Game over, " + gameStatus + "win");
            gameWindow.setTextToInfoGame("Game over");
            return;
        }

        if(currentPlayer == 1) {
            //add(new JLabel("Player #1 is turning"));
            playerStep(cellY, cellX, PLAYER_1);
            //printField();
            if (checkWin(PLAYER_1)) {
                //System.out.println("Player #1 WIN!");
                gameWindow.setTextToInfoGame("Player #1 WIN!");
                gameStatus = "player #1";
                //break;
                return;
            }
            if (isFieldFull()) {
                //System.out.println("DRAW");
                gameWindow.setTextToInfoGame("DRAW !");
                gameStatus = "draw";
                //break;
                return;
            }
            currentPlayer = 2;
            //System.out.println("Player #2 is turning ");
            gameWindow.setTextToInfoGame("Player #2 is turning");
            return;
        }
        if(currentPlayer == 2) {
            playerStep(cellY, cellX, PLAYER_2);
            //printField();
            if (checkWin(PLAYER_2)) {
                //System.out.println("Player #2 WIN!");
                gameWindow.setTextToInfoGame("Player #2 WIN!");
                gameStatus = "player #2";
                //break;
                return;
            }
            if (isFieldFull()) {
                //System.out.println("DRAW");
                gameWindow.setTextToInfoGame("DRAW !");
                gameStatus = "draw";
                //break;
                return;
            }
            currentPlayer = 1;
            //System.out.println("Player #1 is turning ");
            gameWindow.setTextToInfoGame("Player #1 is turning");
            return;
        }


        //aiStep();
//        System.out.println("Player #2 is turning ");
//        playerStep(cellY, cellX, PLAYER_2);
//        printField();
//        if (checkWin(PLAYER_2)) {
//            System.out.println("Player #2 WIN!");
//            gameStatus = "player #2";
//            //break;
//        }
//        if (isFieldFull()) {
//            System.out.println("DRAW");
//            //break;
//        }
    }

    void paintSym (Graphics g, int x, int y) {
        g.drawLine(x * cellWidth, y * cellHeight, (x+1) * cellWidth, (y + 1) * cellHeight);
        g.drawLine(x * cellWidth, (y * cellHeight) + cellHeight, (x+1) * cellWidth, y * cellHeight);
    }





}
