package com.taxi.speedy.company.model;

//Вся информация о юзаре, ТС, а также текущий рабочий статус
public class TaxiUserVehicleInfo extends AbstractBaseEntity {
    private UserVehicle idUserVehicle;
    private TaxiJobStatus idTaxiJobStatus;

    public TaxiUserVehicleInfo() {
    }

    public TaxiUserVehicleInfo(UserVehicle idUserVehicle, TaxiJobStatus idTaxiJobStatus) {
        this.idUserVehicle = idUserVehicle;
        this.idTaxiJobStatus = idTaxiJobStatus;
    }

    public TaxiUserVehicleInfo(Integer id, UserVehicle idUserVehicle, TaxiJobStatus idTaxiJobStatus) {
        super(id);
        this.idUserVehicle = idUserVehicle;
        this.idTaxiJobStatus = idTaxiJobStatus;
    }

    public UserVehicle getIdUserVehicle() {
        return idUserVehicle;
    }

    public void setIdUserVehicle(UserVehicle idUserVehicle) {
        this.idUserVehicle = idUserVehicle;
    }

    public TaxiJobStatus getIdTaxiJobStatus() {
        return idTaxiJobStatus;
    }

    public void setIdTaxiJobStatus(TaxiJobStatus idTaxiJobStatus) {
        this.idTaxiJobStatus = idTaxiJobStatus;
    }

    @Override
    public String toString() {
        return "TaxiUserVehicleInfo{" +
                "idUserVehicle=" + idUserVehicle +
                ", idTaxiJobStatus=" + idTaxiJobStatus +
                ", id=" + id +
                '}';
    }
}
