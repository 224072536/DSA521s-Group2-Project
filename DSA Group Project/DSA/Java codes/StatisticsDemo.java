public class StatisticsDemo {
    public static void main(String[] args) {
        DailyStatistics stats = new DailyStatistics(100);

        // Service times of the students served during the simulated day
        stats.addServiceTime(12);   // Maria      - Registration
        stats.addServiceTime(5);    // Tomas      - Student Card
        stats.addServiceTime(8);    // Ndapewa    - Fees
        stats.addServiceTime(4);    // Simon      - Documents
        stats.addServiceTime(7);    // Helena     - Academic Enquiry
        stats.addServiceTime(6);    // Petrus     - Fees
        stats.addServiceTime(15);   // Johanna    - Registration
        stats.addServiceTime(11);   // Erastus    - Academic Enquiry

        stats.displayStatistics();

        System.out.println("\nManual check:");
        System.out.println("12+5+8+4+7+6+15+11 = 68, 68/8 = 8.50");
        System.out.println("Highest = 15, Lowest = 4, Over 10 min = 12, 15, 11 -> 3");
    }
}
