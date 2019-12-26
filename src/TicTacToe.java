import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    /*
    блок настроек игры
     */
    private static final int SIZE = 5; // размер поля
    private static char[][] map = new char[SIZE][SIZE];
    // поле игры

    private static boolean SILLY_MODE = false;
    private static boolean SCORING_MODE = true;


    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {

        initMap();
        printMap();
        isMapFull();


        while (true) {
            humanTurn();
            if (isEndGame(DOT_X)) {
                break;
            }

            computerTurn();
            if (isEndGame(DOT_O)) {
                break;
            }

        }

        System.out.println("Игра завершена");
    }

    /*
            Метод подготовки игрового поля
     */
    public static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        /**
         * Метод проверки на полностью заполненное поле
         * return boolean - признак оптимальности
         */
    }

    private static boolean isMapFull() {
        boolean result = true;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    //пустая клетка найдена
                    result = false;
                    break;
                }

            }
            //выходим из внешнего цикла, если пустая клетка найдена
            if (!result) {
                break;
            }
        }
        return result;
    }


    //ход человека
    private static void humanTurn() {

        int x, y;

        do {
            System.out.println("Введите координаты через пробел строки, а затем столбец");

            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));

        map[y][x] = DOT_X;
    }


    //ход компьютера

    private static void computerTurn() {

        int x = -1;
        int y = -1;
        boolean moveFound = false;
            /*
            if (!SCORING_MODE){
                //отметка о том что ход не найден

                //упрощенный алгоритм

                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; i++) {
                        if (map[i][j] == DOT_EMPTY) {
                            //проверка направлений

                            if (i - 1 >= 0 && j - 1 >= 0 && map[i - 1][j - 1] == DOT_O) {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("LU");
                            } else if (i - 1 >= 0 && map[i - 1][j] == DOT_O) {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("U");
                            } else if (i - 1 >= 0 && j + 1 < SIZE && map[i - 1][j + 1] == DOT_O) {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("RU");
                            } else if (j + 1 < SIZE && map[i][j + 1] == DOT_O) {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("R");
                            } else if (i + 1 < SIZE && j + 1 < SIZE && map[i + 1][j + 1] == DOT_O) {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("RD");
                            } else if (i + 1 < SIZE && map[i + 1][j] == DOT_O) {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("D");
                            } else if (i + 1 < SIZE && j - 1 >= 0 && map[i + 1][j - 1] == DOT_O) {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("LD");
                            } else if (j - 1 >= 0 && map[i][j - 1] == DOT_O) {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("L");
                            }

                        }
                        //если ход найден, прерываем цикл
                        if (moveFound) {
                            break;
                        }
                    }

                    //если ход найден, прерываем внешний цикл
                    if (moveFound) {
                        break;
                    }
                }
        }
            */
        //алгоритм с подсчетом очков


        int maxScoreFieldX = -1;
        int maxScoreFieldY = -1;
        int maxScore = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int fieldScore = 0;

                if (map[i][j] == DOT_EMPTY) {
                    //проверяем направления
                    if (i - 1 >= 0 && j - 1 >= 0 && map[i - 1][j - 1] == DOT_O) {
                        fieldScore++;
                        System.out.println("LU");

                    }
                    //верх
                    if (i - 1 >= 0 && map[i - 1][j] == DOT_O) {
                        fieldScore++;
                    }
                    //право верх
                    if (i - 1 >= 0 && j + 1 < SIZE && map[i - 1][j + 1] == DOT_O) {
                        fieldScore++;
                    }
                    //право
                    if (j + 1 < SIZE && map[i][j + 1] == DOT_O) {
                        fieldScore++;
                    }
                    //право низ
                    if (i + 1 < SIZE && j + 1 < SIZE && map[i + 1][j + 1] == DOT_O) {
                        fieldScore++;
                    }
                    //низ
                    if (i + 1 < SIZE && map[i + 1][j] == DOT_O) {
                        fieldScore++;
                    }
                }
                if (fieldScore > maxScore) {
                    maxScore = fieldScore;
                    maxScoreFieldX = j;
                    maxScoreFieldY = i;
                }
            }
        }

        //если в цикле найдена лучшая клетка

        if (maxScoreFieldX != -1) {
            x = maxScoreFieldX;
            y = maxScoreFieldY;
        }


        if (x == -1) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
            System.out.println("RANDOM");

        }


        System.out.println("Компьютер сделал ход " + (y + 1) + " " + (x + 1));
        map[y][x] = DOT_O;

    }


    /**
     * Метод валидации запрашиваемой ячейки на корректоность
     *
     * @param x - координаты коризонтали
     * @param y - координаты вертикали
     *          return boolean - признак валидности
     */

    private static boolean isCellValid(int x, int y) {
        boolean result = true;

        //проверка координаты

        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            result = false;
        }

        // проверка заполнености ячейки

        else if (map[y][x] != DOT_EMPTY) {
            result = false;
        }
        return result;
    }


    private static boolean checkWin(char playerSymbol) {
        boolean result = false;

        if (checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol)) {

            result = true;
        }

        return result;
    }

    private static boolean checkWinDiagonals(char playerSymbol){
                boolean leftRight, rightLeft, result;

                leftRight= true;
                rightLeft = true;
                result = false;
                for (int i =0; i< SIZE; i++){
                    leftRight &= (map [i][i] == playerSymbol);
                    rightLeft &= (map [SIZE-i-1][i] == playerSymbol);
                }

                if(leftRight || rightLeft) {
                    result= true;
                }

                return result;
    }

    private static boolean checkWinLines(char playerSymbol){
            boolean cols, rows, result;

            result= false;

            for(int col = 0; col< SIZE; col++){
                cols = true;
                rows = true;

                for(int row= 0; row< SIZE; row++){
                    cols &= (map[col][row] == playerSymbol);
                    rows &= (map[row][col]== playerSymbol);
                }

                //Условие после каждой проверки столбца и колонки
                //позволяет остановить дальнейшее выполнение, без  проверки
                //всех остальных столбцов и строк.
                if(cols || rows){
                    result = true;
                    break;
                }

                if(result){
                    break;
                }
            }

        return result;
    }
            /**
             * Метод проверки игры на завершение
             * @param playerSymbol - символ, которым играет игрок
             * return boolean - признак завершения игры
             */

            private static boolean isEndGame(char playerSymbol){
                boolean result = false;

                printMap();

                //проверка неообходимости следующего хода

            if(checkWin(playerSymbol)){
                System.out.println("Победили " + playerSymbol);
                result = true;
            }

            else if(isMapFull()){
                System.out.println("Ничья ");
                result= true;
            }
                return result;
            }

            }



