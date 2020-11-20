package main;

import collectionClasses.LabWork;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Connect {
    private static final int BUFFER_SIZE = 1024;
    int port = 13, port2 = 12;
    InetSocketAddress myAddress, address;
    DatagramChannel datagramChannel;
    DatagramSocket c;
    private ByteBuffer sendBuffer = ByteBuffer.allocate(16384);


    SocketAddress a;
    DatagramSocket s;


    public void connectServer() throws IOException {
        a = new InetSocketAddress(InetAddress.getLocalHost(), 8888);
        s = new DatagramSocket();


    }

    public void sendMinimalPoint(float minPoint) throws IOException {
        InetAddress hostIP = InetAddress.getLocalHost();
        myAddress = new InetSocketAddress(hostIP, port);
        datagramChannel = DatagramChannel.open();
        datagramChannel.connect(myAddress);

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.asFloatBuffer().put(minPoint);
        buffer.flip();
        datagramChannel.send(buffer, myAddress);
        buffer.clear();
    }

    public void sendIdToRemove(int idToRem) throws IOException {
        InetAddress hostIP = InetAddress.getLocalHost();
        myAddress = new InetSocketAddress(hostIP, port);
        datagramChannel = DatagramChannel.open();
        datagramChannel.connect(myAddress);

        ByteBuffer buffer2 = ByteBuffer.allocate(BUFFER_SIZE);
        buffer2.putInt(idToRem);
        buffer2.flip();
        datagramChannel.send(buffer2, myAddress);
        buffer2.clear();
    }

    public void sendComand(String comand) throws IOException {
        InetAddress hostIP = InetAddress.getLocalHost();
        myAddress = new InetSocketAddress(hostIP, port);
        datagramChannel = DatagramChannel.open();
        //datagramChannel.bind(myAddress);

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.put(comand.getBytes());
        buffer.flip();
        datagramChannel.send(buffer, myAddress);
        buffer.clear();

        InetSocketAddress address = new InetSocketAddress(hostIP, port2);
        DatagramChannel datagramChannel2 = DatagramChannel.open();
        DatagramSocket datagramSocket = datagramChannel2.socket();
        datagramSocket.bind(address);

        datagramChannel2.receive(buffer);
        String data = new String(buffer.array(), "UTF-8");
        System.out.println(data);
        datagramSocket.close();

    }

    public void sendObj(LabWork lw) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(lw);
            buffer.put(byteArrayOutputStream.toByteArray());
            objectOutputStream.flush();
            byteArrayOutputStream.flush();
            buffer.flip();
            datagramChannel.send(buffer, myAddress);
            System.out.println("----\nДанные отправлены.\n----");
            objectOutputStream.close();
            byteArrayOutputStream.close();
            sendBuffer.clear();
        } catch (IOException e) {
            System.out.println("----\nВозникла ошибка:\n");
            e.printStackTrace();
        }
    }
}

