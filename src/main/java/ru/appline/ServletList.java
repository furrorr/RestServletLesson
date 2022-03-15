package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        if (id == 0) {
            pw.print(gson.toJson(model.getFromList()));

            //если ID больше 0, то мы мы проводим проверку
        } else if (id > 0) {
            //если ID больше размера мапы, то "такого пользователя нет"
            if (id > model.getFromList().size()) {
                pw.print("Пользователь не найден");

            //если нет, то выводим данные данного пользователя
            } else {
                pw.println(gson.toJson(model.getFromList().get(id).getName()));
                pw.println(gson.toJson(model.getFromList().get(id).getSurname()));
                pw.println(gson.toJson(model.getFromList().get(id).getSalary()));
            }

            //если мы выводим отрицательное значение, то выводим ошибку
        } else {
            pw.print("Введите положительное число");
        }
    }

//    //обращение к нашему запросу идет из файла adduser.html с помощью get-запроса
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        //в респонсе (ответ) мы указываем, что будет возвращаться html страничка
//        response.setContentType("text/html;charset=utf-8");
//
//        //с помощью этого мы выводим далее нашу страничку
//        PrintWriter pw = response.getWriter();
//
//        //получение ID из нашего поля, которое мы будем заполнять
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        //и проверку по этому ID:
//        //если он равен 0, то мы будем выводить всех доступных пользователей
//        if (id == 0) {
//            pw.print("<html>" + "<h3> Доступные пользователи: </h3><br/>" + "ID пользователя: " + "<ul>");
//
//            for (Map.Entry<Integer, User> entry : model.getFromList().entrySet()) {
//                pw.print("<li>" + entry.getKey() + "</li>" +
//                        "<ul>" + "<li>Имя: " + entry.getValue().getName() + "</li>" +
//                        "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
//                        "<li>Зарплата: " + entry.getValue().getSalary() + "</li>" + "</ul>");
//            }
//            pw.print("</ul>" + "<a href=\"index.jsp\"> Домой </a>" + "</html>");
//
//            //если ID больше 0, то мы мы проводим проверку
//        } else if (id > 0) {
//
//            //если ID больше размера мапы, то "такого пользователя нет"
//            if (id > model.getFromList().size()) {
//                pw.print("<html>" + "<h3> Такого пользователя нет :(</h3>" + "<a href=\"index.jsp\"> Домой </a>" + "</html>");
//
//            //если нет, то выводим данные данного пользователя
//            } else {
//                pw.print("<html>" + "<h3> Запрошенный пользователь: </h3>" + "<br/>" +
//                        "Имя: " + model.getFromList().get(id).getName() + "<br/>" +
//                        "Фамилия: " + model.getFromList().get(id).getSurname() + "<br/>" +
//                        "Зарплата: " + model.getFromList().get(id).getSalary() + "<br/>" +
//                        "<a href=\"index.jsp\"> Домой </a>" + "</html>");
//            }
//
//            //если мы выводим отрицательное значение, то выводим ошибку
//        } else {
//            pw.print("<html>" + "<h3> ID должен быть больше нуля </h3>" + "<a href=\"index.jsp\"> Домой </a>" + "</html>");
//        }
//    }
}
