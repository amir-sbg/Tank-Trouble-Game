package game.troubleTankSample.windows;

import game.sample.ball.GameFrame;
import game.troubleTankSample.Controller;
import game.troubleTankSample.windows.LoginWindow;
import game.troubleTankSample.windows.SettingWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {

    private JFrame mainFrame;
    private JLabel mainLabel;
    private JButton settingButton;
    private JButton computerGame;
    private JButton networkGame;
    private JButton logoutButton;
    private SettingWindow settingWindow;
    private LoginWindow loginWindow;

    public MainWindow(LoginWindow loginWindow){
        this.loginWindow = loginWindow;

        settingWindow = new SettingWindow(this);

        mainFrame = new JFrame("Tank Trouble");
        mainFrame.setSize(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocation(125, 50);

        mainLabel = new JLabel(new ImageIcon("resources/mainWindow.jpg"));
        mainLabel.setSize(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);
        mainLabel.setLocation(0,0);

        computerGame = new JButton(new ImageIcon("resources/singlePlayer.png"));
        computerGame.setPressedIcon(new ImageIcon("resources/singlePlayer2.png"));
        computerGame.setRolloverIcon(new ImageIcon("resources/singlePlayer2.png"));
        computerGame.setBounds(50, 50, 290,80);
        computerGame.setFont(new Font("Clip", Font.PLAIN, 13));
        computerGame.setOpaque(false);
        computerGame.setContentAreaFilled(false);
        computerGame.setBorderPainted(false);
        computerGame.setFocusPainted(false);
        computerGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.setVisible(false);
                Controller.startGame();
            }
        });

        networkGame = new JButton(new ImageIcon("resources/multiplayer.png"));
        networkGame.setPressedIcon(new ImageIcon("resources/multiplayer2.png"));
        networkGame.setRolloverIcon(new ImageIcon("resources/multiplayer2.png"));
        networkGame.setBounds(50, 150, 290,80);
        networkGame.setFont(new Font("Roboto", Font.PLAIN, 13));
        networkGame.setOpaque(false);
        networkGame.setContentAreaFilled(false);
        networkGame.setBorderPainted(false);
        networkGame.setFocusPainted(false);

        settingButton = new JButton(new ImageIcon("resources/setting.png"));
        settingButton.setPressedIcon(new ImageIcon("resources/setting2.png"));
        settingButton.setRolloverIcon(new ImageIcon("resources/setting2.png"));
        settingButton.setBounds(50, 250, 290,80);
        settingButton.setFont(new Font("Roboto", Font.PLAIN, 13));
        settingButton.setOpaque(false);
        settingButton.setContentAreaFilled(false);
        settingButton.setBorderPainted(false);
        settingButton.setFocusPainted(false);
        settingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.setVisible(false);
                settingWindow.setVisible(true);
            }
        });

        logoutButton = new JButton(new ImageIcon("resources/logoutButton.png"));
        logoutButton.setPressedIcon(new ImageIcon("resources/logoutButton2.png"));
        logoutButton.setRolloverIcon(new ImageIcon("resources/logoutButton2.png"));
        logoutButton.setBounds(50, 350, 290,80);
        logoutButton.setFont(new Font("Roboto", Font.PLAIN, 13));
        logoutButton.setOpaque(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.setVisible(false);
                loginWindow.setVisible(true);
            }
        });


        mainFrame.add(computerGame);
        mainFrame.add(networkGame);
        mainFrame.add(settingButton);
        mainFrame.add(logoutButton);
        mainFrame.add(mainLabel);

    }

    public void setVisible(boolean visible){
        mainFrame.setVisible(visible);
    }
}
