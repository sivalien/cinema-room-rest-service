package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Hall {
    @JsonProperty("total_rows")
    private int totalRows = 9;
    @JsonProperty("total_columns")
    private int totalColumns = 9;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;
    @JsonIgnore
    private Map<UUID, Seat> tickets;
    @JsonIgnore
    private Statistics statistics;
    @JsonIgnore
    private int income;

    Hall(){
        availableSeats = new ArrayList<>();
        statistics = new Statistics();
        tickets = new HashMap<>();
        for (int i = 1; i <= this.totalRows; i++) {
            for (int j = 1; j <= this.totalColumns; j++) {
                this.availableSeats.add(new Seat(i, j));
            }
        }
    };

    public Ticket purchaseSeat(Seat seat) {
        availableSeats.remove(seat);
        UUID uuid = UUID.randomUUID();
        tickets.put(uuid, seat);
        statistics.purchaseSeat(seat);
        income += seat.getPrice();
        return new Ticket(uuid, seat);
    }

    public Seat returnSeat(UUID uuid) {
        Seat seat = tickets.get(uuid);
        tickets.remove(uuid);
        availableSeats.add(seat);
        statistics.returnSeat(seat);
        income -= seat.getPrice();
        return seat;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Map<UUID, Seat> getTickets() {return tickets;}

    public Map<String, Integer> getStatistics() {
        return new ConcurrentHashMap<>(Map.of("current_income", income, "number_of_available_seats", availableSeats.size(), "number_of_purchased_tickets", 81 - availableSeats.size()));
    }
}
