package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = null;
        hale = null;

        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {  //Konstruktør

        if (a == null) {
            throw new NullPointerException();
        }


        if (a.length > 0) {
            int i = 0;
            for (; i < a.length; i++) { //Finner første element i lista som ikke er null og lager ett hode
                if (a[i] != null) {

                    hode = new Node<>(a[i]);
                    antall++;
                    break;
                }
            }

            hale = hode;
            //Lager resten av listen
            i++;
            for (; i < a.length; i++) {
                if (a[i] != null) {
                    hale.neste = new Node<>(a[i], hale, null);
                    hale = hale.neste;
                    antall++;
                }
            }

        }
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {

        if (hode == null){
            return true;
        }
        return false;

    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi);
        Node<T> nyNode = new Node<T>(verdi,hale,null);

        if (tom()){
            hode = nyNode;
            hale = hode;
            antall++;
            endringer++;
        }
        else{
            hale.neste = nyNode;
            hale = hale.neste;
            antall++;
            endringer++;
        }
        return true;

    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        //Hvis liste tom returner tom String
        if (tom()) return "[]";
        Node<T> tempNode = hode;

        //Bruker StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("[");


        sb.append(tempNode.verdi);
        tempNode = tempNode.neste;

        //Looper gjennom hver av nodene og lagger til alle som ikke er null
        while (tempNode!=null){
            sb.append(", ");
            sb.append(tempNode.verdi);
            tempNode = tempNode.neste;
        }
        sb.append("]");
        return sb.toString();
    }

    public String omvendtString() {
        //Hvis liste tom returner tom String
        if (tom()) return "[]";
        Node<T> tempNode = hale;

        //Bruker StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        sb.append(tempNode.verdi);
        tempNode = tempNode.forrige;

        //Looper gjennom hver av nodene og lagger til alle som ikke er null
        while (tempNode!=null){
            sb.append(", ");
            sb.append(tempNode.verdi);
            tempNode = tempNode.forrige;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


