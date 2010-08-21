package org.movealong.common.util;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Functional {

    private Functional() {}

    public static <E> void apply(Collection<E> elements, Lambda<E> lambda) {
        for (E element : elements) {
            lambda.apply(element);
        }
    }

    public static <E> boolean detect(Collection<E> elements, Matcher<E> matcher) {
        return detect(elements, matches(matcher));
    }

    public static <E> boolean detect(Collection<E> elements, Test<E> test) {
        return find(elements, test) != null;
    }

    public static <InputCollection extends Collection<E>, OutputCollection extends Collection<E>, E> OutputCollection filter(InputCollection unfiltered, Matcher<E> test, OutputCollection accumulator) {
        return filter(unfiltered, matches(test), accumulator);
    }

    public static <InputCollection extends Collection<E>, OutputCollection extends Collection<E>, E> OutputCollection filter(InputCollection unfiltered, Test<E> test, OutputCollection accumulator) {
        for (E element : unfiltered) {
            if (test.test(element)) {
                accumulator.add(element);
            }
        }
        return accumulator;
    }

    public static <E> E find(Collection<E> elements, Matcher<E> matcher) {
        return find(elements, matches(matcher));
    }

    public static <E> E find(Collection<E> elements, Test<E> test) {
        for (E element : elements) {
            if (test.test(element)) return element;
        }
        return null;
    }

    public static <InputType, AccumulatorType> AccumulatorType foldLeft(List<InputType> input, Folder<InputType, AccumulatorType> folder) {
        return foldLeft(input, folder, null);
    }

    public static <InputType, AccumulatorType> AccumulatorType foldLeft(List<InputType> input, Folder<InputType, AccumulatorType> folder, AccumulatorType seed) {
        AccumulatorType accumulator = seed;
        for (int index = 0; index < input.size(); index++) {
            accumulator = folder.fold(input.get(index), accumulator);
        }
        return accumulator;
    }

    public static <InputType, AccumulatorType> AccumulatorType foldRight(List<InputType> input, Folder<InputType, AccumulatorType> folder) {
        return foldRight(input, folder, null);
    }

    public static <InputType, AccumulatorType> AccumulatorType foldRight(List<InputType> input, Folder<InputType, AccumulatorType> folder, AccumulatorType seed) {
        AccumulatorType accumulator = seed;
        for (int index = input.size() - 1; index >= 0; index--) {
            accumulator = folder.fold(input.get(index), accumulator);
        }
        return accumulator;
    }

    public static <InputType, OutputType, InputCollection extends Collection<InputType>, OutputCollection extends Collection<OutputType>> OutputCollection map(InputCollection source, Mapper<InputType, OutputType> mapper) {
        OutputCollection target = (OutputCollection) CollectionUtil.emptyCopy(source);
        return map(source, mapper, target);
    }

    public static <InputType, OutputType, InputCollection extends Collection<InputType>, OutputCollection extends Collection<OutputType>> OutputCollection map(InputCollection source, Mapper<InputType, OutputType> mapper, OutputCollection target) {
        for (InputType element : source) {
            target.add(mapper.map(element));
        }
        return target;
    }

    public static <T> Test<T> matches(Matcher<T> matcher) {
        return new MatcherTest<T>(matcher);
    }

    public static <E> List<E> reverse(List<E> elements) {
        return foldRight(
                elements,
                new Folder<E, List<E>>() {
                    @Override
                    public List<E> fold(E input, List<E> accumulator) {
                        accumulator.add(input);
                        return accumulator;
                    }
                },
                new ArrayList<E>());
    }

    public static interface Folder<InputType, AccumulatorType> {
        AccumulatorType fold(InputType input, AccumulatorType accumulator);
    }

    public static interface Lambda<T> {
        void apply(T item);
    }

    public static interface Mapper<InputType, OutputType> {
        OutputType map(InputType source);
    }

    private static class MatcherTest<T> implements Test<T> {

        private final Matcher<T> matcher;

        public MatcherTest(Matcher<T> matcher) {
            this.matcher = matcher;
        }

        @Override
        public boolean test(T element) {
            return matcher.matches(element);
        }
    }

    public static interface Test<E> {
        boolean test(E element);
    }

}
