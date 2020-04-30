package Core;
import Core.Data.Access.CustomAccess;
import Core.Data.Access.CustomWordAccess;
import Core.Data.Models.Word;
import GUI.*;

import java.io.IOException;
import Core.Data.Database;
import com.j256.ormlite.dao.Dao;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    private final static String DB_URL="jdbc:sqlite:vocappulary.db";
    public static void main(String[] args) throws SQLException, IOException {
        Database.init(DB_URL);
        initInterface();

    }
    public static void initInterface() throws SQLException {
        final JFrame MainWindow=new MainWindow();
        //final JFrame ProfileWindow=new ProfileWindow();
        //final JFrame BegintestWindow=new BeginTestWindow();
    }
}
