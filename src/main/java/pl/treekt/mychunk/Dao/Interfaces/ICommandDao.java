package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Web.Command;
import pl.treekt.mychunk.Entity.Web.Position;

import java.util.List;

public interface ICommandDao {
    List<Command> getAllCommands(Position position);
    Command getCommandByContent(String content);
    void addCommand(Command command);
    void updateCommand(Command command);
    void deleteCommand(Command command);
    boolean commandExists(String content);
}
