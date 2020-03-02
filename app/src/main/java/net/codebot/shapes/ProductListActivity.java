package net.codebot.shapes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ProductListActivity extends AppCompatActivity {

    String user= "owner";
    String json;

    private static final int ADD_PROD_ACTIVITY_REQUEST_CODE = 0;

    void setJSON(){


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flake);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String flakes = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        baos = new ByteArrayOutputStream();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bread);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        imageBytes = baos.toByteArray();
        String bread = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        baos = new ByteArrayOutputStream();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bread2);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        imageBytes = baos.toByteArray();
        String bread2 = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        baos = new ByteArrayOutputStream();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.figi);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        imageBytes = baos.toByteArray();
        String figi = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        baos = new ByteArrayOutputStream();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flow);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        imageBytes = baos.toByteArray();
        String flow = Base64.encodeToString(imageBytes, Base64.DEFAULT);


        baos = new ByteArrayOutputStream();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lucky);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        imageBytes = baos.toByteArray();
        String lucky = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        baos = new ByteArrayOutputStream();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vitamin);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        imageBytes = baos.toByteArray();
        String vitamine = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        /*
        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        image.setImageBitmap(decodedImage);
        */


        json="{" +
                "\"products\": [" +
                    "{"+
                    "\"id\":\"1\"," +
                    "\"name\":\"Frosted Flakes\"," +
                    "\"pic\":\""+flakes+"\","+
                    "\"quantity\":\"12\"," +
                    "\"location\":\"A1\"" +
                    "},"+
                    "{"+
                    "\"id\":\"2\"," +
                    "\"name\":\"Bread\"," +
                    "\"pic\":\""+bread+"\","+
                    "\"quantity\":\"5\"," +
                    "\"location\":\"D9\"" +
                    "},"+
                    "{"+
                    "\"id\":\"3\"," +
                    "\"name\":\"Whole Wheat Bread\"," +
                    "\"pic\":\""+bread2+"\","+
                    "\"quantity\":\"5\"," +
                    "\"location\":\"D2\"" +
                    "},"+
                    "{"+
                    "\"id\":\"4\"," +
                    "\"name\":\"Figi Water\"," +
                    "\"pic\":\""+figi+"\","+
                    "\"quantity\":\"5\"," +
                    "\"location\":\"G1\"" +
                    "},"+
                    "{"+
                    "\"id\":\"5\"," +
                    "\"name\":\"Flow Water\"," +
                    "\"pic\":\""+flow+"\","+
                    "\"quantity\":\"12\"," +
                    "\"location\":\"H2\"" +
                    "},"+
                    "{"+
                    "\"id\":\"6\"," +
                    "\"name\":\"Lucky Charms\"," +
                    "\"pic\":\""+lucky+"\","+
                    "\"quantity\":\"12\"," +
                    "\"location\":\"G4\"" +
                    "},"+
                    "{"+
                    "\"id\":\"7\"," +
                    "\"name\":\"Vitamine Water\"," +
                    "\"pic\":\""+vitamine+"\","+
                    "\"quantity\":\"12\"," +
                    "\"location\":\"E6\"" +
                    "}"+
                "]" +
                "}";
       // json="{ \"products\": [{\"id\":\"1\", \"name\":\"Frosted Flakes\", \"pic\":\""+flakes+"\", \"quantity\":\"12\", \"location\":\"B12\"}]}";
    }


    ArrayList<Product> productList;

    ArrayList<Product> selectedProducts;

    void clearTable(){
        TableLayout table = (TableLayout) findViewById(R.id.product_table);
        while(table.getChildCount()>1) {
            TableRow child = (TableRow) table.getChildAt(1);
            table.removeView(child);
        }
    }

    void removeProduct(int index){
        TableLayout table = (TableLayout) findViewById(R.id.product_table);
        table.removeView(table.getChildAt(index+1));

    }

    public void populateItemList(String json){
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray products=obj.getJSONArray("products");

            for (int i=0; i<products.length(); i++){
                JSONObject product= products.getJSONObject(i);

                int id=product.getInt("id");
                String name=product.getString("name");
                String pic= product.getString("pic");
                int quantity= product.getInt("quantity");
                String location= product.getString("location");
                productList.add(new Product(id,name,pic,quantity,location));

                Log.i("Name", name);
                Log.i("ID", Integer.toString(id));
                Log.i("Quantity", Integer.toString(quantity));
                Log.i("Location", location);
            }

        } catch (Throwable tx) {
            Log.i("error", "error parsing JSON");
        }

    }

    public void populateTable(){
        TableLayout table = (TableLayout) findViewById(R.id.product_table);


        for(int i=0; i<productList.size(); i++){
            Log.i("update","Updating product name"+i);
            TableRow row = new TableRow(ProductListActivity.this);
            row.setId(productList.get(i).id);
            //row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            row.setWeightSum(1);
            row.setGravity(Gravity.CENTER);




            LinearLayout imageBox= new LinearLayout(ProductListActivity.this);
            imageBox.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT));
            imageBox.setGravity(Gravity.CENTER);
            imageBox.setOrientation(LinearLayout.VERTICAL);


            ImageView pic= new ImageView(ProductListActivity.this);
            pic.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200));

            byte[] imageBytes = Base64.decode(productList.get(i).pic, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            pic.setImageBitmap(decodedImage);

            imageBox.addView(pic);


            TextView itemName= new TextView(ProductListActivity.this);
            itemName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            itemName.setGravity(Gravity.CENTER);
            itemName.setText(productList.get(i).name);
            itemName.setTextColor(Color.BLACK);
            itemName.setTextSize(12);
            itemName.setTypeface(Typeface.DEFAULT_BOLD);

            imageBox.addView(itemName);

            row.addView(imageBox);


            TextView quantity= new TextView(ProductListActivity.this);
            quantity.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT));
            quantity.setGravity(Gravity.CENTER);
            quantity.setText(Integer.toString(productList.get(i).quantity));
            quantity.setTextColor(Color.BLACK);
            quantity.setTextSize(18);
            quantity.setTypeface(Typeface.DEFAULT_BOLD);

            row.addView(quantity);

            TextView location= new TextView(ProductListActivity.this);
            location.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT));
            location.setGravity(Gravity.CENTER);
            location.setText(productList.get(i).location);
            location.setTextColor(Color.BLACK);
            location.setTextSize(18);
            location.setTypeface(Typeface.DEFAULT_BOLD);

            row.addView(location);

            final Button add_remove = new Button(ProductListActivity.this);
            add_remove.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT));
            if (user.equals("shopper")){
                add_remove.setText("Add");
                add_remove.setBackgroundColor(Color.GREEN);
                final int index=i;
                add_remove.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(ProductListActivity.this, "Product added: "+productList.get(index).name, Toast.LENGTH_SHORT).show();
                        selectedProducts.add(productList.get(index));
                    }
                });
            } else {
                add_remove.setText("Remove");
                add_remove.setBackgroundColor(Color.RED);
                final int index=i;
                add_remove.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        Toast.makeText(ProductListActivity.this, "Product removed", Toast.LENGTH_SHORT).show();
                        productList.remove(index);
                        clearTable();
                        populateTable();
                    }
                });
            }

            row.addView(add_remove);

            table.addView(row);


        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_activity);

        productList= new ArrayList<Product>();
        selectedProducts= new ArrayList<Product>();

        user=getIntent().getStringExtra("user");

        Button add_or_path_button= (Button) findViewById(R.id.add_or_path_button);
        if (user.equals("shopper")){
            add_or_path_button.setText("View Path");
            add_or_path_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //String selectedProds = (new Gson()).toJson(selectedProducts);


                    /*
                    ArrayList<Product> selProd= new ArrayList<Product>();

                    for(int i=0; i<selectedProducts.size(); i++){
                        selProd.add(new Product(selectedProducts.get(i).id, selectedProducts.get(i).name,"", selectedProducts.get(i).quantity, selectedProducts.get(i).location));

                    }

                    String selectedProds = (new Gson()).toJson(selProd);
                    Intent intent= new Intent(ProductListActivity.this, PathActivity.class);
                    intent.putExtra("selectedProducts", selectedProds);
                    */


                    ArrayList<String> prodLocations= new ArrayList<String>();
                    for(int i=0; i<selectedProducts.size(); i++) {
                        prodLocations.add(selectedProducts.get(i).location);
                    }
                    Intent intent= new Intent(ProductListActivity.this, GridActivity.class);
                    intent.putStringArrayListExtra("locations", prodLocations);
                    intent.putExtra("showPath", true);
                    startActivity(intent);
                }
            });
        } else {
            add_or_path_button.setText("Add Product");
            add_or_path_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ProductListActivity.this, ProdAddActivity.class);
                    startActivityForResult(intent, ADD_PROD_ACTIVITY_REQUEST_CODE);
                }
            });
        }



        setJSON();
        populateItemList(json);
        populateTable();


        Button searchButton= (Button) findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                TextInputEditText searchText= (TextInputEditText) findViewById(R.id.search_text);
                String text= searchText.getText().toString();
                Log.i("Button", "Search text is "+text);
                TableLayout table = (TableLayout) findViewById(R.id.product_table);

                for(int i=1; i<table.getChildCount(); i++){
                    TableRow row = (TableRow) table.getChildAt(i);
                    String productName=((TextView)((LinearLayout)row.getChildAt(0)).getChildAt(1)).getText().toString();
                    Log.i("Button", "Product name is "+productName);
                    if (productName.contains(text)){
                        row.setVisibility(View.VISIBLE);
                    } else {
                        row.setVisibility(View.GONE);
                    }
                }
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == ADD_PROD_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                String returnString = data.getStringExtra("product");
                Product newProd=(new Gson()).fromJson(returnString,new TypeToken<Product>(){}.getType());

                Log.i("Returned", returnString);


                //restoring image, converting to Base64, adding to product list, and then refreshing table
                try {
                    File f=new File(newProd.pic, "newProd.jpg");
                    Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

                    ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                    b.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
                    byte[] imageBytes2 = baos2.toByteArray();
                    newProd.pic = Base64.encodeToString(imageBytes2, Base64.DEFAULT);

                    productList.add(newProd);
                    clearTable();
                    populateTable();

                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }

            }
        }
    }
}
