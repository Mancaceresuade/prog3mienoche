public class EjercicioEnClase {
    public static void capicua() {
        //Demostrar si un array es capicua
        int[] numeros = {1, 2, 3, 2, 1}; // 1
        boolean capicua = true; //1 
        int n = numeros.length; //1 
        int aux = n / 2; // 1
        for (int i = 0; i < aux; i++) { //1 + (n/2) + n/2
            if (numeros[i] != numeros[n - 1 - i]) { // 5n/2  /// faltan 5 operacion
                capicua = false; // 1
                break; // 1
            }
        }
        if (capicua) { //1
            System.out.println("El array es capicua"); //1
        } else {
            System.out.println("El array no es capicua"); //1
        }
    } // f(n) = 10 + 7n/2
    // f(n) <= c g(n)
    // 10 + 7n/2 <= 9n/2
    // resto 7n/2 ambos lados
    // 10 <= n
    // f(n) pertenece a O(n) para c = 9/2  y n0 > 10
}
