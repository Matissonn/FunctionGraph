package com.MatOs;

import javax.swing.*;
import java.awt.*;


public class Graph extends JPanel{
    static JFrame frame;
    double scale = 50.0;
    static int width = 500;
    static int height = 500;
    static boolean done = false;
    static String str;


    public Graph(){
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
    }

    public static void start() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        frame.setTitle ("График");
        frame.setVisible(true);
        frame.add(new Graph());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int height = this.getHeight();
        int width = this.getWidth();

        g.setColor(new Color(126, 126, 126));
        for(int i = 1; i < width / 2 / scale; i++){
            g.drawLine((int) (width / 2 + scale * i), 0, (int) (width / 2 + scale * i), height);
            g.drawLine((int) (width / 2 - scale * i), 0, (int) (width / 2 - scale * i), height);
            g.drawLine(0, (int) (height / 2 + scale * i), width, (int) (height / 2 + scale * i));
            g.drawLine(0, (int) (height / 2 - scale * i), width, (int) (height / 2 - scale * i));
        }

        g.setColor(Color.black);
        g.drawLine(0, height / 2, width, height / 2);
        g.drawLine(width / 2, 0, width / 2, height);

        double x, y;
        g.setColor(Color.red);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                x = (i - width / 2 - 1) / scale;
                y = (height / 2 - j - 1) / scale;

                double result = RPN.result(str, x);

                if (isCorrect(y, result, 0.02)) {
                    g.fillOval(i, j, 2, 2);
                }
            }
        }

        done = true;
    }

    boolean isCorrect(double x, double result, double error) {
        if (Math.abs(x - result) <= error)
            return true;
        return false;
    }
}