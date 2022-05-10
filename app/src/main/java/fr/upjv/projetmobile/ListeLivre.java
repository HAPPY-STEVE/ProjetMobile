package fr.upjv.projetmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
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

    //private EcouteurARecyclerView monEcouteur;

    private FirebaseFirestore maBaseFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activite_liste_livre);

        initLesLivres();

        leTextView=findViewById(R.id.id_textView);
        leRecyclerView=findViewById(R.id.id_recyclerView);
    }

    public void onClickRetourLister(View view) {
        finish();
    }

    private void initLesLivres() {

        /*lesLivres=
                IntStream.range(0, 100)
                        .mapToObj(i->{
                            Random random = new Random();
                            return new Livre("Nom_"+i,
                                new Date(random.nextInt()));})
                        .collect(Collectors.toList());


        maBaseFireStore
                .collection("Bibliotheque")
                .document("ma_ref_doc")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Toast.makeText(ActivitePrincipale.this, "la lecture est terminée", Toast.LENGTH_SHORT).show();
                        if( task.isComplete()){
                            DocumentSnapshot undocumentSnapshot = task.getResult();
                            Map<String, Object> donneeLue = undocumentSnapshot.getData();

                            for (String s : donneeLue.keySet()){
                                Log.d("Lee", ""+donneeLue.get(s));
                            }
                        }


                    }
                });*/




    }

}