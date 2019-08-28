import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.junit.Test;

import java.net.InetAddress;

public class ProcessTest {

    @Test
    public void testSendMessage() throws Exception {
        MembershipList membershipList = new MembershipList();
        Process p1 = new Process(1, membershipList);
        Process p2 = new Process(2, membershipList);
        Process p3 = new Process(3, membershipList);
        Process p4 = new Process(4, membershipList);

        membershipList.initializeVectorClockForAll();


        p1.action();
        p1.sendMessage(p2);
        p2.sendMessage(p3);

        p3.sendMessage(p1);
        p2.sendMessage(p3);

        System.out.println("p4 = " + p4);
        System.out.println("p3 = " + p3);
        System.out.println("p2 = " + p2);
        System.out.println("p1 = " + p1);
    }


    @Test
    public void testCurrentMillis() throws Exception {
        while (true) {
            Thread.sleep(1000);
            NTPUDPClient client = new NTPUDPClient();
            client.open();
            InetAddress hostAddr = InetAddress.getByName("ntp.ubuntu.com");
            TimeInfo info = client.getTime(hostAddr);
            info.computeDetails(); // compute offset/delay if not already done
            Long offsetValue = info.getOffset();
            Long delayValue = info.getDelay();
            String delay = (delayValue == null) ? "N/A" : delayValue.toString();
            String offset = (offsetValue == null) ? "N/A" : offsetValue.toString();

            System.out.println(" Roundtrip delay(ms)=" + delay
                    + ", clock offset(ms)=" + offset); // offset in ms
            client.close();
        }
    }
}