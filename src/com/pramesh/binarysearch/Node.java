/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.binarysearch;

/**
 *
 * @author pramesh
 */
public class Node {

    int key;
    String value;

    Node leftChild;
    Node rightChild;

    public Node(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return value + " has the key " + key;
    }
}
