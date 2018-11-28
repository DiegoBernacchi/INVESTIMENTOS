package br.com.testeandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import br.com.testeandroid.R;
import br.com.testeandroid.model.Fundo;

public class ListaCardFundoAdapter extends RecyclerView.Adapter<ListaCardFundoAdapter.CardFundoViewHolder> implements Filterable {

    private Context context;

    private List<Fundo> listaFundos;
    private List<Fundo> listaFundosExibidos;

    private static ClickListener clickListener;

    public ListaCardFundoAdapter(Context context, List<Fundo> listaFundos) {
        this.context = context;
        this.listaFundos = listaFundos;
        this.listaFundosExibidos = this.listaFundos;
    }

    @Override
    public CardFundoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lista_fundo, parent, false);
        return new CardFundoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardFundoViewHolder holder, int position) {

        switch (listaFundosExibidos.get(position).getEspecificacao().getRisco().getValor()) {
            case 1:
                holder.txtBarraRisco.setBackgroundColor(context.getResources().getColor(R.color.conservador));
                break;
            case 2:
                holder.txtBarraRisco.setBackgroundColor(context.getResources().getColor(R.color.moderado));
                break;
            case 3:
                holder.txtBarraRisco.setBackgroundColor(context.getResources().getColor(R.color.experiente));
                break;
        }

        holder.txtNomeDoFundo.setText(listaFundosExibidos.get(position).getNomeDoFundo());
        holder.txtValAplicacao.setText(String.valueOf("R$ " + String.format(new Locale("pt", "BR"), "%.2f", listaFundosExibidos.get(position).getOperacao().getAplicacaoMinima())));
    }

    @Override
    public int getItemCount() {
        return listaFundosExibidos.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ListaCardFundoAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(Fundo fundo);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listaFundosExibidos = listaFundos;
                } else {
                    List<Fundo> listaFiltrada = new ArrayList<>();
                    for (Fundo fundo : listaFundos) {
                        if (fundo.getNomeDoFundo().toLowerCase().contains(charString.toLowerCase())) {
                            listaFiltrada.add(fundo);
                        }
                    }

                    listaFundosExibidos = listaFiltrada;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listaFundosExibidos;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listaFundosExibidos = (ArrayList<Fundo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class CardFundoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtBarraRisco, txtNomeDoFundo, txtValAplicacao;

        CardFundoViewHolder(View view) {
            super(view);

            view.setOnClickListener(this);
            this.txtBarraRisco = view.findViewById(R.id.txtBarraRisco);
            this.txtNomeDoFundo = view.findViewById(R.id.txtNomeDoFundo);
            this.txtValAplicacao = view.findViewById(R.id.txtValAplicacao);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(listaFundosExibidos.get(getAdapterPosition()));
        }
    }

}

