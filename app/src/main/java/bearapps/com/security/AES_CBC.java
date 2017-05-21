package bearapps.com.security;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.spongycastle.jce.provider.BouncyCastleProvider;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AES_CBC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes__cbc);
    }


    //AES/CTR/NoPadding
    private void encrypt(){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", new BouncyCastleProvider());
            KeyGenerator kg=KeyGenerator.getInstance("AES/CBC/PKCS5Padding");
            SecretKey key=kg.generateKey();
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] output=cipher.doFinal("someString".getBytes());
            Log.d("TAG",output.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
