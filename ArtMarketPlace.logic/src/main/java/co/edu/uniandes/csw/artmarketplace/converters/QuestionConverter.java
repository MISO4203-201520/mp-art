/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.artmarketplace.converters;

import co.edu.uniandes.csw.artmarketplace.dtos.QuestionDTO;
import co.edu.uniandes.csw.artmarketplace.entities.ArtworkEntity;
import co.edu.uniandes.csw.artmarketplace.entities.ClientEntity;
import co.edu.uniandes.csw.artmarketplace.entities.QuestionEntity;
import java.util.LinkedList;
import java.util.List;

/**
 * La Clase QuestionConverter tiene como finalidad sevir como intermediadora
 * entre la capa logica y de persistencia de la aplicaci�n, mediante la
 * implementaci�n del patron DTO, de la cual esta se encarga de hacer la
 * conversi�n de DTO a Entity y viseversa.
 *
 * @author lf.mendivelso10
 */
public abstract class QuestionConverter {

    public QuestionConverter() {
    }

    /**
     * Este m�todo se encarga de hacer la conversi�n de un objeto del tipo
     * Entity a un objeto DTO del tipo Question solo con fines de referencia.
     *
     * @param entity es la entidad a convertir.
     * @return questionDTO es el resultado de la conversi�n.
     */
    public static QuestionDTO refEntity2DTO(QuestionEntity entity) {
        if (entity != null) {
            QuestionDTO dto = new QuestionDTO();
            dto.setId(entity.getId());
            dto.setEmail(entity.getEmail());
            dto.setQuestion(entity.getQuestion());
            dto.setDate(entity.getDate());
            dto.setArtistEmail(entity.getArtistEmail());
            return dto;
        } else {
            return null;
        }
    }

    /**
     * El m�todo refDTO2Entity tiene como prop�sito hacer la conversi�n de la referencia de un DTO a
     * una Entity, par proposito de consulta. 
     * @param dto es la referencia en DTO
     * @return entity es la referencia en Entidad obtenida despues de la conversi�n.
     */
    public static QuestionEntity refDTO2Entity(QuestionDTO dto) {
        if (dto != null) {
            QuestionEntity entity = new QuestionEntity();
            entity.setId(dto.getId());
            return entity;
        } else {
            return null;
        }
    }

    /**
     * El m�todo basicDTO2Entity esta dise�ado para hacer la coversi�n completa de una objetivo
     * del tipo DTO a un objeto Entity del tipo Question.
     * @param dto es el objeto DTO a convertir
     * @return entity es el objeto Entity producto de la conversi�n. 
     */
    public static QuestionEntity basicDTO2Entity(QuestionDTO dto) {
        if (dto != null) {
            QuestionEntity entity = new QuestionEntity();
            entity.setId(dto.getId());
            entity.setArtwork(ArtworkConverter.refDTO2Entity(dto.getArtwork()));
            entity.setClient(ClientConverter.refDTO2Entity(dto.getClient()));
            entity.setDate(dto.getDate());
            entity.setArtistEmail(dto.getArtistEmail());
            entity.setEmail(dto.getEmail());
            entity.setQuestion(dto.getQuestion());
            return entity;
        } else {
            return null;
        }
    }
    
    /**
     * Este m�todo se encarga de hacer la conversi�n de un objeto del tipo
     * Entity a un objeto DTO del tipo Question.
     *
     * @param entity es la entidad a convertir.
     * @return questionDTO es el resultado de la conversi�n.
     */
    public static QuestionDTO basicEntity2DTO(QuestionEntity entity) {
        if (entity != null) {
            QuestionDTO dto = new QuestionDTO();
            dto.setId(entity.getId());
            dto.setArtwork(ArtworkConverter.refEntity2DTO(entity.getArtwork()));
            dto.setClient(ClientConverter.refEntity2DTO(entity.getClient()));
            dto.setEmail(entity.getEmail());
            dto.setQuestion(entity.getQuestion());
            dto.setDate(entity.getDate());
            dto.setArtistEmail(entity.getArtistEmail());
            return dto;
        } else {
            return null;
        }
    }
    
