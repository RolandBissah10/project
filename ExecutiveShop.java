import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class ExecutiveShop extends JFrame implements KeyListener {

    private String mainChair;
    private CustomLinkedList waitingChairs;
    private int ordCount;
    private int vipCount;
    private String lastRejectedORD;
    private String lastRejectedVIP;
    private Random random;
    private static final int NUM_WAITING_CHAIRS = 5;

    public ExecutiveShop() {
        this.mainChair = null;
        this.waitingChairs = new CustomLinkedList();
        this.ordCount = 0;
        this.vipCount = 0;
        this.lastRejectedORD = null;
        this.lastRejectedVIP = null;
        this.random = new Random();

        // Set up the JFrame
        this.setTitle("Barber Shop Simulator");
        this.setSize(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
        System.out.println("X:         Event:          State of the Shop:");
        System.out.println("+--------+-----------+-----------------------+");
    }

    private void newClient(int type) {
        String client;

        if (type == 1) { // VIP
            if (lastRejectedVIP != null) {
                client = lastRejectedVIP;
                lastRejectedVIP = null;
            } else {
                vipCount++;
                client = "VIP" + vipCount;
            }
        } else { // ORD
            if (lastRejectedORD != null) {
                client = lastRejectedORD;
                lastRejectedORD = null;
            } else {
                ordCount++;
                client = "ORD" + ordCount;
            }
        }

        if (mainChair == null) {
            mainChair = client;
        } else {
            if (waitingChairs.size() < NUM_WAITING_CHAIRS) {
                if (client.startsWith("VIP")) {
                    CustomLinkedList newQueue = new CustomLinkedList();
                    boolean inserted = false;

                    while (!waitingChairs.isEmpty()) {
                        String c = waitingChairs.poll();
                        if (c.startsWith("ORD") && !inserted) {
                            newQueue.add(client);
                            inserted = true;
                        }
                        newQueue.add(c);
                    }

                    if (!inserted) {
                        newQueue.add(client);
                    }

                    waitingChairs = newQueue;
                } else {
                    waitingChairs.add(client);
                }
            } else {
                if (client.startsWith("VIP")) {
                    lastRejectedVIP = client;
                } else {
                    lastRejectedORD = client;
                }
                printState("+-" + client);
                return;
            }
        }

        printState("++" + client);
    }

    private void clientDone() {
        if (mainChair != null) {
            String clientLeaving = mainChair;
            mainChair = waitingChairs.poll();
            printState("--" + clientLeaving);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            int x = random.nextInt(4);
            System.out.print(x + " ---> ");
            if (x == 0) {
                clientDone();
            } else if (x == 1) {
                newClient(1);
            } else {
                newClient(2);
            }
        } else {
            // If any other key is pressed, exit the program
            System.exit(0);
        }
    }

    private void printState(String event) {
        StringBuilder state = new StringBuilder();
        state.append("[");
        if (mainChair != null) {
            state.append(mainChair);
        }
        for (int i = 0; i < NUM_WAITING_CHAIRS; i++) {
            if (i < waitingChairs.size()) {
                state.append(" : ").append(waitingChairs.get(i));
            } else {
                state.append(" : ----");
            }
        }
        state.append(" ]");
        System.out.printf("%-4s%-8s%-2s%s\n", "", "(" + event + ")", "", state.toString());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    public static void main(String[] args) {
        new ExecutiveShop();
    }
}

class Node {
    String data;
    Node next;

    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class CustomLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(String data) {
        Node newNode = new Node(data);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            head = newNode;
        }
        size++;
    }

    public String poll() {
        if (head == null) {
            return null;
        }
        String data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
