/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Identi_Lexema;

/**
 *
 * @author henry
 */
public class Ident_Lexema {
    private String Valor;
    private String Exp_Regular;
    private String Resultado;

    public Ident_Lexema(String Valor, String Exp_Regular) {
        this.Valor = Valor;
        this.Exp_Regular = Exp_Regular;
    }

    public String getResultado() {
        return Resultado;
    }

    public void setResultado(String Resultado) {
        this.Resultado = Resultado;
    }

    public String getValor() {
        return Valor;
    }

    public String getExp_Regular() {
        return Exp_Regular;
    }
    
    
    
}
