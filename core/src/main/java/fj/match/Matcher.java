package fj.match;

import fj.data.List;

/**
 * Created by MarkPerry on 29 Dec 15.
 */
public class Matcher<A, B> {

    List<Case<A, B>> cases;

    private Matcher(List<Case<A, B>> list) {
        cases = list;
    }

    public static <A, B> Matcher<A, B> create() {
        return new Matcher<A, B>(List.nil());
    }

}
