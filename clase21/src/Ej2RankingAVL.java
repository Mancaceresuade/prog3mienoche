import java.util.ArrayList;
import java.util.List;

public class Ej2RankingAVL {

    public static class Jugador implements Comparable<Jugador> {
        String nombre;
        int puntaje;

        public Jugador(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }

        
        public int compareTo(Jugador otro) {
            return Integer.compare(this.puntaje, otro.puntaje);
        }

       
        public String toString() {
            return nombre + " (" + puntaje + ")";
        }
    }

    public static class Nodo {
        Jugador jugador;
        Nodo izq, der;

        public Nodo(Jugador jugador) {
            this.jugador = jugador;
        }
    }

    Nodo raiz;

    public void insertar(Jugador jugador) {
        raiz = insertarRec(raiz, jugador);
    }

    private Nodo insertarRec(Nodo nodo, Jugador jugador) {
        if (nodo == null) return new Nodo(jugador);

        if (jugador.compareTo(nodo.jugador) < 0) {
            nodo.izq = insertarRec(nodo.izq, jugador);
        } else if (jugador.compareTo(nodo.jugador) > 0) {
            nodo.der = insertarRec(nodo.der, jugador);
        } else {
            return nodo; // No duplicados
        }

        return balancear(nodo);
    }

    private int altura(Nodo nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }

    private Nodo rotarIzquierda(Nodo x) {
        Nodo y = x.der;
        x.der = y.izq;
        y.izq = x;
        return y;
    }

    private Nodo rotarDerecha(Nodo y) {
        Nodo x = y.izq;
        y.izq = x.der;
        x.der = y;
        return x;
    }

    private Nodo balancear(Nodo nodo) {
        int balance = altura(nodo.izq) - altura(nodo.der);
        if (balance > 1) {
            if (altura(nodo.izq.izq) >= altura(nodo.izq.der)) {
                nodo = rotarDerecha(nodo);
            } else {
                nodo.izq = rotarIzquierda(nodo.izq);
                nodo = rotarDerecha(nodo);
            }
        } else if (balance < -1) {
            if (altura(nodo.der.der) >= altura(nodo.der.izq)) {
                nodo = rotarIzquierda(nodo);
            } else {
                nodo.der = rotarDerecha(nodo.der);
                nodo = rotarIzquierda(nodo);
            }
        }
        return nodo;
    }

    // Método principal: obtener los k mejores jugadores en el rango
    public List<Jugador> topKEnRango(int p_min, int p_max, int k) {
        List<Jugador> resultado = new ArrayList<>();
        topKRec(raiz, p_min, p_max, k, resultado);
        return resultado;
    }

    // Recorrido optimizado en orden inverso (mayores primero)
    private void topKRec(Nodo nodo, int p_min, int p_max, int k, List<Jugador> resultado) {
        if (nodo == null || resultado.size() >= k) return;

        if (nodo.jugador.puntaje < p_min) {
            topKRec(nodo.der, p_min, p_max, k, resultado);
        } else if (nodo.jugador.puntaje > p_max) {
            topKRec(nodo.izq, p_min, p_max, k, resultado);
        } else {
            topKRec(nodo.der, p_min, p_max, k, resultado); // primero los mayores
            if (resultado.size() < k) resultado.add(nodo.jugador);
            topKRec(nodo.izq, p_min, p_max, k, resultado);
        }
    }

    // Ejemplo de uso
    public static void main(String[] args) {
        Ej2RankingAVL ranking = new Ej2RankingAVL();
        ranking.insertar(new Jugador("Ana", 1200));
        ranking.insertar(new Jugador("Luis", 1500));
        ranking.insertar(new Jugador("Sofía", 1800));
        ranking.insertar(new Jugador("Carlos", 1100));
        ranking.insertar(new Jugador("Marta", 1700));
        ranking.insertar(new Jugador("Tomás", 1600));

        int p_min = 1300;
        int p_max = 1800;
        int k = 3;

        List<Jugador> top = ranking.topKEnRango(p_min, p_max, k);
        System.out.println("Top " + k + " jugadores entre " + p_min + " y " + p_max + ":");
        for (Jugador j : top) {
            System.out.println(j);
        }
    }
}