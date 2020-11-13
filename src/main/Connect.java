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
    static int port = 13, port2=12;
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

    public void sendComand(String comand) throws IOException{
        byte[] sendData = comand.getBytes();
        a = new InetSocketAddress(InetAddress.getLocalHost(), 8888);
        s = new DatagramSocket();

        DatagramPacket o =
                new DatagramPacket(sendData, sendData.length, a);
        s.send(o);
        System.out.println("SENDED:"+sendData.length);

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

    public void waitForResponse() throws IOException {
        byte[] recvBuf = new byte[15000];
        DatagramPacket i =
                new DatagramPacket(recvBuf, recvBuf.length);
        s.receive(i);

        String message = new String(i.getData()).trim();
        System.out.println(message);

    }
}
