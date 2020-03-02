package net.codebot.shapes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OwnerLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);
    }

    public boolean Authenticate(View v){

        EditText user = findViewById(R.id.Username);
        String username = user.getText().toString().trim();

        EditText pass = findViewById(R.id.Password);
        String password = pass.getText().toString();

        return username.equals(password);
    }

    public void TryLogin(View v) {

        TextView LoginStatus = findViewById(R.id.LoginResult);

        if (Authenticate(v)) {
            LoginStatus.setText("Login Successful!");
            LoginStatus.setTextColor(Color.GREEN);

            Intent intent = new Intent(this, OwnerView.class);
            startActivity(intent);

        }
        else {
            LoginStatus.setText("Login Failed. Try Again!!");
            LoginStatus.setTextColor(Color.RED);
        }

    }
}
