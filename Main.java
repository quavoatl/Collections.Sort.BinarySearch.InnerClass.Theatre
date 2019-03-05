package com.example;



public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("TeatruCluj", 6, 5);
        //theatre.getSeats();
        theatre.reserveSeat("A02");
        theatre.reserveSeat("A02");
        theatre.reserveSeat("K02");


    }
}

