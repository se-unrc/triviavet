package trivia;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.options;

import static spark.Spark.before;
import static spark.Spark.after;
import static spark.Spark.halt;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;

import trivia.User;
import trivia.BasicAuth;

import com.google.gson.Gson;
import java.util.Map;

import java.util.ArrayList;

class QuestionParam
{
  String description;
  ArrayList<OptionParam> options;
}

class OptionParam
{
  String description;
  Boolean correct;
}

public class App
{
    static User currentUser;

    public static void main( String[] args )
    {
      before((request, response) -> {
        Base.open();

        String headerToken = (String) request.headers("Authorization");

        if (
          headerToken == null ||
          headerToken.isEmpty() ||
          !BasicAuth.authorize(headerToken)
        ) {
          halt(401);
        }

        currentUser = BasicAuth.getUser(headerToken);
      });

      after((request, response) -> {
        Base.close();
        response.header("Access-Control-Allow-Origin", "*");
        response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        response.header("Access-Control-Allow-Headers",
          "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
      });

      options("/*", (request, response) -> {
        return "OK";
      });

      get("/hello/:name", (req, res) -> {
        return "hello" + req.params(":name");
      });

      post("/questions", (req, res) -> {
        QuestionParam bodyParams = new Gson().fromJson(req.body(), QuestionParam.class);

        Question question = new Question();
        question.set("description", bodyParams.description);
        question.save();

        for(OptionParam item: bodyParams.options) {
          Option option = new Option();
          option.set("description", item.description).set("correct", item.correct);
          question.add(option);
        }

        return question;
      });

      post("/login", (req, res) -> {
        res.type("application/json");

        // if there is currentUser is because headers are correct, so we only
        // return the current user here
        return currentUser.toJson(true);
      });

      post("/users", (req, res) -> {
        Map<String, Object> bodyParams = new Gson().fromJson(req.body(), Map.class);

        User user = new User();
        user.set("username", bodyParams.get("username"));
        user.set("password", bodyParams.get("password"));
        user.saveIt();

        res.type("application/json");

        return user.toJson(true);
      });
    }
}
