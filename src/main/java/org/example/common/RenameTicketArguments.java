package org.example.common;

/** Shared validation for interactive, script and server requests. */
public record RenameTicketArguments(long id, String name) {
    public static RenameTicketArguments parse(String idLine, String nameLine) {
        if (idLine == null || idLine.isBlank()) {
            throw new IllegalArgumentException("ERROR: Ticket ID is required");
        }
        long id;
        try {
            id = Long.parseLong(idLine.strip());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR: Ticket ID must be a positive integer within the long range");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ERROR: Ticket ID must be positive");
        }
        if (nameLine == null) {
            throw new IllegalArgumentException("ERROR: Ticket name is required");
        }
        String name = stripWhitespace(nameLine);
        if (name.isEmpty()) {
            throw new IllegalArgumentException("ERROR: Ticket name must not be blank");
        }
        return new RenameTicketArguments(id, name);
    }

    private static String stripWhitespace(String value) {
        int start = 0;
        int end = value.length();
        while (start < end && isWhitespace(value.codePointAt(start))) {
            start += Character.charCount(value.codePointAt(start));
        }
        while (end > start && isWhitespace(value.codePointBefore(end))) {
            end -= Character.charCount(value.codePointBefore(end));
        }
        return value.substring(start, end);
    }

    private static boolean isWhitespace(int codePoint) {
        return Character.isWhitespace(codePoint) || Character.isSpaceChar(codePoint);
    }
}
