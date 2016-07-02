package fj;

import fj.data.Option;
import fj.data.Stream;

/**
 * Created by MarkPerry on 7/07/2014.
 *
 * https://en.wikipedia.org/wiki/Linear_congruential_generator
 */
public class LcgRng extends Rng {

	private final Long seed;

    public LcgRng() {
        this(System.currentTimeMillis());
    }

	public LcgRng(long s) {
		seed = s;
	}

    public final long getSeed() {
        return seed;
    }

	public final P2<Rng, Integer> nextInt() {
        P2<Rng, Long> p = nextLong();
        int i = (int) p._2().longValue();
        return P.p(p._1(), i);
	}


	public final P2<Rng, Long> nextLong() {
        P2<Long, Long> p = nextLong(seed);
        return P.p(new LcgRng(p._1()), p._2());
    }

    /**
     *
     * @param seed
     * @return Product of Seed and value
     */
    static P2<Long, Long> nextLong(long seed) {
        long newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL;
        long n = newSeed >>> 16;
        return P.p(newSeed, n);
    }

    public static Stream<Integer> intStream() {
        return stream(r -> r.nextInt());
    }

    public static Stream<Integer> naturalIntStream() {
        return stream(r -> r.nextNatural());
    }

    public static <A> Stream<A> stream(F<Rng, P2<Rng, A>> f) {
        return Stream.<A, Rng>unfold(r -> {
            P2<Rng, A> p = f.f(r);
            return Option.some(P.p(p._2(), p._1()));
        }, new LcgRng());
    }

}
