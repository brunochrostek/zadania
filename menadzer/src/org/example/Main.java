package org.example;

import javax.swing.*;
import java.awt.event.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            todo ui = new todo();

            JFrame frame = new JFrame("To-Do List");
            frame.setContentPane(ui.panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(500, 400);
            frame.setVisible(true);

            DefaultListModel<String> listModel = new DefaultListModel<>();
            ui.lista.setModel(listModel);

            ui.dodaj.addActionListener(e -> {
                String text = ui.zadanie.getText().trim();

                if (!text.isEmpty()) {
                    listModel.addElement(text);
                    ui.zadanie.setText("");
                    updateCounter(ui, listModel);
                } else {
                    JOptionPane.showMessageDialog(frame, "Pole nie może być puste");
                }
            });

            ui.usun.addActionListener(e -> {
                int index = ui.lista.getSelectedIndex();

                if (index >= 0) {
                    listModel.remove(index);
                    updateCounter(ui, listModel);
                } else {
                    JOptionPane.showMessageDialog(frame, "Wybierz element do usunięcia");
                }
            });

            ui.lista.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int index = ui.lista.locationToIndex(e.getPoint());
                        if (index >= 0) {
                            listModel.remove(index);
                            updateCounter(ui, listModel);
                        }
                    }
                }
            });

            updateCounter(ui, listModel);
        });
    }

    private static void updateCounter(todo ui, DefaultListModel<String> model) {
        ui.ilosc.setText("Zadań: " + model.getSize());
    }
}