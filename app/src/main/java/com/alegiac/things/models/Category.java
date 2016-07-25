package com.alegiac.things.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by alessandro.giacomell on 25/07/16.
 */
public class Category implements Serializable
{
    /**
     * Category identifier
     */
    private long mIdentifier;

    /**
     * Category name
     */
    private String mName;

    /**
     * Category box color reference
     */
    private int mColorReference;

    /**
     * Category image reference
     */
    private int mImageReference;

    /**
     * Note counter
     */
    private int thingCount = 0;

    public Category()
    {}

    /**
     * Constructor from plain params
     * @param identifier
     * @param name
     * @param colorReference
     * @param imageReference
     */
    public Category(long identifier, String name, int colorReference, int imageReference)
    {
        mIdentifier = identifier;
        mName = name;
        mColorReference = colorReference;
        mImageReference = imageReference;

    }

    /**
     * Constructor from json object
     * @param jsonObject
     * @throws JSONException
     */
    public Category(JSONObject jsonObject) throws JSONException
    {
        mIdentifier = jsonObject.getLong("category_identifier");
        mName = jsonObject.getString("category_name");
        mColorReference = jsonObject.getInt("category_color_reference");
        mImageReference = jsonObject.getInt("category_image_reference");
    }

    /**
     * Convert the object to json object
     * @return
     * @throws JSONException
     */
    public JSONObject toJSON() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category_identifier",mIdentifier);
        jsonObject.put("category_name",mName);
        jsonObject.put("category_color_reference",mColorReference);
        jsonObject.put("category_image_reference",mImageReference);

        return jsonObject;
    }

    public long getIdentifier() {
        return mIdentifier;
    }

    public String getName() {
        return mName;
    }

    public int getColorReference() {
        return mColorReference;
    }

    public int getImageReference() {
        return mImageReference;
    }

    public int getThingCount()
    {
        return thingCount;
    }

    public void setIdentifier(long mIdentifier)
    {
        this.mIdentifier = mIdentifier;
    }

    public void setName(String mName)
    {
        this.mName = mName;
    }

    public void setColorReference(int mColorReference)
    {
        this.mColorReference = mColorReference;
    }

    public void setImageReference(int mImageReference)
    {
        this.mImageReference = mImageReference;
    }

    public void setThingCount(int thingCount)
    {
        this.thingCount = thingCount;
    }
}