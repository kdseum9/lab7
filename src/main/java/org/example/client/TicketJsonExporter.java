package org.example.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.common.TicketExportData;
import org.example.common.model.Coordinates;
import org.example.common.model.Ticket;
import org.example.common.model.Venue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Serializes ticket export data and writes it exclusively on the client machine. */
public final class TicketJsonExporter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private TicketJsonExporter() {
    }

    public static int write(TicketExportData exportData, String outputPath) throws IOException {
        if (exportData == null) {
            throw new IOException("The server did not provide export data.");
        }
        Path path = validateOutputPath(outputPath);
        String json = toJson(exportData);

        try {
            Files.writeString(path, json, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        } catch (FileAlreadyExistsException e) {
            throw new IOException("Output file already exists: " + path, e);
        } catch (IOException e) {
            throw new IOException("Cannot write output file '" + path + "': " + e.getMessage(), e);
        }
        return exportData.getTickets().size();
    }

    static String toJson(TicketExportData exportData) throws JsonProcessingException {
        Map<String, Object> root = new LinkedHashMap<>();
        root.put("login", exportData.getLogin());

        List<Map<String, Object>> tickets = new ArrayList<>();
        for (Ticket ticket : exportData.getTickets()) {
            tickets.add(ticketToMap(ticket));
        }
        root.put("tickets", tickets);
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(root) + System.lineSeparator();
    }

    private static Path validateOutputPath(String outputPath) throws IOException {
        if (outputPath == null || outputPath.isBlank()) {
            throw new IOException("Output path must not be empty.");
        }

        final Path path;
        try {
            path = Path.of(outputPath);
        } catch (InvalidPathException e) {
            throw new IOException("Invalid output path: " + e.getMessage(), e);
        }

        if (Files.exists(path)) {
            throw new IOException("Output file already exists: " + path);
        }
        Path parent = path.toAbsolutePath().getParent();
        if (parent != null && !Files.isDirectory(parent)) {
            throw new IOException("Output directory does not exist or is not a directory: " + parent);
        }
        return path;
    }

    private static Map<String, Object> ticketToMap(Ticket ticket) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", ticket.getId());
        result.put("name", ticket.getName());
        result.put("coordinates", coordinatesToMap(ticket.getCoordinates()));
        result.put("creationDate", ticket.getCreationDate() == null ? null : ticket.getCreationDate().toString());
        result.put("price", ticket.getPrice());
        result.put("discount", ticket.getDiscount());
        result.put("type", ticket.getType() == null ? null : ticket.getType().name());
        result.put("ownerId", ticket.getOwnerId());
        result.put("venue", venueToMap(ticket.getVenue()));
        return result;
    }

    private static Map<String, Object> coordinatesToMap(Coordinates coordinates) {
        if (coordinates == null) {
            return null;
        }
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("x", coordinates.getX());
        result.put("y", coordinates.getY());
        return result;
    }

    private static Map<String, Object> venueToMap(Venue venue) {
        if (venue == null) {
            return null;
        }
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", venue.getId());
        result.put("name", venue.getName());
        result.put("capacity", venue.getCapacity());
        result.put("type", venue.getType() == null ? null : venue.getType().name());
        return result;
    }
}
