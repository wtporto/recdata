package web.recdata.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloReCDATA {

//	@POST
//	@Path("/criar")
//	@Consumes("text/xml")
//	@Produces("text/plain")

	
	
	@GET
	@Produces("text/html")
	public String showOnlineServer(){
		return "<h1>TESTE POST CONSUMES<h1>";
	}
}
