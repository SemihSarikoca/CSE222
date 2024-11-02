/**
 * AVLTree class represents a self-balancing Binary Search Tree (BST) where the difference
 * between heights of left and right subtrees cannot be more than one for all nodes.
 */
public class AVLTree {
    /**
     * Node class represents a node in the AVLTree. Each node has a Stock object,
     * references to left and right child nodes, and a height value.
     */
    private static class Node {
        Stock stock;
        Node left, right;
        int height;
        
        /**
         * Constructs a new Node with the given Stock object.
         * The left and right child are set to null.
         * The height is initially set to 1.
         *
         * @param stock The Stock object to be stored in this node.
         */
        Node(Stock stock) {
            this.stock = stock;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }
    
    // The root of the AVLTree
    private Node root = null;
    
    
    /**
     * Inserts a new stock into the AVL tree.
     * If the stock already exists in the tree, it updates the existing stock.
     * After the insertion, it ensures the tree remains balanced.
     *
     * @param stock The stock to be inserted.
     */
    public void insert(Stock stock) {
        this.root = insert(this.root, stock);
    }
    
    /**
     * Deletes a stock from the AVL tree using its symbol.
     * If the stock does not exist, it does nothing.
     * After the deletion, it ensures the tree remains balanced.
     *
     * @param symbol The symbol of the stock to be deleted.
     */
    public void delete(String symbol) {
        this.root = delete(this.root, symbol);
    }
    
    /**
     * Searches for a stock in the AVL tree using its symbol.
     * If the stock does not exist, it returns null.
     *
     * @param symbol The symbol of the stock to be searched.
     * @return The stock if found, null otherwise.
     */
    public Stock search(String symbol) {
        Node node = search(this.root, symbol);
        return node == null ? null : node.stock;
    }
    
    /**
     * Inserts a new stock into the AVL tree or updates the existing stock.
     * This method is called recursively until the correct position for the new stock is found.
     * If the stock already exists in the tree, it updates the existing stock.
     * After the insertion, it ensures the tree remains balanced by calling the rebalance method.
     *
     * @param node The node currently being compared with the new stock.
     * @param data The stock to be inserted.
     * @return The new or updated node after insertion and rebalancing.
     */
    private Node insert(Node node, Stock data) {
        if (node == null) {
            return new Node(data);
        }
        
        int compareResult = data.getSymbol().compareTo(node.stock.getSymbol());
        
        if (compareResult < 0) {
            node.left = insert(node.left, data);
        } else if (compareResult > 0) {
            node.right = insert(node.right, data);
        } else {
            node.stock = data;
        }
        return rebalance(node);
    }
    
    /**
     * Deletes a stock from the AVL tree using its symbol.
     * This method is called recursively until the node with the matching symbol is found.
     * If the node has one or no child, it replaces the node with its child (if any).
     * If the node has two children, it replaces the node with the stock of the most left child of the right subtree,
     * and then deletes that most left child.
     * After the deletion, it ensures the tree remains balanced by calling the rebalance method.
     *
     * @param node   The node currently being compared with the stock to be deleted.
     * @param symbol The symbol of the stock to be deleted.
     * @return The new root of the subtree after deletion and rebalancing.
     */
    private Node delete(Node node, String symbol) {
        if (node == null) {
            return null;
        }
        
        int compareResult = symbol.compareTo(node.stock.getSymbol());
        
        if (compareResult < 0) {
            node.left = delete(node.left, symbol);
        } else if (compareResult > 0) {
            node.right = delete(node.right, symbol);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node temp = mostLeftChild(node.right);
                node.stock = temp.stock;
                node.right = delete(node.right, temp.stock.getSymbol());
            }
        }
        
        if (node != null) {
            node = rebalance(node);
        }
        
        return node;
    }
    
    /**
     * Searches for a stock in the AVL tree using its symbol.
     * This method is called recursively until the node with the matching symbol is found.
     * If the node does not exist or the symbol matches the node's stock symbol, it returns the node.
     * If the symbol is less than the node's stock symbol, it searches the left subtree.
     * If the symbol is greater than the node's stock symbol, it searches the right subtree.
     *
     * @param node   The node currently being compared with the stock to be searched.
     * @param symbol The symbol of the stock to be searched.
     * @return The node if found, null otherwise.
     */
    private Node search(Node node, String symbol) {
        if (node == null || node.stock.getSymbol().equals(symbol)) {
            return node;
        }
        
        if (symbol.compareTo(node.stock.getSymbol()) < 0) {
            return search(node.left, symbol);
        } else {
            return search(node.right, symbol);
        }
    }
    
