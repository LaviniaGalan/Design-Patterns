package com.dp.domain;

import java.util.Objects;

public class DiabetesRecord {
    String gender;
    int age;
    float urea;
    int creatinine;
    float cholesterol;
    float BMI;
    String diabetesClass;

    public DiabetesRecord(String gender, int age, float urea, int creatinine, float cholesterol, float BMI, String diabetesClass) {
        this.gender = gender;
        this.age = age;
        this.urea = urea;
        this.creatinine = creatinine;
        this.cholesterol = cholesterol;
        this.BMI = BMI;
        this.diabetesClass = diabetesClass;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public float getUrea() {
        return urea;
    }

    public int getCreatinine() {
        return creatinine;
    }

    public float getCholesterol() {
        return cholesterol;
    }

    public float getBMI() {
        return BMI;
    }

    public String getDiabetesClass() {
        return diabetesClass;
    }

    @Override
    public String toString() {
        return "Record{" +
                "gender='" + gender + '\'' +
                ", age=" + age +
                ", urea=" + (urea == 0 ? " - " : urea) +
                ", creatinine=" + (creatinine == 0 ? " - " : creatinine)  +
                ", cholesterol=" + cholesterol +
                ", BMI=" + BMI +
                ", diabetesClass='" + (diabetesClass == null ? " - " : diabetesClass)  + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiabetesRecord that = (DiabetesRecord) o;
        return age == that.age && Float.compare(that.urea, urea) == 0 && creatinine == that.creatinine && Float.compare(that.cholesterol, cholesterol) == 0 && Float.compare(that.BMI, BMI) == 0 && Objects.equals(gender, that.gender) && Objects.equals(diabetesClass, that.diabetesClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, age, urea, creatinine, cholesterol, BMI, diabetesClass);
    }


    public static class RecordBuilder {
        String gender;
        int age;
        float urea;
        int creatinine;
        float cholesterol;
        float BMI;
        String diabetesClass;

        public RecordBuilder() {
        }

        public RecordBuilder setGender(String gender){
            this.gender = gender;
            return this;
        }

        public RecordBuilder setAge(int age){
            this.age = age;
            return this;
        }

        public RecordBuilder setUrea(float urea){
            this.urea = urea;
            return this;
        }

        public RecordBuilder setCreatinine(int creatinine){
            this.creatinine = creatinine;
            return this;
        }

        public RecordBuilder setCholesterol(float cholesterol){
            this.cholesterol = cholesterol;
            return this;
        }

        public RecordBuilder setBMI(float BMI){
            this.BMI = BMI;
            return this;
        }

        public RecordBuilder setDiabetesClass(String diabetesClass){
            this.diabetesClass = diabetesClass;
            return this;
        }

        public DiabetesRecord build(){
            return new DiabetesRecord(gender, age, urea, creatinine, cholesterol, BMI, diabetesClass);
        }
    }
}
