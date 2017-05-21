package bearapps.com.security;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.spongycastle.jsse.provider.BouncyCastleJsseProvider;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBKDF2 extends AppCompatActivity {

    EditText input;
    EditText salt;
    TextView output;
    Button copy;
    Button convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbkdf2);
        input= (EditText) findViewById(R.id.input_PBKDF2);
        output=(TextView) findViewById(R.id.output_PBKDF2);
        copy=(Button) findViewById(R.id.copy_button);
        convert=(Button) findViewById(R.id.convert_button);
        salt=(EditText) findViewById(R.id.input_salt);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PBKDF2_apply(input.getText().toString(),salt.getText().toString());
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyText(output.getText().toString());
            }
        });


    }

    private void copyText(String s) {
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip=ClipData.newPlainText("PBKDF2Output",s);
        clipboard.setPrimaryClip(clip);
    }

    private void PBKDF2_apply(String password, String salt) {
        //Provider PBKDF2=Security.getProvider("PBKDF2WithHmacSHA256");
        //PBKDF2.
        salt=salt!=null && !salt.equals("")?salt:" ";
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 4000, 2*20 * 8);
        SecretKeyFactory f = null;
        String result=" ";
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            result= Base64.encodeToString(f.generateSecret(spec).getEncoded(),Base64.DEFAULT);
            Log.e("PBKDF2",result.toCharArray().length+" ");
            output.setText(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }
}
