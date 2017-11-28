package connect;

import java.sql.*;

public class Connect {
    private Connection c = null;
    public void connect(String nomeBanco){
        connectDB(nomeBanco);
        try {
            existsTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void create(String sql){
        if(c != null)
            createDB(sql);
    }
    public void insert(String sql){
        if(c != null)
            insertDB(sql);
    }
    public void update(String sql){
        if(c != null)
            updateDB(sql);
    }
    public void delete(String sql){
        if(c != null)
            deleteDB(sql);
    }
    public ResultSet Select(String sql){
        ResultSet res = null;
        if(c != null){
            res = selectDB(sql);
        }
        return res;
    }
    private void connectDB(String nomeBanco)
    {
          try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+nomeBanco);
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
          }
          System.out.println("Conectado com sucesso no banco: "+nomeBanco);
    }
    private void existsTable() throws SQLException{
        DatabaseMetaData meta = c.getMetaData();
        
        ResultSet res = meta.getTables(null, null, "site", new String[] {"TABLE"});
        if(!res.next()) {
             String sql = "CREATE TABLE site " +
                       "(sitcod INTEGER PRIMARY KEY autoincrement," +
                       " siturl TEXT NOT NULL)";
            create(sql);
        }
        res = meta.getTables(null, null, "assunto", new String[] {"TABLE"});
        if(!res.next()) {
             String sql = "CREATE TABLE assunto " +
                       "(asscod INTEGER PRIMARY KEY autoincrement," +
                       " assass TEXT NOT NULL)";
            create(sql);
        }
        res = meta.getTables(null, null, "horario", new String[] {"TABLE"});
        if(!res.next()) {
             String sql = "CREATE TABLE horario " +
                       "(horcod INTEGER PRIMARY KEY autoincrement," +
                       " horhor TEXT NOT NULL)";
            create(sql);
        }
        res = meta.getTables(null, null, "noticia", new String[] {"TABLE"});
        if(!res.next()) {
             String sql = "CREATE TABLE noticia " +
                       "(notcod INTEGER PRIMARY KEY autoincrement," +
                       " notlin TEXT NOT NULL," +
                       " nottit TEXT NOT NULL)";
            create(sql);
        }
        
    }
    private void createDB(String sql)
    {
        Statement stmt = null;
        try {
            //c.setAutoCommit(true);
          stmt = c.createStatement();
          stmt.executeUpdate(sql);
          stmt.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Tabela criada com sucesso");
  }
   
  private void insertDB(String sql)
  {
        Statement stmt = null;
        try {
          //c.setAutoCommit(true);
          stmt = c.createStatement();
          stmt.executeUpdate(sql);
          stmt.close();
          //c.commit();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Insert realizado com sucesso");
  }
   
  private ResultSet selectDB(String sql)
  {
        Statement stmt = null;
        try {
          //c.setAutoCommit(false);
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery(sql);
          //stmt.close();
          return rs;
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Select realizado com sucesso");
        return null;
  }
   
  private void updateDB(String sql)
  {
    Statement stmt = null;
    try {
      //c.setAutoCommit(false);
      stmt = c.createStatement();
      stmt.executeUpdate(sql);
      //c.commit();
      stmt.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Update realizado com sucesso");
  }
   
  private void deleteDB(String sql)
  {
        Statement stmt = null;
        try {
          //c.setAutoCommit(false);
          stmt = c.createStatement();
          stmt.executeUpdate(sql);
          //c.commit();
          stmt.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Delete realizado com sucesso");
  }
}
