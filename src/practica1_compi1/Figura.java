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

public class Figura {
    
    private int fin;
    private String nombre;
    private int rotacion;
    private int color;
    
    public Figura(String nombre, int rotacion, int f, int color){
        this.fin=f;
        this.nombre=nombre;
        this.rotacion=rotacion;
        this.color=color;
    }
    
    public String getnombre() {
        return nombre;
    }
    
    public int getrotacion() {
        return rotacion;
    }
    
    public int getfin() {
        return fin;
    }
    
    public int getcolor(){
        return color;
    }
}
