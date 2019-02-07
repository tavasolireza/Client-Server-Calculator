import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class CalculatorClient {
    private CalculatorClient(int port) throws IOException {
        System.out.println("Enter equation (i.e. multiply 7 8)");
        Scanner scanner = new Scanner(System.in);
        String equation = scanner.nextLine();
        InetAddress ip = InetAddress.getLocalHost();
        Socket socket = new Socket(ip, port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printer = new PrintWriter(socket.getOutputStream());
        printer.println(equation);
        printer.flush();

        //read
        String answer = reader.readLine();
        String[] output = answer.split(" ");
        System.out.println("Answer: " + output[0] + "  Calculation Time: " + output[1] + " seconds");
    }

    public static void main(String[] args) throws IOException {
        new CalculatorClient(2400);
    }
}
