package java_bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.FlowSession;
import net.corda.core.flows.InitiatedBy;


@InitiatedBy(TwoPartyFlow.class)
public class TwoPartyFlowResponder extends FlowLogic<Void> {

    private FlowSession counterPartySession;

    public TwoPartyFlowResponder(FlowSession counterPartySession) {
        this.counterPartySession = counterPartySession;
    }

    @Suspendable
    @Override
    public Void call() throws FlowException {
        int reciveInt = counterPartySession.receive(Integer.class).unwrap(it ->{
           if(it > 3) throw new IllegalArgumentException("Number too high");
            return it;
        });
        return null;
    }
}
