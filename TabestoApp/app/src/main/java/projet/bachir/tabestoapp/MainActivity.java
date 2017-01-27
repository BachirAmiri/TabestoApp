package projet.bachir.tabestoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

class Food implements Serializable {
    String name ;
    int price ;
    int picture ;

    public Food(String name_ , int price_){
        this.name = name_ ;
        this.price = price_ ;
        this.picture = 1 ;
    }

    public Food(String name_ , int price_ , int picture_){
        this.name = name_ ;
        this.price = price_ ;
        this.picture = picture_;
    }

    public String getName(){
        return this.name ;
    }
    public int  getPrice(){
        return this.price ;
    }

    public int getPicture(){
        return picture ;
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button ajout = (Button) findViewById(R.id.buttonAdd);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ListActivity.class);
                EditText nom = (EditText) findViewById(R.id.editText);
                String name = nom.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    nom.setError("Champ nom vide");
                    return;
                }

                EditText prix = (EditText) findViewById(R.id.editText2);

                if (TextUtils.isEmpty(prix.getText().toString())) {
                    prix.setError("Champ prix vide");
                    return;
                }
                int price = Integer.parseInt((prix.getText().toString()));
                Food newFood = new Food(name, price, R.drawable.food1);

                i.putExtra("liste", newFood);
                startActivity(i);

            }
        });


        Button ignore = (Button) findViewById(R.id.buttonIgnore);
        ignore.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent i = new Intent(getApplicationContext(), ListActivity.class);
                  startActivity(i);
              }
          }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
