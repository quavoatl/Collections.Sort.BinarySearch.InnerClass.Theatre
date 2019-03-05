package com.example;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Theatre {

    private final String theatreName;
    private List<Seat> seats = new LinkedList<>();
    private int seatsPerRow;
    private int numRow;


    public Theatre(String theatreName, int seatsPerRow, int numRow) {
        this.theatreName = theatreName;
        this.seatsPerRow = seatsPerRow;
        this.numRow = numRow;

        int lastRow = 'A' + (numRow - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = new Seat(row + String.format("%02d", seatNum));
                seats.add(seat);
            }
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    public boolean reserveSeat(String seatNumber) {

        //binary search
        int low = 0;
        int high = seats.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Seat midSeat = seats.get(mid);
            int cmp = midSeat.getSeatNumber().compareTo(seatNumber);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return seats.get(mid).reserve();
            }
        }
        System.out.println("There is no " + seatNumber + " seat");
        return false;
    }

//        Seat requestedSeat = new Seat(seatNumber);
//        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
//
//        if (foundSeat >= 0) {
//            return seats.get(foundSeat).reserve();
//
//        } else {
//            System.out.println("There is no seat " + seatNumber);
//            return false;
//        }
//    }

    public void getSeats() {
        for (Seat seat : seats) {
            System.out.println(seat.getSeatNumber());
        }
    }


    private class Seat implements Comparable<Seat> {

        private final String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }


        public String getSeatNumber() {
            return seatNumber;
        }

        public boolean reserve() {
            if (!this.reserved) {
                this.reserved = true;
                System.out.println(seatNumber + " seat reserved");
                return true;
            } else {
                System.out.println(seatNumber + " seat already reserved");
                return false;
            }
        }

        public boolean cancel() {
            if (this.reserved) {
                this.reserved = false;
                System.out.println(seatNumber + " is now open to reserve.");
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }
    }
}

