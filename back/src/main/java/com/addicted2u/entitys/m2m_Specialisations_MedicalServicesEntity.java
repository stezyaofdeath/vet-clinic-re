package com.addicted2u.entitys;

public class m2m_Specialisations_MedicalServicesEntity {
    private String specms_id;
    private String specms_specialisation;
    private String specms_medicalServices;

    public String getSpecms_id() {
        return specms_id;
    }

    public void setSpecms_id(String specms_id) {
        this.specms_id = specms_id;
    }

    public String getSpecms_specialisation() {
        return specms_specialisation;
    }

    public void setSpecms_specialisation(String specms_specialisation) {
        this.specms_specialisation = specms_specialisation;
    }

    public String getSpecms_medicalServices() {
        return specms_medicalServices;
    }

    public void setSpecms_medicalServices(String specms_medicalServices) {
        this.specms_medicalServices = specms_medicalServices;
    }

    @Override
    public String toString() {
        return "m2m_Specialisations_MedicalServicesEntity{" +
                "specms_id='" + specms_id + '\'' +
                ", specms_specialisation=" + specms_specialisation +
                ", specms_medicalServices=" + specms_medicalServices +
                '}';
    }
}
