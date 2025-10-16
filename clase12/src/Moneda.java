import java.util.ArrayList;

public class Moneda {
    static ArrayList<Integer> resultado = new ArrayList<>();
    static int mejor = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[] monedas = {1, 2, 5 };
        int montoTotal = 6;
        ArrayList<Integer> comb_actual = new ArrayList<>();
        montoTotalConMenorCantidadMonedas(monedas, montoTotal, 0, comb_actual);
        
        System.out.println("Mejor combinación: " + resultado);
        System.out.println("Cantidad mínima de monedas: " + mejor);
    }

    private static void montoTotalConMenorCantidadMonedas(int[] monedas, int montoTotal, int monto_actual,
                                                          ArrayList<Integer> comb_actual) {
        // Caso base: exacto
        if (monto_actual == montoTotal) {
            if (comb_actual.size() < mejor) {
                mejor = comb_actual.size();
                resultado = new ArrayList<>(comb_actual);
            }
            return;
        }

        // Poda: supera el monto o ya usa más monedas que la mejor
        if (monto_actual > montoTotal || comb_actual.size() >= mejor) return;

        // Branch: probar todas las monedas
        for (int j = 0; j < monedas.length; j++) {
            comb_actual.add(monedas[j]);
            montoTotalConMenorCantidadMonedas(monedas, montoTotal, monto_actual + monedas[j], comb_actual);
            comb_actual.remove(comb_actual.size() - 1); // backtracking
        }
    }
}