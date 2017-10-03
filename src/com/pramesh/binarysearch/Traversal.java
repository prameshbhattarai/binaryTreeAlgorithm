/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.binarysearch;

import java.util.function.Consumer;

/**
 *
 * @author pramesh
 */
public class Traversal {

    /*
        Pattern for traversing: left child node -> focus node -> right child node
        When to use ?
        If you want to traverse the tree in the same way it was created.
     */
    public static Consumer<Node> inOrderTraversTree() {
        return (focusNode) -> {
            if (focusNode != null) {
                inOrderTraversTree().accept(focusNode.leftChild);
                System.out.println(focusNode);
                inOrderTraversTree().accept(focusNode.rightChild);
            }
        };
    }

    /*
        Pattern for traversing: focus node -> left child node -> right child node
        When to use ?
        If you want to traverse the roots before child nodes, you will explore all the roots before all of the leaves.
     */
    public static Consumer<Node> preOrderTraverseTree() {
        return (focusNode) -> {
            if (focusNode != null) {
                System.out.println(focusNode);
                inOrderTraversTree().accept(focusNode.leftChild);
                inOrderTraversTree().accept(focusNode.rightChild);
            }
        };
    }

    /*
        Pattern for traversing: left child node -> right child node -> focus node
        When to use ?
        If you want to traverse all the chold nodes before any root, you will explore all the leaves before all of the roots.
     */
    public static Consumer<Node> postOrderTraverseTree() {
        return (focusNode) -> {
            if (focusNode != null) {
                inOrderTraversTree().accept(focusNode.leftChild);
                inOrderTraversTree().accept(focusNode.rightChild);
                System.out.println(focusNode);
            }
        };
    }
}
