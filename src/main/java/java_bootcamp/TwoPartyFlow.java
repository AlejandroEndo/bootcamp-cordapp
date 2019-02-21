package java_bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;

@InitiatingFlow
@StartableByRPC
public class TwoPartyFlow extends FlowLogic<Integer> {

    private Party counterparty;

    public TwoPartyFlow(Party counterparty) {
        this.counterparty = counterparty;
    }


    @Suspendable
    @Override
    public Integer call() throws FlowException {
        FlowSession session = initiateFlow(counterparty);
        session.send(1);
        return null;
    }
}
