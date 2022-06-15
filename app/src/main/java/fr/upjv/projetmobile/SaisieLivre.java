package fr.upjv.projetmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SaisieLivre extends AppCompatActivity {

    private EditText saisieAuteur;
    private EditText saisieTitre;
    private FirebaseFirestore maBaseFireStore;

    //les données à manipuler
    Map<String, Object> donne = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_saisie_livre);

        //on se connecte a firestore a la connexion
        onClickConnexionBDD();

        saisieAuteur = findViewById(R.id.saisieAuteur);
        saisieTitre = findViewById(R.id.saisieTitre);
    }

    public void onClickValiderSaisie(View view) {

        // on recupère les values des deux champs saisis
        String leAuteur = saisieAuteur.getText().toString();
        String leTitre = saisieTitre.getText().toString();

        donne.put("auteur", leAuteur);
        donne.put("titre", leTitre);

        //la fonction firestore
        maBaseFireStore.collection("Bibliotheque")
                .document()
                .set(donne)
                //succès
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SaisieLivre.this,"Livre ajouté", Toast.LENGTH_SHORT).show();

                            }
                        }
                )
                //echec
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SaisieLivre.this,"Echec", Toast.LENGTH_SHORT).show();
                            }
                        }

                );

    }



    public void onClickQuitterSaisie(View view) {
        finish();
    }

    public void onClickConnexionBDD() {
        maBaseFireStore = FirebaseFirestore.getInstance();
        Toast.makeText(SaisieLivre.this, "Connexion OK", Toast.LENGTH_SHORT).show();

    }
}