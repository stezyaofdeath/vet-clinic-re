-- set 'vet_clinic'-schema as default
use vet_clinic;

-- filling in 'medicalServices'-table
insert into medicalServices(ms_id, ms_name, ms_cost) values(1, "Общий осмотр", 10.50);
insert into medicalServices(ms_id, ms_name, ms_cost) values(2, "Стрижка шерсти", 6.25);
insert into medicalServices(ms_id, ms_name, ms_cost) values(3, "Стрижка когтей", 5.20);

insert into medicalServices(ms_id, ms_name, ms_cost) values(4, "Стабилизация позвоночника", 250.0);
insert into medicalServices(ms_id, ms_name, ms_cost) values(5, "Лечение поверхтностных ран", 12.90);
insert into medicalServices(ms_id, ms_name, ms_cost) values(6, "Лечение переломов", 150.0);
insert into medicalServices(ms_id, ms_name, ms_cost) values(7, "Лечение вывихов", 100.80);

insert into medicalServices(ms_id, ms_name, ms_cost) values(8, "Проверка зрения", 120.20);

-- filling in the 'specialisations'-table
insert into specialisations(spec_id, spec_name) values(1, "Терапевт");
insert into specialisations(spec_id, spec_name) values(2, "Хирург");
insert into specialisations(spec_id, spec_name) values(3, "Офтальмолог");

-- filling in the 'm2m_specialisations_medicalServices'-table
insert into m2m_specialisations_medicalServices(specms_specialisation, specms_medicalService) values(1, 1);
insert into m2m_specialisations_medicalServices(specms_specialisation, specms_medicalService) values(1, 2);
insert into m2m_specialisations_medicalServices(specms_specialisation, specms_medicalService) values(1, 3);

insert into m2m_specialisations_medicalServices(specms_specialisation, specms_medicalService) values(2, 4);
insert into m2m_specialisations_medicalServices(specms_specialisation, specms_medicalService) values(2, 5);
insert into m2m_specialisations_medicalServices(specms_specialisation, specms_medicalService) values(2, 6);
insert into m2m_specialisations_medicalServices(specms_specialisation, specms_medicalService) values(2, 7);

insert into m2m_specialisations_medicalServices(specms_specialisation, specms_medicalService) values(3, 8);

-- filling in the 'doctors'-table
insert into doctors(doc_id, doc_name, doc_surname, doc_patronum) values("120299", "Александр", "Коваленко", "Олегович");
insert into doctors(doc_id, doc_name, doc_surname, doc_patronum) values("040898", "Степан", "Язепов", "Юрьевич");

-- filling in the 'm2m_doctors_specialisations'-table
insert into m2m_doctors_specialisations(docspec_doctor, docspec_specialisation) values("120299", 1);
insert into m2m_doctors_specialisations(docspec_doctor, docspec_specialisation) values("120299", 2);
insert into m2m_doctors_specialisations(docspec_doctor, docspec_specialisation) values("040898", 1);
insert into m2m_doctors_specialisations(docspec_doctor, docspec_specialisation) values("040898", 3);







