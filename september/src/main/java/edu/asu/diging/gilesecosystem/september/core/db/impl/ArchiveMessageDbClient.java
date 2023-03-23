package edu.asu.diging.gilesecosystem.september.core.db.impl;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import edu.asu.diging.gilesecosystem.september.core.db.IArchiveMessageDbClient;
import edu.asu.diging.gilesecosystem.september.core.model.IArchiveMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.ArchiveMessage;
import edu.asu.diging.gilesecosystem.util.store.objectdb.DatabaseClient;

@Component
public class ArchiveMessageDbClient extends DatabaseClient<IArchiveMessage> implements IArchiveMessageDbClient {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @PersistenceContext(unitName = "DataPU")
    private EntityManager em;
    
    @Override
    public List<ArchiveMessage> getMessages() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ArchiveMessage> query = builder.createQuery(ArchiveMessage.class);
        Root<ArchiveMessage> root = query.from(ArchiveMessage.class);
        query = query.select(root);

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<ArchiveMessage> getMessages(int offset, int pageSize, String sortField) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ArchiveMessage> query = builder.createQuery(ArchiveMessage.class);
        Root<ArchiveMessage> root = query.from(ArchiveMessage.class);
        query = query.select(root).orderBy(builder.desc(root.get(sortField)));
        TypedQuery<ArchiveMessage> finalQuery = em.createQuery(query);
        finalQuery.setFirstResult(offset).setMaxResults(pageSize);
        return finalQuery.getResultList();
    }

    @Override
    public List<ArchiveMessage> getFilteredMessages(int offset, int pageSize, List<MessageType> filterType,
            String sortField) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ArchiveMessage> query = builder.createQuery(ArchiveMessage.class);
        Root<ArchiveMessage> root = query.from(ArchiveMessage.class);
        query = query.select(root).where(root.get("type").in(filterType)).orderBy(builder.desc(root.get(sortField)));
        TypedQuery<ArchiveMessage> finalQuery = em.createQuery(query);
        finalQuery.setFirstResult(offset).setMaxResults(pageSize);
        return finalQuery.getResultList();
    }

    @Override
    public int getNumberOfMessages() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ArchiveMessage> query = builder.createQuery(ArchiveMessage.class);
        Root<ArchiveMessage> root = query.from(ArchiveMessage.class);
        query = query.select(root);
        return em.createQuery(query).getResultList().size();
    }

    @Override
    public int getNumberOfFilteredMessages(List<MessageType> types) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ArchiveMessage> query = builder.createQuery(ArchiveMessage.class);
        Root<ArchiveMessage> root = query.from(ArchiveMessage.class);
        query = query.select(root).where(root.get("type").in(types));
        return em.createQuery(query).getResultList().size();
    }

    @Override
    protected String getIdPrefix() {
        return "MSG";
    }

    @Override
    protected IArchiveMessage getById(String id) {
        return em.find(ArchiveMessage.class, id);
    }

    @Override
    protected EntityManager getClient() {
        return em;
    }
    
    @PreDestroy
    public void shutdown() {
        em.close();
        em = null;

        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            logger.error("Derby is shutdown.", e);
        }
    }

}
