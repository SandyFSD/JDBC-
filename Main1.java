import java.sql.*;
public class Main1 {
    private static final String DB_URL="jdbc:mysql://localhost:3306/mec";
    private static final String USER="user_name";
    private static final String PASS="****";
    public static void main(String[] args) {
        String insertSql="INSERT INTO employees(name,salary)VALUES(?,?)";
        String selectSql="SELECT id,name,salary FROM employees";
        try (
            Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement insertStmt=conn.prepareStatement(insertSql) ) {
            System.out.println("---Executing Insert Operation---");
            insertStmt.setString(1,"John Doc");
            insertStmt.setDouble(2,50000.00);
            int rowsAffected=insertStmt.executeUpdate();
            System.out.println(rowsAffected+"row(s)inserted successfully.");
            insertStmt.setString(1,"Jane Smith");
            insertStmt.setDouble(2,65000.00);
            rowsAffected=insertStmt.executeUpdate();
            System.out.println(rowsAffected+"row(s)inserted successfully.");
            System.out.println("---Executing Select Operation");
            try (
                PreparedStatement selectStmt=conn.prepareStatement(selectSql);
                ResultSet rs=selectStmt.executeQuery()
                ){
                    System.out.println("Employee data:");
                    System.out.println("--------------");
                    while(rs.next()){
                        int id=rs.getInt("id");
                        String name=rs.getString("name");
                        double salary=rs.getDouble("salary");
                        System.out.printf("ID=%d, Name:%s,Salary:$%.2f%n",id,name,salary);
                    }
            }
        }catch(SQLException e){
            System.err.println("SQL Exception Occured:"+e.getMessage());
            e.printStackTrace();
        }
    }
}

