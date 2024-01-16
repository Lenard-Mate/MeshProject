package org.example.testDemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemoTest {
    private Demo subject;

    @BeforeEach
    public void beforeEach() {
        this.subject = new Demo();
    }

    @Test
    public void testSum() {

        int result = subject.sum(0, 1);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testSum_negativeNumbers() {

        int result = subject.sum(0, -1);
        Assertions.assertEquals(-1, result);
    }
}
