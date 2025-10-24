/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms;


import com.ecms.dbms.models.Instructor;
import com.ecms.dbms.models.Learner;

public class SessionManager {
    private static Instructor loggedInInstructor;
    private static Learner loggedInLearner;

    public static void setLoggedInInstructor(Instructor instructor) {
        loggedInInstructor = instructor;
    }

    public static Instructor getLoggedInInstructor() {
        return loggedInInstructor;
    }

    public static void setLoggedInLearner(Learner learner) {
        loggedInLearner = learner;
    }

    public static Learner getLoggedInLearner() {
        return loggedInLearner;
    }

    public static void clearSession() {
        loggedInInstructor = null;
        loggedInLearner = null;
    }
}

