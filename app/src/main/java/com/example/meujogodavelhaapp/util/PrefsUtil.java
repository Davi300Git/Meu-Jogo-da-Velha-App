package com.example.meujogodavelhaapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefsUtil {
    public static String getSimboloJog1(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("simb_jog_1", "X");
    }
        public static String getSimboloJog2(Context context){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            return preferences.getString("simb_jog_2", "O");
        }

        public static void setSimboloJog1(String simbolo,Context context) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("simb_jog_1", simbolo);
            editor.commit();
        }
        public static String getNomeJog1(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getString("jogador1", "NomedoJogador1");
        }
        public static String getNomeJog2(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getString("jogador2", "NomedoJogador2");
    }

    public static void setNomeJog1(String nome,Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("jogador1", nome);
        editor.commit();
    }
}
