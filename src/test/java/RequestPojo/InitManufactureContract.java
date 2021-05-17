package RequestPojo;

public class InitManufactureContract {
    Manufacturer ManufacturerObject;
    ContractHeader ContractHeaderObject;
    ContractDetail ContractDetailObject;

    public InitManufactureContract(Manufacturer manufacturerObject, ContractHeader contractHeaderObject, ContractDetail contractDetailObject) {
        ManufacturerObject = manufacturerObject;
        ContractHeaderObject = contractHeaderObject;
        ContractDetailObject = contractDetailObject;
    }




    // Getter Methods

    public Manufacturer getManufacturer() {
        return ManufacturerObject;
    }

    public ContractHeader getContractHeader() {
        return ContractHeaderObject;
    }

    public ContractDetail getContractDetail() {
        return ContractDetailObject;
    }

    // Setter Methods

    public void setManufacturer(Manufacturer manufacturerObject) {
        this.ManufacturerObject = manufacturerObject;
    }

    public void setContractHeader(ContractHeader contractHeaderObject) {
        this.ContractHeaderObject = contractHeaderObject;
    }

    public void setContractDetail(ContractDetail contractDetailObject) {
        this.ContractDetailObject = contractDetailObject;
    }

//    public static void main(String[] args) throws JsonProcessingException {
//        ContractDetailJson obj1= new ContractDetailJson();
//        obj1.setLineOfBusiness("abc,xyz");
//        ArrayList val = obj1.getLineOfBusiness();
//        Manufacturer manufacturerObject=new Manufacturer("ASTRA001","Astra Zeneca",false);
//        ContractHeader ContractHeaderObject=new ContractHeader(75,"ABV0011037","ASTRA001","Non-Part D","QATestAstra1947","2004-01-01","2004-02-20","2021-02-05T11:17:18.337","SYSTEM","2021-02-05T11:17:18.337","SYSTEM","NEW",null,"This is test 01");
//        Payment paymentObj=new Payment(30,"AMT",null,null,false,null);
//        Audit auditObj=new Audit("ANNUAL",12,null,true,false);
//        ContractDetailJson ContractDetailJsonObject=new ContractDetailJson(1,val,"usStates1","MON",90,60,30,true,null,null,paymentObj,auditObj);
//        ContractDetail ContractDetailObject=new ContractDetail(62,"ABV0011037",0,"QATestAstra1947","NEW","2004-01-01","2004-02-20",1,"SYSTEM","2021-02-09T19:43:07.767","SYSTEM","2021-02-09T19:43:07.767",ContractDetailJsonObject);
//        InitManufactureContract InitManufactureContractObject=new InitManufactureContract(manufacturerObject,ContractHeaderObject,ContractDetailObject);
//        ObjectMapper objectMapper=new ObjectMapper();
//        String jsonBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(InitManufactureContractObject);
//        System.out.println(jsonBody);
//
//
//    }
}
