package javaslang.collection;

import fj.Equal;
import fj.Hash;
import fj.data.*;
import fj.data.Stream;

/**
 * Created by MarkPerry on 3 Jul 16.
 */
public class HAMTUsage {

    public static void jlHamt(Stream<Integer> s) {
        HashArrayMappedTrie<Integer, Integer> h = HashArrayMappedTrie.empty();

        HashArrayMappedTrie<Integer, Integer> h2 = s.foldLeft((acc, i) -> {
            return h.put(i, i);
//            return acc;
        }, h);

    }
}
