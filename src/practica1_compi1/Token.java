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
public class Token {

    private String tipo;
    private String lex;
    private int col;
    private int fil;

    public Token(String t, String l, int c, int f) {
        this.tipo = t;
        this.lex = l;
        this.col = c;
        this.fil = f;
    }

    public String gettipo() {
        return tipo;
    }

    public String getlex() {
        return lex;
    }

    public int getcol() {
        return col;
    }

    public int getfil() {
        return fil;
    }
}
