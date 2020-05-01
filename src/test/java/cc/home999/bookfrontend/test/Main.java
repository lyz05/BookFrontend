package cc.home999.bookfrontend.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author congcong
 */
import java.awt.GridLayout;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.text.NumberFormatter;

public class Main {

    public static void main(String[] args) throws Exception {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
        format.setParseIntegerOnly(true);
        format.setRoundingMode(RoundingMode.HALF_UP);

        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setMaximum(1000);
        formatter.setMinimum(0.0);
        formatter.setAllowsInvalid(false);
        // formatter.setOverwriteMode(false);

        JFormattedTextField tf = new JFormattedTextField(formatter);
        tf.setColumns(10);
        tf.setValue(123456789.99);
        JFormattedTextField tf1 = new JFormattedTextField(formatter);
        tf1.setValue(1234567890.99);
        JFormattedTextField tf2 = new JFormattedTextField(formatter);
        tf2.setValue(1111.1111);
        JFormattedTextField tf3 = new JFormattedTextField(formatter);
        tf3.setValue(-1111.1111);
        JFormattedTextField tf4 = new JFormattedTextField(formatter);
        tf4.setValue(-56);

        JFrame frame = new JFrame("Test");
        frame.setLayout(new GridLayout(5, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tf);
        frame.add(tf1);
        frame.add(tf2);
        frame.add(tf3);
        frame.add(tf4);
        frame.pack();
        frame.setVisible(true);
    }
}
