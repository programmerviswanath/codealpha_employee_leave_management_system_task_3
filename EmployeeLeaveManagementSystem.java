import java.util.*;

class Employee {
    int id;
    String name;
    int leaveBalance = 10;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class LeaveRequest {
    int empId;
    int daysRequested;
    String status;

    public LeaveRequest(int empId, int daysRequested) {
        this.empId = empId;
        this.daysRequested = daysRequested;
        this.status = "Pending";
    }

    @Override
    public String toString() {
        return "Employee ID: " + empId + " | Days: " + daysRequested + " | Status: " + status;
    }
}

public class EmployeeLeaveManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<Integer, Employee> employees = new HashMap<>();
    static List<LeaveRequest> leaveRequests = new ArrayList<>();

    public static void addEmployee() {
        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // newline
        System.out.print("Enter employee name: ");
        String name = sc.nextLine();
        employees.put(id, new Employee(id, name));
        System.out.println("Employee added successfully!\n");
    }

    public static void requestLeave() {
        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();
        if (employees.containsKey(id)) {
            System.out.print("Enter number of leave days: ");
            int days = sc.nextInt();
            LeaveRequest req = new LeaveRequest(id, days);
            leaveRequests.add(req);
            System.out.println("Leave request submitted.\n");
        } else {
            System.out.println("Employee not found.\n");
        }
    }

    public static void approveLeave() {
        for (LeaveRequest req : leaveRequests) {
            if (req.status.equals("Pending")) {
                Employee emp = employees.get(req.empId);
                if (emp.leaveBalance >= req.daysRequested) {
                    emp.leaveBalance -= req.daysRequested;
                    req.status = "Approved";
                } else {
                    req.status = "Rejected - Insufficient Balance";
                }
            }
        }
        System.out.println("All pending leave requests processed.\n");
    }

    public static void viewLeaveStatus() {
        for (LeaveRequest req : leaveRequests) {
            System.out.println(req);
        }
    }

    public static void viewEmployeeDetails() {
        for (Employee emp : employees.values()) {
            System.out.println("ID: " + emp.id + " | Name: " + emp.name + " | Leave Balance: " + emp.leaveBalance);
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("EMPLOYEE LEAVE MANAGEMENT SYSTEM");
            System.out.println("1. Add Employee");
            System.out.println("2. Request Leave");
            System.out.println("3. Approve Leave Requests");
            System.out.println("4. View Leave Status");
            System.out.println("5. View Employees");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: requestLeave(); break;
                case 3: approveLeave(); break;
                case 4: viewLeaveStatus(); break;
                case 5: viewEmployeeDetails(); break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option!"); break;
            }
        }
    }
}

