public class Main {
    public static void main(String[] args) {
        AVL<Integer> avl = new AVL<>();
        avl.insertar(10);
        avl.insertar(20);
        avl.insertar(30);
        avl.insertar(15);
        avl.inOrder();

        
    }
}
