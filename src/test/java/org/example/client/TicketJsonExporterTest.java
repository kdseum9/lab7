package org.example.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.common.TicketExportData;
import org.example.common.model.Coordinates;
import org.example.common.model.Ticket;
import org.example.common.model.Venue;
import org.example.common.model.enums.TicketType;
import org.example.common.model.enums.VenueType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketJsonExporterTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @TempDir
    Path tempDir;

    @Test
    void writesValidUtf8JsonAndPreservesSpecialCharactersAndNulls() throws Exception {
        String specialName = "Билет \"особый\"\nстрока\\путь";
        Ticket ticket = ticket(12, specialName);
        ticket.setDiscount(null);
        ticket.setVenue(new Venue(44, "Зал \"А\"\nэтаж\\2", 500, null));
        Path output = tempDir.resolve("tickets.json");

        int count = TicketJsonExporter.write(new TicketExportData("алиса", List.of(ticket)), output.toString());

        assertEquals(1, count);
        String json = Files.readString(output, StandardCharsets.UTF_8);
        JsonNode root = OBJECT_MAPPER.readTree(json);
        assertEquals("алиса", root.get("login").asText());
        JsonNode exported = root.get("tickets").get(0);
        assertEquals(specialName, exported.get("name").asText());
        assertEquals(12, exported.get("id").asLong());
        assertEquals(7, exported.get("ownerId").asInt());
        assertEquals("2025-06-07T08:09:10+03:00", exported.get("creationDate").asText());
        assertEquals("VIP", exported.get("type").asText());
        assertEquals(1.25, exported.get("coordinates").get("x").asDouble(), 0.0001);
        assertEquals(2, exported.get("coordinates").get("y").asLong());
        assertTrue(exported.get("discount").isNull());
        assertTrue(exported.get("venue").get("type").isNull());
        assertEquals("Зал \"А\"\nэтаж\\2", exported.get("venue").get("name").asText());
        assertFalse(json.contains("password"));
        assertFalse(json.contains("hash"));
    }

    @Test
    void writesEmptyTicketsArray() throws Exception {
        Path output = tempDir.resolve("empty.json");

        TicketJsonExporter.write(new TicketExportData("alice", List.of()), output.toString());

        JsonNode root = OBJECT_MAPPER.readTree(Files.readString(output));
        assertTrue(root.get("tickets").isArray());
        assertEquals(0, root.get("tickets").size());
    }

    @Test
    void equivalentPayloadProducesEquivalentJson() throws Exception {
        TicketExportData data = new TicketExportData("alice", List.of(ticket(1, "same")));
        Path interactiveOutput = tempDir.resolve("interactive.json");
        Path scriptOutput = tempDir.resolve("script.json");

        TicketJsonExporter.write(data, interactiveOutput.toString());
        TicketJsonExporter.write(data, scriptOutput.toString());

        assertArrayEquals(Files.readAllBytes(interactiveOutput), Files.readAllBytes(scriptOutput));
    }

    @Test
    void refusesEmptyPathExistingFileAndMissingDirectory() throws Exception {
        IOException empty = assertThrows(IOException.class,
                () -> TicketJsonExporter.write(new TicketExportData("alice", List.of()), "  "));
        assertTrue(empty.getMessage().contains("must not be empty"));

        Path existing = tempDir.resolve("existing.json");
        Files.writeString(existing, "keep me", StandardCharsets.UTF_8);
        IOException exists = assertThrows(IOException.class,
                () -> TicketJsonExporter.write(new TicketExportData("alice", List.of()), existing.toString()));
        assertTrue(exists.getMessage().contains("already exists"));
        assertEquals("keep me", Files.readString(existing));

        Path unavailable = tempDir.resolve("missing").resolve("tickets.json");
        IOException missing = assertThrows(IOException.class,
                () -> TicketJsonExporter.write(new TicketExportData("alice", List.of()), unavailable.toString()));
        assertTrue(missing.getMessage().contains("does not exist"));
        assertFalse(Files.exists(unavailable));
    }

    private static Ticket ticket(long id, String name) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setOwnerId(7);
        ticket.setName(name);
        ticket.setCoordinates(new Coordinates(1.25f, 2L));
        ticket.setCreationDate(ZonedDateTime.parse("2025-06-07T08:09:10+03:00"));
        ticket.setPrice(300);
        ticket.setDiscount(15.5);
        ticket.setType(TicketType.VIP);
        ticket.setVenue(new Venue(9, "Venue", 1000, VenueType.CINEMA));
        return ticket;
    }
}
