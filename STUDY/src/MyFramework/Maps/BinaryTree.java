package MyFramework.Maps;


import MyFramework.Collection.ArrayQueue;

import java.util.Scanner;

public class BinaryTree<E> {
    protected Node<E> root;
    
    public BinaryTree() {
        this.root = null;
    }
    
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }
    
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }
    
    public static BinaryTree<String> readPreOrderBinaryTree(Scanner scan) {
        String root = scan.nextLine().trim();
        if (root.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readPreOrderBinaryTree(scan);
            BinaryTree<String> rightTree = readPreOrderBinaryTree(scan);
            return new BinaryTree<>(root, leftTree, rightTree);
            
        }
    }
    
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }
    
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }
    
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }
    
    public void insert(E data) {
        root = insert(root, data);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, 0, sb);
        return sb.toString();
    }
    
    private void toString(Node<E> node, int depth, StringBuilder sb) {
        sb.append("  ".repeat(depth));
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node);
            sb.append("\n");
            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }
    }
    
    private Node<E> insert(Node<E> node, E data) {
        if (node == null) {
            return new Node<>(data);
        }
        
        if (node.left == null) {
            node.left = new Node<>(data);
            return node.left;
        }
        
        if (node.right == null) {
            node.right = new Node<>(data);
            return node.right;
        }
        
        return insert(node.left, data);
    }
    
    public void breadthFirstTraversal() {
        if (root == null) {
            return;
        }
        ArrayQueue<BinaryTree.Node<E>> queue = new ArrayQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            var current = queue.poll();
            System.out.print(current.data + " ");
            
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }
    
    public void inorderTraversal() {
        inorderTraversal(root);
    }
    
    private void inorderTraversal(Node<E> node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }
    
    public void preorderTraversal() {
        preorderTraversal(root);
    }
    
    private void preorderTraversal(Node<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }
    
    public void postorderTraversal() {
        postorderTraversal(root);
    }
    
    public E getData() {
        return root.data;
    }
    
    private void postorderTraversal(Node<E> node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }
    
    protected static class Node<E> {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;
        
        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
