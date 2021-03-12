package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        List filas = new List();
        String s ;
        boolean paredFinal = false;

        int cont = 0;
        do{
            Scanner entradaEscaner = new Scanner (System.in);
            s = entradaEscaner.nextLine ();
            filas.add(s);
            if (cont!=0) {
                for (int i = 0; i < s.length(); i++) {
                    {
                        if (s.charAt(i) == '0' || s.charAt(i) == 'm' || s.charAt(i) == 'e') {
                            paredFinal = false;
                            break;
                        } else {
                            paredFinal = true;
                        }
                    }
                }
            }
            cont++;
        }while(paredFinal != true);

        //Identificacion de e y m

        ListNode l = new ListNode(), nodom = new ListNode(), nodoe = new ListNode();
        l = filas.head;
        int contFilas = 0, contColumnas, cantidadElementos = 0;
        int Filam = 0, Columnam = 0;
        int Filae = 0, Columnae = 0;
        boolean hemosEncontradom = false;
        boolean hemosEncontradoe = false;
        for (int i =0; i<filas.getSize(); i++){
            contColumnas = 0;
            cantidadElementos = l.getObject().toString().length();
            for (int j =0; j<cantidadElementos; j+=2){
                if (l.getObject().toString().charAt(j) == 'm'){
                    Filam = contFilas;
                    Columnam = contColumnas;
                    nodom = l;
                    hemosEncontradom = true;
                }
                else if(l.getObject().toString().charAt(j) == 'e'){
                    Filae = contFilas;
                    Columnae = contColumnas;
                    nodoe = l;
                    hemosEncontradoe = true;
                }
                contColumnas++;
            }
            if (hemosEncontradoe==true && hemosEncontradom==true){
                break;
            }
            contFilas++;
            l = l.next;
        }
        System.out.println("Coodenadas de m: "+ '(' + Filam + ", " +Columnam + ')');
        System.out.println("Coodenadas de e: "+ '(' + Filae + ", " + Columnae + ')' + "\n");

        //Busqueda de al menos una ruta posible

        boolean hemosLlegadoe = false;

        ListNode arriba = new ListNode(), abajo = new ListNode(), horizontal = new ListNode();
        arriba = nodom.previous;
        abajo = nodom.next;
        horizontal = nodom;
        int coordenadaFila = Filae, coordenadaColumna = Columnae;
        for (int i=0; i<cantidadElementos; i+=2){
            if (abajo.getObject().toString().charAt(i) == 'e'){
                coordenadaFila = Filam+(i/2);
                System.out.println("Hemos encontrado la forma de llegar a e: " + '(' + Filam + ", " + Columnam + ") + (" + coordenadaFila + ", " + coordenadaColumna + ')');
                break;
            }
            else if (arriba.getObject().toString().charAt(i) == 'e'){
                coordenadaFila = Filam-(i/2);
                System.out.println("Hemos encontrado la forma de llegar a e: " + '(' + Filam + ", " + Columnam + ") + (" + coordenadaFila + ", " + coordenadaColumna + ')');
                break;
            }
            else if (horizontal.getObject().toString().charAt(i) == 'e'){
                if (Columnae > Columnam){
                    coordenadaColumna = Columnam+1;
                    System.out.println("Hemos encontrado la forma de llegar a e: " + '(' + Filam + ", " + Columnam + ") + (" + coordenadaFila + ", " + coordenadaColumna + ')');
                    break;
                }
                else{
                    coordenadaColumna = Columnam-1;
                    System.out.println("Hemos encontrado la forma de llegar a e: " + '(' + Filam + ", " + Columnam + ") + (" + coordenadaFila + ", " + coordenadaColumna + ')');
                    break;
                }

            }

        }
    }
}
