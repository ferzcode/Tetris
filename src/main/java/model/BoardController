package model;

import observer.ConcreteObservable;
import java.awt.Point;

import static model.Figure.FIGURE_SIZE;

/**
 * Класс, управляющий доской и её отображением.
 * <p>Приватные поля: Board board.<p/>
 *
 * @since java 16
 */
public final class BoardController extends ConcreteObservable {
    private final Board board;

    public enum Rotation {
        LEFT, RIGHT
    }

    // Конструктор
    public BoardController(Board board) {
        this.board = board;
    }

    // Геттер для поля board
    public Board getBoard() {
        return board;
    }

    /**
     * Создаёт новый экземпляр класса Figure, устанавливает координаты
     * в центре верхней части доски.
     * Если фигура не может двигаться вниз, метод устанавливает статус "начат" в false.
     */
    public void createNewFigure() {
        Figure newFigure = Figure.getRandomFigure();

        if (!ableMove(newFigure, new Point(board.getBoardWidth() / 2,
                board.getBoardHeight() - 1 + newFigure.getMinY()))) {
            board.setStarted(false);
            notifyObservers(board.getNumberLinesRemoved());
            return;
        }

        board.setCurrentFigure(newFigure);
        board.setCurX(board.getBoardWidth() / 2);
        board.setCurY(board.getBoardHeight() - 1 + board.getCurrentFigure().getMinY());
        board.setFellStatus(false);
        notifyObservers(board.getNumberLinesRemoved());
    }

    /**
     * Устанавливает все элементы доски в состояние "Пусто".
     */
    public void clearBoard() {
        for (int i = 0; i < board.getBoardHeight() * board.getBoardWidth(); i++) {
            board.setFigure(i, Tetrominoe.Empty);
        }
        notifyObservers(board.getNumberLinesRemoved());
    }

    /**
     * Проверяет, может ли фигура переместиться на указанную координату.
     *
     * @param figure перемещаемая фигура.
     * @param point  координата, на которую нужно переместить фигуру.
     * @return возможность перемещения на точку.
     */
    public boolean ableMove(final Figure figure, final Point point) {
        for (int i = 0; i < FIGURE_SIZE; ++i) {
            int x = point.x + figure.getCoordinate(i).x;
            int y = point.y - figure.getCoordinate(i).y;

            if (x < 0 || x >= board.getBoardWidth() || y < 0 || y >= board.getBoardHeight()) {
                return false;
            }

            if (board.getFigure(new Point(x, y)) != Tetrominoe.Empty) {
                return false;
            }
        }

        return true;
    }

    /**
     * Перемещает фигуру на указанную точку, если это возможно.
     *
     * @param figure перемещаемая фигура.
     * @param point  координата, на которую перемещается фигура.
     */
    public void move(final Figure figure, final Point point) {
        board.setCurX(point.x);
        board.setCurY(point.y);
        board.setCurrentFigure(figure);
        notifyObservers(board.getNumberLinesRemoved());
    }

    /**
     * Функция вращает фигуру в указанном направлении.
     * Если при вращении индекс любого квадрата фигуры выходит за границу доски,
     * функция перемещает фигуру, чтобы она полностью помещалась на доске.
     *
     * @param rotation направление вращения.
     */
    public void rotate(Rotation rotation) {
        if (board.getCurrentFigure().getFigureName()
                .equals(Tetrominoe.SquareShape)) {
            return;
        }

        Figure result = new Figure(board.getCurrentFigure().getFigureName());
        for (int i = 0; i < FIGURE_SIZE; i++) {

            if (rotation == Rotation.LEFT) {
                result.getCoordinate(i).setLocation(board.getCurrentFigure().getCoordinate(i).getY(),
                        (-1) * board.getCurrentFigure().getCoordinate(i).getX());
            }

            if (rotation == Rotation.RIGHT) {
                result.getCoordinate(i).setLocation(-board.getCurrentFigure().getCoordinate(i).getY(),
                        board.getCurrentFigure().getCoordinate(i).getX());
            }

        }

        for (int i = 0; i < FIGURE_SIZE; i++) {
            int x = board.getCurX() + result.getCoordinate(i).x;
            if (x < 0) {
                move(result, new Point(board.getCurX() + 1, board.getCurY()));
            }
            if (x >= board.getBoardWidth()) {
                move(result, new Point(board.getCurX() - 1, board.getCurY()));
            }
        }

        for (int i = 0; i < FIGURE_SIZE; i++) {
            int x = board.getCurX() + result.getCoordinate(i).x;
            int y = board.getCurY() - result.getCoordinate(i).y;

            if (board.getFigure(new Point(x, y)) != Tetrominoe.Empty)
                return;
        }

        if (!ableMove(result, new Point(board.getCurX(), board.getCurY() - 1)))
            return;

        board.setCurrentFigure(result);
        notifyObservers(board.getNumberLinesRemoved());
    }

    /**
     * Проверяет все строки, и если строка заполнена, удаляет её.
     */
    public void removeFullLines() {
        int numFullLines = 0;
        for (int i = board.getBoardHeight() - 1; i >= 0; --i) {
            boolean lineIsFull = true;
            for (int j = 0; j < board.getBoardWidth(); ++j) {
                if (board.getFigure(new Point(j, i)) == Tetrominoe.Empty) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                numFullLines++;

                for (int k = i; k < board.getBoardHeight() - 1; k++) {
                    for (int j = 0; j < board.getBoardWidth(); j++) {
                        board.getBoardField()[(k * board.getBoardWidth()) + j] = board.getFigure(new Point(j, k + 1));
                    }
                }
            }
        }

        if (numFullLines > 0) {
            board.addNumberLinesRemoved(numFullLines);
            notifyObservers(board.getNumberLinesRemoved());
        }
    }

    /**
     * Перемещает текущую фигуру на одну строку вниз, если это возможно.
     */
    public void down() {
        if (!ableMove(board.getCurrentFigure(), new Point(board.getCurX(), board.getCurY() - 1))) {
            pieceDropped();
        } else {
            move(board.getCurrentFigure(), new Point(board.getCurX(), board.getCurY() - 1));
        }
        notifyObservers(board.getNumberLinesRemoved());
    }

    /**
     * Опускает фигуру максимально вниз, пока она не коснётся другой фигуры.
     */
    public void dropDown() {
        int newY = board.getCurY();

        while (newY > 0) {
            if (!ableMove(board.getCurrentFigure(), new Point(board.getCurX(), newY - 1))) {
                break;
            } else {
                move(board.getCurrentFigure(), new Point(board.getCurX(), board.getCurY() - 1));
            }

            --newY;
        }

        pieceDropped();
        notifyObservers(board.getNumberLinesRemoved());
    }

    /**
     * Обрабатывает касание фигуры с другими фигурами или нижней частью доски.
     * <p>Проверяет, заполнена ли какая-либо строка.
     * Вызывает создание следующей фигуры.<p/>
     */
    public void pieceDropped() {
        for (int i = 0; i < FIGURE_SIZE; ++i) {
            int x = board.getCurX() + board.getCurrentFigure().getCoordinate(i).x;
            int y = board.getCurY() - board.getCurrentFigure().getCoordinate(i).y;
            board.getBoardField()[(y * board.getBoardWidth()) + x] = board.getCurrentFigure().getFigureName();
        }

        removeFullLines();
        board.setFellStatus(true);
        createNewFigure();
    }
}
