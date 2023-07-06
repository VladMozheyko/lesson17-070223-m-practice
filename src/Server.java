import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /*
    Задача
    На сервере загадывается число. Задача клиента его угадать. Сервер ведет счет попыток и возвращает
    их клиенту
     */

    /*
    Теория
    Классы для работы с сокетами
    Socket - создание соединения
    ServerSocket - прослушивание соединения

    Парсинг - вытаскивание из строки какого-то конкретного значения, например, int
    В Java для каждого примитивного типа существуют классы обертки
    Класс обертка - дополнение каких-то данных другими данными или функциями в отдельном классе - по факту создается объект,
    который содержит в себе другой объект и что-то к нему добавляет.
    Яркий пример: обертки примитивных типов. У примитивных типов нет встроенных оперций, поэтому у каждому примитивному типу
    добавляется класс обертка, содержая операции для этого типа, например:
    Integer имеет операцию parseInt() - метод, который считывает int из текста
     */
    public static void main(String[] args) throws IOException {  // Передаем исключение из main просто чтобы он код запустился
        ServerSocket serverSocket = new ServerSocket(8056); // Создаем слушателя соединений на порту 8056
        int a = 10;// Загадываем число
        int count = 0;// Количество попыток
        System.out.println("Сервер работает...");
        while (true){ //Бесконечный цикл
            Socket socket = serverSocket.accept(); // Создаем соединение с клиентом
            System.out.println(socket.getPort());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));     // Поток для чтения
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));   // Поток для записи

            while (true) {
                int b = Integer.parseInt(reader.readLine());   // Считываю(парсю) переменную типа int
                System.out.println(b);
                count++;
                System.out.println("Попытка: " + count);
                if (a == b) {
                    writer.write("Вы угадали"); // Пишем ответ пользователю
                    writer.newLine(); // Переходим на новую строку
                    writer.flush(); // Отправляем сообщение
                } else if (a != b) {
                    writer.write("Вы не угадали"); // Пишем ответ пользователю
                    writer.newLine(); // Переходим на новую строку
                    writer.flush(); // Отправляем сообщение
                }
                if (a == b) { // Если угадали число, закрываем все потоки и выходим из цикла
                    break;// Сама операция выхода из цикла
                }

            }
            writer.write("Вы угадали");
            writer.flush();
            writer.close();  // Всегда нужно закрывать потоки, после того как они выполнили свою рабту
            reader.close();
            socket.close();
            break;

        }
    }

    /*
    ДЗ
    1) Сделать Камень, ножницы, бумага через сокета
     */


}
