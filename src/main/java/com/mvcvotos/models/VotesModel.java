package com.mvcvotos.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VotesModel extends Model {

    private Map<String, Integer> registeredVotes;

    public VotesModel() {
        registeredVotes = new HashMap<>();

        registeredVotes.put("Candidato 1", 0);
        registeredVotes.put("Candidato 2", 0);
        registeredVotes.put("Candidato 3", 0);

    }

    public void registerVote(String candidate) {
        registeredVotes.replace(candidate, registeredVotes.get(candidate) + 1);

        notifyObservers();
    }

    public Map<String, Integer> getRegisteredVotes() {
        return registeredVotes;
    }

    public Set<String> getCandidates() {
        return registeredVotes.keySet();
    }

}
