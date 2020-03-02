package net.codebot.shapes;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class ProdAddActivity extends AppCompatActivity {
    int CAMERA_REQUEST = 1888;
    int GRID_REQUEST = 1;

    String imagePath;
    String location;




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GRID_REQUEST){
            char row_num = data.getCharExtra("ROW_NUM",'A');
            int col_num = data.getIntExtra("COL_NUM",0);
            location=row_num+Integer.toString(col_num);
            Button b=(Button) findViewById(R.id.prod_location);
            b.setText(location + " (Click to edit)");
        }
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            Log.i("Done", "Took pic!");
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageView pic= (ImageView)findViewById(R.id.pic_display);
            pic.setImageBitmap(photo);

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            // Create imageDir
            File mypath=new File(directory,"newProd.jpg");

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mypath);
                // Use the compress method on the BitMap object to write image to the OutputStream
                photo.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            imagePath=directory.getAbsolutePath();


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_add);

        Button takePic= (Button) findViewById(R.id.take_pic);
        takePic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra("putSomething", true);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


        Button selectLocation= (Button) findViewById(R.id.prod_location);
        selectLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ProdAddActivity.this, GridActivity.class);
                intent.putExtra("showPath", false);
                startActivityForResult(intent, GRID_REQUEST);
            }
        });





        Button addProd= (Button) findViewById(R.id.add_product_button);
        addProd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int id=Integer.parseInt(((EditText)findViewById(R.id.prod_id)).getText().toString());
                String name=((EditText)findViewById(R.id.prod_name)).getText().toString();

                int quantity=Integer.parseInt(((EditText)findViewById(R.id.prod_quantity)).getText().toString());
                String pic=imagePath;

                Product newProd= new Product(id,name,pic,quantity,location);

                String stringToPassBack= (new Gson()).toJson(newProd);

                Intent intent = new Intent();
                intent.putExtra("product", stringToPassBack);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}
