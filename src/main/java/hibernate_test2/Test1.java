package hibernate_test2;

import hibernate_test2.entity.Detail;
import hibernate_test2.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

public class Test1 {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") //read xml file to know how to create session
                .addAnnotatedClass(Detail.class)
                .addAnnotatedClass(Employee.class) //adding @entity class that we will assosiate to bd
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            Employee employee = new Employee(4300, "Vadym", "Kasyniuk", "IT");
            Detail detail = new Detail("Vinnitsa", "0038097234756", "kasynuikpro@gmail.com");

            session.beginTransaction();
            employee.setEmpDetail(detail);
            session.save(employee);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }
}
