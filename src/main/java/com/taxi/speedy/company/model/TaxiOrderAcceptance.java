package com.taxi.speedy.company.model;

//Прием заказов от юзера или диспечера
public class TaxiOrderAcceptance extends AbstractBaseEntity {
    private UserVehicle idUserVehicle;
    private TaxiDispatcherOrder idTaxiDispatcherOrder;
    private TaxiUserOrder idTaxiUserOrder;
    private Integer executionStatus;                        //Статус выполнение: 1-выполнено, 0- не выполнено
    private Integer adoptionStatus;                         //Статус принятие: 1-принятый заказ, 0- не принятый заказ

    public TaxiOrderAcceptance() {
    }

    public TaxiOrderAcceptance(UserVehicle idUserVehicle, TaxiDispatcherOrder idTaxiDispatcherOrder, TaxiUserOrder idTaxiUserOrder, Integer executionStatus, Integer adoptionStatus) {
        this.idUserVehicle = idUserVehicle;
        this.idTaxiDispatcherOrder = idTaxiDispatcherOrder;
        this.idTaxiUserOrder = idTaxiUserOrder;
        this.executionStatus = executionStatus;
        this.adoptionStatus = adoptionStatus;
    }

    public TaxiOrderAcceptance(Integer id, UserVehicle idUserVehicle, TaxiDispatcherOrder idTaxiDispatcherOrder, TaxiUserOrder idTaxiUserOrder, Integer executionStatus, Integer adoptionStatus) {
        super(id);
        this.idUserVehicle = idUserVehicle;
        this.idTaxiDispatcherOrder = idTaxiDispatcherOrder;
        this.idTaxiUserOrder = idTaxiUserOrder;
        this.executionStatus = executionStatus;
        this.adoptionStatus = adoptionStatus;
    }

    public UserVehicle getIdUserVehicle() {
        return idUserVehicle;
    }

    public void setIdUserVehicle(UserVehicle idUserVehicle) {
        this.idUserVehicle = idUserVehicle;
    }

    public TaxiDispatcherOrder getIdTaxiDispatcherOrder() {
        return idTaxiDispatcherOrder;
    }

    public void setIdTaxiDispatcherOrder(TaxiDispatcherOrder idTaxiDispatcherOrder) {
        this.idTaxiDispatcherOrder = idTaxiDispatcherOrder;
    }

    public TaxiUserOrder getIdTaxiUserOrder() {
        return idTaxiUserOrder;
    }

    public void setIdTaxiUserOrder(TaxiUserOrder idTaxiUserOrder) {
        this.idTaxiUserOrder = idTaxiUserOrder;
    }

    public Integer getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(Integer executionStatus) {
        this.executionStatus = executionStatus;
    }

    public Integer getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(Integer adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    @Override
    public String toString() {
        return "TaxiOrderAcceptance{" +
                "idUserVehicle=" + idUserVehicle +
                ", idTaxiDispatcherOrder=" + idTaxiDispatcherOrder +
                ", idTaxiUserOrder=" + idTaxiUserOrder +
                ", executionStatus=" + executionStatus +
                ", adoptionStatus=" + adoptionStatus +
                ", id=" + id +
                '}';
    }
}
