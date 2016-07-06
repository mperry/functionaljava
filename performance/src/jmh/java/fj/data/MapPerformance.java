package fj.data;

import fj.Equal;
import fj.Hash;
import fj.LcgRng;
import fj.Ord;
import fj.P;
import fj.data.hamt.HashArrayMappedTrie;
import fj.data.hamt2.HashArrayMappedTrie2;
import javaslang.collection.HAMTUsage;
import org.openjdk.jmh.annotations.Benchmark;

/**
 * Created by MarkPerry on 2 Jul 16.
 */
public class MapPerformance {

    public static final int n = 10000000;
    public static final Stream<Integer> s = LcgRng.naturalIntStream().take(n);

    @Benchmark
    public void treeMap() {
        TreeMap<Integer, Integer> t = s.foldLeft((acc, i) -> acc.set(i, i), TreeMap.empty(Ord.intOrd));
    }

    @Benchmark
    public void javaHashMap() {
        s.foldLeft((acc, i) -> {
            acc.put(i, i);
            return acc;
        }, new java.util.HashMap<Integer, Integer>());
    }

    @Benchmark
    public void fjHashMap() {
        s.foldLeft((acc, i) -> {
            acc.set(i, i);
            return acc;
        }, HashMap.hashMap(Equal.intEqual, Hash.intHash));
    }

    @Benchmark
    public void trie() {
        s.foldLeft((acc, i) -> acc.set(i, i), HashArrayMappedTrie.<Integer>emptyKeyInteger());
    }

    @Benchmark
    public void trie2() {
        s.foldLeft((acc, i) -> acc.set(i, i), HashArrayMappedTrie2.<Integer>emptyKeyInteger());
    }

    @Benchmark
    public void javaslang() {
        HAMTUsage.jlHamt(s);

    }

    public static void main(String args[]) {
        new MapPerformance().trie();
    }

}
