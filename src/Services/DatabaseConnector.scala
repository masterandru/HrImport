package Services

import java.sql.{Connection, ResultSet}

trait DatabaseConnector {
  def connect(url: String, user: String, password: String): Connection

  def executeQuery(connection: Connection, query: String): ResultSet

  def close(connection: Connection): Unit
}