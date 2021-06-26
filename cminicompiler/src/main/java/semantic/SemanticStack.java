package semantic;

import java.util.ArrayList;
import java.util.Stack;

public class SemanticStack<T> extends Stack<T> {

    private Integer basePointer, stackPointer;

    public SemanticStack() {
        this.basePointer = this.stackPointer = 0;
    }

    public void pushElements(T... arr) {

        if (arr == null)
            return;

        for (T o : arr)
            this.push(o);

        this.stackPointer += arr.length;
    }

    public ArrayList<T> findLastestElementsUntil(T o) {

        Integer sliceSize = this.search(o) - 1;

        if (sliceSize == -1)
            return null;

        ArrayList<T> elements = new ArrayList<T>();
        while (sliceSize-- >= 0) {
            elements.add(this.pop());
        }

        return elements;
    }

    public Integer getBasePointer() {
        return basePointer;
    }

    public void setBasePointer(Integer basePointer) {
        this.basePointer = basePointer;
    }

    public Integer getStackPointer() {
        return stackPointer;
    }

    public void setStackPointer(Integer stackPointer) {
        this.stackPointer = stackPointer;
    }
}