package MyFramework.Maps;

public class RedBlackTree<T extends Comparable<T>> {
    private static class Node<T> {
        T data;
        Node<T> left, right;
        char color;
        
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.color = 'R';
        }
    }
    
    private Node<T> root;
    
    public RedBlackTree() {
        root = null;
    }
    
    public void insert(T data) {
        root = insert(root, data);
        root.color = 'B'; // Root should always be black
    }
    
    private Node<T> insert(Node<T> node, T data) {
        // Normal Binary Search Tree insertion
        if (node == null) {
            return new Node<>(data);
        }
        
        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else {
            // Duplicate data not allowed
            return node;
        }
        
        // Fix the Red-Black Tree property if it is violated
        return fixViolations(node);
    }
    
    private Node<T> fixViolations(Node<T> node) {
        if (node == null) {
            return null;
        }
        
        // Case 1: Node has red child
        if (isRed(node.left) && isRed(node.right)) {
            node.color = 'R';
            node.left.color = 'B';
            node.right.color = 'B';
        }
        
        // Case 2: Node has red left child and the left child has red right child
        if (isRed(node.left) && isRed(node.left.right)) {
            node.left = rotateLeft(node.left);
            node = rotateRight(node);
        }
        // Case 3: Node has red right child and the right child has red left child
        else if (isRed(node.right) && isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        // Case 4: Node has red right child and the right child has red right child
        else if (isRed(node.right) && isRed(node.right.right)) {
            node = rotateLeft(node);
        }
        // Case 5: Node has red left child and the left child has red left child
        else if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        
        return node;
    }
    
    private boolean isRed(Node<T> node) {
        if (node == null) {
            return false; // Null nodes are black
        }
        return node.color == 'R';
    }
    
    private Node<T> rotateRight(Node<T> node) {
        Node<T> temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = 'R';
        return temp;
    }
    
    private Node<T> rotateLeft(Node<T> node) {
        Node<T> temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = 'R';
        return temp;
    }
    
    
    public void print() {
        print(root, 0);
        System.out.println();
    }
    
    private void print(Node<T> node, int depth) {
        if (node == null) {
            return;
        }
        
        
        print(node.right, depth + 1);
        System.out.print("  ".repeat(depth));
        System.out.println(node.data + " " + node.color);
        print(node.left, depth + 1);
    }
    
    public void printInOrder() {
        print(root, 0);
    }
    
}
