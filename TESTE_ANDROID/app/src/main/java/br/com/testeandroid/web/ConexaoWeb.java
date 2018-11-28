package br.com.testeandroid.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.List;
import br.com.testeandroid.model.Fundo;
import br.com.testeandroid.service.InvestimentoService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConexaoWeb {

    private static String baseUrl = "https://s3.amazonaws.com/orama-media/";
    private Context context;

    public ConexaoWeb(Context context) {
        this.context = context;
    }

    public boolean verificarConexaoInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

    public List<Fundo> listaDeFundos() throws Exception {
        InvestimentoService service = getService(InvestimentoService.class);
        Call<List<Fundo>> call = service.listaDeFundos();
        Response<List<Fundo>> response = call.execute();
        return response.body();
    }

    private <S> S getService(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

}
