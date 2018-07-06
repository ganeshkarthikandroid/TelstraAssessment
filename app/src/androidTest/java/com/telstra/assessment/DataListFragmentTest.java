package com.telstra.assessment;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.telstra.assessment.view.activity.MainActivity;
import com.telstra.assessment.view.fragment.DataListFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DataListFragmentTest {
    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new
            ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        activityTestRule.getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new DataListFragment()).commit();
    }

    /*
     Testing the actionbar is displayed
     */
    @Test
    public void testActionBarIsDisplayed() {
        if (null != activityTestRule.getActivity().getSupportActionBar()
                && null != activityTestRule.getActivity().getSupportActionBar().getTitle()) {
            onView(withText("About Canada")).check(matches(isDisplayed()));
        }
    }

    /*
     Checking all the data are displayed in list
     */
    @Test
    public void testDataAllItemsIsDisplayed() {
        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.recycler_view);
        int totalItems = recyclerView.getAdapter().getItemCount();
        DataListFragment dataListFragment = (DataListFragment)
                activityTestRule.getActivity().getSupportFragmentManager().findFragmentByTag(DataListFragment.class.getSimpleName());
        int adapterItemCount = dataListFragment.getDataListSize();
        assertEquals(totalItems, adapterItemCount);
    }

    /*
     Testing the refresh layout
     */
    @Test
    public void testPullDownRefresh() {
        onView(withId(R.id.swipe_layout)).perform(swipeDown());
    }

}
