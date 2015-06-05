package com.awesome.flipbid.Example;

import com.awesome.flipbid.config.MessagesConfiguration;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sankeerth.reddy on 04/06/15.
 */

@Path(value = "/hello")
public class HelloResource {

    private final MessagesConfiguration conf;

    public HelloResource(MessagesConfiguration conf) {
        this.conf = conf;
    }

    @GET
    public String sayHello() {
        return conf.getHelloRajesh();
    }

    @POST
    @Path("/{id}")
    public String writeIntoAFile(String body, @PathParam("id") long id) throws IOException {
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

}

