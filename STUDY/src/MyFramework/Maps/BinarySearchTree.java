package MyFramework.Maps;

import MyFramework.Collection.ArrayQueue;
import MyFramework.Collection.ArrayStack;


public class BinarySearchTree<E extends Comparable<E>> {
    protected Node<E> root = null;
    protected boolean addReturn;
    protected E deleteReturn;
    
    public BinarySearchTree() {
    }
    
    @SuppressWarnings("unchecked")
    public BinarySearchTree(String postfixExpression) {
        root = (Node<E>) constructExpressionTree(postfixExpression);
    }
    
    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
    
    private static Node<String> constructExpressionTree(String expression) {
        String[] tokens = expression.split(" ");
        ArrayStack<Node<String>> stack = new ArrayStack<>();
        
        for (String token : tokens) {
            if (isOperator(token)) {
                Node<String> right = stack.pop();
                Node<String> left = stack.pop();
                Node<String> newNode = new Node<>(token);
                newNode.left = left;
                newNode.right = right;
                stack.push(newNode);
            } else {
                stack.push(new Node<>(token));
            }
        }
        return stack.pop();
    }
    
    
    @SuppressWarnings("unchecked")
    public int evaluate() {
        if (root.data instanceof String)
            return evaluate((Node<String>) root);
        return -222;
    }
    
    private int evaluate(Node<String> node) {
        if (node.data.matches("\\d+")) {
            return Integer.parseInt(node.data);
        } else if (node.data.length() == 1) {
            int leftVal = evaluate(node.left);
            int rightVal = evaluate(node.right);
            char operator = node.data.charAt(0);
            
            return switch (operator) {
                case '+' -> leftVal + rightVal;
                case '-' -> leftVal - rightVal;
                case '*' -> leftVal * rightVal;
                case '/' -> leftVal / rightVal;
                default -> throw new IllegalArgumentException("Invalid operator: " + operator);
            };
        } else {
            throw new IllegalArgumentException("Invalid node value: " + node.data);
        }
    }
    
    public E getData() {
        if (root != null)
            return root.data;
        else
            return null;
        
    }
    
    public boolean isLeaf() {
        return root.left == null && root.right == null;
    }
    
    public boolean add(E key) {
        root = add(root, key);
        return addReturn;
    }
    
    private Node<E> add(Node<E> root, E key) {
        if (root == null) {
            addReturn = true;
            return new Node<>(key);
        }
        int comp = key.compareTo(root.data);
        if (comp < 0) {
            root.left = add(root.left, key);
        } else if (comp > 0) {
            root.right = add(root.right, key);
        }
        addReturn = false;
        return root;
    }
    
    public E delete(E key) {
        root = delete(root, key);
        return deleteReturn;
    }
    
