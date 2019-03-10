package sistema.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {

    JTextArea txtEditor, txtConsola;
    JButton btnAnalizar;

    public Ventana() {
        txtEditor = new JTextArea();
        txtConsola = new JTextArea();
        btnAnalizar = new JButton("Analizar");

        this.setSize(600,600);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JPanel panelEditor = new JPanel(new BorderLayout()), panelConsola = new JPanel(new BorderLayout());
        panelEditor.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panelConsola.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panelEditor.add(new JLabel("Editor"), BorderLayout.NORTH);
        txtEditor.setRows(20);
        JScrollPane scrollEditor = new JScrollPane(txtEditor);
        scrollEditor.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelEditor.add(scrollEditor, BorderLayout.CENTER);
        panelConsola.add(new JLabel("Consola"), BorderLayout.NORTH);
        txtConsola.setEditable(false);
        txtConsola.setRows(20);
        JScrollPane scrollConsola = new JScrollPane(txtConsola);
        scrollConsola.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelConsola.add(scrollConsola, BorderLayout.CENTER);
        this.add(panelEditor, BorderLayout.NORTH);
        this.add(panelConsola, BorderLayout.CENTER);
        this.add(btnAnalizar, BorderLayout.SOUTH);

        btnAnalizar.addActionListener((ActionEvent ae) -> {
            btnAnalizar(ae);
        });
    }

    private void btnAnalizar(ActionEvent ae) {

    }
}
