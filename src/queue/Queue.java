package queue;

import java.util.Arrays;

/**
 *
 * @author Mieke Narjes
 * @author Uwe Krause
 */
public class Queue<T> {

    /**
     * Deklarationen
     */
    private Object[] storage;
    private final int capacity;

    private int size;
    private int begin;

    /**
     * Default-Kapazitaet, wenn kein Argument uebergeben
     */
    private final int DEFAULTSIZE = 10;

    /**
     * Konstruktor
     *
     * @param arg_capacity
     */
    // Object[arg] --> arg legt die Laenge des Arrays fest
    public Queue(int arg_capacity) {
        this.storage = new Object[arg_capacity];
        this.capacity = arg_capacity;

    }

    /**
     * Konstruktor mit default-KOSTANTE, falls keine Groesse uebergeben wurde
     */
    public Queue() {
        this.storage = new Object[DEFAULTSIZE];
        this.capacity = DEFAULTSIZE;
    }

    /**
     * size wird bei jedem enqueue aktualisiert Exception siehe:
     * https://docs.oracle.com/javase/7/docs/api/java/lang/IndexOutOfBoundsException.html
     */
    public void enqueue(T obj) {

        if (this.isFull()) {
            throw new IndexOutOfBoundsException("Die Kapazitaet der Queue ist erreicht!");
        }

        // naechste freie Position
        // (erster + anzahl) % Kapazitaet
        int neue_position = this.calcFirstFreePos();

        this.storage[neue_position] = obj;

        this.size++;

    }

    private int calcFirstFreePos() {

        return (calcLastFilledPos() + 1) % this.capacity;
    }

    private int calcLastFilledPos() {

        return (this.begin + this.size - 1) % this.capacity;
    }

    /**
     *
     * @return
     */
    public T dequeue() {

        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("Die Queue ist bereits leer");
        }

        /**
         * Reihenfolge dieser Operationen SUPER Wichtig!
         *
         * (1) Objekt zwischenspeichern,
         *
         * (2) Objekt an alter Position loeschen (null)
         *
         * (3) Zeiger auf neue Position setzen
         *
         * (4) Groesse des Datenfeldes anpassen
         *
         */
        // greife erstes Objekt
        Object obj = this.storage[this.begin];

        // ueberschreibe Inhalt alter Position mit null
        this.storage[this.begin] = null;

        // setze den Beginn des Datenfeldes plus 1
        this.begin = (this.begin + 1) % this.size;

        // reduziere Groesse
        this.size--;

        // gib Objekt zurueck
        return (T) obj;

    }

    public int length() {
        return this.size;
    }

    public int showCapacity() {
        return this.capacity;
    }

    public boolean isEmpty() {

        return this.size == 0;
    }

    public boolean isFull() {

        return this.size >= this.capacity;
    }

    /**
     * @return String Textdarstellung der Queue
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Storage: ");

        sb.append(Arrays.deepToString(this.storage));

        return sb.toString();
    }
}
