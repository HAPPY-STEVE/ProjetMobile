package fr.upjv.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivitePrincipale extends AppCompatActivity {
    private Button logIn;
    private FirebaseFirestore maBaseFireStore;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logIn = findViewById(R.id.id_button_authentification_activiteprincipale);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            logIn.setVisibility(View.VISIBLE);
        }
        setContentView(R.layout.layout_activite_principale);
    }

    public void onClickAuthentification(View view){
        Intent authentification = new Intent(this,AuthentificationEmail.class);
        startActivity(authentification);
    }
    public void onClickQuitterActivitePrincipale(View view) {
        finish();
    }



    public void onClickSaisirActivitePrincipale(View view) {
        Intent activiteSaisir = new Intent(this,SaisieLivre.class);
        startActivity(activiteSaisir);
    }

    public void onClickAfficherSelection(View view) {
    }

    public void onClickAfficherListe(View view) {
        Intent activiteListe = new Intent(this,ListeLivre.class);
        startActivity(activiteListe);
    }

    public void onClickConnexionBDD(View view) {
        maBaseFireStore = FirebaseFirestore.getInstance();
    }
}