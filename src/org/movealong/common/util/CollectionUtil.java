package org.movealong.common.util;

import java.util.*;

public class CollectionUtil {

	private CollectionUtil() {}

	public static <C extends Collection> C emptyCopy(C prototype) {
		return (C) emptyCopy(prototype.getClass());
	}

	public static <C extends Collection> C emptyCopy(Class<C> prototypeClass) {
        if (SortedSet.class.isAssignableFrom(prototypeClass)) {
            return (C) new TreeSet();
        } else if (Set.class.isAssignableFrom(prototypeClass)) {
			return (C) new HashSet();
		} else {
			return (C) new ArrayList();
		}
	}

    public static <E> List<E> reverse(List<E> elements) {
        return Functional.foldRight(
                elements,
                new Functional.Folder<E, List<E>>() {
                    @Override
                    public List<E> fold(E input, List<E> accumulator) {
                        accumulator.add(input);
                        return accumulator;
                    }
                },
                new ArrayList<E>());
    }

}
