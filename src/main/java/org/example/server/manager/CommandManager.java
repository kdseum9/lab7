package org.example.server.manager;

import org.example.common.Request;
import org.example.common.Response;
import org.example.server.command.*;

import java.util.HashMap;

/**
 * Менеджер команд, осуществляющий регистрацию и выполнение доступных команд.
 * <p>Хранит отображение названий команд на соответствующие классы и передаёт управление при выполнении.</p>
 */
public class CommandManager {

    private final HashMap<String, AbstractCommand> commands = new HashMap<>();


    public CommandManager(CollectionManager collectionManager) {

        // Регистрация всех доступных команд
        commands.put("info", new InfoCommand());
        commands.put("add", new AddCommand());
        commands.put("show", new ShowCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("print_descending", new PrintDescendingCommand());
        commands.put("help", new HelpCommand());
        commands.put("update", new UpdateCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("clear", new ClearCommand());
        commands.put("add_if_max", new AddIfMaxCommand());
        commands.put("remove_lower", new RemoveLowerCommand());
        commands.put("filter_starts_with", new FilterCommand());
        commands.put("print_field_descending_discount", new PrintFieldDescendingDiscountCommand());
        commands.put("exit", new ExitCommand());


    }

    /**
     * Выполняет команду, соответствующую переданному вводу.
     *
     * @param request
     */
    public Response doCommand(Request request, CollectionManager collectionManager) {
        AbstractCommand command = commands.get(request.getCommandName());
        if (command != null) {
            try {
                return command.execute(request, collectionManager);
            } catch (Exception e) {
                return new Response("Error executing command: " + e.getMessage(), null);
            }
        }
        return new Response("Unknown command: " + request.getCommandName(), null);
    }
}
