import java.util.AbstractList;
import java.util.Iterator;
import java.util.LinkedList;

public class ForwardLinkedList<E> extends AbstractList<E> {
    Node<E> head;
    int capacity;

    public ForwardLinkedList() {
        head = null;
        capacity = 0;
    }

    @Override
    public E get(int index) {
        return null;
    }

    class Node<E> {
        E data;
        Node<E> next;

        Node(E d) {
            data = d;
            next = null;
        }
    }

    @Override
    public void clear() {
        this.head = null;
        capacity = 0;
    }

    @Override
    public int size() {
        Node node = head;
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<E> {
        Node<E> current=head;

        @Override
        public boolean hasNext() {
            return (current.next != null);
        }

        @Override
        public E next() {
            if (current.next != null)
                return current.next.data;
            else
                return null;
        }
    }

    public void addFirst(E element) {
        Node<E> new_node = new Node(element);
        new_node.next = this.head;
        this.head = new_node;
        capacity = capacity + 1;
    }

    public void addLast(E element) {
        if (capacity == 0) {
            addFirst(element);
        } else {
            Node<E> new_node = new Node(element);
            new_node.next = null;
            Node<E> last = this.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;
            capacity = capacity + 1;
        }
    }

    public void removeFirst() {
        if (this.head != null)
            this.head = this.head.next;
        capacity = capacity - 1;
    }

    public void removeLast() {
        if (this.head.next != null) {
            Node last = this.head;
            while (last.next.next != null) {
                last = last.next;
            }
            last.next = null;
            capacity = capacity - 1;
        } else {
            removeFirst();
        }
    }

    public E getFirst() {
        return this.head.data;
    }

    public E getLast() {
        E o = null;
        Node<E> last = this.head;
        if (capacity == 1)
            o = getFirst();
        else {
            if (capacity == 0) return null;
            if (this.head.next != null) {

                while (last.next != null) {
                    last = last.next;
                }
                o = last.data;
            }
        }
        return o;
    }

    public E search(E element) {
        E o = null;
        Node<E> last = this.head;
        if (capacity != 0) {
            while (last.next != null) {
                if (element.equals(last.data)) {
                    o = last.data;
                    break;
                }
                last = last.next;
            }
        }
        return o;
    }

    @Override
    public boolean remove(Object element) {
        Node<E> node = head;

        if (node == null) {
            return false;
        }
        if (node.data.equals(element)) {
            head = head.next;
        } else {
            while (node.next != null) {
                if (node.next.data.equals(element)) {
                    node.next = node.next.next;
                    capacity = capacity - 1;
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = null;
        if (capacity == 0) {
            return "Список порожний";
        }
        sb = new StringBuilder();
        Node currNode = this.head;
        sb.append("[");
        while (currNode != null) {
            if (currNode.next != null) {
                sb.append(currNode.data + ", ");
                currNode = currNode.next;
            } else {
                sb.append(currNode.data);
                currNode = currNode.next;
            }
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        ForwardLinkedList l = new ForwardLinkedList();
        l.addLast("A");
        System.out.println("0  " + l.toString() + " " + l.size());
        l.addLast("B");
        System.out.println("0  " + l.toString() + " " + l.size());
        l.addLast(null);
        System.out.println("0  " + l.toString() + " " + l.size());
        l.addLast(null);
        System.out.println("0  " + l.toString() + " " + l.size());
        l.addFirst("D");
        System.out.println("0  " + l.toString() + " " + l.size());
        l.addLast("Z");
        System.out.println("0  " + l.toString() + " " + l.size());
        System.out.println("2  " + l.toString() + " " + l.size());
        l.getFirst();
        l.getLast();
        System.out.println(l.toString());
        l.addLast("R");
        System.out.println(l.toString());
        l.addFirst("G");
        System.out.println(l.toString());
        System.out.println(l.toString());
        l.removeLast();
        System.out.println(l.toString());
        l.clear();
        System.out.println(l.toString());
     }
}
