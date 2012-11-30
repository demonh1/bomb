
package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;
import objects.Target;

/**
 *
 * @author demonh1
 */
public class TargetAgent extends  Agent {
    
     Target target;
    boolean isDetected;
    AMSAgentDescription [] env_agents; // environment agents

    @Override
    public void setup() {

        this.isDetected = false;
        this.target = new Target();
         // Поведение агента исполняемое в цикле
        this.addBehaviour(new CyclicBehaviour(this){
            
            @Override
            public void action() {
                ACLMessage input_message = receive();
                if (null != input_message) {
                    sayCoords(input_message);
                    die(input_message);
                }
                live(input_message);
                block();
            }

        });
       // this.look_over();
        //this.make_noise("start noise");
    }


    public void lookOver(){
        this.env_agents = null;
        // agents list
        try {
            SearchConstraints c = new SearchConstraints();
            c.setMaxResults(new Long(-1));
         this.env_agents = AMSService.search(this, new AMSAgentDescription(), c);
        }
        catch (Exception e){
            System.out.println( "Problem searching AMS: " + e);
        }
    }


    // создает помехи
     public void makeNoise(String noise){
        for(int i = 0; i != this.env_agents.length ; ++i){
            AID agentID = env_agents[i].getName();

            // не будем посылать сообщения шума сами себе
            if(this.getAID().getName().equals(agentID.getName()))
                continue;
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(agentID);
                // id агента которому отправляем сообщение
            msg.setLanguage("English");
            msg.setContent(noise);
            send(msg); //send message
        }
    }

     public void live (ACLMessage input_message) {
         lookOver();
         makeNoise("noise");

     }
     public void sayCoords (ACLMessage input_message) {
         String msg = input_message.getContent().toString();
         if(!this.isDetected &&  msg.equals("detected")) {
            this.isDetected = true;
            this.target.Say("was detected");
            ACLMessage reply = input_message.createReply();
            reply.setPerformative( ACLMessage.INFORM );
            reply.setContent("coords:" + this.target.getCoordString());
            send(reply);
         }
     }
     public void die(ACLMessage input_message ){
        String message_string = input_message.getContent().toString();
    if(this.isDetected && message_string.split(":")[0].equals("you was killed"))
    {
            this.target.Say("was killed by " + message_string.split(":")[1]);
            this.doDelete();
        }
    }
    
    
    
}
