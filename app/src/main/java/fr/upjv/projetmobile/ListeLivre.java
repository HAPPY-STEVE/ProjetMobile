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

    Map<String, Object> donne = new HashMap<>();

    //private EcouteurARecyclerView monEcouteur;

    private FirebaseFirestore maBaseFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activite_liste_livre);

        List<Livre>livreRecupere = new ArrayList();
//        Random random=new Random();
//        lesLivres=
//                IntStream.range(0, 100)
//                        .mapToObj(i->{
//                            return new Livre("Auteur"+i,
//                                    "Titre"+i);})
//                        .collect(Collectors.toList());



        maBaseFireStore = FirebaseFirestore.getInstance();
        Toast.makeText(ListeLivre.this, "Connexion OK", Toast.LENGTH_SHORT).show();

        maBaseFireStore
                .collection("Bibliotheque")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("lee", document.getId() + " => " + document.get("titre"));
                                String auteur = (String) document.get("auteur");
                                String titre = (String) document.get("titre");
                                Livre l = new Livre(auteur,titre);
                                livreRecupere.add(l);


                            }

                            System.out.println("livre recupéré"+livreRecupere);


                            lesLivres = livreRecupere.stream()
                                    .map( c -> new Livre(c.getTitre(),c.getAuteur()) )
                                    .collect( Collectors.toList() );


                            System.out.println("lesLivres"+lesLivres);

                            EcouteurListe monEcouteur = new EcouteurListe() {
                                @Override
                                public void clicked(int position) {
                                    //leTextView.setText(lesLivres.get(position).toString());


                                    String livreToString=lesLivres.get(position).toString();


                                    Intent monIntent=new Intent(ListeLivre.this, ActivitePrincipale.class);


                                    Bundle unBundle=new Bundle();
                                    unBundle.putString("livre", livreToString);

                                    monIntent.putExtras(unBundle);

                                    startActivity(monIntent);
                                }
                            };


                            LivreViewAdapter monAdapter=new LivreViewAdapter(lesLivres,monEcouteur);
                            leRecyclerView.setLayoutManager(new LinearLayoutManager(ListeLivre.this));
                            leRecyclerView.setAdapter(monAdapter);



                        } else {
                            Log.d("lee", "Error getting documents: ", task.getException());
                        }
                    }
                });

        System.out.println("lesLivres"+lesLivres);

        leTextView=findViewById(R.id.id_textView);
        leRecyclerView=findViewById(R.id.id_recyclerView);


    }

    public void onClickRetourLister(View view) {
        super.onBackPressed();
    }

    private void initLesLivres() {




//


    }


}