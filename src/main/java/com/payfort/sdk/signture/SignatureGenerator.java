package com.payfort.sdk.signture;

import com.payfort.sdk.hash.HashingFunction;
import com.payfort.sdk.parameter.FortParameter;

import java.nio.charset.Charset;

/**
 * The class is responsible to generate fort signature
 */
final public class SignatureGenerator {

    private final HashingFunction hashingFunction;
    private final Charset charset;

    public SignatureGenerator(HashingFunction hashingFunction, Charset charset) {
        this.hashingFunction = hashingFunction;
        this.charset = charset;
    }


    /**
     * @param phrase
     * @param parameters
     * @return hashed string include all passed parameters and phrase
     */
    public String generate(String phrase, FortParameter parameters) {
        return hashingFunction.hash(prepareStringToHash(phrase, parameters), charset).toUpperCase();
    }

    private String prepareStringToHash(String phrase, FortParameter parameters) {
        String concatenatedString = concatenatedString(phrase, parameters);
        return concatenatedString;
    }

    private String concatenatedString(String phrase, FortParameter parameters) {
        StringBuilder sb = new StringBuilder(phrase);

        parameters.iterator(
                (k, v) -> {
                    sb.append(k.name() + "=" + v);
                }
        );
        sb.append(phrase);

        return sb.toString();
    }

}
