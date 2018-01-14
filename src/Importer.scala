import Services.MySQLConnector



object Importer {

  def main(args: Array[String]): Unit = {

    println("---- Start import! ----")

    // connect to the database named "mysql" on the localhost
    val url = "jdbc:mysql://localhost/mysql"
    val username = "root"
    val password = "root"



    //TODO: перенести val в сам класс и скрыть, работаем только через интерфейс
    val databaseConnector = new MySQLConnector()
    val connection = databaseConnector.connect(url, username, password)
    val result = databaseConnector.executeQuery(connection, "SELECT * FROM hrmanager.departments;")
    databaseConnector.fetchResult(result)


  }


}
