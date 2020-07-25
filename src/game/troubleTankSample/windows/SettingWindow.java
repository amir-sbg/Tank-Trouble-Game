package game.troubleTankSample.windows;

import game.sample.ball.GameFrame;
import game.troubleTankSample.windows.MainWindow;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

public class SettingWindow {
    private JFrame settingFrame;
    private JLabel settingLabel;
    private JButton backArrow;
    private JLabel playerLabel;
    private JLabel playerValue;
    private JLabel timeLabel;
    private JLabel timeValue;
    private JLabel singleWinsLabel;
    private JLabel singleWinsValue;
    private JLabel singleLossesLabel;
    private JLabel singleLossesValue;
    private JLabel multiPlayerWinsLabel;
    private JLabel multiPlayerWinsValue;
    private JLabel multiPlayerLossesLabel;
    private JLabel multiPlayerLossesValue;
    private JLabel tankModelLabel;
    private JLabel tankModelBackground;
    private JLabel tankModelValue;
    private JLabel tankLifeLabel;
    private JSlider tankLifeSlider;
    private JLabel gunDamageLabel;
    private JSlider gunDamageSlider;
    private JLabel wallLifeLabel;
    private JSlider wallLifeSlider;
    private JButton changeTankColor;
    private JPanel chooseColor;
    private JLabel tankModel1;
    private JLabel tankModel2;
    private JLabel tankModel3;
    private JLabel tankModel4;
    private MainWindow mainWindow;