    /**
     * El m�todo fullDTO2Entity se encarga de hacer la conversi�n completa de un objeto DTO a un
     * Objeto Entity del tipo Question.
     * @param dto es el objeto tipo DTO a convertir.
     * @return entity es el objeto del tipo Entity producto de la conversi�n.
     */
    public static QuestionEntity fullDTO2Entity(QuestionDTO dto) {
        if (dto != null) {
            QuestionEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }
    
    /**
     * El m�todo fullEntity2DTO se encarga de hacer la conversi�n completa de un objeto Entity a un
     * Objeto DTO del tipo Question.
     * @param dto es el objeto tipo DTO a convertir.
     * @return entity es el objeto del tipo Entity producto de la conversi�n.
     */
    public static QuestionDTO fullEntity2DTO(QuestionEntity entity) {
        if (entity != null) {
            QuestionDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }
    
    /**
     * El m�todo listEntity2DTO tiene como funcionalidad hacer la conversi�n de una listas de objetos
     * del tipo QuestionEntity a una lista de Objetos del tipo QuestionDTO.
     * @param entities es la lista de objetos del tipo QuestionEntity a convertir
     * @return dtos es la lista de objetos del tipo QuestionDTO resutado de la conversi�n
     */
    public static List<QuestionDTO> listEntity2DTO(List<QuestionEntity> entities) {
        List<QuestionDTO> dtos = new LinkedList<QuestionDTO>();
        if (entities != null) {
            for (QuestionEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }
    
    /**
     * El m�todo listDTO2Entity tiene como funcionalidad hacer la conversi�n de una listas de objetos
     * del tipo QuestionDTO a una lista de Objetos del tipo QuestionEntity.
     * @param dtos es la lista de objetos del tipo QuestionEntity a convertir
     * @return entities es la lista de objetos del tipo QuestionDTO resutado de la conversi�n
     */
    public static List<QuestionEntity> listDTO2Entity(List<QuestionDTO> dtos) {
        List<QuestionEntity> entities = new LinkedList<QuestionEntity>();
        if (dtos != null) {
            for (QuestionDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
    /**
     * El m�todo childDTO2Entity tiene como prop�sito el ayudar al manejo de la relaci�n uno a muchas pregunas 
     * asociadas a una obra de arte, en el que su uso asigna a la pregunta, la obra de arte a la cual esta asociada.
     * @param question es la pregunta en objetivo tipo QuestionDTO con la informaci�n de la pregunta.
     * @param artwork es la obra frente a la cual se hace la pregunta.
     * @return entity es el objeto del tipo QuestionEntity con la asignaci�n de la obra de arte a la pregunta.
     */
    public static QuestionEntity childDTO2Entity(QuestionDTO question, ArtworkEntity artwork){
        QuestionEntity entity = basicDTO2Entity(question);
        entity.setArtwork(artwork);
        return entity;
    }
    
    /**
     * El m�todo childDTO2Entity tiene como finalidad el manejo de la relaci�n uno a muchos entre
     * la obra de arte y las preguntas realizadas por los compradores frente a lo obra de arte.
     * @param questions es el conjunto de preguntas asociadas a la obra de arte.
     * @param artwork es la obra de arte sobre la cual se hacen las preguntas.
     * @return entities es el conjuntos de preguntas convertidas desde DTOs a Entities para su almacenamiento.
     */
    public static List<QuestionEntity> childListDTO2Entity(List<QuestionDTO> questions, ArtworkEntity artwork) {
        List<QuestionEntity> entities = new LinkedList<QuestionEntity>();
        if (questions != null) {
            for (QuestionDTO dto : questions) {
                entities.add(childDTO2Entity(dto, artwork));
            }
        }
        return entities;
    }

}
