package edu.asu.diging.gilesecosystem.september.core.db;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import edu.asu.diging.gilesecosystem.september.core.model.IMessage;
import edu.asu.diging.gilesecosystem.september.core.model.impl.Message;
import edu.asu.diging.gilesecosystem.util.store.IDatabaseClient;

public interface IMessageDbClient extends IDatabaseClient<IMessage> {

    public abstract List<Message> getMessages();

}