    public SettingWindow(MainWindow mainWindow){
        this.mainWindow = mainWindow;

        settingFrame = new JFrame("Setting");
        settingFrame.setSize(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);
        settingFrame.setResizable(false);
        settingFrame.setLayout(null);
        settingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingFrame.setLocation(125, 50);

        settingLabel = new JLabel(new ImageIcon("resources/settingBackground.jpg"));
        settingLabel.setSize(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);
        settingLabel.setLocation(0,0);

        backArrow = new JButton(new ImageIcon("resources/backArrow.png"));
        backArrow.setPressedIcon(new ImageIcon("resources/backArrow2.png"));
        backArrow.setRolloverIcon(new ImageIcon("resources/backArrow2.png"));
        backArrow.setBounds(2, 0, 150,70);
        backArrow.setOpaque(false);
        backArrow.setContentAreaFilled(false);
        backArrow.setBorderPainted(false);
        backArrow.setFocusPainted(false);
        backArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                settingFrame.setVisible(false);
                mainWindow.setVisible(true);

            }
        });

        playerLabel = new JLabel(new ImageIcon("resources/playerLabel.png"));
        playerLabel.setBounds(10, 50, 290,147);

        playerValue = new JLabel("PLAYER");
        playerValue.setForeground(new Color(162,94,40));
        playerValue.setFont(new Font("clip", Font.BOLD, 20));
        playerValue.setBounds(320, 50, 290, 147);

        timeLabel = new JLabel(new ImageIcon("resources/timeLabel.png"));
        timeLabel.setBounds(10, 120, 290,147);

        timeValue = new JLabel("0");
        timeValue.setForeground(new Color(162,94,40));
        timeValue.setFont(new Font("clip", Font.BOLD, 20));
        timeValue.setBounds(320, 120, 290, 147);

        singleWinsLabel = new JLabel(new ImageIcon("resources/singleWins.png"));
        singleWinsLabel.setBounds(10, 190, 290,147);

        singleWinsValue = new JLabel("0");
        singleWinsValue.setForeground(new Color(162,94,40));
        singleWinsValue.setFont(new Font("clip", Font.BOLD, 20));
        singleWinsValue.setBounds(320, 190, 290, 147);

        singleLossesLabel = new JLabel(new ImageIcon("resources/singleLosses.png"));
        singleLossesLabel.setBounds(10, 260, 290,147);

        singleLossesValue = new JLabel("0");
        singleLossesValue.setForeground(new Color(162,94,40));
        singleLossesValue.setFont(new Font("clip", Font.BOLD, 20));
        singleLossesValue.setBounds(320, 260, 290, 147);

        multiPlayerWinsLabel = new JLabel(new ImageIcon("resources/multiplayerWins.png"));
        multiPlayerWinsLabel.setBounds(10, 330, 290,147);

        multiPlayerWinsValue = new JLabel("0");
        multiPlayerWinsValue.setForeground(new Color(162,94,40));
        multiPlayerWinsValue.setFont(new Font("clip", Font.BOLD, 20));
        multiPlayerWinsValue.setBounds(320, 330, 290, 147);

        multiPlayerLossesLabel = new JLabel(new ImageIcon("resources/multiplayerLosses.png"));
        multiPlayerLossesLabel.setBounds(10, 400, 290,147);

        multiPlayerLossesValue = new JLabel("0");
        multiPlayerLossesValue.setForeground(new Color(162,94,40));
        multiPlayerLossesValue.setFont(new Font("clip", Font.BOLD, 20));
        multiPlayerLossesValue.setBounds(320, 400, 290, 147);

        tankModelLabel = new JLabel(new ImageIcon("resources/tankModel.png"));
        tankModelLabel.setBounds(10, 500, 290,147);

        tankModelBackground = new JLabel(new ImageIcon("resources/tankModelBackground.png"));
        tankModelBackground.setBounds(220, 500, 290,147);

        tankModelValue = new JLabel(new ImageIcon("resources/tank_dark.png"));
        tankModelValue.setBounds(220, 500, 290, 147);

        tankLifeLabel = new JLabel(new ImageIcon("resources/tankLife.png"));
        tankLifeLabel.setBounds(550, 120, 290,147);

        tankLifeSlider = new JSlider(1,5);
        tankLifeSlider.setPaintTicks(true);
        tankLifeSlider.setPaintLabels(true);
        tankLifeSlider.setMajorTickSpacing(1);
        tankLifeSlider.setUI(new CustomSliderUI(tankLifeSlider));
        tankLifeSlider.setFont(new Font("clip", Font.BOLD, 16));
        tankLifeSlider.setBounds(880, 185, 290, 40);
        tankLifeSlider.setOpaque(false);
        tankLifeSlider.setFocusable(false);
        tankLifeSlider.setForeground(new Color(162,94,40));
        tankLifeSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(tankLifeSlider.getValue());
            }
        });

        gunDamageLabel = new JLabel(new ImageIcon("resources/gunDamage.png"));
        gunDamageLabel.setBounds(550, 190, 290,147);

        gunDamageSlider = new JSlider(1,10);
        gunDamageSlider.setPaintTicks(true);
        gunDamageSlider.setPaintLabels(true);
        gunDamageSlider.setMajorTickSpacing(1);
        gunDamageSlider.setUI(new CustomSliderUI(tankLifeSlider));
        gunDamageSlider.setFont(new Font("clip", Font.BOLD, 16));
        gunDamageSlider.setBounds(880, 255, 290, 40);
        gunDamageSlider.setOpaque(false);
        gunDamageSlider.setFocusable(false);
        gunDamageSlider.setForeground(new Color(162,94,40));
        gunDamageSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(gunDamageSlider.getValue());
            }
        });

        wallLifeLabel = new JLabel(new ImageIcon("resources/wallLife.png"));
        wallLifeLabel.setBounds(550, 260, 290,147);

        wallLifeSlider = new JSlider(1,3);
        wallLifeSlider.setPaintTicks(true);
        wallLifeSlider.setPaintLabels(true);
        wallLifeSlider.setMajorTickSpacing(1);
        wallLifeSlider.setUI(new CustomSliderUI(tankLifeSlider));
        wallLifeSlider.setFont(new Font("clip", Font.BOLD, 16));
        wallLifeSlider.setBounds(880, 325, 290, 40);
        wallLifeSlider.setOpaque(false);
        wallLifeSlider.setFocusable(false);
        wallLifeSlider.setForeground(new Color(162,94,40));
        wallLifeSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(wallLifeSlider.getValue());
            }
        });

        changeTankColor = new JButton(new ImageIcon("resources/changeColor.png"));
        changeTankColor.setPressedIcon(new ImageIcon("resources/changeColor2.png"));
        changeTankColor.setRolloverIcon(new ImageIcon("resources/changeColor2.png"));
        changeTankColor.setBounds(220, 600, 290,80);
        changeTankColor.setFont(new Font("Roboto", Font.PLAIN, 13));
        changeTankColor.setOpaque(false);
        changeTankColor.setContentAreaFilled(false);
        changeTankColor.setBorderPainted(false);
        changeTankColor.setFocusPainted(false);

        chooseColor = new JPanel();
        chooseColor.setLayout(new FlowLayout(FlowLayout.CENTER,7,5));
        chooseColor.setBounds(450, 540, 200, 60);
        chooseColor.setBackground(Color.black);

        tankModel1 = new JLabel(new ImageIcon("resources/tank_blue.png"));
        tankModel2 = new JLabel(new ImageIcon("resources/tank_dark.png"));
        tankModel3 = new JLabel(new ImageIcon("resources/tank_green.png"));
        tankModel4 = new JLabel(new ImageIcon("resources/tank_sand.png"));

        chooseColor.add(tankModel1);
        chooseColor.add(tankModel2);
        chooseColor.add(tankModel3);
        chooseColor.add(tankModel4);
        settingFrame.add(chooseColor);
        chooseColor.setVisible(false);

        changeTankColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chooseColor.setVisible(true);
            }
        });

        tankModel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tankModelValue.setIcon(new ImageIcon("resources/tank_blue.png"));
                chooseColor.setVisible(false);
            }
        });

        tankModel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tankModelValue.setIcon(new ImageIcon("resources/tank_dark.png"));
                chooseColor.setVisible(false);
            }
        });

        tankModel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tankModelValue.setIcon(new ImageIcon("resources/tank_green.png"));
                chooseColor.setVisible(false);
            }
        });

        tankModel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tankModelValue.setIcon(new ImageIcon("resources/tank_sand.png"));
                chooseColor.setVisible(false);
            }
        });

        settingFrame.add(backArrow);

        settingFrame.add(playerLabel);
        settingFrame.add(playerValue);

        settingFrame.add(timeLabel);
        settingFrame.add(timeValue);

        settingFrame.add(singleWinsLabel);
        settingFrame.add(singleWinsValue);

        settingFrame.add(singleLossesLabel);
        settingFrame.add(singleLossesValue);

        settingFrame.add(multiPlayerWinsLabel);
        settingFrame.add(multiPlayerWinsValue);

        settingFrame.add(multiPlayerLossesLabel);
        settingFrame.add(multiPlayerLossesValue);

        settingFrame.add(tankModelLabel);
        settingFrame.add(tankModelValue);
        settingFrame.add(tankModelBackground);

        settingFrame.add(tankLifeLabel);
        settingFrame.add(tankLifeSlider);

        settingFrame.add(gunDamageLabel);
        settingFrame.add(gunDamageSlider);

        settingFrame.add(wallLifeLabel);
        settingFrame.add(wallLifeSlider);

        settingFrame.add(changeTankColor);

        settingFrame.add(settingLabel);
    }

    public void setVisible(boolean visible){
        settingFrame.setVisible(visible);
    }
}

