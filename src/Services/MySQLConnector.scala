package Services

import java.sql.{Connection, DriverManager, ResultSet}

class MySQLConnector extends DatabaseConnector {

  final val driver = "com.mysql.jdbc.Driver"
//  val connection = databaseConnector.connect(url, username, password)
//  val result =

  @Override
  def connect(url: String, user: String, password: String): Connection = {
    //Class.forName(driver)
    DriverManager.getConnection(url, user, password)
  }

  @Override
  def executeQuery(connection: Connection, query: String): ResultSet = {
    connection.createStatement().executeQuery(query)
  }

  @Override
  def fetchResult(resultSet: ResultSet): Unit = {
    val md = resultSet.getMetaData
    val colNames = for (i <- 1 to md.getColumnCount) yield md.getColumnName(i)
    val buildMap = () => (for (n <- colNames) yield n -> resultSet.getObject(n)).toMap
    Iterator.continually(resultSet.next())
      .takeWhile(identity)
      .map(_ => buildMap())
      .toVector
      .toStream
      .foreach(println)

    //    while (resultSet.next()) {
    //      val uid = resultSet.getString("uid")
    //      val name = resultSet.getString("name")
    //      println("uid, name = [" + uid + ", " + name + "]")
    //    }
  }

  @Override
  def close(connection: Connection): Unit = connection.close()

}