package com.alegiac.things.store;

import android.content.Context;

/**
 * Created by alessandro.giacomell on 25/07/16.
 */
public class StoreHandler
{
    private String mStoreFilename;
    private Context mContext;

    /**
     * Singleton instance
     */
    private static StoreHandler mInstance = null;

    /**
     * Private constructor
     */
    private StoreHandler()
    {}

    /**
     * Public singleton access method
     * @return StoreHandler singleton instance
     */
    public static StoreHandler getInstance()
    {
        if (mInstance == null) mInstance = new StoreHandler();
        return mInstance;
    }


}
