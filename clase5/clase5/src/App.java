import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        HashMap<Integer,Integer> map = new HashMap<>();
        System.out.println(fibonacci(1, map));
    }

    private static int fibonacci(int n, HashMap<Integer,Integer> map) {
        if(map.containsKey(n)) return map.get(n);
        if(n==0) return 0;
        if(n==1) return 1;
        int var = fibonacci(n-1,map)+fibonacci(n-2,map);
        map.put(n, var);
        return var;
    } 

    


}
