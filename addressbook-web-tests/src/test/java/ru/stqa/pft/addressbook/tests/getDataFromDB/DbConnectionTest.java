package ru.stqa.pft.addressbook.tests.getDataFromDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {
  Logger logger = LoggerFactory.getLogger(DbConnectionTest.class);

  @Test
  public void testDbConnection() {
    try {
      String dbName = "addressbook";
      String dbUser = "root";
      String dbPassword = "";
      String dBUrl = "jdbc:mysql://localhost/" + dbName + "?user=" + dbUser + "&password=" + dbPassword;
      Connection connect = DriverManager.getConnection(dBUrl);
      Statement statement = connect.createStatement();
      String request = "select group_id,group_name,group_header,group_footer from group_list";
      ResultSet result = statement.executeQuery(request);
      Groups groups = new Groups();
      while (result.next()) {
        groups.add(new GroupData()
                .withId(result.getInt("group_id"))
                .withName(result.getString("group_name"))
                .withHeader(result.getString("group_header"))
                .withFooter(result.getString("group_footer")));
      }
      result.close(); // не собираемся больше читать данные, можно освободить память
      statement.close(); // никаких запросов больше не будет выполняться
      connect.close(); // закрываем соединение с БД
      logger.info("Here is a list of the groups from the DB: \n");
      groups.forEach(System.out::println);

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
