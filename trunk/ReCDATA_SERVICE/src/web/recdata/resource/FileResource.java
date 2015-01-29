package web.recdata.resource;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.FilenameUtils;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import web.recdata.util.FileUtil;
import br.edu.ifpb.recdata.entidades.Erro;
import br.edu.ifpb.recdata.form.FileUploadForm;

/**
 * Serviço de upload de arquivo.
 * 
 * @author Rhavy
 *
 */
@Path("/arquivo")
public class FileResource {
	
	@POST
	@Path("/upload/projeto/{idprojeto}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadProjeto(@PathParam("idprojeto") String idProjeto, 
			@MultipartForm FileUploadForm form) {

		// Tipos de uploads: projeto (pdf).
		ResponseBuilder builder = Response.status(Response.Status.NOT_MODIFIED);
		builder.expires(new Date());
		
		try {
			
			String extension = FilenameUtils.getExtension(form.getFileName());
			
			if (extension.equalsIgnoreCase(FileUtil.PDF_FILE)) {
				
				//TODO: Persistir nome original da imagem na base.
				
				// Guardar imagem no sistema de arquivo.
				FileUtil.writeFile(form.getData(), form.getFileName());
				builder.status(Response.Status.OK);
			
			} else {			
				
				Erro erro = new Erro();
				erro.setMensagem("Formato de arquivo inválido.");
				builder.status(Response.Status.NOT_ACCEPTABLE).entity(erro);
			}		
		} catch (IOException e) {			
			Erro erro = new Erro();
			erro.setMensagem("Problema ao manipular o arquivo.");
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}		

		return builder.build();
	}
}