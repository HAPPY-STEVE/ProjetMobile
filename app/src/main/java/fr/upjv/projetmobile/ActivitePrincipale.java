package fr.upjv.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class ActivitePrincipale extends AppCompatActivity {

    private FirebaseFirestore maBaseFireStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activite_principale);
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