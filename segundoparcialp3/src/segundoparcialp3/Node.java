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
public class Node {

    public int id;
    public String name;
    public int value;
    public Node izq;
    public Node dere;

    Node(int id, String name, int value) {
        this.id = id;
        this.name = name;
        dere = null;
        izq = null;
        this.value = value;
    }
    
    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try
        {
            fichero = new FileWriter("aux_grafico.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } 
        catch (Exception e){
            System.err.println("Error al escribir el archivo aux_grafico.dot");
        }finally{
           try {
                if (null != fichero)
                    fichero.close();
           }catch (Exception e2){
               System.err.println("Error al cerrar el archivo aux_grafico.dot");
           } 
        }                        
        try{
          Runtime rt = Runtime.getRuntime();
          rt.exec( "dot -Tjpg -o "+path+" aux_grafico.dot");
          //Esperamos medio segundo para dar tiempo a que la imagen se genere.
          //Para que no sucedan errores en caso de que se decidan graficar varios
          //árboles sucesivamente.
          Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo aux_grafico.dot");
        }            
    }
    /**
     * Método que retorna el código que grapviz usará para generar la imagen 
     * del árbol binario de búsqueda.
     * @return 
     */
    private String getCodigoGraphviz() {
        return "digraph grafica{\n" +
               "rankdir=TB;\n" +
               "node [shape = record, style=filled, fillcolor=seashell2];\n"+
                getCodigoInterno()+
                "}\n";
    }
    /**
     * Genera el código interior de graphviz, este método tiene la particularidad 
     * de ser recursivo, esto porque recorrer un árbol de forma recursiva es bastante 
     * sencillo y reduce el código considerablemente. 
     * @return 
     */
    private String getCodigoInterno() {
        String etiqueta;
        if(izq==null && dere==null){
            etiqueta="nodo"+id+" [ label =\""+name+"\"];\n";
        }else{
            etiqueta="nodo"+id+" [ label =\"<C0>|"+name+"|<C1>\"];\n";
        }
        if(izq!=null){
            etiqueta=etiqueta + izq.getCodigoInterno() +
               "nodo"+id+":C0->nodo"+izq.id+"\n";
        }
        if(dere!=null){
            etiqueta=etiqueta + dere.getCodigoInterno() +
               "nodo"+id+":C1->nodo"+dere.id+"\n";                    
        }
        return etiqueta;
    }
    
    
}