class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

public class Ej1BinarySearchTree {

    // Método para buscar un nodo con valor x en el BST
    public TreeNode searchBST(TreeNode root, int x) {
        if (root == null || root.value == x) {
            return root;
        }
        if (x < root.value) {
            return searchBST(root.left, x);
        }
        return searchBST(root.right, x);
    }

    // Método para calcular la altura de un nodo
    private int computeHeight(TreeNode node) {
        if (node == null) return -1;
        int leftHeight = computeHeight(node.left);
        int rightHeight = computeHeight(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Método principal para obtener la altura del nodo con valor x
    public int getHeight(TreeNode root, int x) {
        TreeNode node = searchBST(root, x);
        if (node == null) return -1; // Si no se encuentra el nodo
        return computeHeight(node);
    }

    // Método para calcular la altura total del árbol
    public static int altura(TreeNode root) {
        if (root == null) {
            return -1;
        } else {
            return 1 + Math.max(altura(root.left), altura(root.right));
        }
    }

    public static void main(String[] args) {
        Ej1BinarySearchTree tree = new Ej1BinarySearchTree();

        // Crear un árbol de ejemplo
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(25);
        root.right.right.right = new TreeNode(30);

        // Buscar el valor 7 en el árbol
        int valueToSearch = 7;
        TreeNode result = tree.searchBST(root, valueToSearch);

        if (result != null) {
            System.out.println("Valor " + result.value + " encontrado en el árbol.");
            System.out.println("Altura del nodo " + valueToSearch + ": " + tree.getHeight(root, valueToSearch));
        } else {
            System.out.println("Valor no encontrado en el árbol.");
        }

        System.out.println("Altura total del árbol: " + altura(root));
    }
}
// La complejidad es:

//Árbol balanceado: O(log n) búsqueda + O(log n) altura → total O(log n)

//Árbol desbalanceado: O(n) búsqueda + O(n) altura → total O(n)