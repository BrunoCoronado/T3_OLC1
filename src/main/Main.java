package main;

import sistema.bean.Token;
import sistema.ui.Ventana;

import java.util.ArrayList;

public class Main {
    public static ArrayList<Token> tokens;
    public static ArrayList<Token> errores;
    public static ArrayList<String> textos;

    public static  void  main(String args[]){
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
    }
}
