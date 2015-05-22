package org.rockislandschools;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        if(args.length == 0)
        {
            System.out.println("Please specify IP Address.");
            System.exit(0);
        }
        
        String address = args[0];
        
        HostStatus status = new HostStatus();
        
        String currentState = status.IsReachableReturnString(address);
        
        DisplayStatus statusFrame = new DisplayStatus();
        
        statusFrame.buildFrame(address, currentState);
        
        //String newState = status.IsReachableReturnString(address);
        
        while (true){
        String newState = status.IsReachableReturnString(address);
        if (newState.equals(currentState)){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Nothing has changed");
            
        }
            else {
            statusFrame.dispose();
            System.out.println("Building a new frame");
            currentState = newState;
            statusFrame.buildFrame(address, currentState);
            }
        }
    }
}