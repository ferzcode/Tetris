package view;

import observer.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * Строка состояния, отображает количество очков, полученных в текущей игре
 */
public class StatusBar extends JPanel implements Observer {

    private final JLabel score;
    public StatusBar() {
        score = new JLabel();
        score.setText("Очки: ");
        score.setFont(new Font(null, Font.BOLD, 20));
        setBackground(new Color(120,126,165));
        add(score);
    }

    public void setStatusBarText(String text){
        score.setText(text);
    }

    @Override
    public void update(int data) {
        setStatusBarText("Очки: " + data);
    }
}
