package building.builder;

import lombok.Getter;

import java.util.List;

@Getter
public class BuildingSearchBuilder {
    private Long id;
    private String name;
    private Integer floorArea;
    private Integer districtId;
    private String ward;
    private String street;
    private Integer numberOfBasement;
    private String position;
    private String level;
    private Double rentAreaTo;
    private Double rentAreaFrom;
    private Double rentPriceTo;
    private Double rentPriceFrom;
    private String managerName;
    private String managerPhoneNumber;
    private Integer staffId;
    private Integer userId;
    private Double rentPrice;
    private Double brokeageFee;
    private Double emptyArea;
    private String serviceFee;
    private List<String> typeCode;

    public BuildingSearchBuilder(Builder builder){
        this.id = builder.id;
        this.name= builder.name;
        this.floorArea = builder.floorArea;
        this.districtId = builder.districtId;
        this.ward = builder.ward;
        this.street = builder.street;
        this.numberOfBasement = builder.numberOfBasement;
        this.position = builder.position;
        this.level = builder.level;
        this.rentAreaTo = builder.rentAreaTo;
        this.rentAreaFrom = builder.rentAreaFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.managerName = builder.managerName;
        this.managerPhoneNumber = builder.managerPhoneNumber;
        this.staffId = builder.staffId;
        this.userId = builder.userId;
        this.rentPrice = builder.rentPrice;
        this.brokeageFee = builder.brokeageFee;
        this.emptyArea = builder.emptyArea;
        this.serviceFee = builder.serviceFee;
        this.typeCode = builder.typeCode;
    }


    public static class Builder {
        private Long id;
        private String name;
        private Integer floorArea;
        private Integer districtId;
        private String ward;
        private String street;
        private Integer numberOfBasement;
        private String position;
        private String level;
        private Double rentAreaTo;
        private Double rentAreaFrom;
        private Double rentPriceTo;
        private Double rentPriceFrom;
        private String managerName;
        private String managerPhoneNumber;
        private Integer staffId;
        private Integer userId;
        private Double rentPrice;
        private Double brokeageFee;
        private Double emptyArea;
        private String serviceFee;
        private List<String> typeCode;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setNameBuilding(String name){
            this.name = name;
            return this;
        }

        public Builder setFloorArea(Integer floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setDistrictId(Integer districtId) {
            this.districtId = districtId;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setPosition(String position) {
            this.position = position;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setRentAreaTo(Double rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
            return this;
        }

        public Builder setRentAreaFrom(Double rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }

        public Builder setRentPriceTo(Double rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Builder setRentPriceFrom(Double rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setManagerPhoneNumber(String managerPhoneNumber) {
            this.managerPhoneNumber = managerPhoneNumber;
            return this;
        }

        public Builder setStaffId(Integer staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder setRentPrice(Double rentPrice) {
            this.rentPrice = rentPrice;
            return this;
        }

        public Builder setBrokeageFee(Double brokeageFee) {
            this.brokeageFee = brokeageFee;
            return this;
        }

        public Builder setEmptyArea(Double emptyArea) {
            this.emptyArea = emptyArea;
            return this;
        }

        public Builder setServiceFee(String serviceFee) {
            this.serviceFee = serviceFee;
            return this;
        }

        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public BuildingSearchBuilder build(){
            return new BuildingSearchBuilder(this);
        }
    }

}
