package projet.bachir.tabestoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;


class CustomAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    ArrayList<Food> myList = new ArrayList<Food>();
    Context context;

    public CustomAdapter(Context context, ArrayList<Food> myList) {
        this.myList = myList;
        this.context = context;
    }

    public int getCount() {
        return myList.size();
    }

    public Food getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder = null;

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.row_list, parent, false);

            mViewHolder = new MyViewHolder();
            mViewHolder.textViewName = (TextView) convertView
                    .findViewById(R.id.textViewName);
            mViewHolder.textViewAge = (TextView) convertView
                    .findViewById(R.id.textViewAge);
            mViewHolder.imageView = (ImageView) convertView
                    .findViewById(R.id.imageView);
            mViewHolder.imageView.getLayoutParams().height = 300 ;
            mViewHolder.imageView.getLayoutParams().width = 300 ;

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }


        Food listItem = (Food) getItem(position);

        mViewHolder.textViewName.setText(listItem.getName());
        mViewHolder.textViewAge.setText(String.valueOf(listItem.getPrice()) +" €");
        mViewHolder.imageView.setImageResource(listItem.getPicture());

        return convertView;
    }

    private class MyViewHolder {
        TextView textViewName, textViewAge;
        ImageView imageView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iii = new Intent(this.context , ProductActivity.class);
        iii.putExtra("LIST", (Serializable) getItem(position));
        context.startActivity(iii);
    }

}

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        int[] images = { R.drawable.food1, R.drawable.food2,
                R.drawable.food3, R.drawable.food4 };
        final ArrayList<Food> liste_food = new ArrayList<>() ;
        for (int i = 0 ; i< 20 ; i ++){
            liste_food.add(new Food("Food"+i , (int)( Math.random()* 50) + 10, images[((int)(Math.random() * 4)+1) -1] ) );
        }

        Intent iii = getIntent();
        Food recup = (Food)iii.getSerializableExtra("liste");
        if (recup != null ){
            liste_food.add(recup);
        }

        ListView lv = (ListView) findViewById(R.id.listView) ;

        CustomAdapter adapter = new CustomAdapter(this, liste_food);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(adapter);

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