class CustomSliderUI extends BasicSliderUI {

    private BasicStroke stroke = new BasicStroke(1f, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND, 0f, new float[]{1f, 2f}, 0f);

    public CustomSliderUI(JSlider b) {
        super(b);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g, c);
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(18, 16);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke old = g2d.getStroke();
        g2d.setStroke(stroke);
        g2d.setPaint(new Color(162,94,40));
        if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
            g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2,
                    trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
        } else {
            g2d.drawLine(trackRect.x + trackRect.width / 2, trackRect.y,
                    trackRect.x + trackRect.width / 2, trackRect.y + trackRect.height);
        }
        g2d.setStroke(old);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int x1 = thumbRect.x + 2;
        int x2 = thumbRect.x + thumbRect.width - 2;
        int width = thumbRect.width - 4;
        int topY = thumbRect.y + thumbRect.height / 2 - thumbRect.width / 3;
        GeneralPath shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        shape.moveTo(x1, topY);
        shape.lineTo(x2, topY);
        shape.lineTo((x1 + x2) / 2, topY + width);
        shape.closePath();
        g2d.setPaint(new Color(128,22,0));
        g2d.fill(shape);
        Stroke old = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2f));
        g2d.setPaint(new Color(162,94,40));
        g2d.draw(shape);
        g2d.setStroke(old);
    }
}

class chooseColorPanel extends JPanel{
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("resources/chooseColorBackground.png");
        g.drawImage(img.getImage(), 0, 0, 400, 100, null);
    }
}
