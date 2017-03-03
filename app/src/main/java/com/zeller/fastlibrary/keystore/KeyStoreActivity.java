package com.zeller.fastlibrary.keystore;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.zeller.fastlibrary.R;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zeller.fastlibrary.R.layout.activity_key_store;

/**
 * Created by Zellerpooh on 17/2/27.
 */

public class KeyStoreActivity extends AppCompatActivity {
    private static final String TAG = "KeyStoreActivity";
    @Bind(R.id.tv_data)
    EditText tvData;
    @Bind(R.id.encrypt)
    Button encrypt;
    @Bind(R.id.decrypt)
    Button decrypt;
    @Bind(R.id.tv_text)
    TextView tvShow;

    private Encryptor encryptor;
    private Decryptor decryptor;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(activity_key_store);
        ButterKnife.bind(this);

//        encryptor = new Encryptor();
//
//        try {
//            decryptor = new Decryptor();
//        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException |
//                IOException e) {
//            e.printStackTrace();
//        }
    }

    @OnClick({R.id.encrypt, R.id.decrypt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.encrypt:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    encryptText();
                }
                break;
            case R.id.decrypt:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    decryptText();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void decryptText() {
        try {
            tvShow.setText(decryptor
                    .decryptData(TAG, encryptor.getEncryption(), encryptor.getIv()));
        } catch (UnrecoverableEntryException | NoSuchAlgorithmException |
                KeyStoreException | NoSuchPaddingException | NoSuchProviderException |
                IOException | InvalidKeyException e) {
            Log.e(TAG, "decryptData() called with: " + e.getMessage(), e);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void encryptText() {

        try {
            final byte[] encryptedText = encryptor
                    .encryptText(TAG, tvData.getText().toString());
            tvShow.setText(Base64.encodeToString(encryptedText, Base64.DEFAULT));
        } catch (UnrecoverableEntryException | NoSuchAlgorithmException | NoSuchProviderException |
                KeyStoreException | IOException | NoSuchPaddingException | InvalidKeyException e) {
            Log.e(TAG, "onClick() called with: " + e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException | SignatureException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
