package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public void addContact(ContactData contactData) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.saveOrUpdate(contactData);
    session.getTransaction().commit();
    session.close();
    Contacts contacts = contacts();
    Assert.assertTrue(contacts.contains(contactData));
  }

  public void addGroup(GroupData groupData) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.saveOrUpdate(groupData);
    session.getTransaction().commit();
    session.close();
    Groups groups = groups();
    Assert.assertTrue(groups.contains(groupData));
  }


  public int contactId() {
    Contacts contacts = contacts();
    int id;
    if (!contacts.isEmpty()) {
      id = contacts.stream()
              .mapToInt(ContactData::getId)
              .max()
              .getAsInt() + 1;
    } else id = 1;
    return id;
  }

  public int groupId() {
    Groups groups = groups();
    int id;
    if (!groups().isEmpty()) {
      id = groups.stream()
              .mapToInt(GroupData::getId)
              .max()
              .getAsInt() + 1;
    } else id = 1;
    return id;
  }
}
