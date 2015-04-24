/*
 * Copyright 2015 Tagir Valeev
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javax.util.streamex;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LongSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.Random;
import java.util.PrimitiveIterator.OfLong;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.LongStream;

/**
 * A {@link LongStream} implementation with additional functionality
 * 
 * @author Tagir Valeev
 */
public class LongStreamEx implements LongStream {
    private final LongStream stream;

    LongStreamEx(LongStream stream) {
        this.stream = stream;
    }

    @Override
    public boolean isParallel() {
        return stream.isParallel();
    }

    @Override
    public LongStreamEx unordered() {
        return new LongStreamEx(stream.unordered());
    }

    @Override
    public LongStreamEx onClose(Runnable closeHandler) {
        return new LongStreamEx(stream.onClose(closeHandler));
    }

    @Override
    public void close() {
        stream.close();
    }

    @Override
    public LongStreamEx filter(LongPredicate predicate) {
        return new LongStreamEx(stream.filter(predicate));
    }

    /**
     * Returns a {@link LongStreamEx} consisting of the results of applying the
     * given function to the elements of this stream.
     *
     * <p>
     * This is an intermediate operation.
     *
     * @param mapper
     *            a non-interfering, stateless function to apply to each element
     * @return the new stream
     */
    @Override
    public LongStreamEx map(LongUnaryOperator mapper) {
        return new LongStreamEx(stream.map(mapper));
    }

    /**
     * Returns an object-valued {@link StreamEx} consisting of the results of
     * applying the given function to the elements of this stream.
     *
     * <p>
     * This is an intermediate operation.
     *
     * @param <U>
     *            the element type of the new stream
     * @param mapper
     *            a non-interfering, stateless function to apply to each element
     * @return the new stream
     */
    @Override
    public <U> StreamEx<U> mapToObj(LongFunction<? extends U> mapper) {
        return new StreamEx<>(stream.mapToObj(mapper));
    }

    /**
     * Returns an {@link IntStreamEx} consisting of the results of applying the
     * given function to the elements of this stream.
     *
     * <p>
     * This is an intermediate operation.
     *
     * @param mapper
     *            a non-interfering, stateless function to apply to each element
     * @return the new stream
     */
    @Override
    public IntStreamEx mapToInt(LongToIntFunction mapper) {
        return new IntStreamEx(stream.mapToInt(mapper));
    }

    /**
     * Returns a {@link DoubleStreamEx} consisting of the results of applying
     * the given function to the elements of this stream.
     *
     * <p>
     * This is an intermediate operation.
     *
     * @param mapper
     *            a non-interfering, stateless function to apply to each element
     * @return the new stream
     */
    @Override
    public DoubleStreamEx mapToDouble(LongToDoubleFunction mapper) {
        return new DoubleStreamEx(stream.mapToDouble(mapper));
    }

    @Override
    public LongStreamEx flatMap(LongFunction<? extends LongStream> mapper) {
        return new LongStreamEx(stream.flatMap(mapper));
    }

    @Override
    public LongStreamEx distinct() {
        return new LongStreamEx(stream.distinct());
    }

    /**
     * Returns a stream consisting of the elements of this stream in sorted
     * order.
     *
     * <p>
     * This is a stateful intermediate operation.
     *
     * @return the new stream
     */
    @Override
    public LongStreamEx sorted() {
        return new LongStreamEx(stream.sorted());
    }

    @Override
    public LongStreamEx peek(LongConsumer action) {
        return new LongStreamEx(stream.peek(action));
    }

    @Override
    public LongStreamEx limit(long maxSize) {
        return new LongStreamEx(stream.limit(maxSize));
    }

    @Override
    public LongStreamEx skip(long n) {
        return new LongStreamEx(stream.skip(n));
    }

    @Override
    public void forEach(LongConsumer action) {
        stream.forEach(action);
    }

    @Override
    public void forEachOrdered(LongConsumer action) {
        stream.forEachOrdered(action);
    }

    @Override
    public long[] toArray() {
        return stream.toArray();
    }

    @Override
    public long reduce(long identity, LongBinaryOperator op) {
        return stream.reduce(identity, op);
    }

    @Override
    public OptionalLong reduce(LongBinaryOperator op) {
        return stream.reduce(op);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> accumulator, BiConsumer<R, R> combiner) {
        return stream.collect(supplier, accumulator, combiner);
    }

    @Override
    public long sum() {
        return stream.sum();
    }

    @Override
    public OptionalLong min() {
        return stream.min();
    }