    private Node<E> delete(Node<E> root, E key) {
        if (root == null) {
            deleteReturn = null;
            return null;
        }
        int comp = key.compareTo(root.data);
        if (comp < 0) {
            root.left = delete(root.left, key);
        } else if (comp > 0) {
            root.right = delete(root.right, key);
        } else {
            deleteReturn = root.data;
            if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                if (root.left.right == null) {
                    root.data = root.left.data;
                    root.left = root.left.left;
                } else {
                    root.data = findLargestChild(root.left);
                }
            }
        }
        return root;
    }
    
    private E findLargestChild(Node<E> parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }
    
    public E delete2(E key) {
        root = delete2(root, key);
        return deleteReturn;
    }
    
    private Node<E> delete2(Node<E> root, E key) {
        if (root == null) {
            deleteReturn = null;
            return null;
        }
        int comp = key.compareTo(root.data);
        if (comp < 0) {
            root.left = delete(root.left, key);
        } else if (comp > 0) {
            root.right = delete(root.right, key);
        } else {
            deleteReturn = root.data;
            if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                if (root.left.right == null) {
                    root.data = root.left.data;
                    root.left = root.left.left;
                } else {
                    root.data = findSmallestChild(root.right);
                }
            }
        }
        return root;
    }
    
    private E findSmallestChild(Node<E> parent) {
        if (parent.left.left == null) {
            E returnValue = parent.left.data;
            parent.left = parent.left.right;
            return returnValue;
        } else {
            return findSmallestChild(parent.left);
        }
    }
    
    public E predecessor(E target) {
        Node<E> parent = findParent(root, target);
        if (parent == null)
            return null;
        
        Node<E> grandparent = findParent(root, parent.data);
        if (grandparent == null)
            return null;
        
        return grandparent.data;
    }
    
    private Node<E> findParent(Node<E> root, E key) {
        if (root == null) {
            return null;
        }
        int comp = key.compareTo(root.data);
        if (comp < 0) {
            if (root.left.data.equals(key)) {
                return root; // Found parent
            } else {
                return findParent(root.left, key); // Search in the left subtree
            }
        } else if (comp > 0) {
            if (root.right.data.equals(key)) {
                return root; // Found parent
            } else {
                return findParent(root.right, key); // Search in the right subtree
            }
        } else {
            return null;
        }
    }
    
    public boolean contains(E target) {
        return find(root, target) != null;
    }
    
    private Node<E> find(Node<E> root, E key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.data) < 0) {
            return find(root.left, key);
        } else if (key.compareTo(root.data) > 0) {
            return find(root.right, key);
        } else {
            return root;
        }
    }
    
    public String preorder() {
        StringBuilder sb = new StringBuilder();
        preorderRec(root, 0, sb);
        return sb.toString();
    }
    
    private void preorderRec(Node<E> root, int depth, StringBuilder sb) {
        if (root != null) {
            sb.append("   ".repeat(depth));
            sb.append(root.data).append("\n");
            preorderRec(root.left, depth + 1, sb);
            preorderRec(root.right, depth + 1, sb);
        } else {
            sb.append("   ".repeat(depth));
            sb.append("null\n");
        }
    }
    
    public String inorder() {
        StringBuilder sb = new StringBuilder();
        inorderRec(root, 0, sb);
        return sb.toString();
    }
    
    private void inorderRec(Node<E> root, int depth, StringBuilder sb) {
        if (root != null) {
            inorderRec(root.left, depth + 1, sb);
            sb.append("   ".repeat(depth));
            sb.append(root.data).append("\n");
            inorderRec(root.right, depth + 1, sb);
        } else {
            sb.append("   ".repeat(depth));
            sb.append("null\n");
        }
    }
    
    public String postorder() {
        StringBuilder sb = new StringBuilder();
        postorderRec(root, 0, sb);
        return sb.toString();
    }
    
    private void postorderRec(Node<E> root, int depth, StringBuilder sb) {
        if (root != null) {
            postorderRec(root.left, depth + 1, sb);
            postorderRec(root.right, depth + 1, sb);
            sb.append("   ".repeat(depth));
            sb.append(root.data).append("\n");
        } else {
            sb.append("   ".repeat(depth));
            sb.append("null\n");
        }
    }
    
    public void breadthFirstTraversal() {
        if (root == null) {
            return;
        }
        ArrayQueue<Node<E>> queue = new ArrayQueue<>();
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
    
    
    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    
    public boolean isBalanced_() {
        
        int left = 0, right = 0;
        ArrayQueue<Node<E>> queueLeft = new ArrayQueue<>();
        ArrayQueue<Node<E>> queueRight = new ArrayQueue<>();
        queueLeft.offer(root.left);
        while (!queueLeft.isEmpty()) {
            var current = queueLeft.poll();
            left++;
            if (current.left != null) {
                queueLeft.offer(current.left);
            }
            if (current.right != null) {
                queueLeft.offer(current.right);
            }
        }
        queueRight.offer(root.right);
        while (!queueRight.isEmpty()) {
            var current = queueRight.poll();
            right++;
            if (current.left != null) {
                queueRight.offer(current.left);
            }
            if (current.right != null) {
                queueRight.offer(current.right);
            }
        }
        return left == right;
    }
    
    public boolean isBalanced() {
        return isBalanced(root) > 0;
    }
    
    private int isBalanced(Node<E> root) {
        if (root == null) return 0;
        int numLeft = isBalanced(root.left);
        int numRight = isBalanced(root.right);
        if (numLeft > 0 && numRight > 0)
            if (numLeft == numRight)
                return numLeft + numRight + 1;
            else
                return -1;
        return -1;
    }
    
    
    @Override
    public String toString() {
        return preorder();
    }
    
    protected static class Node<E> {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;
        
        public Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        
        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        
        @Override
        public String toString() {
            return data.toString();
        }
    }
    
    
}
