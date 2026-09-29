public class DailyStatistics {

    private int[] serviceTimes;   // service times of students served today
    private int count;            // how many values are actually stored

    public DailyStatistics(int capacity) {
        serviceTimes = new int[capacity];
        count = 0;
    }

    // Record the service time of one student who has been served
    public void addServiceTime(int time) {
        if (count == serviceTimes.length) {
            System.out.println("Statistics array is full - cannot record " + time);
            return;
        }
        serviceTimes[count] = time;
        count++;
    }

    // Load an existing set of values (used by the menu in Part D)
    public void loadTimes(int[] times) {
        count = 0;
        for (int i = 0; i < times.length && i < serviceTimes.length; i++) {
            serviceTimes[i] = times[i];
            count++;
        }
    }

    // 1. Total students served
    public int totalStudentsServed() {
        return count;
    }

    // 2. Total service time - traverse and accumulate
    public int totalServiceTime() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total = total + serviceTimes[i];
        }
        return total;
    }

    // 3. Average service time
    public double averageServiceTime() {
        if (count == 0) {
            return 0;
        }
        return (double) totalServiceTime() / count;
    }

    // 4. Highest service time - assume the first value, then improve
    public int highestServiceTime() {
        if (count == 0) {
            return 0;
        }
        int highest = serviceTimes[0];
        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] > highest) {
                highest = serviceTimes[i];
            }
        }
        return highest;
    }

    // 5. Lowest service time - assume the first value, then improve
    public int lowestServiceTime() {
        if (count == 0) {
            return 0;
        }
        int lowest = serviceTimes[0];
        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] < lowest) {
                lowest = serviceTimes[i];
            }
        }
        return lowest;
    }

    // 6. Number of services longer than 10 minutes
    public int servicesLongerThanTen() {
        int longCount = 0;
        for (int i = 0; i < count; i++) {
            if (serviceTimes[i] > 10) {
                longCount++;
            }
        }
        return longCount;
    }

    public void displayArray() {
        if (count == 0) {
            System.out.println("No service times recorded yet.");
            return;
        }
        System.out.print("Service times array: [ ");
        for (int i = 0; i < count; i++) {
            System.out.print(serviceTimes[i] + " ");
        }
        System.out.println("]");
    }

    public void displayStatistics() {
        System.out.println("========================================");
        System.out.println("           DAILY STATISTICS");
        System.out.println("========================================");
        displayArray();
        System.out.println("Total students served      : " + totalStudentsServed());
        System.out.println("Total service time         : " + totalServiceTime() + " min");
        System.out.printf("Average service time       : %.2f min%n", averageServiceTime());
        System.out.println("Highest service time       : " + highestServiceTime() + " min");
        System.out.println("Lowest service time        : " + lowestServiceTime() + " min");
        System.out.println("Services longer than 10 min: " + servicesLongerThanTen());
        System.out.println("========================================");
    }

    // Give the sorting tasks a copy of the recorded values
    public int[] getTimes() {
        int[] copy = new int[count];
        for (int i = 0; i < count; i++) {
            copy[i] = serviceTimes[i];
        }
        return copy;
    }
}
