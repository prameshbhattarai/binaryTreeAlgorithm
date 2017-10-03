/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.binarysearch;

import java.util.function.BiConsumer;

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

        System.out.println("-----------------------------");
        System.out.println("------PRE ORDER TRAVERSE------");
        System.out.println("-----------------------------");
        Traversal.preOrderTraverseTree().accept(theTree.root);

        System.out.println("-----------------------------");
        System.out.println("------POST ORDER TRAVERSE------");
        System.out.println("-----------------------------");
        Traversal.postOrderTraverseTree().accept(theTree.root);
    }
}
