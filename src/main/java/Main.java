import view.StartMenu;

import java.awt.*;

/**
 * Основная точка входа для игры Тетрис. Инициализирует и запускает игру, отображая стартовое меню.
 */
public class Main {
    /**
     * Главный метод, который запускает игру Тетрис.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            final StartMenu startMenu = new StartMenu(); // Отображаем стартовое меню
        });
    }
}
