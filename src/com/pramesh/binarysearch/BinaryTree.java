/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.binarysearch;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 *
 * @author pramesh
 */
public class BinaryTree {

    Node root;

    private BiConsumer<Integer, String> addNode() {
        return (key, value) -> {
            final Node newNode = new Node(key, value);
            if (root == null) {
                root = newNode;
            } else {
                pushNode().accept(root, newNode);
            }
        };
    }

    private BiConsumer<Node, Node> pushNodeToLeft() {
        return (focusNode, newNode) -> {
            if (focusNode.leftChild != null) {
                pushNode().accept(focusNode.leftChild, newNode);
            } else {
                focusNode.leftChild = newNode;
            }
        };
    }

    private BiConsumer<Node, Node> pushNodeToRight() {
        return (focusNode, newNode) -> {
            if (focusNode.rightChild != null) {
                pushNode().accept(focusNode.rightChild, newNode);
            } else {
                focusNode.rightChild = newNode;
            }
        };
    }

    private BiConsumer<Node, Node> pushNode() {
        return (focusNode, newNode) -> {
            if (newNode.key < focusNode.key) {
                pushNodeToLeft().accept(focusNode, newNode);
            } else {
                pushNodeToRight().accept(focusNode, newNode);
            }
        };
    }

    private Supplier<Node> findNode(int key, Node focusNode) {
        return () -> {
            if (key == focusNode.key) {
                return focusNode;
            } else if (key < focusNode.key) {
                return findNode(key, focusNode.leftChild).get();
            } else if (key > focusNode.key) {
                return findNode(key, focusNode.rightChild).get();
            } else {
                return null;
            }
        };
    }

    private static Supplier<Node> smallerChild(Node focusNode) {
        return () -> {
            if (focusNode.leftChild == null) {
                return focusNode;
            }
            return smallerChild(focusNode.leftChild).get();
        };
    }

    public static Node deleteNode(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.key > value) {
            root.leftChild = deleteNode(root.leftChild, value);
        } else if (root.key < value) {
            root.rightChild = deleteNode(root.rightChild, value);

        } else {
            // if nodeToBeDeleted have both children
            if (root.leftChild != null && root.rightChild != null) {
                Node temp = root;
                // Finding minimum element from right
                Node minNodeForRight = smallerChild(temp.rightChild).get();
                // Replacing current node with minimum node from right subtree
                root = minNodeForRight;
                // Deleting minimum node from right now
                deleteNode(root.rightChild, minNodeForRight.key);

            } // if nodeToBeDeleted has only left child
            else if (root.leftChild != null) {
                root = root.leftChild;
            } // if nodeToBeDeleted has only right child
            else if (root.rightChild != null) {
                root = root.rightChild;
            } // if nodeToBeDeleted do not have child (Leaf node)
            else {
                root = null;
            }
        }
        return root;
    }

    public static void main(String[] args) {

        BinaryTree theTree = new BinaryTree();

        theTree.addNode().accept(50, "Boss");

        theTree.addNode().accept(25, "Vice President");

        theTree.addNode().accept(15, "Office Manager");

        theTree.addNode().accept(30, "Secretary");

        theTree.addNode().accept(75, "Sales Manager");

        theTree.addNode().accept(85, "Salesman 1");

        theTree.addNode().accept(65, "Salesman 2");

        // Different ways to traverse binary trees
        System.out.println("-----------------------------");
        System.out.println("------IN ORDER TRAVERSE------");
        System.out.println("-----------------------------");
        Traversal.inOrderTraversTree().accept(theTree.root);
//
//        System.out.println("-----------------------------");
//        System.out.println("------PRE ORDER TRAVERSE------");
//        System.out.println("-----------------------------");
//        Traversal.preOrderTraverseTree().accept(theTree.root);
//
//        System.out.println("-----------------------------");
//        System.out.println("------POST ORDER TRAVERSE------");
//        System.out.println("-----------------------------");
//        Traversal.postOrderTraverseTree().accept(theTree.root);

//        System.out.println("-----------------------------");
//        System.out.println("----Node with the key 75-----");
//        System.out.println(theTree.findNode(75, theTree.root).get());
//        System.out.println("-----------------------------");

        System.out.println("removing node 75");
        BinaryTree.deleteNode(theTree.root, 50);

        System.out.println("-----------------------------");
        System.out.println("------IN ORDER TRAVERSE------");
        System.out.println("-----------------------------");
        Traversal.inOrderTraversTree().accept(theTree.root);
    }
}
