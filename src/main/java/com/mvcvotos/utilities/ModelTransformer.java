package com.mvcvotos.utilities;

import com.mvcvotos.models.Model;
import com.mvcvotos.models.VotesModel;

public class ModelTransformer {
    private ModelTransformer() {
    }

    public static VotesModel modelToVotesModel(Model model) {
        if (model instanceof VotesModel) {
            return (VotesModel) model;
        }
        return new VotesModel();
    }
}
