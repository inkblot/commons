package org.movealong.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.movealong.common.util.Functional.*;

public class FunctionalTest {

    @Test
    public void _apply() throws Exception {
        final Object alpha = new Object();
        final Object beta = new Object();
        final Object gamma = new Object();
        final Object delta = new Object();
        final List<Object> visited = new ArrayList<Object>();
        apply(
                asList(alpha, beta, gamma, delta),
                new Functional.Lambda<Object>() {
                    @Override
                    public void apply(Object item) {
                        visited.add(item);
                    }
                });
        assertThat(visited, equalTo(asList(alpha, beta, gamma, delta)));
    }

    @Test
    public void _detect() throws Exception {
        final Object alpha = new Object();
        final Object beta = new Object();
        final Object gamma = new Object();
        final Object delta = new Object();
        final List<Object> objects = asList(alpha, beta, delta);
        assertTrue(detect(objects, sameInstance(beta)));
        assertFalse(detect(objects, sameInstance(gamma)));
    }

    @Test
    public void _filter() throws Exception {
        assertThat(
                filter(
                        asList(1, 2, 3, 4, 5),
                        lessThan(3),
                        new ArrayList<Integer>()),
                equalTo(asList(1, 2)));
    }

    @Test
    public void _find() throws Exception {
        final Object alpha = new Object();
        final Object beta = new Object();
        final Object gamma = new Object();
        final Object delta = new Object();
        final List<Object> objects = asList(alpha, beta, delta);
        assertThat(
                find(
                        objects,
                        sameInstance(beta)),
                sameInstance(beta));
        assertThat(
                find(
                        objects,
                        sameInstance(gamma)),
                nullValue());
    }

    @Test
    public void _fold() throws Exception {
        Folder<Integer, List<Integer>> appender =
                new Folder<Integer, List<Integer>>() {
                    @Override
                    public List<Integer> fold(Integer input, List<Integer> accumulator) {
                        List<Integer> list = accumulator == null ? new ArrayList<Integer>() : accumulator;
                        list.add(input);
                        return list;
                    }
                };

        assertThat(
                foldRight(asList(1, 2, 3, 4, 5), appender),
                equalTo(asList(5, 4, 3, 2, 1)));
        assertThat(
                foldLeft(asList(1, 2, 3, 4, 5), appender),
                equalTo(asList(1, 2, 3, 4, 5)));
        assertThat(
                reverse(asList(1, 2, 3, 4, 5)),
                equalTo(asList(5, 4, 3, 2, 1)));
    }

    @Test
    public void _map() throws Exception {
        assertThat(
                map(
                        asList(1, 2, 3, 4, 5),
                        new Functional.Mapper<Integer, BigDecimal>() {
                            public BigDecimal map(Integer i) {
                                return new BigDecimal(i);
                            }
                        },
                        new ArrayList<BigDecimal>()),
                equalTo(asList(
                        new BigDecimal(1),
                        new BigDecimal(2),
                        new BigDecimal(3),
                        new BigDecimal(4),
                        new BigDecimal(5))));
    }

}
