/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package errores;

/**
 *
 * @author henry
 */
public class excepcion {
    private String tipo;
    private String descripcion;
    private String fila;
    private String columna;

    public excepcion(String tipo, String descripcion, String fila, String columna) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fila = fila;
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFila() {
        return fila;
    }

    public String getColumna() {
        return columna;
    }
    
    
}
