package concurrency;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableSet<E> extends ForwardingSet<E> {
    public ObservableSet(Set<E> set) { super(set);}

    private final List<SetObserver<E>> observers = new ArrayList<>();

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers){
            observers.add(observer);
        }
    }
    public void removeObserver(SetObserver<E> observer) {
        synchronized (observers){
            observers.remove(observer);
        }
    }
    private void notifyElementAdded(E element) {
        synchronized (observers){
            for(SetObserver<E> observer : observers){
                observer.added(this, element);
            }
        }
    }

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added)
            notifyElementAdded(element);
        return added;
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res = false;
        for(E element : c){
            res |= add(element);
        }
        return res;
    }

    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver((s, e) -> System.out.println("Observer 1: Element " + e + " added to set "));
        SetObserver<Integer> sndObserver = (s, e) -> System.out.println("Observer 2: Element " + e + " added to set ");
        set.addObserver(sndObserver);
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println("Observer 3: Element " + e + " added to set ");
                if (e == 23)
                    s.removeObserver(this);
            }
        });
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println("Observer 3: Element " + e + " added to set ");
                if (e == 20) {
                    ExecutorService exec = Executors.newSingleThreadExecutor();
                    try {
                        exec.submit(()->s.removeObserver(this)).get();
                    } catch (ExecutionException | InterruptedException e1) {
                        e1.printStackTrace();
                    } finally {
                        exec.shutdown();
                    }
                    s.removeObserver(this);
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
            if (i==50)
                set.removeObserver(sndObserver);
        }
    }
}
