package org.example.server.manager;


import org.example.client.TicketInput;
import org.example.common.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedHashSet;



public class CollectionManager {

    private final LocalDateTime timeOfInitial = LocalDateTime.now();

    private LinkedHashSet<Ticket> collection = new LinkedHashSet<>();


    private static final Logger logger = LoggerFactory.getLogger(CollectionManager.class);



    public CollectionManager() {


    }



    public

    void add(Ticket ticket) {

        collection.add(ticket);

        logger.info("Added ticket: {}", ticket);

    }



    public void update(Ticket ticket) {

        collection.remove(ticket);

        collection.add(ticket);

        logger.info("Updated ticket: {}", ticket);

    }



    public void delete(Ticket ticket) {

        boolean removed = collection.remove(ticket);

        if (removed) {

            logger.info("Deleted ticket: {}", ticket);

        } else {

            logger.warn("Attempted to delete non-existent ticket: {}", ticket);

        }

    }



    public void remove_lower(Ticket ticket) {

        Ticket referenceTicket = TicketInput.generateTicket();

        logger.info("Reference ticket for comparison: {}", referenceTicket);



        int removedCount = 0;



        Iterator<Ticket> iterator = getCollection().iterator();

        while (iterator.hasNext()) {

            Ticket currentTicket = iterator.next();

            if (referenceTicket.compareTo(currentTicket) > 0) {

                iterator.remove();

                removedCount++;

                logger.info("Removed ticket: {}", currentTicket);

                System.out.println("Deleted ticket: " + currentTicket);

            }

        }



        if (removedCount == 0) {

            System.out.println("No tickets were lower than the reference ticket.");

            logger.info("No tickets were removed.");

        } else {

            System.out.println("Total tickets removed: " + removedCount);

            logger.info("Total tickets removed: {}", removedCount);

        }

    }



    public void remove_greater(Ticket ticket) {

        Ticket referenceTicket = TicketInput.generateTicket();



        int removedCount = 0;



        Iterator<Ticket> iterator = getCollection().iterator();

        while (iterator.hasNext()) {

            Ticket currentTicket = iterator.next();

            if (referenceTicket.compareTo(currentTicket) < 0) {

                iterator.remove();

                removedCount++;

                logger.info("Removed ticket: {}", currentTicket);

                System.out.println("Ticket deleted: " + currentTicket);

            }

        }

        if (removedCount == 0) {

            System.out.println("No tickets were greater than the reference ticket.");

            logger.info("No tickets were removed. All are less than the reference.");

        } else {

            System.out.println("Total tickets removed: " + removedCount);

            logger.info("Total tickets removed: {}", removedCount);

        }

    }



    public LocalDateTime getTimeOfInitial() {

        return timeOfInitial;

    }



    public LinkedHashSet<Ticket> getCollection() {

        return collection;

    }



    public Ticket getById(long id) {

        Ticket found = collection.stream()

                .filter(t -> t.getId() == id)

                .findFirst()

                .orElse(null);

        if (found != null) {

            logger.info("Found ticket by ID {}: {}", id, found);

        } else {

            logger.warn("No ticket found with ID: {}", id);

        }

        return found;

    }



    public void setCollection(LinkedHashSet<Ticket> collection) {

        this.collection = collection;

        logger.info("Collection replaced with new set (size: {})", collection.size());

    }




    public boolean add_if_max(Ticket ticket) {

        if (getCollection().isEmpty()) {

            add(ticket);

            logger.info("Added ticket with price: {}", ticket.getPrice());



        }



        int maxPrice = getCollection().stream()

                .mapToInt(Ticket::getPrice)

                .max()

                .orElse(Integer.MIN_VALUE);

        if (ticket.getPrice() > maxPrice) {

            add(ticket);

            logger.info("Added ticket with price: {}", ticket.getPrice());

        } else {

            logger.info("Not added ticket with price: {} <= {}", ticket.getPrice(), maxPrice);

        }

        return false;
    }

    public int clear() {
        int sizeBefore = collection.size();
        collection.clear();
        logger.debug("Cleared collection (removed {} elements)", sizeBefore);
        return sizeBefore;
    }{
    }
}