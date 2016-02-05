package fj.match;

import fj.Bottom;
import fj.F;
import fj.F1Functions;
import fj.F1W;
import fj.data.Option;
import fj.data.Validation;

import static fj.F1W.lift;

/**
 * Created by MarkPerry on 29 Dec 15.
 */
public class Case<A, B> {

    final F<A, Boolean> guard;
    final F<A, B> expression;

    private Case(F<A, Boolean> g, F<A, B> e) {
        guard = g;
        expression = e;
    }

    public static <A, B> Case<A, B> createCase(F<A, Boolean> g, F<A, B> e) {
        return new Case(g, e);
    }

    public boolean isMatch(A a) {
        return guard.f(a);
    }

    public Validation<MatchError, B> matchValidation(A a) {
        if (!isMatch(a)) {
            return Validation.fail(error(a));
        } else {
            return Validation.success(expression.f(a));
        }
    }

    public static <A> MatchError error(A a) {
        return MatchError.error("Did not match on value: " + a);
    }

    public B match(A a) {
        if (!isMatch(a)) {
            throw error(a);
        } else {
            return expression.f(a);
        }
    }

    public Option<B> matchOption(A a) {
        return matchValidation(a).toOption();
    }

    public <C> Case<A, C> map(final F<B, C> f) {
        return createCase(guard, lift(expression).andThen(f));
    }

    public <C> Case<A, C> bind(final F<B, Case<A, C>> f) {
        return createCase(guard, a -> {
            B b = expression.f(a);
            return f.f(b).match(a);
        });
    }

}
