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

}
