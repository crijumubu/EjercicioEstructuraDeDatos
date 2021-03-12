package com.company;

import java.util.Iterator;
import static java.lang.System.*;

public class List implements IList, Iterable<ListNode>{

    private ListNode inode; //Nodo iterable
    private int size;

    public ListNode head; //Punteros para saber donde está el inicio y el fin
    public ListNode tail;

    /**
     * List
     */
    public List() {
        clear();
    }

    /*
    ok
     */
    public List(Object object) {
        add(object);
    }

    /*
    ok
     */
    public boolean isEmpty() {
        return head == null;
    }

    /*
    ok
     */
    @Override
    public int getSize() {
        return size;
    }

    /*
    ok
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /*
    ok
     */
    @Override
    public Object getHead() {
        return head;
    }

    /*
    ok
     */
    @Override
    public Object getTail() {
        return tail;
    }

    /*
    ok
     */
    @Override
    public ListNode search(Object object) {
        inode = head;
        for (int i=0; i<this.size; i++){
            if (inode.getObject() == object){
                return inode;
            }else {
                inode = inode.next;
            }
        }
        return null;
    }

    /*
    ok
     */
    @Override
    public boolean add(Object object) {
        return insertTail(object);
    }

    /*
    ok
     */
    @Override
    public boolean insert(ListNode node, Object object) {
        try {
            ListNode newNode = new ListNode(object);
            newNode.previous = node;
            (node.next).previous = newNode; //Se usa los parentesis para así entenderlo un poco mejor :D
            newNode.next = node.next;
            node.next = newNode;
            this.size++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
    ok
     */
    @Override
    public boolean insert(Object ob, Object object) {
        try {
            if (ob != null) {
                ListNode node = this.search(ob);
                if (node != null) {
                    return insert(node, object);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /*
    ok
     */
    @Override
    public boolean insertHead(Object object) {
        try {
            if (isEmpty()) {
                head = new ListNode(object); //Se crea el nodo
                tail = head;
            } else {
                head.previous = new ListNode(object, head, tail);
                head = head.previous;
            }
            tail.next = head;
            this.size++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
    ok
     */
    @Override
    public boolean insertTail(Object object) {
        try {
            if (isEmpty()) {
                head = new ListNode(object); //Se crea el nodo
                tail = head;
            } else {
                tail.next = new ListNode(object,head,tail); //tail hace referencia al nodo completo, tail.next hace referencia a la cajita del puntero
                tail = tail.next;
            }
            head.previous = tail;
            this.size++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
    ok
     */
    @Override
    public boolean remove(ListNode node) {
        remove(node.getObject());
        return  true;
    }

    /*
    ok
     */
    @Override
    public boolean remove(Object object) {
        if (isEmpty() == false){
            if(head == tail && object == head.getObject()){
                head=null;
                tail=null;
            }
            else if(head.isEquals(object)){ //object == head.getObject()
                head = head.next;
                tail.next = head;
            }
            else{
                ListNode previous = head,temp = head.next;
                while (temp != head && temp.getObject() != object){
                    previous = previous.next;
                    temp = temp.next;
                }
                if (temp != head){
                    previous.next = temp.next;
                    if (temp == tail){
                        tail = previous;
                    }
                }
            }
            size--;
        }
        return true;

    }

    @Override
    public boolean contains(Object object) {
        inode = head;
        do {
            if (inode.getObject() == object){
                return true;
            }
            inode = inode.next;
        } while (inode != head);
        return false;
    }


    public Object[] toArray() {
        Object[] array = new Object[size];
        inode = head;
        for (int i=0; i<size; i++){
            array[i] = inode.getObject();
            inode = inode.next;
        }
        return array;
    }

    @Override
    public Object[] toArray(Object[] object) {
        inode = head;
        for (int i=0; i<size; i++){
            object[i] = inode.getObject();
            inode = inode.next;
        }
        return object;
    }

    @Override
    public Object getBeforeTo() {
        return getBeforeTo(tail).getObject(); //Si invocan al método getBeforeTo que se encuentra sin parámetro se asume que me están pidiendo el objeto que se encuentra antes de la cola
    }

    /*
    ok
     */
    @Override
    public ListNode getBeforeTo(ListNode node) {
        if (isEmpty() == false){
            if (head == tail){
                return head;
            }
            else if(node == head){
                return tail;
            }
            else {
                ListNode previous = null;
                inode = head;
                do{
                    if (inode.getObject() == node.getObject()){
                        return previous;
                    }
                    else {
                        previous = inode;
                        inode = inode.next;
                    }
                }
                while (inode != head);
            }
        }
        return null;
    }

    @Override
    public Object getNextTo() {
        return getNextTo(head); //Si invocan al método getNextTo que se encuentra sin parámetro se asume que me están pidiendo el objeto que se encuentra después de la cabeza, claro está en caso de haya una cabeza o haya algo después de la cabeza
    }

    @Override
    public Object getNextTo(ListNode node) {
        if (isEmpty() == false){
            if (head == tail){
                return head;
            }
            else {
                ListNode previous = head;
                inode = head.next;
                do{
                    if (previous.getObject() == node.getObject()){
                        if (inode != null){
                            return inode.getObject();
                        }
                        else{
                            return null;
                        }
                    }
                    else {
                        previous = inode;
                        inode = inode.next;
                    }
                }
                while (previous != head);
            }
        }
        return null;
    }

    @Override
    public List subList(ListNode from, ListNode to) {
        List out = new List();
        if (isEmpty() == false){
            inode = head;
            boolean itsInRange = false;
            for (int i=0; i<size; i++){
                if ( inode.getObject() == from.getObject() || itsInRange == true){
                    if (inode.getObject() != to.getObject()){
                        out.add(inode.getObject());
                        itsInRange = true;
                    }
                    else {
                        out.add(inode.getObject());
                        break;
                    }
                }
                inode = inode.next;
            }
        }
        return out;
    }

    @Override
    public List sortList() {
        Object previous;
        Object actual;
        int cont = 0;
        do{
            inode = head;
            while(inode.next != head)
            {
                previous = inode.getObject();
                actual = inode.next.getObject();
                if((previous.toString().compareTo(actual.toString()) > 0))
                {
                    this.remove(previous);
                    this.insertTail(previous);
                }
                inode = inode.next;
            }
            cont++;
        }while(cont < size);
        return null;
    }

    @Override
    public Iterator<ListNode> iterator() {
        inode = head;
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return inode.next != null;
            }

            @Override
            public ListNode next() {
                if (inode != tail) {
                    ListNode tmp = inode;
                    inode = inode.next;
                    return tmp;
                } else {
                    return null;
                }
            }
        };
    }

    @Override
    public String toString() {
        inode = head;
        String string = "";
        for (int i=0; i<size; i++){
            string += "ListNode{" + "object=" + inode.getObject() + "," + "next=";
            if (i+1 == size){
                string+=inode.next.getObject()+"}";
            }
            inode = inode.next;
        }
        return string;
    }
}