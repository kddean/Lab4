import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/")
public class ToDo_REST {

Database data = new Database();
ToDo item = new ToDo();
/*@POST
@Produces({MediaType.APPLICATION_XML})
@Path("/xml")

public void getXML(){
	
}*/

@POST
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Produces({MediaType.APPLICATION_JSON})
@Path("/create")
public Response create(ToDo i){
	int id = i.getid();
	String m = i.getMessage();
	boolean r = data.post(id, m);
	String a;
	if(r == true)
		a = "Success";
	else 
		a = "Error";
	return Response.ok(a, "application/xml").build();
}

@GET
@Produces({MediaType.APPLICATION_XML})
@Path("/displayxml/id: [0-9]+")
public Response displayxml(@PathParam("id") int id){
	String i = data.get(id);
	return Response.ok(i, "application/xml").build();
}

@GET
@Produces({MediaType.APPLICATION_JSON})
@Path("/displayjson/id: [0-9]+")
public Response display(@PathParam("id") int id){
	String i = data.get(id);
	String json = "Error";
	try{
		json = new ObjectMapper().writeValueAsString(i);
	} catch(Exception e){
		return Response.status(500).build();
	}
	return Response.ok(json,"application/json").build();
}

@GET
@Produces({MediaType.APPLICATION_XML})
@Path("/displayall/xml")
public Response displayALLXML(){
	String list = data.getList();
	return Response.ok(list, "application/xml").build();
}

@GET
@Produces({MediaType.APPLICATION_JSON})
@Path("/displayall/json")
public Response displayALL(){
	String list = data.getList();
	String json = "Error";
	try{
		json = new ObjectMapper().writeValueAsString(list);
	}catch (Exception e){
		return Response.status(500).build();
	}
	return Response.ok(json, "application/json").build();
}

@DELETE
@Produces({MediaType.APPLICATION_JSON})
@Path("/delete/id: [0-9]+")
public Response delete(@PathParam("id") int id){
	data.delete(id);
	return Response.ok().build();
}


}
