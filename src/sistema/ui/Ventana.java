package sistema.ui;

import main.Main;
import sistema.analizador.Parser;
import sistema.analizador.Scanner;
import sistema.bean.Token;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.util.ArrayList;

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
        try {
            limpiarContenidoGlobal();
            txtConsola.setText("");
            StringReader strReader = new StringReader(limpiarTexto(txtEditor.getText())+"$");
            Scanner scanner = new Scanner(strReader);
            Parser parser = new Parser(scanner);
            parser.parse();
            if(Main.errores.size() == 0){
                for (String contenido: Main.textos) {
                    txtConsola.append(contenido);
                }
            }else{
                for (Token error: Main.errores) {
                    txtConsola.append(error.getLexema() + " | " + error.getTipo() + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al analizar la entrada");
            e.printStackTrace();
        }
    }

    private void limpiarContenidoGlobal(){
        Main.textos = new ArrayList<>();
        Main.tokens = new ArrayList<>();
        Main.errores = new ArrayList<>();
    }

    private String limpiarTexto(String texto){
        if(texto.contains("“"))
            texto = texto.replace('“', '\"');
        if(texto.contains("”"))
            texto = texto.replace('”', '\"');
        return texto;
    }
}
