package fr.upjv.projetmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inscription extends AppCompatActivity {
    private EditText logViewInscription;
    private EditText mdpViewInscription;
    private static final String TAG = "Inscription";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.layout_inscription);

        logViewInscription = findViewById(R.id.id_logView_inscription);
        mdpViewInscription = findViewById(R.id.id_mdpView_inscription);

    }

    private void createAccount(String email, String password) {
        //On cree l'utilisateur avec les donnees recuperes des EditText
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // donnees valides, on a cree un utilisateur dans la bdd firebase
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Inscription.this, "Utilisateur crée !",
                                    Toast.LENGTH_LONG).show();
                            updateUI(user);
                        } else {
                            // on renvoie l'erreur si jamais une erreur existe dans l'inscription (mdp trop court, email invalide etc...)
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Inscription.this, "Erreur dans l'inscription.",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }

    public void onClickInscription(View view){
        Log.d(TAG, "createUserWithEmail:success");

        Log.d(TAG, logViewInscription.getText().toString());

        if(isEmailValid(logViewInscription.getText().toString())){
            createAccount(logViewInscription.getText().toString(), mdpViewInscription.getText().toString());
        }
    }

    //Expression reguliere pour email
    public boolean isEmailValid(String email)
    {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //On renvoie l'utilisateur vers l'authentification si necessaire (qui va le renvoyer vers ActivitePrincipale car connecté)
    private void updateUI(FirebaseUser user) {
        Intent activiteConnexion = new Intent(this,AuthentificationEmail.class);
        startActivity(activiteConnexion);
    }

    public void onClickQuitter(View view){
        finish();
    }
}