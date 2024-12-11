package tetris;

import controller.TetrisKeyAdapter;
import model.Board;
import model.BoardController;
import view.BoardDrawer;
import view.FinishDialog;
import view.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Главный класс игры, который создаёт основные
 * компоненты окна и запускает игру.
 * Реализация игры Тетрис с графическим интерфейсом.
 */
public class Tetris extends JFrame {
    /** Название игры */
    public final static String gameName = "Тетрис";

    /** Задержка между обновлениями */
    public static final int DELAY = 100;
    /** Период для генерации новых фигур */
    public static final int PERIOD = 700;

    /** Имя игрока */
    private String playerName;
    /** Игровое поле */
    private final Board board;
    /** Контроллер для игрового поля */
    private final BoardController boardController;
    /** Рисовальщик для игрового поля */
    private final BoardDrawer boardDrawer;
    /** Строка состояния, отображающая информацию о игре */
    private final StatusBar statusBar;
    /** Таймер для управления обновлениями игры */
    private final Timer timer;

    /**
     * Конструктор игры Тетрис с указанным именем игрока.
     *
     * @param playerName имя игрока
     */
    public Tetris(String playerName) {
        this.playerName = playerName;
        board = new Board();
        boardDrawer = new BoardDrawer(board);
        statusBar = new StatusBar();
        statusBar.setStatusBarText("Игрок: " + playerName);
        boardController = new BoardController(board);
        timer = new Timer();

        init();
        start();
    }

    /**
     * Инициализирует окно игры и компоненты.
     */
    public void init() {
        boardController.addObserver(boardDrawer);
        boardController.addObserver(statusBar);
        boardDrawer.setFocusable(true);
        boardDrawer.addKeyListener(new TetrisKeyAdapter(boardController));

        add(statusBar, BorderLayout.NORTH);
        add(boardDrawer);

        setTitle(gameName);
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Запускает игру, планируя обновления и создавая начальную фигуру.
     */
    public void start() {
        timer.scheduleAtFixedRate(new CurrentTask(), DELAY, PERIOD);
        board.setStarted(true);
        boardController.clearBoard();
        boardController.createNewFigure();
    }

    /**
     * Обновляет состояние игры.
     */
    private void update() {
        if (board.isFellStatus()) {
            board.setFellStatus(false);
            boardController.createNewFigure();
        } else {
            boardController.down();
        }
    }

    /**
     * Задача TimerTask для обновления состояния игры.
     */
    class CurrentTask extends TimerTask {
        @Override
        public void run() {
            if (board.isStarted()) {
                update();
                boardDrawer.repaint();
            } else {
                timer.cancel();
                timer.purge();
                statusBar.setStatusBarText("Игра окончена");

                // Сохранение рекорда игрока
                FinishDialog finishDialog = new FinishDialog(Tetris.this, playerName, boardController.getBoard().getNumberLinesRemoved());
            }
        }
    }
}
