package com.example.meujogodavelhaapp.fragment;

import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.meujogodavelhaapp.R;
import com.example.meujogodavelhaapp.databinding.FragmentJogoBinding;

import java.util.Arrays;
import java.util.Random;

public class JogoFragment extends Fragment {
    // variavel para acessar os elementos da view
    private FragmentJogoBinding binding;
    // vetor de Botões para referanciar os botões
    private Button[] botoes;
    // matriz de String que representa o tabuleiro
    private String[][] tabuleiro;
    // variaveis para os simbolos
    private String simbJog1, simbJog2, simbolo;
    // variaveis Random para
    private Random random;
    // variavel para controlar o numero de jogadas
    private int numJogadas = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instanciar os binding
        binding = FragmentJogoBinding.inflate(inflater, container, false);

        //intanciar os botoes
        botoes = new Button[9];

        //associar o vetor aos botoes
        botoes[0] = binding.bt00;
        botoes[1] = binding.bt01;
        botoes[2] = binding.bt02;
        botoes[3] = binding.bt10;
        botoes[4] = binding.bt11;
        botoes[5] = binding.bt12;
        botoes[6] = binding.bt20;
        botoes[7] = binding.bt21;
        botoes[8] = binding.bt22;

        //associa o listener aos botoes
        for (Button bt : botoes) {
            bt.setOnClickListener(listenerBotoes);
        }

        // instanciar o tabuleiro
        tabuleiro = new String[3][3];

        // preenche a Matrix com ""
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = "";
            }
        }

        //defini os simbolos do jogador1 e do jogador2
        simbJog1 = "X";
        simbJog2 = "O";

        // instancia o Random
        random = new Random();

        //sorteia o quem iniciará o jogo
        sorteia();

        //retorna a view root do binding
        return binding.getRoot();
    }

    private void sorteia() {
        //se gerar um valor verdadeiro, vogador1 começa, caso contrario jogador2 começa
        if (random.nextBoolean()) {
            simbolo = simbJog1;
        } else {
            simbolo = simbJog2;
        }
    }

    private void atualizaVez() {
        if (simbolo.equals(simbJog1)) {
            binding.jogador1.setBackgroundColor(getResources().getColor(R.color.purple_200));
            binding.jogador2.setBackgroundColor(getResources().getColor(R.color.system_accent1_700));
        } else {
            binding.jogador2.setBackgroundColor(getResources().getColor(R.color.purple_200));
            binding.jogador1.setBackgroundColor(getResources().getColor(R.color.system_accent1_700));
        }
    }

    private boolean venceu() {
        //verificar se venceu nas linhas
        for (int li = 0; li < 3; li++)
            if (tabuleiro[li][0].equals(simbolo)
                    && tabuleiro[li][1].equals(simbolo)
                    && tabuleiro[li][2].equals(simbolo)) {
                return true;
            }
        //verifica se venceu nas colunas
        for (int col = 0; col < 3; col++) {
            if (tabuleiro[0][col].equals(simbolo)
                    && tabuleiro[1][col].equals(simbolo)
                    && tabuleiro[2][col].equals(simbolo)) {
                return true;

            }
        }
        if (tabuleiro[0][0].equals(simbolo)
                && tabuleiro[1][1].equals(simbolo)
                && tabuleiro[2][2].equals(simbolo)) {
            return true;
        }
        if (tabuleiro[0][2].equals(simbolo)
                && tabuleiro[1][1].equals(simbolo)
                && tabuleiro[2][0].equals(simbolo)) {
            return true;
        }
        return false;
    }

    private void resetar() {
        //percorrer os botoes do vetor, voltando o background inicial.
        // tornando-os clicáveis novamente e limpando os textos
        for (Button botao : botoes){
            botao.setClickable(true);
            botao.setText("");
            botao.setBackgroundColor(getResources().getColor(R.color.purple_200));
        }
        for (String[] vetor : tabuleiro){
            Arrays.fill(vetor, "");
        }
        // zerar o numero de jogadas
        numJogadas = 0;
    }

    //listener para os Botões
    private View.OnClickListener listenerBotoes = btPress -> {
        //engrementa o numero de jogadas
        numJogadas++;

        //obtem o nome do botao
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());

        // extrai a posição atraves do nome do botão
        String posicao = nomeBotao.substring(nomeBotao.length() - 2);

        // extrai linha e coluna da string posição
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));

        // preencher a posição da matriz com o simbolo "da vez"
        tabuleiro[linha][coluna] = simbolo;

        //faz um casting de view pra Button
        Button botao = (Button) btPress;

        //"setar" o simbolo no botao pressionado
        botao.setText(simbolo);

        // trocar o background do botao pra outra cor
        botao.setBackgroundColor(Color.BLACK);

        //desabilitar o botao que foi clicado
        botao.setClickable(false);

        //verifica se venceu
        if (numJogadas >= 5 && venceu()) {

            //informa que houve um vencedor
            Toast.makeText(getContext(), R.string.venceu, Toast.LENGTH_SHORT).show();
            //reseta
            resetar();

        } else if (numJogadas == 9) {
            //informa que deu velha
            Toast.makeText(getContext(), R.string.velha, Toast.LENGTH_LONG).show();
            resetar();

        }else {
            //inverte o simbolo
            simbolo = simbolo.equals(simbJog1) ? simbJog2 : simbJog1;

            //atualiza a vez
            atualizaVez();
        }
    };
}