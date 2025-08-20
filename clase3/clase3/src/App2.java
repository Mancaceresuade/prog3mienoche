public class App2 {
    public static void main(String[] args) {
        // ejercicio para matriz cuadrada, calcular con recursividad
        int[][] matriz = {{3,4,1},{12,1,3},{1,6,8}};
        todasLasColumnasConUnMultiploDe(matriz,4); // true
        int[][] matriz2 = {{3,4,1},{12,1,3},{1,6,9}};
        todasLasColumnasConUnMultiploDe(matriz,4); // false
    }

}
