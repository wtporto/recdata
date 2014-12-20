package web.recdata.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;




@Path("/servidor")
public class VerificaServidor {
	@GET
    @Path("/online")
    @Produces("application/json")
    public Server servidorOnline() {
            
            Server server = new Server();
            server.setOnline(true);
            
            return server;
    }	
}
