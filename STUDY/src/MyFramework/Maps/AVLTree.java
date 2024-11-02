package MyFramework.Maps;

public class AVLTree<E extends Comparable<E>> {
    private static class Node<E> {
        E data;
        Node<E> left, right;
        int height;
        
        Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }
    
    private Node<E> root;
    
    public AVLTree() {
        root = null;
    }
    
    public void insert(E data) {
        root = insert(root, data);
    }
    
    public void delete(E data) {
        root = delete(root, data);
    }
    
    public E search(E data) {
        Node<E> node = search(root, data);
        return node == null ? null : node.data;
    }
    
    private Node<E> insert(Node<E> node, E data) {
        if (node == null) {
            return new Node<>(data);
        }
        int compResult = data.compareTo(node.data);
        
        if (compResult < 0) {
            node.left = insert(node.left, data);
        } else if (compResult > 0) {
            node.right = insert(node.right, data);
        } else {
            node.data = data;
        }
        
        return rebalance(node);
    }
    
    private Node<E> delete(Node<E> node, E data) {
        if (node == null) {
            return null;
        }
        int compResult = data.compareTo(node.data);
        
        if (compResult < 0) {
            node.left = delete(node.left, data);
        } else if (compResult > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node<E> leftMost = MRC(node.left);
                node.data = leftMost.data;
                node.left = delete(node.left, leftMost.data);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        
        return node;
    }
    
    private Node<E> search(Node<E> node, E data) {
        if (node == null || data.compareTo(node.data) == 0) {
            return node;
        }
        if (data.compareTo(node.data) < 0) {
            return search(node.left, data);
        } else {
            return search(node.right, data);
        }
    }
    
    private Node<E> rebalance(Node<E> node) {
        if (node == null) {
            return null;
        }
        updateHeight(node);
        int balance = getBalance(node);
        
        if (balance < -1) {
            if (node.left != null && height(node.left.right) > height(node.left.left)) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (balance > 1) {
            if (node.right != null && height(node.right.left) > height(node.right.right)) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        return node;
    }
    
    private Node<E> rotateLeft(Node<E> node) {
        if (node == null) {
            return null;
        }
        
        Node<E> newRoot = node.right;
        
        node.right = newRoot.left;
        newRoot.left = node;
        
        updateHeight(node);  // Update the height of the old root first.
        updateHeight(newRoot);  // Then update the height of the new root.
        
        return newRoot;
    }
    
    private Node<E> rotateRight(Node<E> node) {
        if (node == null) {
            return null;
        }
        
        Node<E> newRoot = node.left;
        
        node.left = newRoot.right;
        newRoot.right = node;
        
        updateHeight(node);  // Update the height of the old root first.
        updateHeight(newRoot);  // Then update the height of the new root.
        
        return newRoot;
    }
    
    
    private void updateHeight(Node<E> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }
    
    private int getBalance(Node<E> node) {
        return height(node.right) - height(node.left);
    }
    
    private int height(Node<E> node) {
        return node == null ? 0 : node.height;
    }
    
    private Node<E> MRC(Node<E> node) {
        Node<E> result = node;
        while (result.right != null) {
            result = result.right;
        }
        return result;
    }
    
    public void preOrder() {
        preOrder(root, 0);
    }
    
    private void preOrder(Node<E> node, int depth) {
        if (node == null) {
            return;
        }
        String indent = " ".repeat(depth * 2); // Create an indentation string
        System.out.print(indent + node.data + "\n");
        preOrder(node.left, depth + 1);
        preOrder(node.right, depth + 1);
    }
}
