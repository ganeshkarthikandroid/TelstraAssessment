package com.telstra.assessment.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.telstra.assessment.R;
import com.telstra.assessment.model.GetData;
import com.telstra.assessment.model.Response;
import com.telstra.assessment.presenter.AlertDialogCallback;
import com.telstra.assessment.presenter.DataCallback;
import com.telstra.assessment.presenter.DataPresenter;
import com.telstra.assessment.presenter.DataView;
import com.telstra.assessment.utils.AlertUtils;
import com.telstra.assessment.utils.NetworkUtils;
import com.telstra.assessment.view.adapter.DataBindAdapter;

import java.util.List;


public class DataListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, DataView, AlertDialogCallback {
    private List<GetData> mDataList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mDataView;
    private DataPresenter mDataPresenter;
    private DataCallback mDataCallback;

    /*
     Initializing activity callback
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (null != context) {
            mDataCallback = (DataCallback) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        initializeViews(view);
        setRetainInstance(true);
        return view;
    }

    /*
     Initializing views
     */
    private void initializeViews(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        mDataView = view.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        });
        mDataView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mDataView.getContext(),
                LinearLayoutManager.VERTICAL);
        mDataView.addItemDecoration(dividerItemDecoration);
        mDataPresenter = new DataPresenter(DataListFragment.this);
    }

    /*
     Binding Data
     */
    private void getData() {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            AlertUtils.ShowAlert(getActivity(), getString(R.string.no_internet_connection), this);
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
            mDataPresenter.getDataList(getActivity());
        }

    }

    /*
     Pull down refresh callback
     */
    @Override
    public void onRefresh() {
        getData();
    }

    /*
      Returning size of data list
   */
    public int getDataListSize() {
        return mDataList.size();
    }

    /*
     Updating data value to views
     */
    @Override
    public void updateDataView(Response response) {
        if (null == mDataCallback) return;
        if (null != response.getTitle()) {
            mDataCallback.setTitle(response.getTitle());
        }
        mDataList = response.getDataList();
        mDataView.setAdapter(new DataBindAdapter(mDataList));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResponseFailure() {
        mSwipeRefreshLayout.setRefreshing(false);
        AlertUtils.ShowAlert(getActivity(), getString(R.string.please_try_again), this);
    }

    @Override
    public void retry() {
        getData();
    }
}
