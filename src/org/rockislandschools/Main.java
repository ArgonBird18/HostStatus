package org.rockislandschools;


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
        
    
    }
}