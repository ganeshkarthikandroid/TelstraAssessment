package com.telstra.assessment.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.telstra.assessment.model.Response;
import com.telstra.assessment.service.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;


public class DataPresenter {
    private final DataView mDataView;
    private ApiClient mApiClient;

    /*
     Constructor with parameter
     */
    public DataPresenter(DataView mDataView) {
        this.mDataView = mDataView;
        if (null == mApiClient) {
            mApiClient = new ApiClient();
        }
    }

    /*
     get the data from API response
     */
    public void getDataList(Context context) {
        mApiClient.getApiClient(context).getData().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@Nullable Call<Response> call, @Nullable retrofit2.Response<Response> response) {
                if (null != response && response.isSuccessful()) {
                    Response res = response.body();
                    if (null != res && res.getDataList() != null) {
                        mDataView.updateDataView(res);
                    }
                } else {
                    mDataView.onResponseFailure();
                }
            }

            @Override
            public void onFailure(@Nullable Call<Response> call, @Nullable Throwable t) {
                mDataView.onResponseFailure();
            }
        });
    }
}
