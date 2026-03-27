package Domain;

public class mvsOffice {
    private Long officeId;
    private String officeCitiId;
    private String officeName;

    public mvsOffice() {
    }

    public mvsOffice(Long officeId, String officeCitiId, String officeName) {
        this.officeId = officeId;
        this.officeCitiId = officeCitiId;
        this.officeName = officeName;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeCitiId() {
        return officeCitiId;
    }

    public void setOfficeCitiId(String officeCitiId) {
        this.officeCitiId = officeCitiId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }
}
