package org.movealong.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;
import static org.junit.Assert.*;

public class FunctionalTest {

	@Test
	public void mapList() {
		List<Integer> inputList = Arrays.<Integer>asList(1, 2, 3, 4, 5);
		List<Number> outputList = Functional.map(inputList,
				new Functional.Mapper<Integer, Number>() {
					public Number map(Integer i) {
						return new BigDecimal(i);
					}
				});
		assertArrayEquals(
				new BigDecimal[] {
					new BigDecimal(1),
					new BigDecimal(2),
					new BigDecimal(3),
					new BigDecimal(4),
					new BigDecimal(5)
				},
				outputList.toArray(new BigDecimal[0]));
		assertEquals(ArrayList.class, outputList.getClass());
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(FunctionalTest.class);
	}

}
