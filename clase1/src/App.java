import java.io.FileReader;

public class App {

    boolean todosPares(int[] arr) {
        boolean rta = true;  // 1
        int aux = arr.length; // 1
        for(int i = 1; i < aux; i++) { // 1 +  (n + 1) + n = 2 + 2n
            rta = rta && (arr[i]%2)==0;   // 5n
        }
        return rta; // 1
    } // f(n) = 7n + 4
    // f(n) <=  c g(n)
    // 7n + 4 <= c n  // tomo el termino dominante , sumo uno a la constante
    // 7n + 4 <= 8 n  // divido todo por n
    // 7n/n + 4n <= 8n/n
    // 7 + 4/n <= 8
    // para n = 1 4/n = 4, 7 + 4 = 11 <= 8 no se cumple
    // para n = 2 4/n = 2, 7 + 2 = 9 <= 8 no se cumple
    // para n = 3 4/n = 1.33, 7 + 1.33 = 8.33 <= 8 no se cumple
    // para n = 4 4/n = 1, 7 + 1
    // f(n) pertenece a O(n)  para n >= 4 y c = 8

    public static void main(String[] args){
        System.out.println(Persona.saludar());
        StringBuilder sb = new StringBuilder();
        sb.append("Hola, soy una persona");
        System.out.println(sb.toString());
        try {
            Integer id = 10/0;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }        
        FileReader fr = new FileReader("archivo.txt");
        

    }   
}
