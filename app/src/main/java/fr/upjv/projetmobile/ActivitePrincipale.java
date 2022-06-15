package fr.upjv.projetmobile;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class ActivitePrincipale extends AppCompatActivity {
    private Button deconnexionView;
    private FirebaseFirestore maBaseFireStore;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_activite_principale, null);
        deconnexionView = (Button) view.findViewById(R.id.id_button_deconnexion_activiteprincipale);

        setContentView(R.layout.layout_activite_principale);
        if(currentUser != null){
            deconnexionView.setVisibility(View.VISIBLE);
        }
    }

    public void onClickAuthentification(View view){
        Intent authentification = new Intent(this,AuthentificationEmail.class);
        startActivity(authentification);
    }
    public void onClickQuitterActivitePrincipale(View view) {
        FirebaseAuth.getInstance().signOut();
        this.finishAffinity();
    }



    public void onClickSaisirActivitePrincipale(View view) {
        Intent activiteSaisir = new Intent(this,SaisieLivre.class);
        startActivity(activiteSaisir);
    }

    public void onClickAfficherSelection(View view) {
        Intent unIntent=getIntent();

        Bundle unBundle=unIntent.getExtras();
        if(Objects.nonNull(unBundle)){
            String livreToString = unBundle.getString("livre");
            Toast.makeText(ActivitePrincipale.this, livreToString, Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(ActivitePrincipale.this, "Pas de livre séléctionné", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickDeconnexion(View view){
        FirebaseAuth.getInstance().signOut();
        Intent authentification = new Intent(this,AuthentificationEmail.class);
        startActivity(authentification);
    }
    public void onClickAfficherListe(View view) {
        Intent activiteListe = new Intent(this,ListeLivre.class);
        startActivityForResult(activiteListe,1);
    }

    public void onClickConnexionBDD(View view) {
        maBaseFireStore = FirebaseFirestore.getInstance();
        Toast.makeText(ActivitePrincipale.this, "Connexion OK", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == ActivitePrincipale.RESULT_OK){
                String livreToString = data.getStringExtra("livre");
                Toast.makeText(ActivitePrincipale.this, livreToString, Toast.LENGTH_SHORT).show();
            }
            if (resultCode == ActivitePrincipale.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    } //onActivityResult
}