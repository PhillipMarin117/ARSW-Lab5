package edu.escuelaing.arsw.app.service;

import java.util.HashSet;
import java.util.Random;

import java.util.*;

public class Juego {
    private String numero;
    private HashSet<Integer> hashSet =  new HashSet < Integer > (6);
    private HashMap<Integer, Integer> listNumero = new HashMap<Integer, Integer>();
    private int vidas, picas, famas;
    private Boolean victoria;

    public Juego() {
        numero = generateNumber();
    }

    public void play(Integer guessNumber) {
        hashSet.add(guessNumber);
        LinkedList<Integer> num = new LinkedList<>();
        picas = 0;
        famas = 0;
        num.add((guessNumber /1000));
        num.add(((guessNumber % 1000)/100));
        num.add((((guessNumber % 1000)% 100)/10));
        num.add((((guessNumber % 1000)% 100)%10));
        for (int i= 0; i<4; i++){
            if (listNumero.containsValue(num.get(i))){
                picas ++;
            }
            if(listNumero.get(i)==num.get(i)){
                famas ++;
            }
        }
        vidas--;
    }

    public String generateNumber() {
        vidas = 6;
        hashSet.clear();
        listNumero.clear();
        victoria = false;
        Random r = new Random();
        String snumero = "";
        for (int i = 0; i < 4; i++) {
            int nume = r.nextInt(10);
            listNumero.put(i, nume);
            snumero += Integer.toString(nume);
        }
        return snumero;
    }

    public boolean isFinished(){
        Boolean bandera = false;
        if(getVidas() <= 0 ){
            bandera = true;
            victoria = false;
        } else if (vidas > 0  && famas==4){
            bandera = true;
            victoria = true;
        }
        return bandera;
    }
    public HashSet<Integer> getLastNumbers(){
        return hashSet;
    }

    public String getNumero() {
        return numero;
    }

    public Integer getFama(){
        return famas;
    }

    public Integer getPicas(){
        return picas;
    }

    public Integer getVidas(){
        return vidas;
    }

    public boolean getVictoria(){
        return victoria;
    }

}