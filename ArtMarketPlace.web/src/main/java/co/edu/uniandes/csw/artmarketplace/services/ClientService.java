package co.edu.uniandes.csw.artmarketplace.services;

import co.edu.uniandes.csw.artmarketplace.api.IClientLogic;
import co.edu.uniandes.csw.artmarketplace.dtos.ClientDTO;
import co.edu.uniandes.csw.artmarketplace.providers.StatusCreated;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.resource.ResourceException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.ini4j.Wini;

/**
 * @generated
 */
@Path("/clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientService {

    @Inject
    private IClientLogic clientLogic;

    @Context
    private HttpServletResponse response;

    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public ClientDTO createClient(ClientDTO dto) {
        return clientLogic.createClient(dto);
    }

    /**
     * @generated
     */
    @GET
    public List<ClientDTO> getClients() {
        List<ClientDTO> clients = new ArrayList();
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", clientLogic.countClients());
        }
        try {
            URL url = ArtistService.class.getResource("ArtistService.class");
            String className = url.getFile();
            String filePath = className.substring(0, className.indexOf("WEB-INF") + "WEB-INF".length());
            Wini ini = new Wini(new File(filePath + "/shiro.ini"));
            String path = ini.get("main", "stormpathClient.apiKeyFileLocation");
            ApiKey apiKey = ApiKeys.builder().setFileLocation(path).build();
            Client client = Clients.builder().setApiKey(apiKey).build();
            clients = clientLogic.getClients(page, maxRecords);
            for (ClientDTO clientDTO : clients) {
                try {
                    Account account = client.getResource(clientDTO.getUserId(), Account.class);
                    clientDTO.setFirstName(account.getGivenName());
                    clientDTO.setLastname(account.getSurname());
                    clientDTO.setEmail(account.getEmail());
                }  catch (ResourceException e) {
                    Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, "No existe el usuario con ese ID");
                    Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        } catch (IOException e) {
            clients.clear();
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, "Error al leer el archivo de shiro");
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, e);
        }
        return clients;
    }

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ClientDTO getClient(@PathParam("id") Long id) {
        return clientLogic.getClient(id);
    }

    /**
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ClientDTO updateClient(@PathParam("id") Long id, ClientDTO dto) {
        dto.setId(id);
        return clientLogic.updateClient(dto);
    }

    /**
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteClient(@PathParam("id") Long id) {
        clientLogic.deleteClient(id);
    }
}
