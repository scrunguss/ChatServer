package server;

public class Triple<K,V,I>{

    public final K key;
    public final V value;
    public final I id;

    public Triple(K key, V value, I id){
        this.key = key;
        this.value = value;
        this.id = id;
    }

}