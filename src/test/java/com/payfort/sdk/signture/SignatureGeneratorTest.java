package com.payfort.sdk.signture;

import com.payfort.sdk.FortKeys;
import com.payfort.sdk.parameter.FortKey;
import com.payfort.sdk.parameter.FortParameter;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.hash.Hashing.sha256;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class SignatureGeneratorTest {

    public static final FortKey Z_PARAMETER = new FortKey("z");
    public static final String PHRASE = "A";

    @Test
    public void hashingFunctionCalledWithRightConcatenatedString() {
        AtomicReference<String> stringContainer = new AtomicReference<>("");
        SignatureGenerator generator = new SignatureGenerator((string, charset) -> stringContainer.getAndSet(string), UTF_8);

        FortParameter parameters = new FortParameter();
        parameters.add(FortKeys.TOKEN_NAME, "B");
        parameters.add(FortKeys.AMOUNT, "AB");
        parameters.add(FortKeys.CARD_NUMBER, "AC");
        parameters.add(FortKeys.CUSTOMER_EMAIL, "AA");

        generator.generate("A", parameters);

        assertEquals(PHRASE + "" + FortKeys.AMOUNT.name() + "=AB" + FortKeys.CARD_NUMBER.name() + "=AC" + FortKeys.CUSTOMER_EMAIL.name() + "=AA" + FortKeys.TOKEN_NAME.name() + "=B" + PHRASE, stringContainer.get());

    }

    @Test
    public void hashingFunctionWithListParameterCalledWithRightConcatenatedString() {
        AtomicReference<String> stringContainer = new AtomicReference<>("");
        SignatureGenerator generator = new SignatureGenerator((string, charset) -> stringContainer.getAndSet(string), UTF_8);

        FortParameter parameters = new FortParameter();
        parameters.add(FortKeys.TOKEN_NAME, "B");
        parameters.add(FortKeys.AMOUNT, "AB");
        parameters.add(FortKeys.CARD_NUMBER, "AC");
        parameters.add(FortKeys.CUSTOMER_EMAIL, "AA");
        List<String> list = new ArrayList<>();
        list.add("Value_1");
        list.add("Value_2");
        parameters.add(Z_PARAMETER, list);

        generator.generate(PHRASE, parameters);

        assertEquals(PHRASE + FortKeys.AMOUNT.name() + "=AB" + FortKeys.CARD_NUMBER.name() + "=AC" + FortKeys.CUSTOMER_EMAIL.name() + "=AA" + FortKeys.TOKEN_NAME.name() + "=B" + Z_PARAMETER.name() + "=[Value_1, Value_2]" + PHRASE, stringContainer.get());

    }

    @Test
    public void generateHashUsingSigGenerator() {
        SignatureGenerator generator = new SignatureGenerator((string, charset) -> sha256().hashString(string, charset).toString(),
                UTF_8);

        FortParameter parameters = new FortParameter();
        parameters.add(FortKeys.TOKEN_NAME, "B");
        parameters.add(FortKeys.AMOUNT, "AB");
        parameters.add(FortKeys.CARD_NUMBER, "AC");
        parameters.add(FortKeys.CUSTOMER_EMAIL, "AA");

        String hashedString = generator.generate("A", parameters);

        String concatenatedString = PHRASE + "" + FortKeys.AMOUNT.name() + "=AB" + FortKeys.CARD_NUMBER.name() + "=AC" + FortKeys.CUSTOMER_EMAIL.name() + "=AA" + FortKeys.TOKEN_NAME.name() + "=B" + PHRASE;

        String expected = sha256().hashString(concatenatedString, StandardCharsets.UTF_8).toString().toUpperCase();
        assertEquals(expected, hashedString);
    }


    @Test
    public void generateHashWithParameterContainListUsingSigGenerator() {
        SignatureGenerator generator = new SignatureGenerator((string, charset) -> sha256().hashString(string, charset).toString(),
                UTF_8);

        FortParameter parameters = new FortParameter();
        parameters.add(FortKeys.TOKEN_NAME, "B");
        parameters.add(FortKeys.AMOUNT, "AB");
        parameters.add(FortKeys.CARD_NUMBER, "AC");
        parameters.add(FortKeys.CUSTOMER_EMAIL, "AA");
        List<String> list = new ArrayList<>();
        list.add("Value_1");
        list.add("Value_2");
        parameters.add(Z_PARAMETER, list);

        String hashedString = generator.generate(PHRASE, parameters);

        String concatenatedString = PHRASE + "" + FortKeys.AMOUNT.name() + "=AB" + FortKeys.CARD_NUMBER.name() + "=AC" + FortKeys.CUSTOMER_EMAIL.name() + "=AA" + FortKeys.TOKEN_NAME.name() + "=B" + Z_PARAMETER.name() + "=[Value_1, Value_2]" + PHRASE;

        String expected = sha256().hashString(concatenatedString, StandardCharsets.UTF_8).toString().toUpperCase();
        assertEquals(expected, hashedString);
    }

}
