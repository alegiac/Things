package com.alegiac.things.activities;

import android.support.v7.app.AppCompatActivity;

import com.alegiac.things.R;
import com.alegiac.things.ThingsApplication;

/**
 * Created by alessandro.giacomell on 25/07/16.
 */
public abstract class BaseActivity extends AppCompatActivity
{
    public static final String THEME_PREFERENCES = "com.alegiac.theme_preferences";
    public static final String RECREATE_ACTIVITY = "com.avjindersekhon.recreateactivity";
    public static final String PREFERENCES_THEME_SAVED = "theme_saved";
    public static final String DARKTHEME = "com.avjindersekon.darktheme";
    public static final String LIGHTTHEME = "com.avjindersekon.lighttheme";

    public static final int THEMEINDEX_LIGHTTHEME = R.style.CustomStyle_LightTheme;
    public static final int THEMEINDEX_DARKTHEME = R.style.CustomStyle_DarkTheme;

    /**
     * Customm application main class reference
     */
    protected ThingsApplication application;

    /**
     * Current theme index
     */
    protected int currentTheme;

    /**
     * Default theme index
     */
    protected int defaultThemeIndex = THEMEINDEX_LIGHTTHEME;


    protected void setupActivity()
    {
        // Creating reference to application handler
        application = (ThingsApplication)getApplication();

        // Setting up theme from shared preferences
        currentTheme = getSharedPreferences(THEME_PREFERENCES,MODE_PRIVATE).getInt(PREFERENCES_THEME_SAVED,defaultThemeIndex);
        this.setTheme(currentTheme);
    }
}
