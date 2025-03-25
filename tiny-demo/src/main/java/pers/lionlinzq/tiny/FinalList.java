package pers.lionlinzq.tiny;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class FinalList<E> extends AbstractList<E> {

    private final List<E> finalList;

    public FinalList(List<E> source) {
        this.finalList = new ArrayList<>(source);
    }


    @Override
    public E get(int index) {
        return finalList.get(index);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E set(int index, E element) {
        return finalList.set(index, element);
    }
}
