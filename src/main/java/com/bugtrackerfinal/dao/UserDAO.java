package com.bugtrackerfinal.dao;

import com.bugtrackerfinal.model.User;
import com.bugtrackerfinal.util.FirebaseUtil;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class UserDAO {

    public boolean registerUser(User user) {
        try {
            Firestore db = FirebaseUtil.getFirestore();

            DocumentSnapshot existingUser = db.collection("users")
                    .document(user.getEmail())
                    .get()
                    .get();

            if (existingUser.exists()) {
                return false;
            }

            WriteResult result = db.collection("users")
                    .document(user.getEmail())
                    .set(user)
                    .get();

            return result != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User loginUser(String email, String password) {
        try {
            Firestore db = FirebaseUtil.getFirestore();
            DocumentSnapshot document = db.collection("users").document(email).get().get();

            if (document.exists()) {
                User user = document.toObject(User.class);
                if (user != null && user.getPassword().equals(password)) {
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}