import MyFramework.Collection.*;
import MyFramework.Maps.*;


public class Main {
    public static void main(String[] args) {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.add("The");
        tree.add("quick");
        tree.add("brown");
        tree.add("fox");
        tree.add("apple");
        tree.add("cat");
        tree.add("hat");
        System.out.println(tree.preorder());
        tree.breadthFirstTraversal();
//        Heap<Integer> list = new Heap<>();
//        list.insert(5);
//        list.insert(2);
//        list.insert(7);
//        list.insert(8);
//        list.insert(3);
//        list.insert(4);
//
//        System.out.println(list);
//        System.out.println(list.heapSort());
    }
}
