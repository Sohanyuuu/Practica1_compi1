/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_compi1;

import java.util.ArrayList;

public class Analizador {

    private String cadena;
    private int filas;
    private int columnas;
    private char[][] pila;
    ArrayList<Figura> pieza = new ArrayList<Figura>();
    ArrayList<Nivel> niveles = new ArrayList<Nivel>();
    ArrayList<Token> tokens = new ArrayList<Token>();
    ArrayList<Error> errores = new ArrayList<Error>();
    ArrayList<Token> tokens2 = new ArrayList<Token>();
    ArrayList<Error> errores2 = new ArrayList<Error>();
    public Analizador(String cadena) {
        this.cadena = cadena;

    }

    public String getcadena() {
        return cadena;
    }

    public ArrayList<Figura> getpieza() {
        return pieza;
    }

    public ArrayList<Nivel> getniveles() {
        return niveles;
    }
    
    public ArrayList<Error> geterrores() {
        return errores;
    }
    
    public ArrayList<Error> geterrores2() {
        return errores2;
    }
    
     public ArrayList<Token> gettokens() {
        return tokens;
    }
     
     public ArrayList<Token> gettokens2() {
        return tokens2;
    }

    public int getfila() {
        return filas;
    }

    public int getcolumna() {
        return columnas;
    }

    public char[][] getpila() {
        return pila;
    }

    public void setcadena(String cadena) {
        this.cadena = cadena;
    }

