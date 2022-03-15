package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    private static AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


//    //обращение к нашему запросу идет из файла adduser.html с помощью post-запроса
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        //в респонсе (ответ) мы указываем, что будет возвращаться html страничка
//        response.setContentType("text/html;charset=utf-8");
//
//        //в реквесте (запрос) может быть кирилица (русские символы)
//        request.setCharacterEncoding("UTF-8");
//
//        //с помощью этого мы выводим далее нашу страничку
//        PrintWriter pw = response.getWriter();
//
//        //выделили из реквеста(запроса) три переменные: имя, фамилию и зарплату, которые указываются в самих импутах в addUser.htmlq
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//
//        //создали нового пользователя
//        User user = new User(name, surname, salary);
//
//        //и добавили нового пользователя в нашу модельку
//        model.add(user, counter.getAndIncrement());
//
//        //вывели на страничку, что наш пользователь создан и дополнительно две ссылки на создание нового и вернуться на главную
//        pw.println("<html>" + "<h3>Пользователь " + name + " " + surname + " с зарплатой " + salary + " успешно создан:)</h3>" +
//                "<a href=\"addUser.html\"> Создать нового пользователя </a><br/> " +
//                "<a href=\"index.jsp\"> Домой </a>" + "</html>");
//
//    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        request.setCharacterEncoding("UTF-8");

        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        double salary = jobj.get("salary").getAsDouble();

        User user = new User(name, surname, salary);
        model.add(user, counter.getAndIncrement());

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(model.getFromList()));

    }
}
