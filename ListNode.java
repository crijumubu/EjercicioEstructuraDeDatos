package com.company;

public class ListNode {

    private Object object; //Objeto a almacenar
    public ListNode previous; //Puntero o dirección para ir al nodo anterior
    public ListNode next; //Puntero o dirección para ir al siguiente nodo

    public ListNode() {
        this.object = null; //Solo creo un nodo pero no le paso nada
        this.previous = null;
        this.next = null;
    }

    public ListNode(Object object) {
        this.object = object; //Solo creo un nodo y le paso el objeto más no el nodo
        this.previous = null;
        this.next = null;
    }

    public ListNode(Object object, ListNode next, ListNode previous) {
        this.object = object; //Solo creo un nodo y le paso ambos valores
        this.next = next;
        this.previous = previous;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isEquals(Object object) {
        if (this.getObject().toString().equals(object.toString())) {
            return true;
        }
        return false;
    }

    public boolean isEquals(ListNode node) {
        if (this.toString().equals(node.toString())) {
            return true;
        }
        return false;
    }

}