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

    public LivreViewAdapter(List<Livre> lesLivres,EcouteurListe monEcouteur) {
        this.lesLivres = lesLivres;
        this.monEcouteur = monEcouteur;
    }

    @NonNull
    @Override
    public LivreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater monLayoutInflater = LayoutInflater.from(parent.getContext());

        View uneView =
                monLayoutInflater.inflate(
                        R.layout.descripteur_de_ligne,
                        parent, false);

        return new LivreHolder(uneView,monEcouteur);
    }



    @Override
    public void onBindViewHolder(@NonNull LivreHolder holder, int position) {

        holder.mettreAJourLivreHolder(lesLivres.get(position));
    }

    @Override
    public int getItemCount() {
        return lesLivres.size();
    }
}
