package edu.asu.diging.gilesecosystem.september.core.db.impl;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import edu.asu.diging.gilesecosystem.september.core.db.ISystemMessageDbClient;
import edu.asu.diging.gilesecosystem.september.core.model.ISystemMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.SystemMessage;
import edu.asu.diging.gilesecosystem.util.store.objectdb.DatabaseClient;

@Component
public class SystemMessageDbClient extends DatabaseClient<ISystemMessage> implements ISystemMessageDbClient {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager em;

    @Override
    protected String getIdPrefix() {
        return "MSG";
    }

    @Override
    protected ISystemMessage getById(String id) {
        return em.find(SystemMessage.class, id);
    }

    @Override
    protected EntityManager getClient() {
        return em;
    }

    @Override
    public List<SystemMessage> getMessages() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<SystemMessage> query = builder.createQuery(SystemMessage.class);
        Root<SystemMessage> root = query.from(SystemMessage.class);
        query = query.select(root);

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<SystemMessage> getMessages(int offset, int pageSize, String sortField) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<SystemMessage> query = builder.createQuery(SystemMessage.class);
        Root<SystemMessage> root = query.from(SystemMessage.class);
        query = query.select(root).orderBy(builder.desc(root.get(sortField)));

        TypedQuery<SystemMessage> finalQuery = em.createQuery(query);
        finalQuery.setFirstResult(offset).setMaxResults(pageSize);
        return finalQuery.getResultList();
    }

    @Override
    public List<SystemMessage> getFilteredMessages(int offset, int pageSize, List<MessageType> filterType, String sortField) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<SystemMessage> query = builder.createQuery(SystemMessage.class);
        Root<SystemMessage> root = query.from(SystemMessage.class);
        query = query.select(root).where(root.get("type").in(filterType)).orderBy(builder.desc(root.get(sortField)));

        TypedQuery<SystemMessage> finalQuery = em.createQuery(query);
        finalQuery.setFirstResult(offset).setMaxResults(pageSize);
        return finalQuery.getResultList();
    }

    @Override
    public int getNumberOfMessages() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<SystemMessage> query = builder.createQuery(SystemMessage.class);
        Root<SystemMessage> root = query.from(SystemMessage.class);
        query = query.select(root);

        return em.createQuery(query).getResultList().size();
    }

    @PreDestroy
    public void shutdown() {
        em.close();
    }

    @Override
    public int getNumberOfFilteredMessages(List<MessageType> messageType) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<SystemMessage> query = builder.createQuery(SystemMessage.class);
        Root<SystemMessage> root = query.from(SystemMessage.class);
        query = query.select(root).where(root.get("type").in(messageType));

        return em.createQuery(query).getResultList().size();
    }
}
