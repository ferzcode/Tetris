package view;

import model.Board;
import model.Figure;
import model.Tetrominoe;
import observer.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * Класс, отвечающий за отображение игрового поля и падающих фигур
 * <p>Константы: Color[] colors - массив цветов для всех фигур</p>
 * <p>Приватные поля: Board board</p>
 */
public class BoardDrawer extends JPanel implements Observer {
    public final static String backgroundPictureName = "/game_background.png";
    public final static Color[] colors = {
            new Color(0, 0, 0), new Color(253, 200, 18),
            new Color(255, 126, 43), new Color(239, 31, 40),
            new Color(37, 175, 75), new Color(64, 67, 207),
            new Color(165, 71, 164), new Color(20, 161, 234)};

    private final Board board;

    /**
     * Конструктор, устанавливающий поле Board board
     * @param board модель для отображения
     */
    public BoardDrawer(Board board) {
        this.board = board;
        setBackground(new Color(0xFFFFFF));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Image img = Toolkit.getDefaultToolkit().getImage(StartMenu.class.getResource(backgroundPictureName));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

        drawBoard(g);
    }

    private int getSquareWidth() {
        return (int) getSize().getWidth() / board.getBoardWidth();
    }

    private int getSquareHeight() {
        return (int) getSize().getHeight() / board.getBoardHeight();
    }

    private void drawSquare(Graphics g, int x, int y, Tetrominoe tetrominoe) {
        Color color = colors[tetrominoe.ordinal()];

        g.setColor(color);
        g.fillRect(x, y, getSquareWidth(), getSquareHeight());

        g.setColor(Color.BLACK);
        g.drawLine(x, y + getSquareHeight(), x, y);
        g.drawLine(x, y, x + getSquareWidth(), y);
        g.drawLine(x, y + getSquareHeight(), x + getSquareWidth(), y + getSquareHeight());
        g.drawLine(x + getSquareWidth(), y + getSquareHeight(), x + getSquareWidth(), y);
    }

    /**
     * Отображение игрового поля и падающей фигуры
     * @param g  Graphics
     */
    public void drawBoard(Graphics g) {
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - board.getBoardHeight() * getSquareHeight();

        for (int i = 0; i < board.getBoardHeight(); ++i) {
            for (int j = 0; j < board.getBoardWidth(); ++j) {
                Tetrominoe figure = board.getFigure(new Point(j, board.getBoardHeight() - i - 1));

                if (figure != Tetrominoe.Empty) {
                    drawSquare(g, j * getSquareWidth(), boardTop + i * getSquareHeight(), figure);
                }
            }
        }

        if (board.getCurrentFigure().getFigureName() != Tetrominoe.Empty) {
            for (int i = 0; i < Figure.FIGURE_SIZE; i++) {
                int x = board.getCurX() + board.getCurrentFigure().getCoordinate(i).x;
                int y = board.getCurY() - board.getCurrentFigure().getCoordinate(i).y;
                drawSquare(g, x * getSquareWidth(), boardTop + (board.getBoardHeight() - y - 1) * getSquareHeight(),
                        board.getCurrentFigure().getFigureName());
            }
        }
    }

    @Override
    public void update(int data) {
        this.repaint();
    }
}
