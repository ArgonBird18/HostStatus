package org.rockislandschools;

public class Main {

    public static void main(String[] args) {

        if(args.length == 0)
        {
            System.out.println("Please specify IP Address.");
            System.exit(0);
        }

        String ipAdd = args[0];

        HostStatus state = new HostStatus();
        state.IsReachable(ipAdd);

    }
}
