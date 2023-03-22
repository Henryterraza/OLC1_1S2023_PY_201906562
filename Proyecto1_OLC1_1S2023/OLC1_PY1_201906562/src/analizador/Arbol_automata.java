/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizador;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.xml.transform.Source;

/**
 *
 * @author henry
 */
public class Arbol_automata {

    private Nodo_automata arbol_exp;
    private String nombre_expresion;
    private int num_nodo = 1;
    private int num_estado = 0;
    public ArrayList<Tabla_Sig> Tablas_sig = new ArrayList<>();
    public ArrayList<String> Terminales = new ArrayList<>();
    public ArrayList<Nodo_Estado> Tabla_Transiciones = new ArrayList<>();

    public Arbol_automata(Nodo_automata arbol_exp, String nombre) {
        Nodo_automata raiz = new Nodo_automata(".");
        Nodo_automata aceptacion = new Nodo_automata("#");
        aceptacion.setHoja(true);
        aceptacion.setAnulable(false);
        raiz.setHijo_der(aceptacion);
        raiz.setHijo_izq(arbol_exp);

        this.arbol_exp = raiz;
        this.nombre_expresion = nombre;

        asignar_identificador(this.arbol_exp);
        construccion_arbol(this.arbol_exp);
        tabla(this.arbol_exp, num_nodo);
        GraficarGrap_GenerarDot(crear_Tabla_sig(), "SIGUIENTES_201906562\\", "ExpReg-" + this.nombre_expresion);
        Crear_Terminales();
        Crear_Transiciones();
        GraficarGrap_GenerarDot(Crear_Tabla_Transicion(), "TRANSICIONES_201906562\\", "ExpReg-" + this.nombre_expresion);
        
        GraficarGrap_GenerarDot(Crear_AFD(),"AFD_201906562\\", "ExpReg-" + this.nombre_expresion);
        num_nodo = 0;
        GraficarGrap_GenerarDot("digraph test {\n" + crear_arbol(this.arbol_exp, num_nodo) + "}", "ARBOLES_201906562\\", "ExpReg-" + this.nombre_expresion);

    }

    public void asignar_identificador(Nodo_automata actual) {

        if (actual == null) {
            return;
        }

        if (actual.isHoja()) {
            actual.setIdentificador(num_nodo);
            num_nodo++;
            return;
        }

        asignar_identificador(actual.getHijo_izq());
        asignar_identificador(actual.getHijo_der());
    }

    public void construccion_arbol(Nodo_automata actual) {

        if (actual == null) {
            return;
        }

        if (actual.isHoja()) {
            actual.getPrimeros().add(actual.getIdentificador());
            actual.getUltimos().add(actual.getIdentificador());
            return;

        }

        construccion_arbol(actual.getHijo_izq());
        construccion_arbol(actual.getHijo_der());

        if (actual.getDato().equals("*")) {
            actual.setAnulable(true);
            actual.getPrimeros().addAll(actual.getHijo_izq().getPrimeros());
            actual.getUltimos().addAll(actual.getHijo_izq().getUltimos());
        } else if (actual.getDato().equals("+")) {
            actual.setAnulable(actual.getHijo_izq().isAnulable());
            actual.getPrimeros().addAll(actual.getHijo_izq().getPrimeros());
            actual.getUltimos().addAll(actual.getHijo_izq().getUltimos());
        } else if (actual.getDato().equals("?")) {
            actual.setAnulable(true);
            actual.getPrimeros().addAll(actual.getHijo_izq().getPrimeros());
            actual.getUltimos().addAll(actual.getHijo_izq().getUltimos());
        } else if (actual.getDato().equals("|")) {
            actual.setAnulable(actual.getHijo_izq().isAnulable() || actual.getHijo_der().isAnulable());
            actual.getPrimeros().addAll(actual.getHijo_izq().getPrimeros());
            actual.getPrimeros().addAll(actual.getHijo_der().getPrimeros());
            actual.getUltimos().addAll(actual.getHijo_izq().getUltimos());
            actual.getUltimos().addAll(actual.getHijo_der().getUltimos());
        } else if (actual.getDato().equals(".")) {
            actual.setAnulable(actual.getHijo_izq().isAnulable() && actual.getHijo_der().isAnulable());
            if (actual.getHijo_izq().isAnulable()) {
                actual.getPrimeros().addAll(actual.getHijo_izq().getPrimeros());
                actual.getPrimeros().addAll(actual.getHijo_der().getPrimeros());
            } else {
                actual.getPrimeros().addAll(actual.getHijo_izq().getPrimeros());
            }

            if (actual.getHijo_der().isAnulable()) {
                actual.getUltimos().addAll(actual.getHijo_izq().getUltimos());
                actual.getUltimos().addAll(actual.getHijo_der().getUltimos());
            } else {
                actual.getUltimos().addAll(actual.getHijo_der().getUltimos());
            }

        }

    }

