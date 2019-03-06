package com.example;

import java.util.*;


public class Theatre {

    private final String theatreName;
    private List<Seat> seats = new LinkedList<>();

    static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if (seat1.getPrice() < seat2.getPrice()) {
                return -1;
            } else if (seat1.getPrice() > seat2.getPrice()) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    public Theatre(String theatreName, int seatsPerRow, int numRow) {
        this.theatreName = theatreName;

        int lastRow = 'A' + (numRow - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {

                double price = 12.00;

                if ((row < 'D') && (seatNum >= 4 && seatNum <= 9)) {
                    price = 14.00;
                } else if ((row > 'F') || (seatNum < 4 || seatNum > 9)) {
                    price = 7.00;
                }
                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
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

    public Collection<Seat> getSeats() {
        return seats;
    }

    public class Seat implements Comparable<Seat> {

        private final String seatNumber;
        private boolean reserved = false;
        private double price;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }


        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
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

