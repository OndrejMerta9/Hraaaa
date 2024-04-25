import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GamePanel panel;
    GameFrame(){
        setTitle("Pytlák");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        panel = new GamePanel();
        add(panel);
        setBackground(Color.CYAN);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
