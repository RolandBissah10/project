import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BarberingShopSimulation {
    private static final int NUM_WAITING_CHAIRS = 5;
    private static List<String> waitingChairs = new ArrayList<>();
    private static String mainChair = null;
    private static int vipCounter = 0;
    private static int ordCounter = 0;

    public static void main(String[] args) {
        initializeWaitingChairs();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press the Y/y to trigger an event or any other key to exit:");

        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("Y")) {
                triggerEvent();
            } else if (input.equals("y")) {
                triggerEvent();
            }  else {
                break;
            }
        }

        scanner.close();
    }

    public static void triggerEvent() {
        Random random = new Random();
        int x = random.nextInt(4);

        switch (x) {
            case 0:
                if (mainChair != null) {
                    System.out.println(x + " ---> (-- " + mainChair + ") " + getSitOrder());
                    mainChair = null;
                    shiftWaitingCustomers();
                } else {
                    System.out.println(x + " ---> (No client in main chair)");
                }
                break;
            case 1:
                vipCounter++;
                String vip = "VIP" + vipCounter;
                if (mainChair == null) {
                    mainChair = vip;
                    System.out.println(x + " ---> (++ " + vip + ") " + getSitOrder());
                } else if (waitingChairs.size() < NUM_WAITING_CHAIRS) {
                    waitingChairs.add(0, vip);
                    System.out.println(x + " ---> (++ " + vip + ") " + getSitOrder());
                } else {
                    System.out.println(x + " ---> (+- " + vip + ") " + getSitOrder());
                }
                break;
            case 2:
            case 3:
                ordCounter++;
                String ord = "ORD" + ordCounter;
                if (mainChair == null) {
                    mainChair = ord;
                    System.out.println(x + " ---> (++ " + ord + ") " + getSitOrder());
                } else if (waitingChairs.size() < NUM_WAITING_CHAIRS) {
                    waitingChairs.add(0, ord);
                    System.out.println(x + " ---> (++ " + ord + ") " + getSitOrder());
                } else {
                    System.out.println(x + " ---> (+- " + ord + ") " + getSitOrder());
                }
                break;
        }
    }

    public static void initializeWaitingChairs() {
        for (int i = 0; i < NUM_WAITING_CHAIRS; i++) {
            waitingChairs.add(null);
        }
    }

    public static String getSitOrder() {
        StringBuilder sitOrder = new StringBuilder("[");
        if (mainChair != null) {
            sitOrder.append(mainChair);
        }
        for (String customer : waitingChairs) {
            if (customer != null) {
                sitOrder.append(", ").append(customer);
            }
        }
        sitOrder.append("]");
        return sitOrder.toString();
    }

    public static void shiftWaitingCustomers() {
        if (waitingChairs.size() > 0) {
            mainChair = waitingChairs.remove(waitingChairs.size() - 1);
        }
    }
}
