package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    DataUser dataUser;
    Button add, remove,cancel;
    EditText name;
    ListView lvName;
    ArrayList nameList;
    ArrayList idList;
    ArrayAdapter adapter;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataUser = new DataUser(this,"userdb.sqlite",null,1);


        nameList= new ArrayList();
        idList= new ArrayList();

        dataUser.addUser(new User("Tien"));
        add = findViewById(R.id.btnAdd);
        remove =findViewById(R.id.btnRemove);
        cancel = findViewById(R.id.btnCancel);
        name =findViewById(R.id.name);
        lvName =findViewById(R.id.namelv);

        getNameList();

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,nameList);

        lvName.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.addUser(new User(name.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.RemoveUser((int)idList.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index=position;
            }
        });

    }

    private ArrayList getNameList(){
//        ArrayList<String> arrayList= new ArrayList<>();
        nameList.clear();
        idList.clear();
        for (Iterator iterator = dataUser.getAll().iterator(); iterator.hasNext(); ) {
           User user = (User) iterator.next();
            nameList.add(user.getName());
            idList.add(user.getId());
        }
        return nameList;
    }
}