package main;

import collectionClasses.LabWork;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Connect {
    private static final int BUFFER_SIZE = 1024;
    static int port = 11;
    InetSocketAddress myAddress;
    DatagramChannel datagramChannel;
    private ByteBuffer sendBuffer = ByteBuffer.allocate(16384);

    public void connectServer() throws IOException {}

    public void sendMinimalPoint(float minPoint) throws IOException {
        InetAddress hostIP = InetAddress.getLocalHost();
        myAddress = new InetSocketAddress(hostIP, port);
        datagramChannel = DatagramChannel.open();
        datagramChannel.bind(null);

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.asFloatBuffer().put(minPoint);
        buffer.flip();
        datagramChannel.send(buffer, myAddress);
        buffer.clear();
    }

    public void sendComand(String comand) throws IOException{
        InetAddress hostIP = InetAddress.getLocalHost();
        myAddress = new InetSocketAddress(hostIP, port);
        datagramChannel = DatagramChannel.open();
        datagramChannel.bind(null);

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.put(comand.getBytes());
        buffer.flip();
        datagramChannel.send(buffer, myAddress);
        buffer.clear();
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
        }catch (IOException e){
            System.out.println("----\nВозникла ошибка:\n");
            e.printStackTrace();
        }
    }
}
