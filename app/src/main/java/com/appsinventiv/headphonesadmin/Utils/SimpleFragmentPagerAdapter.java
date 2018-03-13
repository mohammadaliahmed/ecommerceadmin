package com.appsinventiv.headphonesadmin.Utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.appsinventiv.headphonesadmin.Fragments.CompletedOrders;
import com.appsinventiv.headphonesadmin.Fragments.PendingOrders;
import com.appsinventiv.headphonesadmin.Fragments.ShippedOrders;

/**
 * Created by AliAh on 02/03/2018.
 */

public class SimpleFragmentPagerAdapter  extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PendingOrders();
        }
        else if (position == 1){
            return new ShippedOrders();
        }
//        else if (position == 2){
//            return new FoodFragment();
//        }
        else {
            return new CompletedOrders();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Pending";
            case 1:
                return "Shipped";
            case 2:
                return "Completed";
//            case 3:
//                return mContext.getString(R.string.category_nature);
            default:
                return null;
        }
    }

}
