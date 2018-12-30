package com.payfort.sdk;

import static com.payfort.sdk.FortKeys.SERVICE_COMMAND;
import static com.payfort.sdk.hash.HashingFunctions.SHA_256;
import static com.payfort.sdk.types.FortCurrency.JOD;
import static com.payfort.sdk.types.FortEnvironment.SAND_BOX;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.Before;
import org.junit.Test;

import com.payfort.sdk.parameter.FortParameter;
import com.payfort.sdk.types.FortCurrency;
import com.payfort.sdk.types.FortEnvironment;

public class FortSDKTest {

    private FortRequestConfiguration configuration;
    private FortAccount fortAccount = new FortAccount("mAccessCode", "mMerchantIdentifier", Constants.SHAREQUEST, Constants.SHARESPONSE, SHA_256.hashingFunction());
    ;
    private FortSDK fortSDK;


    @Before
    public void setup() {
        configuration = new FortRequestConfiguration();
        fortSDK = new FortSDK(fortAccount, FortEnvironment.SAND_BOX, configuration);
    }

    @Test
    public void checkCalculateRequestSignatureCalledWithParameter() {
        AtomicReference<String> reference = new AtomicReference();

        fortAccount.setHashingFunction((str, inc) -> {
            reference.getAndSet(str);
            return Constants.RETURN_FROM_HASH;
        });

        FortSDK fortSDK = new FortSDK(fortAccount, FortEnvironment.SAND_BOX, new FortRequestConfiguration());
        FortParameter parameter = new FortParameter();
        parameter.add(SERVICE_COMMAND, "token");
        String result = fortSDK.calculateRequestSignature(parameter);

        assertEquals(Constants.SHAREQUEST + SERVICE_COMMAND.name() + "=token" + Constants.SHAREQUEST, reference.get());
        assertEquals(Constants.RETURN_FROM_HASH.toUpperCase(), result);
    }

    @Test
    public void checkCalculateResponseSignatureCalledWithParameter() {
        AtomicReference<String> reference = new AtomicReference();

        fortAccount.setHashingFunction((str, inc) -> {
            reference.getAndSet(str);
            return Constants.RETURN_FROM_HASH;
        });

        FortSDK fortSDK = new FortSDK(fortAccount, FortEnvironment.SAND_BOX, new FortRequestConfiguration());
        FortParameter parameter = new FortParameter();
        parameter.add(SERVICE_COMMAND, "token");
        String result = fortSDK.calculateResponseSignature(parameter);

        assertEquals(Constants.SHARESPONSE + SERVICE_COMMAND.name()+ "=token" + Constants.SHARESPONSE, reference.get());
        assertEquals(Constants.RETURN_FROM_HASH.toUpperCase(), result);
    }


    @Test
    public void checkValidateResponseSignatureWillReturnFalseWhenCallWithoutSignature() {
        FortParameter parameter = new FortParameter();
        parameter.add(SERVICE_COMMAND, "token");

        assertFalse(fortSDK.validateResponseSignature(parameter));
    }


    @Test
    public void checkValidateResponseSignatureWillReturnFalseWhenCallWithInvalidSignature() {
        FortParameter parameter = new FortParameter();
        parameter.add(SERVICE_COMMAND, "TOKENIZATION");
        parameter.add(FortKeys.SIGNATURE, "Invalid Sig");

        assertFalse(fortSDK.validateResponseSignature(parameter));
    }

    @Test
    public void checkValidateResponseSignatureWillReturnTrueWhenCallWithValidSignature() {
        FortParameter parameter = new FortParameter();
        parameter.add(SERVICE_COMMAND, "token");
        parameter.add(FortKeys.SIGNATURE, fortSDK.calculateResponseSignature(parameter));

        assertTrue(fortSDK.validateResponseSignature(parameter));
    }


    @Test
    public void checkCurrencyDecimalPointsCalculation() {
        int decimalPoint = fortSDK.currencyDecimalPoints("JOD");

        assertEquals(JOD.decimalPoints(), decimalPoint);
    }

    @Test
    public void checkPayfortCurrencyWorking() {
        FortCurrency fortCurrency = fortSDK.payfortCurrency("JOD");

        assertTrue(fortCurrency == JOD);
    }

    @Test
    public void checkConvertFortAmount() {
        double fortAmount = fortSDK.convertFortAmount(5, JOD);

        assertEquals(5000d, fortAmount, 0);
    }

    @Test
    public void checkAmountCastFromFortCurrency() {
        double fortAmount = fortSDK.castAmountFromFort(5000d, JOD);

        assertEquals(5d, fortAmount, 0);
    }

    @Test
    public void checkGenerateRandomMerchantReference() {
        assertNotEquals(fortSDK.generateRandomMerchantReference(), fortSDK.generateRandomMerchantReference());
    }
}