    /**
     * Rebalances the AVL tree after an insertion or deletion.
     * This method first updates the height of the node.
     * Then, it gets the balance factor of the node (the difference between the heights of the right and left subtrees).
     * If the balance factor is greater than 1, it means the right subtree is heavier, so it needs a left rotation.
     * If the balance factor is less than -1, it means the left subtree is heavier, so it needs a right rotation.
     * If the balance factor is within the range [-1, 1], it means the tree is already balanced, so it returns the node.
     *
     * @param node The node to be rebalanced.
     * @return The new root of the subtree after rebalancing.
     */
    private Node rebalance(Node node) {
        updateHeight(node);
        int balance = getBalance(node);
        
        if (balance > 1) {
            if (node.left != null && height(node.left.right) > height(node.left.left)) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (balance < -1) {
            if (node.right != null && height(node.right.left) > height(node.right.right)) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        return node;
    }
    
    /**
     * Performs a right rotation on the given node.
     * This operation is used to rebalance the AVL tree during insertions and deletions.
     * It is called when the left subtree of the given node is heavier than the right subtree.
     * The left child of the given node becomes the new root of the subtree,
     * the right child of the new root (the left child of the original node) becomes the left child of the original node,
     * and the original node becomes the right child of the new root.
     * After the rotation, it updates the heights of the original node and the new root.
     *
     * @param node The node to be rotated.
     * @return The new root of the subtree after the rotation.
     */
    private Node rotateRight(Node node) {
        if (node == null || node.left == null) {
            return node;
        }
        
        Node newRoot = node.left;
        Node temp = newRoot.right;
        
        newRoot.right = node;
        node.left = temp;
        
        updateHeight(node);
        updateHeight(newRoot);
        
        return newRoot;
    }
    
    /**
     * Performs a left rotation on the given node.
     * This operation is used to rebalance the AVL tree during insertions and deletions.
     * It is called when the right subtree of the given node is heavier than the left subtree.
     * The right child of the given node becomes the new root of the subtree,
     * the left child of the new root (the right child of the original node) becomes the right child of the original node,
     * and the original node becomes the left child of the new root.
     * After the rotation, it updates the heights of the original node and the new root.
     *
     * @param node The node to be rotated.
     * @return The new root of the subtree after the rotation.
     */
    private Node rotateLeft(Node node) {
        if (node == null || node.right == null) {
            return node;
        }
        
        Node newRoot = node.right;
        Node temp = newRoot.left;
        
        newRoot.left = node;
        node.right = temp;
        
        updateHeight(node);
        updateHeight(newRoot);
        
        return newRoot;
    }
    
    /**
     * Updates the height of the given node.
     * The height of a node is defined as 1 plus the maximum height of its two children.
     * If the node is null, it does nothing.
     *
     * @param node The node whose height is to be updated.
     */
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }
    
    /**
     * Returns the height of the given node.
     * If the node is null, it returns -1.
     *
     * @param node The node whose height is to be returned.
     * @return The height of the node, or -1 if the node is null.
     */
    private int height(Node node) {
        return node == null ? -1 : node.height;
    }
    
    /**
     * Returns the balance factor of the given node.
     * The balance factor of a node is defined as the height of its right child minus the height of its left child.
     * If the node is null, it returns 0.
     *
     * @param node The node whose balance factor is to be returned.
     * @return The balance factor of the node, or 0 if the node is null.
     */
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.right) - height(node.left);
    }
    
    /**
     * Returns the most left child of the given node.
     * This method is used to find the successor of a node in an in-order traversal of the AVL tree.
     *
     * @param node The node whose most left child is to be returned.
     * @return The most left child of the node.
     */
    private Node mostLeftChild(Node node) {
        Node current = node;
        
        while (current.left != null) {
            current = current.left;
        }
        
        return current;
    }
    
    /**
     * Performs an in-order traversal of the AVL tree.
     * In an in-order traversal, it first visits the left subtree, then the root, and finally the right subtree.
     * It prints the stock of each node during the traversal.
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }
    
    /**
     * Performs a pre-order traversal of the AVL tree.
     * In a pre-order traversal, it first visits the root, then the left subtree, and finally the right subtree.
     * It prints the stock of each node during the traversal.
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }
    
    /**
     * Performs a post-order traversal of the AVL tree.
     * In a post-order traversal, it first visits the left subtree, then the right subtree, and finally the root.
     * It prints the stock of each node during the traversal.
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }
    
    /**
     * Helper method for the public inOrderTraversal method.
     * It recursively performs an in-order traversal of the subtree rooted at the given node.
     *
     * @param node The root of the subtree to be traversed.
     */
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.stock);
            inOrderTraversal(node.right);
        }
    }
    
    /**
     * Helper method for the public preOrderTraversal method.
     * It recursively performs a pre-order traversal of the subtree rooted at the given node.
     *
     * @param node The root of the subtree to be traversed.
     */
    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(node.stock);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }
    
    /**
     * Helper method for the public postOrderTraversal method.
     * It recursively performs a post-order traversal of the subtree rooted at the given node.
     *
     * @param node The root of the subtree to be traversed.
     */
    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.stock);
        }
    }
}
