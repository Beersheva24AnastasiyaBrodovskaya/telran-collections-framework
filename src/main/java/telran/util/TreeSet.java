package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
@SuppressWarnings("unchecked")
public class TreeSet<T> implements Set<T> {
    private static class Node<T> {
        T obj;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        Node(T obj) {
            this.obj = obj;
        }
    }

    private class TreeSetIterator implements Iterator<T> {
        Node<T> cur = null;
        Node<T> prev = null;

        TreeSetIterator(){
            cur = getMinNode(root);
        }

        private void setNextCurrent(){
            if(cur.right == null){
                cur = getGreatParent(cur);
            } else {
                cur = getMinNode(cur.right);
            }
        }

        private Node<T> getGreatParent(Node<T> node){
            Node<T> res = node;
            while (res.parent != null && res.parent.right == res){
                res = res.parent;
            }
            return res.parent;
        }
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            prev = cur;
            T obj = cur.obj;
            setNextCurrent();

            return obj;

        }

        @Override
        public void remove() {
            if (prev == null){
                throw new IllegalStateException();
            }
            removeNode(prev);
            prev = null;
        }
    }

    private Node<T> root;
    private Comparator<T> comparator;
    int size;
    public TreeSet(Comparator<T> comparator) {
        this.comparator = comparator;
    } 
    
    public TreeSet() {
        this((Comparator<T>)Comparator.naturalOrder());
    }
    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            Node<T> node = new Node<>(obj);
            if(root == null) {
                addRoot(node);
            } else {
                addAfterParent(node);
            }
            size++;

        }
        return res;
    }

    private void addAfterParent(Node<T> node) {
        Node<T> parent = getParent(node.obj);
        if(comparator.compare(node.obj, parent.obj) > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        node.parent = parent;
    }

    private void addRoot(Node<T> node) {
        root = node;
    }

    @Override
    public boolean remove(T pattern) {
        boolean res = false;
        Node <T> node = getNode(pattern);
        if (node != null){
            res = true;
            removeNode(node);
        }
        return res;
    }

    private void removeNode(Node<T> node){
        if(node.left == null || node.right == null){
            removeWithoutChildren(node);
        } else {
            removeWithChildren(node);
        }
        size--;
    }

    private void removeWithoutChildren(Node <T> node){
        Node<T> child = node.left == null ? node.right : node.left;
        if (node==root){
            addRoot(child);
        }else {
            if(node.parent.left == node){
                node.parent.left = child;
            } else {
                node.parent.right = child;
            }
        }
        if (child != null){
            child.parent = node.parent;
        }
    }


    private void removeWithChildren(Node<T> node){
        Node<T> maxNode = getMaxNode(node.left);
        node.obj = maxNode.obj;
        removeWithoutChildren(maxNode);
    }

    private Node<T> getMinNode(Node<T> node){
        Node<T> cur = node;
        while(cur.left != null){
            cur = cur.left;
        }
        return cur;
    }
    private Node<T> getMaxNode(Node<T> node){
        Node<T> cur= node;
        while (cur.right != null){
            cur = cur.right;
        }
        return cur;
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T obj) {
        
        Node<T> res = getNode(obj);
        return res != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeSetIterator();
    }

    @Override
    public T get(Object pattern) {
        T res = null;
        T patternT = (T) pattern;
        if (contains(patternT)){
            res = getNode(patternT).obj;
        }
        return res;
    }
    private Node<T> getParentOrNode(T pattern) {
        Node<T> current = root;
        Node<T> parent = null;
        int compRes = 0;
        while(current != null && (compRes = comparator.compare(pattern, current.obj)) != 0) {
            parent = current;
            current = compRes > 0 ? current.right : current.left;
        }
        return current == null ? parent : current;
    }
    private Node<T> getNode(T pattern) {
        Node<T> res = getParentOrNode(pattern);
        if(res != null) {
            int compRes = comparator.compare(pattern, res.obj);
            res = compRes == 0 ? res : null;
        }
        
        return res;

    }
    private Node<T> getParent(T pattern) {
        Node<T> res = getParentOrNode(pattern);
        int compRes = comparator.compare(pattern, res.obj);
        return compRes == 0 ? null : res;

    }

}