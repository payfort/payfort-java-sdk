package com.payfort.sdk;

import com.payfort.sdk.parameter.FortParameter;
import com.payfort.sdk.exception.FortException;

/**
 * Callback interface for fort call api method
 */
public interface FortResponseHandler {
    void handleResponse(FortParameter response);

    void handleFailure(FortParameter originalRequest, FortException ex);
}
