package com.payfort.sdk.parameter;

import com.payfort.sdk.FortKeys;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class FortParameterTest {

    public static final String AMOUNT_VALUE = "123";
    private static final String SERVICE_COMMAND_VALUE = "SERVICE_COMMAND_VALUE";
    private static final String CARD_NUMBER_VALUE = "CARD_NUMBER_VALUE";

    @Test
    public void afterAddingNewParameterWhenCallGetOnSameParameterThenWillReturn() {
        FortParameter parameter = new FortParameter();
        parameter.add(FortKeys.AMOUNT, AMOUNT_VALUE);
        Assert.assertEquals(AMOUNT_VALUE, parameter.get(FortKeys.AMOUNT));
    }

    @Test
    public void afterAddingOneParameterWhenIterateOVerThenWillIterateOverOnePair() {
        FortParameter parameter = new FortParameter();
        parameter.add(FortKeys.AMOUNT, AMOUNT_VALUE);

        final AtomicInteger elementCounter = new AtomicInteger(0);
        parameter.iterator(
                (k,v)->{
                    if (elementCounter.get() == 0) {
                        Assert.assertEquals(FortKeys.AMOUNT, k);
                        Assert.assertEquals(AMOUNT_VALUE, v);
                    }
                    elementCounter.incrementAndGet();
                }
        );
        Assert.assertEquals(1, elementCounter.get());
    }


    @Test
    public void afterAddingTwoParameterWhenCallGetOnSameParameterThenWillReturnTwoParameter() {
        FortParameter parameter = new FortParameter();
        parameter.add(FortKeys.AMOUNT, AMOUNT_VALUE);
        parameter.add(FortKeys.SERVICE_COMMAND, SERVICE_COMMAND_VALUE);
        Assert.assertEquals(AMOUNT_VALUE, parameter.get(FortKeys.AMOUNT));
        Assert.assertEquals(SERVICE_COMMAND_VALUE, parameter.get(FortKeys.SERVICE_COMMAND));
    }

    @Test
    public void afterAddingTwoParameterWhenIterateOVerThenWillIterateOverTwoPairSortedAlphabetic() {
        FortParameter parameter = new FortParameter();
        parameter.add(FortKeys.SERVICE_COMMAND, SERVICE_COMMAND_VALUE);
        parameter.add(FortKeys.AMOUNT, AMOUNT_VALUE);
        final AtomicInteger elementCounter = new AtomicInteger(0);
        parameter.iterator(
                (k,v)->{
                    if (elementCounter.get() == 0) {
                        Assert.assertEquals(FortKeys.AMOUNT, k);
                        Assert.assertEquals(AMOUNT_VALUE, v);
                    }
                    if (elementCounter.get() == 1) {
                        Assert.assertEquals(FortKeys.SERVICE_COMMAND,k);
                        Assert.assertEquals(SERVICE_COMMAND_VALUE, v);
                    }

                    elementCounter.incrementAndGet();
                }
        );
        Assert.assertEquals(2, elementCounter.get());
    }

    @Test
    public void afterAddingThreeParameterWhenIterateOVerThenWillIterateOverThreePairSortedAlphabetic() {
        FortParameter parameter = new FortParameter();
        parameter.add(FortKeys.SERVICE_COMMAND, SERVICE_COMMAND_VALUE);
        parameter.add(FortKeys.AMOUNT, AMOUNT_VALUE);
        parameter.add(FortKeys.CARD_NUMBER, CARD_NUMBER_VALUE);

        final AtomicInteger elementCounter = new AtomicInteger(0);

        parameter.iterator(
                (k,v)->{
                    if (elementCounter.get() == 0) {
                        Assert.assertEquals(FortKeys.AMOUNT, k);
                        Assert.assertEquals(AMOUNT_VALUE, v);
                    }
                    if (elementCounter.get() == 1) {
                        Assert.assertEquals(FortKeys.CARD_NUMBER, k);
                        Assert.assertEquals(CARD_NUMBER_VALUE, v);
                    }
                    if (elementCounter.get() == 2) {
                        Assert.assertEquals(FortKeys.SERVICE_COMMAND, k);
                        Assert.assertEquals(SERVICE_COMMAND_VALUE, v);
                    }
                    elementCounter.incrementAndGet();
                }
        );
        Assert.assertEquals(3, elementCounter.get());
    }
}