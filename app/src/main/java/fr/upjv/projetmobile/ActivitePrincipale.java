package fr.upjv.projetmobile;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//Correspond a l'activite principale regroupant les differents boutons amenant aux autres activites
public class ActivitePrincipale extends AppCompatActivity {
    private Button deconnexionView;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //On inflate pour eviter que le bouton soit reconnu comme nulle
        LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_activite_principale, null);
        deconnexionView = (Button) view.findViewById(R.id.id_button_deconnexion_activiteprincipale);

        setContentView(R.layout.layout_activite_principale);

        //Si un utilisateur est reconnu, on affiche le bouton de deconnexion
        if(currentUser != null){
            deconnexionView.setVisibility(View.VISIBLE);
        }
    }

    //Quitte l'application directement
    public void onClickQuitterActivitePrincipale(View view) {
        FirebaseAuth.getInstance().signOut();
        this.finishAffinity();
    }



    //Demarre l'activite SaisieLivre via bouton
    public void onClickSaisirActivitePrincipale(View view) {
        Intent activiteSaisir = new Intent(this,SaisieLivre.class);
        startActivity(activiteSaisir);
    }



    //Demarre l'activite Authentification via bouton, log-out l'utilisateur
    public void onClickDeconnexion(View view){
        FirebaseAuth.getInstance().signOut();
        Intent authentification = new Intent(this,AuthentificationEmail.class);
        startActivity(authentification);
    }

    //Demarre l'activite ActiviteListe via bouton
    public void onClickAfficherListe(View view) {
        Intent activiteListe = new Intent(this,ListeLivre.class);
        startActivityForResult(activiteListe,1);
    }



    //Recupere les resultats de l'activite ListeLivre, correspond au livre choisi
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Si le requestCode correspond a celui lance pour l'activite ListeLivre, on peut recuperer les valeurs adequates
        if (requestCode == 1) {
            if(resultCode == ActivitePrincipale.RESULT_OK){
                //On recupere les informations du livre passes par un bundle
                String livreToString = data.getStringExtra("livre");
                Toast.makeText(ActivitePrincipale.this, livreToString, Toast.LENGTH_SHORT).show();
            }
            if (resultCode == ActivitePrincipale.RESULT_CANCELED) {
            }
        }
    }
}