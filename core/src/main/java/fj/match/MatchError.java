package fj.match;

/**
 * Created by MarkPerry on 29 Dec 15.
 */
public class MatchError extends Error {


    public MatchError() {
        super();
    }

    public MatchError(String var1) {
        super(var1);
    }

    public MatchError(String var1, Throwable var2) {
        super(var1, var2);
    }

    public MatchError(Throwable var1) {
        super(var1);
    }

    public static MatchError error(final String s) {
        return new MatchError(s);
    }

}
