package fj.data.hamt;

/**
 * Created by MarkPerry on 7 Jul 16.
 */
public class ValueNode<K, V> {
    K key;
    V value;
    int nodeHash;
    int remainderHash;

    public ValueNode(K key, V value, int remainderHash, int nodeHash) {
        this.key = key;
        this.value = value;
        this.nodeHash = nodeHash;
        this.remainderHash = remainderHash;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public int getNodeHash() {
        return nodeHash;
    }

    public int getRemainderHash() {
        return remainderHash;
    }

    public static <K, V> ValueNode<K, V> valueNode(K k, V v, int remainingHash, int nodeHash) {
        return new ValueNode<>(k, v, remainingHash, nodeHash);
    }
}
