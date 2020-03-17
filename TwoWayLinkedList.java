import java.util.Iterator;

/* Struktura danych: gąsienica cykliczna
*  Autor: Grzegorz Myjak
 */

public class TwoWayLinkedList <T> implements Iterable<T>
{
    private class Element
    {
        private T value;
        private Element next;
        private Element previous;

        public Element (T value)
        {
            this.value = value;
        }

        public Element getNext()
        {
            return next;
        }

        public Element getPrevious()
        {
            return previous;
        }

        public T getValue()
        {
            return value;
        }

        public void setNext(Element e)
        {
            next = e;
        }

        public void setPrevious(Element e)
        {
            previous = e;
        }

        public void insertAfter(T value)
        {
            Element temp = new Element(value);
            temp.setPrevious(this);
            temp.setNext(getNext());
            getNext().setPrevious(temp);
            setNext(temp);
        }

        public void insertBefore(T value)
        {
            Element temp = new Element(value);
            temp.setPrevious(getPrevious());
            temp.setNext(this);
            getPrevious().setNext(temp);
            setPrevious(temp);
        }

        public T remove()
        {
            getPrevious().setNext(getNext());
            getNext().setPrevious(getPrevious());
            return getValue();
        }
    }

    private Element sentinel;

    public TwoWayLinkedList()
    {
        sentinel = new Element(null);
        sentinel.setNext(sentinel);
        sentinel.setPrevious(sentinel);
    }

    public void addHead(T value)
    {
        sentinel.insertAfter(value);
    }

    public void addTail(T value)
    {
        sentinel.insertBefore(value);
    }

    public T getHead()
    {
        return sentinel.getNext().getValue();
    }

    public T getTail()
    {
        return sentinel.getPrevious().getValue();
    }

    public T cutTail()
    {
        return sentinel.getPrevious().remove();
    }

    @Override
    public Iterator<T> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator
    {
        private Element current;

        public ListIterator()
        {
            current = sentinel;
        }

        @Override
        public boolean hasNext()
        {
            if (current.getNext().getValue() != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        @Override
        public T next()
        {
            current = current.getNext();
            return current.getValue();
        }
    }
}
