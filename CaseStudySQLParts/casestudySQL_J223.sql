create database transportsystem;
use transportsystem;

create table vehicles (
    vehicleid int auto_increment primary key,
    model varchar(255) not null,
    capacity decimal(10, 2) not null,
    vtype varchar(50) not null,
    vstatus varchar(50) not null
);

create table routes (
    routeid int auto_increment primary key,
    startdestination varchar(255) not null,
    enddestination varchar(255) not null,
    distance decimal(10, 2) not null
);

create table trips (
    tripid int auto_increment primary key,
    vehicleid int,
    routeid int,
    departuredate datetime not null,
    arrivaldate datetime not null,
    vstatus varchar(50) not null,
    triptype varchar(50) default 'freight',
    maxpassengers int,
    foreign key (vehicleid) references vehicles(vehicleid),
    foreign key (routeid) references routes(routeid)
);

create table passengers (
    passengerid int auto_increment primary key,
    firstname varchar(255) not null,
    gender varchar(255) not null,
    age int not null,
    email varchar(255) unique not null,
    phonenumber varchar(50) not null
);

create table bookings (
    bookingid int auto_increment primary key,
    tripid int,
    passengerid int,
    bookingdate datetime not null,
    vstatus varchar(50) not null,
    foreign key (tripid) references trips(tripid),
    foreign key (passengerid) references passengers(passengerid)
);

create table drivers (
    driverid int auto_increment primary key,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    licensenumber varchar(255) unique not null,
    vstatus varchar(50) not null
);

create table driverallocations (
    allocationid int auto_increment primary key,
    tripid int,
    driverid int,
    allocationdate datetime not null,
    foreign key (tripid) references trips(tripid),
    foreign key (driverid) references drivers(driverid)
);

create table driverdeallocations (
    deallocationid int auto_increment primary key,
    tripid int,
    driverid int,
    deallocationdate datetime not null,
    foreign key (tripid) references trips(tripid),
    foreign key (driverid) references drivers(driverid)
);