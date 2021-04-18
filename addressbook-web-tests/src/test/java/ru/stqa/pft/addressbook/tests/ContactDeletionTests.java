package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.contact().selectRandomContact();
    app.contact().deleteSelectedContact();
    app.contact().acceptAlertDelete();
    app.contact().checkDeleteMessage();
  }
}
