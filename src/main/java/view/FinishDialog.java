package view;

import tetris.Tetris;
import tetris.RecordManager;

import javax.swing.*;

/**
 * Диалоговое окно, которое отображается в конце игры.
 * При нажатии OK начинается новая игра (класс Tetris создаётся заново),
 * при нажатии на крестик открывается класс StartMenu (класс StartMenu
 * загружается снова)
 */
public class FinishDialog {
    public FinishDialog(JFrame parentsFrame, String playerName, int score) {
        // Сохранение рекорда игрока
        RecordManager recordsManager = new RecordManager("src/main/resources/records.txt");
        recordsManager.addRecord(playerName, score);

        int answer = JOptionPane.showOptionDialog(null,
                "Вы хотите начать заново?",
                "Игра окончена",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, new Object[]{"OK", "Отмена"}, "OK");

        if (answer == JOptionPane.OK_OPTION) {
            parentsFrame.dispose();
            new Tetris(playerName); // Передаем никнейм игрока в конструктор Tetris
        } else {
            parentsFrame.dispose();
            new StartMenu(); // Предполагаем, что у вас есть класс StartMenu
        }
    }
}
