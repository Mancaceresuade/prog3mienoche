public class App2 {
    public static void main(String[] args) {
        // ejercicio para matriz cuadrada, calcular con recursividad
        int[][] matriz = {{3,4,1},{12,1,3},{1,6,8}};
        todasLasColumnasConUnMultiploDe(matriz,4); // true
        int[][] matriz2 = {{3,4,1},{12,1,3},{1,6,9}};
        todasLasColumnasConUnMultiploDe(matriz,4); // false
    }

    private static boolean todasLasColumnasConUnMultiploDe(int[][] matriz, int numero) {
        return todasLasColumnasConMultipoDeRec(matriz, numero, 0);
    }

    // Revisa cada columna de la matriz
    private static boolean todasLasColumnasConMultipoDeRec(int[][] matriz, int numero, int columna) {
        if (columna == matriz[0].length) return true; // revisamos todas las columnas
        if (!columnaConMultipoDeRec(matriz, numero, matriz.length - 1, columna)) {
            return false; // si alguna columna no tiene múltiplo → false
        }
        return todasLasColumnasConMultipoDeRec(matriz, numero, columna + 1);
    }

    // Revisa si una columna tiene al menos un múltiplo
    private static boolean columnaConMultipoDeRec(int[][] matriz, int numero, int fila, int columna) {
        if (fila < 0) return false; // no encontró múltiplo en esa columna
        if (matriz[fila][columna] % numero == 0) return true; // encontró múltiplo
        return columnaConMultipoDeRec(matriz, numero, fila - 1, columna);
    }    

}
