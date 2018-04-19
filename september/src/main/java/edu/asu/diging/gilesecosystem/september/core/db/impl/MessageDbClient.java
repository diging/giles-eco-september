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

import edu.asu.diging.gilesecosystem.september.core.db.IMessageDbClient;
import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.model.MessageType;
import edu.asu.diging.gilesecosystem.september.core.model.impl.Message;
import edu.asu.diging.gilesecosystem.util.store.objectdb.DatabaseClient;

@Component
public class MessageDbClient extends DatabaseClient<IMessage> implements IMessageDbClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PersistenceContext(unitName = "DataPU")
    private EntityManager em;

    @Override
    protected String getIdPrefix() {
        return "MSG";
    }

    @Override
    protected IMessage getById(String id) {
        return em.find(Message.class, id);
    }

    @Override
    protected EntityManager getClient() {
        return em;
    }

    @Override
    public List<Message> getMessages() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query = query.select(root);

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Message> getMessages(int offset, int pageSize, String sortField) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query = query.select(root).orderBy(builder.desc(root.get(sortField)));

        TypedQuery<Message> finalQuery = em.createQuery(query);
        finalQuery.setFirstResult(offset).setMaxResults(pageSize);
        return finalQuery.getResultList();
    }

    @Override
    public List<Message> getFilteredMessages(int offset, int pageSize, List<MessageType> filterType, String sortField) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query = query.select(root).where(root.get("type").in(filterType)).orderBy(builder.desc(root.get(sortField)));

        TypedQuery<Message> finalQuery = em.createQuery(query);
        finalQuery.setFirstResult(offset).setMaxResults(pageSize);
        return finalQuery.getResultList();
    }

    @Override
    public int getNumberOfMessages() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query = query.select(root);

        return em.createQuery(query).getResultList().size();
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

    @Override
    public int getNumberOfFilteredMessages(List<MessageType> regex) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Message> query = builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query = query.select(root).where(root.get("type").in(regex));

        return em.createQuery(query).getResultList().size();
    }
}
