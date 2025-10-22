import java.util.ArrayList;

public class App {
    static int contadorCombinaciones = 0;
    static int optimo = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> resultados = new ArrayList<>() ;
    public static void main(String[] args) throws Exception {
        int[] proyectos = {3,4,5,1,2,5};  //  emp 1  3+4=7    emp 2 5+1=6  emp 3 2+5=7   //  optimo 7
        // {0,0,0,0,0,0}  todo al 1
        // {0,0,1,1,2,2}    optimo 7   //  emp 1  3+4=7    emp 2 5+1=6  emp 3 2+5=7   //  optimo 7
        // {1,1,2,2,0,0}    alternativa
        // {2,2,2,2,2,2,}  todo al 3
        int empleados = 3; // 3**6  = 729
        ArrayList<Integer> com_actual = new ArrayList<>();
        asignarHoras(proyectos,empleados,com_actual,0);
        System.out.println(contadorCombinaciones);
        System.out.println(optimo);
        resultados.forEach(r -> System.out.println(r));
    }
    private static void asignarHoras(int[] proyectos, int empleados, ArrayList<Integer> com_actual, int i) {
        int optimoActual = calcularOptimoAcutal(proyectos,empleados,com_actual); 
        // caso base
        if(i==proyectos.length) {
            contadorCombinaciones++;
            // filtro , fuerza bruta
            if(optimoActual < optimo) {
                optimo = optimoActual;   
                resultados.clear();
                resultados.add(new ArrayList<>(com_actual));             
            } else if(optimoActual == optimo) {
                resultados.add(new ArrayList<>(com_actual));             
            }
            return;
        }        
        // ramificacion
        for (int j = 0; j < empleados; j++) {
            com_actual.add(j);
            if(optimoActual > optimo) {
                // poda
                com_actual.remove(com_actual.size()-1);
                continue;    
            }
            asignarHoras(proyectos, empleados, com_actual, i+1);
            com_actual.remove(com_actual.size()-1);
        }
    }
    private static int calcularOptimoAcutal(int[] proyectos, int empleados, ArrayList<Integer> com_actual) {
        // {3,4,5,1,2,5}  // 20
        // {0,0,0,0,0,0}  todo al 1
        // {0,0,1,1,2,2}    optimo 7   //  emp 1  3+4=7    emp 2 5+1=6  emp 3 2+5=7   //  optimo 7
        // {1,1,2,2,0,0}    alternativa
        // {2,2,2,2,2,2,}  todo al 3
        int[] emp = new int[empleados];
        // agrupar por empleado
        for (int i = 0; i < com_actual.size(); i++) {
            emp[com_actual.get(i)] += proyectos[i];
        }
        int maximo = 0;
        for (int i = 0; i < emp.length; i++) {
            if(emp[i]>maximo){
                maximo=emp[i];
            }
        }
        return maximo;
    }
    
}






