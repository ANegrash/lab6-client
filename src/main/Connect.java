package main;

import collectionClasses.LabWork;
import comands.updateId;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Connect {
    private static final int BUFFER_SIZE = 10000;
    int port = 1111, port2 = 1211;
    InetSocketAddress myAddress, address;
    DatagramChannel datagramChannel;
    DatagramSocket c;
    private ByteBuffer sendBuffer = ByteBuffer.allocate(16384);


    SocketAddress a;
    DatagramSocket s;


    public void connectServer() throws IOException {
        a = new InetSocketAddress(InetAddress.getByName("se.ifmo.ru"), 8888);
        s = new DatagramSocket();


    }

    public void sendMinimalPoint(float minPoint) throws IOException {
        InetAddress hostIP = InetAddress.getLocalHost();
        myAddress = new InetSocketAddress(hostIP, port);
        datagramChannel = DatagramChannel.open();
        //datagramChannel.bind(myAddress);
        String minP = ""+minPoint;
        ByteBuffer buffer42 = ByteBuffer.allocate(BUFFER_SIZE);
        byte[] bb = minP.getBytes();

        buffer42.put(bb);
        buffer42.flip();
        datagramChannel.send(buffer42, myAddress);
        buffer42.clear();

        InetSocketAddress address = new InetSocketAddress(hostIP, port2);
        DatagramChannel datagramChannel2 = DatagramChannel.open();
        DatagramSocket datagramSocket = datagramChannel2.socket();
        datagramSocket.bind(address);

        datagramChannel2.receive(buffer42);
        String data = new String(buffer42.array(), "UTF-8");
        System.out.println(data);
        datagramSocket.close();
    }

    public void sendIdToRemove(int idToRem) throws IOException {
        updateId uid = new updateId();
        InetAddress hostIP = InetAddress.getLocalHost();
        myAddress = new InetSocketAddress(hostIP, port);
        datagramChannel = DatagramChannel.open();
        //datagramChannel.bind(myAddress);
        ByteBuffer buffer42 = ByteBuffer.allocate(BUFFER_SIZE);

        buffer42.putInt(idToRem);
        buffer42.flip();
        datagramChannel.send(buffer42, myAddress);
        buffer42.clear();

        InetSocketAddress address = new InetSocketAddress(hostIP, port2);
        DatagramChannel datagramChannel2 = DatagramChannel.open();
        DatagramSocket datagramSocket = datagramChannel2.socket();
        datagramSocket.bind(address);

        datagramChannel2.receive(buffer42);
        String data = new String(buffer42.array(), "UTF-8");
        System.out.println(data);
        datagramSocket.close();
        if (data.contains("Ошибка")) uid.vybor(true); else uid.vybor(false);
    }

    public void sendPQMToFilter(double idTofilter) throws IOException {
        InetAddress hostIP = InetAddress.getLocalHost();
        myAddress = new InetSocketAddress(hostIP, port);
        datagramChannel = DatagramChannel.open();
        //datagramChannel.bind(myAddress);
        ByteBuffer buffer42 = ByteBuffer.allocate(BUFFER_SIZE);

        buffer42.putDouble(idTofilter);
        buffer42.flip();
        datagramChannel.send(buffer42, myAddress);
        buffer42.clear();

        InetSocketAddress address = new InetSocketAddress(hostIP, port2);
        DatagramChannel datagramChannel2 = DatagramChannel.open();
        DatagramSocket datagramSocket = datagramChannel2.socket();
        datagramSocket.bind(address);

        datagramChannel2.receive(buffer42);
        String data = new String(buffer42.array(), "UTF-8");
        System.out.println(data);
        datagramSocket.close();
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

