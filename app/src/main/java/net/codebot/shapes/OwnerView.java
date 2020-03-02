package net.codebot.shapes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OwnerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_view);
    }

    public void GetProducts(View v) {
        Intent intent = new Intent(this, Search.class);
        intent.putExtra("user", "owner");
        startActivity(intent);
    }
}
