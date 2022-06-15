package fr.upjv.projetmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.upjv.projetmobile.model.Livre;

public class ListeLivre extends AppCompatActivity {

    private List<Livre> lesLivres;


    private TextView leTextView;
    private RecyclerView leRecyclerView;


    private FirebaseFirestore maBaseFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activite_liste_livre);

        //la liste qui recupère les livres de la BDD
        List<Livre> livreRecupere = new ArrayList();


        maBaseFireStore = FirebaseFirestore.getInstance();
        Toast.makeText(ListeLivre.this, "Connexion OK", Toast.LENGTH_SHORT).show();


        //la fonction get firestore
        maBaseFireStore
                .collection("Bibliotheque")
                .get()
                //ajout du listener de reussite
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //boucle sur tous les livre récupérés
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("lee", document.getId() + " => " + document.get("titre"));
                                String auteur = (String) document.get("auteur");
                                String titre = (String) document.get("titre");

                                //on les ajoute dans la liste
                                Livre l = new Livre(auteur, titre);
                                livreRecupere.add(l);


                            }

                            // on fait un stream pour remplir lesLivres
                            lesLivres = livreRecupere.stream()
                                    .map(c -> new Livre(c.getTitre(), c.getAuteur()))
                                    .collect(Collectors.toList());


                            //on implémente la méthode au clic d'un item
                            EcouteurListe monEcouteur = new EcouteurListe() {
                                @Override
                                public void clicked(int position) {
                                    //on envoie le resultat dans l'activité principale
                                    String livreToString = lesLivres.get(position).toString();
                                    Intent monIntent = new Intent(ListeLivre.this, ActivitePrincipale.class);

                                    Bundle unBundle = new Bundle();
                                    unBundle.putString("livre", livreToString);

                                    monIntent.putExtras(unBundle);
                                    setResult(ListeLivre.RESULT_OK, monIntent);

                                    finish();
                                }
                            };

                            // on peuple le view adapter avec lesLivres et l'ecouteur
                            LivreViewAdapter monAdapter = new LivreViewAdapter(lesLivres, monEcouteur);
                            leRecyclerView.setLayoutManager(new LinearLayoutManager(ListeLivre.this));
                            leRecyclerView.setAdapter(monAdapter);


                        } else {
                            Log.d("lee", "Error getting documents: ", task.getException());
                        }
                    }
                });


        leTextView = findViewById(R.id.id_textView);
        leRecyclerView = findViewById(R.id.id_recyclerView);


    }

    public void onClickRetourLister(View view) {
        super.onBackPressed();
    }


}