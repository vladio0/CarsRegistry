package Domain;

public class RegisterOffice {
    private Long registerOfficeId;
    private String registerCityOfficeId;
    private String officeName;

    public RegisterOffice() {
    }

    public RegisterOffice(Long officeId, String officeCityId, String officeName) {
        this.registerOfficeId = officeId;
        this.registerCityOfficeId = officeCityId;
        this.officeName = officeName;
    }

    public Long getRegisterOfficeId() {
        return registerOfficeId;
    }

    public void setRegisterOfficeId(Long registerOfficeId) {
        this.registerOfficeId = registerOfficeId;
    }

    public String getOfficeCityId() {
        return registerCityOfficeId;
    }

    public void setOfficeCityId(String officeCitiId) {
        this.registerCityOfficeId = officeCitiId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }
}
