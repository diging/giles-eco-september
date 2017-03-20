package edu.asu.diging.gilesecosystem.september.core.service;

import edu.asu.diging.gilesecosystem.requests.ISystemMessageRequest;

public interface IRequestProcessor {

    public abstract void processRequest(ISystemMessageRequest request);

}