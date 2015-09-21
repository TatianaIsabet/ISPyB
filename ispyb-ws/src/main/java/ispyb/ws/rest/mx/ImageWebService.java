package ispyb.ws.rest.mx;

import ispyb.server.mx.vos.collections.DataCollection3VO;
import ispyb.server.mx.vos.collections.Image3VO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

@Path("/")
public class ImageWebService extends MXRestWebService {

	private final static Logger logger = Logger.getLogger(ImageWebService.class);

	@RolesAllowed({ "User", "Manager", "LocalContact" })
	@GET
	@Path("{token}/proposal/{proposal}/mx/image/{imageId}/get")
	@Produces({ "application/json" })
	public Response getImageById(@PathParam("token") String token,
			@PathParam("proposal") String proposal, 
			@PathParam("imageId") int imageId) {

		String methodName = "getImageById";
		long start = this.logInit(methodName, logger, token, proposal, imageId);
		try {
			Image3VO image = this.getImage3Service().findByPk(imageId);
			this.logFinish(methodName, start, logger);
			return this.sendImage(image.getJpegThumbnailFileFullPath());
		} catch (Exception e) {
			return this.logError(methodName, e, start, logger);
		}
	}
	
	
		

}
