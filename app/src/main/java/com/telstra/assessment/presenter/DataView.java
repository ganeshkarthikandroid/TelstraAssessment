package com.telstra.assessment.presenter;

import com.telstra.assessment.model.Response;


public interface DataView {
    void updateDataView(Response response);

    void onResponseFailure();
}
