package game.troubleTankSample.windows;

import game.sample.ball.GameFrame;
import game.troubleTankSample.windows.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class LoginWindow {

    private JFrame loginFrame;
    private JLabel loginLabel;
    private JLabel loginIcon;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    public MainWindow mainWindow;

    public LoginWindow(){
        mainWindow = new MainWindow(this);

        loginFrame = new JFrame("Login");
        loginFrame.setSize(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);
        loginFrame.setResizable(false);
        loginFrame.setLayout(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocation(125, 50);

        loginLabel = new JLabel(new ImageIcon("resources/Login.jpg"));
        loginLabel.setSize(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);
        loginLabel.setLocation(0,0);

        usernameField = new JTextField();
        usernameField = BorderUtil.createTextField(usernameField, "Username", 500, 290, 280, 40);
        usernameField.setFont(new Font("Roboto", Font.PLAIN, 13));

        passwordField = new JPasswordField();
        passwordField = BorderUtil.createTextField(passwordField, "Password", 500, 340, 280, 40);
        passwordField.setFont(new Font("Roboto", Font.PLAIN, 13));;


        loginButton = new JButton(new ImageIcon("resources/loginButton.png"));
        loginButton.setPressedIcon(new ImageIcon("resources/loginButton2.png"));
        loginButton.setRolloverIcon(new ImageIcon("resources/loginButton2.png"));
        loginButton.setForeground(Color.BLACK);
        loginButton.setBackground(Color.GRAY);
        loginButton.setBounds(492, 395, 290,50);
        loginButton.setFont(new Font("Roboto", Font.PLAIN, 13));
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);

        loginFrame.add(usernameField);
        loginFrame.add(passwordField);
        loginFrame.add(loginButton);
        loginFrame.add(loginLabel);


        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setFont(new Font("Roboto", Font.PLAIN, 13));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setFont(new Font("Roboto", Font.PLAIN | Font.ITALIC, 13));
                    usernameField.setText("Username");
                }
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordField.getText().equals("Password")) {
                    passwordField.setText("");
                    passwordField.setFont(new Font("Roboto", Font.PLAIN, 13));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().isEmpty()) {
                    passwordField.setFont(new Font("Roboto", Font.PLAIN | Font.ITALIC, 13));
                    passwordField.setText("Password");
                }
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginFrame.setVisible(false);
                mainWindow.setVisible(true);
            }
        });

    }

    public void setVisible(boolean visible){
        loginFrame.setVisible(visible);
        usernameField.requestFocus(false);
        loginButton.requestFocus(true);
    }
}

class BorderUtil {

    @SuppressWarnings({ "unchecked", "serial" })
    public static <T extends JTextField> T createTextField(T field, String text, int x, int y, int width,
                                                           int height) {

        T f = null;
        if (field instanceof JPasswordField) {
            f = (T) new JPasswordField(text) {
                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor(getBackground());
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                    super.paintComponent(g);
                }
            };
        } else {
            f = (T) new JTextField(text) {
                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor(getBackground());
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                    super.paintComponent(g);
                }
            };
        }

        f.setBounds(x, y, width, height);
        f.setForeground(Color.GRAY);
        f.setHorizontalAlignment(JTextField.CENTER);
        f.setOpaque(false);
        f.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        return f;
    }
}