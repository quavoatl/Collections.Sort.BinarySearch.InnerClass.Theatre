package com.example;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("TeatruCluj", 8, 12);
        theatre.reserveSeat("A02");
        theatre.reserveSeat("A02");
        theatre.reserveSeat("K02");

        List<Theatre.Seat> seatsByName = new ArrayList<>(theatre.getSeats());
        printSeatsList(seatsByName);
        System.out.println();

        List<Theatre.Seat> seatsByPrice = new ArrayList<>(theatre.getSeats());
        seatsByPrice.add(theatre.new Seat("B00", 13));
        Collections.sort(seatsByPrice, Theatre.PRICE_ORDER);
        printSeatsList(seatsByPrice);

    }

    public static void printSeatsList(List<Theatre.Seat> list) {
        for (Theatre.Seat seat : list) {
            System.out.print(seat.getSeatNumber() + " price : " + seat.getPrice() + " $ ");
        }
    }
}

