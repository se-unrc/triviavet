package trivia;

import org.javalite.activejdbc.Model;

public class User extends Model {
  static{
    validatePresenceOf("username");
  }
}
