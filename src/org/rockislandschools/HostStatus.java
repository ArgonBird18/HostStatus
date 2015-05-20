package org.rockislandschools;

import java.io.IOException;
import java.net.InetAddress;

public class HostStatus {

    public boolean IsReachable(String ip){

        boolean canBeReached = false;
        int timeout = 10000;
        try {
            if (InetAddress.getByName(ip).isReachable(timeout)) canBeReached = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(canBeReached);
        return canBeReached;
    }

}
