/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundoparcialp3;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author GAMING
 */
public class tree {
    
    public Node raiz;
    
    public void nuevo(int id, String name, int value) {
        this.raiz = agregarNode(this.raiz, id, name, value);
    }

    private int contarNodes(Node raiz) {
        int contador = 1;
        if (raiz.izq != null) {
            contador += contarNodes(raiz.izq);
        }
        if (raiz.dere != null) {
            contador += contarNodes(raiz.dere);
        }
        return contador;
    }

    private Node agregarNode(Node actual, int id, String name, int value) {
        if (actual == null) {
            //System.out.println("Nuevo - " + value);
            return new Node(id, name, value);
        }

        if (actual.izq == null && actual.dere == null) {
            actual.izq = agregarNode(actual.izq, id, name, value);
        } else if (actual.izq != null && actual.dere == null) {
            actual.dere = agregarNode(actual.dere, id, name, value);
        } else if (contarNodes(actual.izq) <= contarNodes(actual.dere)) {
            actual.izq = agregarNode(actual.izq, id, name, value);
        } else if (contarNodes(actual.izq) > contarNodes(actual.dere)) {
            actual.dere = agregarNode(actual.dere, id, name, value);
        }
        return actual;

    }

    public void graficar(String path) {
        raiz.graficar(path);
    }    
}
