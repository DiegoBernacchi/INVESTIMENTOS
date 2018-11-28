package br.com.testeandroid.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Locale;
import br.com.testeandroid.R;
import br.com.testeandroid.delegate.BackPressedDelegate;
import br.com.testeandroid.model.Fundo;

public class DetalheFundoFragment extends Fragment implements BackPressedDelegate {

    private static String TAG_FUNDO = "FUNDO";

    private MainActivity mainActivity;
    private Fundo fundo;

    private TextView txtTituloFundo, txtValNomeCompleto, txtValObjetivo, txtValPublicoAlvo, txtTipoPerfilRisco, txtValPerfilRisco, txtValDataInicio;
    private ImageView imgVideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_detalhe_fundo, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainActivity = (MainActivity) this.getActivity();

        if (mainActivity != null) {
            mainActivity.setOnBackClickListener(this);
            if (mainActivity.getSupportActionBar() != null) {
                mainActivity.getSupportActionBar().setTitle(mainActivity.getResources().getString(R.string.titulo_detalhe_do_fundo));
            }
            txtTituloFundo = mainActivity.findViewById(R.id.txtTituloFundo);
            txtValNomeCompleto = mainActivity.findViewById(R.id.txtValNomeCompleto);
            txtValObjetivo = mainActivity.findViewById(R.id.txtValObjetivo);
            txtValPublicoAlvo = mainActivity.findViewById(R.id.txtValPublicoAlvo);
            txtTipoPerfilRisco = mainActivity.findViewById(R.id.txtTipoPerfilRisco);
            txtValPerfilRisco = mainActivity.findViewById(R.id.txtValPerfilRisco);
            txtValDataInicio = mainActivity.findViewById(R.id.txtValDataInicio);
            imgVideo = mainActivity.findViewById(R.id.imgVideo);
        }

        Bundle args = this.getArguments();
        if (args != null) {
            fundo = (Fundo) args.get(TAG_FUNDO);
        }

        carregarDadosDaTela();
    }

    public void carregarDadosDaTela() {
        txtTituloFundo.setText(fundo.getNomeDoFundo());
        txtValNomeCompleto.setText(fundo.getNomeCompleto());
        txtValObjetivo.setText(fundo.getDescricao().getObjetivo());
        txtValPublicoAlvo.setText(fundo.getDescricao().getPublicoAlvo());

        switch (fundo.getEspecificacao().getRisco().getValor()) {
            case 1:
                txtTipoPerfilRisco.setBackgroundColor(mainActivity.getResources().getColor(R.color.conservador));
                break;
            case 2:
                txtTipoPerfilRisco.setBackgroundColor(mainActivity.getResources().getColor(R.color.moderado));
                break;
            case 3:
                txtTipoPerfilRisco.setBackgroundColor(mainActivity.getResources().getColor(R.color.experiente));
                break;
        }

        txtValPerfilRisco.setText(fundo.getEspecificacao().getRisco().getDescricao());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        txtValDataInicio.setText(format.format(fundo.getDataInicio()));

        if (fundo.getVideo() != null && fundo.getVideo().getUrlImagem() != null) {
            Picasso.get().load(fundo.getVideo().getUrlImagem()).into(imgVideo);
        } else {
            Picasso.get().load(R.drawable.ic_imagem).into(imgVideo);
        }
    }

    @Override
    public void onBackPressed() {
        FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, new ListaFundoFragment());
        fragmentTransaction.commit();
    }

}
