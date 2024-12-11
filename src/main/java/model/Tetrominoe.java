package model;

import java.awt.Point;

/**
 * Перечисление, представляющее различные типы Тетромино в игре Тетрис.
 * Каждый Тетромино имеет уникальную форму, определяемую массивом координат Point.
 */
public enum Tetrominoe {
    /** Пустой Тетромино */
    Empty(new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)}),
    /** Z-образный Тетромино */
    ZShape(new Point[]{
            new Point(0, -1), new Point(0, 0), new Point(-1, 0), new Point(-1, 1)}),
    /** S-образный Тетромино */
    SShape(new Point[]{
            new Point(0, -1), new Point(0, 0), new Point(1, 0), new Point(1, 1)}),
    /** Линия */
    LineShape(new Point[]{
            new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(0, 2)}),
    /** T-образный Тетромино */
    TShape(new Point[]{
            new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, 1)}),
    /** Квадрат */
    SquareShape(new Point[]{
            new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)}),
    /** L-образный Тетромино */
    LShape(new Point[]{
            new Point(-1, -1), new Point(0, -1), new Point(0, 0), new Point(0, 1)}),
    /** Зеркальный L-образный Тетромино */
    MirroredLShape(new Point[]{
            new Point(1, -1), new Point(0, -1), new Point(0, 0), new Point(0, 1)});

    private final Point[] coordinates;

    /** Общее количество Тетромино */
    public static final int TETROMINOE_NUMBER = 8;

    /**
     * Конструктор, создающий Тетромино с указанными координатами.
     *
     * @param coordinates координаты, определяющие форму Тетромино
     */
    Tetrominoe(Point[] coordinates) {
        this.coordinates = coordinates;
    }

    /** @return координаты, определяющие форму Тетромино */
    public Point[] getCoordinates() {
        return coordinates;
    }
}
