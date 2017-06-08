package com.example.android.earthweb;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarRanking extends AppCompatActivity {

    EditText editNome, editCPF, editPapel, editPlastico, editMetal, editVidro;
    Button btnCadastrar;

    String nome, CPF, papel, plastico, metal, vidro, url, parametros;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_ranking);

        editNome = (EditText) findViewById(R.id.editNome);
        editCPF = (EditText) findViewById(R.id.editCPF);
        editPapel = (EditText) findViewById(R.id.editPapel);
        editPlastico = (EditText) findViewById(R.id.editPlastico);
        editMetal = (EditText) findViewById(R.id.editMetal);
        editVidro = (EditText) findViewById(R.id.editVidro);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        progress = new ProgressDialog(CadastrarRanking.this);
        progress.setMessage("Realizando cadastro...");
        progress.setCanceledOnTouchOutside(false);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()){

                    nome = editNome.getText().toString();
                    CPF = editCPF.getText().toString();
                    papel = editPapel.getText().toString();
                    plastico = editPlastico.getText().toString();
                    metal = editMetal.getText().toString();
                    vidro = editVidro.getText().toString();

                    if(nome.isEmpty() || CPF.isEmpty()){
                        Snackbar.make(findViewById(R.id.layoutCadastrar), "O campo de nome e CPF não podem estar vazios", Snackbar.LENGTH_LONG).show();
                    }
                    else{
                        if(papel.isEmpty()){
                            papel = "0";
                        }
                        if(plastico.isEmpty()){
                            plastico = "0";
                        }
                        if(metal.isEmpty()){
                            metal = "0";
                        }
                        if(vidro.isEmpty()){
                            vidro = "0";
                        }
                        url = "http://earthweb.candlet.com.br/pontos.php";
                        parametros = "identificador=" + "batatavelha" + "&name=" + nome + "&cpf=" + CPF + "&papel=" + papel + "&plastico=" + plastico + "&metal=" + metal + "&vidro=" + vidro;
                        new enviar().execute(url, parametros);
                    }
                }
                else{
                    Snackbar.make(findViewById(R.id.layoutCadastrar), "Nenhuma conexão foi encontrada", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
    private class enviar extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progress.show();
        }
        @Override
        protected String doInBackground(String... urls){
            return Conexao.postDados(urls[0], urls[1]);
        }
        @Override
        protected void onPostExecute(String resultado){
            progress.dismiss();
            if (resultado.contains("ok")){
                setResult(1);
                finish();
            }
            else if (resultado.contains("erro")){
                Snackbar.make(findViewById(R.id.layoutCadastrar), "Erro ao se cadastrar", Snackbar.LENGTH_LONG).show();
            }
            else if (resultado == null){
                Snackbar.make(findViewById(R.id.layoutCadastrar), "Erro ao se cadastrar...", Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
