package view;

import tetris.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Окно, которое открывается первым при загрузке приложения.
 * Единственная кнопка "Start" запускает игру (создаётся класс Tetris)
 */
public class StartMenu extends JFrame {

    public final static String backgroundPictureName = "/tetris_background.png";
    public final static String gameName = "Тетрис";

    public StartMenu(){
        JTextField playerNameField = new JTextField(20); // Поле для ввода никнейма
        Container container = new Container();
        MenuBar menuBar = new MenuBar(this);

        JPanel contentPane = new JPanel(new GridBagLayout()) {
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(StartMenu.class.getResource(backgroundPictureName));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        setTitle(gameName);
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        container.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 10));

        // Добавляем обработчик для поля ввода никнейма
        playerNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(playerNameField.getText()); // Запускаем игру с никнеймом
            }
        });

        playerNameField.setFont(new Font("MV Boli", Font.BOLD, 20));
        container.add(playerNameField);

        contentPane.add(container);
        setJMenuBar(menuBar);
        setContentPane(contentPane);
        setVisible(true);
    }

    // Метод для запуска игры с указанным никнеймом
    private void startGame(String playerName) {
        // Проверяем валидность никнейма
        if (playerName == null || playerName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Пожалуйста, введите корректное имя пользователя.",
                    "Некорректное имя пользователя",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        dispose(); // Закрываем текущее окно
        new Tetris(playerName); // Запускаем игру с указанным никнеймом
    }
}
