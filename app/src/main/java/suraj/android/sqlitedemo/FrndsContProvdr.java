package suraj.android.sqlitedemo;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import java.util.regex.Matcher;

/**
 * Created by Tata Strive on 10/6/2017.
 */
public class FrndsContProvdr extends ContentProvider
{
    DatabaseHandler handler = null;

    public  static final String AUTHORITY = "suraj.android.sqlitedemo";
    public static final String PATH_FRNDS_LIST = "FRNDS_LIST";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH_FRNDS_LIST);
    public static final int FRNDS_LIST=1;

    public static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        MATCHER.addURI(AUTHORITY, PATH_FRNDS_LIST, FRNDS_LIST);
    }

    public static final String MIME_TYPE_1 = ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+"vnd.suraj.android.frndslist";


    public String getType(Uri uri)
    {
       if(MATCHER.match(uri)==FRNDS_LIST)
       {
           return MIME_TYPE_1;
       }
        return null;
    }

    @Override
    public boolean onCreate()
    {
        System.out.println("Returning ***********************In on create**************************************" + " records!");
        Context context = getContext();
        handler = new DatabaseHandler(context, "tata_friends",null,1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        Context context = getContext();
        handler = new DatabaseHandler(context, "tata_friends",null,1);

        System.out.println("Returning ***********************In query**************************************");

        String id=null;
        Cursor c = null;
        if(MATCHER.match(uri)==FRNDS_LIST) {
            //id = uri.getPathSegments().get(1);
            c = handler.getAllFriends(projection, selection, selectionArgs, sortOrder);
            return c;
        }
        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {

        try
        {
        long id = handler.addFriend(values);
        Uri retuUri = ContentUris.withAppendedId(CONTENT_URI, id);
        return retuUri;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        return 0;
    }
}
