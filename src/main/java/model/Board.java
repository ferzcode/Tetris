package model;

import java.awt.Point;

/**
 * Класс, представляющий игровое поле в Тетрисе.
 * Управляет состоянием игрового поля и предоставляет методы для взаимодействия с ним.
 */
public class Board {
    /** Ширина игрового поля */
    private static final int BOARD_WIDTH = 10;
    /** Высота игрового поля */
    private static final int BOARD_HEIGHT = 20;

    private int numberLinesRemoved = 0;
    private int curX;
    private int curY;
    private boolean isStarted;
    private boolean fellStatus;
    private Figure currentFigure;
    private final Tetrominoe[] boardField;

    /**
     * Конструктор, создающий объект Board.
     * Инициализирует игровое поле пустыми ячейками и устанавливает статус падения в false.
     */
    public Board() {
        boardField = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];
        fellStatus = false;
    }

    /** @return ширину игрового поля */
    public int getBoardWidth() {
        return BOARD_WIDTH;
    }

    /** @return высоту игрового поля */
    public int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    /** @return текущую координату x падающего Тетромино */
    public int getCurX() {
        return curX;
    }

    /**
     * Устанавливает текущую координату x падающего Тетромино.
     *
     * @param curX координата x для установки
     */
    public void setCurX(int curX) {
        this.curX = curX;
    }

    /** @return текущую координату y падающего Тетромино */
    public int getCurY() {
        return curY;
    }

    /**
     * Устанавливает текущую координату y падающего Тетромино.
     *
     * @param curY координата y для установки
     */
    public void setCurY(int curY) {
        this.curY = curY;
    }

    /** @return true, если игра начата; false в противном случае */
    public boolean isStarted() {
        return isStarted;
    }

    /**
     * Устанавливает статус начала игры.
     *
     * @param isStarted true, если игра начата; false в противном случае
     */
    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    /** @return true, если Тетромино упало; false в противном случае */
    public boolean isFellStatus() {
        return fellStatus;
    }

    /**
     * Устанавливает статус падения Тетромино.
     *
     * @param fellStatus true, если Тетромино упало; false в противном случае
     */
    public void setFellStatus(boolean fellStatus) {
        this.fellStatus = fellStatus;
    }

    /** @return текущее падающее Тетромино */
    public Figure getCurrentFigure() {
        return currentFigure;
    }

    /**
     * Устанавливает текущее падающее Тетромино.
     *
     * @param currentFigure Тетромино для установки
     */
    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    /** @return массив, представляющий игровое поле */
    public Tetrominoe[] getBoardField() {
        return boardField;
    }

    /** @return количество удалённых линий */
    public int getNumberLinesRemoved() {
        return numberLinesRemoved;
    }

    /**
     * Устанавливает Тетромино в указанной позиции игрового поля.
     *
     * @param index индекс, в который нужно установить Тетромино
     * @param figure Тетромино для установки
     */
    public void setFigure(final int index, final Tetrominoe figure) {
        boardField[index] = figure;
    }

    /**
     * Получает Тетромино в указанной точке игрового поля.
     *
     * @param point точка, из которой нужно получить Тетромино
     * @return Тетромино в указанной точке
     */
    public Tetrominoe getFigure(final Point point) {
        if (point.y * BOARD_WIDTH + point.x < BOARD_WIDTH * BOARD_HEIGHT - 1) {
            return boardField[(point.y * BOARD_WIDTH) + point.x];
        }
        return Tetrominoe.Empty;
    }

    /**
     * Добавляет указанное количество удалённых линий к общему числу.
     *
     * @param lines количество линий для добавления
     */
    public void addNumberLinesRemoved(final int lines) {
        numberLinesRemoved += lines;
    }
}
