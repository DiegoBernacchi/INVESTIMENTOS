package br.com.testeandroid.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;
import br.com.testeandroid.R;
import br.com.testeandroid.adapter.ListaCardFundoAdapter;
import br.com.testeandroid.delegate.BackPressedDelegate;
import br.com.testeandroid.delegate.ExecutaTaskDelegate;
import br.com.testeandroid.model.Fundo;
import br.com.testeandroid.task.ProgressoTask;
import br.com.testeandroid.web.ConexaoWeb;

public class ListaFundoFragment extends Fragment implements BackPressedDelegate {

    private MainActivity mainActivity;
    private ConexaoWeb conexaoWeb;

    private static String TAG_ERRO = "ERRO";
    private static String TAG_FUNDO = "FUNDO";

    private EditText edtFiltrarFundo;
    private RecyclerView rclListaFundos;
    private ListaCardFundoAdapter listaCardFundoAdapter;

    private List<Fundo> listaDeFundos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_lista_fundo, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainActivity = (MainActivity) this.getActivity();

        if (mainActivity != null) {
            mainActivity.setOnBackClickListener(this);
            if (mainActivity.getSupportActionBar() != null) {
                mainActivity.getSupportActionBar().setTitle(mainActivity.getResources().getString(R.string.titulo_lista_fundos));
            }
        }

        conexaoWeb = new ConexaoWeb(mainActivity);

        edtFiltrarFundo = mainActivity.findViewById(R.id.edtFiltrarFundo);
        edtFiltrarFundo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listaCardFundoAdapter.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        rclListaFundos = mainActivity.findViewById(R.id.rclListaFundos);
        rclListaFundos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mainActivity);
        rclListaFundos.setLayoutManager(layoutManager);
        rclListaFundos.setItemAnimator(new DefaultItemAnimator());

        buscarDados();
    }

    private void buscarDados() {
        if (conexaoWeb.verificarConexaoInternet()) {
            new ProgressoTask(mainActivity, "Aguarde...", "Carregando dados", new ExecutaTaskDelegate() {
                @Override
                public Bundle executar() {
                    Bundle bundle = new Bundle();
                    try {
                        listaDeFundos = conexaoWeb.listaDeFundos();
                    } catch (Exception e) {
                        bundle.putString(TAG_ERRO, e.getMessage());
                    }
                    return bundle;
                }

                @Override
                public void tratarRetorno(Bundle dados) {
                    if (dados.getString(TAG_ERRO) != null) {
                        new AlertDialog.Builder(mainActivity)
                                .setMessage("Erro ao carregar dados " + dados.getString(TAG_ERRO))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mainActivity.finish();
                                    }

                                })
                                .show();
                    } else {
                        listaCardFundoAdapter = new ListaCardFundoAdapter(mainActivity, listaDeFundos);
                        listaCardFundoAdapter.setOnItemClickListener(new ListaCardFundoAdapter.ClickListener() {
                            @Override
                            public void onItemClick(Fundo fundo) {
                                Bundle args = new Bundle();
                                args.putSerializable(TAG_FUNDO, fundo);

                                DetalheFundoFragment detalheFundoFragment = new DetalheFundoFragment();
                                detalheFundoFragment.setArguments(args);

                                FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_main, detalheFundoFragment);
                                fragmentTransaction.commit();
                            }
                        });
                        rclListaFundos.setAdapter(listaCardFundoAdapter);
                    }
                }

            }).execute();
        } else {
            new AlertDialog.Builder(mainActivity)
                    .setMessage("Sem conexão com a internet|")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mainActivity.finish();
                        }

                    })
                    .show();
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(mainActivity)
                .setMessage("Deseja sair do sistema?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainActivity.finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

}