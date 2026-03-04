/*
 * TODO: Nikolay Merenko
 * TODO: March 2026
 * TODO: Period 4
 * TODO: A BST class that implements the BSTInterface and uses the TreeNode class to create a Binary Search Tree.  
 */

 import java.lang.Comparable;

 public class BSTTester {
   public static void main(String[] args) {
     BST tree = new BST();

     System.out.println("--- BST Tester ---");

     // initially empty
     System.out.println("size (expected 0): " + tree.size());
     System.out.println("isEmpty (expected true): " + tree.isEmpty());

     // add elements
     int[] values = {50, 30, 70, 20, 40, 60, 80};
     for (int v : values) {
       tree.add(v);
     }
     System.out.println("size after adds (expected 7): " + tree.size());
     System.out.println("isEmpty (expected false): " + tree.isEmpty());

     // find existing and non-existing
     System.out.println("find 60 (expected true): " + tree.find(60));
     System.out.println("find 25 (expected false): " + tree.find(25));

     // traversals
     tree.printInOrder();   
     tree.printPreOrder(); 
     tree.printPostOrder();

     // test replace: existing
     boolean replaced = tree.replace(70, 75);
     System.out.println("replace 70 with 75 (expected true): " + replaced);
     System.out.println("find 70 (expected false): " + tree.find(70));
     System.out.println("find 75 (expected true): " + tree.find(75));

     // test replace: not existing should add
     boolean replaced2 = tree.replace(999, 65);
     System.out.println("replace 999 with 65 (expected false, 65 added): " + replaced2);
     System.out.println("find 65 (expected true): " + tree.find(65));

     // print inorder after replacements
     tree.printInOrder();

     // test delete leaf, one-child, two-child
     System.out.println("delete 20 (leaf, expected true): " + tree.delete(20));
     System.out.println("delete 30 (probably one-child, expected true): " + tree.delete(30));
     System.out.println("delete 50 (two-child root, expected true): " + tree.delete(50));
     System.out.println("delete 1234 (not present, expected false): " + tree.delete(1234));

     tree.printInOrder();
     System.out.println("size after deletes (expected >0): " + tree.size());

     // final emptiness check
     System.out.println("isEmpty (expected false): " + tree.isEmpty());
   }

}