    public String crear_arbol(Nodo_automata actual, int padre) {
        String content = "";
        num_nodo += 1;

        int number = num_nodo;

        if (actual == null) {
            num_nodo -= 1;
            return content;
        }

        if (actual.isHoja()) {
            content += "N_" + number + "[shape = none label=<\n"
                    + " <TABLE border=\"1\" cellspacing=\"2\" cellpadding=\"10\" >\n"
                    + " <TR>\n"
                    + " <TD colspan=\"3\">" + actual.isAnulable() + "</TD>\n"
                    + " </TR>\n"
                    + " <TR>\n"
                    + " <TD>" + actual.getPrimeros() + "</TD>\n"
                    + " <TD>" + actual.getDato() + "</TD>\n"
                    + " <TD>" + actual.getUltimos() + "</TD>\n"
                    + " </TR>\n"
                    + " <TR>\n"
                    + " <TD colspan=\"3\">" + actual.getIdentificador() + "</TD>\n"
                    + " </TR>\n"
                    + " </TABLE>>];";
        } else {
            content += "N_" + number + "[shape = none label=<\n"
                    + " <TABLE border=\"1\" cellspacing=\"2\" cellpadding=\"10\" >\n"
                    + " <TR>\n"
                    + " <TD colspan=\"3\">" + actual.isAnulable() + "</TD>\n"
                    + " </TR>\n"
                    + " <TR>\n"
                    + " <TD>" + actual.getPrimeros() + "</TD>\n"
                    + " <TD>" + actual.getDato() + "</TD>\n"
                    + " <TD>" + actual.getUltimos() + "</TD>\n"
                    + " </TR>\n"
                    + " </TABLE>>];";
        }

        if (padre != 0) {
            content += "N_" + padre + " -> N_" + number + ";\n";
        }

        content += crear_arbol(actual.getHijo_izq(), number);
        content += crear_arbol(actual.getHijo_der(), number);

        return content;
    }

    public void tabla(Nodo_automata actual, int Nodo_Ult) {
        for (int c = 1; c < Nodo_Ult; c++) {
            Tabla_Sig fila = new Tabla_Sig();
            recorrer_arbol(actual, fila, c);
            Tablas_sig.add(fila);
        }
    }

    public String crear_Tabla_sig() {
        String content = "digraph G {\n"
                + "label     = \"TABLA DE SIGUIENTES\"\n"
                + "labelloc  =  t // t: Place the graph's title on top.\n"
                + "fontsize  = 30 // Make title stand out by giving a large font size \n"
                + "fontcolor = blue\n"
                + "N_1[shape = none label=<\n"
                + "<TABLE border=\"1\" cellspacing=\"2\" cellpadding=\"10\" >\n"
                + "<TR>\n"
                + "<TD colspan=\"2\" width=\"200\">Hoja</TD>\n"
                + "<TD colspan=\"1\" width=\"150\">Siguintes</TD>\n"
                + "</TR>\n";

        for (int i = 0; i < Tablas_sig.size(); i++) {
            content += "<TR>\n"
                    + " <TD>" + Tablas_sig.get(i).getDato() + "</TD>\n"
                    + " <TD>" + Tablas_sig.get(i).getNodo() + "</TD>\n"
                    + " <TD>" + Tablas_sig.get(i).getSiguientes() + "</TD>\n"
                    + " </TR>\n";
        }

        content += "</TABLE>>]\n"
                + "}";

        return content;

    }

    public void Crear_Terminales() {
        for (int i = 0; i < Tablas_sig.size(); i++) {
            if (Terminales.isEmpty()) {
                Terminales.add(Tablas_sig.get(0).getDato());
            } else {
                if (!Terminales.contains(Tablas_sig.get(i).getDato()) && !Tablas_sig.get(i).getDato().equals("#")) {
                    Terminales.add(Tablas_sig.get(i).getDato());
                }
            }

        }

    }

