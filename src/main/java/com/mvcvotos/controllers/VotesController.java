package com.mvcvotos.controllers;

import java.util.Set;

import com.mvcvotos.models.VotesModel;
import com.mvcvotos.utilities.ModelTransformer;

public class VotesController extends Controller {

    public void executeVote(String votedCandidate) {
        VotesModel votesModel = ModelTransformer.modelToVotesModel(model);
        Set<String> registeredCandidates = votesModel.getCandidates();

        if (registeredCandidates.contains(votedCandidate)) {
            votesModel.registerVote(votedCandidate);
        }
    }
}
