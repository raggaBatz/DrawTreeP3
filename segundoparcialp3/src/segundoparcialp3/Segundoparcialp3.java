
package segundoparcialp3;

/**
 *
 * @author GAMING
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Segundoparcialp3 {
    
    
    private static int gananciatot = 0;
    private static int preventa = 0;
    private static int ventadisponible = 0;

    public static void calcgantanciatot(Node raiz) {
        
        if (raiz == null) {
            
            return;
        }
        if (!raiz.name.equals("A")) {
            
            gananciatot = gananciatot + 150*10;
        }
        if (raiz.izq == null && raiz.dere == null) {
            
            return;
        }
        
        calcgantanciatot(raiz.izq);
        
        if (raiz.dere != null) {
            
            calcgantanciatot(raiz.dere);
        }
    }

    public static void calcPreventa(Node raiz) {
        
        if (raiz == null) {
            
            return;
        }
        if (!raiz.name.equals("A")) {
            
            afectado = afectado + raiz.name + "   ";
            
            preventa = preventa + raiz.value*150;
        }
        if (raiz.izq == null && raiz.dere == null) {
            
            return;
        }
        calcPreventa(raiz.izq);
        if (raiz.dere != null) {
            
            calcPreventa(raiz.dere);
        }
    }
    
    private static String afectado = "";
    
    public static void nodoAfectado(String i, Node raiz) {
        
        if (raiz == null) {
            
            return;
        }
        if (raiz.name.equals(i)) {
            
            calcPreventa(raiz);
        }
        if (raiz.izq == null && raiz.dere == null) {
            
            return;
        }
        nodoAfectado(i, raiz.izq);
        if (raiz.dere != null) {
            
            nodoAfectado(i, raiz.dere);
        }
    }

    private static String resul = "";
    
    

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        
        tree arbol = new tree();
        arbol.nuevo(1, "A", 0);
        arbol.nuevo(2, "B", 7);
        arbol.nuevo(3, "C", 5);
        arbol.nuevo(4, "D", 3);
        arbol.nuevo(5, "E", 8);
        arbol.nuevo(6, "F", 9);
        arbol.nuevo(7, "G", 10);
        arbol.nuevo(8, "H", 2);
        arbol.nuevo(9, "I", 4);
        arbol.nuevo(10, "J", 0);
        arbol.nuevo(11, "K", 0);
        arbol.nuevo(12, "L", 10);
        arbol.nuevo(13, "M", 6);
        arbol.nuevo(14, "N", 10);
        arbol.nuevo(15, "O", 10);
        arbol.graficar("arbol_numeros.jpg");
        
        calcgantanciatot(arbol.raiz);
        calcPreventa(arbol.raiz);
        ventadisponible = gananciatot - preventa;
        System.out.println("GANACIAS DE PRODUCCION TOTAL:" + gananciatot);
        System.out.println();
        System.out.println("Casas Disponibles para venta " + ventadisponible/150);
        System.out.println();
        System.out.println("GANANCIAS EN PREVENTA: " + preventa);
        System.out.println();
        System.out.println("GANANCIAS DE CASAS POR VENDER" + ventadisponible);
        System.out.println();
        
        
        resul = "";
        afectado = "";
        preventa = 0;
        ventadisponible = 0;
        gananciatot = 0;
        
        ///////////////
        System.out.println("");
        System.out.println("\nPOR FAVOR INGRESE UN NODO PARA SIMULAR UNA FALLA");
        System.out.println();
        String nomb = leer.nextLine();
        String name = nomb.toUpperCase();
        nodoAfectado(name, arbol.raiz);
        System.out.println("Nodos afectado por la caida: " + afectado);
        System.out.println("Perdidas de ganacia: Q" + preventa);
        System.out.println("CASAS AFECTADAS POR LA CAIDA " + preventa/150);
    }

    
}