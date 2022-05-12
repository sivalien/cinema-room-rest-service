package cinema;

public class Statistics {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

    Statistics() {
        current_income = 0;
        number_of_available_seats = 81;
        number_of_purchased_tickets = 0;
    }

    public void purchaseSeat(Seat seat) {
        current_income += seat.getPrice();
        number_of_purchased_tickets++;
        number_of_available_seats--;
    }

    public void returnSeat(Seat seat) {
        current_income -= seat.getPrice();
        number_of_available_seats++;
        number_of_purchased_tickets--;
    }

}
