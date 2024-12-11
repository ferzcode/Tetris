package controller;

import model.BoardController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Класс, который обрабатывает ввод с клавиатуры для игры Тетрис.
 * Этот класс расширяет KeyAdapter для предоставления функционала обработки ввода с клавиатуры.
 */
public class TetrisKeyAdapter extends KeyAdapter {
    private final BoardController boardController;

    /**
     * Конструктор, который создает TetrisKeyAdapter с указанным BoardController.
     *
     * @param boardController экземпляр BoardController, отвечающий за управление игровым полем
     */
    public TetrisKeyAdapter(BoardController boardController) {
        this.boardController = boardController;
    }

    /**
     * Вызывается при нажатии клавиши.
     * Обрабатывает различные события нажатия клавиш для управления игрой Тетрис.
     *
     * @param e экземпляр KeyEvent, представляющий событие нажатия клавиши
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Если игра не начата, выход из метода
        if (!boardController.getBoard().isStarted()) {
            return;
        }

        // Получение кода нажатой клавиши
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_LEFT: // Стрелка влево
                if (boardController.ableMove(boardController.getBoard().getCurrentFigure(),
                        new java.awt.Point(boardController.getBoard().getCurX() - 1, boardController.getBoard().getCurY()))) {
                    boardController.move(boardController.getBoard().getCurrentFigure(),
                            new java.awt.Point(boardController.getBoard().getCurX() - 1, boardController.getBoard().getCurY()));
                }
                break;
            case KeyEvent.VK_RIGHT: // Стрелка вправо
                if (boardController.ableMove(boardController.getBoard().getCurrentFigure(),
                        new java.awt.Point(boardController.getBoard().getCurX() + 1, boardController.getBoard().getCurY()))) {
                    boardController.move(boardController.getBoard().getCurrentFigure(),
                            new java.awt.Point(boardController.getBoard().getCurX() + 1, boardController.getBoard().getCurY()));
                }
                break;
            case KeyEvent.VK_DOWN: // Стрелка вниз
                boardController.down();
                break;
            case KeyEvent.VK_UP: // Стрелка вверх
                boardController.rotate(BoardController.Rotation.RIGHT);
                break;
            case KeyEvent.VK_SPACE: // Пробел
                boardController.dropDown();
                break;
        }
    }
}
