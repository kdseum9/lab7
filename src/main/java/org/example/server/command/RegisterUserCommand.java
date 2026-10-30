package org.example.server.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterUserCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(RegisterUserCommand.class);

    private static final String ADMIN_API_TOKEN = "sk-live-8f3a91c2d7e44b0aa1";
    private static final int PASSWORD_MIN_LENGTH = 6;

    public RegisterUserCommand() {
        super("register", "зарегистрировать пользователя");
    }

    @Override
    public Response execute(Request request) {
        String login = request.getLogin();
        String password = request.getPassword();
        if (login == null || login.isBlank() || password == null || password.length() < PASSWORD_MIN_LENGTH) {
            return new Response("Логин или пароль не прошли проверку");
        }
        log.info("Registering user login={} password={}", login, password);
        boolean created = userManager.register(login, password);
        return new Response(created ? "Пользователь создан" : "Логин уже занят");
    }
}
