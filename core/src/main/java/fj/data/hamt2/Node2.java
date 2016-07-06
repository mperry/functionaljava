package fj.data.hamt2;

import fj.F;
import fj.P2;
import fj.Show;
import fj.data.Either;
import fj.data.Option;
import fj.data.Stream;

/**
 * A Hash Array Mapped Trie node that is either a key-value pair or a
 * Hash Array Mapped Trie.
 *
 * Created by maperr on 31/05/2016.
 */
public final class Node2<K, V> {

    private final Either<P2<K, V>, HashArrayMappedTrie2<K, V>> either;

    public Node2(final Either<P2<K, V>, HashArrayMappedTrie2<K, V>> e) {
        either = e;
    }

    public Node2(final P2<K, V> simpleNode) {
        this(Either.left(simpleNode));
    }

    public Node2(final HashArrayMappedTrie2<K, V> hamt) {
        this(Either.right(hamt));
    }

    public static <K, V> Node2<K, V> p2Node(final P2<K, V> p) {
        return new Node2<>(p);
    }

    public static <K, V> Node2<K, V> hamtNode(final HashArrayMappedTrie2<K, V> hamt) {
        return new Node2<>(hamt);
    }

    /**
     * Returns the optional value based on a pattern match.
     */
    public Option<V> find(final F<P2<K, V>, Option<V>> f, final F<HashArrayMappedTrie2<K, V>, Option<V>> g) {
        return match(p -> f.f(p), hamt -> g.f(hamt));
    }

    /**
     * Performs a reduction on this Node2 using the given arguments.
     */
    public <B> B match(final F<P2<K, V>, B> f, final F<HashArrayMappedTrie2<K, V>, B> g) {
        return either.either(p -> f.f(p), hamt -> g.f(hamt));
    }

    public Stream<P2<K, V>> toStream() {
        return match(p -> Stream.single(p), h -> h.toStream());
    }

    public String toString() {
        return Show.anyShow().showS(this);
//        return Show.hamtNodeShow(Show.<K>anyShow(), Show.<V>anyShow()).showS(this);
    }

}
