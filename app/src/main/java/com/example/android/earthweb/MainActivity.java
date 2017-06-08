package com.example.android.earthweb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView btnCadastrar, btnAumentar, btnDiminuir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = (TextView) findViewById(R.id.btnCadastrar);
        btnAumentar = (TextView) findViewById(R.id.btnAumentar);
        btnDiminuir = (TextView) findViewById(R.id.btnDiminuir);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrarIntent = new Intent(MainActivity.this, CadastrarRanking.class);
                startActivityForResult(cadastrarIntent, 1);
            }
        });
        btnAumentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aumentarIntent = new Intent(MainActivity.this, AumentarRanking.class);
                startActivityForResult(aumentarIntent, 2);
            }
        });
        btnDiminuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diminuirIntent = new Intent(MainActivity.this, DiminuirRanking.class);
                startActivityForResult(diminuirIntent, 3);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if (resultCode == 1){
            }
        }
        if(requestCode == 2){
            if (resultCode == 1){
            }
        }
        if(requestCode == 3){
            if (resultCode == 1){
            }
        }
    }
}
