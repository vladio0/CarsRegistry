package Domain;

import java.time.LocalDate;

public class VehicleDocument extends Car {

    private String docType;
    private LocalDate docIssuedAt;
    private LocalDate docExpiresAt;
    private boolean isActive;
    private RegisterOffice registerOffice;

    public VehicleDocument() {
    }

    public VehicleDocument(String carMark, String carModel, String carCountry, int carYear,
                           String carLicensePlate, String carColour, String carFuelType,
                           double carWeight) {
        super(carMark, carModel, carCountry, carYear, carLicensePlate, carColour,
                carFuelType, carWeight);
    }


    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public LocalDate getDocIssuedAt() {
        return docIssuedAt;
    }

    public void setDocIssuedAt(LocalDate docIssuedAt) {
        this.docIssuedAt = docIssuedAt;
    }

    public LocalDate getDocExpiresAt() {
        return docExpiresAt;
    }

    public void setDocExpiresAt(LocalDate docExpiresAt) {
        this.docExpiresAt = docExpiresAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public RegisterOffice getRegisterOffice() {
        return registerOffice;
    }

    public void setRegisterOffice(RegisterOffice registerOffice) {
        this.registerOffice = registerOffice;
    }
}

