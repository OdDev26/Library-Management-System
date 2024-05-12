 This application uses Spring's Default Schema to get authentication and authorization out of the box. It uses Basic Authentication.
 To create a book, ensure you have a user with a librarian role as this is the only role allowed to add books, you can achieve this by
 running the sql scripts below after starting the application:

 insert into users values('john',1,'$2a$12$hM9yE893vOpIqEwSkOej6.9qJUy8AGp0rFsxOxpojQwlLhT4meEjG');
 insert into authorities values('ROLE_LIBRARIAN','john');


> [!NOTE]  
>  The encode password above was encoded using bcrypt password encoder and the value is **test**.

> [!IMPORTANT]  
> It's also important to note that all apis except the create patron api are locked automatically by Spring security and require the 
necessary basic authentication (username and password) to access them. You can go through the SecurityConfig class to know what roles
are configured for the various roles.

