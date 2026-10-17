package api.utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1: Fetches all data (rows/columns) from a specified sheet (e.g., UserData)
    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        // Path to your Excel file inside the project structure
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        //user.dir will give the project location
        XLUtility xlutil = new XLUtility(path);

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        // Create a 2D array matching the sizes found in the Excel sheet
        String userData[][] = new String[totalrows][totalcols];

        // Loop starts from 1 to skip the header row (Row 0)
        for (int i = 1; i <= totalrows; i++) {
            for (int j = 0; j < totalcols; j++) {
                // i-1 maps the Excel data properly to the 0-indexed 2D array
            	userData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }
        return userData;
    }

    // returns only the usernames
    @DataProvider(name = "UserNames")
    public String[] getUserNAmes() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xlutil = new XLUtility(path);

        int totalrows = xlutil.getRowCount("Sheet1");
        //int totalcols = xlutil.getCellCount("Sheet1", 1);

        String userData[] = new String[totalrows];

        for (int i = 1; i <= totalrows; i++) 
        {
              	userData[i - 1] = xlutil.getCellData("Sheet1", i, 1);
        }
        return userData;
    }
}
