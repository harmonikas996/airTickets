# airTickets - Web application for Flight reservation
Find Your Next Flight Ticket, Book Hotel, Rent A Car On Your Next Journey

# Software & Source Code

1. Install

[nodeJS](https://nodejs.org/en/)

[Angular 6](https://angular.io/)

[STS](https://spring.io/tools3/sts/all)

[Visual Studio Code](https://code.visualstudio.com/download)

2. clone repository 
```
git clone https://github.com/harmonikas996/airTickets.git
```


# How to start Angular 6 application
1. Open project with Visual Studio Code
2. Install dependencies via terminal command
```
npm install
```

3. Build application with
```
ng build
```
4. Run application with
```
ng serve
```

# How to start SpringBoot application
1. Import project to STS
2. Update application.properties file with your MySql Database credentials and url
3. Run As Spring Boot App

Application provides comperhensive admin panels with roles for System Admin, Rentacar Admin, Hotel Admin, Aircompany Admin. It is possible to search and reserve flight tickets, hotel rooms and cars. System Administrator is responsible for creation of Companies. SysAdmin can also set administrators for each company.

Authentication and authorization system is implemented with Spring Security on the back-end side, and with Auth and Role Guard on front-end side. Process of registration requires account activation by visiting a link provided via email. User password are encripted.

Special thanks to my dear colleagues Nikola ([@rebnikola](https://github.com/rebnikola)) & Marko ([@mardelaa](https://github.com/mardelaa)) for their contribution and dedicated work!
