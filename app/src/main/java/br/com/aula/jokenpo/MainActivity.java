package br.com.aula.jokenpo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Variável responsável pela pontuação
    private int pontuacao = 0;

    // Seleções de pedra, papel e tesoura
    public void selectPedra(View view) {
        this.opcaoSelecionada("pedra");
    }

    public void selectPapael(View view) {
        this.opcaoSelecionada("papel");
    }

    public void selectTesoura(View view) {
        this.opcaoSelecionada("tesoura");
    }

    public void opcaoSelecionada(String opcaoSelecionada) {
        // Instancia a o resultado da imagem da escolha do app, do jogador
        // e também instancia o resultado junto com a pontuaçã
        ImageView imagemResultadoApp = findViewById(R.id.imagePadraoApp);
        ImageView imagemResultadoJogador = findViewById(R.id.imagePadraoJogador);
        TextView textResultado = findViewById(R.id.textResultado);
        TextView textPontuacao = findViewById(R.id.textPontuacaoNumero);

        // Lógica de escolha da máquina
        int numeroAleatorio = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[numeroAleatorio];

        // Mudando a imagem da escolha do App (conforme a lógica do jogo)
        switch (opcaoApp) {
            case "pedra":
                imagemResultadoApp.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imagemResultadoApp.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imagemResultadoApp.setImageResource(R.drawable.tesoura);
                break;
        }

        switch (opcaoSelecionada) {
            case "pedra":
                imagemResultadoJogador.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imagemResultadoJogador.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imagemResultadoJogador.setImageResource(R.drawable.tesoura);
                break;
        }

        // Aplicar lógica do jogo
        if (
            // App ganhando
                (opcaoApp.equals("pedra") && opcaoSelecionada.equals("tesoura")) ||
                (opcaoApp.equals("papel") && opcaoSelecionada.equals("pedra")) ||
                (opcaoApp.equals("tesoura") && opcaoSelecionada.equals("papel"))
        ) {
            textResultado.setText(R.string.appJogoLoss);
            // Reseta a pontuação caso o jogador perca
            pontuacao = 0;
        } else if (
            // Jogador ganhando
                (opcaoSelecionada.equals("pedra") && opcaoApp.equals("tesoura")) ||
                (opcaoSelecionada.equals("papel") && opcaoApp.equals("pedra")) ||
                (opcaoSelecionada.equals("tesoura") && opcaoApp.equals("papel"))
        ) {
            textResultado.setText(R.string.appJogoWin);
            // Aumenta em 1 ponto a pontuação do jogador caso ele ganhe
            pontuacao++;
        } else {
            // Jogo empata
            textResultado.setText(R.string.appJogoDraw);
        }

        // Atualiza a pontuação na tela
        textPontuacao.setText(String.valueOf(pontuacao));
    }
}