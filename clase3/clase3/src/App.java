public class App {
    public static void main(String[] args) throws Exception {
        // ejercicio para matriz cuadrada
        int[][] matriz = {{3,4,1},{12,1,3},{1,6,8}};
        todasLasColumnasConUnMultiploDe(matriz,4); // true
        int[][] matriz2 = {{3,4,1},{12,1,3},{1,6,9}};
        todasLasColumnasConUnMultiploDe(matriz,4); // false
        
    }
    private static boolean todasLasColumnasConUnMultiploDe(int[][] matriz, int elemento) {
        boolean rta = true; // 1
        for (int j = 0; j < matriz[0].length; j++) { // 1 + 2n + n
            rta = rta && algunElementoMultiploEnColumna(matriz,j,elemento);
            // 2n + n( h(n)) = 2n  + n( 3+8n) = 2n + 3n + 8n^2 = 5n + 8n^2
        } 
        return rta; // 1
    } // f(n) = 8n^2+ 8n + 3
    // f(n) <= c g(n)
    // 8n^2+ 8n + 3 <= 9n^2
    // 8n^2/n^2 + 8n/n^2 + 3/n^2 <= 9n^2/n^2
    // 8 + 8/n + 3/n^2 <= 9
    // se prueban valores de n0
    // se cumple a partir de n0 = 9 y c=9 por lo que f(n) pertenece a O(n^2)
    // 
    private static boolean algunElementoMultiploEnColumna(int[][] matriz, int j, int elemento) {
        boolean rta = false; // 1
        for (int i = 0; i < matriz.length; i++) { // 1 + 2n + n
            rta = rta || matriz[i][j]%elemento==0; // 5n
        }
        return rta; // 1
    } // h(n) = 3 + 8n
    
        
}
