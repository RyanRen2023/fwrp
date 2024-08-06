/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquin.cst8288.fwrptomc.service;

import com.algonquin.cst8288.fwrptomc.dao.RatingAndFeedbackViewDao;
import com.algonquin.cst8288.fwrptomc.model.RatingAndFeedbackView;

import java.util.List;

public class RatingAndFeedbackViewService {

    private RatingAndFeedbackViewDao feedbackViewDao;

    public RatingAndFeedbackViewService() {
        this.feedbackViewDao = new RatingAndFeedbackViewDao();
    }

    public List<RatingAndFeedbackView> getRatingAndFeedbackByRetailerId(int retailerId) {
        return feedbackViewDao.getRatingAndFeedbackByRetailerId(retailerId);
    }
}