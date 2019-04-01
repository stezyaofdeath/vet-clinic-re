-- creating a schema
create schema vet_clinic;

-- set this schema as default
use vet_clinic;

-- creating tables
create table medicalServices (
	ms_id int primary key,
    ms_name varchar(30) not null,
    ms_cost float not null
);

create table specialisations (
	spec_id int primary key,
    spec_name varchar(40) not null
);

create table m2m_specialisations_medicalServices (
	specms_id int auto_increment primary key,
	specms_specialisation int,
    specms_medicalService int,
    
    foreign key(specms_specialisation) references specialisations(spec_id) on delete cascade,
    foreign key(specms_medicalService) references medicalServices(ms_id) on delete cascade
);

create table doctors (
	doc_id varchar(10) primary key,
    doc_name varchar(20) not null,
    doc_surname varchar(20) not null,
    doc_patronum varchar(20) not null
);

create table m2m_doctors_specialisations (
	docspec_id int auto_increment primary key,
	docspec_doctor varchar(20),
    docspec_specialisation int,
    
    foreign key(docspec_doctor) references doctors(doc_id),
    foreign key(docspec_specialisation) references specialisations(spec_id)
);

create table clients (
	cl_id int primary key auto_increment,
    cl_login varchar(20) not null,
    cl_password varchar(40) not null,
    
    cl_name varchar(30) default "НЕ УКАЗАНО",
    cl_surname varchar(30) default "НЕ УКАЗАНО",
    cl_patronum varchar(30) default "НЕ УКАЗАНО",
    cl_phone varchar(30) default "НЕ УКАЗАНО",
    cl_email varchar(300) default "НЕ УКАЗАНО"
);

create table procedures (
	proc_id int auto_increment primary key,
    proc_client int,
    proc_medService int,
    proc_doctor varchar(20),
    proc_date datetime,
    
    foreign key(proc_medService) references medicalServices(ms_id),
    foreign key(proc_doctor) references doctors(doc_id)
);