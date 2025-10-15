import java.util.ArrayList;

public class Mochila {
    static int maximo = 0;
    static ArrayList<ObjetoMochila> resultado = new ArrayList<>();
    public static void main(String[] args) {
        ArrayList<ObjetoMochila> objetos = new ArrayList<>();
        cargar(objetos);
        ArrayList<ObjetoMochila> com_actual = new ArrayList<>();
        int capacidad = 10;
        combinar(objetos, com_actual, capacidad, 0);        
        System.out.println(resultado);
        System.out.println(resultado.stream().mapToInt(n -> n.valor).sum());
    }

    private static void combinar(ArrayList<ObjetoMochila> objetos, 
    ArrayList<ObjetoMochila> com_actual, int capacidad, int i) {
        int peso_actual = com_actual.stream().mapToInt(n -> n.peso).sum();
        int valor_actual = com_actual.stream().mapToInt(n -> n.valor).sum();
        // poda
        if (peso_actual > capacidad) return;
        // caso base
        if (i == objetos.size()) {
            if (valor_actual > maximo) {
                maximo = valor_actual;
                resultado = new ArrayList<>(com_actual);
            }
            return;
        }
        // cota
        int cota = calcularCota(objetos, com_actual, capacidad, i);
        if (cota <= maximo) return;

        // ramificacion
        com_actual.add(objetos.get(i));
        combinar(objetos, com_actual, capacidad, i + 1);
        com_actual.remove(com_actual.size() - 1); // backtracking
        combinar(objetos, com_actual, capacidad, i + 1);
    }

    // Cota: valor actual + suma de valores de los objetos restantes que entran en la capacidad
    private static int calcularCota(ArrayList<ObjetoMochila> objetos, ArrayList<ObjetoMochila> com_actual, int capacidad, int i) {
        int peso_actual = com_actual.stream().mapToInt(n -> n.peso).sum();
        int valor_actual = com_actual.stream().mapToInt(n -> n.valor).sum();
        int peso_restante = capacidad - peso_actual;
        int cota = valor_actual;
        // Agrega los objetos restantes mientras haya capacidad
        for (int j = i; j < objetos.size(); j++) {
            if (objetos.get(j).peso <= peso_restante) {
                peso_restante -= objetos.get(j).peso;
                cota += objetos.get(j).valor;
            }
        }
        return cota;
    }

    private static void cargar(ArrayList<ObjetoMochila> objetos) {
        objetos.add(new ObjetoMochila("A", 40, 2));
        objetos.add(new ObjetoMochila("B", 30, 5));
        objetos.add(new ObjetoMochila("C", 50, 10));
    }
}

class ObjetoMochila {
    String desc;
    int valor;
    int peso;
    
    public ObjetoMochila(String desc, int valor, int peso) {
        this.desc = desc;
        this.valor = valor;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "ObjetoMochila [desc=" + desc + ", valor=" + valor + ", peso=" + peso + "]";
    }
}