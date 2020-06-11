/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1_compi1;

/**
 *
 * @author sohal
 */
public class Nivel {
    private int numero;
    private String noombre;
    private int x;
    private int y;
    private char[][] matriz;
    
    public Nivel(int numero, String nombre, int x, int y, char[][] matriz){
        this.noombre = nombre;
        this.numero = numero;
        this.x = x;
        this.y = y;
        this.matriz = new char[x][y];
        this.matriz = matriz;
    }
    
    public String getnombre() {
        return noombre;
    }
    
    public int getnumero() {
        return numero;
    }
    
    public int getx() {
        return x;
    }
    
    public int gety() {
        return y;
    }
    
    public char[][] getmatriz() {
        return matriz;
    }
}
