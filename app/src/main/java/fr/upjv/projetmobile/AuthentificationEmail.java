package fr.upjv.projetmobile;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AuthentificationEmail extends AppCompatActivity {
    //Reference des EditText presents
    private EditText logView;
    private EditText mdpView;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logView = (EditText) findViewById(R.id.id_logView);
        mdpView = (EditText) findViewById(R.id.id_mdpView);

        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.layout_authentification_email);
    }


    @Override
    public void onStart() {
        super.onStart();
        // On verifie s'il y a un utilisateur present et si oui, on renvoie vers ActivitePrincipale
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    //OnClick du bouton connection sur l'activite, recupere les valeurs des EditText presents
    public void onClickSignIn(View view){
        logView = (EditText) findViewById(R.id.id_logView);
        mdpView = (EditText) findViewById(R.id.id_mdpView);
        String email = logView.getText().toString();
        String mdp = mdpView.getText().toString();
        signIn(email, mdp);
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Connexion valide, on renvoie vers l'activite principale
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            reload();
                        } else {
                            // Connexion refusee, on fait un toast avec l'erreur
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthentificationEmail.this, "Utilisateur introuvable",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    //En cas d'utilisateur present, on renvoie vers l'activite principale
    private void reload() {
        Toast.makeText(AuthentificationEmail.this, "Connecté avec succès !",
                Toast.LENGTH_SHORT).show();
        Intent activitePrincipale = new Intent(this,ActivitePrincipale.class);
        startActivity(activitePrincipale);
    }

    public void onClickInscription(View view){
        Intent activiteInscription = new Intent(this,Inscription.class);
        startActivity(activiteInscription);
    }
}