package com.awesome.flipbid.Example;

import com.awesome.database.DatabaseHelper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by sankeerth.reddy on 04/06/15.
 */
@Path("/{id}")
public class PostResource {
    @POST
    public String writeIntoAFile(String body, @PathParam("id") long id) throws IOException, SQLException {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        String sqlQuery = "SELECT id FROM Test where ";
        String response = "";
        System.out.println("ID : " + id);
        System.out.println("Body : " + body);

        FileOutputStream fop = null;
        File file;
        try {

            file = new File("/Users/sankeerth.reddy/Desktop/1.txt");
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }


            // get the content in bytes
            byte[] contentInBytes = body.getBytes();
            databaseHelper.executeQuery(sqlQuery);
            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");
            response = "Successfully wrote " + body + " in a file with id : " + id;

            return response;
        }
        finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GET
    public String test(@PathParam("id") long id) {
        return "This is the response : " + id ;
    }
}
