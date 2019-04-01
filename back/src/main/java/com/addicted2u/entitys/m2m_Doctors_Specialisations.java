package com.addicted2u.entitys;

public class m2m_Doctors_Specialisations {
    private String docspec_id;
    private String docspec_doctor;
    private String docspec_specialisation;

    public String getDocspec_id() {
        return docspec_id;
    }

    public void setDocspec_id(String docspec_id) {
        this.docspec_id = docspec_id;
    }

    public String getDocspec_doctor() {
        return docspec_doctor;
    }

    public void setDocspec_doctor(String docspec_doctor) {
        this.docspec_doctor = docspec_doctor;
    }

    public String getDocspec_specialisation() {
        return docspec_specialisation;
    }

    public void setDocspec_specialisation(String docspec_specialisation) {
        this.docspec_specialisation = docspec_specialisation;
    }

    @Override
    public String toString() {
        return "m2m_Doctors_Specialisations{" +
                "docspec_id='" + docspec_id + '\'' +
                ", docspec_doctor='" + docspec_doctor + '\'' +
                ", docspec_specialisation='" + docspec_specialisation + '\'' +
                '}';
    }
}