    public void Crear_Transiciones() {
        if (Tabla_Transiciones.isEmpty()) {
            Nodo_Estado Nuevo = new Nodo_Estado();
            Nuevo.setEstado("S" + num_estado);
            Nuevo.getList_Estados_Sig().addAll(arbol_exp.getPrimeros());
            Tabla_Transiciones.add(Nuevo);
            num_estado += 1;
        }
        boolean inicio = true;
        int cont = 30;
        while (inicio) {
            for (int i = 0; i < Tabla_Transiciones.size(); i++) {
                if (!Tabla_Transiciones.get(i).isRecorrido()) {

                    Verificar_Letras(Tabla_Transiciones.get(i));

                    Tabla_Transiciones.get(i).setRecorrido(true);

                }

            }
            if (cont == 30) {
                inicio = false;
            }
            cont++;
        }
    }

    public String Crear_Tabla_Transicion() {
        String content = "digraph test {\n"
                + "label     = \"TABLA DE TRANSICIONES\"\n"
                + "labelloc  =  t // t: Place the graph's title on top.\n"
                + "fontsize  = 20 // Make title stand out by giving a large font size \n"
                + "fontcolor = blue\n"
                + "graph [ratio=fill];\n"
                + "node [label=\"\\N\", fontsize=15, shape=plaintext];\n"
                + "graph [bb=\"0,0,352,154\"];\n"
                + "N_1[shape =plaintext label=<\n"
                + "\n"
                + "<TABLE border=\"0\" cellborder=\"1\" cellspacing=\"0\" >\n"
                + "<TR>\n"
                + "<TD width= \"150\" >Estado</TD>\n"
                + "<TD width= \"150\" >\n"
                + "<TABLE border=\"0\" cellpadding=\"10\" cellborder=\"1\" cellspacing=\"0\" >\n"
                + "<TR>\n"
                + "<TD colspan=\"" + Terminales.size() + "\">Terminales</TD>\n"
                + "</TR>\n"
                + "<TR>\n";

        for (int i = 0; i < Terminales.size(); i++) {
            content += " <TD width=\"100\">" + Terminales.get(i) + "</TD>\n";
        }
        content += "</TR>\n"
                + "</TABLE>\n"
                + "</TD>\n"
                + "</TR>";

        for (int i = 0; i < Tabla_Transiciones.size(); i++) {
            content += "<TR>\n"
                    + "<TD>" + Tabla_Transiciones.get(i).getEstado() + " " + Tabla_Transiciones.get(i).getList_Estados_Sig() + "</TD>\n"
                    + "<TD>\n"
                    + "<TABLE border=\"0\" cellpadding=\"10\" cellborder=\"1\" cellspacing=\"0\" >\n"
                    + "<TR>\n";

            for (int j = 0; j < Tabla_Transiciones.get(i).getList_Transiciones().size(); j++) {
                content += "<TD width=\"100\">" + Tabla_Transiciones.get(i).getList_Transiciones().get(j) + "</TD>\n";
            }

            content += "</TR>\n"
                    + "</TABLE>\n"
                    + "</TD>\n"
                    + "</TR>";

        }

        content += "</TABLE>>]\n"
                + "}";

        return content;

    }

