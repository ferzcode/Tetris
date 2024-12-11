package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Панель меню для окна игры Тетрис, предоставляющая опции для информации об игре, правил, рекордов и выхода из игры.
 */
public class MenuBar extends JMenuBar {
    /**
     * Конструктор панели меню с указанным родительским окном JFrame.
     *
     * @param parentsFrame родительское окно JFrame
     */
    public MenuBar(JFrame parentsFrame) {
        JMenu menu = new JMenu("Игра");

        JMenuItem infoItem = new JMenuItem("Информация");
        JMenuItem rulesItem = new JMenuItem("Правила");
        JMenuItem recordsItem = new JMenuItem("Рекорды");
        JMenuItem exitItem = new JMenuItem("Выход");

        infoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showOptionDialog(null,
                            Files.readString(Paths.get("src/main/resources/info.txt")),
                            "ИНФОРМАЦИЯ",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, null, null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        rulesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showOptionDialog(null,
                            Files.readString(Paths.get("src/main/resources/rules.txt")),
                            "ПРАВИЛА",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, null, null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        recordsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showOptionDialog(null,
                            Files.readString(Paths.get("src/main/resources/records.txt")),
                            "РЕКОРДЫ",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, null, null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showOptionDialog(null,
                        "Вы хотите выйти?",
                        "ВЫХОД",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);

                if (answer == JOptionPane.YES_OPTION) {
                    parentsFrame.dispose();
                }
            }
        });

        menu.add(infoItem);
        menu.add(rulesItem);
        menu.add(recordsItem);
        menu.add(exitItem);
        menu.add(new JSeparator());

        this.add(menu);
        this.setBackground(new Color(12, 12, 12, 124));
    }
}

