package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {
        // Create SessionFactory
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Insert a new department
        insertDepartment(sessionFactory);

        // Delete a department by ID
        deleteDepartment(sessionFactory, 1); // Replace 1 with the ID to delete

        sessionFactory.close();
    }

    // Method to insert a new Department
    public static void insertDepartment(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Department department = new Department();
        department.setName("Computer Science");
        department.setLocation("Block A");
        department.setHodName("Dr. Smith");

        session.save(department);

        transaction.commit();
        session.close();

        System.out.println("Department inserted successfully.");
    }

    // Method to delete a Department by ID
    public static void deleteDepartment(SessionFactory sessionFactory, int departmentId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM Department WHERE departmentId = :id";
        int rowsAffected = session.createQuery(hql)
                                  .setParameter("id", departmentId)
                                  .executeUpdate();

        transaction.commit();
        session.close();

        if (rowsAffected > 0) {
            System.out.println("Department deleted successfully.");
        } else {
            System.out.println("No department found with ID: " + departmentId);
        }
    }
}
