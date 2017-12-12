package suraj.android.sqlitedemo;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText editName,editAge,editHobby, editQuality;
    ListView listvFriends;
    Button btnAdd,btnShow;

    ArrayList<String> al_details;

    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editName = (EditText) findViewById(R.id.editName);
        editAge = (EditText) findViewById(R.id.editAge);
        editHobby = (EditText) findViewById(R.id.editHobby);
        editQuality = (EditText) findViewById(R.id.editQuality);

        listvFriends= (ListView) findViewById(R.id.listvFriends);
        registerForContextMenu(listvFriends);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);

        al_details = new ArrayList<String>();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 11, 1, "Delete");
        menu.add(1, 12, 2, "Update");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = acmi.position;
        final DatabaseHandler handler = new DatabaseHandler(FormActivity.this, "tata_friends",null,1);


        switch (item.getItemId())
        {
            case 11:
                    handler.deleteFriend(c.getInt(0));
                    displayFriends();
                    break;
            case 12:
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(FormActivity.this);
                    builder.setTitle("Enter New Details");
                    final View cust_view = getLayoutInflater().inflate(R.layout.update_popup,null);

                    final EditText name,age, hobby, quality;
                    final TextView frnd_id;
                    name  =(EditText) cust_view.findViewById(R.id.edit_frndName);
                    age   = (EditText)cust_view.findViewById(R.id.edit_frndAge);
                    hobby  = (EditText)cust_view.findViewById(R.id.edit_frndHobby);
                    quality  =(EditText) cust_view.findViewById(R.id.edit_frndQuality);
                    frnd_id = (TextView) cust_view.findViewById(R.id.frnd_id);
                    frnd_id.setText("Id : "+c.getString(0));
                    name.setText(""+c.getString(1));
                    age.setText(""+c.getString(2));
                    hobby.setText(""+c.getString(3));
                    quality.setText("" + c.getString(4));

                    builder.setView(cust_view);
                    builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            handler.updateFriend(Integer.parseInt("" + c.getString(0)), name.getText().toString(), age.getText().toString(),
                                    hobby.getText().toString(), quality.getText().toString());
                            displayFriends();
                        }});

                    android.support.v7.app.AlertDialog dialog =  builder.create();
                    dialog.show();
                    break;

        }
        return super.onContextItemSelected(item);

    }

    public void onClick(View v)
    {
        if(v.getId()==R.id.btnAdd)
        {
            if(editName.getText().toString().equals("") || editAge.getText().toString().equals("") ||
                    editQuality.getText().toString().equals("") || editHobby.getText().toString().equals(""))
            {
                Toast.makeText(FormActivity.this, "Enter All Details", Toast.LENGTH_SHORT).show();
            }
            else
            {
                DatabaseHandler handler = new DatabaseHandler(FormActivity.this, "tata_friends",null,1);

                ContentValues emptyRow = new ContentValues();
                emptyRow.put("name",editName.getText().toString());
                emptyRow.put("age", Integer.parseInt(editAge.getText().toString()));
                emptyRow.put("hobby", editHobby.getText().toString());
                emptyRow.put("quality", editQuality.getText().toString());

                long id = handler.addFriend(emptyRow);
                Toast.makeText(FormActivity.this, "Id = "+id, Toast.LENGTH_SHORT).show();

                editAge.setText("");
                editHobby.setText("");
                editQuality.setText("");
                editName.setText("");
            }
        }
        if(v.getId()==R.id.btnShow)
        {
            displayFriends();
        }
    }

    public void displayFriends()
    {
        al_details.clear();

        DatabaseHandler handler = new DatabaseHandler(FormActivity.this, "tata_friends",null,1);

        c = handler.getAllFriends(null,null, null ,null);
        CustomCursorAdapter cutome_adapter = new CustomCursorAdapter(FormActivity.this, c);
        listvFriends.setAdapter(cutome_adapter);
    }
}