    @Override
    public OptionalLong max() {
        return stream.max();
    }

    @Override
    public long count() {
        return stream.count();
    }

    @Override
    public OptionalDouble average() {
        return stream.average();
    }

    @Override
    public LongSummaryStatistics summaryStatistics() {
        return stream.summaryStatistics();
    }

    @Override
    public boolean anyMatch(LongPredicate predicate) {
        return stream.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(LongPredicate predicate) {
        return stream.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(LongPredicate predicate) {
        return stream.noneMatch(predicate);
    }

    @Override
    public OptionalLong findFirst() {
        return stream.findFirst();
    }

    @Override
    public OptionalLong findAny() {
        return stream.findAny();
    }

    @Override
    public DoubleStreamEx asDoubleStream() {
        return new DoubleStreamEx(stream.asDoubleStream());
    }

    @Override
    public StreamEx<Long> boxed() {
        return new StreamEx<>(stream.boxed());
    }

    @Override
    public LongStreamEx sequential() {
        return new LongStreamEx(stream.sequential());
    }

    @Override
    public LongStreamEx parallel() {
        return new LongStreamEx(stream.parallel());
    }

    @Override
    public OfLong iterator() {
        return stream.iterator();
    }

    @Override
    public java.util.Spliterator.OfLong spliterator() {
        return stream.spliterator();
    }

    /**
     * Returns a new {@code LongStreamEx} which is a concatenation of this
     * stream and the stream containing supplied values
     * 
     * @param values
     *            the values to append to the stream
     * @return the new stream
     */
    public LongStreamEx append(long... values) {
        return new LongStreamEx(LongStream.concat(stream, LongStream.of(values)));
    }

    public LongStreamEx append(LongStream other) {
        return new LongStreamEx(LongStream.concat(stream, other));
    }

    /**
     * Returns a new {@code LongStreamEx} which is a concatenation of the stream
     * containing supplied values and this stream
     * 
     * @param values
     *            the values to prepend to the stream
     * @return the new stream
     */
    public LongStreamEx prepend(long... values) {
        return new LongStreamEx(LongStream.concat(LongStream.of(values), stream));
    }

    public LongStreamEx prepend(LongStream other) {
        return new LongStreamEx(LongStream.concat(other, stream));
    }

    public LongStreamEx remove(LongPredicate predicate) {
        return new LongStreamEx(stream.filter(predicate.negate()));
    }

    public OptionalLong findAny(LongPredicate predicate) {
        return stream.filter(predicate).findAny();
    }

    public OptionalLong findFirst(LongPredicate predicate) {
        return stream.filter(predicate).findFirst();
    }

    /**
     * Returns true if this stream contains the specified value
     *
     * <p>
     * This is a short-circuiting terminal operation.
     * 
     * @param value
     *            the value too look for in the stream
     * @return true if this stream contains the specified value
     * @see LongStream#anyMatch(LongPredicate)
     */
    public boolean has(long value) {
        return stream.anyMatch(x -> x == value);
    }

    public LongStreamEx sorted(Comparator<Long> comparator) {
        return new LongStreamEx(stream.boxed().sorted(comparator).mapToLong(Long::longValue));
    }

    /**
     * Returns a stream consisting of the elements of this stream in reverse
     * sorted order.
     *
     * <p>
     * This is a stateful intermediate operation.
     *
     * @return the new stream
     * @since 0.0.8
     */
    public LongStreamEx reverseSorted() {
        return sorted((a, b) -> b.compareTo(a));
    }

    public <V extends Comparable<? super V>> LongStreamEx sortedBy(LongFunction<V> keyExtractor) {
        return sorted(Comparator.comparing(i -> keyExtractor.apply(i)));
    }

    public LongStreamEx sortedByInt(LongToIntFunction keyExtractor) {
        return sorted(Comparator.comparingInt(i -> keyExtractor.applyAsInt(i)));
    }

    public LongStreamEx sortedByLong(LongUnaryOperator keyExtractor) {
        return sorted(Comparator.comparingLong(i -> keyExtractor.applyAsLong(i)));
    }

    public LongStreamEx sortedByDouble(LongToDoubleFunction keyExtractor) {
        return sorted(Comparator.comparingDouble(i -> keyExtractor.applyAsDouble(i)));
    }

    /**
     * Returns the minimum element of this stream according to the provided
     * {@code Comparator}.
     *
     * <p>
     * This is a terminal operation.
     *
     * @param comparator
     *            a non-interfering, stateless {@link Comparator} to compare
     *            elements of this stream
     * @return an {@code OptionalLong} describing the minimum element of this
     *         stream, or an empty {@code OptionalLong} if the stream is empty
     * @since 0.1.2
     */
    public OptionalLong min(Comparator<Long> comparator) {
        return stream.reduce((a, b) -> comparator.compare(a, b) > 0 ? b : a);
    }

    /**
     * Returns the minimum element of this stream according to the provided key
     * extractor function.
     *
     * <p>
     * This is a terminal operation.
     *
     * @param <V>
     *            the type of the {@code Comparable} sort key
     * @param keyExtractor
     *            a non-interfering, stateless function
     * @return an {@code OptionalLong} describing some element of this stream
     *         for which the lowest value was returned by key extractor, or an
     *         empty {@code OptionalLong} if the stream is empty
     * @since 0.1.2
     */
    public <V extends Comparable<? super V>> OptionalLong minBy(LongFunction<V> keyExtractor) {
        return stream.reduce((a, b) -> keyExtractor.apply(a).compareTo(keyExtractor.apply(b)) > 0 ? b : a);
    }

    /**
     * Returns the minimum element of this stream according to the provided key
     * extractor function.
     *
     * <p>
     * This is a terminal operation.
     *
     * @param keyExtractor
     *            a non-interfering, stateless function
     * @return an {@code OptionalLong} describing some element of this stream
     *         for which the lowest value was returned by key extractor, or an
     *         empty {@code OptionalLong} if the stream is empty
     * @since 0.1.2
     */
    public OptionalLong minByInt(LongToIntFunction keyExtractor) {
        return stream.reduce((a, b) -> Integer.compare(keyExtractor.applyAsInt(a), keyExtractor.applyAsInt(b)) > 0 ? b : a);
    }

    /**
     * Returns the minimum element of this stream according to the provided key
     * extractor function.
     *
     * <p>
     * This is a terminal operation.
     *
     * @param keyExtractor
     *            a non-interfering, stateless function
     * @return an {@code OptionalLong} describing some element of this stream
     *         for which the lowest value was returned by key extractor, or an
     *         empty {@code OptionalLong} if the stream is empty
     * @since 0.1.2
     */
    public OptionalLong minByLong(LongUnaryOperator keyExtractor) {
        return stream.reduce((a, b) -> Long.compare(keyExtractor.applyAsLong(a), keyExtractor.applyAsLong(b)) > 0 ? b : a);
    }

    /**
     * Returns the minimum element of this stream according to the provided key
     * extractor function.
     *
     * <p>
     * This is a terminal operation.
     *
     * @param keyExtractor
     *            a non-interfering, stateless function
     * @return an {@code OptionalLong} describing some element of this stream
     *         for which the lowest value was returned by key extractor, or an
     *         empty {@code OptionalLong} if the stream is empty
     * @since 0.1.2
     */
    public OptionalLong minByDouble(LongToDoubleFunction keyExtractor) {
        return stream.reduce((a, b) -> Double.compare(keyExtractor.applyAsDouble(a), keyExtractor.applyAsDouble(b)) > 0 ? b : a);
    }

    /**
     * Returns the maximum element of this stream according to the provided
     * {@code Comparator}.
     *
     * <p>
     * This is a terminal operation.
     *
     * @param comparator
     *            a non-interfering, stateless {@link Comparator} to compare
     *            elements of this stream
     * @return an {@code OptionalLong} describing the minimum element of this
     *         stream, or an empty {@code OptionalLong} if the stream is empty
     */
    public OptionalLong max(Comparator<Long> comparator) {
        return stream.reduce((a, b) -> comparator.compare(a, b) > 0 ? a : b);
    }

    /**
     * Returns the maximum element of this stream according to the provided key
     * extractor function.
     *
     * <p>
     * This is a terminal operation.
     *
     * @param <V>
     *            the type of the {@code Comparable} sort key
     * @param keyExtractor
     *            a non-interfering, stateless function
     * @return an {@code OptionalLong} describing some element of this stream
     *         for which the highest value was returned by key extractor, or an
     *         empty {@code OptionalLong} if the stream is empty
     * @since 0.1.2
     */
    public <V extends Comparable<? super V>> OptionalLong maxBy(LongFunction<V> keyExtractor) {
        return stream.reduce((a, b) -> keyExtractor.apply(a).compareTo(keyExtractor.apply(b)) > 0 ? a : b);
    }

    /**
     * Returns the maximum element of this stream according to the provided key
     * extractor function.
     *
     * <p>
     * This is a terminal operation.
     *
     * @param keyExtractor
     *            a non-interfering, stateless function
     * @return an {@code OptionalLong} describing some element of this stream
     *         for which the highest value was returned by key extractor, or an
     *         empty {@code OptionalLong} if the stream is empty
     * @since 0.1.2
     */
    public OptionalLong maxByInt(LongToIntFunction keyExtractor) {
        return stream.reduce((a, b) -> Integer.compare(keyExtractor.applyAsInt(a), keyExtractor.applyAsInt(b)) > 0 ? a : b);
    }

    /**
     * Returns the maximum element of this stream according to the provided key
     * extractor function.
     *
     * <p>
     * This is a terminal operation.
     *
     * @param keyExtractor
     *            a non-interfering, stateless function
     * @return an {@code OptionalLong} describing some element of this stream
     *         for which the highest value was returned by key extractor, or an
     *         empty {@code OptionalLong} if the stream is empty
     * @since 0.1.2
     */
    public OptionalLong maxByLong(LongUnaryOperator keyExtractor) {
        return stream.reduce((a, b) -> Long.compare(keyExtractor.applyAsLong(a), keyExtractor.applyAsLong(b)) > 0 ? a : b);
    }

    /**
     * Returns the maximum element of this stream according to the provided key
     * extractor function.
     *
     * <p>
     * This is a terminal operation.
     *
     * @param keyExtractor
     *            a non-interfering, stateless function
     * @return an {@code OptionalLong} describing some element of this stream
     *         for which the highest value was returned by key extractor, or an
     *         empty {@code OptionalLong} if the stream is empty
     * @since 0.1.2
     */
    public OptionalLong maxByDouble(LongToDoubleFunction keyExtractor) {
        return stream.reduce((a, b) -> Double.compare(keyExtractor.applyAsDouble(a), keyExtractor.applyAsDouble(b)) > 0 ? a : b);
    }

    public static LongStreamEx empty() {
        return new LongStreamEx(LongStream.empty());
    }

    /**
     * Returns a sequential {@code LongStreamEx} containing a single element.
     *
     * @param element
     *            the single element
     * @return a singleton sequential stream
     */
    public static LongStreamEx of(long element) {
        return new LongStreamEx(LongStream.of(element));
    }

    /**
     * Returns a sequential ordered {@code LongStreamEx} whose elements are the
     * specified values.
     *
     * @param elements
     *            the elements of the new stream
     * @return the new stream
     */
    public static LongStreamEx of(long... elements) {
        return new LongStreamEx(LongStream.of(elements));
    }

    /**
     * Returns a sequential {@link LongStreamEx} with the specified range of the
     * specified array as its source.
     *
     * @param array
     *            the array, assumed to be unmodified during use
     * @param startInclusive
     *            the first index to cover, inclusive
     * @param endExclusive
     *            index immediately past the last index to cover
     * @return an {@code LongStreamEx} for the array range
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code startInclusive} is negative, {@code endExclusive}
     *             is less than {@code startInclusive}, or {@code endExclusive}
     *             is greater than the array size
     * @since 0.1.1
     * @see Arrays#stream(long[], int, int)
     */
    public static LongStreamEx of(long[] array, int startInclusive, int endExclusive) {
        return new LongStreamEx(Arrays.stream(array, startInclusive, endExclusive));
    }

    /**
     * Returns a {@code LongStreamEx} object which wraps given
     * {@link LongStream}
     * 
     * @param stream
     *            original stream
     * @return the wrapped stream
     * @since 0.0.8
     */
    public static LongStreamEx of(LongStream stream) {
        return stream instanceof LongStreamEx ? (LongStreamEx) stream : new LongStreamEx(stream);
    }

    /**
     * Returns a sequential {@code LongStreamEx} containing an
     * {@link OptionalLong} value, if present, otherwise returns an empty
     * {@code LongStreamEx}.
     *
     * @param optional
     *            the optional to create a stream of
     * @return a stream with an {@code OptionalLong} value if present, otherwise
     *         an empty stream
     * @since 0.1.1
     */
    public static LongStreamEx of(OptionalLong optional) {
        return optional.isPresent() ? of(optional.getAsLong()) : empty();
    }

    public static LongStreamEx of(Collection<Long> c) {
        return new LongStreamEx(c.stream().mapToLong(Long::longValue));
    }

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code long}
     * values produced by given {@link Random} object.
     *
     * <p>
     * A pseudorandom {@code long} value is generated as if it's the result of
     * calling the method {@link Random#nextLong()}.
     *
     * @param random
     *            a {@link Random} object to produce the stream from
     * @return a stream of pseudorandom {@code long} values
     * @see Random#longs()
     */
    public static LongStreamEx of(Random random) {
        return new LongStreamEx(random.longs());
    }

    public static LongStreamEx of(Random random, long streamSize) {
        return new LongStreamEx(random.longs(streamSize));
    }

    public static LongStreamEx of(Random random, long randomNumberOrigin, long randomNumberBound) {
        return new LongStreamEx(random.longs(randomNumberOrigin, randomNumberBound));
    }

    public static LongStreamEx of(Random random, long streamSize, long randomNumberOrigin, long randomNumberBound) {
        return new LongStreamEx(random.longs(streamSize, randomNumberOrigin, randomNumberBound));
    }

    /**
     * Returns an infinite sequential ordered {@code LongStreamEx} produced by
     * iterative application of a function {@code f} to an initial element
     * {@code seed}, producing a stream consisting of {@code seed},
     * {@code f(seed)}, {@code f(f(seed))}, etc.
     *
     * <p>
     * The first element (position {@code 0}) in the {@code LongStreamEx} will
     * be the provided {@code seed}. For {@code n > 0}, the element at position
     * {@code n}, will be the result of applying the function {@code f} to the
     * element at position {@code n - 1}.
     *
     * @param seed
     *            the initial element
     * @param f
     *            a function to be applied to to the previous element to produce
     *            a new element
     * @return A new sequential {@code LongStream}
     * @see LongStream#iterate(long, LongUnaryOperator)
     */
    public static LongStreamEx iterate(final long seed, final LongUnaryOperator f) {
        return new LongStreamEx(LongStream.iterate(seed, f));
    }

    /**
     * Returns an infinite sequential unordered stream where each element is
     * generated by the provided {@code LongSupplier}. This is suitable for
     * generating constant streams, streams of random elements, etc.
     *
     * @param s
     *            the {@code LongSupplier} for generated elements
     * @return a new infinite sequential unordered {@code LongStreamEx}
     * @see LongStream#generate(LongSupplier)
     */
    public static LongStreamEx generate(LongSupplier s) {
        return new LongStreamEx(LongStream.generate(s));
    }

    /**
     * Returns a sequential ordered {@code LongStreamEx} from 0 (inclusive) to
     * {@code endExclusive} (exclusive) by an incremental step of {@code 1}.
     *
     * @param endExclusive
     *            the exclusive upper bound
     * @return a sequential {@code LongStreamEx} for the range of {@code int}
     *         elements
     * @see #range(long, long)
     * @since 0.1.1
     */
    public static LongStreamEx range(long endExclusive) {
        return new LongStreamEx(LongStream.range(0, endExclusive));
    }

    /**
     * Returns a sequential ordered {@code LongStreamEx} from
     * {@code startInclusive} (inclusive) to {@code endExclusive} (exclusive) by
     * an incremental step of {@code 1}.
     *
     * @param startInclusive
     *            the (inclusive) initial value
     * @param endExclusive
     *            the exclusive upper bound
     * @return a sequential {@code LongStreamEx} for the range of {@code long}
     *         elements
     * @see LongStream#range(long, long)
     */
    public static LongStreamEx range(long startInclusive, long endExclusive) {
        return new LongStreamEx(LongStream.range(startInclusive, endExclusive));
    }

    /**
     * Returns a sequential ordered {@code LongStreamEx} from
     * {@code startInclusive} (inclusive) to {@code endInclusive} (inclusive) by
     * an incremental step of {@code 1}.
     *
     * @param startInclusive
     *            the (inclusive) initial value
     * @param endInclusive
     *            the inclusive upper bound
     * @return a sequential {@code LongStreamEx} for the range of {@code long}
     *         elements
     * @see LongStream#rangeClosed(long, long)
     */
    public static LongStreamEx rangeClosed(long startInclusive, long endInclusive) {
        return new LongStreamEx(LongStream.rangeClosed(startInclusive, endInclusive));
    }

    /**
     * Returns a sequential unordered {@code LongStreamEx} of given length which
     * elements are equal to supplied value.
     * 
     * @param value
     *            the constant value
     * @param length
     *            the length of the stream
     * @return a new {@code LongStreamEx}
     * @since 0.1.2
     */
    public static LongStreamEx constant(long value, long length) {
        return new LongStreamEx(LongStream.generate(() -> value).limit(length));
    }
}
