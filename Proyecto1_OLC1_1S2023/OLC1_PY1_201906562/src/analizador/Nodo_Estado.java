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
public class Nodo_Estado {
    private String Estado;
    private ArrayList<Integer> List_Estados_Sig = new ArrayList<>();
    private ArrayList<String> List_Transiciones = new ArrayList<>();
    private boolean Aceptacion;
    private boolean Recorrido;

    public boolean isAceptacion() {
        return Aceptacion;
    }

    public void setAceptacion(boolean Aceptacion) {
        this.Aceptacion = Aceptacion;
    }

    public boolean isRecorrido() {
        return Recorrido;
    }

    public void setRecorrido(boolean Recorrido) {
        this.Recorrido = Recorrido;
    }
    

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public ArrayList<Integer> getList_Estados_Sig() {
        return List_Estados_Sig;
    }

    public void setList_Estados_Sig(ArrayList<Integer> List_Estados_Sig) {
        this.List_Estados_Sig = List_Estados_Sig;
    }

    public ArrayList<String> getList_Transiciones() {
        return List_Transiciones;
    }

    public void setList_Transiciones(ArrayList<String> List_Transiciones) {
        this.List_Transiciones = List_Transiciones;
    }
    
}
