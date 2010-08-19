package org.movealong.common.util;

import java.util.*;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;
import static org.junit.Assert.*;

public class CollectionUtilTest {

	@Test
	public void emptyCopyArray() {
		assertEquals(ArrayList.class, CollectionUtil.emptyCopy(new ArrayList()).getClass());
		assertEquals(ArrayList.class, CollectionUtil.<List>emptyCopy(new LinkedList()).getClass());
		assertEquals(ArrayList.class, CollectionUtil.emptyCopy(Arrays.asList(new Object())).getClass());
		assertEquals(ArrayList.class, CollectionUtil.emptyCopy(Collections.emptyList()).getClass());
		assertEquals(ArrayList.class, CollectionUtil.emptyCopy(Collections.<Object>unmodifiableList(new LinkedList<Object>())).getClass());
		assertEquals(ArrayList.class, CollectionUtil.emptyCopy(Collections.<Object>synchronizedList(new LinkedList<Object>())).getClass());
	}

	@Test
	public void emptyCopySet() {
		assertEquals(HashSet.class, CollectionUtil.emptyCopy(new HashSet()).getClass());
		assertEquals(TreeSet.class, CollectionUtil.<SortedSet>emptyCopy(new TreeSet()).getClass());
		assertEquals(HashSet.class, CollectionUtil.emptyCopy(Collections.emptySet()).getClass());
		assertEquals(HashSet.class, CollectionUtil.emptyCopy(Collections.<Object>unmodifiableSet(new TreeSet<Object>())).getClass());
		assertEquals(HashSet.class, CollectionUtil.emptyCopy(Collections.<Object>synchronizedSet(new TreeSet<Object>())).getClass());
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CollectionUtilTest.class);
	}
}
