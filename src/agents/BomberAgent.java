
package agents;

import coordinate.Coord;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Vector;
import objects.Bomber;

/**
 *
 * @author demonh1
 */
public class BomberAgent extends Agent {
    
     Bomber bomber;
    Vector <AID> targets; // list of targets

    @Override
     protected void setup() {
        this.bomber  = new Bomber();
        this.targets = new Vector <AID>();

        this.addBehaviour(new CyclicBehaviour(this) {
            
            @Override
            public void action() {
                ACLMessage input_message = receive();
                if (null != input_message) {
                    detectTarget(input_message);
                    Kill(input_message);
                }
                block();
            }
        });
    }

public void detectTarget( ACLMessage input_message ){
    String message_string = input_message.getContent().toString();
        AID sender =  input_message.getSender();
        if(message_string.equals("noise") && (! targets.contains(sender))){
            this.targets.add(sender);
             // --------------------------------
            // this.bomber.say("" + sender );
           // --------------------------------
            ACLMessage reply = input_message.createReply();
            reply.setPerformative( ACLMessage.INFORM );
            reply.setContent( "detected");
            send(reply);
}
}

public void Kill(ACLMessage input_message){
        String message_string = input_message.getContent().toString();
        AID sender =  input_message.getSender();
        Coord target_coord = new Coord();
      if(message_string.split(":")[0].equals("coords") &&
                (this.targets.contains(sender))) {
            target_coord.fromString(message_string.split(":")[1]);
            this.bomber.Say("target at " + target_coord.toBrackets());
            ACLMessage reply = input_message.createReply();
            reply.setPerformative( ACLMessage.INFORM );
            reply.setContent( "you was killed:" + this.bomber.getName());
            send(reply);
        }
    }

    
}
