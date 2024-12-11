package model;

import java.awt.Point;
import java.util.Random;

/**
 * Класс, представляющий фигуру для игры в Тетрис.
 * Хранит информацию о структуре игровых объектов и содержит перечисление Tetrominoe.
 */
public class Figure {
    /** Размер фигуры */
    public static final int FIGURE_SIZE = 4;

    private final Point[] coordinates;
    private final Tetrominoe figureName;

    /**
     * Конструктор, создающий новый объект Figure с указанным Tetrominoe.
     *
     * @param tetrominoe имя фигуры
     */
    public Figure(final Tetrominoe tetrominoe) {
        coordinates = new Point[FIGURE_SIZE];
        for (int i = 0; i < FIGURE_SIZE; i++) {
            coordinates[i] = new Point();
            coordinates[i].setLocation(tetrominoe.getCoordinates()[i]);
        }

        this.figureName = tetrominoe;
    }

    /**
     * Создает случайную фигуру.
     *
     * @return экземпляр класса Figure с случайным именем
     */
    public static Figure getRandomFigure() {
        Random random = new Random();
        Tetrominoe[] values = Tetrominoe.values();
        return new Figure(values[Math.abs(random.nextInt()) % (Tetrominoe.TETROMINOE_NUMBER - 1) + 1]);
    }

    /**
     * Находит минимальное значение Y среди точек фигуры.
     *
     * @return минимальное значение Y
     */
    public int getMinY() {
        int minY = (int) coordinates[0].getY();

        for (int i = 1; i < FIGURE_SIZE; i++) {
            minY = Math.min(minY, (int) coordinates[i].getY());
        }

        return minY;
    }

    /**
     * Возвращает имя фигуры.
     *
     * @return имя фигуры
     */
    public Tetrominoe getFigureName() {
        return this.figureName;
    }

    /**
     * Получает координату по указанному индексу.
     *
     * @param index индекс координаты, которую нужно получить
     * @return координата по указанному индексу
     */
    public Point getCoordinate(final int index) {
        return coordinates[index];
    }
}
