package com.application.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<T> {

    private final int capacity;
    private int size;
    private final Map<Integer, Node> hashmap;
    private final DoublyLinkedList internalQueue;

    LRUCache(final int capacity) {
        this.capacity = capacity;
        this.hashmap = new HashMap<>();
        this.internalQueue = new DoublyLinkedList();
        this.size = 0;
    }

    public int get(final int key) {
        Node node = hashmap.get(key);
        if(node == null) {
            return -1;
        }
        internalQueue.modeNodeToFront(node);
        return (int) hashmap.get(key).value;
    }

    public void put(final int key, final int value) {
        Node currentNode = hashmap.get(key);
        if(currentNode != null) {
            currentNode.value = value;
            internalQueue.modeNodeToFront(currentNode);
            return;
        }

        if(size == capacity) {
            int rearNodeKey = internalQueue.getRearKey();
            internalQueue.removeNodeFromRear();
            hashmap.remove(rearNodeKey);
            size--;
        }

        Node node = new Node(key, value);
        internalQueue.addNodeToFront(node);
        hashmap.put(key, node);
        size++;
    }
    
    public void delete(final int key) {
    	Node currentNode = hashmap.get(key);
    	if(currentNode != null) {
            internalQueue.deleteNode(currentNode);
            hashmap.remove(key);
            size--;
            return;
        } 
    }

    private class Node {
        int key;
        int value;
        Node next, prev;
        public Node(final int key2, final int value) {
            this.key = key2;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private class DoublyLinkedList {
        private Node front, rear;
        public DoublyLinkedList() {
            front = rear = null;
        }

        private void addNodeToFront(final Node node) {
            if(rear == null) {
                front = rear = node;
                return;
            }
            node.next = front;
            front.prev = node;
            front = node;
        }

        public void modeNodeToFront(final Node node) {
            if(front == node) {
                return;
            }

            if(node == rear) {
                rear = rear.prev;
                rear.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            node.prev = null;
            node.next = front;
            front.prev = node;
            front = node;
        }

        private void removeNodeFromRear() {
            if(rear == null) {
                return;
            }

            System.out.println("Deleting key: " + rear.key);
            if(front == rear) {
                front = rear = null;
            } else {
                rear = rear.prev;
                rear.next = null;
            }
        }

        private int getRearKey() {
            return rear.key;
        }
        
        private void deleteNode(final Node node) {
        	System.out.println("Deleting key: " + node.key);
        	if(front == rear) {
        		front = rear = null;
            } else if (node == rear){
            	rear = rear.prev;
                rear.next = null;
            }
            else {
            	node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }
    }
}
