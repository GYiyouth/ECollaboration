package servlet.message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 * Created by GR on 2017/1/2.
 */
public class MessageCenter extends WebSocketServer {
    private static final int PORT = 2333;

    public static void main(String[] args) {
        MessageCenter server = new MessageCenter(PORT);
        server.start();

        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = server.getPort();
            print(String.format("服务已启动: %s:%d", ip, port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(in);

        while (true) {
            try {
                String msg = reader.readLine();
                server.broadcastMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public MessageCenter(int port) {
        super(new InetSocketAddress(port));
    }

    public MessageCenter(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        String address = webSocket.getRemoteSocketAddress().getAddress().getHostAddress();
        String message = String.format("(%s) <加入>", address);
        broadcastMessage(message);
        print(message);
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        String address = webSocket.getRemoteSocketAddress().getAddress().getHostAddress();
        String message = String.format("(%s) <离开>", address);
        broadcastMessage(message);
        print(message);
    }

    @Override
    public void onMessage(WebSocket webSocket, String msg) {
        String address = webSocket.getRemoteSocketAddress().getAddress().getHostAddress();
        String message = String.format("(%s) %s", address, msg);
        broadcastMessage(message);
        print(message);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        if (null != webSocket) {
            if (!webSocket.isClosed()) {
                webSocket.close(0);
            }
        }
        e.printStackTrace();
    }

    /**
     * 广播收到消息
     *
     * @param msg
     */
    private void broadcastMessage(String msg) {
        Collection<WebSocket> connections = connections();

        synchronized (connections) {
            for (WebSocket client : connections) {
                client.send(msg);
            }
        }
    }

    private static void print(String msg) {
        System.out.println(String.format("[%d] %s", System.currentTimeMillis(), msg));
    }
}
