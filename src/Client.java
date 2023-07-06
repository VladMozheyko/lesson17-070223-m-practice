import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8056); // Создаем сокет для соединения с сервером на порту 8056
        System.out.println(socket.getPort());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));  // Поток записи
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Поток чтения
        System.out.println("Клиент работает...");

        Scanner scanner = new Scanner(System.in); // Сканер для считывания пользовательского ввода
         String request = " ";
           while (!request.equals("Вы угадали")) {
               System.out.println("Игра");
               int input = scanner.nextInt();
               writer.write(String.valueOf(input));  // Операция преобразования int к строке
               writer.newLine(); // Переходим на новую строку
               writer.flush(); // Отправляем строку
               request = reader.readLine(); // Получаю запрос в строку
           }
    }
}
