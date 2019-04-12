package trivia;

import static spark.Spark.get;
import static spark.Spark.post;

public class App
{
    public static void main( String[] args )
    {
      get("/hello/:name", (req, res) -> {
        return "hello" + req.params(":name");
      });

      post("/answers", (req, res) -> {
        // do something
        // return response
      }
    }
}
