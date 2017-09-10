/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Mieke Narjes
 * @author Uwe Krause
 */
public class QueueTest {

    public QueueTest() {
    }

    /**
     * Testet erwartetes Verhalten beim Hinzufuegen von Elementen
     */
    @Test
    public void testEnqueue() {

        Queue instance = new Queue();
        instance.enqueue("Eins");

        assertEquals(1, instance.length());

        instance.enqueue("Zwei");
        instance.enqueue("Drei");
        instance.enqueue("Vier");
        instance.enqueue("Fuenf");

        assertEquals(5, instance.length());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEnqueueThrowsExceptionWhenTooManyItems() {

        Queue instance = new Queue(2);
        instance.enqueue("Eins");
        instance.enqueue("Zwei");
        // ein Element zu viel!
        instance.enqueue("Drei");
    }

    /**
     * Fuege mehere Elemente hinzu und entferne anschliessend
     */
    @Test
    public void testDequeue() {
        Queue instance = new Queue(5);

        instance.enqueue("Eins");
        instance.enqueue("Zwei");

        instance.enqueue("Drei");

        instance.dequeue();
        assertEquals(2, instance.length());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsExceptionWhenEmptyButTryToGetElementsAnyway() {

        Queue instance = new Queue(2);
        instance.enqueue("Eins");

        instance.dequeue();

        instance.dequeue();

    }

    /**
     * Testet das korrekte Zaehlen von Elementen in der Klasse
     */
    @Test
    public void testLength() {
        Queue instance = new Queue();
        instance.enqueue("Eins");

        assertEquals(1, instance.length());

        instance.enqueue("Zwei");
        instance.enqueue("Drei");

        assertEquals(3, instance.length());

        instance.enqueue("Vier");
        instance.enqueue("Fuenf");

        assertEquals(5, instance.length());

        instance.dequeue();

        assertEquals(4, instance.length());

        instance.dequeue();

        assertEquals(3, instance.length());

        instance.dequeue();
        instance.dequeue();
        instance.dequeue();

        assertEquals(0, instance.length());

    }

    /**
     * Testet, ob Kapazitaet korrekt gesetzt wird
     */
    @Test
    public void testShowCapacity() {

        int expResult = 15;

        Queue instance = new Queue(expResult);

        int result = instance.showCapacity();
        assertEquals(expResult, result);

    }

    /**
     * Queue Leer, wenn initialisiert?
     */
    @Test
    public void testIsEmptyAtInitialize() {
        Queue instance = new Queue(5);

        /**
         * Bei Initialisierung leer
         */
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

    }

    /**
     * Queue leer, wenn gleiche Anzahl entfernt wird, wie vorher hinzugefuegt?
     */
    @Test
    public void testIsEmptyAfterOperations() {

        Queue instance = new Queue(5);

        instance.enqueue("Eins");
        instance.enqueue("Zwei");

        instance.dequeue();
        instance.dequeue();

        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Abfrage, ob Queue voll ist
     */
    @Test
    public void testIsFull() {

        Queue instance = new Queue(2);

        instance.enqueue("Eins");
        instance.enqueue("Zwei");

        boolean expResult = true;
        boolean result = instance.isFull();
        assertEquals(expResult, result);

    }

    /**
     * Liefert die ToString Methode das erwartete Ergebnis?
     */
    @Test
    public void testToString() {

        Queue instance = new Queue(2);

        instance.enqueue("Eins");
        instance.enqueue("Zwei");

        String expResult = "Storage: [Eins, Zwei]";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

}
