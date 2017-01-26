package projet.bachir.tabestoapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ArrayList<Food> liste_food = new ArrayList<>() ;
        for (int i = 0 ; i< 20 ; i ++){
            liste_food.add(new Food("Food"+i , (int)( Math.random()* 50) + 10,"food"+((int)(Math.random() * 4)+1)+"" ) );
        }


        Intent iii = getIntent();
        Food recup = (Food)iii.getSerializableExtra("liste");
        if (recup != null ){
            liste_food.add(recup);
        }

        final String[] foods = new String[liste_food.size()];
        for (int i =0 ; i<  liste_food.size() ; i++){
            foods[i] = liste_food.get(i).getName() + " " + liste_food.get(i).getPrice()+"€" ;
        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foods);

        ListView lv = (ListView) findViewById(R.id.listView) ;
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent,View view , int position, long id){
                Intent iii = new Intent(getApplicationContext() , ProductActivity.class);
                iii.putExtra("LIST", (Serializable) liste_food.get(position));
                startActivity(iii);

            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
