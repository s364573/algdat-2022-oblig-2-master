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

        fratilKontroll(antall,fra,til);

        Liste<T> resultat = new DobbeltLenketListe<>();
        Node<T> fraNode;
        fraNode = finnNode(fra);
        int lengde = til-fra;

        while (lengde > 0){
            resultat.leggInn(fraNode.verdi);
            fraNode = fraNode.neste;
            lengde--;
        }


        return resultat;

    }

    //hjelpemetode
    private void fratilKontroll(int lengde,int fra, int til){
        if (fra < 0 || til > lengde){
            throw new IndexOutOfBoundsException();
        }
        if (fra > til){
            throw new IllegalArgumentException();
        }
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
        else{
            return false;
        }
    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi);
        Node<T> nyNode = new Node<>(verdi,hale,null);

        if (tom()){//Lager nytt hode hvis tom
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
        // Sjekk for null
        Objects.requireNonNull(verdi, "Verdien kan ikke være et null-objekt");


        //Sjekker om indeks ikke er OutOfBounds
        if (indeks < 0) {
            throw new IndexOutOfBoundsException("Indeks " + indeks + " er negativ!");
        }
        else if (indeks > antall){
            throw new IndexOutOfBoundsException("Indeks " + indeks + " > antall(" + antall + ") noder!");
        }



        //Bruker denne om listen er tom
        if (antall == 0) {
            hode = new Node<T>(verdi, null, null);
            hale = hode;

        }
        //Legges til først i listen
        else if (indeks == 0) {
            hode = new Node<T>(verdi, null, hode);
            hode.neste.forrige = hode;
        }
        // Legges sist i listen
        else if (indeks == antall) {
            hale = new Node<T>(verdi, hale, null);
            hale.forrige.neste = hale;
        }
        //Går gjennom med for-loop og finner posisjon til indeks og legger inn node
        else {
            Node<T> tempNode = hode;
            for (int i = 0; i < indeks; i++) {
                tempNode = tempNode.neste;
            }
            tempNode = new Node<T>(verdi, tempNode.forrige, tempNode);
            tempNode.neste.forrige = tempNode.forrige.neste = tempNode;
        }

        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {

        if (indeksTil(verdi) != -1){
            return true;
        }
        else {
            return false;
        }
    }

    //Hjelpemetode
    private Node<T> finnNode(int indeks){

        //Lager en variabel som skiller mellom sidene indekssøket skal skje
        int side = antall/2;
        Node<T> temp;

        //Hvis indeks er under halvparten av lengden vil vi starte fra hode og gå bakover mot halen
        if (indeks < side){
            temp = hode;
            for (int i = 0; i< indeks;i++){
                temp = temp.neste;
            }
            return temp;
        }
        else { //Hvis indeks er over halvparten av lengen vil vi starte fra halen og gå framover mot hode
            temp = hale;
            for (int i = antall-1; i > indeks;i--){
                temp = temp.forrige;
            }
            return temp;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);
        Node<T> temp;
        temp = finnNode(indeks);
        return temp.verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node<T> temp;
        temp = hode;
        for (int i = 0; i < antall; i++){ //looper gjennom hver node og sjekker verdien opp mot input
            if (temp.verdi.equals(verdi)){
                return i;
            }
            temp = temp.neste;
        }
        //Hvis inputverdien er null eller ikke i lista vil det returnere -1
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks,false);
        //Sjekker at nyverdi ikke null
        Objects.requireNonNull(nyverdi);

        Node<T> temp = finnNode(indeks); //finner indeks og legger til node

        T gammelVerdi = temp.verdi;
        endringer++;
        temp.verdi = nyverdi;

        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        if (verdi == null) {
            return false;
        }

        Node<T> temp = hode;

        //Fjerner første node i listen
        if (verdi.equals(temp.verdi)) {
            if (temp.neste != null) {
                hode = temp.neste;
                hode.forrige = null;
            } else {
                hode = null;
                hale = null;
            }
            antall--;
            endringer++;
            return true;
        }

        //Fjerner siste node i listen
        temp = hale;
        if (verdi.equals(temp.verdi)) {
            hale = temp.forrige;
            hale.neste = null;
            antall--;
            endringer++;
            return true;
        }
        //Fjerne node immellom
        temp = hode.neste;
        for (; temp!= null; temp = temp.neste) {
            if (verdi.equals(temp.verdi)) {

                //Gjør at nodene til begge sider av noden som skal fjernes nå peker mot hverandre
                temp.forrige.neste = temp.neste;
                temp.neste.forrige = temp.forrige;

                antall--;
                endringer++;
                return true;
            }
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks,false);

        Node<T> temp = hode;
        T verdi;

        //Fjerne første node i listen
        if (indeks == 0){
            verdi = temp.verdi;

            if (temp.neste != null){
                hode = temp.neste;
                hode.forrige = null;
            }
            else {
                hode = null;
                hale = null;
            }
        }

        //Fjerne siste noden i listen
        else if (indeks == antall-1){
            temp = hale;
            verdi = hale.verdi;

            hale = temp.forrige;
            hale.neste = null;
        }


        //Fjerne noden imellom
        else {
            for (int i = 0; i < indeks; i++){
                temp = temp.neste;
            }
            verdi = temp.verdi;

            //Gjør at nodene til begge sider av noden som skal fjernes nå peker mot hverandre
            temp.forrige.neste = temp.neste;
            temp.neste.forrige = temp.forrige;
        }

        antall--;
        endringer++;
        return verdi;
    }

    @Override
    public void nullstill() {

        Node<T> temp;
        temp = hode;
        for(; temp !=null; temp = temp.neste){
            temp.verdi = null;
            temp.forrige = null;
            temp.neste = null;
        }

        hode = null;
        hale = null;
        antall = 0;
        endringer++;


        //Kan bruke denne metoden også men den er treigere enn metode 1
        /*

        for (Node<T> temp1 = hode; temp1 !=null; temp1 = temp1.neste){
            fjern(0);
        }
        */
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


