package fj.match;

import fj.F0;

/**
 * Created by MarkPerry on 29 Dec 15.
 */
public class ValueMatcher<A, B> {

    final A value;

    private ValueMatcher(A a) {
        value = a;
    }

    public ValueMatcher<A, B> create(A a) {

        return null;
    }

    public static <A, B> ValueMatcher<A, B> match(A a) {
        return null;
    }


    public B match() {
        return null;
    }

}
