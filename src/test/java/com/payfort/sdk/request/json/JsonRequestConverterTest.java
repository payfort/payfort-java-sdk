package com.payfort.sdk.request.json;

import com.payfort.sdk.parameter.FortKey;
import com.payfort.sdk.parameter.FortParameter;
import com.payfort.sdk.request.converter.json.JsonRequestConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.payfort.sdk.FortKeys.AMOUNT;
import static com.payfort.sdk.FortKeys.CURRENCY;
import static org.junit.Assert.assertEquals;


public class JsonRequestConverterTest {

    public static final FortKey LIST_PARAMETER = new FortKey("ListParameter");
    public static final String EN = "EN";
    public static final String AR = "AR";

    @Test
    public void checkJsonSerializing() {
        FortParameter parameter = new FortParameter();
        parameter.add(AMOUNT, "B Value");
        parameter.add(CURRENCY, "A Value");
        JsonRequestConverter generator = new JsonRequestConverter();
        String json = generator.serialize(parameter);
        assertEquals("{\"" + AMOUNT.name()+ "\":\"B Value\",\"" + CURRENCY.name() + "\":\"A Value\"}", json);
    }


    @Test
    public void checkJsonParsing() {
        FortParameter parameter = new FortParameter();
        parameter.add(AMOUNT, "B Value");
        parameter.add(CURRENCY, "A Value");
        JsonRequestConverter generator = new JsonRequestConverter();
        assertEquals(parameter, generator.parse("{\"" + AMOUNT.name() + "\":\"B Value\",\"" + CURRENCY.name() + "\":\"A Value\"}"));
    }

    @Test
    public void checkJsonSerializingWithListParameter() {
        FortParameter parameter = new FortParameter();
        parameter.add(AMOUNT, "B Value");
        parameter.add(CURRENCY, "A Value");
        List<String> values = new ArrayList<>();
        values.add(EN);
        values.add(AR);
        parameter.add(LIST_PARAMETER, values);
        JsonRequestConverter generator = new JsonRequestConverter();
        String json = generator.serialize(parameter);
        assertEquals("{\""+LIST_PARAMETER.name()+"\":[\""+EN+"\",\""+AR+"\"],\""+AMOUNT.name()+"\":\"B Value\",\""+CURRENCY.name()+"\":\"A Value\"}", json);
    }

    @Test
    public void checkJsonParsingWithListParameter() {
        FortParameter parameter = new FortParameter();
        parameter.add(AMOUNT, "B Value");
        parameter.add(CURRENCY, "A Value");
        List<String> values = new ArrayList<>();
        values.add(EN);
        values.add(AR);
        parameter.add(LIST_PARAMETER, values);
        JsonRequestConverter generator = new JsonRequestConverter();
        assertEquals(parameter, generator.parse("{\""+LIST_PARAMETER.name()+"\":[\""+EN+"\",\""+AR+"\"],\""+AMOUNT.name()+"\":\"B Value\",\""+CURRENCY.name()+"\":\"A Value\"}"));
    }
}

