package org.reserva.reservashotel.ticket;

import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl {

    private final TicketRepository ticketRep;

    public TicketServiceImpl(TicketRepository ticketRep) {this.ticketRep=ticketRep;}
}
