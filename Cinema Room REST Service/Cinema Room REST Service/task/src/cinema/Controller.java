package cinema;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class Controller {
    Hall hall = new Hall();

    @GetMapping("/seats")
    public Hall getSeats(){
        return hall;
    }

    @PostMapping(path = "/purchase")
    public ResponseEntity purchase(@RequestBody Seat seat){
        if (seat.getRow() < 1 || seat.getRow() > 9 ||
                seat.getColumn() < 1 || seat.getColumn() > 9) {
            return ResponseEntity.badRequest().body(
                    new ConcurrentHashMap<>(Map.of("error", "The number of a row or a column is out of bounds!")));
        } else if (!hall.getAvailableSeats().contains(seat)) {
            return ResponseEntity.badRequest().body(
                    new ConcurrentHashMap<>(Map.of("error", "The ticket has been already purchased!"))
            );
        }
        return ResponseEntity.ok(hall.purchaseSeat(seat));
    }

    @PostMapping(path = "/return")
    public ResponseEntity returnTicket (@RequestBody Token token) {
        if (!hall.getTickets().containsKey(token.getToken())) {
            return ResponseEntity.badRequest().body(
                    new ConcurrentHashMap<>(Map.of("error", "Wrong token!"))
            );
        }
        return ResponseEntity.ok().body(
                new ConcurrentHashMap<>(Map.of("returned_ticket", hall.returnSeat(token.getToken())))
        );
    }

    @PostMapping(path = "/stats")
    public ResponseEntity getStatistics (@RequestParam(required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            return ResponseEntity.status(401).body(
                    new ConcurrentHashMap<>(Map.of("error", "The password is wrong!"))
            );
        }
        return ResponseEntity.ok().body(hall.getStatistics());
    }
}
