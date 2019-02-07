import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class CalculatorServer {
    enum Operator {add, subtract, divide, multiply, sin, cos, tan, cot}

    private CalculatorServer() throws IOException {
        String res;
        ServerSocket msocket = new ServerSocket(2400);
        while (true) {
            Socket socket = msocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            String read = reader.readLine();
            res = calculate(socket, read);
            printWriter.println(res);
            printWriter.flush();

        }

    }

    private static String calculate(Socket socket, String read) throws IOException, ArithmeticException {
        float answer = 0;
        String result;
        float radians = 0;
        String[] input = read.split(" ");
        if (input.length == 3) {
            Operator operator = Operator.valueOf(input[0].toLowerCase());
            switch (operator) {
                case add:
                    long startTime = System.nanoTime();
                    answer = Float.valueOf(input[1]) + Float.valueOf(input[2]);
                    long endTime = System.nanoTime();
                    double seconds = (double)(endTime-startTime) / 1_000_000_000.0;
                    result = Float.toString(answer) + " " + Double.toString(seconds);
//                    System.out.println(endTime);
//                    System.out.println(startTime);
//                    System.out.println(endTime - startTime);
//                    System.out.println("time is "+ (endTime - startTime) / 1_000_000_000.0);
                    return result;
                case subtract:
                    startTime = System.nanoTime();
                    answer = Float.valueOf(input[1]) - Float.valueOf(input[2]);
                    endTime = System.nanoTime();
                    seconds = (double)(endTime-startTime) / 1_000_000_000.0;
                    result = Float.toString(answer) + " " + Double.toString(seconds);                    return result;
                case divide:
                    startTime = System.nanoTime();
                    answer = Float.valueOf(input[1]) / Float.valueOf(input[2]);
                    endTime = System.nanoTime();
                    seconds = (double)(endTime-startTime) / 1_000_000_000.0;
                    result = Float.toString(answer) + " " + Double.toString(seconds);
                    return result;
                case multiply:
                    startTime = System.nanoTime();
                    answer = Float.valueOf(input[1]) * Float.valueOf(input[2]);
                    endTime = System.nanoTime();
                    seconds = (double)(endTime-startTime) / 1_000_000_000.0;
                    result = Float.toString(answer) + " " + Double.toString(seconds);
                    return result;
            }
        } else {
            Operator operator = Operator.valueOf(input[0].toLowerCase());
            switch (operator) {
                case sin:
                    long startTime = System.nanoTime();
                    radians = (float) Math.toRadians(Float.valueOf(input[1]));
                    answer = (float) Math.sin(radians);
                    long endTime = System.nanoTime();
                    double seconds = (double)(endTime-startTime) / 1_000_000_000.0;
                    result = Float.toString(answer) + " " + Double.toString(seconds);
                    return result;
                case cos:
                    startTime = System.nanoTime();
                    radians = (float) Math.toRadians(Float.valueOf(input[1]));
                    answer = (float) Math.cos(radians);
                    endTime = System.nanoTime();
                    seconds = (double)(endTime-startTime) / 1_000_000_000.0;
                    result = Float.toString(answer) + " " + Double.toString(seconds);
                    return result;
                case tan:
                    startTime = System.nanoTime();
                    radians = (float) Math.toRadians(Float.valueOf(input[1]));
                    answer = (float) Math.tan(radians);
                    endTime = System.nanoTime();
                    seconds = (double)(endTime-startTime) / 1_000_000_000.0;
                    result = Float.toString(answer) + " " + Double.toString(seconds);
                    return result;
                case cot:
                    startTime = System.nanoTime();
                    radians = (float) Math.toRadians(Float.valueOf(input[1]));
                    answer = 1 / ((float) Math.tan(radians));
                    endTime = System.nanoTime();
                    seconds = (double)(endTime-startTime) / 1_000_000_000.0;
                    result = Float.toString(answer) + " " + Double.toString(seconds);
                    return result;

            }
        }
        return "Error";
    }


    public static void main(String[] args) throws IOException {
        new CalculatorServer();
    }
}
