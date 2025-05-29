package org.example.server.handlers;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashHandler {
    public static String encryptString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-224");

            // Вычисляем хеш входной строки
            byte[] messageDigest = md.digest(input.getBytes());

            // Преобразуем массив байтов в положительное число
            BigInteger no = new BigInteger(1, messageDigest);

            // Конвертируем в шестнадцатеричное представление
            String hashtext = no.toString(16);

            // SHA-224 создает 56-символьную хеш-строку (224 бита = 28 байт = 56 hex символов)
            // Добавляем ведущие нули при необходимости
            while (hashtext.length() < 56) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-224 algorithm not available", e);
        }
    }
}
