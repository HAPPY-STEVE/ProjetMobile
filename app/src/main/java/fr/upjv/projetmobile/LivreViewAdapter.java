package fr.upjv.projetmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.upjv.projetmobile.model.Livre;

public class LivreViewAdapter extends RecyclerView.Adapter<LivreHolder>{

    private final EcouteurListe monEcouteur;
    private List<Livre> lesLivres;

    //constructeur avec la liste de livre et l'écouteur
    public LivreViewAdapter(List<Livre> lesLivres,EcouteurListe monEcouteur) {
        this.lesLivres = lesLivres;
        this.monEcouteur = monEcouteur;
    }

    @NonNull
    @Override
    public LivreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //on recupere le conteneur de ligne et on le branche avec le holder
        LayoutInflater monLayoutInflater = LayoutInflater.from(parent.getContext());

        View uneView =
                monLayoutInflater.inflate(
                        R.layout.descripteur_de_ligne,
                        parent, false);

        return new LivreHolder(uneView,monEcouteur);
    }



    //on met a jour
    @Override
    public void onBindViewHolder(@NonNull LivreHolder holder, int position) {

        holder.mettreAJourLivreHolder(lesLivres.get(position));
    }

    //on recupere la taille de la liste
    @Override
    public int getItemCount() {
        return lesLivres.size();
    }
}
