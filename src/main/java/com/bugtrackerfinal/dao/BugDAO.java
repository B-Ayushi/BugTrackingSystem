package com.bugtrackerfinal.dao;

import java.util.ArrayList;
import java.util.List;

import com.bugtrackerfinal.model.Bug;
import com.bugtrackerfinal.util.FirebaseUtil;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

public class BugDAO {

    public boolean addBug(Bug bug) {
        try {
            Firestore db = FirebaseUtil.getFirestore();

            if (bug.getBugId() == null || bug.getBugId().isBlank()) {
                String generatedId = db.collection("bugs").document().getId();
                bug.setBugId(generatedId);
            }

            WriteResult result = db.collection("bugs")
                    .document(bug.getBugId())
                    .set(bug)
                    .get();

            return result != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bug> getAllBugs() {
        List<Bug> bugList = new ArrayList<>();

        try {
            Firestore db = FirebaseUtil.getFirestore();
            CollectionReference bugs = db.collection("bugs");
            ApiFuture<QuerySnapshot> future = bugs.get();

            List<com.google.cloud.firestore.QueryDocumentSnapshot> documents =
                    future.get().getDocuments();

            for (com.google.cloud.firestore.QueryDocumentSnapshot doc : documents) {
                Bug bug = doc.toObject(Bug.class);
                if (bug != null) {
                    bugList.add(bug);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bugList;
    }

    public List<Bug> getBugsByReporter(String email) {
        List<Bug> bugList = new ArrayList<>();

        try {
            Firestore db = FirebaseUtil.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("bugs")
                    .whereEqualTo("reportedBy", email)
                    .get();

            List<com.google.cloud.firestore.QueryDocumentSnapshot> documents =
                    future.get().getDocuments();

            for (com.google.cloud.firestore.QueryDocumentSnapshot doc : documents) {
                Bug bug = doc.toObject(Bug.class);
                if (bug != null) {
                    bugList.add(bug);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bugList;
    }

    public List<Bug> getBugsAssignedTo(String email) {
        List<Bug> bugList = new ArrayList<>();

        try {
            Firestore db = FirebaseUtil.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("bugs")
                    .whereEqualTo("assignedTo", email)
                    .get();

            List<com.google.cloud.firestore.QueryDocumentSnapshot> documents =
                    future.get().getDocuments();

            for (com.google.cloud.firestore.QueryDocumentSnapshot doc : documents) {
                Bug bug = doc.toObject(Bug.class);
                if (bug != null) {
                    bugList.add(bug);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bugList;
    }

    public List<Bug> getAssignedBugs() {
        List<Bug> all = getAllBugs();
        List<Bug> filtered = new ArrayList<>();

        for (Bug bug : all) {
            String assignedTo = bug.getAssignedTo();
            if (assignedTo != null && !assignedTo.isBlank()) {
                filtered.add(bug);
            }
        }

        return filtered;
    }

    public List<Bug> getUnassignedBugs() {
        List<Bug> all = getAllBugs();
        List<Bug> filtered = new ArrayList<>();

        for (Bug bug : all) {
            String assignedTo = bug.getAssignedTo();
            if (assignedTo == null || assignedTo.isBlank()) {
                filtered.add(bug);
            }
        }

        return filtered;
    }

    public List<Bug> getAssignedBugsByStatus(String status) {
        List<Bug> assigned = getAssignedBugs();
        List<Bug> filtered = new ArrayList<>();

        for (Bug bug : assigned) {
            if (bug.getStatus() != null && bug.getStatus().equalsIgnoreCase(status)) {
                filtered.add(bug);
            }
        }

        return filtered;
    }

    public boolean updateBug(String bugId, String priority, String status,
                             String assignedTo, String deadline, String comments) {
        try {
            Firestore db = FirebaseUtil.getFirestore();

            if (priority != null && !priority.isBlank()) {
                db.collection("bugs").document(bugId).update("priority", priority).get();
            }

            if (status != null && !status.isBlank()) {
                db.collection("bugs").document(bugId).update("status", status).get();
            }

            if (assignedTo != null) {
                db.collection("bugs").document(bugId).update("assignedTo", assignedTo).get();
            }

            if (deadline != null) {
                db.collection("bugs").document(bugId).update("deadline", deadline).get();
            }

            if (comments != null) {
                db.collection("bugs").document(bugId).update("adminComments", comments).get();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBugStatusOnly(String bugId, String status) {
        try {
            Firestore db = FirebaseUtil.getFirestore();

            if (status != null && !status.isBlank()) {
                db.collection("bugs").document(bugId).update("status", status).get();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}