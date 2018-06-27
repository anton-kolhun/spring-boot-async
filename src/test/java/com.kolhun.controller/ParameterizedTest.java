package com.kolhun.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterizedTest {

    private int parameter;

    public ParameterizedTest(int parameter) {
        this.parameter = parameter;
    }

    @Parameterized.Parameters
    public static Collection<Integer> data() {
        return Arrays.asList(11, 23);
    }

    @Test
    public void doTest() {
        System.out.println(parameter);
    }
}
