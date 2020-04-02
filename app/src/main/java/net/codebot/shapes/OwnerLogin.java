package net.codebot.shapes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctionsException;



public class OwnerLogin extends AppCompatActivity {

    boolean auth_status;

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

        LoginService.validateLogin(username, password).addOnCompleteListener(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull final Task<Boolean> task) {
                Log.i("onComplete", "got to onComplete");
                if (!task.isSuccessful()) {
                    Log.i("ERROR", "Exception");
                    Exception e = task.getException();
                    if (e instanceof FirebaseFunctionsException) {
                        FirebaseFunctionsException ffe = (FirebaseFunctionsException) e;
                        FirebaseFunctionsException.Code code = ffe.getCode();
                        Object details = ffe.getDetails();
                        Log.i("Error", ffe.getMessage());
                        Toast.makeText(OwnerLogin.this, "Error Validating Login", Toast.LENGTH_SHORT).show();
                        auth_status = false;
                    }
                } else {
                    Log.i("RETURNED", "Returned Login " + task.getResult());
    //                    Toast.makeText(ProductListActivity.this, "Product Successfully Added", Toast.LENGTH_SHORT).show();
                       auth_status = task.getResult();
                }
            }
        });

        return ((username.equals("rnnn") && password.equals("storefront")) ||
                (username.equals("admin") && password.equals("password")));

//        return LoginService.validateLogin(username, password);
//        Log.i("auth_status_pre", String.valueOf(auth_status));
//        Log.i("auth_status_post", String.valueOf(auth_status));

    }

    public void TryLogin(View v) {

        TextView LoginStatus = findViewById(R.id.LoginResult);

//        Authenticate(v);
//
//        Log.i("auth_status", String.valueOf(auth_status));
//
//        try
//        {
//            Thread.sleep(2000);
//        }
//        catch(InterruptedException ex)
//        {
//            Thread.currentThread().interrupt();
//        }

        if (Authenticate(v)) {
            LoginStatus.setText("Login Successful!");
            LoginStatus.setTextColor(Color.GREEN);

            Intent intent = new Intent(this, OwnerView.class);
            startActivity(intent);

        }
        else {
            LoginStatus.setText("Login Failed. Try Again!");
            LoginStatus.setTextColor(Color.RED);
        }

    }
}
