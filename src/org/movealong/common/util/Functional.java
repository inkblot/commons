package org.movealong.common.util;

import java.util.Collection;
import java.util.List;

public class Functional {

	private Functional() {}

	public static <InputType, AccumulatorType> AccumulatorType foldRight(List<InputType> input, Folder<InputType, AccumulatorType> folder, AccumulatorType seed) {
		if (input.isEmpty()) {
			return seed;
		} else {
			return foldRight(input.subList(1, input.size()), folder, folder.fold(input.get(0), seed));
		}
	}

	public static <InputType, AccumulatorType> AccumulatorType foldLeft(List<InputType> input, Folder<InputType, AccumulatorType> folder, AccumulatorType seed) {
		if (input.isEmpty()) {
			return seed;
		} else {
			return foldLeft(input.subList(0, input.size() - 1), folder, folder.fold(input.get(input.size() - 1), seed));
		}
	}

    public static <E> boolean detect(Collection<E> elements, Test<E> test) {
        for (E element : elements) {
            if (test.test(element)) return true;
        }
        return false;
    }

    public static interface Folder<InputType, AccumulatorType> {
		AccumulatorType fold(InputType input, AccumulatorType accumulator);
	}

	public static <InputType, OutputType, InputCollection extends Collection<InputType>, OutputCollection extends Collection<OutputType>> OutputCollection map(InputCollection source, Mapper<InputType,OutputType> mapper) {
		OutputCollection target = (OutputCollection) CollectionUtil.emptyCopy(source);
		for (InputType element : source) {
			target.add(mapper.map(element));
		}
		return target;
	}

	public static interface Mapper<InputType, OutputType> {
		OutputType map(InputType source);
	}

    public static <InputCollection extends Collection<E>, OutputCollection extends Collection<E>, E> Collection filter(InputCollection unfiltered, Test<E> test, OutputCollection accumulator) {
        for (E element : unfiltered) {
            if (test.test(element)) {
                accumulator.add(element);
            }
        }
        return accumulator;
    }

    public static interface Test<E> {
        boolean test(E element);
    }
}
