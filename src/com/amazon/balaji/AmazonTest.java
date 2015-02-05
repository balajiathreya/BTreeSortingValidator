package com.amazon.balaji;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by balaji on 2/4/15.
 *
 *  Problem:
 *  A B-tree is a generalization of a binary search tree, where each node has n keys and n+1 children and n
 *  can be different for each node. The keys are sorted the same as in a binary search tree. For each key k in
 *  the tree, all children to the left must have keys less than k, and all children to the right must have keys
 *  greater than k.
 *
 *  Write a method that validates whether a B-tree is correctly sorted. You do NOT need to validate whether the tree
 *  is balanced. Use the following model for a node in the B-tree.
 *
 *  Java:
 *  class Node {
 *      List<Integer> keys;
 *      List<Node> children;
 *   }
 *
 *   C:
 *   struct node {
 *      int* values;
 *      struct node** children;
 *   };
 *
 *  INSTRUCTIONS TO RUN:
 *      1. Load the file into an IDE like Intellij and Eclipse. Run the main method.
 *      2. You can modify the constructBTree() method to construct a new BTree and check if the solution works
 *      (I didn't have time to add more methods to test multiple inputs)
 *
*      The algorithm used is a simple in-order traversal of the BTree
 *
 *   Runtime and Space Complexity:
 *       The entire Tree is traversed once. Time taken for this is O(n). Another O(n) time is spent on
 *       checking if the arraylist resulting from the traversal is properly sorted. So, the average run time
 *       complexity is O(n) + O(n) = O(2n) = O(n)
 *
 *
 *
 */
public class AmazonTest {
    // this is the arraylist that contains all the keys after in-order traversal. if this list is properly sorted
    // then the BTree is properly sorted
    ArrayList<Integer> sortedKeys = new ArrayList<Integer>();

    static class Node {
        List<Integer> keys;
        List<Node> children;
        // pointer to keep track of child node we are currently exploring
        int visitingChildIndex;

        Node(List<Integer> keys, List<Node> children){
            this.keys = keys;
            this.children = children;
        }
    }


    static class BTree{
        Node root;
        BTree(Node root){
            this.root = root;
        }
    }


    public static void main(String[] args){
        AmazonTest test = new AmazonTest();
        test.validateBTree(constructBTree());
        test.validateBTree(constructBTree1());
        test.validateBTree(constructBTree2());
    }

    void validateBTree(BTree bTree){
        traverseBTree(bTree.root);
        if(sortedKeys.size() == 0 || sortedKeys.size() == 1){
            System.out.println("BTree is correctly sorted");
            return;
        }
        for(int i = 1; i < sortedKeys.size(); i++){
            if(sortedKeys.get(i) > sortedKeys.get(i - 1)){
                continue;
            }
            else{
                System.out.println("BTree is not correctly sorted");
                return;
            }
        }
        System.out.println("BTree is correctly sorted");
    }

    void traverseBTree(Node root){
        if(root.children == null || root.children.size() == 0){
            sortedKeys.addAll(root.keys);
        }
        else{
            while (root.visitingChildIndex <= root.children.size()-1){
                traverseBTree(root.children.get(root.visitingChildIndex));
                if(root.visitingChildIndex <= root.keys.size() - 1){
                    sortedKeys.add(root.keys.get(root.visitingChildIndex));
                }
                root.visitingChildIndex++;
            }
        }
    }


    static BTree constructBTree(){
        Node left = new Node(Arrays.asList(new Integer[] {4,7}), null);
        Node right = new Node(Arrays.asList(new Integer[] {17,24}), null);
        Node root = new Node(Arrays.asList(new Integer[] {13}),null);
        root.children = Arrays.asList(left, right);

        Node n1 = new Node(Arrays.asList(new Integer[] {1,3}), null);
        Node n2 = new Node(Arrays.asList(new Integer[] {5,6}), null);
        Node n3 = new Node(Arrays.asList(new Integer[] {8,9,11}), null);
        left.children = Arrays.asList(n1,n2,n3);

        Node n4 = new Node(Arrays.asList(new Integer[] {14,16}), null);
        Node n5 = new Node(Arrays.asList(new Integer[] {18,20}), null);
        Node n6 = new Node(Arrays.asList(new Integer[] {25,30,31,35}), null);
        right.children = Arrays.asList(n4,n5,n6);

        BTree bTree = new AmazonTest.BTree(root);
        return bTree;
    }


    static BTree constructBTree1(){
        Node left = new Node(Arrays.asList(new Integer[] {4,7}), null);
        Node right = new Node(Arrays.asList(new Integer[] {17,24}), null);
        Node root = new Node(Arrays.asList(new Integer[] {13}),null);
        root.children = Arrays.asList(left, right);

        Node n1 = new Node(Arrays.asList(new Integer[] {1,3}), null);
        Node n2 = new Node(Arrays.asList(new Integer[] {5,6}), null);
        Node n3 = new Node(Arrays.asList(new Integer[] {8,10,15}), null);
        left.children = Arrays.asList(n1,n2,n3);

        Node n4 = new Node(Arrays.asList(new Integer[] {14,16}), null);
        Node n5 = new Node(Arrays.asList(new Integer[] {18,20}), null);
        Node n6 = new Node(Arrays.asList(new Integer[] {25,30,31,35}), null);
        right.children = Arrays.asList(n4,n5,n6);

        BTree bTree = new AmazonTest.BTree(root);
        return bTree;
    }

    static BTree constructBTree2(){
        Node left = new Node(Arrays.asList(new Integer[] {4,9}), null);
        Node right = new Node(Arrays.asList(new Integer[] {17,24}), null);
        Node root = new Node(Arrays.asList(new Integer[] {13}),null);
        root.children = Arrays.asList(left, right);

        Node n1 = new Node(Arrays.asList(new Integer[] {1,3}), null);
        Node n2 = new Node(Arrays.asList(new Integer[] {5,6}), null);
        Node n3 = new Node(Arrays.asList(new Integer[] {8,10,15}), null);
        left.children = Arrays.asList(n1,n2,n3);

        Node n4 = new Node(Arrays.asList(new Integer[] {14,16}), null);
        Node n5 = new Node(Arrays.asList(new Integer[] {18,20}), null);
        Node n6 = new Node(Arrays.asList(new Integer[] {25,30,31,35}), null);
        right.children = Arrays.asList(n4,n5,n6);

        BTree bTree = new AmazonTest.BTree(root);
        return bTree;
    }

}