    public String Crear_AFD() {
        String content = "digraph finite_state_machine {\n"
                + "fontname=\"Helvetica,Arial,sans-serif\"\n"
                + "node [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "edge [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "rankdir=LR;\n"
                + "nodesep=0.5;\n"
                + "node [shape = circle width = \"1.2\"]\n"
                + "S[shape=plaintext, label= \"\"]\n";

        for (int i = 0; i < Tabla_Transiciones.size(); i++) {

            if (Tabla_Transiciones.get(i).isAceptacion()) {
                content += Tabla_Transiciones.get(i).getEstado() + "[shape = doublecircle]\n";

            } else {
                content += Tabla_Transiciones.get(i).getEstado() + "\n";
            }
        }
        
        content += "S -> S0 [label = \"Inicio\"];\n";

        for (int i = 0; i < Tabla_Transiciones.size(); i++) {
            for (int j = 0; j < Terminales.size(); j++) {

                if (!Tabla_Transiciones.get(i).getList_Transiciones().get(j).equals("--")) {
                    content += Tabla_Transiciones.get(i).getEstado() + " -> " + Tabla_Transiciones.get(i).getList_Transiciones().get(j);
                    String text = Terminales.get(j).replace("\"", "");
                    text = text.replace("\\", "\\" + "\\");
                    content += " [label = \"" + text + "\"];\n";

                }

            }

        }

        content += "}";

        return content;
    }

    public void Verificar_Letras(Nodo_Estado Estado_actual) {
        for (int i = 0; i < Terminales.size(); i++) {

            ArrayList<Integer> Union = new ArrayList<>();
            boolean Letra_encontrada = false;
            for (int j = 0; j < Estado_actual.getList_Estados_Sig().size(); j++) {
                for (int k = 0; k < Tablas_sig.size(); k++) {
                    if (Estado_actual.getList_Estados_Sig().get(j) == Tablas_sig.get(k).getNodo()) {
                        if (Terminales.get(i).equals(Tablas_sig.get(k).getDato())) {
                            Union.addAll(Tablas_sig.get(k).getSiguientes());
                            Letra_encontrada = true;
                        }
                        break;
                    }

                }

            }
            if (Letra_encontrada) {
                boolean encontro = false;
                for (int j = 0; j < Tabla_Transiciones.size(); j++) {
                    if (Tabla_Transiciones.get(j).getList_Estados_Sig().size() == Union.size()) {
                        if (Tabla_Transiciones.get(j).getList_Estados_Sig().containsAll(Union)) {
                            encontro = true;
                            Estado_actual.getList_Transiciones().add(Tabla_Transiciones.get(j).getEstado());
                        }
                    }

                }

                if (!encontro) {
                    Nodo_Estado Nuevo = new Nodo_Estado();
                    Nuevo.setEstado("S" + num_estado);
                    Nuevo.getList_Estados_Sig().addAll(Union);

                    if (Union.contains(Tablas_sig.size())) {
                        Nuevo.setAceptacion(true);
                    }
                    Tabla_Transiciones.add(Nuevo);
                    Estado_actual.getList_Transiciones().add("S" + num_estado);
                    num_estado += 1;
                }
            } else {
                Estado_actual.getList_Transiciones().add("--");
            }
        }

    }

    public void recorrer_arbol(Nodo_automata actual, Tabla_Sig fila, int Num_bus) {

        if (actual == null) {
            return;
        }

        if (actual.isHoja()) {
            if (actual.getIdentificador() == Num_bus) {
                fila.setDato(actual.getDato());
                fila.setNodo(Num_bus);
            }
            return;
        }

        recorrer_arbol(actual.getHijo_izq(), fila, Num_bus);
        recorrer_arbol(actual.getHijo_der(), fila, Num_bus);

        if (actual.getDato().equals("*")) {
            if (actual.getHijo_izq().getUltimos().contains(Num_bus)) {
                fila.getSiguientes().addAll(actual.getHijo_izq().getPrimeros());
            }
        } else if (actual.getDato().equals("+")) {
            if (actual.getHijo_izq().getUltimos().contains(Num_bus)) {
                fila.getSiguientes().addAll(actual.getHijo_izq().getPrimeros());
            }
        } else if (actual.getDato().equals(".")) {
            if (actual.getHijo_izq().getUltimos().contains(Num_bus)) {
                fila.getSiguientes().addAll(actual.getHijo_der().getPrimeros());
            }

        }
    }

    private void GraficarGrap_GenerarDot(String cadena, String carpeta, String nombre) {
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try {
            fichero = new FileWriter(carpeta + nombre + ".dot");
            escritor = new PrintWriter(fichero);
            escritor.println(cadena);
            escritor.close();
            fichero.close();
            reportar(carpeta, nombre);
        } catch (Exception e) {
            System.out.println("error en generar dot");
            e.printStackTrace();
        }
    }

    public void reportar(String carpeta, String nombre) throws IOException {

        String file_input_path = carpeta + nombre + ".dot";
        String do_path = "C:\\Program Files\\Graphviz\\bin\\dot.exe";

        String file_get_path = carpeta + nombre + ".jpg";
        try {
            ProcessBuilder pBuilder;
            pBuilder = new ProcessBuilder(do_path, "-Tjpg", "-o", file_get_path, file_input_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
