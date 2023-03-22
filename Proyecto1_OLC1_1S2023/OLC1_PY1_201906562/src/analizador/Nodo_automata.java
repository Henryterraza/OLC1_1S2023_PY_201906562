/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizador;

import java.util.ArrayList;

/**
 *
 * @author henry
 */
public class Nodo_automata {

    private String dato;
    private Nodo_automata hijo_izq;
    private Nodo_automata hijo_der;
    private boolean hoja = false;
    private int identificador;
    private boolean anulable;
    private ArrayList<Integer> primeros = new ArrayList<>();
    private ArrayList<Integer> ultimos = new ArrayList<>();
    
    
    public Nodo_automata(String dato) {
        this.dato = dato;
    }

    public ArrayList<Integer> getUltimos() {
        return ultimos;
    }

    public void setUltimos(ArrayList<Integer> ultimos) {
        this.ultimos = ultimos;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public ArrayList<Integer> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(ArrayList<Integer> primeros) {
        this.primeros = primeros;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public String getDato() {
        return dato;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    

    public void setDato(String dato) {
        this.dato = dato;
    }
    
    

    public Nodo_automata getHijo_izq() {
        return hijo_izq;
    }

    public void setHijo_izq(Nodo_automata hijo_izq) {
        this.hijo_izq = hijo_izq;
    }

    public Nodo_automata getHijo_der() {
        return hijo_der;
    }

    public void setHijo_der(Nodo_automata hijo_der) {
        this.hijo_der = hijo_der;
    }

    public boolean isHoja() {
        return hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }
    
}
