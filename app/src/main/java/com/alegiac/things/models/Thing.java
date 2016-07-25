package com.alegiac.things.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by alessandro.giacomell on 25/07/16.
 */
public class Thing implements Serializable
{
    private final static String MEDIA_IMAGE = "image";
    private final static String MEDIA_VIDEO = "video";
    private final static String MEDIA_NONE = "none";

    private final static String STATUS_TODO = "todo";
    private final static String STATUS_OVERDUE = "overdue";
    private final static String STATUS_DONE = "done";

    private UUID mIdentifier;
    private String mTitle;
    private String mBody;
    private String mMediaFileName;
    private String mInsertTimestamp;
    private String mLastEditTimestamp;
    private String mTodoTimestamp;
    private String mRemindTimestamp;
    private boolean mHasToBeReminded;
    private boolean mHasMedia;
    private String mMediaType;
    private Category mCategory;

    public Thing(String title, Category category, String todoTimestamp)
    {
        Long ts = System.currentTimeMillis()/1000;

        this.mIdentifier = UUID.randomUUID();
        this.mTitle = title;
        this.mBody = "";
        this.mCategory = category;
        this.mTodoTimestamp = todoTimestamp;
        this.mHasToBeReminded = false;
        this.mHasMedia = false;
        this.mMediaFileName = "";
        this.mMediaType = MEDIA_NONE;
        this.mInsertTimestamp = ts.toString();
        this.mLastEditTimestamp = null;
        this.mRemindTimestamp = null;
    }

    public Thing(JSONObject jsonObject) throws JSONException
    {
        mIdentifier = UUID.fromString(jsonObject.getString("thing_identifier"));
        mTitle = jsonObject.getString("thing_title");
        mBody = jsonObject.getString("thing_body");
        mMediaFileName = jsonObject.getString("thing_media_filename");
        mInsertTimestamp = jsonObject.getString("thing_insert_ts");
        mLastEditTimestamp = jsonObject.getString("thing_edit_ts");
        mTodoTimestamp = jsonObject.getString("thing_todo_ts");
        mRemindTimestamp = jsonObject.getString("thing_remind_ts");
        mHasToBeReminded = jsonObject.getBoolean("thing_has_remind");
        mHasMedia = jsonObject.getBoolean("thing_has_media");
        mMediaType = jsonObject.getString("thing_media_type");
        mCategory = new Category(jsonObject.getJSONObject("thing_category"));
    }

    public JSONObject toJSON() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("thing_identifier",mIdentifier.toString());
        jsonObject.put("thing_title",mTitle);
        jsonObject.put("thing_body",mBody);
        jsonObject.put("thing_media_filename",mMediaFileName);
        jsonObject.put("thing_insert_ts",mInsertTimestamp);
        jsonObject.put("thing_edit_ts",mLastEditTimestamp);
        jsonObject.put("thing_todo_ts",mTodoTimestamp);
        jsonObject.put("thing_remind_ts",mRemindTimestamp);
        jsonObject.put("thing_has_remind",mHasToBeReminded);
        jsonObject.put("thing_has_media",mHasMedia);
        jsonObject.put("thing_media_type",mMediaType);
        jsonObject.put("thing_category",mCategory);

        return jsonObject;
    }

    public UUID getIdentifier()
    {
        return mIdentifier;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String mTitle)
    {
        this.mTitle = mTitle;
    }

    public String getBody()
    {
        return mBody;
    }

    public void setBody(String mBody)
    {
        this.mBody = mBody;
    }

    public String getMediaFileName()
    {
        return mMediaFileName;
    }

    public void setMediaFileName(String mMediaFileName)
    {
        this.mMediaFileName = mMediaFileName;
    }

    public String getInsertTimestamp()
    {
        return mInsertTimestamp;
    }

    public void setInsertTimestamp(String mInsertTimestamp)
    {
        this.mInsertTimestamp = mInsertTimestamp;
    }

    public String getLastEditTimestamp()
    {
        return mLastEditTimestamp;
    }

    public void setLastEditTimestamp(String mLastEditTimestamp)
    {
        this.mLastEditTimestamp = mLastEditTimestamp;
    }

    public String getTodoTimestamp()
    {
        return mTodoTimestamp;
    }

    public void setTodoTimestamp(String mTodoTimestamp)
    {
        this.mTodoTimestamp = mTodoTimestamp;
    }

    public String getRemindTimestamp()
    {
        return mRemindTimestamp;
    }

    public void setRemindTimestamp(String mRemindTimestamp)
    {
        this.mRemindTimestamp = mRemindTimestamp;
    }

    public boolean isHasToBeReminded()
    {
        return mHasToBeReminded;
    }

    public void setHasToBeReminded(boolean mHasToBeReminded)
    {
        this.mHasToBeReminded = mHasToBeReminded;
    }

    public boolean isHasMedia()
    {
        return mHasMedia;
    }

    public void setHasMedia(boolean mHasMedia)
    {
        this.mHasMedia = mHasMedia;
    }

    public String getMediaType()
    {
        return mMediaType;
    }

    public void setMediaType(String mMediaType)
    {
        this.mMediaType = mMediaType;
    }

    public Category getCategory()
    {
        return mCategory;
    }

    public void setCategory(Category mCategory)
    {
        this.mCategory = mCategory;
    }
}