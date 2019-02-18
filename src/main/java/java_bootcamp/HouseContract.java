package java_bootcamp;

import net.corda.core.contracts.Command;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;
import org.jetbrains.annotations.NotNull;

import java.security.PublicKey;
import java.util.List;

public class HouseContract implements Contract {

    @Override
    public void verify(@NotNull LedgerTransaction tx) throws IllegalArgumentException {
        if (tx.getCommands().size() != 1)
            throw new IllegalArgumentException("Tx must have one command.");
        Command command = tx.getCommand(0);
        List<PublicKey> requiredSigners = command.getSigners();
        CommandData commandType = command.getValue();

        if(commandType instanceof Register){

            // Shape constraints.
            if(tx.getInputStates().size() != 0)
                throw new IllegalArgumentException("Registration Tx must have no inputs");
            if(tx.getOutputStates().size() != 1)
                throw new IllegalArgumentException("Registration transaction must have one output.");


            // Content constraints.

            // Required signer constraints.
        } else if (commandType instanceof Tranfer){
            // TODO("Transfer transaction logic")
        } else {
            throw new IllegalArgumentException("Command type not recognised.");
        }
    }

    public class Register implements CommandData {
    }

    public class Tranfer implements CommandData {
    }
}
