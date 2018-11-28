package br.com.testeandroid.service;

import java.util.List;
import br.com.testeandroid.model.Fundo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InvestimentoService {
    @GET("json/fund_detail_full.json?limit=1000&offset=0&serializer=fund_detail_full")
    Call<List<Fundo>> listaDeFundos();
}