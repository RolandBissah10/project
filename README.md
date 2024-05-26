Description:
This Java program simulates a barbering shop scenario where customers, both regular and VIP, arrive for service. The program manages the seating arrangement of customers, including a main chair and waiting chairs. It randomly generates events such as a customer leaving, a VIP customer arriving, or a regular customer arriving.

Features:

    The main chair represents the barber's working chair, where a customer receives service.
    Waiting chairs are available for customers who arrive when the main chair is occupied.
    VIP customers are given priority and may occupy the main chair immediately if available.
    Regular customers join the waiting queue if the main chair is occupied and waiting chairs are available.
    Events are triggered by pressing the space key, simulating the progression of time.

Implementation:

    The program utilizes Java's ArrayList to manage the waiting chairs and store customer information.
    It uses a Random object to generate random events.
    The Scanner class is used to capture user input to trigger events.
    Various methods are implemented to handle event triggering, customer seating, and updating the seating arrangement.

How to Use:

    Run the program.
    Press the Y/y key to trigger events and observe the simulation.
    Exit the program by pressing any key other than space.
