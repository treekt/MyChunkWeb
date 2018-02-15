package pl.treekt.mychunk.Service.Interfaces;

import pl.treekt.mychunk.Entity.Web.Command;
import pl.treekt.mychunk.Entity.Web.Position;

import java.util.List;

public interface ICommandService {
    List<Command> getAllCommands(Position position);
    Command getCommandByContent(String content);
    Boolean addCommand(Command command);
    void updateCommand(Command command);
    void deleteCommand(String content);
}
