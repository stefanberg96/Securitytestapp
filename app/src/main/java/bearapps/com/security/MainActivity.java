package bearapps.com.security;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.spongycastle.jsse.provider.BouncyCastleJsseProvider;

import java.security.Security;


public class MainActivity extends AppCompatActivity {
    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pbkdf2=(Button) findViewById(R.id.PBKDF2_button);
        pbkdf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PBKDF2.class);
                startActivity(intent);
            }
        });
    }
}
