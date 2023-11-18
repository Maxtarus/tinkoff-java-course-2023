package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PortScanner {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int LAST_PORT = 49151;
    private static final Map<Integer, String> MOST_POPULAR_USED_PORTS = Map.ofEntries(
        Map.entry(21, "FTP"),
        Map.entry(22, "SSH"),
        Map.entry(23, "Telnet"),
        Map.entry(25, "SMTP"),
        Map.entry(53, "DNS"),
        Map.entry(67, "DHCP"),
        Map.entry(80, "HTTP"),
        Map.entry(110, "POP3"),
        Map.entry(123, "NTP"),
        Map.entry(135, "EPMAP"),
        Map.entry(137, "NETBIOS-NS"),
        Map.entry(138, "NETBIOS-DGM"),
        Map.entry(139, "NETBIOS-SSN"),
        Map.entry(445, "MICROSOFT-DS"),
        Map.entry(1900, "SSDP"),
        Map.entry(5353, "MDNS"),
        Map.entry(5355, "LLMNR"),
        Map.entry(8080, "HTTP Proxy")
    );

    private PortScanner() {}

    public static List<Port> checkPorts() {
        List<Port> usedPorts = new ArrayList<>();

        for (int i = 1; i < LAST_PORT; i++) {
            try {
                ServerSocket serverSocket = new ServerSocket(i);
                serverSocket.close();
            } catch (IOException e) {
                Port tspPort = new Port(Port.Protocol.TCP, i, MOST_POPULAR_USED_PORTS.getOrDefault(i, ""));
                usedPorts.add(tspPort);
            }

            try {
                DatagramSocket datagramSocket = new DatagramSocket(i);
                datagramSocket.close();
            } catch (IOException e) {
                Port udpPort = new Port(Port.Protocol.UDP, i, MOST_POPULAR_USED_PORTS.getOrDefault(i, ""));
                usedPorts.add(udpPort);
            }
        }

        return usedPorts;
    }

    public static void printUsedPorts(List<Port> usedPorts) {
        LOGGER.info(String.format("%-10s %-8s %s", "Протокол", "Порт", "Сервис"));
        for (Port usedPort : usedPorts) {
            LOGGER.info(String.format("%-10s %-8d %s", usedPort.protocol(), usedPort.port(), usedPort.app()));
        }
    }
}
