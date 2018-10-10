package com.master.androidx;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int a = 0x00000002;
        int b = 0x00000001;
        int c = a | b;
        assertEquals(c, 0x00000003);
        assertEquals(c, 3);

    }
}