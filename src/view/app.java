package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.acction_top;
import controller.action_login;
import controller.action_menu;
import model.DB;
import model.Person;

public class app {
    private static JFrame frame;
    private static login lg;
    private static FlappyBird flappybird;
    private static topPlayer topplayer;
    private DB topPlay;
    private action_login ac;
    private acction_top actop;
    private action_menu ac_menu;

    public static void main(String[] args) {
        new app();
    }

    public app() {
        
        
        topPlay = new DB();
        topPlay.connectDB();

        lg = new login(topPlay);
        ac = new action_login(lg, this);
        lg.addLoginListener(ac);

        flappybird = new FlappyBird(topPlay);

        ac_menu = new action_menu(this);
        topplayer = new topPlayer(topPlay);
        actop = new acction_top(this);
        topplayer.addTop(actop);
        init();
    }

    public void init() {
        int boardWidth = 360;
        int boardHeight = 640;

        frame = new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar jmenu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem top = new JMenuItem("Top");
        top.addActionListener(ac_menu);
        file.add(top);
        jmenu.add(file);

        frame.setJMenuBar(jmenu);
        frame.add(lg);
        frame.setVisible(true);
    }

    public void startGame(String name) {
        if (lg != null && flappybird != null) {
            Person ps = new Person(name, null);
            this.topPlay = new DB(ps);
            flappybird = new FlappyBird(topPlay);
            frame.remove(lg);
            frame.add(flappybird);
            flappybird.requestFocus();
            frame.pack();
            frame.revalidate();
            frame.repaint();
        }
    }

    public void showTopPlayer() {
        if (lg != null && topplayer != null) {
            frame.remove(lg);
            frame.add(topplayer);
            frame.revalidate();
            frame.repaint();
        }
    }

    public void showLogin() {
        if (topplayer != null && lg != null) {
            frame.remove(topplayer);
            frame.add(lg);
            frame.revalidate();
            frame.repaint();
        }
    }

    public void showTopPlayerMenu() {
        if (flappybird != null && topplayer != null) {
            frame.remove(flappybird);
            frame.add(topplayer);
            frame.revalidate();
            frame.repaint();
        }
    }
}