    public void analizar() {

        System.out.println("esta analizando:   " + cadena);

        char[] caracteres = cadena.toCharArray();
        int estado = 0;
        int contadorpila = 0;
        int columna = 0;
        int nivel = 0;
        int fila = 0;
        String numero = "";
        String nombre = "";
        String comentariod="";
        String comentariou="";
        
        for (int i = 0; i < caracteres.length; i++) {
            switch (estado) {
                case 0:
                    if (Character.isLetter(caracteres[i])) {
                        System.out.println("es letra");
                        nombre += String.valueOf(caracteres[i]);
                        estado = 1;
                        columna++;
                    } else if (Character.isDigit(caracteres[i])) {
                        System.out.println("es numero");
                        numero += caracteres[i];
                        estado = 3;
                        columna++;
                    } else if (caracteres[i] == 35) {
                        System.out.println("numeral");
                        estado = 2;
                        contadorpila++;
                        pila[contadorpila - 1][columna] = caracteres[i];
                        columna++;
                    } else if (caracteres[i] == 42) {
                        System.out.println("asterisco");
                        estado = 2;
                        contadorpila++;
                        pila[contadorpila - 1][columna] = caracteres[i];
                        columna++;
                    }else if (caracteres[i] == 47) {
                        estado = 9;
                        columna++;
                    }else if (caracteres[i] == 60) {
                        estado = 5;
                        columna++;
                    }
                    else if (caracteres[i] == 10) {
                        estado = 0;
                        fila++;
                        columna=0;
                    }else if (caracteres[i] == 32) {
                        estado = 0;
                        columna++;
                    }else {
                        estado = 0;
                        columna++;
                        errores.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 1:
                    if (Character.isLetter(caracteres[i])) {
                        System.out.println("es letra");
                        nombre += String.valueOf(caracteres[i]);
                        estado = 1;
                        columna++;
                    } else if (caracteres[i] == 32) {
                        System.out.println("blanco");
                        nombre += String.valueOf(caracteres[i]);
                        estado = 1;
                        columna++;
                        tokens.add(new Token("Nombre nivel", nombre,columna, fila));
                    } else if (caracteres[i] == 10) {
                        System.out.println("salto");
                        estado = 0;
                        numero = "";
                        columna = 0;
                        fila++;
                    } else if (caracteres[i] == 95) {
                        System.out.println("guion bajo");
                        estado = 1;
                        columna++;
                    } else {
                        estado = 0;
                        columna++;
                        errores.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 2:
                    if (caracteres[i] == 42) {
                        System.out.println("es asterisco");
                        estado = 2;
                        pila[contadorpila - 1][columna] = caracteres[i];
                        columna++;
                    } else if (caracteres[i] == 10) {
                        System.out.println("salto");
                        estado = 6;
                        columna = 0;
                        contadorpila++;
                        fila++;
                    } else if (caracteres[i] == 35) {
                        System.out.println("numeral");
                        estado = 2;
                        pila[contadorpila - 1][columna] = caracteres[i];
                        columna++;
                    } else if (caracteres[i] == 32) {
                        System.out.println("espacio");
                        estado = 2;
                        columna++; 
                    } else {
                        errores.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 3:
                    if (Character.isDigit(caracteres[i])) {
                        System.out.println("es numero");
                        numero += caracteres[i];
                        estado = 3;
                    } else if (caracteres[i] == 120) {
                        System.out.println("por");
                        nombre = "";
                        filas = Integer.parseInt(numero);
                        estado = 4;
                        tokens.add(new Token("Numero", numero,columna, fila));
                        columna++;
                        columna++;
                        tokens.add(new Token("Guion", "-",columna, fila));
                        numero = "";
                    } else if (caracteres[i] == 10) {
                        System.out.println("salto");
                        estado = 0;
                        nivel = Integer.parseInt(numero);
                        tokens.add(new Token("Numero", numero,columna, fila));
                        columna = 0;
                        fila++;
                        numero = "";
                    } else {
                        estado = 0;
                        numero = "";
                        columna++;
                        errores.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 4:
                    if (Character.isDigit(caracteres[i])) {
                        System.out.println("es numero");
                        numero += caracteres[i];
                        estado = 4;
                        
                    } else if (caracteres[i] == 32) {
                        System.out.println("salto");
                        estado = 0;
                        columnas = Integer.parseInt(numero);
                        pila = new char[filas][columnas];
                        contadorpila = 0;
                        columna++;
                        tokens.add(new Token("Numero", numero,columna, fila));
                        numero = "";
                    }else{
                        errores.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 5:
                    if (caracteres[i] == 33) {
                        estado = 7;
                        columna++;
                    }else{
                        errores.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 6:
                    if (caracteres[i] == 35) {
                        System.out.println("numeral");
                        estado = 2;
                        pila[contadorpila - 1][columna] = caracteres[i];
                        columna++;
                    } else if (caracteres[i] == 42) {
                        System.out.println("asterisco");
                        estado = 2;
                        pila[contadorpila - 1][columna] = caracteres[i];
                        columna++;
                    } else if(caracteres[i] == 10){
                        fila++;
                        niveles.add(new Nivel(nivel, nombre, filas, columnas, pila));
                        tokens.add(new Token("Matriz nivel", " ",1, fila));
                        System.out.println(nombre + "nombre nivel" + nivel);
                        columna = 0;
                        estado = 0;
                        
                    }else{
                        errores.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 7:
                    if(caracteres[i] == 33){
                        estado = 8;
                        columna++;
                    }else if(caracteres[i] == 10){
                        fila++; 
                        columna = 0;
                    }else{
                        comentariod+=String.valueOf(caracteres[i]);
                        estado= 7;
                        columna++;
                    }
                    break;
                case 8:
                    if(caracteres[i] == 62){
                        tokens.add(new Token("Comentario multilinea", comentariod,columna, fila));
                        estado = 0;
                    }else{
                        errores.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 9:
                    if (caracteres[i] == 47) {
                        estado = 10;
                    }else{
                        errores.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 10:
                    if (caracteres[i] == 10) {
                        estado = 0;
                        tokens.add(new Token("Comentario simple", comentariou,columna, fila));
                        fila++;
                    }else{
                        comentariou+=String.valueOf(caracteres[i]);
                    }
                    break;

            }

        }

        System.out.println(columnas + "  " + filas);

    }

    public void analizar2() {
        System.out.println("esta analizando:   " + cadena);

        char[] caracteres = cadena.toCharArray();
        int estado = 0;
        int rot = 0;
        int columna = 0;
        int fila = 0;
        String letra = "";
        for (int i = 0; i < caracteres.length; i++) {
            switch (estado) {
                case 0:
                    if (caracteres[i] == 73) {
                        letra = String.valueOf(caracteres[i]);
                        estado = 1;
                        columna++;
                        tokens.add(new Token("Identificador pieza", letra,columna, fila));
                    } else if (caracteres[i] == 74) {
                        letra = String.valueOf(caracteres[i]);
                        estado = 1;
                        columna++;
                        tokens2.add(new Token("Identificador pieza", letra,columna, fila));
                    } else if (caracteres[i] == 76) {
                        letra = String.valueOf(caracteres[i]);
                        estado = 1;
                        columna++;
                        tokens2.add(new Token("Identificador pieza", letra,columna, fila));
                    } else if (caracteres[i] == 79) {
                        letra = String.valueOf(caracteres[i]);
                        estado = 1;
                        columna++;
                        tokens2.add(new Token("Identificador pieza", letra,columna, fila));
                    } else if (caracteres[i] == 83) {
                        letra = String.valueOf(caracteres[i]);
                        estado = 1;
                        columna++;
                        tokens2.add(new Token("Identificador pieza", letra,columna, fila));
                    }else if (caracteres[i] == 84) {
                        letra = String.valueOf(caracteres[i]);
                        estado = 1;
                        columna++;
                        tokens2.add(new Token("Identificador pieza", letra,columna, fila));
                    } else if (caracteres[i] == 90) {
                        letra = String.valueOf(caracteres[i]);
                        estado = 1;
                        columna++;
                        tokens2.add(new Token("Identificador pieza", letra,columna, fila)); 
                    } else {
                        errores2.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 1:
                    if (caracteres[i] == 32) {
                        estado = 1;
                        columna++;
                    } else if (caracteres[i] == 44) {
                        estado = 2;
                        columna++;
                        tokens2.add(new Token("Separador (coma)", ",",columna, fila));
                    } else {
                        errores2.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;
                case 2:
                    if (caracteres[i] == 118) {
                        System.out.println(rot);
                        rot = 2;
                        estado = 2;
                        tokens2.add(new Token("Identificador rotacion", String.valueOf(caracteres[i]),columna, fila));
                    } else if (caracteres[i] == 60) {
                        rot = 3;
                        estado = 2;
                        tokens2.add(new Token("Identificador rotacion", String.valueOf(caracteres[i]),columna, fila));
                    } else if (caracteres[i] == 62) {
                        rot = 4;
                        estado = 2;
                        tokens2.add(new Token("Identificador rotacion", String.valueOf(caracteres[i]),columna, fila));
                    } else if (caracteres[i] == 94) {
                        rot = 1;
                        estado = 2;
                        tokens2.add(new Token("Identificador rotacion", String.valueOf(caracteres[i]),columna, fila));
                    } else if (caracteres[i] == 10) {
                        int color = 0;
                        pieza.add(new Figura(letra, rot, 0,0));
                        rot = 0;
                        estado = 0;
                        fila++;
                    } else if (caracteres[i] == 32) {
                        estado = 2;
                    } else {
                        errores2.add(new Error(String.valueOf(caracteres[i]),columna, fila));
                    }
                    break;

            }

        }
    }

}
