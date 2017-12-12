package suraj.android.sqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter
{
    public CustomCursorAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View oneView = inflater.inflate(R.layout.list_item,parent,false);
        return oneView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView txtv_name,txtv_age,txtv_hobby,txtv_quality;

        txtv_name = (TextView)view.findViewById(R.id.txtv_name);
        txtv_age = (TextView)view.findViewById(R.id.txtv_age);
        txtv_hobby = (TextView)view.findViewById(R.id.txtv_hobby);
        txtv_quality = (TextView)view.findViewById(R.id.txtv_quality);

        int indexOfName = cursor.getColumnIndexOrThrow("name");
        int indexOfage = cursor.getColumnIndexOrThrow("age");
        int indexOfhobby = cursor.getColumnIndexOrThrow("hobby");
        int indexOfquality = cursor.getColumnIndexOrThrow("quality");

        txtv_name.setText(cursor.getString(indexOfName));
        txtv_age.setText("Age: "+cursor.getString(indexOfage));
        txtv_hobby.setText("Hobby: "+cursor.getString(indexOfhobby));
        txtv_quality.setText("Quality: "+cursor.getString(indexOfquality));
    }
}
