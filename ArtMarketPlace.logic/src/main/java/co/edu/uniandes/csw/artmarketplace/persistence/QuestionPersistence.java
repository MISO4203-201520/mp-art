package co.edu.uniandes.csw.artmarketplace.persistence;

import co.edu.uniandes.csw.artmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.artmarketplace.entities.QuestionEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * La clase QuestionPersistence esta dise�ada para gestionar la creaci�n,
 * modificaci�n y eleminaci�n de preguntas realizadas por los comprador sobre
 * las obras de arte.
 *
 * @author lf.mendivelso10
 */
@Stateless
public class QuestionPersistence extends CrudPersistence<QuestionEntity> {

    /**
     * El m�todo constructor de QuestionPersistence no contiene parametro, y en
     * su ejecuci�n, hace la asignaci�n del tipo de EntityClass requerida para
     * la uso del CrudPersistence.
     */
    public QuestionPersistence() {
        this.entityClass = QuestionEntity.class;
    }

    public List<QuestionEntity> listByArtwork(Long artist) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("artwork_id", artist);
            Query q = em.createNamedQuery("QuestionEntity.listByArtwork");
            q.setParameter("artwork_id",artist);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
