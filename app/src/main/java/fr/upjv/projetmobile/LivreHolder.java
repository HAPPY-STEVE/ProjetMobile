package fr.upjv.projetmobile;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import fr.upjv.projetmobile.model.Livre;


public class LivreHolder extends RecyclerView.ViewHolder{

    private TextView textViewPourLeNom;

    private EcouteurListe monEcouteur;


    public LivreHolder(@NonNull View itemView, EcouteurListe monEcouteur) {
        super(itemView);

        textViewPourLeNom=itemView.findViewById(R.id.id_nom_ligne_textview);

        this.monEcouteur = monEcouteur;

        itemView.setOnClickListener(l->{
            monEcouteur.clicked(getAdapterPosition());
        });

    }

    public void mettreAJourLivreHolder(Livre unLivre){
        if(Objects.nonNull(unLivre))
            textViewPourLeNom.setText(unLivre.getTitre());
    }
}
