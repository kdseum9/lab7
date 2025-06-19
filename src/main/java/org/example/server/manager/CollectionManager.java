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

    public void add(Ticket ticket) {

        synchronized (collection) {
            collection.add(ticket);
            logger.info("Added ticket: {}", ticket);
        }
    }

    public void update(Ticket ticket) {

        synchronized (collection) {
            collection.remove(ticket);
            collection.add(ticket);
            logger.info("Updated ticket: {}", ticket);
        }
    }

    public void delete(Ticket ticket) {
        synchronized (collection) {
            boolean removed = collection.remove(ticket);
            if (removed) {
                logger.info("Deleted ticket: {}", ticket);
            } else {
                logger.warn("Attempted to delete non-existent ticket: {}", ticket);
            }
        }
    }

    public boolean removeById(long id) {
        synchronized (collection) {
            Iterator<Ticket> iterator = collection.iterator();
            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();
                if (ticket.getId() == id) {
                    iterator.remove(); // Используем iterator.remove() для безопасного удаления во время итерации
                    logger.info("Removed ticket with ID {} from collection.", id);
                    return true;
                }
            }
            logger.warn("Attempted to remove non-existent ticket with ID {}.", id);
            return false;
        }
    }
    public void remove_lower(Ticket ticket) {
        synchronized (collection) {
            Ticket referenceTicket = TicketInput.generateTicket();
            logger.info("Reference ticket for comparison: {}", referenceTicket);

            int removedCount = 0;
            Iterator<Ticket> iterator = collection.iterator();
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
    }

    public void remove_greater(Ticket ticket) {
        synchronized (collection) {
            Ticket referenceTicket = TicketInput.generateTicket();
            int removedCount = 0;

            Iterator<Ticket> iterator = collection.iterator();
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
    }

    public LocalDateTime getTimeOfInitial() {

        return timeOfInitial;

    }

    public LinkedHashSet<Ticket> getCollection() {
        synchronized (collection) {
            return new LinkedHashSet<>(collection); // Возвращаем копию для безопасности
        }
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

    public void setCollection(LinkedHashSet<Ticket> newCollection) {
        synchronized (collection) {
            collection.clear();
            collection.addAll(newCollection);
            logger.info("Collection replaced with new set (size: {})", newCollection.size());
        }
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

    public int clear(String userLogin) {
        synchronized (collection) { // Синхронизация коллекции
            int initialSize = collection.size();
            int userId = DataBaseManager.getUserId(userLogin); // Получаем ID пользователя по его логину

            if (userId == -1) {
                logger.warn("Attempted to remove tickets for non-existent user login: {}", userLogin);
                return 0; // Пользователь не найден, удалять нечего
            }

            // Используем Iterator для безопасного удаления элементов во время итерации
            Iterator<Ticket> iterator = collection.iterator();
            int removedCount = 0;
            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();
                // Проверяем, совпадает ли ownerId билета с ID текущего пользователя
                if (ticket.getOwnerId() == userId) {
                    iterator.remove(); // Безопасное удаление из LinkedHashSet
                    removedCount++;
                    logger.debug("Removed in-memory ticket with ID {} belonging to user ID {}", ticket.getId(), userId);
                }
            }
            logger.info("Removed {} tickets from in-memory collection for user {}.", removedCount, userLogin);
            return removedCount;
        }
    }
    }
