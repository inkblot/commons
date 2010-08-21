package org.movealong.common.util;

import java.util.*;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.movealong.common.util.CollectionUtil.reverse;

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

    @Test
    public void _reverse() {
        assertThat(
                reverse(asList(1, 2, 3, 4, 5)),
                equalTo(asList(5, 4, 3, 2, 1)));
    }

}
